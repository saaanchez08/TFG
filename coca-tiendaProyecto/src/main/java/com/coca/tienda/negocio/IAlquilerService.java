package com.coca.tienda.negocio;

import java.sql.SQLException;

import javax.naming.NamingException;

public interface IAlquilerService {
	
		public Integer realizarAlquiler(Integer alquilerId, String fechaInicio, String fechaFin, Double precio, Integer materialId)throws ClassNotFoundException, SQLException, NamingException;

}
