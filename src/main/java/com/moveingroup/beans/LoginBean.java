package com.moveingroup.beans;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import com.moveingroup.clients.LoginClient;
import com.moveingroup.security.CookieHelper;
import com.moveingroup.utils.LoginUsuario;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Named
@Data
@Slf4j
@Scope("session")
public class LoginBean {
	
	@Autowired
	private LoginClient loginClient;
	
	@Value("${mig.cookie.expiration.time}")
    private Integer tiempoExpiracionCookie;

	private String username;
	
	private String password;
	
	public String doLoginUsuario() {
		String ret = null;
		// TODO: Hacer un findIdUsuarioByUsername y pasarlo como idUsuario
		try {
			
			Long idUsuario = (long) 0;
			LoginUsuario loginUsuario = new LoginUsuario(username, password, idUsuario, null);
			String migToken = loginClient.getTokenUsuario(loginUsuario);
			
			if (migToken != null) {

				CookieHelper cookieHelper = new CookieHelper();
				cookieHelper.setCookie("token", migToken.substring(7), tiempoExpiracionCookie);

				ret = "index.xhtml?faces-redirect=true";
			    } else {
				ret = "403.xhtml";
			    }
		} catch (Exception e) {
			log.error(e.getMessage() + e);
		}
		return ret;
	}
	
	public String doLoginEmpresa() {
		String ret = null;
		// TODO: Hacer un findIdUsuarioByUsername y pasarlo como idUsuario
		try {
			
			Long idEmpresa = (long) 0;
			LoginUsuario loginUsuario = new LoginUsuario(username, password, null, idEmpresa);
			String migToken = loginClient.getTokenUsuario(loginUsuario);
			
			if (migToken != null) {

				CookieHelper cookieHelper = new CookieHelper();
				cookieHelper.setCookie("token", migToken.substring(7), tiempoExpiracionCookie);

				ret = "index.xhtml?faces-redirect=true";
			    } else {
				ret = "403.xhtml";
			    }
		} catch (Exception e) {
			log.error(e.getMessage() + e);
		}
		return ret;
	}
}
