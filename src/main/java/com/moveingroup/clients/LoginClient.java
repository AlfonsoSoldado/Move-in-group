package com.moveingroup.clients;

import javax.inject.Named;

import com.moveingroup.dto.UserAccountDto;
import com.moveingroup.rest.LoginRestTemplate;

@Named
public class LoginClient {

	private static String RESOURCE_URL = "/login/";

	public String getTokenUsuario(UserAccountDto usuario) {
		LoginRestTemplate service = LoginRestTemplate.builder().build();
		return service.loginUsuario(RESOURCE_URL, usuario);
	}
	
	public String getTokenEmpresa(UserAccountDto usuario) {
		LoginRestTemplate service = LoginRestTemplate.builder().build();
		return service.loginEmpresa(RESOURCE_URL, usuario);
	}
	
	public String getTokenAdmin(UserAccountDto usuario) {
		LoginRestTemplate service = LoginRestTemplate.builder().build();
		return service.loginAdmin(RESOURCE_URL, usuario);
	}

}
