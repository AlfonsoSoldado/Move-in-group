package com.moveingroup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Valoracion {

	public Valoracion() {
		super();
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private int rango;
	
	@Column(nullable = false)
	private int puntos;
	
	@Column(nullable = false)
	private int puntosNegativos;
	
	@Column(nullable = false)
	private String medalla;
}
