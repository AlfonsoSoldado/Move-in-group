package com.moveingroup.rest;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.ValoracionDto;

import lombok.Builder;

@Builder
@Named
public class ValoracionRestTemplate {

	private static String context_url;

	@Value("${mig.context.url}")
	private void setContext_url(String url) {
		context_url = url;
	}

	public ValoracionDto save(String url, ValoracionDto valoracionDto) {
		ValoracionDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<ValoracionDto> request = new HttpEntity<>(valoracionDto, httpHeaders);

			ret = restTemplate.postForObject(context_url + url + "valoracion", request, ValoracionDto.class);
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		return ret;
	}
	
	public ValoracionDto update(String url, Long id, ValoracionDto valoracionDto) {
		ValoracionDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<ValoracionDto> request = new HttpEntity<>(valoracionDto, httpHeaders);

			ResponseEntity<ValoracionDto> response = restTemplate.exchange(context_url + url + "update/" + id, HttpMethod.PUT, request, ValoracionDto.class);
			ret = response.getBody();
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		return ret;
	}
	
	public Long countByMedalla(String url, String medalla) {

		RestTemplate restTemplate = new RestTemplate();
		
		long res = 0;
		
		try {
			ResponseEntity<Long> result = restTemplate.getForEntity(context_url + url + "countByMedalla/" + medalla, Long.class);
			res = result.getBody();
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
}
