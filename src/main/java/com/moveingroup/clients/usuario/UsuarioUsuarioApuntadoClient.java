package com.moveingroup.clients.usuario;

import javax.inject.Named;

import com.moveingroup.rest.UsuarioApuntadoRestTemplate;

@Named
public class UsuarioUsuarioApuntadoClient {

private static String RESOURCE_URL = "/usuario/usuarioApuntado/";

	
	public void delete(Long id) {
		UsuarioApuntadoRestTemplate service = UsuarioApuntadoRestTemplate.builder().build();
		service.delete(RESOURCE_URL, id);
	}
}
