package com.moimah.fx.beans;

import com.moimah.entities.Residencias;
import com.moimah.entities.Residenciasobservaciones;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResidenciasObservacionesBean {

	
	private Residenciasobservaciones residenciasobservaciones;
	
	private IntegerProperty codResidencia = new SimpleIntegerProperty();
	private ObjectProperty<Residencias> residencias = new SimpleObjectProperty<Residencias>();
	private StringProperty observaciones  = new SimpleStringProperty();
	
	public ResidenciasObservacionesBean(Residenciasobservaciones residenciasobservaciones) {
		 this.residenciasobservaciones = residenciasobservaciones;
		 codResidencia.set(residenciasobservaciones.getCodResidencia());
		 residencias.set(residenciasobservaciones.getResidencias());
		 observaciones.set(residenciasobservaciones.getObservaciones());
	}

	public final IntegerProperty codResidenciaProperty() {
		return this.codResidencia;
	}
	

	public final int getCodResidencia() {
		return this.codResidenciaProperty().get();
	}
	

	public final void setCodResidencia(final int codResidencia) {
		this.codResidenciaProperty().set(codResidencia);
	}
	

	public final ObjectProperty<Residencias> residenciasProperty() {
		return this.residencias;
	}
	

	public final Residencias getResidencias() {
		return this.residenciasProperty().get();
	}
	

	public final void setResidencias(final Residencias residencias) {
		this.residenciasProperty().set(residencias);
	}
	

	public final StringProperty observacionesProperty() {
		return this.observaciones;
	}
	

	public final String getObservaciones() {
		return this.observacionesProperty().get();
	}
	

	public final void setObservaciones(final String observaciones) {
		this.observacionesProperty().set(observaciones);
	}
	

}
