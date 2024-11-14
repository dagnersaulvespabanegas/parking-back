package com.daniela.Resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.daniela.Entity.User;
import com.daniela.Entity.PageEntity.EntityPage;
import com.daniela.Entity.PageEntity.PageInfo;
import com.daniela.Repository.UserRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component @RestController
@Secured("ROLE_ADMINISTRADOR")
public class UserQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private UserRepository userRepository;
  

    @QueryMapping 
    public EntityPage<User> getUsers(@Argument int page,@Argument int size) {
    	 Pageable pageable = PageRequest.of(page, size);
    	 Page<User> entityPage = userRepository.findAll(pageable);
    	 PageInfo pageInfo = new PageInfo(entityPage.getTotalPages(), entityPage.getTotalElements(), page, size);
    	 return new EntityPage<User>(pageInfo, entityPage.getContent());
    }
    
    @QueryMapping 
    public User getUser(@Argument String email) {
        return userRepository.findByEmail(email);
 
    }
    
    @QueryMapping 
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}