package com.moveingroup.clients;

import javax.inject.Named;

import com.moveingroup.rest.LoginRestTemplate;
import com.moveingroup.utils.LoginUsuario;


@Named
public class LoginClient {

	public String getTokenUsuario(LoginUsuario usuario) {
		LoginRestTemplate service = LoginRestTemplate.builder().build();
		return service.loginUsuario("/loginUsuario", usuario);
	    }

}
