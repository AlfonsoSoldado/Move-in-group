package com.moveingroup.security.back;

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

	private static final long serialVersionUID = -2136794809248574922L;

    @Value("$mig.security.key}")
    public void setKey(String value) {
	this.key = value;
    }

    @Value("${mig.cookie.expiration.time}")
    public void setTiempoExpiracion(Long value) {
	this.tiempoExpiracion = value;
    }

    // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    public void addAuthentication(HttpServletResponse res, UserAccountDto us) {

	String tipoRol = us.getRolDto().getTipoRol();

	if(tipoRol.equals(Constantes.ROL_USUARIO)) {
		String token = Jwts.builder().setSubject(us.getUsuarioDto().getNombre() + " " + us.getUsuarioDto().getApellidos())

			    // Agregamos en el payload la info que necesitemos

			    .claim("username", us.getUsername())
			    .claim("idUsuario", us.getUsuarioDto().getId())
			    .claim("rol", tipoRol)

			    .setExpiration(new Date(System.currentTimeMillis() + tiempoExpiracion))

			    // Hash con el que firmaremos la clave
			    .signWith(SignatureAlgorithm.HS512, key).compact();

		    // agregamos al encabezado el token
		    res.addHeader("Authorization", "Bearer" + " " + token);
	}
	    
	}

    @Override
    public String getTokenJWT(HttpServletRequest request) {
	return request.getHeader("Authorization");
    }
}
