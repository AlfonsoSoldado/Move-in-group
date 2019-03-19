package com.moveingroup.clients.empresa;

import java.util.List;

import javax.inject.Named;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.rest.ActividadRestTemplate;

@Named
public class EmpresaActividadClient {
	
	private static String RESOURCE_URL = "/empresa/actividad/";
	
	public List<ActividadDto> findByEmpresaId(Long id) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.findByEmpresaId(RESOURCE_URL, id);
	}

	public ActividadDto findById(Long id) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.findById(RESOURCE_URL, id);
	}
	
	public void cancelarActividad(Long id, String auth) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		service.cancelarActividad(RESOURCE_URL, id, auth);
	}
}
