package com.moveingroup.clients;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	
	public List<ActividadDto> filtrar(String nombre, String pais, String ciudad, Date desde, Date hasta) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
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
		if(desde != null) {
			String desdeFormateada = df.format(desde);
			url += "/" + desdeFormateada;
		} else {
			String desdeFormateada = df.format(new Date());
			url += "/" + desdeFormateada;
		}
		
		if(hasta != null) {
			String hastaFormateada = df.format(hasta);
			url += "/" + hastaFormateada;
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.YEAR, 200);
			Date hastaIncrementada = c.getTime();
			
			String hastaFormateada = df.format(hastaIncrementada);
			
			url += "/" + hastaFormateada;
		}
		
		
		return service.filtrar(url);
	}
	
	public Long countByActividad(String tipoActividad) {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.countByActividad(RESOURCE_URL, tipoActividad);
	}
	
	public Double getGananciasAdmin() {
		ActividadRestTemplate service = ActividadRestTemplate.builder().build();
		return service.getGananciasAdmin(RESOURCE_URL);
	}
	

}
