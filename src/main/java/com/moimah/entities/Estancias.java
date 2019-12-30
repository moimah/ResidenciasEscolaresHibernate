package com.moimah.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Estancias 
 */
@Entity
@Table(name = "estancias", catalog = "estanciashibernate_moises_abreu")
public class Estancias implements java.io.Serializable {

	private int codEstancia;
	private Estudiantes estudiantes;
	private Residencias residencias;
	private Date fechaInicio;
	private Date fechaFin;
	private Short precioPagado;

	public Estancias() {
	}

	public Estancias(int codEstancia) {
		this.codEstancia = codEstancia;
	}

	public Estancias(int codEstancia, Estudiantes estudiantes, Residencias residencias, Date fechaInicio, Date fechaFin,
			Short precioPagado) {
		this.codEstancia = codEstancia;
		this.estudiantes = estudiantes;
		this.residencias = residencias;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precioPagado = precioPagado;
	}

	@Id

	@Column(name = "codEstancia", unique = true, nullable = false)
	public int getCodEstancia() {
		return this.codEstancia;
	}

	public void setCodEstancia(int codEstancia) {
		this.codEstancia = codEstancia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codEstudiante")
	public Estudiantes getEstudiantes() {
		return this.estudiantes;
	}

	public void setEstudiantes(Estudiantes estudiantes) {
		this.estudiantes = estudiantes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codResidencia")
	public Residencias getResidencias() {
		return this.residencias;
	}

	public void setResidencias(Residencias residencias) {
		this.residencias = residencias;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaInicio", length = 10)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaFin", length = 10)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "precioPagado")
	public Short getPrecioPagado() {
		return this.precioPagado;
	}

	public void setPrecioPagado(Short precioPagado) {
		this.precioPagado = precioPagado;
	}

	@Override
	public String toString() {
		return "codEstancia=" + codEstancia + ", estudiantes=" + estudiantes + ", residencias=" + residencias
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", precioPagado=" + precioPagado;
	}
	
	
	

}
