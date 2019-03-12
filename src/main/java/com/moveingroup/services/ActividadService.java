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
	
	public List<ActividadDto> findByUsuarioId(Long id) {
		List<ActividadDto> res = new ArrayList<>();
		
		try {
			List<Actividad> actividades = actividadRepository.findByUsuarioId(id);
			
			for (Actividad actividad : actividades) {
				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(actividad, ActividadDto.class));
			}
			
			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
			// TODO: Tratar excepción
		}
	}

	public ActividadDto findById(Long id) {
		ActividadDto actividadDto = new ActividadDto();
		try {
			Actividad actividad = actividadRepository.findById(id).orElse(null);
			ModelMapper modelMapper = new ModelMapper();
			actividadDto = modelMapper.map(actividad, ActividadDto.class);
		} catch (Exception e) {
			// TODO: Tratar excepción
		}
		return actividadDto;
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
	
	public ActividadDto cancelarActividad(Long id) {
		ActividadDto actividadDto = new ActividadDto();
		try {
			Actividad actividad = actividadRepository.findById(id).orElse(null);
			
			actividad.setCancelada(true);
			
			Actividad actividadSaved = actividadRepository.save(actividad);
			
			ModelMapper modelMapper = new ModelMapper();
			actividadDto = modelMapper.map(actividadSaved, ActividadDto.class);
		} catch (Exception e) {
			// TODO: Tratar excepción
		}
		return actividadDto;
	}
}
