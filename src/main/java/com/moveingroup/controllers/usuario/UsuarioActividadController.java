package com.moveingroup.controllers.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.services.ActividadService;

@RestController
@RequestMapping("/usr/actividad")
public class UsuarioActividadController {

	@Autowired
	private ActividadService actividadService;
	
	@GetMapping("/findByUsuarioId/{id}")
	public List<ActividadDto> findByUsuarioId(@PathVariable Long id) {
		return actividadService.findByUsuarioId(id);
	}
	
	@GetMapping("/findActividadesTerminadas/{id}")
	public List<ActividadDto> findActividadesTerminadas(@PathVariable Long id) {
		return actividadService.findActividadesTerminadas(id);
	}
	
	@GetMapping("/findActividadesByApuntado/{id}")
	public List<ActividadDto> findActividadesByApuntado(@PathVariable Long id) {
		return actividadService.findActividadesByApuntado(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteActividad(@PathVariable Long id) {
		actividadService.deleteActividad(id);
	}

	@RequestMapping(value = "actividad", method = RequestMethod.POST)
	public ActividadDto save(@RequestBody ActividadDto actividadDto) {
		return actividadService.save(actividadDto);
	}
	
	@GetMapping("/findById/{id}")
	public ActividadDto findById(@PathVariable Long id) {
		return actividadService.findById(id);
	}
	
	@RequestMapping(value = "/cancelarActividad/{id}", method = RequestMethod.PUT)
    public ActividadDto deshabilitarById(@PathVariable("id") Long id) {
		return actividadService.cancelarActividad(id);
    }
}
