package com.moveingroup.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto {

	private Long id;
	
	private String nombre;
	
	private String apellidos;
	
	private String email;
	
	private String telefono;
	
	// ====================================
	
	private ValoracionDto valoracion;
}
