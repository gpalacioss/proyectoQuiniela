package com.project.quiniela.config.security;



import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.project.quiniela.models.security.ConstantsSecurity;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	static final String CLAIM_KEY_AUDIENCE = "audience";
	static final String CLAIM_KEY_CREATED = "created";

	
	public String getUserNameFromToken(String token) {
		String username;
		try {
			
			final Claims claim = getClaimsFromToken(token);
			username = claim.getSubject();
			
		} catch (Exception e) {
			
			username = null;
		}
		
		return username;
	}
	
	
	public Date getCreatedDateFromToken(String token) {
	        Date created;
	        try {
	            final Claims claims = getClaimsFromToken(token);
	            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
	        } catch (Exception e) {
	            created = null;
	        }
	        return created;
	 }
	
	public Claims getClaimsFromToken(String token) {
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
	
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		
		return expiration;
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }
	
	
	public String generateToken(UserDetails user) {	
		return doGenerateToken(user.getUsername(), user.getPassword());
	}
	
	private String doGenerateToken(String subject, String pass) {
		Claims claims = Jwts.claims().setSubject(subject);
		//claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
		claims.put("scopes", pass);
		
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
