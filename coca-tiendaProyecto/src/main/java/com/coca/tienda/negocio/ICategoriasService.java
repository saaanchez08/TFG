package com.coca.tienda.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.coca.tienda.dtos.CategoriaDTO;

public interface ICategoriasService {
	public List<CategoriaDTO> obtenerTodosCategorias() throws ClassNotFoundException, SQLException, NamingException;

	public List<CategoriaDTO> buscarCategoria(String id, String nombre, String descripcion)
			throws ClassNotFoundException, SQLException, NamingException;
}
