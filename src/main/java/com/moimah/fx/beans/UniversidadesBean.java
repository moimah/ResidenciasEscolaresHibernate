package com.moimah.fx.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.moimah.entities.Residencias;
import com.moimah.entities.Universidades;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

public class UniversidadesBean {
	
	private Universidades universidad;
	
	private StringProperty codUniversidad = new SimpleStringProperty();
	private StringProperty nomUniversidad = new SimpleStringProperty(); 
	private	ListProperty<ResidenciaBean> residenciases = new SimpleListProperty<ResidenciaBean>();	
	
	
	public UniversidadesBean(Universidades universidades) {
		
		this.universidad = universidades;		
		codUniversidad.set(universidad.getCodUniversidad());
		nomUniversidad.set(universidad.getNomUniversidad());		
			
		List<Residencias> list = universidad.getResidenciases(); 
		List<ResidenciaBean> listBean = new ArrayList<ResidenciaBean>(); 
		
		for(Residencias r : list) {
			listBean.add(new ResidenciaBean(r));
		}
		
		residenciases.set(FXCollections.observableArrayList(listBean));
		
	}


	public final StringProperty codUniversidadProperty() {
		return this.codUniversidad;
	}
	


	public final String getCodUniversidad() {
		return this.codUniversidadProperty().get();
	}
	


	public final void setCodUniversidad(final String codUniversidad) {
		this.codUniversidadProperty().set(codUniversidad);
	}
	


	public final StringProperty nomUniversidadProperty() {
		return this.nomUniversidad;
	}
	


	public final String getNomUniversidad() {
		return this.nomUniversidadProperty().get();
	}
	


	public final void setNomUniversidad(final String nomUniversidad) {
		this.nomUniversidadProperty().set(nomUniversidad);
	}
	


	public final ListProperty<ResidenciaBean> residenciasesProperty() {
		return this.residenciases;
	}
	


	public final ObservableList<ResidenciaBean> getResidenciases() {
		return this.residenciasesProperty().get();
	}
	


	public final void setResidenciases(final ObservableList<ResidenciaBean> residenciases) {
		this.residenciasesProperty().set(residenciases);
	}
	
	public Universidades getUniversidad() {
		return universidad;
	}
	

	@Override
	public String toString() {		
		return getNomUniversidad();
	}

	
}
