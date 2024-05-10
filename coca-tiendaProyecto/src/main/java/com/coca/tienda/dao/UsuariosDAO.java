package com.coca.tienda.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.coca.tienda.dtos.ComboDTO;

public interface UsuariosDAO {

	public Integer insertarUsuarios(String nombre, String email, String contrasena)throws ClassNotFoundException, SQLException, NamingException;
}
