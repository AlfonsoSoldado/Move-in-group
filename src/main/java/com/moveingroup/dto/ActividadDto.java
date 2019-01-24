package com.moveingroup.dto;

import java.util.Date;

import com.moveingroup.entities.Usuario;

import lombok.Data;

@Data
public class ActividadDto {

	private Long id;
	
	private String name;
	
	private Date moment;
	
	private String city;
	
	private String country;
	
	private String address;
	
	private String type;
	
	private Integer price;
	
	private UsuarioDto usuario;
}
