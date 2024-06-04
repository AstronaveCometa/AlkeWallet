package com.billeteraVirtual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.billeteraVirtual.entity.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MenuController {
	
	@GetMapping(value = "/menu")
	public String menu(HttpServletRequest request, Model modelo, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
		if(usuarioActual == null) {
			redirectAttributes.addFlashAttribute("mensaje", "Debe iniciar sesión para acceder");
			return "login";
		}
		modelo.addAttribute("usuarioActual", usuarioActual);
		return "menu";
	}
}
