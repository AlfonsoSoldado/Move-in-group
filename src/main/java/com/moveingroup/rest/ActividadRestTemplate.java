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

import com.moveingroup.dto.ActividadDto;

import lombok.Builder;

@Builder
public class ActividadRestTemplate {

	private static String CONTEXT_URL = "http://localhost:8083";

	public List<ActividadDto> getAll(String url) {

		RestTemplate restTemplate = new RestTemplate();

		List<ActividadDto> res = new ArrayList<ActividadDto>();

		try {
			ResponseEntity<ActividadDto[]> result = restTemplate.getForEntity(CONTEXT_URL + url, ActividadDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}

		return res;
	}
	
	public List<ActividadDto> getAllByEmpresas(String url) {

		RestTemplate restTemplate = new RestTemplate();

		List<ActividadDto> res = new ArrayList<ActividadDto>();

		try {
			ResponseEntity<ActividadDto[]> result = restTemplate.getForEntity(CONTEXT_URL + url, ActividadDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}

		return res;
	}

	public List<ActividadDto> findByUsuarioId(String url, Long id) {

		RestTemplate restTemplate = new RestTemplate();

		List<ActividadDto> res = new ArrayList<ActividadDto>();

		try {
			ResponseEntity<ActividadDto[]> result = restTemplate
					.getForEntity(CONTEXT_URL + url + "findByUsuarioId/" + id, ActividadDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}

		return res;
	}
	
	public List<ActividadDto> findActividadesTerminadas(String url, Long id) {

		RestTemplate restTemplate = new RestTemplate();

		List<ActividadDto> res = new ArrayList<ActividadDto>();

		try {
			ResponseEntity<ActividadDto[]> result = restTemplate
					.getForEntity(CONTEXT_URL + url + "findActividadesTerminadas/" + id, ActividadDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}

		return res;
	}
	
	public List<ActividadDto> findByEmpresaId(String url, Long id) {

		RestTemplate restTemplate = new RestTemplate();

		List<ActividadDto> res = new ArrayList<ActividadDto>();

		try {
			ResponseEntity<ActividadDto[]> result = restTemplate
					.getForEntity(CONTEXT_URL + url + "findByEmpresaId/" + id, ActividadDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}

		return res;
	}

	public ActividadDto findById(String url, Long id) {

		RestTemplate restTemplate = new RestTemplate();

		ActividadDto res = new ActividadDto();

		try {
			ResponseEntity<ActividadDto> result = restTemplate.getForEntity(CONTEXT_URL + url + "findById/" + id,
					ActividadDto.class);
			res = result.getBody();
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}

		return res;
	}

	public ActividadDto save(String url, ActividadDto actividadDto) {
		ActividadDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<ActividadDto> request = new HttpEntity<>(actividadDto, httpHeaders);

			ret = restTemplate.postForObject(CONTEXT_URL + url + "actividad", request, ActividadDto.class);
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		return ret;
	}

	public void cancelarActividad(String url, Long id, String auth) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.put(CONTEXT_URL + url + "cancelarActividad/" + id, auth);
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
	}

}
