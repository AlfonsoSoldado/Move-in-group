package com.moveingroup.security;

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
import com.moveingroup.utils.Constantes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractJwtUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String key;

	protected long tiempoExpiracion;

	public abstract String getTokenJWT(HttpServletRequest request);

	public Authentication getAuthentication(HttpServletRequest request) throws JsonProcessingException {

		// Obtenemos el token que está en las cookies del navegador
		Cookie[] cookies = request.getCookies();
		String tokenAux = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(Constantes.TOKEN)) {
					tokenAux = c.getValue();
				}
			}
		}
		String token = tokenAux != null ? tokenAux.replace(Constantes.BEARER, "") : null;

		// validar token
		if (token != null && isValidJWT(token)) {
			String user = Jwts.parser().setSigningKey(key).parseClaimsJws(token.replace(Constantes.BEARER, ""))
					.getBody().getSubject();

			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			String username = (String) claims.get(Constantes.PAYLOAD_USERNAME);
			String rol = (String) claims.get(Constantes.PAYLOAD_ROL);
			Integer idUsuario = (Integer) claims.get(Constantes.PAYLOAD_IDUSUARIO);
			Integer idEmpresa = (Integer) claims.get(Constantes.PAYLOAD_IDEMPRESA);

			Map<String, String> mapClaims = new HashMap<>();

			mapClaims.put(Constantes.PAYLOAD_USERNAME, username);
			mapClaims.put(Constantes.PAYLOAD_ROL, rol);
			if(idUsuario != null) {
				mapClaims.put(Constantes.PAYLOAD_IDUSUARIO, Long.toString(idUsuario));
			}
			if(idEmpresa != null) {
				mapClaims.put(Constantes.PAYLOAD_IDEMPRESA, Long.toString(idEmpresa));
			}
			
			// Recordamos que para las demás peticiones que no sean /login
			// no requerimos una autenticacion por username/password
			// por este motivo podemos devolver un Token con el usuario

			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(rol));

			return user != null ? new MigToken(user, null, authorities, mapClaims) : null;
		}
		return null;
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
