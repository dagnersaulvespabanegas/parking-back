package com.daniela.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.daniela.Entity.Plan;
import com.daniela.Entity.PageEntity.EntityPage;
import com.daniela.Entity.PageEntity.PageInfo;
import com.daniela.Repository.PlanRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@RestController
@Secured("ROLE_ADMINISTRADOR")
public class PlanQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private PlanRepository planRepository;

    @QueryMapping 
    public EntityPage<Plan> getPlanes(@Argument int page, @Argument int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Plan> entityPage = planRepository.findAll(pageable);
        PageInfo pageInfo = new PageInfo(entityPage.getTotalPages(), entityPage.getTotalElements(), page, size);
        return new EntityPage<>(pageInfo, entityPage.getContent());
    }

    @QueryMapping 
    public Plan getPlan(@Argument Long planId) {
        return planRepository.findById(planId).orElse(null);
    }
}
