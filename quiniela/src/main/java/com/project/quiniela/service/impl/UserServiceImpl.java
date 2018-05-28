package com.project.quiniela.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.quiniela.dao.UserDao;
import com.project.quiniela.models.user.User;
import com.project.quiniela.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	public void prueba(User usuario) {
		
		userDao.save(usuario);
	}
	
	public List<User> findUserById(Long idUser){
		return userDao.findByIdUsuario(idUser);
	}
	
	
	public List<User> findUser(){
		return userDao.findAll();
	}
	
	public User findUserByNombreUsuario(String nombreUsuario) {
		return userDao.findByNombreUsuario(nombreUsuario);
	}

}
