package com.moveingroup.security;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {

	public void setCookie(String name, String value, int expiry) {
		invalidateSession();

		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		Cookie cookie = setValueToCookie(name, value, expiry, request);

		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.addCookie(cookie);
	}

	private Cookie setValueToCookie(String name, String value, int expiry, HttpServletRequest request) {
	    Cookie cookie = null;

	    Cookie[] userCookies = request.getCookies();
	    if (userCookies != null && userCookies.length > 0) {
	    	for (int i = 0; i < userCookies.length; i++) {
	    		if (userCookies[i].getName().equals(name)) {
	    			cookie = userCookies[i];
	    			break;
	    		}
	    	}
	    }

	    if (cookie != null) {
	    	cookie.setValue(value);
	    } else {
	    	cookie = new Cookie(name, value);
	    	cookie.setPath(request.getContextPath());
	    }

	    cookie.setMaxAge(expiry);
	    return cookie;
	}

	public void setCookie(String name, String value, int expiry, HttpServletRequest request,
			HttpServletResponse response) {
		invalidateSession(request, response);

		Cookie cookie = setValueToCookie(name, value, expiry, request);

		response.addCookie(cookie);
	}

	public Cookie getCookie(String name) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Cookie cookie = null;

		if (facesContext != null) {

			HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

			Cookie[] userCookies = request.getCookies();
			if (userCookies != null && userCookies.length > 0) {
				for (int i = 0; i < userCookies.length; i++) {
					if (userCookies[i].getName().equals(name)) {
						cookie = userCookies[i];
					}
				}
			}
		}
		return cookie;
	}

	public Cookie getCookie(HttpServletRequest request, String name) {

		Cookie cookie = null;

		if (request != null) {

			Cookie[] userCookies = request.getCookies();
			if (userCookies != null && userCookies.length > 0) {
				for (int i = 0; i < userCookies.length; i++) {
					if (userCookies[i].getName().equals(name)) {
						cookie = userCookies[i];
					}
				}
			}
		}
		return cookie;
	}

	public void invalidateSession() {

		// Request object to fetch the cookies
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		// Response object to delete the cookies
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.setContentType("text/html");

		Cookie[] cookies = request.getCookies();

		// Delete all the cookies
		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {

				if (cookies[i].getName().equals("token")) {
					cookies[i].setMaxAge(0);
					cookies[i].setPath(request.getContextPath());
					response.addCookie(cookies[i]);
				}
			}
		}
	}

	public void invalidateSession(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html");

		Cookie[] cookies = request.getCookies();

		// Delete all the cookies
		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {

				if (cookies[i].getName().equals("token")) {
					cookies[i].setMaxAge(0);
					cookies[i].setPath(request.getContextPath());
					response.addCookie(cookies[i]);
				}
			}
		}
	}
}
