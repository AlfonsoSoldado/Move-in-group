package com.moveingroup.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class LoginUsuario {

	private String username;
	
	private String password;
	
	private Long idUsuario;
	
	private Long idEmpresa;
}
