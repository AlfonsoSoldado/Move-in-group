package com.moveingroup.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.ActividadDto;

public class ActividadRestTemplate {
	
	private static String CONTEXT_URL = "http://localhost:8083";

	public List<ActividadDto> getAll(String url) {

		RestTemplate restTemplate = new RestTemplate();
		
		List<ActividadDto> res = new ArrayList<ActividadDto>();
		
		try {
			ResponseEntity<ActividadDto[]> result = restTemplate.getForEntity(CONTEXT_URL + url + "/", ActividadDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
}
