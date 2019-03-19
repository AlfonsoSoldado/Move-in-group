package com.moveingroup.controllers.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.entities.Empresa;
import com.moveingroup.services.EmpresaService;

@RestController
@RequestMapping("/empresa/empresa")
public class EmpresaEmpresaController {

	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/findOne")
	public Empresa findOne(@RequestParam("id") Long id) {
		return empresaService.findOne(id);
	}
}
