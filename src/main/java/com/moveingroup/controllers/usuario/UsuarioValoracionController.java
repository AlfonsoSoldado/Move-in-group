package com.moveingroup.controllers.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.ValoracionDto;
import com.moveingroup.services.ValoracionService;

@RestController
@RequestMapping("/usuario/valoracion")
public class UsuarioValoracionController {

	@Autowired
	private ValoracionService valoracionService;
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ValoracionDto update(@PathVariable("id") Long id, @RequestBody ValoracionDto valoracionDto) {
		return valoracionService.update(id, valoracionDto);
	}
}
