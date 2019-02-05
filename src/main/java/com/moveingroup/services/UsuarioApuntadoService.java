package com.moveingroup.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.dto.UsuarioApuntadoDto;
import com.moveingroup.entities.UsuarioApuntado;
import com.moveingroup.repositories.UsuarioApuntadoRepository;

@Service
public class UsuarioApuntadoService {

	@Autowired
	private UsuarioApuntadoRepository usuarioApuntadoRepository;
	
	public UsuarioApuntadoDto findByActividadId(Long id) {
		UsuarioApuntadoDto ret = null;
		try {
			UsuarioApuntado usuarioApuntado = usuarioApuntadoRepository.findByActividadId(id);
			
			ModelMapper modelMapper = new ModelMapper();
			ret = modelMapper.map(usuarioApuntado, UsuarioApuntadoDto.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ret;
	}
}
