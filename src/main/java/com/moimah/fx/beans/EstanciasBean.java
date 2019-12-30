package com.moimah.fx.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.result.NoMoreReturnsException;

import com.moimah.entities.Estancias;
import com.moimah.entities.Estudiantes;
import com.moimah.entities.Residencias;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class EstanciasBean {

	private Estancias estancias;
	private IntegerProperty codEstancia = new SimpleIntegerProperty();
	private ObjectProperty<EstudiantesBean> estudiante = new SimpleObjectProperty<EstudiantesBean>();
	private ObjectProperty<Residencias> residencias = new SimpleObjectProperty<Residencias>(); //Esta mal se debería arreglar
	private ObjectProperty<Date> fechaInicio = new SimpleObjectProperty<Date>(); 
	private ObjectProperty<Date> fechaFin = new SimpleObjectProperty<Date>(); 
	private ObjectProperty<Integer> precioPagado = new SimpleObjectProperty<Integer>(); 
	
	private ListProperty<EstudiantesBean> listEstudiante  = new SimpleListProperty<EstudiantesBean>();
	
	public EstanciasBean(Estancias estancia) {
		this.estancias = estancia; 
		codEstancia.set(estancias.getCodEstancia());				
		residencias.set(estancias.getResidencias());			
		fechaInicio.set(estancias.getFechaInicio());		
		fechaFin.set(estancias.getFechaFin());		
		precioPagado.set(Integer.valueOf(estancias.getPrecioPagado()));		
		
		EstudiantesBean estudianteBean = new EstudiantesBean(estancias.getEstudiantes());
		estudiante.set(estudianteBean);
		
		
		//Nuevo
		ArrayList<EstudiantesBean> list = new ArrayList<EstudiantesBean>(); 
		list.add(estudianteBean);
		listEstudiante.set(FXCollections.observableArrayList(list));
		
	}

	public final IntegerProperty codEstanciaProperty() {
		return this.codEstancia;
	}
	

	public final int getCodEstancia() {
		return this.codEstanciaProperty().get();
	}
	

	public final void setCodEstancia(final int codEstancia) {
		this.codEstanciaProperty().set(codEstancia);
	}
	

	public final ObjectProperty<EstudiantesBean> estudianteProperty() {
		return this.estudiante;
	}
	

	public final EstudiantesBean getEstudiante() {
		return this.estudianteProperty().get();
	}
	

	public final void setEstudiante(final EstudiantesBean estudiante) {
		this.estudianteProperty().set(estudiante);
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
	

	public final ObjectProperty<Date> fechaInicioProperty() {
		return this.fechaInicio;
	}
	

	public final Date getFechaInicio() {
		return this.fechaInicioProperty().get();
	}
	

	public final void setFechaInicio(final Date fechaInicio) {
		this.fechaInicioProperty().set(fechaInicio);
	}
	

	public final ObjectProperty<Date> fechaFinProperty() {
		return this.fechaFin;
	}
	

	public final Date getFechaFin() {
		return this.fechaFinProperty().get();
	}
	

	public final void setFechaFin(final Date fechaFin) {
		this.fechaFinProperty().set(fechaFin);
	}
	

	public final ObjectProperty<Integer> precioPagadoProperty() {
		return this.precioPagado;
	}
	

	public final Integer getPrecioPagado() {
		return this.precioPagadoProperty().get();
	}
	

	public final void setPrecioPagado(final Integer precioPagado) {
		this.precioPagadoProperty().set(precioPagado);
	}
	

	public final ListProperty<EstudiantesBean> listEstudianteProperty() {
		return this.listEstudiante;
	}
	

	public final ObservableList<EstudiantesBean> getListEstudiante() {
		return this.listEstudianteProperty().get();
	}
	

	public final void setListEstudiante(final ObservableList<EstudiantesBean> listEstudiante) {
		this.listEstudianteProperty().set(listEstudiante);
	}
	
	
	public Estancias getEstancias() {
		return estancias;
	}

	

		
	
}
