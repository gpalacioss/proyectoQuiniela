package com.project.quiniela.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.SimpleIdGenerator;

import com.project.quiniela.dao.UserDao;
import com.project.quiniela.models.user.User;
import com.project.quiniela.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService{
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * Metodo que se sobreescribe por implementar de la interfaz de spring security UserDetailsService para obtener el usuario
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User usuario = findUserByNombreUsuario(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Nombre de usuario invalido");
		}
		
		return new org.springframework.security.core.userdetails.User(usuario.getNombreUsuario(), usuario.getPassword(), getAuthority());
	}
	
	
	private List<SimpleGrantedAuthority> getAuthority(){
		return Arrays.asList(new SimpleGrantedAuthority("ADMINISTRADOR"));
	}
	
	public User saveOrUpdate(User usuario) {
		
		return userDao.save(usuario);
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
	
	public void deleteUser(Long idUsuario) {
		userDao.deleteById(idUsuario);
	}

	

}
