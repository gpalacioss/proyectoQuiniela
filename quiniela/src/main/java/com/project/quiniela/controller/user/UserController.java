package com.project.quiniela.controller.user;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.quiniela.models.user.User;
import com.project.quiniela.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private ObjectMapper objectMapper;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@RequestMapping(value = "/saveUsuario", method = RequestMethod.POST)
	public Response saveOrUpdateUser(@RequestBody String userJson) throws JsonParseException, JsonMappingException, IOException {
		
		objectMapper = new ObjectMapper();
		
		User usuario = objectMapper.readValue(userJson, User.class);
		
		System.out.println("nombre de usuario :: " + usuario.getNombreUsuario());
		
		if(!validaUsuario(usuario.getNombreUsuario()) || usuario.getIdUsuario() != null){
			usuario.setFechaCreacion(new Date());
			usuario.setPassword(bcryptPasswordEncoder.encode(usuario.getPassword()));
			userService.saveOrUpdate(usuario);
			return Response.accepted().build();
		}else {
			System.out.println("no guardo nada");
			return Response.status(Response.Status.NOT_MODIFIED).build();
		}
		
	}
	
	private boolean validaUsuario(String nombreUsuario) {
		boolean respuesta = false;
		
		User usuario = userService.findUserByNombreUsuario(nombreUsuario);
		
		if (usuario != null) {
			respuesta = true;
		}
		
		return respuesta;
	}
	
	@RequestMapping(value = "/existeUsuario", method = RequestMethod.POST)
	public User validaUserLogin(@RequestBody String usuario) throws JsonParseException, JsonMappingException, IOException{
		objectMapper = new ObjectMapper();
		
		User user = objectMapper.readValue(usuario, User.class);
		
		user = userService.findUserByNombreUsuario(user.getNombreUsuario());
		return user;
	}
	
	
	@RequestMapping(value = "/getUsuarios", method = RequestMethod.GET)
	public List<User> getUsuarios() {
		
		List<User> result = userService.findUser();
		
		return result;
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public void deleteUsuario(@RequestBody String jsonUsuario) throws JsonParseException, JsonMappingException, IOException {
		
		objectMapper = new ObjectMapper();
		
		User usuario = objectMapper.readValue(jsonUsuario, User.class);
		
		if (usuario.getIdUsuario() != null) {
			userService.deleteUser(usuario.getIdUsuario());
		}
	}

}
