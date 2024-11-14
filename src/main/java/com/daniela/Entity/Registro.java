package com.daniela.Entity;

import java.util.Optional;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "registros")
public class Registro {
    @Id
    private Long registroId;       
    private String placa;         
    private Tarifa tipoVehiculo;    
    private String hora;       
    private String fecha;
    private Plaza plaza;
    private Tipo_Registro tipo;              // Tipo de registro: ENTRADA o SALIDA

    public Registro() {
    	 this.registroId = new Random().nextLong();
    }

    public Registro(String placa, Tarifa tipoVehiculo, String hora, String fecha, Tipo_Registro tipo, Plaza plaza) {
    	 this.registroId = new Random().nextLong();
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.hora = hora;
        this.fecha = fecha;
        this.tipo = tipo;
        this.plaza = plaza;
    }

    public Long getRegistroId() {
        return registroId;
    }

    public void setRegistroId(Long registroId) {
        this.registroId = registroId;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Tarifa getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(Tarifa tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Tipo_Registro getTipo() {
        return tipo;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }
    
    public Plaza getPlaza() {
        return plaza;
    }

    public void setTipo(Tipo_Registro tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "registroId=" + registroId +
                ", placa='" + placa + '\'' +
                ", tipoVehiculo=" + tipoVehiculo +
                ", hora='" + hora + '\'' +
                ", fecha='" + fecha + '\'' +
                ", tipo=" + tipo +
                '}';
    }
    public Registro merge(Registro updateRequest) {
  	  Optional.ofNullable(updateRequest.getPlaca()).ifPresent(this::setPlaca);
  	  Optional.ofNullable(updateRequest.getTipoVehiculo()).ifPresent(this::setTipoVehiculo);
  	  Optional.ofNullable(updateRequest.getHora()).ifPresent(this::setHora);
  	  Optional.ofNullable(updateRequest.getFecha()).ifPresent(this::setFecha);
        Optional.ofNullable(updateRequest.getTipo()).ifPresent(this::setTipo);
        Optional.ofNullable(updateRequest.getPlaza()).ifPresent(this::setPlaza);

        return this;
    }
    
    public enum Tipo_Registro {
        ENTRADA,
        SALIDA
    }
}

