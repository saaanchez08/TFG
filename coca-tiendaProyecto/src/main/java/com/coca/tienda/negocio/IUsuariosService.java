package com.coca.tienda.negocio;

import java.sql.SQLException;

import javax.naming.NamingException;

public interface IUsuariosService {
	
	public Integer insertarUsuarios(String nombre, String email, String contrasena)throws ClassNotFoundException, SQLException, NamingException;

}
