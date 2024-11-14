package com.daniela.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.daniela.Entity.SmartContract;
import com.daniela.Repository.SmartContractRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@RestController
public class SmartContractMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private SmartContractRepository smartContractRepository;

    @MutationMapping
    public SmartContract createSmartContract(@Argument SmartContract contract) {
        return smartContractRepository.save(contract);
    }


}
