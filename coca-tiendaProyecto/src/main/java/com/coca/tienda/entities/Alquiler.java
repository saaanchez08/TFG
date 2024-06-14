package com.coca.tienda.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer alquilerID;

    @ManyToOne
    @JoinColumn(name = "materialID", referencedColumnName = "materialID")
    private Material material;
    
    @Column(name = "fechaInicio")
    private LocalDate fechaInicio;

    @Column(name = "fechaFin")
    private LocalDate fechaFin;

    private Double precio;
    
	public Integer getAlquilerID() {
		return alquilerID;
	}
	public void setAlquilerID(Integer alquilerID) {
		this.alquilerID = alquilerID;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public LocalDate  getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate  fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate  getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate  fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
}
