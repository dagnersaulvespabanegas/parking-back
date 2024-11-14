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
import com.daniela.Entity.Tarifa;
import com.daniela.Entity.PageEntity.EntityPage;
import com.daniela.Entity.PageEntity.PageInfo;
import com.daniela.Repository.TarifaRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@RestController
public class TarifaQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private TarifaRepository tarifaRepository;

    @QueryMapping 
    public EntityPage<Tarifa> getTarifas(@Argument int page, @Argument int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tarifa> entityPage = tarifaRepository.findAll(pageable);
        PageInfo pageInfo = new PageInfo(entityPage.getTotalPages(), entityPage.getTotalElements(), page, size);
        return new EntityPage<>(pageInfo, entityPage.getContent());
    }

    @QueryMapping 
    public Tarifa getTarifa(@Argument String tipoVehiculo) {
        return tarifaRepository.findByTipoVehiculo(tipoVehiculo); // Método que deberías definir en el repositorio
    }
    
    @QueryMapping 
    public List<Tarifa> getAllTarifas() {
        return tarifaRepository.findAll();
    }
}
