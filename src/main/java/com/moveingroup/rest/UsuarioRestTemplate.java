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

import com.moveingroup.dto.UsuarioDto;

import lombok.Builder;

@Builder
@Named
public class UsuarioRestTemplate {
	
	private static String context_url;

	@Value("${mig.context.url}")
	private void setContext_url(String url) {
		context_url = url;
	}

	public List<UsuarioDto> getAll(String url) {

		RestTemplate restTemplate = new RestTemplate();
		
		List<UsuarioDto> res = new ArrayList<UsuarioDto>();
		
		try {
			ResponseEntity<UsuarioDto[]> result = restTemplate.getForEntity(context_url + url, UsuarioDto[].class);
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
			ResponseEntity<UsuarioDto[]> result = restTemplate.getForEntity(context_url + url + "mejoresValorados", UsuarioDto[].class);
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
			ResponseEntity<UsuarioDto> result = restTemplate.getForEntity(context_url + url + "findOne/" + id , UsuarioDto.class);
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

			ret = restTemplate.postForObject(context_url + url + "usuario", request, UsuarioDto.class);
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		return ret;
	}
	
	public UsuarioDto update(String url, Long id, UsuarioDto usuarioDto) {
		UsuarioDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<UsuarioDto> request = new HttpEntity<>(usuarioDto, httpHeaders);

			ResponseEntity<UsuarioDto> response = restTemplate.exchange(context_url + url + "update/" + id,
					HttpMethod.PUT, request, UsuarioDto.class);
			ret = response.getBody();
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		return ret;
	}
	
	public Long usuarioCount(String url) {

		RestTemplate restTemplate = new RestTemplate();
		
		long res = 0;
		
		try {
			ResponseEntity<Long> result = restTemplate.getForEntity(context_url + url + "usuarioCount", Long.class);
			res = result.getBody();
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
}
