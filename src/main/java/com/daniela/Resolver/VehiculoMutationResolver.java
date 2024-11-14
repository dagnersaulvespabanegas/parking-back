package com.daniela.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.daniela.Entity.Vehiculo;
import com.daniela.Exception.EntityNotFoundException;
import com.daniela.Repository.VehiculoRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;


@Component
@RestController
public class VehiculoMutationResolver  implements GraphQLMutationResolver{

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @MutationMapping
    public Vehiculo createVehiculo(@Argument Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @MutationMapping
    public Vehiculo updateVehiculo(@Argument Long vehiculoId, @Argument Vehiculo updateRequest) {
        Vehiculo automovil = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new EntityNotFoundException("Automovil not found"));
        automovil.merge(updateRequest);
        return vehiculoRepository.save(automovil);
    }

    @MutationMapping
    public boolean deleteVehiculo(@Argument Long vehiculoId) {
        vehiculoRepository.deleteById(vehiculoId);
        return true;
    }
}
