package com.billeteraVirtual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.billeteraVirtual.entity.Usuario;
import com.billeteraVirtual.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
		
	@GetMapping(value = {"/","/login"})
	public String index(Model modelo) {
		Usuario usuarioParaLogin = new Usuario();
		modelo.addAttribute("usuarioParaLogin", usuarioParaLogin);
		return "login";
	}
	
	@PostMapping(value = "/procesarLogin")
	public String login(
			@ModelAttribute(value = "usuarioParaLogin") Usuario usuarioParaLogin,
			RedirectAttributes redirectAttributes, HttpServletRequest request
			) {
		if(usuarioService.validarUsuario(usuarioParaLogin.getEmail(), usuarioParaLogin.getContrasena())) {
			redirectAttributes.addFlashAttribute("mensaje", "Inicio de sesión exitoso");
			Usuario usuarioActual = usuarioService.obtenerUsuarioPorEmail(usuarioParaLogin.getEmail());
			HttpSession session = request.getSession();
			session.setAttribute("usuarioActual", usuarioActual);
			return "redirect:/menu";
		} else {
			redirectAttributes.addFlashAttribute("mensaje", "Error en el inicio de sesión");
			return "redirect:/login";
		}		
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	}
}
