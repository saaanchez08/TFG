package com.coca.tienda.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class MainController {
	
	@RequestMapping("/")
    public String redirectToLogin() {
		return "redirect:/tienda/login.jsp"; // Redirige al login dentro del contexto /tienda/
    }

}
