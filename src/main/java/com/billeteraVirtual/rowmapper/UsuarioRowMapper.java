package com.billeteraVirtual.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.billeteraVirtual.entity.Usuario;


public class UsuarioRowMapper implements RowMapper<Usuario>{

	public Usuario mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(resultSet.getInt("USU_ID"));
		usuario.setNombre(resultSet.getString("USU_NAME"));
		usuario.setEmail(resultSet.getString("USU_EMAIL"));
		usuario.setContrasena(resultSet.getString("USU_PASSWORD"));
		usuario.setSaldo(resultSet.getLong("USU_SALDO"));
		usuario.setRol(resultSet.getString("USU_ROLE"));
		return usuario;
	}
}
