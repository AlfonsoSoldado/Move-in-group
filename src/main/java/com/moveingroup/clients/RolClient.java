package com.moveingroup.clients;

import javax.inject.Named;

import com.moveingroup.dto.RolDto;
import com.moveingroup.rest.RolRestTemplate;

@Named
public class RolClient {

	private static String RESOURCE_URL = "/rol/";

	public RolDto findByTipoRol(String tipoRol) {
		RolRestTemplate service = RolRestTemplate.builder().build();
		return service.findByTipoRol(RESOURCE_URL, tipoRol);
	}
}
