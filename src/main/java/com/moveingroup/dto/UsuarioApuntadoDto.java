package com.moveingroup.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioApuntadoDto {

	private Long id;
	
	// =====================================================
	
	private UsuarioDto usuario;
	
	private ActividadDto actividad;
}
