package com.moveingroup.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.dto.AmigosDto;
import com.moveingroup.entities.Amigos;
import com.moveingroup.repositories.AmigosRepository;

@Service
public class AmigosService {

	@Autowired
	private AmigosRepository amigosRepository;
	
	public List<AmigosDto> getMisAmigos(Long idUsuario){
		List<AmigosDto> res = new ArrayList<>();
		
		try {
			List<Amigos> amigos = amigosRepository.getMisAmigos(idUsuario);
			
			for(Amigos amigo: amigos) {
				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(amigo, AmigosDto.class));
			}
			
			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
			// TODO: Tratar excepción
		}
	}
	
	public List<AmigosDto> getMisPeticionesDeAmistad(Long idUsuario){
		List<AmigosDto> res = new ArrayList<>();
		
		try {
			List<Amigos> amigos = amigosRepository.getMisPeticionesDeAmistad(idUsuario);
			
			for(Amigos amigo: amigos) {
				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(amigo, AmigosDto.class));
			}
			
			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
			// TODO: Tratar excepción
		}
	}
	
	public AmigosDto save(AmigosDto amigosDto) {
		ModelMapper modelMapper = new ModelMapper();
		Amigos amigos = modelMapper.map(amigosDto, Amigos.class);

		try {

			Amigos savedAmigos = amigosRepository.save(amigos);

			return modelMapper.map(savedAmigos, AmigosDto.class);

		} catch (Throwable e) {
			throw new IllegalArgumentException();
			// TODO: Tratar excepción
		}

	}
	
	public AmigosDto aceptarPeticion(Long id, AmigosDto amigosDto) {
		try {
			if(amigosRepository.existsById(id)) {
				ModelMapper modelMapper = new ModelMapper();
				Amigos amigos = modelMapper.map(amigosDto, Amigos.class);
				
				amigos.setYaEsAmigo(true);
				
				Amigos updatedAmigos = amigosRepository.save(amigos);
				
				return modelMapper.map(updatedAmigos, AmigosDto.class);
			} else {
				throw new IllegalArgumentException();
				// TODO: Tratar excepción
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
			// TODO: Tratar excepción
		}
	}
}
