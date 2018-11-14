package faeteam3.Notlage;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import exceptions.IllegalActionExcepiton;


@RestController
public class Main_Controller 
{
	
//	 Notlage Post     dvp nachricht   self
//	 Notlage id get   self notlage bestätigen
//	 Notlage id losen put  bp  slef
//	 notlage id bestätigen put  bp self lösen
	
	@Autowired
	private Services_Klasse service_klasse;
	
	  
	@Autowired
	private NotlageRepository notlage_repository;
		
		
	
	@RequestMapping(path="/Notlage", method=RequestMethod.POST)
    public ResponseEntity<Object> neueNotlage(@RequestBody Nachricht nachricht) 
	{
		 if (nachricht.getDvpid()==null)
		      throw new IllegalActionExcepiton("Illegale DVP ID-" + nachricht.getDvpid());

		Notlage not = notlage_repository.save(new Notlage(nachricht));
		
		Resource<Notlage> res =new Resource<>(not);
		res.add(linkTo(methodOn(Main_Controller.class).notlageGet(not.get_id())).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(res);	
        
    }
	
	@RequestMapping(path="/Notlage/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> deleteNotlage(@PathVariable long id) 
	{
		notlage_repository.deleteById(id);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
	
	@RequestMapping(path="/Notlage/{id}", method=RequestMethod.GET)
    public ResponseEntity<Object> notlageGet(@PathVariable long id) 
	{
		Optional<Notlage> not = notlage_repository.findById(id);
		
		if (!not.isPresent())
		      throw new IllegalActionExcepiton("Existiert Nicht!- " + id);
		Notlage real_not = not.get();
		
		Resource<Notlage> res =new Resource<>(real_not);
		res.add(linkTo(methodOn(Main_Controller.class).notlageGet(real_not.get_id())).withSelfRel());
		if(real_not.getGelöstt()==false)
			res.add(linkTo(methodOn(Main_Controller.class).notlageBestätigen(id,null)).withRel("lösen"));
		if(real_not.getBestätigt()==false)
			res.add(linkTo(methodOn(Main_Controller.class).notlageLösen(id,null)).withRel("bestätigen"));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
    }
	
	@RequestMapping(path="/Notlage/{id}/bestätigen", method=RequestMethod.PUT)
    public ResponseEntity<Object> notlageBestätigen(@PathVariable long id ,@RequestBody ReturnBP  uuid) {
		

		Optional<Notlage> not = notlage_repository.findById( id);
		
		if (!not.isPresent())
		      throw new IllegalActionExcepiton("Existiert Nicht!- " + id);
		
		Notlage real_not = not.get();
		if(real_not.getBestätigt()==false)
		{
			real_not.setBestätigt(uuid.getUuid());
		}
		notlage_repository.save(real_not);
		
		Resource<Notlage> res =new Resource<>(real_not);
		res.add(linkTo(methodOn(Main_Controller.class).notlageGet(real_not.get_id())).withSelfRel());
		if(real_not.getGelöstt()==false)
		{
			res.add(linkTo(methodOn(Main_Controller.class).notlageBestätigen(id,null)).withRel("lösen"));
		}
		if(real_not.getBestätigt()==false)
		{
			res.add(linkTo(methodOn(Main_Controller.class).notlageLösen(id,null)).withRel("bestätigen"));
		}

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
    }
	
	@RequestMapping(path="/Notlage/{id}/lösen", method=RequestMethod.PUT)
    public ResponseEntity<Object> notlageLösen(@PathVariable long id , @RequestBody ReturnBP  uuid) {

		Optional<Notlage> not = notlage_repository.findById(id);
		
		if (!not.isPresent())
		      throw new IllegalActionExcepiton("Existiert Nicht!- " + id);
		
		Notlage real_not = not.get();
		if(real_not.getGelöstt()==false)
		{
			real_not.setGelöst(uuid.getUuid());
		}

		notlage_repository.save(real_not);

		Resource<Notlage> res =new Resource<>(real_not);
		res.add(linkTo(methodOn(Main_Controller.class).notlageGet(real_not.get_id())).withSelfRel());
		if(real_not.getGelöstt()==false)
		{
			res.add(linkTo(methodOn(Main_Controller.class).notlageBestätigen(id,null)).withRel("lösen"));
		}
		if(real_not.getBestätigt()==false)
		{
			res.add(linkTo(methodOn(Main_Controller.class).notlageLösen(id,null)).withRel("bestätigen"));
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
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
	


//	@PostMapping("/Notlage")
//	public Notlage createNotlage(@RequestBody Nachricht na) {
//		Notlage not = service_klasse.erstelle_neue_Notlage(na);
//		return not;
//	}
	
//	@RequestMapping(path="/Notlage", method=RequestMethod.GET)
//    public HttpEntity<NotlageAntowrt> greeting(
//            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
//
//		NotlageAntowrt greeting = new NotlageAntowrt("");
//        greeting.add(linkTo(methodOn(Services_Klasse.class).erstelle_neue_Nachricht(null)).withSelfRel());
//
//        return new ResponseEntity<>(greeting, HttpStatus.OK);
//    }

//	@GetMapping("/nachricht")
//	public List getAllTickets() {
//		return ticketService.findAll();
//	}



//	@PutMapping("/nachricht/{id}")
//	public Ticket changeTicket(@PathVariable long id, @RequestBody Ticket ticket) {
//		return ticketService.updateTicket(id, ticket);
//	}
//
//	@DeleteMapping("/nachricht/{id}")
//	public String deleteTicket(@PathVariable long id) {
//		ticketService.deleteById(id);
//		return String.format("Ticket id #%d successfully deleted", id);
//	}

	// --------------------------------------------
	// CRUD OPERATIONS FOR CHILD RECORDS (COMMENTS)

//	@PostMapping("/nachricht/{id}/comments")
//	public Ticket createComment(@PathVariable long id, @RequestBody Comment comment) {
//		return ticketService.createComment(id, comment);
//	}
//
//	@GetMapping("/nachricht/{id}/comments")
//	public List getAllComments(@PathVariable long id) {
//		return ticketService.findAllComments(id);
//	}
//
//	@GetMapping("/nachricht/comments/{id}")
//	public Comment getComment(@PathVariable long id) {
//		return ticketService.findComment(id);
//	}
//
//	@PutMapping("/nachricht/comments/{id}")
//	public Comment changeComment(@PathVariable long id, @RequestBody Comment comment) {
//		return ticketService.updateComment(id, comment);
//	}
//
//	@DeleteMapping("/nachricht/comments/{id}")
//	public String deleteComment(@PathVariable long id) {
//		ticketService.deleteCommentById(id);
//		return String.format("Comment id %d successfully deleted", id);
//	}
//	

	 

}
