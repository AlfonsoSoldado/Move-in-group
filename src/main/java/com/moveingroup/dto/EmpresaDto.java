package com.moveingroup.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpresaDto {
	
	private Long id;
	
	private String nombre;
	
	private String ciudad;
	
	private String pais;
	
	private String email;
	
	private String telefono;
	
	private String web;
	
	private String descripcion;
	
	private Integer ingresos;
}
