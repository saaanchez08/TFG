package com.coca.tienda.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.coca.tienda.dao.CategoriasDAO;
import com.coca.tienda.dtos.CategoriaDTO;
import com.coca.tienda.utils.DBUtils;

@Component
public class CategoriasDAOImplJdbc implements CategoriasDAO {
	private static final Logger logger = LoggerFactory.getLogger(CategoriasDAOImplJdbc.class);

	@Override
	public List<CategoriaDTO> obtenerTodosCategorias() throws ClassNotFoundException, SQLException, NamingException {
		List<CategoriaDTO> listaCategorias = new ArrayList<>();
		Connection connection = DBUtils.conectaBBDD();

		try (Statement st = connection.createStatement();

				ResultSet rs = st.executeQuery("SELECT * FROM categoria")) {

			while (rs.next()) {

				Integer id = rs.getInt("categoriaID");
				String nombre = rs.getString("nombre");
				String descripcion = rs.getString("descripcion");

				CategoriaDTO categoria = new CategoriaDTO(id, nombre, descripcion);
				listaCategorias.add(categoria);

				logger.info("añadida categoria " + id + " " + nombre);
			}
		} finally {

			connection.close();
		}

		return listaCategorias;
	}

	@Override
	public List<CategoriaDTO> buscarCategoria(String id, String nombre, String descripcion)
			throws ClassNotFoundException, SQLException, NamingException {

		String sql = "SELECT * FROM categoria " + "WHERE categoriaID LIKE ? AND nombre LIKE ? AND descripcion LIKE ?";

		Connection connection = DBUtils.conectaBBDD();
		List<CategoriaDTO> listaCategorias = new ArrayList<>();

		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, "%" + id + "%");
		ps.setString(2, "%" + nombre + "%");
		ps.setString(3, "%" + descripcion + "%");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			listaCategorias.add(new CategoriaDTO(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
		connection.close();
		return listaCategorias;

	}
}
