package com.moimah.fx.beans;

import java.util.HashSet;
import java.util.Set;

import com.moimah.entities.Estancias;
import com.moimah.entities.Estudiantes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

public class EstudiantesBean {
	
	private Estudiantes estudiantes;
	private IntegerProperty codEstudiante = new SimpleIntegerProperty();
	private StringProperty dni = new SimpleStringProperty();
	private StringProperty nomEstudiante = new SimpleStringProperty();
	private StringProperty telefonoEstudiante = new SimpleStringProperty();
	private ListProperty<Estancias> estanciases = new SimpleListProperty<Estancias>();
	
	public EstudiantesBean(Estudiantes estudiante) {
		this.estudiantes = estudiante;
		codEstudiante.set(estudiantes.getCodEstudiante());
		dni.set(estudiantes.getDni());
		nomEstudiante.set(estudiantes.getNomEstudiante());
		telefonoEstudiante.set(estudiantes.getTelefonoEstudiante());
		estanciases.set(FXCollections.observableList(estudiantes.getEstanciases()));
		
	}

	public final IntegerProperty codEstudianteProperty() {
		return this.codEstudiante;
	}
	

	public final int getCodEstudiante() {
		return this.codEstudianteProperty().get();
	}
	

	public final void setCodEstudiante(final int codEstudiante) {
		this.codEstudianteProperty().set(codEstudiante);
	}

	public final StringProperty dniProperty() {
		return this.dni;
	}
	

	public final String getDni() {
		return this.dniProperty().get();
	}
	

	public final void setDni(final String dni) {
		this.dniProperty().set(dni);
	}
	

	public final StringProperty nomEstudianteProperty() {
		return this.nomEstudiante;
	}
	

	public final String getNomEstudiante() {
		return this.nomEstudianteProperty().get();
	}
	

	public final void setNomEstudiante(final String nomEstudiante) {
		this.nomEstudianteProperty().set(nomEstudiante);
	}
	

	public final StringProperty telefonoEstudianteProperty() {
		return this.telefonoEstudiante;
	}
	

	public final String getTelefonoEstudiante() {
		return this.telefonoEstudianteProperty().get();
	}
	

	public final void setTelefonoEstudiante(final String telefonoEstudiante) {
		this.telefonoEstudianteProperty().set(telefonoEstudiante);
	}
	

	public final ListProperty<Estancias> estanciasesProperty() {
		return this.estanciases;
	}
	

	public final ObservableList<Estancias> getEstanciases() {
		return this.estanciasesProperty().get();
	}
	

	public final void setEstanciases(final ObservableList<Estancias> estanciases) {
		this.estanciasesProperty().set(estanciases);
	}
	
	public Estudiantes getEstudiantes() {
		return estudiantes;
	}
	

	@Override
	public String toString() {
		
		return getNomEstudiante();
	}
	
	
	
	
}
