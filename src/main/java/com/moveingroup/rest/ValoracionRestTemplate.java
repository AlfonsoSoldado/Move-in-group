package com.moveingroup.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.ValoracionDto;

import lombok.Builder;

@Builder
public class ValoracionRestTemplate {

	private static String CONTEXT_URL = "http://localhost:8083";

	public ValoracionDto save(String url, ValoracionDto valoracionDto) {
		ValoracionDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<ValoracionDto> request = new HttpEntity<>(valoracionDto, httpHeaders);

			ret = restTemplate.postForObject(CONTEXT_URL + url + "valoracion", request, ValoracionDto.class);
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		return ret;
	}
}
