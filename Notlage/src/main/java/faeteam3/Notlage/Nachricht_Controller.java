package faeteam3.Notlage;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


import org.springframework.beans.factory.annotation.Autowired;
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

import resource.NotlageAntowrt;

@RestController
public class Nachricht_Controller 
{
	

	@Autowired
	private Services_Klasse service_klasse;
	
	@RequestMapping("/version")
    public String version() 
	{
        return "1";
    }

	@PostMapping("/Notlage")
	public Notlage createNotlage(@RequestBody Nachricht na) {
		Notlage not = service_klasse.erstelle_neue_Notlage(na);
		return not;
	}
	
	@RequestMapping(path="/Notlage", method=RequestMethod.GET)
    public HttpEntity<NotlageAntowrt> greeting(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

		NotlageAntowrt greeting = new NotlageAntowrt("");
        greeting.add(linkTo(methodOn(Services_Klasse.class).erstelle_neue_Nachricht(null)).withSelfRel());

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

//	@GetMapping("/nachricht")
//	public List getAllTickets() {
//		return ticketService.findAll();
//	}

	@GetMapping("/nachricht/{id}")
	public Nachricht getTicket(@PathVariable long id) {
		return service_klasse.findNachricht(id);
	}

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
	 @ExceptionHandler
	  void handleIllegalArgumentException
	  (  IllegalArgumentException e, HttpServletResponse response) throws IOException 
	  	{  response.sendError(HttpStatus.BAD_REQUEST.value());  }
	

	 

}
