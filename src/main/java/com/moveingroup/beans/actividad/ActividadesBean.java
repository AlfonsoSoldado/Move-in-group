package com.moveingroup.beans.actividad;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.moveingroup.clients.ActividadClient;
import com.moveingroup.clients.usuario.UsuarioActividadClient;
import com.moveingroup.dto.ActividadDto;

import lombok.Data;

@Named
@Data
@Scope("view")
public class ActividadesBean {
	
	@Autowired
	private ActividadClient actividadClient;
	
	@Autowired
	private UsuarioActividadClient usuarioActividadClient;
	
	private List<ActividadDto> actividades;

	private ActividadDto selectedActividad;

	public void init() {
		actividades = actividadClient.getAll();
	}

	public void initActividadesDeUsuario() {
		Long id = (long) 1; //TODO: Cambiar por el id del payload
		actividades = usuarioActividadClient.findByUsuarioId(id); //TODO: Pasar el Auth
	}
}
