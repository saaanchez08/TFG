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

import com.coca.tienda.negocio.IUsuariosService;

@Controller
@RequestMapping("/usuarios/")
public class UsuariosController {
	
	@Autowired
	IUsuariosService usuariosService;
	
	@GetMapping("insertarusuario")
	public String getFormularioInsertarUsuarios(ModelMap model) throws ClassNotFoundException, SQLException, NamingException {
		return "/Usuarios/insertarUsuarios";
	}

	@PostMapping("insertarusuario")
	public String insertarUsuarios(
			@RequestParam("nombre") String nombre,
			@RequestParam("email")String descripcion,
			@RequestParam("contrasena")String contrasena, ModelMap model) throws ClassNotFoundException, SQLException, NamingException {


		Integer resultado = usuariosService.insertarUsuarios(nombre, descripcion, contrasena);
		model.addAttribute("resultado", resultado);

		return "/Usuarios/insertarUsuarios";
	}

}
