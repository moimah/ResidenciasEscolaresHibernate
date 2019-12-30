package com.moimah.fx.dialogs;

import com.moimah.entities.Universidades;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class ResidenciaDialogModel {
	
	private StringProperty nombreResidencia = new SimpleStringProperty();
	private BooleanProperty comedor = new SimpleBooleanProperty(); 
	private DoubleProperty precioMensual = new SimpleDoubleProperty();
	private ListProperty<Universidades> universidades = new SimpleListProperty<Universidades>(); 
	
	private ObjectProperty<Universidades> universidadSeleccionada  = new SimpleObjectProperty<Universidades>();
	
	private StringProperty observacion = new SimpleStringProperty();
	
	
	public final StringProperty nombreResidenciaProperty() {
		return this.nombreResidencia;
	}
	
	public final String getNombreResidencia() {
		return this.nombreResidenciaProperty().get();
	}
	
	public final void setNombreResidencia(final String nombreResidencia) {
		this.nombreResidenciaProperty().set(nombreResidencia);
	}
	
	public final BooleanProperty comedorProperty() {
		return this.comedor;
	}
	
	public final boolean isComedor() {
		return this.comedorProperty().get();
	}
	
	public final void setComedor(final boolean comedor) {
		this.comedorProperty().set(comedor);
	}
	
	public final DoubleProperty precioMensualProperty() {
		return this.precioMensual;
	}
	
	public final double getPrecioMensual() {
		return this.precioMensualProperty().get();
	}
	
	public final void setPrecioMensual(final double precioMensual) {
		this.precioMensualProperty().set(precioMensual);
	}

	public final ListProperty<Universidades> universidadesProperty() {
		return this.universidades;
	}
	

	public final ObservableList<Universidades> getUniversidades() {
		return this.universidadesProperty().get();
	}
	

	public final void setUniversidades(final ObservableList<Universidades> universidades) {
		this.universidadesProperty().set(universidades);
	}

	public final ObjectProperty<Universidades> universidadSeleccionadaProperty() {
		return this.universidadSeleccionada;
	}
	

	public final Universidades getUniversidadSeleccionada() {
		return this.universidadSeleccionadaProperty().get();
	}
	

	public final void setUniversidadSeleccionada(final Universidades universidadSeleccionada) {
		this.universidadSeleccionadaProperty().set(universidadSeleccionada);
	}

	public final StringProperty observacionProperty() {
		return this.observacion;
	}
	

	public final String getObservacion() {
		return this.observacionProperty().get();
	}
	

	public final void setObservacion(final String observacion) {
		this.observacionProperty().set(observacion);
	}
	
	
	

}
