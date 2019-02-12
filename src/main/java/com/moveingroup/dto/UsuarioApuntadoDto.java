package com.moveingroup.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioApuntadoDto {

	private Long id;
	
	// =====================================================
	
	private UsuarioDto usuarioDto;
	
	private ActividadDto actividadDto;
}
