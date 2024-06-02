package com.billeteraVirtual.repository;

import java.util.List;

import com.billeteraVirtual.entity.Usuario;

public interface UsuarioDao {

	public List<Usuario> obtenerTodosLosUsuarios();
	
	public Usuario obtenerUsuarioPorId(int ID);
	
	public Usuario obtenerUsuarioPorEmail(String email);
	
	public void crearUsuario(Usuario usuario);
	
	public void actualizarUsuario(Usuario usuario);
	
	public void actualizarUsuarioSaldo(Usuario usuario);
	
	public void eliminarUsuarioPorId(int ID);
}
