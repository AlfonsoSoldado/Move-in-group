package com.moveingroup.services;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<UsuarioApuntadoDto> findByActividadId(Long id) {
		List<UsuarioApuntadoDto> ret = new ArrayList<>();
		try {
			List<UsuarioApuntado> usuarioApuntado = new ArrayList<>();
			usuarioApuntado = usuarioApuntadoRepository.findByActividadId(id);
			
			ModelMapper modelMapper = new ModelMapper();
			for(UsuarioApuntado ua: usuarioApuntado) {
				ret.add(modelMapper.map(ua, UsuarioApuntadoDto.class));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ret;
	}
}
