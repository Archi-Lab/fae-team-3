package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DVPRepository extends CrudRepository<DVP, Long> {

    List<DVP> findByName(String name);
    
    Optional<DVP> findById(Long id);
    
    Optional<DVP> findByRegisterId(Long id);
}
