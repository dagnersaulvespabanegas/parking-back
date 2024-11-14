package com.daniela.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daniela.Entity.Pago;
import com.daniela.Entity.Registro;

public interface PagoRepository extends MongoRepository<Pago, Long> {
    Pago findByRegistro(Registro registro);
    Boolean existsByRegistro(Registro registro);
}

