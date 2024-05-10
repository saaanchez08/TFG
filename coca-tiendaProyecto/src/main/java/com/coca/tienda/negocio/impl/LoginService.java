package com.coca.tienda.negocio.impl;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public boolean validateUser(String userid, String password) {
        return userid.equalsIgnoreCase("root")
                && password.equalsIgnoreCase("pass");
	}
}