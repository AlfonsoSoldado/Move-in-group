package com.moveingroup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.entities.Actividad;
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

	@GetMapping("/findOne")
	public Actividad findOne(@RequestParam("id") Long id) {
		return actividadService.findOne(id);
	}

}