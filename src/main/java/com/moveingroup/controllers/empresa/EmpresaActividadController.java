package com.moveingroup.controllers.empresa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.services.ActividadService;

@RestController
@RequestMapping("/emp/actividad")
public class EmpresaActividadController {

	@Autowired
	private ActividadService actividadService;
	
	@GetMapping("/findByEmpresaId/{id}")
	public List<ActividadDto> findByEmpresaId(@PathVariable Long id) {
		return actividadService.findByEmpresaId(id);
	}
	
	@GetMapping("/findActividadesTerminadasByEmpresaId/{id}")
	public List<ActividadDto> findActividadesTerminadasByEmpresaId(@PathVariable Long id) {
		return actividadService.findActividadesTerminadasByEmpresaId(id);
	}
	
	@GetMapping("/findById/{id}")
	public ActividadDto findById(@PathVariable Long id) {
		return actividadService.findById(id);
	}
	
	@RequestMapping(value = "/cancelarActividad/{id}", method = RequestMethod.PUT)
    public ActividadDto deshabilitarById(@PathVariable("id") Long id) {
		return actividadService.cancelarActividad(id);
    }
	
	@GetMapping("/calcularGananciasTotales/{id}")
	public Integer calcularGananciasTotales(@PathVariable Long id) {
		return actividadService.calcularGananciasTotales(id);
	}
	
	@GetMapping("/gananciasEmpresaTotal/{id}")
	public Integer gananciasEmpresaTotal(@PathVariable Long id) {
		return actividadService.gananciasEmpresaTotal(id);
	}
}
