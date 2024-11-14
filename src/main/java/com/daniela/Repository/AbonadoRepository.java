package com.daniela.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daniela.Entity.Abonado;

public interface AbonadoRepository extends MongoRepository<Abonado, Long> {
    Abonado findByCedula(String cedula);
    Boolean existsByCedula(String cedula);
}

