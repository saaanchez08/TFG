package com.coca.tienda.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.coca.tienda.dtos.CategoriaDTO;

public interface CategoriasDAO {
	public List<CategoriaDTO> obtenerTodosCategorias() throws ClassNotFoundException, SQLException, NamingException;

	public List<CategoriaDTO> buscarCategoria(String id, String nombre, String descripcion) throws ClassNotFoundException, SQLException, NamingException;
}
