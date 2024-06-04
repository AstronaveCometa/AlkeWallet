package com.billeteraVirtual.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mostrar {

	/*
	 * Este controller es de referencia, no se utilizará en la app
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping(value = "/mostrar")
	public List<Usuario> mostrar(){
		return usuarioService.obtenerTodosLosUsuarios();
	}
	
	@PostMapping(value = "/crear")
	public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario){
		usuarioService.crearUsuario(usuario);
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}
	*/
}
