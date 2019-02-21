package com.moveingroup.clients.usuario;

import javax.inject.Named;

import com.moveingroup.dto.UsuarioDto;
import com.moveingroup.rest.UsuarioRestTemplate;

@Named
public class UsuarioUsuarioClient {
	
	private static String RESOURCE_URL = "/usuario/usuario/";
	
	public UsuarioDto getById(Long id) {
		UsuarioRestTemplate service = UsuarioRestTemplate.builder().build();
		return service.findOne(RESOURCE_URL, id);
	}
}
