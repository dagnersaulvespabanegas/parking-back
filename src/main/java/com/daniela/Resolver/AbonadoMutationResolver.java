package com.daniela.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.daniela.Entity.Abonado;
import com.daniela.Exception.EntityNotFoundException;
import com.daniela.Repository.AbonadoRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@RestController
public class AbonadoMutationResolver implements GraphQLMutationResolver{

    @Autowired
    private AbonadoRepository abonadoRepository;

    @MutationMapping
    public Abonado createAbonado(@Argument Abonado abonado) {
        return abonadoRepository.save(abonado);
    }

    @MutationMapping
    public Abonado updateAbonado(@Argument Abonado inputAbonado) {
        Abonado abonado = abonadoRepository.findById(inputAbonado.getAbonadoId())
                .orElseThrow(() -> new EntityNotFoundException("Abonado not found"));
        abonado.merge(inputAbonado);
        return abonadoRepository.save(abonado);
    }

    @MutationMapping
    public boolean deleteAbonado(@Argument Long abonadoId) {
        abonadoRepository.deleteById(abonadoId);
        return true;
    }
}
