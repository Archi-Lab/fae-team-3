package faeteam3.Notlage.notlage.controller;

import faeteam3.Notlage.notlage.repository.NotlageRepository;
import faeteam3.Notlage.notlage.model.Notlage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController()
public class NotlageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotlageController.class);
    private final NotlageRepository notlageRepository;

    @Autowired
    public NotlageController(NotlageRepository notlageRepository) {
        this.notlageRepository = notlageRepository;
    }

    @GetMapping(path = "/notlage")
    public   ResponseEntity<?> getNotlagen(){
        final Iterable<Notlage> notlageList = this.notlageRepository.findAll();
        final List<Resource> resourceLst = new ArrayList<>();
        notlageList.forEach(n->
        {
            Resource<Notlage> resource = buildNotlageResource(n);
            resourceLst.add(resource);
        });
        Resources<Resource<Notlage>> resources = new Resources(resourceLst);
        resources.add(linkTo(methodOn(NotlageController.class).getNotlagen()).withSelfRel());

        LOGGER.info("RETURN ALL NOTLAGEN!");
        return  ResponseEntity.ok(resources);
    }

    @GetMapping(path = "/notlage/{id}")
    public   ResponseEntity<?> getNotlagen(@PathVariable Long id){
      final Optional<Notlage> optNotlage = this.notlageRepository.findById(id);

      LOGGER.info("RETURN NOTLAGE" + id);

      if(optNotlage.isPresent())
      {
          LOGGER.info("Notlage<"+ id+"> found");
          Notlage notlage = optNotlage.get();
          Resource<Notlage> resource = buildNotlageResource(notlage);
          return  ResponseEntity.ok(resource);
      }
      LOGGER.info("Notlage<"+ id+"> not found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/notlage")
    ResponseEntity<?> addNotlage(@RequestBody Notlage notlage) {
        LOGGER.info("POST Notlage");
        notlageRepository.save(notlage);

        Resource<Notlage>  resource = new Resource<>(notlage);
        resource.add(linkTo(methodOn(NotlageController.class).getNotlagen(notlage.getNotlageId())).withSelfRel());
        resource.add(linkTo(methodOn(NotlageController.class).getNotlageLoesen(notlage.getNotlageId())).withRel("loesen"));

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/notlage/{id}/bestaetigen")
    ResponseEntity<?> getNotlageBestaetigen(@PathVariable Long id) {
        LOGGER.info("GET Notlage bestaetigen");
        LOGGER.info("RETURN NOTLAGE" + id);
        final Optional<Notlage> optNotlage = this.notlageRepository.findById(id);

        if(optNotlage.isPresent())
        {
            LOGGER.info("Notlage<"+ id+"> found");
            Resource<Notlage> resource = buildNotlageResource(optNotlage.get());
            return ResponseEntity.ok(resource);
        }
        LOGGER.info("Notlage<"+ id+"> not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/notlage/{id}/loesen")
    ResponseEntity<?> getNotlageLoesen(@PathVariable Long id) {

        LOGGER.info("GET Notlage loesen");
        final Optional<Notlage> optNotlage = this.notlageRepository.findById(id);
        if(optNotlage.isPresent())
        {
            LOGGER.info("Notlage<"+ id+"> found");
            LOGGER.info("RETURN NOTLAGE" + id);
            Resource<Notlage> resource = buildNotlageResource(optNotlage.get());
            return ResponseEntity.ok(resource);
        }
        LOGGER.info("Notlage<"+ id+"> not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PutMapping("/notlage/{id}/bestaetigen")
//    ResponseEntity<?> setNotlageBestaetigen(@PathVariable Long id, @RequestBody Bezugsperson bezugsperson) {
//
//        final Optional<Notlage> optNotlage = this.notlageRepository.findById(id);
//
//        LOGGER.info("PUT: confirm Notlage");
//
//        if(optNotlage.isPresent())
//        {
//            Notlage notlage = optNotlage.get();
//
//            if(notlage.isOffen())
//            {
//                LOGGER.info("Tries to mark Notlage<" + notlage.getNotlageId() +"> as confirmed.");
//                notlage.markBestaetigt(bezugsperson);
//                notlageRepository.save(notlage);
//                Resource<Notlage> resource = buildNotlageResource(notlage);
//                return ResponseEntity.ok(resource);
//            }
//            LOGGER.info("Notlage isnt in state opened and cant be confirmed.");
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//        LOGGER.info("Notlage<"+ id+"> not found");
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PutMapping("/notlage/{id}/bestaetigen")
    ResponseEntity<?> setNotlageBestaetigen(@PathVariable Long id) {

        final Optional<Notlage> optNotlage = this.notlageRepository.findById(id);

        LOGGER.info("PUT: confirm Notlage");

        if(optNotlage.isPresent())
        {
            Notlage notlage = optNotlage.get();

            if(notlage.isOffen())
            {
                LOGGER.info("Tries to mark Notlage<" + notlage.getNotlageId() +"> as confirmed.");
                notlage.markBestaetigt();
                notlageRepository.save(notlage);
                Resource<Notlage> resource = buildNotlageResource(notlage);
                return ResponseEntity.ok(resource);
            }
            LOGGER.info("Notlage isnt in state opened and cant be confirmed.");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        LOGGER.info("Notlage<"+ id+"> not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/notlage/{id}/loesen")
    ResponseEntity<?> setNotlageLoesen(@PathVariable Long id) {
        LOGGER.info("PUT: solve Notlage");

        final Optional<Notlage> optNotlage = this.notlageRepository.findById(id);

        if(optNotlage.isPresent())
        {
            Notlage notlage = optNotlage.get();

            if(notlage.isBestaetigt())
            {
                LOGGER.info("Tries to mark Notlage<" + notlage.getNotlageId() +"> as solved.");
                notlage.markGeloest();
                notlageRepository.save(notlage);
                Resource<Notlage> resource = buildNotlageResource(notlage);
                return ResponseEntity.ok(resource);
            }
            LOGGER.info("Notlage isnt in state confirmed and cant be solved.");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        LOGGER.info("Notlage<"+ id+"> not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    @PutMapping("/notlage/{id}/loesen")
//    ResponseEntity<?> setNotlageLoesen(@PathVariable Long id, @RequestBody  Bezugsperson bezugsperson) {
//        LOGGER.info("PUT: solve Notlage");
//
//        final Optional<Notlage> optNotlage = this.notlageRepository.findById(id);
//
//        if(optNotlage.isPresent())
//        {
//            Notlage notlage = optNotlage.get();
//
//            if(notlage.isBestaetigt())
//            {
//                LOGGER.info("Tries to mark Notlage<" + notlage.getNotlageId() +"> as solved.");
//                notlage.markGeloest(bezugsperson);
//                notlageRepository.save(notlage);
//                Resource<Notlage> resource = buildNotlageResource(notlage);
//                return ResponseEntity.ok(resource);
//            }
//            LOGGER.info("Notlage isnt in state confirmed and cant be solved.");
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//        LOGGER.info("Notlage<"+ id+"> not found");
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    Resource<Notlage> buildNotlageResource(@NotNull Notlage notlage)
    {
        final Long id = notlage.getNotlageId();
        Resource resource = new Resource<>(notlage);
        resource.add(linkTo(methodOn(NotlageController.class).getNotlagen(id)).withSelfRel());

        if(notlage.isBestaetigt() && notlage.isGeloest())
        {
            return  resource;
        }

        if(!notlage.isBestaetigt())
        {
            resource.add(linkTo(methodOn(NotlageController.class).getNotlageBestaetigen(id)).withRel("bestaetigen"));
        }
        else
        {
            resource.add(linkTo(methodOn(NotlageController.class).getNotlageLoesen(id)).withRel("loesen"));
        }
        return resource;
    }
}
