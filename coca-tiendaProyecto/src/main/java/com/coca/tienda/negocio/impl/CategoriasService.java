package com.coca.tienda.negocio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coca.tienda.dtos.CategoriaDTO;
import com.coca.tienda.dtos.MaterialDTO;
import com.coca.tienda.entities.Categoria;
import com.coca.tienda.entities.Material;
import com.coca.tienda.repositories.CategoriaRepository;

import java.util.List;
import java.util.Optional;
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
    public boolean deleteCategoria(Integer categoriaID) {
    	Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaID);
		 if (categoriaOptional.isPresent()) {
			 categoriaRepository.delete(categoriaOptional.get());
	            return true;
	        }
	        return false;
	    }

	public boolean insertMaterial(CategoriaDTO categoriaDTO) {
		try {
			Categoria categoria = new Categoria(categoriaDTO.getcategoriaID(), categoriaDTO.getNombre(), categoriaDTO.getDescripcion());
			categoriaRepository.save(categoria);
            return true;

		 } catch (Exception e) {
	            return false;
	        }
	}
	public boolean updateCategoria(Integer categoriaID,CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaID);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            categoria.setCategoriaID(categoriaID);
            categoria.setNombre(categoriaDTO.getNombre());
            categoria.setDescripcion(categoriaDTO.getDescripcion());
            categoriaRepository.save(categoria);
            return true;
        }
        return false;
    }
}
