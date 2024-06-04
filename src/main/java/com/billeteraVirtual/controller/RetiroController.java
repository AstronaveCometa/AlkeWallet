package com.billeteraVirtual.controller;

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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RetiroController {

	@Autowired
	private TransaccionService transaccionService;
	
	@GetMapping(value = "/sendmoney")
	public String sendmoneyPage(HttpServletRequest request, Model modelo, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
		if(usuarioActual == null) {
			redirectAttributes.addFlashAttribute("mensaje", "Debe iniciar sesión para acceder");
			return "redirect:/login";
		}
		Transaccion transaccionActual = new Transaccion();
		modelo.addAttribute("transaccionActual", transaccionActual);
		modelo.addAttribute("usuarioActual", usuarioActual);
		redirectAttributes.addFlashAttribute("error", "Hola hola pirinolas");
		return "sendmoney";
	}
	
	@PostMapping(value = "/retirar")
	public String retirar(@ModelAttribute(value = "transaccionActual") Transaccion transaccionActual,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {		
		HttpSession session = request.getSession();
		Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
		transaccionActual.setIdEmisor(usuarioActual.getId());
		transaccionActual.setIdReceptor(usuarioActual.getId());
		transaccionActual.setTipo("retiro");
		Transaccion transaccionEjecutada = transaccionService.retiro(transaccionActual);
		if(transaccionEjecutada == null) {
			redirectAttributes.addFlashAttribute("error", "Saldo insuficiente");
			session.setAttribute("usuarioActual", usuarioActual);
			return "redirect:/sendmoney";
		} else {
			transaccionService.insertarTransaccion(transaccionEjecutada);
			usuarioActual.setSaldo(usuarioActual.getSaldo()-transaccionActual.getMonto());
			session.setAttribute("usuarioActual", usuarioActual);
			redirectAttributes.addFlashAttribute("exito", "Retiro realizado exitosamente");
			return "redirect:/sendmoney";
		}
	}
}
