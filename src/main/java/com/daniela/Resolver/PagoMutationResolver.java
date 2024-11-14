package com.daniela.Resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.daniela.Entity.Pago;
import com.daniela.Exception.EntityNotFoundException;
import com.daniela.Repository.PagoRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;



@Component
@RestController
public class PagoMutationResolver implements GraphQLMutationResolver {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PagoRepository pagoRepository;

    @MutationMapping
    public Pago createPago(@Argument Pago pago) {
        LOGGER.info("Creating new Pago: {}", pago);
        return pagoRepository.save(pago);
    }

    @MutationMapping
    public Pago updatePago(@Argument Long pagoId, @Argument Pago updateRequest) {
        LOGGER.info("Updating Pago with id: {}", pagoId);
        Pago pago = pagoRepository.findById(pagoId)
                .orElseThrow(() -> new EntityNotFoundException("Pago not found with id: " + pagoId));
        
        // Merge updateRequest into the existing pago
        pago.merge(updateRequest);
        return pagoRepository.save(pago);
    }

    @MutationMapping
    public boolean deletePago(@Argument Long pagoId) {
        LOGGER.info("Deleting Pago with id: {}", pagoId);
        if (!pagoRepository.existsById(pagoId)) {
            throw new EntityNotFoundException("Pago not found with id: " + pagoId);
        }
        pagoRepository.deleteById(pagoId);
        return true;
    }
}
