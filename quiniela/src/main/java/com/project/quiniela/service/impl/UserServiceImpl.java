package com.project.quiniela.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.quiniela.dao.EntityService;
import com.project.quiniela.models.user.User;
import com.project.quiniela.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private EntityService entityService;
	
	public void prueba(User usuario) {
		
		entityService.merge(usuario);
	}
	
	public List<User> findUserById(Long idUser){
		return entityService.findWithQuery("from User u where u.idUsuario = ? ", idUser);
	}
	

}
