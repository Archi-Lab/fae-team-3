package faeteam3.Bezugsperson.Bezugsperson.repositories;

import faeteam3.Bezugsperson.Bezugsperson.controller.BezugspersonController;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import faeteam3.Bezugsperson.Bezugsperson.models.Bezugsperson;

//@RepositoryRestResource(path = "Bezugsperson")
public interface BezugspersonRepository extends CrudRepository<Bezugsperson, Long> {

}
