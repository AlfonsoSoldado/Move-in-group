package com.moveingroup.clients.usuario;

import java.util.List;

import javax.inject.Named;

import com.moveingroup.dto.AmigosDto;
import com.moveingroup.rest.AmigosRestTemplate;

@Named
public class UsuarioAmigosClient {

	private static String RESOURCE_URL = "/usr/amigos/";
	
	public List<AmigosDto> getMisAmigos(Long idUsuario) {
		AmigosRestTemplate service = AmigosRestTemplate.builder().build();
		return service.getMisAmigos(RESOURCE_URL, idUsuario);
	}
	
	public List<AmigosDto> getMisPeticionesDeAmistad(Long idUsuario) {
		AmigosRestTemplate service = AmigosRestTemplate.builder().build();
		return service.getMisPeticionesDeAmistad(RESOURCE_URL, idUsuario);
	}
	
	public AmigosDto save(AmigosDto amigosDto) {
		AmigosRestTemplate service = AmigosRestTemplate.builder().build();
		return service.save(RESOURCE_URL, amigosDto);
	}
	
	public AmigosDto aceptarPeticion(Long id, AmigosDto amigosDto) {
		AmigosRestTemplate service = AmigosRestTemplate.builder().build();
		return service.aceptarPeticion(RESOURCE_URL, id, amigosDto);
	}
	
	public void rechazarPeticion(Long id) {
		AmigosRestTemplate service = AmigosRestTemplate.builder().build();
		service.rechazarPeticion(RESOURCE_URL, id);
	}
	
	public void delete(Long id) {
		AmigosRestTemplate service = AmigosRestTemplate.builder().build();
		service.delete(RESOURCE_URL, id);
	}
}
