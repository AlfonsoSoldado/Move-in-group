package com.moveingroup.rest;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.UserAccountDto;

import lombok.Builder;

@Builder
@Named
public class UserAccountRestTemplate {

	private static String context_url;

	@Value("${mig.context.url}")
	private void setContext_url(String url) {
		context_url = url;
	}

	public UserAccountDto save(String url, UserAccountDto userAccountDto) {
		UserAccountDto ret = null;

		RestTemplate restTemplate = new RestTemplate();
		try {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("Content-Type", "application/json");

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			HttpEntity<UserAccountDto> request = new HttpEntity<>(userAccountDto, httpHeaders);

			ret = restTemplate.postForObject(context_url + url + "userAccount", request, UserAccountDto.class);
		} catch (HttpClientErrorException e) {
			throw new IllegalArgumentException();
		}
		return ret;
	}
	
	public UserAccountDto findByUsername(String url, String username) {

		RestTemplate restTemplate = new RestTemplate();
		
		UserAccountDto res = new UserAccountDto();
		
		try {
			ResponseEntity<UserAccountDto> result = restTemplate.getForEntity(context_url + url + "findByUsername/" + username , UserAccountDto.class);
			res = result.getBody();
		} catch (HttpClientErrorException e) {
			throw new IllegalArgumentException();
		}
		
		return res;
	}
}
