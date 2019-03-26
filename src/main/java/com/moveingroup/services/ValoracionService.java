package com.moveingroup.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.dto.ValoracionDto;
import com.moveingroup.entities.Valoracion;
import com.moveingroup.repositories.ValoracionRepository;

@Service
public class ValoracionService {

	@Autowired
	private ValoracionRepository valoracionRepository;
	
	public ValoracionDto save(ValoracionDto valoracionDto) {
		ModelMapper modelMapper = new ModelMapper();
		Valoracion valoracion = modelMapper.map(valoracionDto, Valoracion.class);

		try {

			Valoracion savedValoracion = valoracionRepository.save(valoracion);

			return modelMapper.map(savedValoracion, ValoracionDto.class);

		} catch (Throwable e) {
			throw new IllegalArgumentException();
			// TODO: Tratar excepción
		}

	}
	
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
