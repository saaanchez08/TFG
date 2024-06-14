package com.coca.tienda.negocio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coca.tienda.dtos.CategoriaDTO;
import com.coca.tienda.entities.Categoria;
import com.coca.tienda.repositories.CategoriaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriasService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CategoriaDTO> getFilteredCategorias(Integer categoriaID, String nombre, String descripcion) {
        return categoriaRepository.findAll().stream()
                .filter(categoria -> categoriaID == null || categoria.getCategoriaID().equals(categoriaID))
                .filter(categoria -> nombre == null || categoria.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .filter(categoria -> descripcion == null || categoria.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CategoriaDTO convertToDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getCategoriaID(), categoria.getNombre(), categoria.getDescripcion());
    }
}
