package com.moveingroup.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.dto.ValoracionDto;
import com.moveingroup.entities.Valoracion;
import com.moveingroup.repositories.ValoracionRepository;
import com.moveingroup.utils.Constantes;

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
	
	public ValoracionDto update(Long id, ValoracionDto valoracionDto) {
		try {
			if(valoracionRepository.existsById(id)) {
				int newRango = valoracionDto.getPuntos() - valoracionDto.getPuntosNegativos();
				valoracionDto.setRango(newRango);
				
				if(valoracionDto.getRango() >= 0 && valoracionDto.getRango() < 10) {
					valoracionDto.setMedalla(Constantes.MEDALLA_NOVATO);
				} else if (valoracionDto.getRango() >= 10 && valoracionDto.getRango() < 20 ) {
					valoracionDto.setMedalla(Constantes.MEDALLA_BUENO);
				} else if (valoracionDto.getRango() >= 20 && valoracionDto.getRango() < 30 ) {
					valoracionDto.setMedalla(Constantes.MEDALLA_BUENISIMO);
				} else if (valoracionDto.getRango() >= 30 ) {
					valoracionDto.setMedalla(Constantes.MEDALLA_EXTRAORDINARIO);
				} else if (valoracionDto.getRango() >= -15 && valoracionDto.getRango() < 0) {
					valoracionDto.setMedalla(Constantes.MEDALLA_MALO);
				} else if (valoracionDto.getRango() < -15) {
					valoracionDto.setMedalla(Constantes.MEDALLA_MALISIMO);
				}
				
				ModelMapper modelMapper = new ModelMapper();
				Valoracion valoracion = modelMapper.map(valoracionDto, Valoracion.class);
				
				Valoracion updatedValoracion = valoracionRepository.save(valoracion);
				
				return modelMapper.map(updatedValoracion, ValoracionDto.class);
			} else {
				throw new IllegalArgumentException();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
	
	public void deleteValoracion(Long id) {
		if(valoracionRepository.findById(id).isPresent()) {
			valoracionRepository.delete(valoracionRepository.findById(id).orElse(null));
		}
	}
	
	public long countByMedalla(String medalla) {
		return valoracionRepository.countByMedalla(medalla);
	}
}
