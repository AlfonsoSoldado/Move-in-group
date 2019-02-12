package com.moveingroup.security.front;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.moveingroup.security.token.MigToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AbstractJwtUtilFront implements Serializable {

	
    private static final long serialVersionUID = 6651392100358580755L;

    protected String key;

    protected long tiempoExpiracion;

    // Método para validar el token enviado por el cliente
    public Authentication getAuthentication(HttpServletRequest request) throws JsonProcessingException {

	// Obtenemos el token que viene en el encabezado de la peticion
	String token = getAuthorizationFromCookie(request);

	// si hay un token presente, entonces lo validamos
	if (token != null && isValidJWT(token)) {
	    String user = Jwts.parser().setSigningKey(key).parseClaimsJws(token.replace("Bearer", ""))
		    .getBody().getSubject();

	    Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	    String dni = (String) claims.get("dni");
	    Integer idUsuario = (Integer) claims.get("idUsuario");
	    String tipoRol = (String) claims.get("rol");

	    Map<String, String> mapClaims = new HashMap<>();

	    mapClaims.put("dni", dni);
	    if (idUsuario != null) {
		mapClaims.put("idUsuario", idUsuario.toString());
	    }
	    mapClaims.put("rol", tipoRol);

	    // Recordamos que para las demás peticiones que no sean /login
	    // no requerimos una autenticacion por username/password
	    // por este motivo podemos devolver un DatToken con el user

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

	    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + tipoRol));
	    return user != null ? new MigToken(user, null, grantedAuthorities, mapClaims) : null;
	}
	return null;
    }

    public String getAuthorizationFromCookie(HttpServletRequest request) {
	CookieHelper cookieHelper = new CookieHelper();
	Cookie cookie = cookieHelper.getCookie(request, "token");
	String token = null;
	if (cookie != null && cookie.getValue() != null) {
	    token = cookie.getValue();
	}
	return token;
    }
    
    public boolean isValidJWT(String tokenJWT) {
	boolean valid = false;
	try {
	    // si hay un token presente, entonces lo validamos
	    if (tokenJWT != null) {
		// Validamos el token
		Jwts.parser().setSigningKey(key).parseClaimsJws(tokenJWT);
		valid = true;
	    }
	} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
		| IllegalArgumentException e) {
	    log.info(e.getMessage(), e);
	}

	return valid;
    }
}
