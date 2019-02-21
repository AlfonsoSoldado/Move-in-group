package com.moveingroup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.UsuarioDto;
import com.moveingroup.entities.Usuario;
import com.moveingroup.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/")
	public List<Usuario> findAll() {
		return usuarioService.findAll();
	}
	
	@GetMapping("/mejoresValorados")
	public List<UsuarioDto> mejoresValorados() {
		return usuarioService.getMejoresValorados();
	}
	
}
