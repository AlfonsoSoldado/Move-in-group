package com.moveingroup.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;


public class JwtFilter extends GenericFilterBean {

	private JwtUtil jwtUtil;
	
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
	    throws IOException, ServletException {

	if (jwtUtil == null) {
	    ServletContext servletContext = request.getServletContext();
	    WebApplicationContext webApplicationContext = WebApplicationContextUtils
		    .getWebApplicationContext(servletContext);
	    jwtUtil = webApplicationContext.getBean(JwtUtil.class);
	}

	Authentication authentication = jwtUtil.getAuthentication((HttpServletRequest) request);

	SecurityContextHolder.getContext().setAuthentication(authentication);

	filterChain.doFilter(request, response);
    }
}
