package faeteam3.Bezugsperson.Bezugsperson.repositories;

import faeteam3.Bezugsperson.Bezugsperson.models.Kommunikationskanal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KommunikationskanalRepository extends CrudRepository<Kommunikationskanal, String> {
}


