package com.moveingroup.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
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
@Named
public class AmigosRestTemplate {

	private static String context_url;

	@Value("${mig.context.url}")
	private void setContext_url(String url) {
		context_url = url;
	}

	public List<AmigosDto> getMisAmigos(String url, Long idUsuario) {

		RestTemplate restTemplate = new RestTemplate();

		List<AmigosDto> res = new ArrayList<AmigosDto>();

		try {
			ResponseEntity<AmigosDto[]> result = restTemplate
					.getForEntity(context_url + url + "getMisAmigos/" + idUsuario, AmigosDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			throw new IllegalArgumentException();
		}

		return res;
	}

	public List<AmigosDto> getMisPeticionesDeAmistad(String url, Long idUsuario) {

		RestTemplate restTemplate = new RestTemplate();

		List<AmigosDto> res = new ArrayList<AmigosDto>();

		try {
			ResponseEntity<AmigosDto[]> result = restTemplate
					.getForEntity(context_url + url + "getMisPeticionesDeAmistad/" + idUsuario, AmigosDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			throw new IllegalArgumentException();
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

			ret = restTemplate.postForObject(context_url + url + "amigos", request, AmigosDto.class);
		} catch (HttpClientErrorException e) {
			throw new IllegalArgumentException();
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

			ResponseEntity<AmigosDto> response = restTemplate.exchange(context_url + url + "aceptarPeticion/" + id,
					HttpMethod.PUT, request, AmigosDto.class);
			ret = response.getBody();
		} catch (HttpClientErrorException e) {
			throw new IllegalArgumentException();
		}
		return ret;
	}

	public void rechazarPeticion(String url, Long id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.delete(context_url + url + "rechazarPeticion/" + id);
		} catch (HttpClientErrorException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public void delete(String url, Long id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.delete(context_url + url + id);
		} catch (HttpClientErrorException e) {
			throw new IllegalArgumentException();
		}
	}
}
