package com.coca.tienda.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.coca.tienda.dao.AlquilerDAO;
import com.coca.tienda.utils.DBUtils;

@Component
public class AlquilerDAOImplJdbc implements AlquilerDAO {

    @Override
    public Integer realizarAlquiler(Integer alquilerId, String fechaInicio, String fechaFin, Double monto,
            Integer usuarioID, Integer materialId) throws ClassNotFoundException, SQLException {

        String sql = "INSERT INTO alquiler (fechaInicio, fechaFin, monto, usuarioID, materialID) VALUES (?, ?, ?, ?, ?);";
     
        	Connection connection = DBUtils.conectaBBDD();
    	    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, fechaInicio);
            ps.setString(2, fechaFin);
            ps.setDouble(3, monto);
            ps.setInt(4, usuarioID);
            ps.setInt(5, materialId);

    	    Integer resultado = ps.executeUpdate();

    	    connection.close();

        return resultado;
    }

}