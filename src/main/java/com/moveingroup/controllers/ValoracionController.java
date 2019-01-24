package com.moveingroup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.entities.Valoracion;
import com.moveingroup.services.ValoracionService;

@RestController
@RequestMapping("/valoracion")
public class ValoracionController {
	
	@Autowired
	private ValoracionService valoracionService;

	@GetMapping("/")
	public List<Valoracion> findAll() {
		return valoracionService.findAll();
	}
	
	@GetMapping("/findOne")
	public Valoracion findOne(@RequestParam("id") Long id) {
		return valoracionService.findOne(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteValoracion(@PathVariable Long id) {
		valoracionService.deleteValoracion(id);
	}
	
	
}
