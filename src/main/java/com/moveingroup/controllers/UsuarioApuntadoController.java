package com.moveingroup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.UsuarioApuntadoDto;
import com.moveingroup.services.UsuarioApuntadoService;

@RestController
@RequestMapping("/usuarioApuntado")
public class UsuarioApuntadoController {
	
	@Autowired
	private UsuarioApuntadoService usuarioApuntadoService;
	
	@GetMapping("/findByActividadId")
	public List<UsuarioApuntadoDto> findByActividadId(@RequestParam("id") Long id) {
		return usuarioApuntadoService.findByActividadId(id);
	}
	
}
