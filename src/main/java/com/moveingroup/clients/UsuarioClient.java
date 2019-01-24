package com.moveingroup.clients;

import java.util.List;

import javax.inject.Named;

import com.moveingroup.dto.UsuarioDto;
import com.moveingroup.rest.UsuarioRestTemplate;

@Named
public class UsuarioClient {

	private static String RESOURCE_URL = "/usuario";

	public List<UsuarioDto> getAll() {
		UsuarioRestTemplate service = new UsuarioRestTemplate();
		return service.getAll(RESOURCE_URL);
	}
	
	public UsuarioDto getById(Long id) {
		UsuarioRestTemplate service = new UsuarioRestTemplate();
		return service.findOne(RESOURCE_URL, id);
	}
}
