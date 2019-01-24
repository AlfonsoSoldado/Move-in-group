package com.moveingroup.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.entities.Empresa;
import com.moveingroup.repositories.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	public List<Empresa> findAll() {
		List<Empresa> target = new ArrayList<>();
		
		Iterable<Empresa> source = empresaRepository.findAll();
		source.forEach(target::add);
		
		return target;
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
}
