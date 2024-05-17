package com.coca.tienda.controllers;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Acerca/")
public class AcercaDeNosotrosController {
	
	@GetMapping("AcercaDeNosotros")
	  public String getAcercaDeNosotros(ModelMap model) throws ClassNotFoundException, SQLException, NamingException {
        return "/Acerca/AcercaDeNosotros";
    }

}
