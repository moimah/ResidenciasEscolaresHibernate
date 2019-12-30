package com.moimah.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Residenciasobservaciones
 */
@Entity
@Table(name = "residenciasobservaciones", catalog = "estanciashibernate_moises_abreu")
public class Residenciasobservaciones implements java.io.Serializable {

	private int codResidencia;
	private Residencias residencias;
	private String observaciones;

	public Residenciasobservaciones() {
	}
	
	

	public Residenciasobservaciones(String observaciones) {
		super();
		this.observaciones = observaciones;
	}



	public Residenciasobservaciones(Residencias residencias) {
		this.residencias = residencias;
	}

	public Residenciasobservaciones(Residencias residencias, String observaciones) {
		this.residencias = residencias;
		this.observaciones = observaciones;
	}

	@GenericGenerator(name = "com.moimah.hibernate.ResidenciasobservacionesIdGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "residencias"))
	@Id
	@GeneratedValue(generator = "com.moimah.hibernate.ResidenciasobservacionesIdGenerator")

	@Column(name = "codResidencia", unique = true, nullable = false)
	public int getCodResidencia() {
		return this.codResidencia;
	}

	public void setCodResidencia(int codResidencia) {
		this.codResidencia = codResidencia;
	}

	@OneToOne(fetch = FetchType.LAZY, optional = false)	
	@PrimaryKeyJoinColumn
	public Residencias getResidencias() {
		return this.residencias;
	}

	public void setResidencias(Residencias residencias) {
		this.residencias = residencias;
	}

	@Column(name = "observaciones", length = 200)
	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

}
