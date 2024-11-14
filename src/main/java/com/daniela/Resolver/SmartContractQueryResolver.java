package com.daniela.Resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.daniela.Entity.Plaza;
import com.daniela.Entity.SmartContract;
import com.daniela.Entity.PageEntity.EntityPage;
import com.daniela.Entity.PageEntity.PageInfo;
import com.daniela.Repository.PlazaRepository;
import com.daniela.Repository.SmartContractRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@RestController
public class SmartContractQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private SmartContractRepository smartContractRepository;

    @QueryMapping 
    public EntityPage<SmartContract> getContracts(@Argument int page, @Argument int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SmartContract> entityPage = smartContractRepository.findAll(pageable);
        PageInfo pageInfo = new PageInfo(entityPage.getTotalPages(), entityPage.getTotalElements(), page, size);
        return new EntityPage<SmartContract>(pageInfo, entityPage.getContent());
    }

 

}
