package com.project.quiniela.models.security;

import java.io.Serializable;

public class TokenAutenticado implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;
	
	public TokenAutenticado(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
