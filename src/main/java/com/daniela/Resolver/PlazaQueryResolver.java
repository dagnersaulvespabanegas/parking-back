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
import com.daniela.Entity.PageEntity.EntityPage;
import com.daniela.Entity.PageEntity.PageInfo;
import com.daniela.Repository.PlazaRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@RestController
public class PlazaQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private PlazaRepository plazaRepository;

    @QueryMapping 
    public EntityPage<Plaza> getPlazas(@Argument int page, @Argument int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Plaza> entityPage = plazaRepository.findAll(pageable);
        PageInfo pageInfo = new PageInfo(entityPage.getTotalPages(), entityPage.getTotalElements(), page, size);
        return new EntityPage<Plaza>(pageInfo, entityPage.getContent());
    }

    @QueryMapping 
    public Plaza getPlaza(@Argument Long plazaId) {
        return plazaRepository.findById(plazaId).orElse(null); // Método que deberías definir en el repositorio
    }
    
    @QueryMapping 
    public List<Plaza> getAllPlazas() {
        return plazaRepository.findAll();
    }
}
