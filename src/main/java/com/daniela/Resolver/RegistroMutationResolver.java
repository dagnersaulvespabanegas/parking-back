package com.daniela.Resolver;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import com.daniela.Entity.Plaza;
import com.daniela.Entity.Plaza.Status;
import com.daniela.Entity.Registro;
import com.daniela.Entity.Registro.Tipo_Registro;
import com.daniela.Entity.Vehiculo;
import com.daniela.Exception.EntityNotFoundException;
import com.daniela.Repository.PlazaRepository;
import com.daniela.Repository.RegistroRepository;
import com.daniela.Repository.VehiculoRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@RestController
public class RegistroMutationResolver  implements GraphQLMutationResolver{

    @Autowired
    private RegistroRepository registroRepository;
    @Autowired
    private PlazaRepository plazaRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @MutationMapping
    public Registro createRegistro(@Argument Registro registro) {
        // Buscar el vehículo por su placa
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(registro.getPlaca());

        // Si el tipo de registro es ENTRADA
        if (registro.getTipo() == Tipo_Registro.ENTRADA) {
            
            // Obtener una plaza disponible
            Plaza plaza = plazaRepository.findFirstByEstado(Status.DISPONIBLE);
            
            if (plaza == null) {
                throw new RuntimeException("No hay plazas disponibles.");
            }

            if (vehiculo == null) {
                // Si no existe un vehículo con esa placa, lo creamos
                vehiculo = new Vehiculo();
                vehiculo.setPlaca(registro.getPlaca());
                vehiculo.setEstado(Vehiculo.Status.ADENTRO);
            } else {
                // Si el vehículo ya existe, solo actualizamos su estado
                vehiculo.setEstado(Vehiculo.Status.ADENTRO);
            }
            
            // Actualizamos el estado de la plaza
            plaza.setEstado(Status.OCUPADA);

            // Guardamos el vehículo y la plaza
            vehiculoRepository.save(vehiculo);
            plazaRepository.save(plaza);

            // Establecemos la plaza en el registro
            registro.setPlaza(plaza);

            return registroRepository.save(registro);
            
        } else {
            // Para la salida, obtenemos la plaza del registro
            if (registro.getPlaza() == null) {
                throw new RuntimeException("El registro no tiene una plaza asociada para la salida.");
            }
            
            Optional<Plaza> plaza = plazaRepository.findById(registro.getPlaza().getPlazaId());
            
            if (plaza.isPresent()) {
                // Actualizamos el estado del vehículo a AFUERA
                vehiculo.setEstado(Vehiculo.Status.AFUERA);
                vehiculoRepository.save(vehiculo);

                // Marcamos la plaza como DISPONIBLE
                plaza.get().setEstado(Status.DISPONIBLE);
                plazaRepository.save(plaza.get());
            } else {
                throw new RuntimeException("La plaza no existe para esta salida.");
            }
        }

        return registroRepository.save(registro);
    }







    @MutationMapping
    public Registro updateRegistro(@Argument Long registroId, @Argument Registro updateRequest) {
        Registro registro = registroRepository.findById(registroId)
                .orElseThrow(() -> new EntityNotFoundException("Registro not found"));
        registro.merge(updateRequest);
        return registroRepository.save(registro);
    }

    @MutationMapping
    public boolean deleteRegistro(@Argument Long registroId) {
        registroRepository.deleteById(registroId);
        return true;
    }
}
