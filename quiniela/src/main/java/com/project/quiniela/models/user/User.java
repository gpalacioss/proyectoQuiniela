package com.project.quiniela.models.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;


    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;


    @Column(name = "PASSWORD")
    private String password;


    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;


    @Column(name = "ESTATUS")
    private boolean estatus = true;



    @ManyToOne
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL")
    private Profile perfil;



    public Long getIdUsuario() {
        return idUsuario;
    }



    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }



    public String getNombreUsuario() {
        return nombreUsuario;
    }



    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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



    public Profile getPerfil() {
        return perfil;
    }



    public void setPerfil(Profile perfil) {
        this.perfil = perfil;
    }



}
