package faeteam3.Notlage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotlageRepository   extends CrudRepository<Notlage, Long> {

    List<Notlage> findBybehoben(boolean behoben);
    List<Notlage> findBydvpid(Long dvpid);

    

}
