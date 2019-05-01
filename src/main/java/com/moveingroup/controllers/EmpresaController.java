package com.moveingroup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.EmpresaDto;
import com.moveingroup.entities.Empresa;
import com.moveingroup.services.EmpresaService;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;

	@GetMapping("/")
	public List<EmpresaDto> findAll() {
		return empresaService.findAll();
	}
	
	@GetMapping("/findOne")
	public Empresa findOne(@RequestParam("id") Long id) {
		return empresaService.findOne(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteEmpresa(@PathVariable Long id) {
		empresaService.deleteEmpresa(id);
	}
	
	@RequestMapping(value = "empresa", method = RequestMethod.POST)
	public EmpresaDto save(@RequestBody EmpresaDto empresaDto) {
		return empresaService.save(empresaDto);
	}
	
	@GetMapping("/empresaCount")
	public long empresaCount() {
		return empresaService.empresaCount();
	}
	
}
