package com.moveingroup.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.UsuarioApuntadoDto;

import lombok.Builder;

@Builder
@Named
public class UsuarioApuntadoRestTemplate {

	private static String context_url;

	@Value("${mig.context.url}")
	private void setContext_url(String url) {
		context_url = url;
	}

	public List<UsuarioApuntadoDto> findByActividadId(String url, Long id) {

		RestTemplate restTemplate = new RestTemplate();

		List<UsuarioApuntadoDto> res = new ArrayList<>();

		try {
			ResponseEntity<UsuarioApuntadoDto[]> result = restTemplate
					.getForEntity(context_url + url + "findByActividadId?id=" + id, UsuarioApuntadoDto[].class);
			res = Arrays.asList(result.getBody());
		} catch (HttpClientErrorException e) {
			throw new IllegalArgumentException();
		}

		return res;
	}

	public void delete(String url, Long id) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			restTemplate.delete(context_url + url + id);
		} catch (HttpClientErrorException e) {
			throw new IllegalArgumentException();
		}
	}
	
	public UsuarioApuntadoDto save(String url, UsuarioApuntadoDto usuarioApuntadoDto) {
		UsuarioApuntadoDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<UsuarioApuntadoDto> request = new HttpEntity<>(usuarioApuntadoDto, httpHeaders);

			ret = restTemplate.postForObject(context_url + url + "usuarioApuntado", request, UsuarioApuntadoDto.class);
		} catch (HttpClientErrorException e) {
			throw new IllegalArgumentException();
		}
		return ret;
	}
}
