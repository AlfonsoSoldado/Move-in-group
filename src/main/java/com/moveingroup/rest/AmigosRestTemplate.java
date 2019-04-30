package com.moveingroup.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.AmigosDto;

import lombok.Builder;

@Builder
public class AmigosRestTemplate {

	private static String CONTEXT_URL = "http://localhost:8083";
	
	public List<AmigosDto> getMisAmigos(String url, Long idUsuario) {
	
		RestTemplate restTemplate = new RestTemplate();
		
		List<AmigosDto> res = new ArrayList<AmigosDto>();
		
		try {
			ResponseEntity<AmigosDto[]> result = restTemplate
					.getForEntity(CONTEXT_URL + url + "getMisAmigos/" + idUsuario, AmigosDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		
		return res;
	}
	
	public List<AmigosDto> getMisPeticionesDeAmistad(String url, Long idUsuario) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		List<AmigosDto> res = new ArrayList<AmigosDto>();
		
		try {
			ResponseEntity<AmigosDto[]> result = restTemplate
					.getForEntity(CONTEXT_URL + url + "getMisPeticionesDeAmistad/" + idUsuario, AmigosDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		
		return res;
	}
	
	public AmigosDto save(String url, AmigosDto amigosDto) {
		AmigosDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<AmigosDto> request = new HttpEntity<>(amigosDto, httpHeaders);

			ret = restTemplate.postForObject(CONTEXT_URL + url + "amigos", request, AmigosDto.class);
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		return ret;
	}
	
	public AmigosDto aceptarPeticion(String url, Long id, AmigosDto amigosDto) {
		AmigosDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<AmigosDto> request = new HttpEntity<>(amigosDto, httpHeaders);

			ResponseEntity<AmigosDto> response = restTemplate.exchange(CONTEXT_URL + url + "aceptarPeticion/" + id, HttpMethod.PUT, request, AmigosDto.class);
			ret = response.getBody();
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		return ret;
	}
	
	public void rechazarPeticion(String url, Long id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.delete(CONTEXT_URL + url + "rechazarPeticion/" + id);
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
	}
}
