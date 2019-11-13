package com.moveingroup.clients.usuario;

import java.util.List;

import javax.inject.Named;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.rest.ActividadRestTemplate;

@Named
public class UsuarioActividadClient {
	
	private static String RESOURCE_URL = "/usr/actividad/";
	
	public List<ActividadDto> findByUsuarioId(Long id) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.findByUsuarioId(RESOURCE_URL, id);
	}
	
	public List<ActividadDto> findActividadesTerminadas(Long id) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.findActividadesTerminadas(RESOURCE_URL, id);
	}
	
	public List<ActividadDto> findActividadesByApuntado(Long id) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.findActividadesByApuntado(RESOURCE_URL, id);
	}
	
	public ActividadDto save(ActividadDto actividadDto) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.save(RESOURCE_URL, actividadDto);
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
