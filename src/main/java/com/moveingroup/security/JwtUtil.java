package com.moveingroup.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.moveingroup.dto.UserAccountDto;
import com.moveingroup.utils.Constantes;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil extends AbstractJwtUtil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Value("${mig.security.key}")
	public void setKey(String value) {
		this.key = value;
	}

	@Value("${mig.cookie.expiration.time}")
	public void setTiempoExpiracion(Long value) {
		this.tiempoExpiracion = value;
	}

	public String addAuthenticationUsuario(HttpServletResponse res, UserAccountDto userAccount) {

		String token = "";

		if (userAccount != null) {
			token = Jwts.builder().setSubject(userAccount.getUsername())

					.claim(Constantes.PAYLOAD_USERNAME, userAccount.getUsername())
					.claim(Constantes.PAYLOAD_ROL, Constantes.ROL_USUARIO)
					.claim(Constantes.PAYLOAD_IDUSUARIO, userAccount.getUsuario().getId())
					.setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion))

					.signWith(SignatureAlgorithm.HS512, key).compact();

			res.addHeader(Constantes.AUTHORIZATION, Constantes.BEARER + " " + token);
		}
		return token;
	}
	
	public String addAuthenticationEmpresa(HttpServletResponse res, UserAccountDto userAccount) {

		String token = "";

		if (userAccount != null) {
			token = Jwts.builder().setSubject(userAccount.getUsername())

					.claim(Constantes.PAYLOAD_USERNAME, userAccount.getUsername())
					.claim(Constantes.PAYLOAD_ROL, Constantes.ROL_EMPRESA)
					.claim(Constantes.PAYLOAD_IDEMPRESA, userAccount.getEmpresa().getId())
					.setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion))

					.signWith(SignatureAlgorithm.HS512, key).compact();

			res.addHeader(Constantes.AUTHORIZATION, Constantes.BEARER + " " + token);
		}
		return token;
	}
	
	public String addAuthenticationAdmin(HttpServletResponse res, UserAccountDto userAccount) {

		String token = "";

		if (userAccount != null) {
			token = Jwts.builder().setSubject(userAccount.getUsername())

					.claim(Constantes.PAYLOAD_USERNAME, userAccount.getUsername())
					.claim(Constantes.PAYLOAD_ROL, Constantes.ROL_ADMIN)
					.setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion))

					.signWith(SignatureAlgorithm.HS512, key).compact();

			res.addHeader(Constantes.AUTHORIZATION, Constantes.BEARER + " " + token);
		}
		return token;
	}

	@Override
	public String getTokenJWT(HttpServletRequest request) {
		return request.getHeader(Constantes.AUTHORIZATION);
	}
}
