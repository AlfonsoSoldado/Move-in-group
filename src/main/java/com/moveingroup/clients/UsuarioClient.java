package com.moveingroup.clients;

import java.util.List;

import javax.inject.Named;

import com.moveingroup.dto.UsuarioDto;
import com.moveingroup.rest.UsuarioRestTemplate;

@Named
public class UsuarioClient {

	private static String RESOURCE_URL = "/usuarioAnonimo/";

	public List<UsuarioDto> getAll() {
		UsuarioRestTemplate service = UsuarioRestTemplate.builder().build();
		return service.getAll(RESOURCE_URL);
	}
	
	public UsuarioDto findOne(Long id) {
		UsuarioRestTemplate service = UsuarioRestTemplate.builder().build();
		return service.findOne(RESOURCE_URL, id);
	}
	
	public List<UsuarioDto> getMejoresValorados() {
		UsuarioRestTemplate service = UsuarioRestTemplate.builder().build();
		return service.getMejoresValorados(RESOURCE_URL);
	}
	
	public UsuarioDto save(UsuarioDto usuarioDto) {
		UsuarioRestTemplate service = UsuarioRestTemplate.builder().build();
		return service.save(RESOURCE_URL, usuarioDto);
	}
	
	public long usuarioCount() {
		UsuarioRestTemplate service = UsuarioRestTemplate.builder().build();
		return service.usuarioCount(RESOURCE_URL);
	}
}
