package com.billeteraVirtual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroController {

	/*@Autowired
	private UsuarioService usuarioService;*/
	
	@GetMapping(value = "/register")
	public String index(Model modelo) {
		return "register";
	}

}