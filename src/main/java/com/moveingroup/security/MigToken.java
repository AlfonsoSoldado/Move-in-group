package com.moveingroup.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
public class MigToken extends AbstractAuthenticationToken {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private final transient Object username;
	
	private transient Object password;
	
	private transient Object rol;
	
	private transient Object idUsuario;
	
	private transient Object idEmpresa;
	
	private transient Map<String, String> payload;

	public MigToken(Object principal, Object credentials, Object idUsuario, Object idEmpresa, Object rol) {
		super(null);
		this.username = principal;
		this.password = credentials;
		if (idUsuario != null) {
			this.idUsuario = idUsuario;
		}
		if (rol != null) {
			this.rol = rol;
		}
		if (idEmpresa != null) {
			this.idEmpresa = idEmpresa;
		}
		setAuthenticated(false);
	}

	public MigToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.username = principal;
		this.password = credentials;
		super.setAuthenticated(true);
	}

	public MigToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities,
			Map<String, String> payload) {
		super(authorities);
		this.username = principal;
		this.password = credentials;
		super.setAuthenticated(true);
		this.payload = payload;
	}

	public Object getCredentials() {
		return this.password;
	}

	public Object getPrincipal() {
		return this.username;
	}

	public Object getRol() {
		return this.rol;
	}
	
	public Object getIdUsuario() {
		return this.idUsuario;
	}	

	public Object getIdEmpresa() {
		return this.idEmpresa;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		password = null;
	}

	public Map<String, String> getPayload() {
		return payload;
	}

	public void setPayload(Map<String, String> payload) {
		this.payload = payload;
	}
}
