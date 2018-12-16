package faeteam3.Notlage.controller;


import faeteam3.Notlage.kafka.SendeEinheit;
import faeteam3.Notlage.model.Nachricht;
import faeteam3.Notlage.model.Notlage;
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
    
    
    // Kafka  test
    @GetMapping("/notlage/hello")
    public ResponseEntity<?> hello(){
    	 LOGGER.info("SENDE Messages");

         IntStream.range(0, 2)
                 .forEach(i -> {
                 sender.send2("ungeVer.t", new UngeVer(1L,"data1","data3",(long) i));
                 sender.send3("ungeRou.t", new UngeRou(3L,"data44","data66",(long) i));
                 }      
                 );
         LOGGER.info("All messages received");
        return  ResponseEntity.ok().build();
    }
    
    @PostMapping("/notlage")
    ResponseEntity<?> addNotlage(@RequestBody Nachricht nachricht) 
    {
    	LOGGER.info("POST Notlage");
    	if (nachricht.getDvpid()==null)
		      throw new IllegalActionExcepiton("Illegale DVP ID-" + nachricht.getDvpid());

		Notlage notlage = notlageRepository.save(new Notlage(nachricht));
		
		Resource<Notlage> res =new Resource<>(notlage);
		res.add(linkTo(methodOn(NotlageController.class).getNotlage(notlage.getNotlageId())).withSelfRel().withType("GET"));
		LOGGER.info("POST Notlage success");

       return ResponseEntity.status(HttpStatus.OK).body(res);	     
    }
    
    @GetMapping(path = "/notlage")
    public ResponseEntity<?> getAllNotlagen(){
    	LOGGER.info("GET ALL NOTLAGEN");
        final Iterable<Notlage> notlageList = this.notlageRepository.findAll();
        final List<Resource> resourceLst = new ArrayList<>();
        notlageList.forEach(n->
        {
            Resource<Notlage> resource = new Resource<>(n);
            resourceLst.add(resource);
        });
        Resources<Resource<Notlage>> resources = new Resources(resourceLst);
        resources.add(linkTo(methodOn(NotlageController.class).getAllNotlagen()).withSelfRel().withType("GET"));

        LOGGER.info("GET ALL NOTLAGEN sucess");
        return  ResponseEntity.ok(resources);
    }

    @GetMapping(path = "/notlage/{id}")
    public ResponseEntity<?> getNotlage(@PathVariable Long id){
    	
    	
    	LOGGER.info("RETURN NOTLAGE" + id);
    	final Optional<Notlage> optNotlage = notlageRepository.findById(id);
		
		if (!optNotlage.isPresent())
		{
			LOGGER.info("Notlage<"+ id+"> not found");
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
    }

    @PutMapping("/notlage/{id}/bestaetigen")
    public ResponseEntity<?> notlageBestaetigen(@PathVariable Long id, @RequestBody Long bpID) {
    	
    	LOGGER.info("PUT: confirm Notlage");
    	final Optional<Notlage> optNotlage = this.notlageRepository.findById(id);
		
		if (!optNotlage.isPresent())
		{
			LOGGER.info("Notlage<"+ id+"> not found");
		    throw new IllegalActionExcepiton("Existiert Nicht!- " + id);
		}
		
		Notlage notlage = optNotlage.get();
		LOGGER.info("Tries to mark Notlage<" + notlage.getNotlageId() +"> as confirmed.");
		if(notlage.isBestaetigt()==false)
		{
			notlage.bestaetigeNotlage(bpID);
			notlageRepository.save(notlage);
		}

		Resource<Notlage> res =new Resource<>(notlage);
		res.add(linkTo(methodOn(NotlageController.class).getNotlage(notlage.getNotlageId())).withSelfRel().withType("GET"));
		if(notlage.isGeloest()==false)
			res.add(linkTo(methodOn(NotlageController.class).notlageBestaetigen(id,null)).withRel("lösen").withType("PUT"));
		if(notlage.isBestaetigt()==false)
			res.add(linkTo(methodOn(NotlageController.class).notlageLoesen(id,null)).withRel("bestätigen").withType("PUT"));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
        
    }

    @PutMapping("/notlage/{id}/loesen")
    public ResponseEntity<?> notlageLoesen(@PathVariable Long id, @RequestBody Long bpID) {
    	
    	
    	LOGGER.info("PUT: solve Notlage");
    	final Optional<Notlage> optNotlage = this.notlageRepository.findById(id);
		
		if (!optNotlage.isPresent())
		{
			LOGGER.info("Notlage<"+ id+"> not found");
		    throw new IllegalActionExcepiton("Existiert Nicht!- " + id);
		}
		
		Notlage notlage = optNotlage.get();
		LOGGER.info("Tries to mark Notlage<" + notlage.getNotlageId() +"> as solved.");
		if(notlage.isGeloest()==false)
		{
			notlage.loeseNotlage(bpID);
			notlageRepository.save(notlage);
		}

		Resource<Notlage> res =new Resource<>(notlage);
		res.add(linkTo(methodOn(NotlageController.class).getNotlage(notlage.getNotlageId())).withSelfRel().withType("GET"));
		if(notlage.isGeloest()==false)
			res.add(linkTo(methodOn(NotlageController.class).notlageBestaetigen(id,null)).withRel("lösen").withType("PUT"));
		if(notlage.isBestaetigt()==false)
			res.add(linkTo(methodOn(NotlageController.class).notlageLoesen(id,null)).withRel("bestätigen").withType("PUT"));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
        
    }
    
    @RequestMapping(path="/notlage/info", method=RequestMethod.GET)
    public ResponseEntity<Object> info() 
	{
		Resource<String> res =new Resource<>("Für ein Post bitte DVP id und String(Payload) angeben");
        return ResponseEntity.status(HttpStatus.OK).body(res);	  
    }
    
	@RequestMapping(path="/Notlage/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> deleteNotlage(@PathVariable long id) 
	{
		notlageRepository.deleteById(id);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
	
	@RequestMapping("/version")
    public String version() 
	{
        return "1";
    }
	
	 @ExceptionHandler
	  void handleIllegalArgumentException
	  (  IllegalArgumentException e, HttpServletResponse response) throws IOException 
	  	{  response.sendError(HttpStatus.BAD_REQUEST.value());  }
	
	



}
