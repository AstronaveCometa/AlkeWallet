package com.billeteraVirtual.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billeteraVirtual.entity.Transaccion;
import com.billeteraVirtual.rowmapper.TransaccionRowMapper;

@Repository
public class TransaccionDaoImpl implements TransaccionDao {

	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	public TransaccionDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Transaccion> obtenerTodasLasTransacciones() {
		String sql = "SELECT * FROM billeteraVirtual.Transacciones";
		return jdbcTemplate.query(sql, new TransaccionRowMapper());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("deprecation")
	@Override
	public Transaccion obtenerTransaccionPorID(int ID) {
		String sql = "SELECT * FROM billeteraVirtual.Transacciones Where TRAN_ID = ?";
		List<Transaccion> resultado = jdbcTemplate.query(sql, new Object[]{ID}, new TransaccionRowMapper());
		if(resultado.isEmpty()) {
			return null;
		} else {
		Transaccion transaccion = resultado.get(0);
		return transaccion;
		}
	}

	@Transactional
	@Override
	public void insertarTransaccion(Transaccion transaccion) {
		String sql = "INSERT INTO billeteraVirtual.Transacciones (TRAN_TYPE, TRAN_AMOUNT, TRAN_DATE, TRAN_USU_ID_SENDER, TRAN_USU_ID_RECEIVER) VALUES (?, ?, CURRENT_TIMESTAMP, ?, ?)";
		jdbcTemplate.update(sql, transaccion.getTipo(), transaccion.getMonto(), transaccion.getIdEmisor(), transaccion.getIdReceptor());
	}
	
	@Transactional
	@Override
	public void eliminarTransaccionPorId(int ID) {
		String sql = "DELETE FROM billeteraVirtual.Transacciones WHERE TRAN_ID = ? ";
		jdbcTemplate.update(sql, ID);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Transaccion> obtenerTransaccionesPorIdUsuario(int ID) {
		String sql = "SELECT * FROM Transacciones WHERE TRAN_USU_ID_SENDER = ? order by TRAN_DATE;";
		return jdbcTemplate.query(sql, new Object[]{ID}, new TransaccionRowMapper());
	}

}
