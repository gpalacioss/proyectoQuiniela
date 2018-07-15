package com.project.quiniela.models.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.quiniela.enums.NombrePerfil;


@Entity
@Table(name = "PERFIL")
public class Profile implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERFIL")
    private Long idPerfil;

    @Column(name = "NOMBRE_PERFIL")
    @Enumerated(EnumType.STRING)
    private NombrePerfil nombrePerfil;

    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public NombrePerfil getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(NombrePerfil nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }




}
