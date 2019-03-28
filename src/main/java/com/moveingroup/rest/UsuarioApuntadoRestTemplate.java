package com.moveingroup.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.UsuarioApuntadoDto;

import lombok.Builder;

@Builder
public class UsuarioApuntadoRestTemplate {
	
	private static String CONTEXT_URL = "http://localhost:8083";

	public List<UsuarioApuntadoDto> findByActividadId(String url, Long id) {

		RestTemplate restTemplate = new RestTemplate();
		
		List<UsuarioApuntadoDto> res = new ArrayList<>();
		
		try {
			ResponseEntity<UsuarioApuntadoDto[]> result = restTemplate.getForEntity(CONTEXT_URL + url + "findByActividadId?id=" + id , UsuarioApuntadoDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
}
