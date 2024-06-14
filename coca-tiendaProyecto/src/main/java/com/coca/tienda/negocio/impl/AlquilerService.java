package com.coca.tienda.negocio.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coca.tienda.entities.Alquiler;
import com.coca.tienda.entities.Material;
import com.coca.tienda.repositories.AlquilerRepository;

@Service
public class AlquilerService {

    @Autowired
    private AlquilerRepository alquilerRepository;

    public Integer realizarAlquiler(Alquiler alquiler) throws ClassNotFoundException, SQLException, NamingException {
       
        Alquiler alquilerGuardado = alquilerRepository.save(alquiler);
        return alquilerGuardado.getAlquilerID(); 
    }

	public void calcularPrecioTotal(Alquiler alquiler) {
		LocalDate fechaInicio = alquiler.getFechaInicio();
        LocalDate fechaFin = alquiler.getFechaFin();

        // Calcular la duración en días
        long diffDays = ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1; // Sumamos 1 para incluir el último día

        // Obtener el precio base del material
        Material material = alquiler.getMaterial();
        double precioBase = material.getPrecio();

        // Calcular el precio total
        double precioTotal = precioBase * diffDays;

        // Asignar el precio total al alquiler
        alquiler.setPrecio(precioTotal);	
	}
}