package com.moveingroup.security.front;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtilFront extends AbstractJwtUtilFront {

	private static final long serialVersionUID = 5426977484359176926L;
	
	//TODO: Mirar si cambiar el security Key
	@Value("${mig.security.key}")
    public void setKey(String value) {
		this.key = value;
    }
	
	@Value("${mig.cookie.expiration.time}")
    public void setTiempoExpiracion(Long value) {
        this.tiempoExpiracion = value;
    }
	
	public  String crearTokenAuth(String nif) {

		String token = Jwts.builder().setSubject(nif)

				// Agregamos en el payload la info que necesitemos

				.claim("dni", nif)
				.setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion))

				// Hash con el que firmaremos la clave
				.signWith(SignatureAlgorithm.HS512, key).compact();

		// agregamos al encabezado el token
		return "Bearer" +" " + token;
	}
}
