package com.moveingroup.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	public List<EmpresaDto> getAll(String url) {

		RestTemplate restTemplate = new RestTemplate();
		
		List<EmpresaDto> res = new ArrayList<EmpresaDto>();
		
		try {
			ResponseEntity<EmpresaDto[]> result = restTemplate.getForEntity(CONTEXT_URL + url, EmpresaDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
}
