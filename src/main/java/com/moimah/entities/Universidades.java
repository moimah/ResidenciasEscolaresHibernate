package com.moimah.entities;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Universidades 
 */
@Entity
@Table(name = "universidades", catalog = "estanciashibernate_moises_abreu")
public class Universidades implements java.io.Serializable {

	private String codUniversidad;
	private String nomUniversidad;
	private List<Residencias> residenciases = new ArrayList<Residencias>(0);

	public Universidades() {
	}

	public Universidades(String codUniversidad) {
		this.codUniversidad = codUniversidad;
	}

	public Universidades(String codUniversidad, String nomUniversidad, List<Residencias> residenciases) {
		this.codUniversidad = codUniversidad;
		this.nomUniversidad = nomUniversidad;
		this.residenciases = residenciases;
	}

	@Id

	@Column(name = "codUniversidad", unique = true, nullable = false, length = 16)
	public String getCodUniversidad() {
		return this.codUniversidad;
	}

	public void setCodUniversidad(String codUniversidad) {
		this.codUniversidad = codUniversidad;
	}

	@Column(name = "nomUniversidad", length = 30)
	public String getNomUniversidad() {
		return this.nomUniversidad;
	}

	public void setNomUniversidad(String nomUniversidad) {
		this.nomUniversidad = nomUniversidad;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "universidades", cascade = {CascadeType.ALL})
	public List<Residencias> getResidenciases() {
		return this.residenciases;
	}

	public void setResidenciases(List<Residencias> residenciases) {
		this.residenciases = residenciases;
	}

	@Override
	public String toString() {
		return  nomUniversidad;
	}
	
	

}
