package com.coca.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coca.tienda.dtos.CategoriaDTO;
import com.coca.tienda.dtos.MaterialDTO;
import com.coca.tienda.entities.Alquiler;
import com.coca.tienda.entities.Categoria;
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
    @DeleteMapping("/{categoriaID}")
    public ResponseEntity<String> deleteCategoria(@PathVariable Integer categoriaID) {
        boolean deleted = categoriaService.deleteCategoria(categoriaID);
        if (deleted) {
            return ResponseEntity.ok("Categoría eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada");
        }
    }  
    @PostMapping
    public ResponseEntity<String> insertCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        boolean inserted = categoriaService.insertMaterial(categoriaDTO);
        if (inserted) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Categoria insertada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al insertar la Categoria");
        }
    }
   
    @PutMapping("/{categoriaID}")
    public ResponseEntity<String> updateCategoria(@PathVariable Integer categoriaID, @RequestBody CategoriaDTO categoriaDTO) {
        boolean updated = categoriaService.updateCategoria(categoriaID, categoriaDTO);
        if (updated) {
            return ResponseEntity.ok("Categoria actualizada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria no encontrado");
        }
    }
}
   






