package com.coca.tienda.negocio.impl;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coca.tienda.dao.UsuariosDAO;
import com.coca.tienda.dtos.ComboDTO;
import com.coca.tienda.negocio.IUsuariosService;

@Component
public class UsuariosService implements IUsuariosService {
	
	@Autowired
	UsuariosDAO usuariosDAO;

	@Override
	public Integer insertarUsuarios(String nombre, String email, String contrasena) throws ClassNotFoundException, SQLException, NamingException {
		return usuariosDAO.insertarUsuarios(nombre, email, contrasena);
	}
	
}
