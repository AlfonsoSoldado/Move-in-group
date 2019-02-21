package com.moveingroup.clients;

import java.util.List;

import javax.inject.Named;

import com.moveingroup.dto.UsuarioDto;
import com.moveingroup.rest.UsuarioRestTemplate;

@Named
public class UsuarioClient {

	private static String RESOURCE_URL = "/usuario/";

	public List<UsuarioDto> getAll() {
		UsuarioRestTemplate service = UsuarioRestTemplate.builder().build();
		return service.getAll(RESOURCE_URL);
	}
	
	public List<UsuarioDto> getMejoresValorados() {
		UsuarioRestTemplate service = UsuarioRestTemplate.builder().build();
		return service.getMejoresValorados(RESOURCE_URL);
	}
}
