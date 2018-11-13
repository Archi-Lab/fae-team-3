package faeteam3.Bezugsperson.Bezugsperson.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import faeteam3.Bezugsperson.Bezugsperson.models.BenachrichtigungsVorgang;

public interface BenachrichtigungsVorgangRepository extends CrudRepository<BenachrichtigungsVorgang, Long> {
}
