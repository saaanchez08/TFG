package com.coca.tienda.negocio.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coca.tienda.dtos.AlquilerDTO;
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
    
    public boolean deleteAlquiler(int materialID) {
        Optional<Alquiler> alquilerOptional = alquilerRepository.findById(materialID);
        if (alquilerOptional.isPresent()) {
            alquilerRepository.delete(alquilerOptional.get());
            return true;
        }
        return false;
    }

    public List<AlquilerDTO> getFilteredAlquiler(Integer alquilerID, String fecha_inicio, String fecha_fin, Integer materialID, Double precio) {
        return alquilerRepository.findAll().stream()
                .filter(alquiler -> alquilerID == null || alquiler.getAlquilerID().equals(alquilerID))
                .filter(alquiler -> fecha_inicio == null || alquiler.getFecha_inicio().toString().equals(fecha_inicio))
                .filter(alquiler -> fecha_fin == null || alquiler.getFecha_fin().toString().equals(fecha_fin))
                .filter(alquiler -> materialID == null || alquiler.getMaterial().getMaterialID().equals(materialID))
                .filter(alquiler -> precio == null || alquiler.getPrecio()==(precio))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AlquilerDTO convertToDTO(Alquiler alquiler) {
        AlquilerDTO dto = new AlquilerDTO();
        dto.setAlquilerID(alquiler.getAlquilerID());
        dto.setFecha_inicio(alquiler.getFecha_inicio().toString());
        dto.setFecha_fin(alquiler.getFecha_fin().toString());
        dto.setMaterialID(alquiler.getMaterial().getMaterialID());
        dto.setPrecio(alquiler.getPrecio());
        return dto;
    }

    public boolean updateAlquiler(Integer alquilerID, AlquilerDTO alquilerDTO) {
        Optional<Alquiler> alquilerOptional = alquilerRepository.findById(alquilerID);
        if (alquilerOptional.isPresent()) {
            Alquiler alquiler = alquilerOptional.get();
            alquiler.setFecha_inicio(LocalDate.parse(alquilerDTO.getFecha_inicio()));
            alquiler.setFecha_fin(LocalDate.parse(alquilerDTO.getFecha_fin()));
            alquiler.setMaterial(materialRepository.findById(alquilerDTO.getMaterialID()).orElse(null));
            alquiler.setPrecio(alquilerDTO.getPrecio());
            alquilerRepository.save(alquiler);
            return true;
        }
        return false;
    }
}