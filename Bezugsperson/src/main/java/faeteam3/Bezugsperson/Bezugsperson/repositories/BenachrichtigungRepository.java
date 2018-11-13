package faeteam3.Bezugsperson.Bezugsperson.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import faeteam3.Bezugsperson.Bezugsperson.models.Benachrichtigung;

@Repository
public interface BenachrichtigungRepository extends CrudRepository<Benachrichtigung, Long> {
}
