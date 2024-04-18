package com.coca.tienda.negocio.impl;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;

import com.coca.tienda.dao.CategoriasDAO;
import com.coca.tienda.dtos.CategoriaDTO;
import com.coca.tienda.negocio.ICategoriasService;

public class CategoriasService implements ICategoriasService {

	@Autowired
	CategoriasDAO categoriasDAO;

	@Override
	public List<CategoriaDTO> obtenerTodosCategorias() throws ClassNotFoundException, SQLException, NamingException {
		// TODO Auto-generated method stub
		return categoriasDAO.obtenerTodosCategorias();
	}

	@Override
	public List<CategoriaDTO> buscarCategoria(String id, String nombre, String descripcion)
			throws ClassNotFoundException, SQLException, NamingException {
		// TODO Auto-generated method stub
		return categoriasDAO.buscarCategoria(id, nombre, descripcion);
	}
}
