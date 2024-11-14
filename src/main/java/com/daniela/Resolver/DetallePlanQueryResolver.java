package com.daniela.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.daniela.Entity.DetallePlan;
import com.daniela.Entity.PageEntity.EntityPage;
import com.daniela.Entity.PageEntity.PageInfo;
import com.daniela.Exception.EntityNotFoundException;
import com.daniela.Repository.DetallePlanRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component @RestController
public class DetallePlanQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private DetallePlanRepository detallePlanRepository;

    @QueryMapping
    public EntityPage<DetallePlan> getDetallesDePlanDePagos(@Argument int page, @Argument int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DetallePlan> entityPage = detallePlanRepository.findAll(pageable);
        PageInfo pageInfo = new PageInfo(entityPage.getTotalPages(), entityPage.getTotalElements(), page, size);
        return new EntityPage<>(pageInfo, entityPage.getContent());
    }

    @QueryMapping
    public DetallePlan getDetalleDePlanDePagos(@Argument Long detalleId) {
        return detallePlanRepository.findById(detalleId)
                .orElseThrow(() -> new EntityNotFoundException("Detalle not found with id: " + detalleId));
    }
}
