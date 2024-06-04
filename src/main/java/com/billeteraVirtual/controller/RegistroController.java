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

@Controller
public class RegistroController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value = "/register")
	public String index(Model modelo) {
		Usuario usuarioParaRegistro = new Usuario();
		modelo.addAttribute("usuarioParaRegistro", usuarioParaRegistro);
		return "register";
	}

	@PostMapping(value = "/procesarRegistro")
	public String login(
			@ModelAttribute(value = "usuarioParaRegistro") Usuario usuarioParaRegistro,
			RedirectAttributes redirectAttributes,
			Model model
			) {
		if(usuarioService.existeUsuario(usuarioParaRegistro.getEmail())) {
			redirectAttributes.addFlashAttribute("mensaje", "El usuario ya existe");
			return "redirect:/login";
		} else {
			redirectAttributes.addFlashAttribute("creacion", "Usuario creado con exito");
			usuarioService.crearUsuario(usuarioParaRegistro);
			return "redirect:/login";
		}		
	}
}
