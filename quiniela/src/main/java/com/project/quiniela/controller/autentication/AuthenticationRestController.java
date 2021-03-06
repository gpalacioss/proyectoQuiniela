package com.project.quiniela.controller.autentication;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.quiniela.config.security.JwtAuthenticationRequest;
import com.project.quiniela.config.security.JwtTokenUtil;
import com.project.quiniela.models.security.JwtAuthenticationResponse;
import com.project.quiniela.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value = "/token")
public class AuthenticationRestController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@RequestMapping(value = "/genera-token", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody JwtAuthenticationRequest jwtAuthenticationRequest) throws AuthenticationException{
		
		final UserDetails user = (UserDetails) userDetailsService.loadUserByUsername(jwtAuthenticationRequest.getUsername());
		
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
		//Authentication auth = authenticationManager.authenticate(authReq);
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(authReq);
		
		final String token = jwtTokenUtil.generateToken(user);
		
		System.out.println("Valor del Token:: " + token);
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

}
