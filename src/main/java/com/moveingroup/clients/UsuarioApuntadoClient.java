package com.moveingroup.clients;

import java.util.List;

import javax.inject.Named;

import com.moveingroup.dto.UsuarioApuntadoDto;
import com.moveingroup.rest.UsuarioApuntadoRestTemplate;

@Named
public class UsuarioApuntadoClient {

	private static String RESOURCE_URL = "/usuarioApuntado/";

	
	public List<UsuarioApuntadoDto> findByActividadId(Long id) {
		UsuarioApuntadoRestTemplate service = UsuarioApuntadoRestTemplate.builder().build();
		return service.findByActividadId(RESOURCE_URL, id);
	}
}
