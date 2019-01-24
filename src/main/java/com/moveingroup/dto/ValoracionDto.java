package com.moveingroup.dto;

import lombok.Data;

@Data
public class ValoracionDto {
	
	private Long id;
	
	private int rank;
	
	private int goodPoints;
	
	private int badPoints;
	
	private String badge;
}
