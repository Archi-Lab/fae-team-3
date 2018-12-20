package faeteam3.Bezugsperson.Bezugsperson.controller;

import faeteam3.Bezugsperson.Bezugsperson.models.Anwesenheit;
import faeteam3.Bezugsperson.Bezugsperson.models.Benachrichtigung;
import faeteam3.Bezugsperson.Bezugsperson.models.Bezugsperson;
import faeteam3.Bezugsperson.Bezugsperson.models.EMailAdresse;
import faeteam3.Bezugsperson.Bezugsperson.models.Kommunikationsart;
import faeteam3.Bezugsperson.Bezugsperson.models.Kommunikationskanal;
import faeteam3.Bezugsperson.Bezugsperson.models.TechAdresse;
import faeteam3.Bezugsperson.Bezugsperson.repositories.AnwesenheitRepository;
import faeteam3.Bezugsperson.Bezugsperson.repositories.BenachrichtigungRepository;
import faeteam3.Bezugsperson.Bezugsperson.repositories.BezugspersonRepository;
import faeteam3.Bezugsperson.Bezugsperson.repositories.KommunikationskanalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Bezugsperson")
@ExposesResourceFor(Bezugsperson.class)
public class BezugspersonController {
	
	
	@Autowired
	private WorkerService service;

    private final BezugspersonRepository bezugspersonRepository;
    private final AnwesenheitRepository anwesenheitRepository;
    private final BenachrichtigungRepository benachrichtigungRepository;
    private final KommunikationskanalRepository kommunikationskanalRepository;

    @Autowired
    public BezugspersonController(
            BezugspersonRepository bezugspersonRepository,
            AnwesenheitRepository anwesenheitRepository,
            BenachrichtigungRepository benachrichtigungRepository,
            KommunikationskanalRepository kommunikationskanalRepository) {
        this.bezugspersonRepository = bezugspersonRepository;
        this.anwesenheitRepository = anwesenheitRepository;
        this.benachrichtigungRepository = benachrichtigungRepository;
        this.kommunikationskanalRepository = kommunikationskanalRepository;
    }

    /*
        APIs für die Bezugspersonen
     */
    
    @RequestMapping(path = "", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBezugspersonen() {

    	Iterable<Bezugsperson> bpList = bezugspersonRepository.findAll();
    	
        Resources<Bezugsperson> resources = new Resources<>(bpList);

        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .getBezugspersonen()).withSelfRel());

        for (Bezugsperson bp : bpList) {

         	resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
         			.getBezugsperson(bp.getBp_id())).withSelfRel());

        }
        
        return ResponseEntity.ok(resources);
    }
    
    @RequestMapping(path = "", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postBezugsperson(@RequestBody Map<String,String> body) {
        String bp_id_ext = body.get("bp_id_ext");

        Bezugsperson bezugsperson = bezugspersonRepository.save(new Bezugsperson(bp_id_ext));

        List<Bezugsperson> bp_list = new ArrayList<Bezugsperson>();
        bp_list.add(bezugsperson);

        Resources<Bezugsperson> resources = new Resources<>(bp_list);

        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .getBezugsperson(bezugsperson.getBp_id())).withSelfRel());

        return ResponseEntity.ok(resources);

    }

    @RequestMapping(path = "/{bp_id}", method = GET)
    public ResponseEntity<?> getBezugsperson(@PathVariable String bp_id) {
        List<Bezugsperson> bezugspersonList = new ArrayList<Bezugsperson>();
        Optional<Bezugsperson> optionalBezugsperson = bezugspersonRepository.findById(bp_id);

        if (optionalBezugsperson.isPresent()) {

            bezugspersonList.add(optionalBezugsperson.get());
            Resources<Bezugsperson> resources = new Resources<>(bezugspersonList);
            resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                    .getBezugsperson(bp_id)).withSelfRel());

            return ResponseEntity.ok(resources);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(path= "/{bp_id}", method = DELETE)
    public ResponseEntity<?> deleteBezugsperson(@PathVariable String bp_id) {

        if(!bezugspersonRepository.findById(bp_id).isPresent()) {
            return ResponseEntity.noContent().build();
        }

        bezugspersonRepository.deleteById(bp_id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/{bp_id}/Kommunikationskanal", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getKommunikationskanaele(@PathVariable("bp_id") String bp_id) {
    	Bezugsperson bp = bezugspersonRepository.findById(bp_id).get();
    	
    	List<Kommunikationskanal> kList = bp.getKommunikationskanalList();
    	
        Resources<Kommunikationskanal> resources = new Resources<>(kList);

        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .getAnwesenheiten(bp_id)).withSelfRel());

        
        for (Kommunikationskanal k : kList) {
        	resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
        			.getKommunikationskanal(bp_id, k.getKanalId())).withSelfRel());
        }
        
        
        return ResponseEntity.ok(resources);
    }
    
    @RequestMapping(path = "/{bp_id}/Kommunikationskanal", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addKommunikationskanal(@PathVariable String bp_id, @RequestBody Map<String, String> body) {
        
    	Bezugsperson bezugsperson = bezugspersonRepository.findById(bp_id).get();
        
    	Kommunikationskanal ka = null;
    	switch (Kommunikationsart.valueOf(body.get("Kommunikationsart"))) {
		case EMAIL:
			ka = new Kommunikationskanal(body.get("Bezeichnung"), Kommunikationsart.valueOf(body.get("Kommunikationsart")), new TechAdresse(body.get("Adresse")));
			break;
		case MMS: // TODO
			break;
		case PUSH:
			break;
		case SMS:
			break;
		case WHATSPASS:
			break;
    		
    	}
    	
        ka = kommunikationskanalRepository.save(ka);
        
        bezugsperson.addKommunikationskanal(ka);
        bezugspersonRepository.save(bezugsperson);
        
        Resources<Kommunikationskanal> resources = new Resources<Kommunikationskanal>(bezugsperson.getKommunikationskanalList());

        
        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .getKommunikationskanal(bp_id, ka.getKanalId())).withSelfRel());
        
        
        return ResponseEntity.ok(resources);
    }
    
    @RequestMapping(path = "/{bp_id}/Kommunikationskanal/{k_id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getKommunikationskanal(@PathVariable("bp_id") String bp_id, @PathVariable("k_id") String k_id) {
    	  Kommunikationskanal kk = kommunikationskanalRepository.findById(k_id).get();

          List<Kommunikationskanal> kList = new ArrayList<>();
          kList.add(kk);

          Resources<Kommunikationskanal> resources = new Resources<>(kList);

          resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                  .getKommunikationskanal(bp_id, k_id)).withSelfRel());

          return ResponseEntity.ok(resources);
    }
    
    @RequestMapping(path = "/{bp_id}/Kommunikationskanal/{k_id}", method = DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteKommunikationskanal(@PathVariable("bp_id") String bp_id, @PathVariable("k_id") String k_id) {
        
    	if(!bezugspersonRepository.findById(bp_id).isPresent()) {
    		return ResponseEntity.noContent().build();
    	}
    	
    	if(!kommunikationskanalRepository.findById(k_id).isPresent()) {
            return ResponseEntity.noContent().build();
        }
        
    	Kommunikationskanal k = kommunikationskanalRepository.findById(k_id).get();
    	Bezugsperson bp = bezugspersonRepository.findById(bp_id).get();
    	
    	bp.getKommunikationskanalList().remove(k);
    	bezugspersonRepository.save(bp);
    	kommunikationskanalRepository.delete(k);
    	
        return ResponseEntity.ok().build();
    }

    /*
        APIs für die Benachrichtigungen einer Bezugsperson
     */
    @RequestMapping(path = "/{bp_id}/Anwesenheit", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postAnwesenheit(@PathVariable String bp_id,@RequestBody Map<String,String> body){
    		/*
                                             @ModelAttribute("von") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) GregorianCalendar von,
                                             @ModelAttribute("bis") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) GregorianCalendar bis) {
    	*/
        Bezugsperson bezugsperson = bezugspersonRepository.findById(bp_id).get();

        LocalDateTime von = LocalDateTime.parse(body.get("von"), DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime bis = LocalDateTime.parse(body.get("bis"), DateTimeFormatter.ISO_DATE_TIME);
        
        Anwesenheit anwesenheit = new Anwesenheit(von, bis);
        
        System.out.println(body.get("von"));
        System.out.println(body.get("bis"));
        
        anwesenheit = anwesenheitRepository.save(anwesenheit);

        anwesenheit = anwesenheitRepository.findById(anwesenheit.getAnw_id()).get();
        System.out.println(anwesenheit.getStartDate().toString());
        System.out.println(anwesenheit.getEndDate().toString());
        
        bezugsperson.addAnwesenheit(anwesenheit);
        bezugspersonRepository.save(bezugsperson);

        //List<Anwesenheit> anwesenheitList = new ArrayList<>();
        //anwesenheitList.add(anwesenheit);

        
        Resources<Anwesenheit> resources = new Resources<Anwesenheit>(bezugsperson.getAnwesenheitList());

        
        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .getAnwesenheit(bp_id, anwesenheit.getAnw_id())).withSelfRel());
        
        
        return ResponseEntity.ok(resources);
    }

    @RequestMapping(path = "/{bp_id}/Anwesenheit/{anw_id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnwesenheit(@PathVariable("bp_id") String bp_id, @PathVariable("anw_id") String anw_id) {

        Anwesenheit anwesenheit = anwesenheitRepository.findById(anw_id).get();

        List<Anwesenheit> anwesenheitList = new ArrayList<>();
        anwesenheitList.add(anwesenheit);

        Resources<Anwesenheit> resources = new Resources<>(anwesenheitList);

        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .getAnwesenheit(bp_id, anw_id)).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @RequestMapping(path = "/{bp_id}/Anwesenheit", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnwesenheiten(@PathVariable("bp_id") String bp_id) {

    	Bezugsperson bp = bezugspersonRepository.findById(bp_id).get();
    	
    	List<Anwesenheit> anwesenheitList = bp.getAnwesenheitList();
    	
        Resources<Anwesenheit> resources = new Resources<>(anwesenheitList);

        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .getAnwesenheiten(bp_id)).withSelfRel());

        
        for (Anwesenheit anw : anwesenheitList) {
        	resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
        			.getAnwesenheit(bp_id, anw.getAnw_id())).withSelfRel());
        }
        
        
        return ResponseEntity.ok(resources);
    }

    @RequestMapping(path = "/{bp_id}/Anwesenheit/{anw_id}", method = DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAnwesenheit(@PathVariable("bp_id") String bp_id, @PathVariable("anw_id") String anw_id) {
        
    	if(!bezugspersonRepository.findById(bp_id).isPresent()) {
    		return ResponseEntity.noContent().build();
    	}
    	
    	if(!anwesenheitRepository.findById(anw_id).isPresent()) {
            return ResponseEntity.noContent().build();
        }
        
    	Anwesenheit a = anwesenheitRepository.findById(anw_id).get();
    	Bezugsperson bp = bezugspersonRepository.findById(bp_id).get();
    	
    	bp.getAnwesenheitList().remove(a);
    	bezugspersonRepository.save(bp);
    	anwesenheitRepository.delete(a);
    	
        return ResponseEntity.ok().build();
    }

    /*
        Benachrichtigung APIs
     */
    @RequestMapping(path = "/benachrichtigeBPs", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> benachrichtigeBPs(@ModelAttribute("notlage_uri") String notlage_uri,
                                               @ModelAttribute("dvp_id") String dvp_id,
                                               @ModelAttribute("nachricht") String nachricht) {
    	// TODO  hier warscheinlich neue Notlage bearbeiten, also BP ermittlen. Hier link zu WorkerService
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/Benachrichtigung/{ben_id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBenachrichtigung(@PathVariable("ben_id") Long ben_id) {
        Benachrichtigung benachrichtigung = benachrichtigungRepository.findById(ben_id).get();

        List<Benachrichtigung> benachrichtigungList = new ArrayList<>();
        benachrichtigungList.add(benachrichtigung);

        Resources<Benachrichtigung> resources = new Resources<>(benachrichtigungList);

        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .getBenachrichtigung(ben_id)).withSelfRel());
        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .putBenachrichtigungZustellen(ben_id)).withSelfRel());
        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .putBenachrichtigungGelesen(ben_id)).withSelfRel());


        return ResponseEntity.ok(resources);
    }

    @RequestMapping(path = "/Benachrichtigung/{ben_id}/gelesen", method = PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putBenachrichtigungGelesen(@PathVariable("ben_id") Long ben_id) {
        Benachrichtigung benachrichtigung = benachrichtigungRepository.findById(ben_id).get();

        benachrichtigung.setzeAlsGelesen();
        benachrichtigungRepository.save(benachrichtigung);

        List<Benachrichtigung> benachrichtigungList = new ArrayList<>();
        benachrichtigungList.add(benachrichtigung);

        Resources<Benachrichtigung> resources = new Resources<>(benachrichtigungList);

        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class).getBenachrichtigung(ben_id)).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @RequestMapping(path = "/Benachrichtigung/{ben_id}/zustellen", method = PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putBenachrichtigungZustellen(@PathVariable("ben_id") Long ben_id) {
        Benachrichtigung benachrichtigung = benachrichtigungRepository.findById(ben_id).get();

        benachrichtigung.setzeAlsZugestellt();
        benachrichtigungRepository.save(benachrichtigung);

        List<Benachrichtigung> benachrichtigungList = new ArrayList<>();
        benachrichtigungList.add(benachrichtigung);

        Resources<Benachrichtigung> resources = new Resources<>(benachrichtigungList);

        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class).getBenachrichtigung(ben_id)).withSelfRel());

        return ResponseEntity.ok(resources);
    }

}