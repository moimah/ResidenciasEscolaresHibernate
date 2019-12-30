package com.moimah.fx.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.moimah.entities.Estancias;
import com.moimah.entities.Residencias;
import com.moimah.entities.Residenciasobservaciones;
import com.moimah.entities.Universidades;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

public class ResidenciaBean {
	
	private Residencias residencia;
	
	private IntegerProperty codResidencia = new SimpleIntegerProperty();
	private ObjectProperty<Universidades> universidades = new SimpleObjectProperty<Universidades>(); //Prueba
	private StringProperty nomResidencia = new SimpleStringProperty();
	private IntegerProperty precioMensual = new SimpleIntegerProperty();
	private BooleanProperty comedor = new SimpleBooleanProperty();
	private ObjectProperty<Residenciasobservaciones> residenciasObservaciones = new SimpleObjectProperty<Residenciasobservaciones>();
	private ListProperty<EstanciasBean> estanciases = new SimpleListProperty<EstanciasBean>();
	
	
	public ResidenciaBean(Residencias residencias) {	
		
		this.residencia = residencias; 
		
		codResidencia.set(residencia.getCodResidencia());
		universidades.set(residencia.getUniversidades());
		nomResidencia.set(residencia.getNomResidencia());
		precioMensual.set(residencia.getPrecioMensual());
		comedor.set(residencia.getComedor());
		residenciasObservaciones.set(residencia.getResidenciasobservaciones());
		
		List<Estancias> list = residencia.getEstanciases(); 
		List<EstanciasBean> listBean = new ArrayList();	
		
		for (Estancias e : list) {
		    listBean.add(new EstanciasBean(e));
		}
		
		estanciases.set(FXCollections.observableArrayList(listBean));
				
		
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
	


	public final ObjectProperty<Universidades> universidadesProperty() {
		return this.universidades;
	}
	


	public final Universidades getUniversidades() {
		return this.universidadesProperty().get();
	}
	


	public final void setUniversidades(final Universidades universidades) {
		this.universidadesProperty().set(universidades);
	}
	


	public final StringProperty nomResidenciaProperty() {
		return this.nomResidencia;
	}
	


	public final String getNomResidencia() {
		return this.nomResidenciaProperty().get();
	}
	


	public final void setNomResidencia(final String nomResidencia) {
		this.nomResidenciaProperty().set(nomResidencia);
	}
	


	public final IntegerProperty precioMensualProperty() {
		return this.precioMensual;
	}
	


	public final int getPrecioMensual() {
		return this.precioMensualProperty().get();
	}
	


	public final void setPrecioMensual(final int precioMensual) {
		this.precioMensualProperty().set(precioMensual);
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
	


	public final ObjectProperty<Residenciasobservaciones> residenciasObservacionesProperty() {
		return this.residenciasObservaciones;
	}
	


	public final Residenciasobservaciones getResidenciasObservaciones() {
		return this.residenciasObservacionesProperty().get();
	}
	


	public final void setResidenciasObservaciones(final Residenciasobservaciones residenciasObservaciones) {
		this.residenciasObservacionesProperty().set(residenciasObservaciones);
	}
	


	public final ListProperty<EstanciasBean> estanciasesProperty() {
		return this.estanciases;
	}
	


	public final ObservableList<EstanciasBean> getEstanciases() {
		return this.estanciasesProperty().get();
	}
	


	public final void setEstanciases(final ObservableList<EstanciasBean> estanciases) {
		this.estanciasesProperty().set(estanciases);
	}
	

	public Residencias getResidencia() {
		return residencia;
	}


	@Override
	public String toString() {
		return getNomResidencia(); 
	}
	
	
	
	
	
	
}
