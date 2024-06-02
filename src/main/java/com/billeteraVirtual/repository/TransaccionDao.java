package com.billeteraVirtual.repository;

import java.util.List;

import com.billeteraVirtual.entity.Transaccion;

public interface TransaccionDao {
	
	public List<Transaccion> obtenerTodasLasTransacciones();
	
	public Transaccion obtenerTransaccionPorID(int ID);
	
	public void insertarTransaccion(Transaccion transaccion);
	
	public void eliminarTransaccionPorId(int ID);
}
