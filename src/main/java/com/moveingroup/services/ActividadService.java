package com.moveingroup.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.entities.Actividad;
import com.moveingroup.repositories.ActividadRepository;
import com.moveingroup.utils.Constantes;

@Service
public class ActividadService {

	@Autowired
	private ActividadRepository actividadRepository;

	public List<ActividadDto> findAll() {
		List<Actividad> target = new ArrayList<>();
		List<ActividadDto> res = new ArrayList<>();

		target = actividadRepository.findAll(new Date());

		for (Actividad actividad : target) {
			ModelMapper modelMapper = new ModelMapper();
			res.add(modelMapper.map(actividad, ActividadDto.class));
		}

		return res;
	}
	
	public List<ActividadDto> filtrar(String nombre, String pais, String ciudad) {
		List<ActividadDto> res = new ArrayList<>();
		
		if("null".equals(nombre)) {
			nombre = null;
		}
		if("null".equals(pais)) {
			pais = null;
		}
		if("null".equals(ciudad)) {
			ciudad = null;
		}
		
		try {
			List<Actividad> actividades = actividadRepository.filtrar(nombre, pais, ciudad);
			
			for (Actividad actividad : actividades) {
				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(actividad, ActividadDto.class));
			}
			
			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
	}
	
	public List<ActividadDto> getAllByEmpresas() {
		List<Actividad> target = new ArrayList<>();
		List<ActividadDto> res = new ArrayList<>();

		Iterable<Actividad> source = actividadRepository.getAllByEmpresas();
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
			List<Actividad> actividades = actividadRepository.findByUsuarioId(id, new Date());
			
			for (Actividad actividad : actividades) {
				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(actividad, ActividadDto.class));
			}
			
			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
	}
	
	public List<ActividadDto> findActividadesTerminadas(Long id) {
		List<ActividadDto> res = new ArrayList<>();
		
		try {
			List<Actividad> actividades = actividadRepository.findActividadesTerminadas(id, new Date());
			
			for (Actividad actividad : actividades) {
				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(actividad, ActividadDto.class));
			}
			
			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
	}
	
	public List<ActividadDto> findByEmpresaId(Long id) {
		List<ActividadDto> res = new ArrayList<>();
		
		try {
			List<Actividad> actividades = actividadRepository.findByEmpresaId(id, new Date());
			
			for (Actividad actividad : actividades) {
				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(actividad, ActividadDto.class));
			}
			
			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
	}

	public ActividadDto findById(Long id) {
		ActividadDto actividadDto = new ActividadDto();
		try {
			Actividad actividad = actividadRepository.findById(id).orElse(null);
			ModelMapper modelMapper = new ModelMapper();
			actividadDto = modelMapper.map(actividad, ActividadDto.class);
		} catch (Exception e) {
			throw new IllegalArgumentException();
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
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
		return actividadDto;
	}
	
	public Long countByActividad(String tipoActividad) {
		Long res = 0L;
		try {
			if(tipoActividad.equals(Constantes.ACTIVIDAD_ACTIVA)) {
				Date date = new Date();
				res = actividadRepository.countActividadesActivas(date);
			} else if (tipoActividad.equals(Constantes.ACTIVIDAD_TERMINADA)) {
				Date date = new Date();
				res = actividadRepository.countActividadesTerminadas(date);
			} else if (tipoActividad.equals(Constantes.ACTIVIDAD_CANCELADA)) {
				res = actividadRepository.countActividadesCanceladas();
			}
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
		return res;
	}
}
