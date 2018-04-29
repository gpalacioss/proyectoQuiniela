package com.project.quiniela.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.quiniela.models.user.User;


public interface UserDao extends JpaRepository<User, Long>{

	@SuppressWarnings("unchecked")
	User save(User usuario);
	
	List<User> findByIdUsuario(Long idUsuario);
	
	long count();
	
}
