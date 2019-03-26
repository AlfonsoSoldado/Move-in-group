package com.moveingroup.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.UserAccountDto;

import lombok.Builder;

@Builder
public class UserAccountRestTemplate {

	private static String CONTEXT_URL = "http://localhost:8083";

	public UserAccountDto save(String url, UserAccountDto userAccountDto) {
		UserAccountDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<UserAccountDto> request = new HttpEntity<>(userAccountDto, httpHeaders);

			ret = restTemplate.postForObject(CONTEXT_URL + url + "userAccount", request, UserAccountDto.class);
		} catch (HttpClientErrorException e) {
			// TODO: Controlar excepción
		}
		return ret;
	}
}
