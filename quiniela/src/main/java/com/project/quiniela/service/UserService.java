package com.project.quiniela.service;

import java.util.List;

import com.project.quiniela.models.user.User;

public interface UserService {
	
	public void prueba(User usuario);
	
	public List<User> findUserById(Long idUser);

	public List<User> findUser();
	
}
