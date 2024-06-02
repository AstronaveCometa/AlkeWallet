package com.billeteraVirtual.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billeteraVirtual.entity.Usuario;
import com.billeteraVirtual.rowmapper.UsuarioRowMapper;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	@Autowired
	private final JdbcTemplate jdbcTemplate;

	public UsuarioDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {
		String sql = "SELECT * FROM billeteraVirtual.Usuarios;";
		return jdbcTemplate.query(sql, new UsuarioRowMapper());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("deprecation")
	@Override
	public Usuario obtenerUsuarioPorId(int ID) {
		String sql = "SELECT * FROM billeteraVirtual.Usuarios WHERE USU_ID = ?";
		List<Usuario> resultado = jdbcTemplate.query(sql, new Object[]{ID}, new UsuarioRowMapper());
		if(resultado.isEmpty()) {
			return null;
		} else {
		Usuario usuario = resultado.get(0);
		return usuario;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Usuario obtenerUsuarioPorEmail(String email) {
		String sql = "SELECT * FROM billeteraVirtual.Usuarios WHERE USU_EMAIL = ?;";
		@SuppressWarnings("deprecation")
		List<Usuario> resultado = jdbcTemplate.query(sql, new Object[]{email}, new UsuarioRowMapper());
		if(resultado.isEmpty()) {
			return null;
		} else {
		Usuario usuario = resultado.get(0);
		return usuario;
		}
	}
	
	@Transactional
	@Override
	public void crearUsuario(Usuario usuario) {
		String sql = "INSERT INTO billeteraVirtual.Usuarios (USU_NAME, USU_EMAIL, USU_PASSWORD, USU_SALDO, USU_ROLE) VALUE (?, ?, ?, 0, 'usuario')";
		jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getContrasena());
	}

	@Transactional
	@Override
	public void actualizarUsuario(Usuario usuario) {
		String sql = "UPDATE billeteraVirtual.Usuarios SET USU_NAME = ?, USU_EMAIL = ?, USU_PASSWORD = ? WHERE USU_ID = ?;";
		jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getContrasena(), usuario.getId());
	}
	
	@Transactional
	@Override
	public void actualizarUsuarioSaldo(Usuario usuario) {
		String sql = "UPDATE billeteraVirtual.Usuarios SET USU_SALDO = ? WHERE USU_ID = ?;";
		jdbcTemplate.update(sql, usuario.getSaldo(), usuario.getId());
	}

	@Transactional
	@Override
	public void eliminarUsuarioPorId(int ID) {
		String sql = "DELETE FROM billeteraVirtual.Usuarios WHERE USU_ID = ? ";
		jdbcTemplate.update(sql, ID);
	}



}
