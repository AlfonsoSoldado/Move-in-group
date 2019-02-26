package com.moveingroup.clients.usuario;

import java.util.List;

import javax.inject.Named;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.rest.ActividadRestTemplate;

@Named
public class UsuarioActividadClient {
	
	private static String RESOURCE_URL = "/usuario/actividad/";
	
	public List<ActividadDto> findByUsuarioId(Long id) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.findByUsuarioId(RESOURCE_URL, id);
	}
	
	public ActividadDto save(ActividadDto actividadDto) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.save(RESOURCE_URL, actividadDto);
	}
}
