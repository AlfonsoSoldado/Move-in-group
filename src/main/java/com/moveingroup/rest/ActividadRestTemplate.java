package com.moveingroup.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.ActividadDto;

import lombok.Builder;

@Builder
public class ActividadRestTemplate {

	private static String CONTEXT_URL = "http://localhost:8083";

	public List<ActividadDto> getAll(String url) {

		RestTemplate restTemplate = new RestTemplate();

		List<ActividadDto> res = new ArrayList<ActividadDto>();

		try {
			ResponseEntity<ActividadDto[]> result = restTemplate.getForEntity(CONTEXT_URL + url,
					ActividadDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}

		return res;
	}

	public ActividadDto save(String url, ActividadDto actividadDto) {
		ActividadDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<ActividadDto> request = new HttpEntity<>(actividadDto, headers);
			
			ResponseEntity<ActividadDto> response = restTemplate
					  .exchange(url + "actividad", HttpMethod.POST, request, ActividadDto.class);

			ret = response.getBody();
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		return ret;
	}
	
	
}
