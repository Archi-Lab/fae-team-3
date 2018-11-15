package faeteam3.Notlage.notlage.repository;

import faeteam3.Notlage.notlage.model.Notlage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "notlage", path = "notlage")
public interface NotlageRepository   extends CrudRepository<Notlage, Long> {

}
