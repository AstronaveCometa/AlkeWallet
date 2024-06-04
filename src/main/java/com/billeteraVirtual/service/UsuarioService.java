package com.billeteraVirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billeteraVirtual.entity.Usuario;
import com.billeteraVirtual.repository.UsuarioDao;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	public List<Usuario> obtenerTodosLosUsuarios(){
		return usuarioDao.obtenerTodosLosUsuarios();
	}
	
	public Usuario obtenerUsuarioPorId(int ID) {
		return usuarioDao.obtenerUsuarioPorId(ID);
	}
	
	public Usuario obtenerUsuarioPorEmail(String email) {
		return usuarioDao.obtenerUsuarioPorEmail(email);
	}
	
	public void crearUsuario(Usuario usuario) {
		usuarioDao.crearUsuario(usuario);;
	}
	
	public void actualizarUsuario(Usuario usuario) {
		usuarioDao.actualizarUsuario(usuario);;
	}
	
	public boolean validarUsuario(String email, String password) {
		Usuario usuario = usuarioDao.obtenerUsuarioPorEmail(email);
		if(usuario == null) {
			return false;
		} else if (!(usuario.getContrasena().equals(password))) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean existeUsuario(String email) {
		Usuario usuario = usuarioDao.obtenerUsuarioPorEmail(email);
		if(usuario == null) {
			return false;
		} else {
			return true;
		}
	}
	
	
}
