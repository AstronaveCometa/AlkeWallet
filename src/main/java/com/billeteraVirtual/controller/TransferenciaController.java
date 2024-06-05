package com.billeteraVirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.billeteraVirtual.entity.Transaccion;
import com.billeteraVirtual.entity.Usuario;
import com.billeteraVirtual.service.TransaccionService;
import com.billeteraVirtual.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class TransferenciaController {

	@Autowired
	private TransaccionService transaccionService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value = "/transfer")
	public String transferPage(HttpServletRequest request, Model modelo, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
		if(usuarioActual == null) {
			redirectAttributes.addFlashAttribute("mensaje", "Debe iniciar sesión para acceder");
			return "redirect:/login";
		}
		Transaccion transaccionActual = new Transaccion();
		List<Usuario> todosLosUsuarios = usuarioService.obtenerTodosLosUsuarios();
		modelo.addAttribute("todosLosUsuarios", todosLosUsuarios);
		modelo.addAttribute("transaccionActual", transaccionActual);
		modelo.addAttribute("usuarioActual", usuarioActual);
		return "transfer";
	}
	
	@PostMapping(value = "/transferir")
	public String retirar(@ModelAttribute(value = "transaccionActual") Transaccion transaccionActual,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {		
		HttpSession session = request.getSession();
		Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
		transaccionActual.setIdEmisor(usuarioActual.getId());
		transaccionActual.setTipo("transferencia");
		Transaccion transaccionEjecutada = transaccionService.transferencia(transaccionActual);
		if(transaccionEjecutada == null) {
			redirectAttributes.addFlashAttribute("error", "Saldo insuficiente");
			session.setAttribute("usuarioActual", usuarioActual);
			return "redirect:/transfer";
		} else {
			transaccionService.insertarTransaccion(transaccionEjecutada);
			usuarioActual.setSaldo(usuarioActual.getSaldo() - transaccionActual.getMonto());
			session.setAttribute("usuarioActual", usuarioActual);
			redirectAttributes.addFlashAttribute("exito", "Transferencia realizada exitosamente");
			return "redirect:/transfer";
		}
	}
}
