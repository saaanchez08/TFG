package com.coca.tienda.negocio.impl;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coca.tienda.dao.AlquilerDAO;
import com.coca.tienda.negocio.IAlquilerService;

@Component
public class AlquilerService implements IAlquilerService{
	
	@Autowired
	AlquilerDAO alquilerDAO;
	
	@Override
	public Integer realizarAlquiler(Integer alquilerId, String fechaInicio, String fechaFin, Double precio, Integer materialId) throws ClassNotFoundException, SQLException, NamingException {
		
		return alquilerDAO.realizarAlquiler(alquilerId, fechaInicio, fechaFin, precio, materialId);
	}

}
