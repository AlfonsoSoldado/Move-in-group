package com.moveingroup.clients;

import java.util.List;

import javax.inject.Named;

import com.moveingroup.dto.EmpresaDto;
import com.moveingroup.rest.EmpresaRestTemplate;

@Named
public class EmpresaClient {

	private static String RESOURCE_URL = "/empresa/";
	
	public List<EmpresaDto> findAll() {
		EmpresaRestTemplate service = EmpresaRestTemplate.builder().build();
		return service.getAll(RESOURCE_URL);
	}
}
