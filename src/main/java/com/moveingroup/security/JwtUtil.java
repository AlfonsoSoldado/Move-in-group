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

	private static final long serialVersionUID = 1;

    @Value("${mig.security.key}")
    public void setKey(String value) {
	this.key = value;
    }

    @Value("${mig.cookie.expiration.time}")
    public void setTiempoExpiracion(Long value) {
	this.tiempoExpiracion = value;
    }

    // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    public void addAuthentication(HttpServletResponse res, UserAccountDto us) {

	String tipoRol = us.getRol().getTipoRol();

	if(tipoRol.equals(Constantes.ROL_USUARIO)) {
		String token = Jwts.builder().setSubject(us.getUsuario().getNombre() + " " + us.getUsuario().getApellidos())

			    // Agregamos en el payload la info que necesitemos

			    .claim(Constantes.PAYLOAD_USERNAME, us.getUsername())
			    .claim(Constantes.PAYLOAD_IDUSUARIO, us.getUsuario().getId())
			    .claim(Constantes.PAYLOAD_ROL, tipoRol)

			    .setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion))

			    // Hash con el que firmaremos la clave
			    .signWith(SignatureAlgorithm.HS512, key).compact();

		    // agregamos al encabezado el token
		    res.addHeader(Constantes.AUTHORIZATION, Constantes.BEARER + " " + token);
	} else if(tipoRol.equals(Constantes.ROL_EMPRESA)) {
		String token = Jwts.builder().setSubject(us.getEmpresa().getNombre())

			    // Agregamos en el payload la info que necesitemos

			    .claim(Constantes.PAYLOAD_USERNAME, us.getUsername())
			    .claim(Constantes.PAYLOAD_IDEMPRESA, us.getEmpresa().getId())
			    .claim(Constantes.PAYLOAD_ROL, tipoRol)

			    .setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion))

			    // Hash con el que firmaremos la clave
			    .signWith(SignatureAlgorithm.HS512, key).compact();

		    // agregamos al encabezado el token
		    res.addHeader(Constantes.AUTHORIZATION, Constantes.BEARER + " " + token);
	} 
	    
	}

  @Override
  public String getTokenJWT(HttpServletRequest request) { 
  	return request.getHeader(Constantes.AUTHORIZATION);
  }
}
