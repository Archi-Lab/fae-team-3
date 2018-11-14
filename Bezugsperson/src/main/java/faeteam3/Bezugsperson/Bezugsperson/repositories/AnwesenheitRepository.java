package faeteam3.Bezugsperson.Bezugsperson.repositories;

import faeteam3.Bezugsperson.Bezugsperson.models.Anwesenheit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnwesenheitRepository extends CrudRepository<Anwesenheit, Long> {
}
