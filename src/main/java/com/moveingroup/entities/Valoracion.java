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
	private int rank;
	
	@Column(nullable = false)
	private int goodPoints;
	
	@Column(nullable = false)
	private int badPoints;
	
	@Column(nullable = false)
	private String badge;
}
