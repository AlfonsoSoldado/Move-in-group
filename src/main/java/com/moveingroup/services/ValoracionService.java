package com.moveingroup.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.entities.Valoracion;
import com.moveingroup.repositories.ValoracionRepository;

@Service
public class ValoracionService {

	@Autowired
	private ValoracionRepository valoracionRepository;
	
	public List<Valoracion> findAll() {
		List<Valoracion> target = new ArrayList<>();
		
		Iterable<Valoracion> source = valoracionRepository.findAll();
		source.forEach(target::add);
		
		return target;
	}
	
	public Valoracion findOne(Long id) {
		Valoracion valoracion = valoracionRepository.findById(id).orElse(null);
		return valoracion;
	}
	
	public void deleteValoracion(Long id) {
		if(valoracionRepository.findById(id).isPresent()) {
			valoracionRepository.delete(valoracionRepository.findById(id).orElse(null));
		}
	}
}
