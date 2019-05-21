package com.moveingroup.controllers.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.entities.Usuario;
import com.moveingroup.services.UsuarioService;

@RestController
@RequestMapping("/usr/usuario")
public class UsuarioUsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/findOne/{id}")
	public Usuario findOne(@PathVariable Long id) {
		return usuarioService.findOne(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUsuario(@PathVariable Long id) {
		usuarioService.deleteUsuario(id);
	}
}
