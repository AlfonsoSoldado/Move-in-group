package com.moveingroup.dto;

import lombok.Data;

@Data
public class ValoracionDto {
	
	private Long id;
	
	private int rango;
	
	private int puntos;
	
	private int puntosNegativos;
	
	private String medalla;
}
