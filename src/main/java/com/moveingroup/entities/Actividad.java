package com.moveingroup.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Actividad {
	
	public Actividad() {
		super();
	}

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = true)
	private Date momento;
	
	@Column(nullable = false)
	private String ciudad;
	
	@Column(nullable = false)
	private String pais;
	
	@Column(nullable = false)
	private String direccion;
	
	@Column(nullable = false)
	private String tipoActividad;
	
	@Column(nullable = true)
	private Integer precio;
	
	// =====================================================
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "LG_USUARIO", nullable = false)
	private Usuario usuario;
	
}