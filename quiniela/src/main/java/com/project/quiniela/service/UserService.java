package com.project.quiniela.service;

import java.util.List;

import com.project.quiniela.models.user.User;

public interface UserService {
	
	public User saveOrUpdate(User usuario);
	
	public List<User> findUserById(Long idUser);

	public List<User> findUser();
	
	public User findUserByNombreUsuario(String nombreUsuario);
	
	public void deleteUser(Long idUsuario);

	
}
