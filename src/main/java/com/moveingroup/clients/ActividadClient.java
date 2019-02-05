package com.moveingroup.clients;

import java.util.List;

import javax.inject.Named;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.rest.ActividadRestTemplate;

@Named
public class ActividadClient {

	private static String RESOURCE_URL = "/actividad/";

	public List<ActividadDto> getAll() {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.getAll(RESOURCE_URL);
	}

	public ActividadDto save(ActividadDto actividadDto) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.save(RESOURCE_URL, actividadDto);
	}
}
