package com.moveingroup.controllers.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.services.ActividadService;

@RestController
@RequestMapping("/usuario/actividad")
public class UsuarioActividadController {

	@Autowired
	private ActividadService actividadService;
	
	@DeleteMapping("/delete/{id}")
	public void deleteActividad(@PathVariable Long id) {
		actividadService.deleteActividad(id);
	}

	@RequestMapping(value = "actividad", method = RequestMethod.POST)
	public ActividadDto save(@RequestBody ActividadDto actividadDto) {
		return actividadService.save(actividadDto);
	}
}
