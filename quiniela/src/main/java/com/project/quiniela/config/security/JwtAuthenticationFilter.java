package com.project.quiniela.config.security;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.quiniela.models.security.ConstantsSecurity;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	/**
	 * esta clase lo proporciona spring security
	 **/
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		String authToken = request.getHeader(ConstantsSecurity.HEADER_STRING);
		String username = null;
		
//      authToken.startsWith("Bearer ");
//      authToken = authToken.substring(7);
		
		if (authToken != null) {
			
			try {
				
				username = jwtTokenUtil.getUserNameFromToken(authToken);
				
			} catch (IllegalArgumentException e) {
				
				logger.error("Error al obtener el nombre del usuario del token" + e);
				
			} catch (ExpiredJwtException e) {
				
                logger.warn("El token ha expirado y ya no es valido", e);
                
            } catch(SignatureException e){
            	
                logger.error("Auternticacion fallida el nombre de usuario o contraseña no son validos.");
                
            }
			
		}else {
			logger.error("no encuentra la cadena portadora, ignorara el encabezado");
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if (jwtTokenUtil.validateToken(authToken, userDetails)) {
				
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("Usuario autenticado :: " + username + ", establecer contexto de seguirdad");
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
		
}
