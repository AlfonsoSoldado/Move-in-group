package com.moveingroup.clients;

import java.util.List;

import javax.inject.Named;

import com.moveingroup.dto.EmpresaDto;
import com.moveingroup.rest.EmpresaRestTemplate;

@Named
public class EmpresaClient {

	private static String RESOURCE_URL = "/empresaAnonima/";
	
	public List<EmpresaDto> findAll() {
		EmpresaRestTemplate service = EmpresaRestTemplate.builder().build();
		return service.getAll(RESOURCE_URL);
	}
	
	public EmpresaDto save(EmpresaDto empresaDto) {
		EmpresaRestTemplate service = EmpresaRestTemplate.builder().build();
		return service.save(RESOURCE_URL, empresaDto);
	}
	
	public long empresaCount() {
		EmpresaRestTemplate service = EmpresaRestTemplate.builder().build();
		return service.empresaCount(RESOURCE_URL);
	}
}
