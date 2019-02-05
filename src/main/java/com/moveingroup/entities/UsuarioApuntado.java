package com.moveingroup.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class UsuarioApuntado {

	public UsuarioApuntado() {
		super();
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	// =====================================================
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "LG_USUARIO", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(targetEntity = Actividad.class)
	@JoinColumn(name = "LG_ACTIVIDAD", nullable = false)
	private Actividad actividad;
}
