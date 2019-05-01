package com.moveingroup.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.dto.EmpresaDto;
import com.moveingroup.entities.Empresa;
import com.moveingroup.repositories.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	public List<EmpresaDto> findAll() {
		List<Empresa> target = new ArrayList<>();
		List<EmpresaDto> res = new ArrayList<>();
		
		Iterable<Empresa> source = empresaRepository.findAll();
		source.forEach(target::add);
		
		ModelMapper modelMapper = new ModelMapper();
		for(Empresa e: target) {
			EmpresaDto eDto = modelMapper.map(e, EmpresaDto.class);
			res.add(eDto);
		}
		
		return res;
	}
	
	public Empresa findOne(Long id) {
		Empresa empresa = empresaRepository.findById(id).orElse(null);
		return empresa;
	}
	
	public void deleteEmpresa(Long id) {
		if(empresaRepository.findById(id).isPresent()) {
			empresaRepository.delete(empresaRepository.findById(id).orElse(null));
		}
	}
	
	public EmpresaDto save(EmpresaDto empresaDto) {
		ModelMapper modelMapper = new ModelMapper();
		Empresa empresa = modelMapper.map(empresaDto, Empresa.class);

		try {

			Empresa savedEmpresa = empresaRepository.save(empresa);

			return modelMapper.map(savedEmpresa, EmpresaDto.class);

		} catch (Throwable e) {
			throw new IllegalArgumentException();
			// TODO: Tratar excepción
		}

	}
	
	public long empresaCount() {
		return empresaRepository.empresaCount();
	}
}
