package com.daniela.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.daniela.Entity.SmartContract;

public interface SmartContractRepository extends MongoRepository<SmartContract, Long> {


}
