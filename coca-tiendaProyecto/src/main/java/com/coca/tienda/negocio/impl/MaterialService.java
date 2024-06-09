package com.coca.tienda.negocio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coca.tienda.dtos.MaterialDTO;
import com.coca.tienda.entities.Material;
import com.coca.tienda.repositories.MaterialRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<MaterialDTO> getAllMaterials() {
        return materialRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private MaterialDTO convertToDTO(Material material) {
        return new MaterialDTO(
                material.getMaterialID(),
                material.getNombre(),
                material.getDescripcion(),
                material.getPrecio(),
                material.getEstado(),
                material.getCategoriaID()
        );
    }
}

