package com.daniela.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daniela.Entity.Plaza;
import com.daniela.Entity.Registro;

public interface RegistroRepository extends MongoRepository<Registro, Long> {
    Registro findByPlaca(String placa);
    Registro findByPlaza(Plaza plaza);
    Boolean existsByPlaca(String placa);
}

