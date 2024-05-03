package com.coca.tienda.controllers;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coca.tienda.negocio.IAlquilerService;

@Controller
@RequestMapping("/alquiler/")
public class AlquilerController {
    
    @Autowired
    IAlquilerService alquilerService;
    
    @GetMapping("realizaralquiler")
    public String getFormularioRealizarAlquiler(ModelMap model) throws ClassNotFoundException, SQLException, NamingException {
        return "/Alquiler/realizarAlquiler";
    }
    
    @PostMapping("realizaralquiler")
    public String realizarAlquiler(
            @RequestParam(value = "alquilerId", required = false) Integer alquilerId,
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin,
            @RequestParam("monto") Double monto,
            @RequestParam("usuarioID") Integer usuarioID,
            @RequestParam(value = "materialId", required = false) Integer materialId,
            ModelMap model) {
        
        try {
            Integer resultado = alquilerService.realizarAlquiler(alquilerId, fechaInicio, fechaFin, monto, usuarioID, materialId);
            model.addAttribute("resultado", resultado);
        } catch (Exception e) {
            // Manejar la excepción adecuadamente, por ejemplo, mostrando un mensaje de error al usuario
            model.addAttribute("error", "Se produjo un error al realizar el alquiler: " + e.getMessage());
            return "/Alquiler/realizarAlquiler";
        }
        
        return "/Alquiler/realizarAlquiler";
    }
}
