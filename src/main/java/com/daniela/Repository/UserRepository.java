package com.daniela.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.daniela.Entity.User;


public interface UserRepository extends MongoRepository<User, Long> {
	User  findByCedula(String cedula);
	User findByEmail(String email);
    Boolean existsByEmail(String email);
}
