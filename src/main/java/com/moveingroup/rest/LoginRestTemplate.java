package com.moveingroup.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.UserAccountDto;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Builder
@Slf4j
@Service
public class LoginRestTemplate {

	private static String CONTEXT_URL = "http://localhost:8083";

	public String loginUsuario(String url, UserAccountDto user) {
		String ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<UserAccountDto> request = new HttpEntity<>(user, httpHeaders);

			ret = restTemplate.postForObject(CONTEXT_URL + url + "loginUsuario", request, String.class);

			return ret;
		} catch (HttpClientErrorException e) {
			log.error(e.getMessage() + e);
			throw new IllegalArgumentException("Error en el método loginUsuario");
		}
	}
	
	public String loginEmpresa(String url, UserAccountDto user) {
		String ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<UserAccountDto> request = new HttpEntity<>(user, httpHeaders);

			ret = restTemplate.postForObject(CONTEXT_URL + url + "loginEmpresa", request, String.class);

			return ret;
		} catch (HttpClientErrorException e) {
			log.error(e.getMessage() + e);
			throw new IllegalArgumentException("Error en el método loginEmpresa");
		}
	}
	
	public String loginAdmin(String url, UserAccountDto user) {
		String ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<UserAccountDto> request = new HttpEntity<>(user, httpHeaders);

			ret = restTemplate.postForObject(CONTEXT_URL + url + "loginAdmin", request, String.class);

			return ret;
		} catch (HttpClientErrorException e) {
			log.error(e.getMessage() + e);
			throw new IllegalArgumentException("Error en el método loginEmpresa");
		}
	}
}
