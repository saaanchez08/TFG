package com.coca.tienda.dtos;

public class AlquilerDTO {
	private Integer alquilerID;
    private String fecha_inicio;
    private String fecha_fin;
    private int materialID;
    private double precio;

    // Getters y setters
    public Integer getAlquilerID() {
        return alquilerID;
    }

    public void setAlquilerID(Integer alquilerID) {
        this.alquilerID = alquilerID;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
