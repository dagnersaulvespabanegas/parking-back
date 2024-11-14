package com.daniela.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.daniela.Entity.Plan;
import com.daniela.Exception.EntityNotFoundException;
import com.daniela.Repository.PlanRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component @RestController
public class PlanMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private PlanRepository planRepository;

    @MutationMapping
    public Plan createPlan(@Argument Plan plan) {
        return planRepository.save(plan);
    }

    @MutationMapping
    public Plan updatePlan( @Argument Plan updatePlan) {
        Plan plan = planRepository.findById(updatePlan.getPlanId())
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));
        plan.merge(updatePlan);
        return planRepository.save(plan);
    }

    @MutationMapping
    public boolean deletePlan(@Argument Long planId) {
        planRepository.deleteById(planId);
        return true;
    }
}
