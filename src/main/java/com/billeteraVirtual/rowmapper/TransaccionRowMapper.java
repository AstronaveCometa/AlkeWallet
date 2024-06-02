package com.billeteraVirtual.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.billeteraVirtual.entity.Transaccion;

public class TransaccionRowMapper implements RowMapper<Transaccion>{

	public Transaccion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Transaccion transaccion = new Transaccion();
		transaccion.setId(resultSet.getInt("TRAN_ID"));
		transaccion.setTipo(resultSet.getString("TRAN_TYPE"));
		transaccion.setMonto(resultSet.getLong("TRAN_AMOUNT"));
		transaccion.setFecha(resultSet.getString("TRAN_DATE"));
		transaccion.setIdEmisor(resultSet.getInt("TRAN_USU_ID_SENDER"));
		transaccion.setIdReceptor(resultSet.getInt("TRAN_USU_ID_RECEIVER"));
		return transaccion;
	}
}