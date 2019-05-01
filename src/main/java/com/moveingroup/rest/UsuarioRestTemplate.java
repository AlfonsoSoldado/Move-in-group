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

import com.moveingroup.dto.UsuarioDto;

import lombok.Builder;

@Builder
public class UsuarioRestTemplate {
	
	private static String CONTEXT_URL = "http://localhost:8083";

	public List<UsuarioDto> getAll(String url) {

		RestTemplate restTemplate = new RestTemplate();
		
		List<UsuarioDto> res = new ArrayList<UsuarioDto>();
		
		try {
			ResponseEntity<UsuarioDto[]> result = restTemplate.getForEntity(CONTEXT_URL + url, UsuarioDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
	
	public List<UsuarioDto> getMejoresValorados(String url) {

		RestTemplate restTemplate = new RestTemplate();
		
		List<UsuarioDto> res = new ArrayList<UsuarioDto>();
		
		try {
			ResponseEntity<UsuarioDto[]> result = restTemplate.getForEntity(CONTEXT_URL + url + "mejoresValorados", UsuarioDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
	
	public UsuarioDto findOne(String url, Long id) {

		RestTemplate restTemplate = new RestTemplate();
		
		UsuarioDto res = new UsuarioDto();
		
		try {
			ResponseEntity<UsuarioDto> result = restTemplate.getForEntity(CONTEXT_URL + url + "findOne?id=" + id , UsuarioDto.class);
			res = result.getBody();
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
	
	public UsuarioDto save(String url, UsuarioDto usuarioDto) {
		UsuarioDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<UsuarioDto> request = new HttpEntity<>(usuarioDto, httpHeaders);

			ret = restTemplate.postForObject(CONTEXT_URL + url + "usuario", request, UsuarioDto.class);
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		return ret;
	}
	
	public Long usuarioCount(String url) {

		RestTemplate restTemplate = new RestTemplate();
		
		long res = 0;
		
		try {
			ResponseEntity<Long> result = restTemplate.getForEntity(CONTEXT_URL + url + "usuarioCount", Long.class);
			res = result.getBody();
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
}
