package com.daniela.Entity;

import java.util.Optional;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plazas")
public class Plaza {
    @Id
    private Long plazaId;    
    private Status estado;  
    private int numero;
    private Double largo;    
    private Double ancho;    

    // Constructor por defecto
    public Plaza() {
    	 this.plazaId = new Random().nextLong();
    }

  
    public Plaza(Status estado, int numero, Double largo, Double ancho) {
    	 this.plazaId = new Random().nextLong();
    	 this.numero = numero;
        this.estado = estado;
        this.largo = largo;
        this.ancho = ancho;
    }


    public Long getPlazaId() {
        return plazaId;
    }

    public void setPlazaId(Long plazaId) {
        this.plazaId = plazaId;
    }

    public Status getEstado() {
        return estado;
    }

    public void setEstado(Status estado) {
        this.estado = estado;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
    public String toString() {
        return "Plaza{" +
                "plazaId=" + plazaId +
                ", estado=" + estado +
                ", largo=" + largo +
                ", ancho=" + ancho +
                '}';
    }
    public Plaza merge(Plaza updateRequest) {
  	  Optional.ofNullable(updateRequest.getEstado()).ifPresent(this::setEstado);
  	Optional.ofNullable(updateRequest.getNumero()).ifPresent(this::setNumero);
  	  Optional.ofNullable(updateRequest.getAncho()).ifPresent(this::setAncho);
  	  Optional.ofNullable(updateRequest.getLargo()).ifPresent(this::setLargo);


        return this;
    }
    // Enumeración para representar el estado de la plaza
    public enum Status {
        OCUPADA,
        DISPONIBLE
    }
}
