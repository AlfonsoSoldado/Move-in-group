package com.moveingroup.controllers.empresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.services.ActividadService;

@RestController
@RequestMapping("/empresa/actividad")
public class EmpresaActividadController {

	@Autowired
	private ActividadService actividadService;
	
	@GetMapping("/findByEmpresaId/{id}")
	public List<ActividadDto> findByEmpresaId(@PathVariable Long id) {
		return actividadService.findByEmpresaId(id);
	}
}
