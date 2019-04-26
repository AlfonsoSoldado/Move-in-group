package com.moveingroup.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AmigosDto {

	
	private Long id;
	
	// ====================================================
	
	private UsuarioDto amigoA;
	
	private UsuarioDto amigoB;
	
	private boolean yaEsAmigo;
}
