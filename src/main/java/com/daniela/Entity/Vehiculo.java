package com.daniela.Entity;

import java.util.Optional;
import java.util.Random;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehiculos")
public class Vehiculo {
    @Id
    private Long vehiculoId;
    private String placa; 
    private String color; 
    @DBRef
    private Abonado abonado; 
    private Status estado;
    

    public Vehiculo() {
        this.vehiculoId = new Random().nextLong();
    }

    // Constructor con parámetros
    public Vehiculo(String placa, Abonado abonado, String color,Status estado) {
        this.vehiculoId = new Random().nextLong();
        this.placa = placa;
        this.abonado = abonado;
        this.color = color;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Abonado getAbonado() {
        return abonado;
    }

    public void setAbonado(Abonado abonado) {
        this.abonado = abonado;
    }


    
    public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	

	public Status getEstado() {
		return estado;
	}

	public void setEstado(Status estado) {
		this.estado = estado;
	}



    public Vehiculo merge(Vehiculo updateRequest) {
  	  Optional.ofNullable(updateRequest.getPlaca()).ifPresent(this::setPlaca);
  	  Optional.ofNullable(updateRequest.getAbonado()).ifPresent(this::setAbonado);
  	 Optional.ofNullable(updateRequest.getColor()).ifPresent(this::setColor);
  	 Optional.ofNullable(updateRequest.getEstado()).ifPresent(this::setEstado);
  	

        return this;
    }
    public enum Status {
        ADENTRO,
        AFUERA
    }

}
