package com.moimah.entities;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Residencias
 */
@Entity
@Table(name = "residencias", catalog = "estanciashibernate_moises_abreu")
public class Residencias implements java.io.Serializable {

	private Integer codResidencia;
	private Universidades universidades;
	private String nomResidencia;
	private Short precioMensual;
	private Boolean comedor;
	private Residenciasobservaciones residenciasobservaciones;
	private List<Estancias> estanciases = new ArrayList<Estancias>(0);

	public Residencias() {
	}

	public Residencias(Universidades universidades, String nomResidencia, Short precioMensual, Boolean comedor,
			Residenciasobservaciones residenciasobservaciones, List<Estancias> estanciases) {
		this.universidades = universidades;
		this.nomResidencia = nomResidencia;
		this.precioMensual = precioMensual;
		this.comedor = comedor;
		this.residenciasobservaciones = residenciasobservaciones;
		this.estanciases = estanciases;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "codResidencia", unique = true, nullable = false)
	public Integer getCodResidencia() {
		return this.codResidencia;
	}

	public void setCodResidencia(Integer codResidencia) {
		this.codResidencia = codResidencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codUniversidad")
	public Universidades getUniversidades() {
		return this.universidades;
	}

	public void setUniversidades(Universidades universidades) {
		this.universidades = universidades;
	}

	@Column(name = "nomResidencia", length = 30)
	public String getNomResidencia() {
		return this.nomResidencia;
	}

	public void setNomResidencia(String nomResidencia) {
		this.nomResidencia = nomResidencia;
	}

	@Column(name = "precioMensual")
	public Short getPrecioMensual() {
		return this.precioMensual;
	}

	public void setPrecioMensual(Short precioMensual) {
		this.precioMensual = precioMensual;
	}

	@Column(name = "comedor")
	public Boolean getComedor() {
		return this.comedor;
	}

	public void setComedor(Boolean comedor) {
		this.comedor = comedor;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "residencias", cascade = CascadeType.ALL)
	public Residenciasobservaciones getResidenciasobservaciones() {
		return this.residenciasobservaciones;
	}

	public void setResidenciasobservaciones(Residenciasobservaciones residenciasobservaciones) {
		this.residenciasobservaciones = residenciasobservaciones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "residencias", cascade = CascadeType.ALL)
	public List<Estancias> getEstanciases() {
		return this.estanciases;
	}

	public void setEstanciases(List<Estancias> estanciases) {
		this.estanciases = estanciases;
	}
	
	@Override
	public String toString() {		
		return nomResidencia;
	}

}
