package com.billeteraVirtual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping(value = {"/","/login"})
	public String index(Model model) {
		model.addAttribute("titulo", "Login");
		return "login";
	}
}
