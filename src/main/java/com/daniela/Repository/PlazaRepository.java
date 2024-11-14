package com.daniela.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daniela.Entity.Plaza;
import com.daniela.Entity.Plaza.Status;

public interface PlazaRepository extends MongoRepository<Plaza, Long> {
    Plaza findByEstado(Status estado);
    Boolean existsByEstado(Status estado);
    Plaza findFirstByEstado(Status estado);

}
