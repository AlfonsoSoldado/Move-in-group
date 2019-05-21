package com.moveingroup.rest;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.moveingroup.dto.RolDto;

import lombok.Builder;

@Builder
@Named
public class RolRestTemplate {

	private static String context_url;

	@Value("${mig.context.url}")
	private void setContext_url(String url) {
		context_url = url;
	}

	public RolDto findByTipoRol(String url, String tipoRol) {

		RestTemplate restTemplate = new RestTemplate();
		
		RolDto res = new RolDto();
		
		try {
			ResponseEntity<RolDto> result = restTemplate.getForEntity(context_url + url + "findByTipoRol?tipoRol=" + tipoRol , RolDto.class);
			res = result.getBody();
		} catch (HttpClientErrorException e) {
			//TODO: Controlar excepción
		}
		
		return res;
	}
}
