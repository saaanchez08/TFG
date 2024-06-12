package com.coca.tienda.controllers;

import com.coca.tienda.dtos.MaterialDTO;
import com.coca.tienda.negocio.impl.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/tienda/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public List<MaterialDTO> getAllMaterials() {
        return materialService.getAllMaterials();
    }
}
