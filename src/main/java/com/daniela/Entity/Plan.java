package com.daniela.Entity;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planes")
public class Plan {
    @Id
    private Long planId;
    private Abonado abonado;  
    private Double montoSemanaMes; 
    private int duracion;  
    private Tipo tipo; 
    private String fechaInicio;  
    private Double montoTotal;  
    private List<DetallePlan> detalle; 

   
    public Plan() {
        this.planId = new Random().nextLong();
    }

  
    public Plan(Abonado abonado, Double montoSemanaMes, int duracion, Tipo tipo, String fechaInicio, Double montoTotal, List<DetallePlan> detalle) {
        this.planId = new Random().nextLong();
        this.abonado = abonado;
        this.montoSemanaMes = montoSemanaMes;
        this.duracion = duracion;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.montoTotal = montoTotal;
        this.detalle = detalle;
    }

 
    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Abonado getAbonado() {
        return abonado;
    }

    public void setAbonado(Abonado abonado) {
        this.abonado = abonado;
    }

    public Double getMontoSemanaMes() {
        return montoSemanaMes;
    }

    public void setMontoSemanaMes(Double montoSemanaMes) {
        this.montoSemanaMes = montoSemanaMes;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public List<DetallePlan> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetallePlan> detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planId=" + planId +
                ", abonado=" + abonado +
                ", montoSemanaMes=" + montoSemanaMes +
                ", duracion=" + duracion +
                ", tipo=" + tipo +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", montoTotal=" + montoTotal +
                '}';
    }

    public Plan merge(Plan updateRequest) {
        Optional.ofNullable(updateRequest.getAbonado()).ifPresent(this::setAbonado);
        Optional.ofNullable(updateRequest.getDuracion()).ifPresent(this::setDuracion);
        Optional.ofNullable(updateRequest.getTipo()).ifPresent(this::setTipo);
        Optional.ofNullable(updateRequest.getMontoTotal()).ifPresent(this::setMontoTotal);
        Optional.ofNullable(updateRequest.getMontoSemanaMes()).ifPresent(this::setMontoSemanaMes);
        Optional.ofNullable(updateRequest.getFechaInicio()).ifPresent(this::setFechaInicio);
        Optional.ofNullable(updateRequest.getDetalle()).ifPresent(this::setDetalle);
        return this;
    }

 
    public enum Tipo {
        SEMANAL,
        MENSUAL
    }
}
