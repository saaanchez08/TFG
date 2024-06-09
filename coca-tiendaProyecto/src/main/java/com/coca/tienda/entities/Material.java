package com.coca.tienda.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialID;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String estado;
    private Long categoriaID;
    
    
	public Long getMaterialID() {
		return materialID;
	}
	public void setMaterialID(Long materialID) {
		this.materialID = materialID;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getCategoriaID() {
		return categoriaID;
	}
	public void setCategoriaID(Long categoriaID) {
		this.categoriaID = categoriaID;
	}
}
