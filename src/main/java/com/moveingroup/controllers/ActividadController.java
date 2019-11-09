package com.moveingroup.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.services.ActividadService;

@RestController
@RequestMapping("/actividad")
public class ActividadController {

	@Autowired
	private ActividadService actividadService;

	@GetMapping("/")
	public List<ActividadDto> findAll() {
		return actividadService.findAll();
	}
	
	@GetMapping("/empresas")
	public List<ActividadDto> getAllByEmpresas() {
		return actividadService.getAllByEmpresas();
	}
	
	@GetMapping("/filtrar/{nombre}/{pais}/{ciudad}/{desde}/{hasta}")
	public List<ActividadDto> filtrar(@PathVariable String nombre, @PathVariable String pais, @PathVariable String ciudad, @PathVariable @DateTimeFormat(iso = ISO.DATE) Date desde, @PathVariable @DateTimeFormat(iso = ISO.DATE) Date hasta) {
		return actividadService.filtrar(nombre,pais,ciudad,desde,hasta);
	}
	
	@GetMapping("/countByActividad/{tipoActividad}")
	public long countByActividad(@PathVariable String tipoActividad) {
		return actividadService.countByActividad(tipoActividad);
	}
	
}
