package com.moveingroup.clients.usuario;

import javax.inject.Named;

import com.moveingroup.dto.UsuarioDto;
import com.moveingroup.rest.UsuarioRestTemplate;

@Named
public class UsuarioUsuarioClient {
	
	private static String RESOURCE_URL = "/usr/usuario/";
	
	public UsuarioDto getById(Long id) {
		UsuarioRestTemplate service = UsuarioRestTemplate.builder().build();
		return service.findOne(RESOURCE_URL, id);
	}

	public UsuarioDto update(UsuarioDto usuarioDto) {
		UsuarioRestTemplate service = UsuarioRestTemplate.builder().build();
		return service.update(RESOURCE_URL, usuarioDto.getId(), usuarioDto);
	}
}
