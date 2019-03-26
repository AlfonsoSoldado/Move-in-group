package com.moveingroup.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.RolDto;

import lombok.Builder;

@Builder
public class RolRestTemplate {

	private static String CONTEXT_URL = "http://localhost:8083";

	public RolDto findByTipoRol(String url, String tipoRol) {

		RestTemplate restTemplate = new RestTemplate();
		
		RolDto res = new RolDto();
		
		try {
			ResponseEntity<RolDto> result = restTemplate.getForEntity(CONTEXT_URL + url + "findByTipoRol?tipoRol=" + tipoRol , RolDto.class);
			res = result.getBody();
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
}
