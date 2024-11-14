package com.daniela.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.daniela.Entity.DetallePlan;
import com.daniela.Exception.EntityNotFoundException;
import com.daniela.Repository.DetallePlanRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@RestController
public class DetallePlanMutationResolver  implements GraphQLMutationResolver{

    @Autowired
    private DetallePlanRepository detallePlanRepository;

    @MutationMapping
    public DetallePlan createDetalleDePlanDePagos(@Argument DetallePlan detalle) {
        return detallePlanRepository.save(detalle);
    }

    @MutationMapping
    public DetallePlan updateDetalleDePlanDePagos(@Argument DetallePlan detalleUpdate) {
        DetallePlan detalle = detallePlanRepository.findById(detalleUpdate.getDetalleId())
                .orElseThrow(() -> new EntityNotFoundException("Detalle not found"));
        detalle.merge(detalleUpdate);
        return detallePlanRepository.save(detalle);
    }

    @MutationMapping
    public boolean deleteDetalleDePlanDePagos(@Argument Long detalleId) {
        detallePlanRepository.deleteById(detalleId);
        return true;
    }
}
