package com.coca.tienda.dao;

import java.sql.Date;
import java.sql.SQLException;

public interface AlquilerDAO {

	Integer realizarAlquiler(Integer alquilerId, Date fechaInicio, Date fechaFin, Double precio, Integer materialId) throws ClassNotFoundException, SQLException;

}
