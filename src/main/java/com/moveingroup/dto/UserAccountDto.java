package com.moveingroup.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAccountDto {

	private Long id;
	
	private String username;
	
	private String password;
	
	// =====================================
	
	private UsuarioDto usuario;
	
	private EmpresaDto empresa;
	
	private AdminDto admin;
	
	private RolDto rol;
}
