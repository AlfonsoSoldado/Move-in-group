package com.moveingroup.beans.actividad;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.moveingroup.clients.ActividadClient;
import com.moveingroup.dto.ActividadDto;

import lombok.Data;

@Named
@Data
@Scope("view")
public class ActividadesBean {
	
	@Autowired
	private ActividadClient actividadClient;
	
	private List<ActividadDto> actividades;

	private ActividadDto selectedActividad;

	public void init() {
		actividades = actividadClient.getAll();
	}

}
