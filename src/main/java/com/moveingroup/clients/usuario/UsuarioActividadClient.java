package com.moveingroup.clients.usuario;

import javax.inject.Named;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.rest.ActividadRestTemplate;

@Named
public class UsuarioActividadClient {
	
	private static String RESOURCE_URL = "/usuario/actividad/";
	
	public ActividadDto save(ActividadDto actividadDto) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.save(RESOURCE_URL, actividadDto);
	}
}
