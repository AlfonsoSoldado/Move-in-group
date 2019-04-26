package com.moveingroup.clients.usuario;

import javax.inject.Named;

import com.moveingroup.dto.ValoracionDto;
import com.moveingroup.rest.ValoracionRestTemplate;

@Named
public class UsuarioValoracionClient {

	private static String RESOURCE_URL = "/usuario/valoracion/";

	public ValoracionDto update(ValoracionDto valoracionDto) {
		ValoracionRestTemplate service = ValoracionRestTemplate.builder().build();
		return service.update(RESOURCE_URL, valoracionDto);
	}
}
