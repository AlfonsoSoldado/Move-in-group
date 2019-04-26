package com.moveingroup.controllers.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.UsuarioApuntadoDto;
import com.moveingroup.services.UsuarioApuntadoService;

@RestController
@RequestMapping("/usuario/usuarioApuntado")
public class UsuarioUsuarioApuntadoController {

	@Autowired
	private UsuarioApuntadoService usuarioApuntadoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		usuarioApuntadoService.delete(id);
	}
	
	@RequestMapping(value = "usuarioApuntado", method = RequestMethod.POST)
	public UsuarioApuntadoDto save(@RequestBody UsuarioApuntadoDto usuarioApuntadoDto) {
		return usuarioApuntadoService.save(usuarioApuntadoDto);
	}
}
