package com.project.quiniela.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.quiniela.dao.UserDao;
import com.project.quiniela.models.user.Role;
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
		
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), getAuthority(usuario.getRoles()));
	}
	
	
	private List<SimpleGrantedAuthority> getAuthority(Set<Role> roles){
		return roles.stream().map(authority -> new SimpleGrantedAuthority( authority.getNombreRole().name())).collect(Collectors.toList());
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
		return userDao.findByUsername(nombreUsuario);
	}
	
	public void deleteUser(Long idUsuario) {
		userDao.deleteById(idUsuario);
	}

	

}
