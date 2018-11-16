package faeteam3.Notlage;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DVPRepository extends CrudRepository<DVP, Long> {
    
    Optional<DVP> findById(Long id);
    
    Optional<DVP> findByRegisterId(Long id);
}