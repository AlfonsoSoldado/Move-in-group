package com.moveingroup.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.entities.Actividad;
import com.moveingroup.repositories.ActividadRepository;

@Service
public class ActividadService {

	@Autowired
	private ActividadRepository actividadRepository;

	public List<ActividadDto> findAll() {
		List<Actividad> target = new ArrayList<>();
		List<ActividadDto> res = new ArrayList<>();

		Iterable<Actividad> source = actividadRepository.findAll();
		source.forEach(target::add);

		for (Actividad actividad : target) {
			ModelMapper modelMapper = new ModelMapper();
			res.add(modelMapper.map(actividad, ActividadDto.class));
		}

		return res;
	}

	public Actividad findOne(Long id) {
		Actividad actividad = actividadRepository.findById(id).orElse(null);
		return actividad;
	}

	public void deleteActividad(Long id) {
		if (actividadRepository.findById(id).isPresent()) {
			actividadRepository.delete(actividadRepository.findById(id).orElse(null));
		}
	}

	public ActividadDto save(ActividadDto actividadDto) {
		ModelMapper modelMapper = new ModelMapper();
		Actividad actividad = modelMapper.map(actividadDto, Actividad.class);

		try {

			Actividad savedActividad = actividadRepository.save(actividad);

			return modelMapper.map(savedActividad, ActividadDto.class);

		} catch (Throwable e) {
			throw new IllegalArgumentException();
			// TODO: Tratar excepción
		}

	}
}
