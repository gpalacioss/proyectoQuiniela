package com.project.quiniela.controller.autentication;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.quiniela.config.security.JwtAuthenticationRequest;
import com.project.quiniela.config.security.JwtTokenUtil;
import com.project.quiniela.models.security.TokenAutenticado;
import com.project.quiniela.models.user.User;
import com.project.quiniela.service.UserService;

@RestController
@RequestMapping(value = "/token")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/genera-token", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) throws AuthenticationException{
		
		final Authentication autenticacion = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						jwtAuthenticationRequest.getUsername(), 
						jwtAuthenticationRequest.getPassword()
				)
		);
		
		SecurityContextHolder.getContext().setAuthentication(autenticacion);
		
		final User usuario = userService.findUserByNombreUsuario(jwtAuthenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(usuario);
		return ResponseEntity.ok(new TokenAutenticado(token));
	}

}
