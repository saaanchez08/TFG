package com.coca.tienda.dtos;

public class CategoriaDTO {
	private Integer categoriaID;
	private String nombre;
	private String descripcion;

	public CategoriaDTO(Integer categoriaID, String nombre, String descripcion) {
		super();
		this.categoriaID = categoriaID;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Integer getcategoriaID() {
		return categoriaID;
	}

	public void setcategoriaID(Integer categoriaID) {
		this.categoriaID = categoriaID;
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

}
