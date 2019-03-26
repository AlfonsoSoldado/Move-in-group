package com.moveingroup.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.dto.RolDto;
import com.moveingroup.entities.Rol;
import com.moveingroup.repositories.RolRepository;

@Service
public class RolService {

	@Autowired
	private RolRepository rolRepository;

	public RolDto findByTipoRol(String tipoRol) {
		RolDto rolDto = new RolDto();
		try {
			Rol rol = rolRepository.findByTipoRol(tipoRol);
			ModelMapper modelMapper = new ModelMapper();
			rolDto = modelMapper.map(rol, RolDto.class);
		} catch (Exception e) {
			// TODO: Tratar excepción
		}
		return rolDto;
	}
}
