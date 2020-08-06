package com.syscho.user.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class JwtUtils {

	@Value("${jwt.secret.key}")
	private String SECRET_KEY;

	public String createToken(String username) {
		Map<String, Object> claims = setClaims();
		return Jwts.builder().addClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	private Map<String, Object> setClaims() {
		Map<String, Object> claims = new HashMap<>();
		claims.put("org", "Syscho");
		return claims;
	}

	public Claims extaractClaims(String token) {
		 Claims body  = null;
		try {
			  body = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		} catch (SignatureException exp) {

			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,"UnAuthrorized");
		}
		return body;

	}

	public <T> T extaractClaim(String token, Function<Claims, T> claimResolver) {
		Claims claims = extaractClaims(token);

		return claimResolver.apply(claims);
	}

	public boolean isTokenExpire(String token) {
		return extaractExpireTime(token).before(new Date());
	}

	public Date extaractExpireTime(String token) {
		return extaractClaim(token, Claims::getExpiration);
	}

	public String extaractUserName(String token) {
		return extaractClaim(token, Claims::getSubject);
	}

	public boolean validateToken(String token, String userName) {
		String extaractUserName = extaractUserName(token);
		return (extaractUserName.equals(userName) && !isTokenExpire(token));

	}

}
