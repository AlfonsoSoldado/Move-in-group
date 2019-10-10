package com.moveingroup.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import com.moveingroup.clients.LoginClient;
import com.moveingroup.clients.RolClient;
import com.moveingroup.clients.UsuarioClient;
import com.moveingroup.dto.UserAccountDto;
import com.moveingroup.security.CookieHelper;
import com.moveingroup.utils.Constantes;

import lombok.Data;

@Named
@Data
@Scope("session")
public class LoginBean {
	
	@Autowired
	private LoginClient loginClient;
	
	@Autowired
	private RolClient rolClient;
	
	@Autowired
	private UsuarioClient usuarioClient;
	
	@Value("${mig.cookie.expiration.time}")
    private Integer tiempoExpiracionCookie;

	private String username;
	
	private String password;
	
	public String doAccederRegistro() {
		return "registro-usuario.xhtml?faces-redirect=true";
	}
	
	public String doLoginUsuario() {
		String ret = null;
		try {
			
			UserAccountDto userAccountDto = new UserAccountDto();
			userAccountDto.setUsername(username);
			userAccountDto.setPassword(password);
			userAccountDto.setRol(rolClient.findByTipoRol(Constantes.ROL_USUARIO));
			String migToken = loginClient.getTokenUsuario(userAccountDto);
			
			if (migToken != null) {

				CookieHelper cookieHelper = new CookieHelper();
				cookieHelper.setCookie(Constantes.TOKEN, migToken, tiempoExpiracionCookie);
				ret = "usuario/actividades.xhtml?faces-redirect=true";
			    } else {
			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login incorrecto",
						    ""));
			    }
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el login",
				    ""));
		}
		return ret;
	}
	
	public String doLoginEmpresa() {
		String ret = null;
		try {
			
			UserAccountDto userAccountDto = new UserAccountDto();
			userAccountDto.setUsername(username);
			userAccountDto.setPassword(password);
			userAccountDto.setRol(rolClient.findByTipoRol(Constantes.ROL_EMPRESA));
			String migToken = loginClient.getTokenEmpresa(userAccountDto);
			
			if (migToken != null) {

				CookieHelper cookieHelper = new CookieHelper();
				cookieHelper.setCookie(Constantes.TOKEN, migToken, tiempoExpiracionCookie);
				ret = "empresa/actividades.xhtml?faces-redirect=true";
			    } else {
			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login incorrecto",
							    ""));
			    }
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el login",
				    ""));
		}
		return ret;
	}
	
	public String doLoginAdmin() {
		String ret = null;
		try {
			
			UserAccountDto userAccountDto = new UserAccountDto();
			userAccountDto.setUsername(username);
			userAccountDto.setPassword(password);
			userAccountDto.setRol(rolClient.findByTipoRol(Constantes.ROL_ADMIN));
			String migToken = loginClient.getTokenAdmin(userAccountDto);
			
			if (migToken != null) {

				CookieHelper cookieHelper = new CookieHelper();
				cookieHelper.setCookie(Constantes.TOKEN, migToken, tiempoExpiracionCookie);
				ret = "admin/estadisticas.xhtml?faces-redirect=true";
			    } else {
			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login incorrecto",
							    ""));
			    }
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el login",
				    ""));
		}
		return ret;
	}
}
