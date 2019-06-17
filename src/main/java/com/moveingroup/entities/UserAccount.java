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
public class UserAccount {
	
	public UserAccount() {
		super();
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	// =====================================
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "LG_USUARIO", nullable = true)
	private Usuario usuario;
	
	@ManyToOne(targetEntity = Empresa.class)
	@JoinColumn(name = "LG_EMPRESA", nullable = true)
	private Empresa empresa;
	
	@ManyToOne(targetEntity = Admin.class)
	@JoinColumn(name = "LG_ADMIN", nullable = true)
	private Admin admin;
	
	@ManyToOne(targetEntity = Rol.class)
	@JoinColumn(name = "LG_ROL", nullable = false)
	private Rol rol;
}
