package com.billeteraVirtual.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Transaccion{

	@Id
	private int id;
	private String tipo;
	private Long monto;
	private String fecha;
	private int idEmisor;
	private int idReceptor;
	
	public Transaccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaccion(int id, String tipo, Long monto, String fecha, int idEmisor, int idReceptor) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.monto = monto;
		this.fecha = fecha;
		this.idEmisor = idEmisor;
		this.idReceptor = idReceptor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getIdEmisor() {
		return idEmisor;
	}

	public void setIdEmisor(int idEmisor) {
		this.idEmisor = idEmisor;
	}

	public int getIdReceptor() {
		return idReceptor;
	}

	public void setIdReceptor(int idReceptor) {
		this.idReceptor = idReceptor;
	}
	
	
}
