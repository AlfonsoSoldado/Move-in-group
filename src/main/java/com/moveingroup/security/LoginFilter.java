package com.moveingroup.security;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moveingroup.constants.Constants;
import com.moveingroup.dto.UserAccountDto;
import com.moveingroup.services.UserAccountService;

import lombok.Getter;
import lombok.Setter;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private UserAccountService userAccountService;

	private JwtUtil jwtUtil;

	public LoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	public LoginFilter(String url, AuthenticationManager authManager, UserAccountService userAccountService) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		this.userAccountService = userAccountService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		if (jwtUtil == null) {
			ServletContext servletContext = req.getServletContext();
			WebApplicationContext webApplicationContext = WebApplicationContextUtils
					.getWebApplicationContext(servletContext);
			jwtUtil = webApplicationContext.getBean(JwtUtil.class);
		}

		// obtenemos el body de la peticion que asumimos viene en formato JSON
		InputStream body = req.getInputStream();

		// Realizamos un mapeo a nuestra clase User para tener ahi los datos
		InfoPersonal user = new ObjectMapper().readValue(body, InfoPersonal.class);

		// Validamos el usuario
		try {
			if (user.getIdUsuario() != null) {
				if (userAccountService.loginWithUsername(user.getUsername(), user.getPassword(),
						user.getIdUsuario()) != null) {
					return new MigToken(user.getUsername(), user.getPassword(), user.getIdUsuario(), null,
							Constants.ROL_USUARIO);
				} else {
					throw new BadCredentialsException("Authentication failed for " + user.getUsername());
				}
			} else if (user.getIdEmpresa() != null) {
				if (userAccountService.loginWithEmpresa(user.getUsername(), user.getPassword(),
						user.getIdEmpresa()) != null) {
					return new MigToken(user.getUsername(), user.getPassword(), null, user.getIdEmpresa(),
							Constants.ROL_EMPRESA);
				} else {
					throw new BadCredentialsException("Authentication failed for " + user.getUsername());
				}
			} else {
				throw new BadCredentialsException("Authentication failed for " + user.getUsername());
			}
		} catch (Exception e) {
			throw new BadCredentialsException("Authentication failed for " + user.getUsername() + " " + e);

		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		if (auth instanceof MigToken) {
			MigToken dat = (MigToken) auth;
			if (dat.getPrincipal() != null && dat.getCredentials() != null && dat.getUsuario() != null) {

				UserAccountDto us = userAccountService.loginWithUsername(dat.getPrincipal().toString(),
						dat.getCredentials().toString(), Long.parseLong(dat.getUsuario().toString()));

				// Si la autenticacion fue exitosa, agregamos el token a la respuesta
				jwtUtil.addAuthentication(res, us);
			} else if(dat.getPrincipal() != null && dat.getCredentials() != null && dat.getEmpresa() != null) {
				UserAccountDto us2 = userAccountService.loginWithEmpresa(dat.getPrincipal().toString(),
						dat.getCredentials().toString(), Long.parseLong(dat.getEmpresa().toString()));
				
				// Si la autenticacion fue exitosa, agregamos el token a la respuesta
				jwtUtil.addAuthentication(res, us2);
			}
		}
	}
}

@Getter
@Setter
class InfoPersonal {
	private String username;
	private String password;
	private Long idUsuario;
	private Long idEmpresa;
}
