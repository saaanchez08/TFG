package com.coca.tienda.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.coca.tienda.dtos.ComboDTO;

public interface IUsuariosService {
	
	public Integer insertarUsuarios(String nombre, String email, String contrasena)throws ClassNotFoundException, SQLException, NamingException;
	
}
