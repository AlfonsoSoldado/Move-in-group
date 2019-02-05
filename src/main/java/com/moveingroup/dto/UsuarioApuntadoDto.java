package com.moveingroup.dto;

import lombok.Data;

@Data
public class UsuarioApuntadoDto {

	private Long id;

	private UsuarioDto usuarioDto;

	private ActividadDto actividadDto;
}
