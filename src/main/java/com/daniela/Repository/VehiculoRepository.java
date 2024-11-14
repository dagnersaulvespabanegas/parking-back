package com.daniela.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daniela.Entity.Vehiculo;

public interface VehiculoRepository extends MongoRepository<Vehiculo, Long> {
    Vehiculo findByPlaca(String placa);
    Boolean existsByPlaca(String placa);
}

