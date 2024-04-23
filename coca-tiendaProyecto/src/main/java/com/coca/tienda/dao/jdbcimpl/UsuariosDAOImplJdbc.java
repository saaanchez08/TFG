package com.coca.tienda.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import org.springframework.stereotype.Component;

import com.coca.tienda.dao.UsuariosDAO;
import com.coca.tienda.utils.DBUtils;

@Component
public class UsuariosDAOImplJdbc implements UsuariosDAO{

	@Override
	public Integer insertarUsuarios(String nombre, String email, String contrasena) throws ClassNotFoundException, SQLException, NamingException {
		
		String sql = "INSERT INTO Usuario (nombre, email, contrasena) VALUES (?, ?, ?)";

	    Connection connection = DBUtils.conectaBBDD();
	    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	    ps.setString(1, nombre);
	    ps.setString(2, email);
	    ps.setString(3, contrasena);

	    Integer resultado = ps.executeUpdate();
	    connection.close();
	    return resultado;
	    
	}

}
