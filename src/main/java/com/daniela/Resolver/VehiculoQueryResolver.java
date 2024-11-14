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
import com.daniela.Entity.Vehiculo; 
import com.daniela.Entity.PageEntity.EntityPage;
import com.daniela.Entity.PageEntity.PageInfo;
import com.daniela.Repository.VehiculoRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@RestController
public class VehiculoQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @QueryMapping 
    public EntityPage<Vehiculo> getVehiculos(@Argument int page, @Argument int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Vehiculo> entityPage = vehiculoRepository.findAll(pageable);
        PageInfo pageInfo = new PageInfo(entityPage.getTotalPages(), entityPage.getTotalElements(), page, size);
        return new EntityPage<>(pageInfo, entityPage.getContent());
    }

    @QueryMapping 
    public Vehiculo getVehiculo(@Argument String placa) {
        return vehiculoRepository.findByPlaca(placa); // Método que deberías definir en el repositorio
    }
    
    @QueryMapping 
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }
}
