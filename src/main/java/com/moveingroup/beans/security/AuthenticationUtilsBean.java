package com.moveingroup.beans.security;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import com.moveingroup.security.CookieHelper;
import com.moveingroup.utils.Constantes;
import com.moveingroup.utils.ConstantesRedireccion;

public class AuthenticationUtilsBean {

	public String getAuthorizationFromCookie() {
		CookieHelper cookieHelper = new CookieHelper();
		Cookie cookie = cookieHelper.getCookie(Constantes.TOKEN);

		String token = null;

		if (cookie != null && cookie.getValue() != null) {
			token = cookie.getValue();
		}
		return token;
	}

	protected void redirectLoginAgricultor() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/" + (ConstantesRedireccion.INDEX));
	}

	protected void redirectLoginOperador() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(ec.getRequestContextPath() + "/" + (ConstantesRedireccion.INDEX));
	}
}
