package com.coca.tienda.dtos;

public class UsuarioDTO {
    private int usuarioID;
    private String nombre;
    private String email;
    private String contrasena;

    // Constructor vacío
    public UsuarioDTO() {
    }

    // Constructor con todos los campos
    public UsuarioDTO(int usuarioID, String nombre, String email, String contrasena) {
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    // Getters y setters
    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // toString() para facilitar la impresión
    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "usuarioID=" + usuarioID +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}

