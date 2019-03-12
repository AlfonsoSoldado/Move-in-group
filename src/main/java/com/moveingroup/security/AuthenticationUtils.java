package com.moveingroup.security;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtils {

	public String getRolFromPayload() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return auth.getAuthorities().toString();
	}
	
	public String getParamFromPayload(String param) {

		String ret = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth instanceof MigToken) {
			MigToken mig = (MigToken) auth;
			Map<String, String> map = mig.getPayload();
			ret = map.get(param);
		}

		return ret;

	}
}
