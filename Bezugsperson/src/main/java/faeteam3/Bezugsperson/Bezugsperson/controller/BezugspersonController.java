package faeteam3.Bezugsperson.Bezugsperson.controller;

import faeteam3.Bezugsperson.Bezugsperson.models.Bezugsperson;
import faeteam3.Bezugsperson.Bezugsperson.repositories.BezugspersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

    @PostMapping(path = "/Bezugsperson")
    public ResponseEntity<?> postBezugsperson(@RequestBody String bp_id_ext) {

        Bezugsperson bezugsperson = bezugspersonRepository.save(new Bezugsperson(bp_id_ext));

        List<Bezugsperson> bp_list = new ArrayList<Bezugsperson>();
        bp_list.add(bezugsperson);

        Resources<Bezugsperson> resources = new Resources<>(bp_list);

        return ResponseEntity.ok(resources);

    }

    @RequestMapping(path = "/{bp_id}", method = GET)
    public ResponseEntity<?> getBezugsperson(@PathVariable Long bp_id) {

        return ResponseEntity.ok(bp_id);

    }

    @GetMapping(path = "/test")
    public ResponseEntity<?> test() {

        return ResponseEntity.ok("Hallo Welt!");

    }



}
