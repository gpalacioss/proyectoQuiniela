package com.project.quiniela.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.quiniela.models.user.User;
import com.project.quiniela.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private ObjectMapper objectMapper;
	
	@RequestMapping(value = "/saveUsuario", method = RequestMethod.POST)
	public void saveOrUpdateUser(@RequestBody String userJson) throws JsonParseException, JsonMappingException, IOException {
		
		objectMapper = new ObjectMapper();
		
		User usuario = objectMapper.readValue(userJson, User.class);
		
		System.out.println("nombre de usuario :: " + usuario.getNombreUsuario());
		
	}
	
	
	@RequestMapping(value = "/getUsuarios", method = RequestMethod.GET)
	public void getUsuarios() {
		
		List<User> result = userService.findUser();
		
		result.forEach(usuario -> {
			
			System.out.println("======================================================");
			System.out.println("nombre :: " + usuario.getNombreUsuario());
			System.out.println("estatus " + usuario.getEstatus());
			System.out.println("fecha "  + usuario.getFechaCreacion());
			System.out.println("nombre del perfil "  + usuario.getPerfil().getNombrePerfil());
			System.out.println("======================================================");
		});
	}

}
