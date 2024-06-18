package com.coca.tienda.negocio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coca.tienda.dtos.MaterialDTO;
import com.coca.tienda.entities.Material;
import com.coca.tienda.repositories.MaterialRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<MaterialDTO> getAllMaterials() {
        return materialRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MaterialDTO> getFilteredMaterials(Integer materialID, String nombre, String descripcion, Double precio, String estado, Integer categoriaID) {
        return materialRepository.findAll().stream()
                .filter(material -> materialID == null || material.getMaterialID().equals(materialID))
                .filter(material -> nombre == null || material.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(material -> descripcion == null || material.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
                .filter(material -> precio == null || material.getPrecio().equals(precio))
                .filter(material -> estado == null || material.getEstado().equalsIgnoreCase(estado))
                .filter(material -> categoriaID == null || material.getCategoriaID().equals(categoriaID))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MaterialDTO convertToDTO(Material material) {
        return new MaterialDTO(material.getMaterialID(), material.getNombre(), material.getDescripcion(), material.getPrecio(), material.getEstado(), material.getCategoriaID());
    }
    
    public boolean deleteMaterial(Integer materialID) {
        Optional<Material> materialOptional = materialRepository.findById(materialID);
        if (materialOptional.isPresent()) {
            materialRepository.delete(materialOptional.get());
            return true;
        }
        return false;
    }
}

