package com.moveingroup.clients.empresa;

import javax.inject.Named;

import com.moveingroup.rest.UsuarioApuntadoRestTemplate;

@Named
public class EmpresaUsuarioApuntadoClient {

private static String RESOURCE_URL = "/emp/usuarioApuntado/";

	
	public void delete(Long id) {
		UsuarioApuntadoRestTemplate service = UsuarioApuntadoRestTemplate.builder().build();
		service.delete(RESOURCE_URL, id);
	}
}
