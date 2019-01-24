package com.moveingroup.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.entities.Actividad;
import com.moveingroup.repositories.ActividadRepository;

@Service
public class ActividadService {

	@Autowired
	private ActividadRepository actividadRepository;
	
	public List<Actividad> findAll() {
		List<Actividad> target = new ArrayList<>();
		
		Iterable<Actividad> source = actividadRepository.findAll();
		source.forEach(target::add);
		
		return target;
	}
	
	public Actividad findOne(Long id) {
		Actividad actividad = actividadRepository.findById(id).orElse(null);
		return actividad;
	}
	
	public void deleteActividad(Long id) {
		if(actividadRepository.findById(id).isPresent()) {
			actividadRepository.delete(actividadRepository.findById(id).orElse(null));
		}
	}
}
