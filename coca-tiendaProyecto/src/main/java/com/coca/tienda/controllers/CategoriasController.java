package com.coca.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coca.tienda.dtos.CategoriaDTO;
import com.coca.tienda.negocio.impl.CategoriasService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/tienda/categoria")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriaService;

    @GetMapping
    public List<CategoriaDTO> getFilteredCategories(
            @RequestParam(required = false) Integer categoriaID,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String descripcion) {
        return categoriaService.getFilteredCategorias(categoriaID, nombre, descripcion);
    }
}


