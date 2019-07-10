package com.moveingroup.clients;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	public List<ActividadDto> getAllByEmpresas() {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.getAllByEmpresas(RESOURCE_URL + "empresas");
	}
	
	public List<ActividadDto> filtrar(String nombre, String pais, String ciudad) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		
		if("".equals(nombre)) {
			nombre = null;
		}
		if("".equals(pais)) {
			pais = null;
		}
		if("".equals(ciudad)) {
			ciudad = null;
		}
		
		String url = RESOURCE_URL + "filtrar/";
		url += nombre;
		url += "/" + pais;
		url += "/" + ciudad;
		
		return service.filtrar(url);
	}
	

}
