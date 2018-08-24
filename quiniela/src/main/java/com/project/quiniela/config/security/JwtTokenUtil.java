package com.project.quiniela.config.security;



import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.quiniela.models.security.ConstantsSecurity;
import com.project.quiniela.models.user.User;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	
	public String getUserNameFromToken(String token) {
		String username;
		final Claims claim = getClaimFromToken(token);
		username = claim.getSubject();
		return username;
	}
	
	public Claims getClaimFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(ConstantsSecurity.SIGNING_KEY)
					.parseClaimsJws(token).getBody();
		}catch (Exception e) {
			claims = null;
		}
		return claims;
	}
	
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		
		return expiration;
	}
	
	public String generateToken(User user) {	
		return doGenerateToken(user.getNombreUsuario());
	}
	
	private String doGenerateToken(String subject) {
		Claims claims = Jwts.claims().setSubject(subject);
		claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuer("")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + ConstantsSecurity.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
				.signWith(SignatureAlgorithm.HS256, ConstantsSecurity.SIGNING_KEY)
				.compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String userName = getUserNameFromToken(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
		
	}

}
