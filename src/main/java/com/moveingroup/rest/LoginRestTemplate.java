package com.moveingroup.rest;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.utils.LoginUsuario;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Builder
@Slf4j
public class LoginRestTemplate {

	private static String CONTEXT_URL = "http://localhost:8083";
	
	public String loginUsuario(String url, LoginUsuario user)  {
		RestTemplate restTemplate = new RestTemplate();
		try {
		    HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    HttpEntity<LoginUsuario> entity = new HttpEntity<>(user, headers);

		    ResponseEntity<String> response = restTemplate.exchange(CONTEXT_URL + url, HttpMethod.POST, entity, String.class);
		    HttpHeaders headersResponse = response.getHeaders();
		    if (!headersResponse.get("Authorization").isEmpty()) {
			return headersResponse.get("Authorization").get(0);
		    } else {
			return null;
		    }

		} catch (HttpClientErrorException e) {
		    log.error(e.getMessage() + e);
		    throw new IllegalArgumentException("Error en el método loginUsuario");
		}
	    }
}
