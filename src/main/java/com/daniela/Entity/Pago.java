package com.daniela.Entity;

import java.util.Optional;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pagos")
public class Pago {
    @Id
    private Long pagoId;                // ID único para cada pago
    private Registro registro;          // Registro asociado al pago
    private String tiempoTranscurrido;  // Tiempo total que el vehículo estuvo estacionado
    private Double totalPagar;          // Monto total a pagar

    // Constructor por defecto
    public Pago() {
    	this.pagoId = new Random().nextLong();
    }

    // Constructor con parámetros
    public Pago( Registro registro, String tiempoTranscurrido, Double totalPagar) {
    	this.pagoId = new Random().nextLong();
        this.registro = registro;
        this.tiempoTranscurrido = tiempoTranscurrido;
        this.totalPagar = totalPagar;
    }

    // Getters y Setters
    public Long getPagoId() {
        return pagoId;
    }

    public void setPagoId(Long pagoId) {
        this.pagoId = pagoId;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public String getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public void setTiempoTranscurrido(String tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "pagoId=" + pagoId +
                ", registro=" + registro +
                ", tiempoTranscurrido='" + tiempoTranscurrido + '\'' +
                ", totalPagar=" + totalPagar +
                '}';
    }
    
    public Pago merge(Pago updateRequest) {
    	  Optional.ofNullable(updateRequest.getRegistro()).ifPresent(this::setRegistro);
    	  Optional.ofNullable(updateRequest.getTiempoTranscurrido()).ifPresent(this::setTiempoTranscurrido);
    	  Optional.ofNullable(updateRequest.getTotalPagar()).ifPresent(this::setTotalPagar);


          return this;
      }
}

