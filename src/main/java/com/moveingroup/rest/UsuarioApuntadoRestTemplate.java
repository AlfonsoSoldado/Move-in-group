package com.moveingroup.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.UsuarioApuntadoDto;

import lombok.Builder;

@Builder
public class UsuarioApuntadoRestTemplate {
	
	private static String CONTEXT_URL = "http://localhost:8083";

	public UsuarioApuntadoDto findByActividadId(String url, Long id) {

		RestTemplate restTemplate = new RestTemplate();
		
		UsuarioApuntadoDto res = new UsuarioApuntadoDto();
		
		try {
			ResponseEntity<UsuarioApuntadoDto> result = restTemplate.getForEntity(CONTEXT_URL + url + "findOne?id=" + id , UsuarioApuntadoDto.class);
			res = result.getBody();
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
}
