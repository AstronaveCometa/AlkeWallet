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
public class DepositosController {

	@Autowired
	private TransaccionService transaccionService;
	
	@GetMapping(value = "/deposit")
	public String depositPage(HttpServletRequest request, Model modelo, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
		if(usuarioActual == null) {
			redirectAttributes.addFlashAttribute("mensaje", "Debe iniciar sesión para acceder");
			return "redirect:/login";
		}
		Transaccion transaccionActual = new Transaccion();
		modelo.addAttribute("transaccionActual", transaccionActual);
		modelo.addAttribute("usuarioActual", usuarioActual);
		return "deposit";
	}
	
	@PostMapping(value = "/depositar")
	public String depositar(@ModelAttribute(value = "transaccionActual") Transaccion transaccionActual,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
		transaccionActual.setIdEmisor(usuarioActual.getId());
		transaccionActual.setIdReceptor(usuarioActual.getId());
		transaccionActual.setTipo("deposito");
		transaccionService.insertarTransaccion(transaccionService.depositar(transaccionActual));
		usuarioActual.setSaldo(usuarioActual.getSaldo()+transaccionActual.getMonto());
		session.setAttribute("usuarioActual", usuarioActual);
		redirectAttributes.addFlashAttribute("exito", "Depósito realizado exitosamente");
		return "redirect:/deposit";
	}
			
			
			
			
}
