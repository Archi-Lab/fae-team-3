package faeteam3.Notlage.controller;


import faeteam3.Notlage.kafka.SendeEinheit;
import faeteam3.Notlage.model.Nachricht;
import faeteam3.Notlage.model.Notlage;
import faeteam3.Notlage.model.support.BPantwort;
import faeteam3.Notlage.model.support.UngeRou;
import faeteam3.Notlage.model.support.UngeVer;
import faeteam3.Notlage.repository.NotlageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exceptions.IllegalActionExcepiton;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
* Basis Controller Klasse für die Bearbeitung von REST anfragen
*/
@RepositoryRestController()
public class NotlageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotlageController.class);
    private NotlageRepository notlageRepository;

    @Autowired
    public NotlageController(NotlageRepository notlageRepository) {
        this.notlageRepository = notlageRepository;
    }
    
    @Autowired
    private SendeEinheit sender;
    
    public NotlageController()
    {}

    
    /**
	    * Diese Funktion dient nur für  das Testen von Kafka,
	    * <br> hallo
	    * @param Keine
	    * @return HTML Status 200 OK
	    * @throws Keine
	    */
	    @GetMapping("/notlage/hello")
	    public ResponseEntity<?> hello(){
	    	LOGGER.info("SENDE Messages");


	        IntStream.range(0, 2)
	                 .forEach(i -> 
	                 {
	                	 sender.sendUngeVer( new UngeVer("112","extraPayload","data3" ,String.valueOf(i)));
	                	 sender.sendUngeRou( new UngeRou("336","extraPayload","data66",String.valueOf(i)));
	                 }      
	                		 );
	        LOGGER.info("All messages received");
	        return  ResponseEntity.ok().build();
	    }
    
   
    
    /**
    * Es wird eine neue Notlage Ressource angelegt. Die ID ist ein Long Wert.
    * @author FAE: Team 3
    * @version 1.0
    * @param nachricht
    * <br> Die originID darf nicht bereits mit einer Notlage assoziiert sein.
    * <br> Die Origin (Herkunft) muss eines der folgenden {@link faeteam3.Notlage.model.support.Konstants Werte} haben:
    * <li> ungewöhnliches Verhalten
    * <li> ungewöhnliche Route
    * @return HTML Status 200 OK und Körper der Ressource
    * @throws IllegalActionExcepiton 
    * <li> Bei falschen Eingabedaten.
    */
    @PostMapping("/notlage")
    ResponseEntity<?> addNotlage(@RequestBody Nachricht nachricht) 
    {
    	LOGGER.info("POST Notlage");
    	// TODO teste auf andere fehlende Informationen und darauf, ob DVP existiert und ob origin existiert
    	// und ob es bereits eine Notlage mit der originID gibt
    	if (nachricht.getDvpid()==null)
    	{
		      throw new IllegalActionExcepiton("Illegale DVP ID-" + nachricht.getDvpid());
		      
    	}

		Notlage notlage = new Notlage();
		notlage.setDvp(nachricht.getDvpid());
		notlage.setExtraDatat(nachricht.getPayload());
		notlage.setOrigin(nachricht.getOrigin());
		notlage.setIdOrigin(nachricht.getOrigin_id());
		notlage = notlageRepository.save(notlage);
		
		Resource<Notlage> res =new Resource<>(notlage);
		res.add(linkTo(methodOn(NotlageController.class).getNotlage(notlage.getNotlageId())).withSelfRel().withType("GET"));
		LOGGER.info("POST Notlage success");
		sender.sendNotlage(notlage);

       return ResponseEntity.status(HttpStatus.OK).body(res);	     
    }
    
    /**
     * Alle Notlage Ressorucen werden zurück gesendet.
     * @author FAE: Team 3
     * @version 1.0
     * @return HTML Status 200 OK und Körper der Ressource
     * @throws IllegalActionExcepiton 
     * <li> Wenn die Notlage Ressoruce nicht existiert
     */
    @GetMapping(path = "/notlage")
    public ResponseEntity<?> getAllNotlagen(){
    	LOGGER.info("GET ALL NOTLAGEN");
        final Iterable<Notlage> notlageList = this.notlageRepository.findAll();
        final List<Resource> resourceLst = new ArrayList<>();
        notlageList.forEach(n->
        {
        	// Vlt. wäre es besser, nur eine Liste mit ID Werten zurück zu geben.
            Resource<Notlage> resource = new Resource<>(n);
            resource.add(linkTo(methodOn(NotlageController.class).getNotlage(n.getNotlageId())).withSelfRel().withType("GET"));
            resourceLst.add(resource);
        });
        Resources<Resource<Notlage>> resources = new Resources(resourceLst);
        resources.add(linkTo(methodOn(NotlageController.class).getAllNotlagen()).withSelfRel().withType("GET"));

        LOGGER.info("GET ALL NOTLAGEN sucess");
        return  ResponseEntity.ok(resources);
    }

    /**
     * Die spezifizierte Notlage Ressoruce wird zurück gesendet.
     * @author FAE: Team 3
     * @version 1.0
     * @param id Pfad Variable. ID der Notlage Ressource.
     * @return HTML Status 200 OK und Körper der Ressource
     * @throws IllegalActionExcepiton 
     * <li> Wenn die Notlage Ressoruce nicht existiert
     */
    @GetMapping(path = "/notlage/{id}")
    public ResponseEntity<?> getNotlage(@PathVariable Long id){
    	
    	
    	LOGGER.info("RETURN NOTLAGE" + id);
    	final Optional<Notlage> optNotlage = notlageRepository.findById(id);
		
		if (!optNotlage.isPresent())
		{
			LOGGER.info("Notlage<"+ id+"> not found");
			throw new IllegalActionExcepiton("Existiert Nicht!- " + id);
		   // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    
//		    throw new IllegalActionExcepiton("Existiert Nicht!- " + id);
		}
		Notlage notlage = optNotlage.get();
		
		Resource<Notlage> res =new Resource<>(notlage);
		res.add(linkTo(methodOn(NotlageController.class).getNotlage(notlage.getNotlageId())).withSelfRel().withType("GET"));
		if(notlage.isGeloest()==false)
			res.add(linkTo(methodOn(NotlageController.class).notlageBestaetigen(id,null)).withRel("lösen").withType("PUT"));
		if(notlage.isBestaetigt()==false)
			res.add(linkTo(methodOn(NotlageController.class).notlageLoesen(id,null)).withRel("bestätigen").withType("PUT"));

		LOGGER.info("Notlage<"+ id+"> found");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    /**
     * Die Notlage wird als Bestätigt markiert.
     * <br> Wenn die Notlage bereits gelöst ist, dann wird die Ressource nicht verändert und es wird Exception geworfen.
     * <br> Wenn die Notlage bereits bestätigt ist, dann wird die Ressource nicht verändert und es wird keine Exception geworfen.
     * @author FAE: Team 3
     * @version 1.0
     * @param id Pfad Variable. ID der Notlage Ressource.
     * @param antwort Format: {@link faeteam3.Notlage.model.support.BPantwort BPantwort}
     * <br> Die BP ID muss existieren.
     * @return HTML Status 202 ACCEPTED und Körper der Ressource
     * @throws IllegalActionExcepiton 
     * <li> Bei falschen Eingabedaten
     * <li> Wenn die Notlage Ressoruce nicht existiert
     * <li> Wenn die Notlage bereits gelöst wurde
     */
    @PutMapping("/notlage/{id}/bestaetigen")
    public ResponseEntity<?> notlageBestaetigen(@PathVariable Long id, @RequestBody BPantwort antwort) {
    	
    	LOGGER.info("PUT: confirm Notlage");
    	final Optional<Notlage> optNotlage = this.notlageRepository.findById(id);
		
		if (!optNotlage.isPresent())
		{
			LOGGER.info("Notlage<"+ id+"> not found");
		    throw new IllegalActionExcepiton("Existiert Nicht!- " + id);
		}
		
		// TODO teste darauf, ob BP existiert  und ob bereits gelöst ist
		
		Notlage notlage = optNotlage.get();
		LOGGER.info("Tries to mark Notlage<" + notlage.getNotlageId() +"> as confirmed.");
		if(notlage.isBestaetigt()==false)
		{
			notlage.bestaetigeNotlage(antwort.getBpID());
			notlage = notlageRepository.save(notlage);
			sender.sendNotlage(notlage);
		}

		Resource<Notlage> res =new Resource<>(notlage);
		res.add(linkTo(methodOn(NotlageController.class).getNotlage(notlage.getNotlageId())).withSelfRel().withType("GET"));
		if(notlage.isGeloest()==false)
			res.add(linkTo(methodOn(NotlageController.class).notlageBestaetigen(id,null)).withRel("lösen").withType("PUT"));
		if(notlage.isBestaetigt()==false)
			res.add(linkTo(methodOn(NotlageController.class).notlageLoesen(id,null)).withRel("bestätigen").withType("PUT"));
		
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
        
    }

    /**
     * Die Notlage wird als Gelöst markiert.
     * <br> Notlage muss nicht bereits bestätigt sein.
     * <br> Wenn die Notlage bereits gelöst ist, dann wird die Ressource nicht verändert und es wird keine Exception geworfen.
     * @author FAE: Team 3
     * @version 1.0
     * @param id Pfad Variable. ID der Notlage Ressource.
     * @param antwort Format: {@link faeteam3.Notlage.model.support.BPantwort BPantwort}
     * <br> Die BP ID muss existieren.
     * @return HTML Status 202 ACCEPTED und Körper der Ressource
     * @throws IllegalActionExcepiton 
     * <li> Bei falschen Eingabedaten.
     * <li> Wenn die Notlage Ressoruce nicht existiert
     */
    @PutMapping("/notlage/{id}/loesen")
    public ResponseEntity<?> notlageLoesen(@PathVariable Long id, @RequestBody BPantwort antwort) {
    	
    	
    	LOGGER.info("PUT: solve Notlage");
    	final Optional<Notlage> optNotlage = this.notlageRepository.findById(id);
		
		if (!optNotlage.isPresent())
		{
			LOGGER.info("Notlage<"+ id+"> not found");
		    throw new IllegalActionExcepiton("Existiert Nicht!- " + id);
		}
		
		// TODO teste darauf, ob BP existiert
		
		Notlage notlage = optNotlage.get();
		LOGGER.info("Tries to mark Notlage<" + notlage.getNotlageId() +"> as solved.");
		if(notlage.isGeloest()==false)
		{
			notlage.loeseNotlage(antwort.getBpID());
			notlage = notlageRepository.save(notlage);
			sender.sendNotlage(notlage);
		}

		Resource<Notlage> res =new Resource<>(notlage);
		res.add(linkTo(methodOn(NotlageController.class).getNotlage(notlage.getNotlageId())).withSelfRel().withType("GET"));
		if(notlage.isGeloest()==false)
			res.add(linkTo(methodOn(NotlageController.class).notlageBestaetigen(id,null)).withRel("lösen").withType("PUT"));
		if(notlage.isBestaetigt()==false)
			res.add(linkTo(methodOn(NotlageController.class).notlageLoesen(id,null)).withRel("bestätigen").withType("PUT"));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
        
    }
      
	// vlt löshen mal testen
	@ExceptionHandler
	void handleIllegalArgumentException( IllegalArgumentException e,  HttpServletResponse response) throws IOException 
	{
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

}
