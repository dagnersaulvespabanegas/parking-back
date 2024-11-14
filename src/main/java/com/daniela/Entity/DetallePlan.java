package com.daniela.Entity;

import java.util.Optional;
import java.util.Random;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "detalle_plan")
public class DetallePlan {
    @Id
    private Long detalleId;        
    private Double monto;       
    private String fechaPago;         
  

   
    public DetallePlan() {
    	 this.detalleId = new Random().nextLong();
    }


    public DetallePlan(Long detalleId, Double monto, String fechaPago) {
    	 this.detalleId = new Random().nextLong();
        this.detalleId = detalleId;
        this.monto = monto;
        this.fechaPago = fechaPago;

    }

    // Getters y Setters
    public Long getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(Long detalleId) {
        this.detalleId = detalleId;
    }


    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }
    
    public DetallePlan merge(DetallePlan updateRequest) {
    	  Optional.ofNullable(updateRequest.getDetalleId()).ifPresent(this::setDetalleId);
    	  Optional.ofNullable(updateRequest.getFechaPago()).ifPresent(this::setFechaPago);
    	  Optional.ofNullable(updateRequest.getMonto()).ifPresent(this::setMonto);
 

          return this;
      }

}
