package com.moveingroup.controllers.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.services.UsuarioApuntadoService;

@RestController
@RequestMapping("/empresa/usuarioApuntado")
public class EmpresaUsuarioApuntadoController {

	@Autowired
	private UsuarioApuntadoService usuarioApuntadoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		usuarioApuntadoService.delete(id);
	}
}
