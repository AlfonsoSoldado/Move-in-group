package com.moveingroup.clients;

import javax.inject.Named;

import com.moveingroup.dto.ValoracionDto;
import com.moveingroup.rest.ValoracionRestTemplate;

@Named
public class ValoracionClient {

	private static String RESOURCE_URL = "/valoracion/";

	public ValoracionDto save(ValoracionDto valoracionDto) {
		ValoracionRestTemplate service = ValoracionRestTemplate.builder().build();
		return service.save(RESOURCE_URL, valoracionDto);
	}
	
	public long countByMedalla(String medalla) {
		ValoracionRestTemplate service = ValoracionRestTemplate.builder().build();
		return service.countByMedalla(RESOURCE_URL, medalla);
	}
}
