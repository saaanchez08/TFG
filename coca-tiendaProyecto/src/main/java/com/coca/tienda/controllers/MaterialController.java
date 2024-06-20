package com.coca.tienda.controllers;

import com.coca.tienda.dtos.MaterialDTO;
import com.coca.tienda.negocio.impl.MaterialService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/tienda/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public List<MaterialDTO> getAllMaterials(
            @RequestParam(required = false) Integer materialID,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) Double precio,
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Integer categoriaID) {
        return materialService.getFilteredMaterials(materialID, nombre, descripcion, precio, estado, categoriaID);
    }
    
    @DeleteMapping("/{materialID}")
    public ResponseEntity<String> deleteMaterial(@PathVariable Integer materialID) {
        boolean deleted = materialService.deleteMaterial(materialID);
        if (deleted) {
            return ResponseEntity.ok("Material eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material no encontrado");
        }
    }
    
    @PostMapping
    public ResponseEntity<String> insertMaterial(@RequestBody MaterialDTO materialDTO) {
        boolean inserted = materialService.insertMaterial(materialDTO);
        if (inserted) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Material insertado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al insertar el material");
        }
    }

    @PutMapping("/{materialID}")
    public ResponseEntity<String> updateMaterial(@PathVariable Integer materialID, @RequestBody MaterialDTO materialDTO) {
        boolean updated = materialService.updateMaterial(materialID, materialDTO);
        if (updated) {
            return ResponseEntity.ok("Material actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material no encontrado");
        }
    }
}
