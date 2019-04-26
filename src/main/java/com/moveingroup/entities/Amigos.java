package com.moveingroup.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Amigos {

	public Amigos() {
		super();
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "LG_AMIGO_A", nullable = false)
	private Usuario amigoA;
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "LG_AMIGO_B", nullable = false)
	private Usuario amigoB;
	
	@Column(nullable = false)
	private boolean yaEsAmigo;
}
