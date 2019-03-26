package com.moveingroup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.RolDto;
import com.moveingroup.services.RolService;

@RestController
@RequestMapping("/rol")
public class RolController {
	
	@Autowired
	private RolService rolService;

	@GetMapping("/findByTipoRol")
	public RolDto findOne(@RequestParam("tipoRol") String tipoRol) {
		return rolService.findByTipoRol(tipoRol);
	}

	
	
}
