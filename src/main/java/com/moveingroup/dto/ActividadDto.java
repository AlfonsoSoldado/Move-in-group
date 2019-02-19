package com.moveingroup.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActividadDto {

	private Long id;
	
	private String nombre;
	
	private Date momento;
	
	private String ciudad;
	
	private String pais;
	
	private String direccion;
	
	private String tipoActividad;
	
	private Integer precio;
	
	// =====================================================
	
	private UsuarioDto usuario;
}
