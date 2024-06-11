package com.coca.tienda.dao;

import java.sql.SQLException;

public interface AlquilerDAO {

	Integer realizarAlquiler(Integer alquilerId, String fechaInicio, String fechaFin, Double precio,
			Integer materialId) throws ClassNotFoundException, SQLException;

}
