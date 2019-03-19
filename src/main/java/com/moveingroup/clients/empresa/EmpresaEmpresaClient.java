package com.moveingroup.clients.empresa;

import javax.inject.Named;

import com.moveingroup.dto.EmpresaDto;
import com.moveingroup.rest.EmpresaRestTemplate;

@Named
public class EmpresaEmpresaClient {

	private static String RESOURCE_URL = "/empresa/empresa/";
	
	public EmpresaDto getById(Long id) {
		EmpresaRestTemplate service = EmpresaRestTemplate.builder().build();
		return service.findOne(RESOURCE_URL, id);
	}
}
