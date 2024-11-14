package com.daniela.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.daniela.Entity.Plan;

public interface PlanRepository extends MongoRepository<Plan, Long> {
    Plan findByTipo(String tipo);
    Boolean existsByTipo(String tipo);
}

