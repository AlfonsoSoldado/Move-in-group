package com.moveingroup.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;


@Component("logoutHandler")
public class LogoutHandler implements LogoutSuccessHandler {

	
	/** cookie helper. */
	private CookieHelper cookieHelper = new CookieHelper();

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.logout.LogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

			request.getSession().invalidate();
			cookieHelper.invalidateSession(request, response);
			response.sendRedirect("/login.xhtml");
	}
}
