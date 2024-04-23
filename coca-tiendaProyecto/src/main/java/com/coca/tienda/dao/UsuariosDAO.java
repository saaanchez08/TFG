package com.coca.tienda.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

public interface UsuariosDAO {

	public Integer insertarUsuarios(String nombre, String email, String contrasena)throws ClassNotFoundException, SQLException, NamingException;
}
