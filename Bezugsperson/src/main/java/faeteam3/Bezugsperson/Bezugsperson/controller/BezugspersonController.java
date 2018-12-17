package faeteam3.Bezugsperson.Bezugsperson.controller;

import faeteam3.Bezugsperson.Bezugsperson.models.Anwesenheit;
import faeteam3.Bezugsperson.Bezugsperson.models.Benachrichtigung;
import faeteam3.Bezugsperson.Bezugsperson.models.Bezugsperson;
import faeteam3.Bezugsperson.Bezugsperson.repositories.AnwesenheitRepository;
import faeteam3.Bezugsperson.Bezugsperson.repositories.BenachrichtigungRepository;
import faeteam3.Bezugsperson.Bezugsperson.repositories.BezugspersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.DateTimeParser;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/Bezugsperson")
@ExposesResourceFor(Bezugsperson.class)
public class BezugspersonController {
	
	
	@Autowired
	private WorkerService service;

    private final BezugspersonRepository bezugspersonRepository;
    private final AnwesenheitRepository anwesenheitRepository;
    private final BenachrichtigungRepository benachrichtigungRepository;

    @Autowired
    public BezugspersonController(
            BezugspersonRepository bezugspersonRepository,
            AnwesenheitRepository anwesenheitRepository,
            BenachrichtigungRepository benachrichtigungRepository) {
        this.bezugspersonRepository = bezugspersonRepository;
        this.anwesenheitRepository = anwesenheitRepository;
        this.benachrichtigungRepository = benachrichtigungRepository;
    }

    /*
        APIs für die Bezugspersonen
     */

    @RequestMapping(path = "", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postBezugsperson(/*@RequestBody String bp_id_ext*/ @RequestBody Map<String,String> body) {
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
    public ResponseEntity<?> getBezugsperson(@PathVariable Long bp_id) {
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
    public ResponseEntity<?> deleteBezugsperson(@PathVariable Long bp_id) {

        if(!bezugspersonRepository.findById(bp_id).isPresent()) {
            return ResponseEntity.noContent().build();
        }

        bezugspersonRepository.deleteById(bp_id);
        return ResponseEntity.ok().build();
    }



    /*
        APIs für die Benachrichtigungen einer Bezugsperson
     */
    // TODO
    @RequestMapping(path = "/{bp_id}/Anwesenheit", method = POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postAnwesenheit(@PathVariable Long bp_id,
                                             @ModelAttribute("von") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") GregorianCalendar von,
                                             @ModelAttribute("bis") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") GregorianCalendar bis) {

        Bezugsperson bezugsperson = bezugspersonRepository.findById(bp_id).get();

        Anwesenheit anwesenheit = new Anwesenheit(von, bis);

        anwesenheit = anwesenheitRepository.save(anwesenheit);

        bezugsperson.addAnwesenheit(anwesenheit);
        bezugspersonRepository.save(bezugsperson);

        List<Anwesenheit> anwesenheitList = new ArrayList<>();
        anwesenheitList.add(anwesenheit);

        Resources<Anwesenheit> resources = new Resources<>(anwesenheitList);

        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .getAnwesenheit(bp_id, anwesenheit.getAnw_id())).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @RequestMapping(path = "/{bp_id}/Anwesenheit/{anw_id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnwesenheit(@PathVariable("bp_id") Long bp_id, @PathVariable("anw_id") Long anw_id) {

        Anwesenheit anwesenheit = anwesenheitRepository.findById(anw_id).get();

        List<Anwesenheit> anwesenheitList = new ArrayList<>();
        anwesenheitList.add(anwesenheit);

        Resources<Anwesenheit> resources = new Resources<>(anwesenheitList);

        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .getAnwesenheit(bp_id, anw_id)).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    // TODO
    @RequestMapping(path = "/{bp_id}/Anwesenheit", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnwesenheiten(@PathVariable("bp_id") Long bp_id) {

        List<Long> anwesenheitIDList = bezugspersonRepository.findById(bp_id).get().getAnwesenheitList();
        Iterable<Anwesenheit> anwesenheitList = anwesenheitRepository.findAllById(anwesenheitIDList);

        Resources<Anwesenheit> resources = new Resources<>(anwesenheitList);

        resources.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(BezugspersonController.class)
                .getAnwesenheiten(bp_id)).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @RequestMapping(path = "/{bp_id}/Anwesenheit/{anw_id}", method = DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAnwesenheit(@PathVariable("bp_id") Long bp_id, @PathVariable("anw_id") Long anw_id) {
        if(!anwesenheitRepository.findById(anw_id).isPresent()) {
            return ResponseEntity.noContent().build();
        }

        anwesenheitRepository.deleteById(anw_id);
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