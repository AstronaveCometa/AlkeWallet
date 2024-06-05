package com.billeteraVirtual.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.billeteraVirtual.entity.Transaccion;
import com.billeteraVirtual.entity.Usuario;
import com.billeteraVirtual.service.TransaccionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HistorialController {

	@Autowired
	private TransaccionService transaccionService;
	
	@GetMapping(value = "/history")
	public String historyPage(HttpServletRequest request, Model modelo, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		Usuario usuarioActual = (Usuario) session.getAttribute("usuarioActual");
		if(usuarioActual == null) {
			redirectAttributes.addFlashAttribute("mensaje", "Debe iniciar sesión para acceder");
			return "redirect:/login";
		}
		List<Transaccion> todasLasTransferencias = transaccionService.obtenerTransaccionesPorIdUsuario(usuarioActual.getId());
		modelo.addAttribute("todasLasTransferencias", todasLasTransferencias);
		modelo.addAttribute("usuarioActual", usuarioActual);
		return "history";
	}
}
