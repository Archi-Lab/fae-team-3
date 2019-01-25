package faeteam3.Notlage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import faeteam3.Notlage.model.Notlage;

@RepositoryRestResource(collectionResourceRel = "notlage", path = "notlage")
public interface NotlageRepository   extends CrudRepository<Notlage, Long> 
{
	
	Optional<Notlage> findById(Long id);
	
	List<Notlage> findByDvpInternDvpID(Long internDvpID);

 //   List<Notlage> findBybehoben(boolean behoben);
 //   List<Notlage> findBydvpid(Long dvpid);

    

}
