package com.daniela.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.daniela.Entity.DetallePlan;

public interface DetallePlanRepository extends MongoRepository<DetallePlan, Long> {
    DetallePlan findByFechaPago(String fechaPago);
}
