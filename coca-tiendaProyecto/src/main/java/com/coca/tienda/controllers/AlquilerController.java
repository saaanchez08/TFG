package com.coca.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coca.tienda.entities.Alquiler;
import com.coca.tienda.negocio.impl.AlquilerService;

@RestController
@RequestMapping("/tienda/alquiler")
@CrossOrigin(origins = "http://localhost:8080")
public class AlquilerController {

    @Autowired
    private AlquilerService alquilerService;

    @PostMapping("/realizar")
    public ResponseEntity<?> realizarAlquiler(@RequestBody Alquiler alquiler) {
        try {
            alquilerService.calcularPrecioTotal(alquiler); // Implementar este método en AlquilerService
            Integer resultado = alquilerService.realizarAlquiler(alquiler);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al realizar el alquiler: " + e.getMessage());
        }
    }
}
