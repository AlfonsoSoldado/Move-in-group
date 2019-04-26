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
	
	@Column(nullable = false)
	private Boolean cancelada;
	
	@Column(nullable = true)
	private int rango;
	
	// =====================================================
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "LG_USUARIO", nullable = true)
	private Usuario usuario;
	
	@ManyToOne(targetEntity = Empresa.class)
	@JoinColumn(name = "LG_EMPRESA", nullable = true)
	private Empresa empresa;
	
}
