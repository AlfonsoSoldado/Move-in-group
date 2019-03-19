package com.moveingroup.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.EmpresaDto;

import lombok.Builder;

@Builder
public class EmpresaRestTemplate {

	private static String CONTEXT_URL = "http://localhost:8083";
	
	public EmpresaDto findOne(String url, Long id) {

		RestTemplate restTemplate = new RestTemplate();
		
		EmpresaDto res = new EmpresaDto();
		
		try {
			ResponseEntity<EmpresaDto> result = restTemplate.getForEntity(CONTEXT_URL + url + "findOne?id=" + id , EmpresaDto.class);
			res = result.getBody();
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
}
