package com.moimah.fx.dialogs;

import java.time.LocalDate;
import com.moimah.fx.beans.EstudiantesBean;
import com.moimah.fx.beans.ResidenciaBean;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;


public class EstanciaDialogModel {
	

	private DoubleProperty codigoEstancia = new SimpleDoubleProperty();
	private ListProperty<EstudiantesBean> listEstudiantesBean = new SimpleListProperty<EstudiantesBean>();	
	private ObjectProperty<EstudiantesBean> estudianteSeleccionado = new SimpleObjectProperty<EstudiantesBean>();	
	private ListProperty<ResidenciaBean> listResidenciaBean = new SimpleListProperty<ResidenciaBean>();
	private ObjectProperty<ResidenciaBean> residenciaSeleccionada = new SimpleObjectProperty<ResidenciaBean>(); 
	private ObjectProperty<LocalDate> fechaInicio = new SimpleObjectProperty<LocalDate>();
	private ObjectProperty<LocalDate> fechaFin = new SimpleObjectProperty<LocalDate>();
	private DoubleProperty precioPagado = new SimpleDoubleProperty();
	
	
	public final DoubleProperty codigoEstanciaProperty() {
		return this.codigoEstancia;
	}
	
	public final double getCodigoEstancia() {
		return this.codigoEstanciaProperty().get();
	}
	
	public final void setCodigoEstancia(final double codigoEstancia) {
		this.codigoEstanciaProperty().set(codigoEstancia);
	}
	
	public final ListProperty<EstudiantesBean> listEstudiantesBeanProperty() {
		return this.listEstudiantesBean;
	}
	
	public final ObservableList<EstudiantesBean> getListEstudiantesBean() {
		return this.listEstudiantesBeanProperty().get();
	}
	
	public final void setListEstudiantesBean(final ObservableList<EstudiantesBean> listEstudiantesBean) {
		this.listEstudiantesBeanProperty().set(listEstudiantesBean);
	}
	
	public final ListProperty<ResidenciaBean> listResidenciaBeanProperty() {
		return this.listResidenciaBean;
	}
	
	public final ObservableList<ResidenciaBean> getListResidenciaBean() {
		return this.listResidenciaBeanProperty().get();
	}
	
	public final void setListResidenciaBean(final ObservableList<ResidenciaBean> listResidenciaBean) {
		this.listResidenciaBeanProperty().set(listResidenciaBean);
	}
	
	public final ObjectProperty<LocalDate> fechaInicioProperty() {
		return this.fechaInicio;
	}
	
	public final LocalDate getFechaInicio() {
		return this.fechaInicioProperty().get();
	}
	
	public final void setFechaInicio(final LocalDate fechaInicio) {
		this.fechaInicioProperty().set(fechaInicio);
	}
	
	public final ObjectProperty<LocalDate> fechaFinProperty() {
		return this.fechaFin;
	}
	
	public final LocalDate getFechaFin() {
		return this.fechaFinProperty().get();
	}
	
	public final void setFechaFin(final LocalDate fechaFin) {
		this.fechaFinProperty().set(fechaFin);
	}
	
	public final DoubleProperty precioPagadoProperty() {
		return this.precioPagado;
	}
	
	public final double getPrecioPagado() {
		return this.precioPagadoProperty().get();
	}
	
	public final void setPrecioPagado(final double precioPagado) {
		this.precioPagadoProperty().set(precioPagado);
	}

	public final ObjectProperty<EstudiantesBean> estudianteSeleccionadoProperty() {
		return this.estudianteSeleccionado;
	}
	

	public final EstudiantesBean getEstudianteSeleccionado() {
		return this.estudianteSeleccionadoProperty().get();
	}
	

	public final void setEstudianteSeleccionado(final EstudiantesBean estudianteSeleccionado) {
		this.estudianteSeleccionadoProperty().set(estudianteSeleccionado);
	}
	

	public final ObjectProperty<ResidenciaBean> residenciaSeleccionadaProperty() {
		return this.residenciaSeleccionada;
	}
	

	public final ResidenciaBean getResidenciaSeleccionada() {
		return this.residenciaSeleccionadaProperty().get();
	}
	

	public final void setResidenciaSeleccionada(final ResidenciaBean residenciaSeleccionada) {
		this.residenciaSeleccionadaProperty().set(residenciaSeleccionada);
	}
	
	
	

}
