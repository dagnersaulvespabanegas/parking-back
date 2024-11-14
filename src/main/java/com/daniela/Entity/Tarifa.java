package com.daniela.Entity;

import java.util.Optional;
import java.util.Random;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tarifas")
public class Tarifa {
@Id
private Long tarifaId;
private String tipoVehiculo;
private double precio_hora;
private double precio_dia;
private double precio_lavado;



public Tarifa() {
	this.tarifaId = new Random().nextLong();
}


public Tarifa( String tipoVehiculo, double precio_hora, double precio_dia, double precio_lavado) {
	this.tarifaId = new Random().nextLong();
	this.tipoVehiculo = tipoVehiculo;
	this.precio_hora = precio_hora;
	this.precio_dia = precio_dia;
	this.precio_lavado = precio_lavado;
}


public Long getTarifaId() {
	return tarifaId;
}


public void setTarifaId(Long tarifaId) {
	this.tarifaId = tarifaId;
}


public String getTipoVehiculo() {
	return tipoVehiculo;
}


public void setTipoVehiculo(String tipoVehiculo) {
	this.tipoVehiculo = tipoVehiculo;
}


public double getPrecio_hora() {
	return precio_hora;
}


public void setPrecio_hora(double precio_hora) {
	this.precio_hora = precio_hora;
}


public double getPrecio_dia() {
	return precio_dia;
}


public void setPrecio_dia(double precio_dia) {
	this.precio_dia = precio_dia;
}


public double getPrecio_lavado() {
	return precio_lavado;
}


public void setPrecio_lavado(double precio_lavado) {
	this.precio_lavado = precio_lavado;
}
public Tarifa merge(Tarifa updateRequest) {
	Optional.ofNullable(updateRequest.getTipoVehiculo()).ifPresent(this::setTipoVehiculo);
	Optional.ofNullable(updateRequest.getPrecio_hora()).ifPresent(this::setPrecio_hora);
	Optional.ofNullable(updateRequest.getPrecio_dia()).ifPresent(this::setPrecio_dia);
	Optional.ofNullable(updateRequest.getPrecio_lavado()).ifPresent(this::setPrecio_lavado);
	return this;
}

}
