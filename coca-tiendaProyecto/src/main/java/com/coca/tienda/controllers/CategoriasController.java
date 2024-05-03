package com.coca.tienda.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coca.tienda.dtos.CategoriaDTO;
import com.coca.tienda.negocio.ICategoriasService;

@Controller
@RequestMapping("/categorias/")
public class CategoriasController {
    @Autowired
    ICategoriasService categoriasService;

    @GetMapping("listarcategorias")
    public String getListadoCategorias() {
        return "/Categorias/listadoCategorias";
    }

    @PostMapping("listarcategorias")
    public String buscarCategoria(@RequestParam(value = "id", required = false) String id, 
                                  @RequestParam("nombre") String nombre,
                                  @RequestParam("descripcion") String descripcion, 
                                  ModelMap model)
            throws ClassNotFoundException, SQLException, NamingException {

        List<CategoriaDTO> listaCategorias;

        if (id != null && !id.isEmpty()) {
            listaCategorias = categoriasService.buscarCategoria(id, nombre, descripcion);
        } else {
            listaCategorias = categoriasService.buscarCategoria("", nombre, descripcion);
        }

        model.addAttribute("lista", listaCategorias);

        return "/Categorias/listadoCategorias";
    }}