package com.coca.tienda.dtos;

public class MaterialDTO {
    private Integer materialID;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String estado;
    private Integer categoriaID;
    
	public MaterialDTO(Integer materialID, String nombre, String descripcion, Double precio, String estado, Integer categoriaID) {
		super();
		this.materialID = materialID;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.categoriaID = categoriaID;
	}
	
	public Integer getMaterialID() {
		return materialID;
	}
	public void setMaterialID(Integer materialID) {
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
	public Integer getCategoriaID() {
		return categoriaID;
	}
	public void setCategoriaID(Integer categoriaID) {
		this.categoriaID = categoriaID;
	}

}
