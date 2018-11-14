package faeteam3.Bezugsperson.Bezugsperson.controller;

import faeteam3.Bezugsperson.Bezugsperson.models.Anwesenheit;
import faeteam3.Bezugsperson.Bezugsperson.models.Bezugsperson;
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

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/Bezugsperson")
@ExposesResourceFor(Bezugsperson.class)
public class BezugspersonController {

    private final BezugspersonRepository bezugspersonRepository;

    @Autowired
    public BezugspersonController(
            BezugspersonRepository bezugspersonRepository) {
        this.bezugspersonRepository = bezugspersonRepository;
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
                                             @ModelAttribute("von") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") Calendar von,
                                             @ModelAttribute("bis") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") Calendar bis) {

        Bezugsperson bezugsperson = bezugspersonRepository.findById(bp_id).get();

        bezugsperson.addAnwesenheit(new Anwesenheit(von, bis));

        return ResponseEntity.ok(von);

    }

    @RequestMapping(path = "/{bp_id}/Anwesenheit/{anw_id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAnwesenheit(@PathVariable Long bp_id, @PathVariable Long anw_id) {
        return ResponseEntity.ok().build();
    }

}
