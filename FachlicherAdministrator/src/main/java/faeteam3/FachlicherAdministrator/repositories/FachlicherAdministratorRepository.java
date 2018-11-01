package faeteam3.FachlicherAdministrator.repositories;

import faeteam3.FachlicherAdministrator.models.FachlicherAdministrator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FachlicherAdministratorRepository extends CrudRepository<FachlicherAdministrator, Long> {

}
