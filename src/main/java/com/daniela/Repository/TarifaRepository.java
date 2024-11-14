package com.daniela.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daniela.Entity.Tarifa;

public interface TarifaRepository extends MongoRepository<Tarifa, Long> {
    Tarifa findByTipoVehiculo(String tipoVehiculo);
    Boolean existsByTipoVehiculo(String tipoVehiculo);
}

