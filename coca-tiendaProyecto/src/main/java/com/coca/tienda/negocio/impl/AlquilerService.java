package com.coca.tienda.negocio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coca.tienda.entities.Alquiler;
import com.coca.tienda.entities.Material;
import com.coca.tienda.repositories.AlquilerRepository;
import com.coca.tienda.repositories.MaterialRepository;

@Service
public class AlquilerService {

    @Autowired
    private AlquilerRepository alquilerRepository;

    @Autowired
    private MaterialRepository materialRepository;

    public Alquiler realizarAlquiler(Alquiler alquiler) {
        Material material = materialRepository.findById(alquiler.getMaterial().getMaterialID())
            .orElseThrow(() -> new IllegalArgumentException("Material no encontrado"));

        if (material.getPrecio() == null) {
            throw new IllegalArgumentException("El precio del material no puede ser nulo");
        }

        return alquilerRepository.save(alquiler);
    }
}



