package com.billeteraVirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billeteraVirtual.entity.Usuario;
import com.billeteraVirtual.repository.UsuarioDaoImpl;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDaoImpl usuarioDaoImpl;
	
	public List<Usuario> obtenerTodosLosUsuarios(){
		return usuarioDaoImpl.obtenerTodosLosUsuarios();
	}
	
	public Usuario obtenerUsuarioPorId(int ID) {
		return usuarioDaoImpl.obtenerUsuarioPorId(ID);
	}
	
	public Usuario obtenerUsuarioPorEmail(String email) {
		return usuarioDaoImpl.obtenerUsuarioPorEmail(email);
	}
	
	public void crearUsuario(Usuario usuario) {
		usuarioDaoImpl.crearUsuario(usuario);;
	}
	
	public void actualizarUsuario(Usuario usuario) {
		usuarioDaoImpl.actualizarUsuario(usuario);;
	}
	
	public boolean validarUsuario(String email, String password) {
		Usuario usuario = usuarioDaoImpl.obtenerUsuarioPorEmail(email);
		System.out.println(usuario);
		if(usuario == null) {
			return false;
		} else if (!(usuario.getContrasena().equals(password))) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
