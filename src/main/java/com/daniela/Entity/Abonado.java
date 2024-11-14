package com.daniela.Entity;

import java.util.Optional;
import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "abonados")
public class Abonado {
@Id
private Long abonadoId;
private String nombre;
private String cedula;
private String placa;
private String direccion;
private String celular;
public Abonado() {
	 this.abonadoId = new Random().nextLong();
}
public Abonado(String nombre, String cedula, String placa, String direccion, String celular) {
	
	 this.abonadoId = new Random().nextLong();
	this.nombre = nombre;
	this.cedula = cedula;
	this.placa = placa;
	this.direccion = direccion;
	this.celular = celular;
}
public Long getAbonadoId() {
	return abonadoId;
}
public void setAbonadoId(Long abonadoId) {
	this.abonadoId = abonadoId;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getCedula() {
	return cedula;
}
public void setCedula(String cedula) {
	this.cedula = cedula;
}
public String getPlaca() {
	return placa;
}
public void setPlaca(String placa) {
	this.placa = placa;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public String getCelular() {
	return celular;
}
public void setCelular(String celular) {
	this.celular = celular;
}
public Abonado merge(Abonado updateRequest) {
	  Optional.ofNullable(updateRequest.getCedula()).ifPresent(this::setCedula);
	  Optional.ofNullable(updateRequest.getNombre()).ifPresent(this::setNombre);
	  Optional.ofNullable(updateRequest.getPlaca()).ifPresent(this::setPlaca);
	  Optional.ofNullable(updateRequest.getDireccion()).ifPresent(this::setDireccion);
    Optional.ofNullable(updateRequest.getCelular()).ifPresent(this::setCelular);

    return this;
}

}
