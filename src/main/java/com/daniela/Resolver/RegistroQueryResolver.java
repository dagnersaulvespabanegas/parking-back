package com.daniela.Resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.daniela.Entity.Registro;
import com.daniela.Entity.PageEntity.EntityPage;
import com.daniela.Entity.PageEntity.PageInfo;
import com.daniela.Repository.RegistroRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
@RestController
@Secured("ROLE_ADMINISTRADOR")
public class RegistroQueryResolver implements GraphQLQueryResolver {
    @Autowired
    private RegistroRepository registroRepository;

    @QueryMapping 
    public EntityPage<Registro> getRegistros(@Argument int page, @Argument int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Registro> entityPage = registroRepository.findAll(pageable);
        PageInfo pageInfo = new PageInfo(entityPage.getTotalPages(), entityPage.getTotalElements(), page, size);
        return new EntityPage<>(pageInfo, entityPage.getContent());
    }

    @QueryMapping 
    public Registro getRegistro(@Argument Long registroId) {
        return registroRepository.findById(registroId).orElse(null); // Método que deberías definir en el repositorio
    }
    
    @QueryMapping 
    public Registro getRegistroByPlaca(@Argument String placa) {
        return registroRepository.findByPlaca(placa); // Método que deberías definir en el repositorio
    }
    
    @QueryMapping 
    public List<Registro> getAllRegistros() {
        return registroRepository.findAll();
    }
}
