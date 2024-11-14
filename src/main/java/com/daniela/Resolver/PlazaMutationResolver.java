package com.daniela.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.daniela.Entity.Plaza;
import com.daniela.Exception.EntityNotFoundException;
import com.daniela.Repository.PlazaRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@RestController
public class PlazaMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private PlazaRepository plazaRepository;

    @MutationMapping
    public Plaza createPlaza(@Argument Plaza plaza) {
        return plazaRepository.save(plaza);
    }

    @MutationMapping
    public Plaza updatePlaza(@Argument Plaza updatePlaza) {
        Plaza plaza = plazaRepository.findById(updatePlaza.getPlazaId())
                .orElseThrow(() -> new EntityNotFoundException("Plaza not found"));
        plaza.merge(updatePlaza);
        return plazaRepository.save(plaza);
    }

    @MutationMapping
    public boolean deletePlaza(@Argument Long plazaId) {
        plazaRepository.deleteById(plazaId);
        return true;
    }
}
