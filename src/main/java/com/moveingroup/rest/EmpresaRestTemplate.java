package com.moveingroup.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
	
	public EmpresaDto save(String url, EmpresaDto empresaDto) {
		EmpresaDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<EmpresaDto> request = new HttpEntity<>(empresaDto, httpHeaders);

			ret = restTemplate.postForObject(CONTEXT_URL + url + "empresa", request, EmpresaDto.class);
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		return ret;
	}
}
