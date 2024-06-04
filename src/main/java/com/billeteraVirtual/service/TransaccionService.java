package com.billeteraVirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billeteraVirtual.entity.Transaccion;
import com.billeteraVirtual.entity.Usuario;
import com.billeteraVirtual.repository.TransaccionDao;
import com.billeteraVirtual.repository.UsuarioDao;

@Service
public class TransaccionService {

	@Autowired
	private TransaccionDao transaccionDao;
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	public List<Transaccion> obtenerTodasLasTransacciones(){
		return transaccionDao.obtenerTodasLasTransacciones();
	}
	
	public Transaccion obtenerTransaccionPorID(int ID) {
		return transaccionDao.obtenerTransaccionPorID(ID);
	}
	
	public void insertarTransaccion(Transaccion transaccion) {
		transaccionDao.insertarTransaccion(transaccion);
	}
	
	public void eliminarTransaccionPorId(int ID) {
		transaccionDao.eliminarTransaccionPorId(ID);
	}
	
	public Transaccion depositar(Transaccion transaccion) {
		transaccion.setTipo("deposito");
		Usuario usuarioReceptor = usuarioDao.obtenerUsuarioPorId(transaccion.getIdReceptor());
		usuarioReceptor.setSaldo(usuarioReceptor.getSaldo() + transaccion.getMonto());
		usuarioDao.actualizarUsuarioSaldo(usuarioReceptor);
		return transaccion;
	}
	
	public Transaccion retiro(Transaccion transaccion) {
		Usuario usuarioEmisor = usuarioDao.obtenerUsuarioPorId(transaccion.getIdEmisor());
		Long saldoUsuarioEmisor = usuarioEmisor.getSaldo();
		if(saldoUsuarioEmisor<transaccion.getMonto()) {
			return null;
		} else {
			usuarioEmisor.setSaldo(usuarioEmisor.getSaldo() - transaccion.getMonto());
			usuarioDao.actualizarUsuarioSaldo(usuarioEmisor);
			return transaccion;
		}
	}
	
	public Transaccion transferencia(Long monto, int IdEmisor, int IdReceptor) {
		Transaccion transaccion = new Transaccion("transferencia", monto, IdEmisor, IdReceptor);
		Usuario usuarioEmisor = usuarioDao.obtenerUsuarioPorId(IdEmisor);
		Usuario usuarioReceptor = usuarioDao.obtenerUsuarioPorId(IdReceptor);
		Long saldoUsuarioEmisor = usuarioEmisor.getSaldo();
		if(saldoUsuarioEmisor<monto) {
			return null;
		} else {
			usuarioEmisor.setSaldo(usuarioEmisor.getSaldo() - monto);
			usuarioReceptor.setSaldo(usuarioReceptor.getSaldo() + monto);
			usuarioDao.actualizarUsuarioSaldo(usuarioEmisor);
			usuarioDao.actualizarUsuarioSaldo(usuarioReceptor);
			return transaccion;
		}
	}
}
