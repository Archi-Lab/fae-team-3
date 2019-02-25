package faeteam3.Notlage.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import faeteam3.Notlage.model.Notlage;

/**
* Repository für Notlagen
*/
@RepositoryRestResource(collectionResourceRel = "notlage", path = "notlage")
public interface NotlageRepository   extends CrudRepository<Notlage, Long> 
{
	
	Optional<Notlage> findById(Long id);
	
	List<Notlage> findByDvpInternDvpID(String internDvpID);

 //   List<Notlage> findBybehoben(boolean behoben);
 //   List<Notlage> findBydvpid(Long dvpid);

    

}
