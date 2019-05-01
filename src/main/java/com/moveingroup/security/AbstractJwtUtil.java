package com.moveingroup.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private static final long serialVersionUID = 6651392100358580755L;

	protected String key;

	protected long tiempoExpiracion;

	public abstract String getTokenJWT(HttpServletRequest request);

	public Authentication getAuthentication(HttpServletRequest request) throws JsonProcessingException {

		// Obtenemos el token que viene en el encabezado de la peticion
		String tokenAux = request.getHeader("token");
		String token = tokenAux != null ? tokenAux.replace(Constantes.BEARER, "") : null;

		// si hay un token presente, entonces lo validamos
		if (token != null && isValidJWT(token)) {
			String user = Jwts.parser().setSigningKey(key).parseClaimsJws(token.replace(Constantes.BEARER, "")).getBody()
					.getSubject();

			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			String username = (String) claims.get(Constantes.PAYLOAD_USERNAME);
			Integer idUsuario = (Integer) claims.get(Constantes.PAYLOAD_IDUSUARIO);
			Integer idEmpresa = (Integer) claims.get(Constantes.PAYLOAD_IDEMPRESA);
			String tipoRol = (String) claims.get(Constantes.PAYLOAD_ROL);

			Map<String, String> mapClaims = new HashMap<>();

			mapClaims.put(Constantes.PAYLOAD_USERNAME, username);
			mapClaims.put(Constantes.PAYLOAD_ROL, tipoRol);
			mapClaims.put(Constantes.PAYLOAD_IDUSUARIO, idUsuario.toString());
			mapClaims.put(Constantes.PAYLOAD_IDEMPRESA, idEmpresa.toString());

			// Recordamos que para las demás peticiones que no sean /login
			// no requerimos una autenticacion por username/password
			// por este motivo podemos devolver un MigToken con el usuario

			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

			if (tipoRol != null) {
				grantedAuthorities.add(new SimpleGrantedAuthority(tipoRol));
			}

			return user != null ? new MigToken(user, null, grantedAuthorities, mapClaims) : null;
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
