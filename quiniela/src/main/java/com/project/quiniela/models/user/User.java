package com.project.quiniela.models.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private Long idUsuario;
	
	
	@Column(name = "USERNAME")
	private String username;
	
	
	@Column(name = "PASSWORD")
	private String password;
	
	
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	
	@Column(name = "ESTATUS")
	private boolean estatus = true;
	
	
	
	@ManyToMany
	@JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")},
	inverseJoinColumns = {@JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE")})
	private Set<Role> roles;



	public Long getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public boolean getEstatus() {
		return estatus;
	}



	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}



	public Set<Role> getRoles() {
		return roles;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
