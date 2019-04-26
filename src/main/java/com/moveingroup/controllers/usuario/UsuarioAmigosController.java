package com.moveingroup.controllers.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.AmigosDto;
import com.moveingroup.services.AmigosService;

@RestController
@RequestMapping("/usuario/amigos")
public class UsuarioAmigosController {

	@Autowired
	private AmigosService amigosService;
	
	@GetMapping("/getMisAmigos/{id}")
	public List<AmigosDto> getMisAmigos(@PathVariable Long id){
		return amigosService.getMisAmigos(id);
	}
	
	@GetMapping("/getMisPeticionesDeAmistad/{id}")
	public List<AmigosDto> getMisPeticionesDeAmistad(@PathVariable Long id){
		return amigosService.getMisPeticionesDeAmistad(id);
	}
	
	@RequestMapping(value = "amigos", method = RequestMethod.POST)
	public AmigosDto save(@RequestBody AmigosDto amigosDto) {
		return amigosService.save(amigosDto);
	}
	
    @RequestMapping(value = "/aceptarPeticion/{id}", method = RequestMethod.PUT)
    public AmigosDto aceptarPeticion(@PathVariable("id") Long id, @RequestBody AmigosDto porteDto) {
	return amigosService.aceptarPeticion(id, porteDto);
    }
}
