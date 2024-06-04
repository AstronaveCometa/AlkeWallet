package com.billeteraVirtual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.billeteraVirtual.entity.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MenuController {
	
	@GetMapping(value = "/menu")
	public String menu(HttpServletRequest request, Model modelo) {
		HttpSession session = request.getSession();
		Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
		modelo.addAttribute("usuarioActual", usuarioActual);
		return "menu";
	}
}
