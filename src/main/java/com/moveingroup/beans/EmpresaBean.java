package com.moveingroup.beans;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.moveingroup.clients.EmpresaClient;
import com.moveingroup.dto.EmpresaDto;

import lombok.Data;

@Named
@Data
public class EmpresaBean {

	@Autowired
	private EmpresaClient empresaClient;
	
	private List<EmpresaDto> empresas;
	
	public void init() {
		empresas = empresaClient.findAll();
	}
}
