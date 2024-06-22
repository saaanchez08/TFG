package com.coca.tienda.controllers;

import java.util.List;

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

import com.coca.tienda.dtos.AlquilerDTO;
import com.coca.tienda.entities.Alquiler;
import com.coca.tienda.negocio.impl.AlquilerService;

@RestController
@RequestMapping("/tienda/alquiler")
@CrossOrigin(origins = "http://localhost:8080")
public class AlquilerController {

    @Autowired
    private AlquilerService alquilerService;

    @PostMapping("/realizar")
    public ResponseEntity<Alquiler> realizarAlquiler(@RequestBody Alquiler alquiler) {
        try {
            Alquiler nuevoAlquiler = alquilerService.realizarAlquiler(alquiler);
            return ResponseEntity.ok(nuevoAlquiler);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    @DeleteMapping("/{materialID}")
    public ResponseEntity<String> deleteCategoria(@PathVariable int materialID) {
        boolean deleted = alquilerService.deleteAlquiler(materialID);
        if (deleted) {
            return ResponseEntity.ok("Alquiler eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alquiler no encontrada");
        }
    }
    
    @GetMapping
    public List<AlquilerDTO> getFilteredAlquiler(
            @RequestParam(required = false) Integer alquilerID,
            @RequestParam(required = false) String fecha_inicio,
            @RequestParam(required = false) String fecha_fin,
            @RequestParam(required = false) Integer materialID,
            @RequestParam(required = false) Double precio) {
        return alquilerService.getFilteredAlquiler(alquilerID, fecha_inicio, fecha_fin, materialID, precio);
    }
    
    @PutMapping("/{alquilerID}")
    public ResponseEntity<String> updateAlquiler(@PathVariable Integer alquilerID, @RequestBody AlquilerDTO alquilerDTO) {
        boolean updated = alquilerService.updateAlquiler(alquilerID, alquilerDTO);
        if (updated) {
            return ResponseEntity.ok("Alquiler se ha actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alquiler no encontrado");
        }
    }
}