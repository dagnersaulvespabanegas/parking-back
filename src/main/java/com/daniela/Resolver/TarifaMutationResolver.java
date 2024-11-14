package com.daniela.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.daniela.Entity.Tarifa;
import com.daniela.Exception.EntityNotFoundException;
import com.daniela.Repository.TarifaRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@RestController
public class TarifaMutationResolver implements GraphQLMutationResolver{

    @Autowired
    private TarifaRepository tarifaRepository;

    @MutationMapping
    public Tarifa createTarifa(@Argument Tarifa tarifa) {
        return tarifaRepository.save(tarifa);
    }

    @MutationMapping
    public Tarifa updateTarifa(@Argument Long tarifaId, @Argument Tarifa updateRequest) {
        Tarifa tarifa = tarifaRepository.findById(tarifaId)
                .orElseThrow(() -> new EntityNotFoundException("Tarifa not found"));
        tarifa.merge(updateRequest);
        return tarifaRepository.save(tarifa);
    }

    @MutationMapping
    public boolean deleteTarifa(@Argument Long tarifaId) {
        tarifaRepository.deleteById(tarifaId);
        return true;
    }
}
