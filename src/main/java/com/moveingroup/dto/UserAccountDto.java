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
	
	private UsuarioDto usuarioDto;
	
	private EmpresaDto empresaDto;
	
	private AdminDto adminDto;
	
	private RolDto rolDto;
}
