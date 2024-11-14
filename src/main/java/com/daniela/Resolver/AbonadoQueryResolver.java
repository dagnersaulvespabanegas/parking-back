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
import com.daniela.Entity.Abonado;
import com.daniela.Entity.PageEntity.EntityPage;
import com.daniela.Entity.PageEntity.PageInfo;
import com.daniela.Repository.AbonadoRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@RestController
public class AbonadoQueryResolver implements GraphQLQueryResolver {
    
    @Autowired
    private AbonadoRepository abonadoRepository;

    @QueryMapping 
    public EntityPage<Abonado> getAbonados(@Argument int page, @Argument int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Abonado> entityPage = abonadoRepository.findAll(pageable);
        PageInfo pageInfo = new PageInfo(entityPage.getTotalPages(), entityPage.getTotalElements(), page, size);
        return new EntityPage<>(pageInfo, entityPage.getContent());
    }

    @QueryMapping 
    public Abonado getAbonado(@Argument Long abonadoId) {
        return abonadoRepository.findById(abonadoId)
                .orElse(null); // Aquí deberías manejar la excepción de forma adecuada
    }
    
    @QueryMapping 
    public List<Abonado> getAllAbonados() {
        return abonadoRepository.findAll();
    }
}
