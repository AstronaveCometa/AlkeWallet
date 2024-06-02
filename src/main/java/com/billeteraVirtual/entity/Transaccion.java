package com.billeteraVirtual.entity;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Transaccion{

	@Id
	private int id;
	
	@Column
	private String tipo;
	
	@Column
	private Long monto;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private String fecha;
	
	@Column
	private int idEmisor;
	
	@Column
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
