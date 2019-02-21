package com.moveingroup.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.dto.UsuarioDto;
import com.moveingroup.entities.Usuario;
import com.moveingroup.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll() {
		List<Usuario> target = new ArrayList<>();
		
		Iterable<Usuario> source = usuarioRepository.findAll();
		source.forEach(target::add);
		
		return target;
	}
	
	public Usuario findOne(Long id) {
		Usuario usuario = usuarioRepository.findById(id).orElse(null);
		return usuario;
	}
	
	public void deleteUsuario(Long id) {
		if(usuarioRepository.findById(id).isPresent()) {
			usuarioRepository.delete(usuarioRepository.findById(id).orElse(null));
		}
	}
	
	public List<UsuarioDto> getMejoresValorados() {
		List<Usuario> usuarios = usuarioRepository.findTop10ByOrderByValoracionRangoDesc();
		List<UsuarioDto> res = new ArrayList<>();
		
		for (Usuario usuario: usuarios) {
			ModelMapper modelMapper = new ModelMapper();
			res.add(modelMapper.map(usuario, UsuarioDto.class));
		}
		
		return res;
	}
}
