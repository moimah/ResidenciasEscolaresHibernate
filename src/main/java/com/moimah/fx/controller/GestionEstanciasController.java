package com.moimah.fx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.moimah.entities.Estancias;
import com.moimah.entities.Estudiantes;
import com.moimah.entities.Residencias;
import com.moimah.fx.beans.EstanciasBean;
import com.moimah.fx.beans.EstudiantesBean;
import com.moimah.fx.beans.ResidenciaBean;
import com.moimah.fx.dialogs.EstanciaDialogController;
import com.moimah.fx.dialogs.EstanciaDialogModel;
import com.moimah.hibernate.HibernateController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;


import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;

public class GestionEstanciasController implements Initializable {

	
	//Model
	EstanciasBean estanciaBean;
	
	@FXML
    private AnchorPane root;

    @FXML
    private TableView<EstanciasBean> table;

    @FXML
    private TableColumn<EstanciasBean, Number> columnCodigo;

    @FXML
    private TableColumn<EstanciasBean, EstudiantesBean> columnEstudiante;

    @FXML
    private TableColumn<EstanciasBean, Residencias> columnResidencia;

    @FXML
    private TableColumn<EstanciasBean, Date> columnFechaInicio;

    @FXML
    private TableColumn<EstanciasBean, Date> columnFechaFin;

    @FXML
    private TableColumn<EstanciasBean, Integer> columnPrecioPagado;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnEdit;
    
    //Controllers secundarios
    private HibernateController hibernateController;
    private MainController mainController; 
    
    private ArrayList<ResidenciaBean> listaResidencias;
    
    private ArrayList<EstudiantesBean> listaEstudiantes; 
	
	public GestionEstanciasController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionEstanciasView.fxml"));
		loader.setController(this);
		loader.load();
		
		
	}
	
	public void injectMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public void injectDatabaseController(HibernateController hibernateController) {
		this.hibernateController = hibernateController;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		columnCodigo.setCellValueFactory(v -> v.getValue().codEstanciaProperty());
		columnEstudiante.setCellValueFactory(v -> v.getValue().estudianteProperty());
		columnResidencia.setCellValueFactory(v -> v.getValue().residenciasProperty());		
		columnFechaInicio.setCellValueFactory(v -> v.getValue().fechaInicioProperty());
		columnFechaFin.setCellValueFactory(v -> v.getValue().fechaFinProperty());
		columnPrecioPagado.setCellValueFactory(v -> v.getValue().precioPagadoProperty());		
		

	}
	
	
	/**
	 * Registra una estancia usando el controlador de hibernate
	 * @param estancia
	 */
	private void registrarEstancia(Estancias estancia) {
		
		hibernateController.insertEstancia(estancia);

		updateContent();
		
	}
	
	/**
	 * Actualiza una estancia usando el controlador de hibernate
	 * @param estancia
	 */
	private void actualizarEstancia(Estancias estancia) {
		hibernateController.updateEstancia(estancia);
		updateContent();
	}

	/**
	 * Cuadro de diaógo personalizado que cargara las listas necesarias como parametros, y el objeto estancia a editar
	 * @param listEstudiante
	 * @param listResidencias
	 * @param estancia
	 */	
private void editEstanciaDialog(ArrayList<EstudiantesBean> listEstudiante, ArrayList<ResidenciaBean> listResidencias, EstanciasBean estancia) {
		
		EstanciaDialogController dialog = new EstanciaDialogController();
		
		dialog.cargarEstudiantes(listEstudiante);
		dialog.cargarResidencias(listResidencias);
		dialog.cargarEstancia(estancia);
		
		
		Estancias estanciaSeleccionada = table.getSelectionModel().getSelectedItem().getEstancias();	
		
		Optional<EstanciaDialogModel> result = dialog.showAndWait(); 
	
		if (result.isPresent()) { // Si se han obtenido resultados rellenar objeto estancia
			
			estancia.setCodEstancia((int) result.get().getCodigoEstancia());			
			estanciaSeleccionada.setEstudiantes(result.get().getEstudianteSeleccionado().getEstudiantes()); 
			
			
			LocalDate da = result.get().getFechaInicio();			
			Date dateA = java.sql.Date.valueOf(da);
			estanciaSeleccionada.setFechaInicio(dateA);
			
			LocalDate db = result.get().getFechaFin();
			Date dateB = java.sql.Date.valueOf(db);
			estanciaSeleccionada.setFechaFin(dateB);
			
			estanciaSeleccionada.setPrecioPagado((short) result.get().getPrecioPagado());
			estanciaSeleccionada.setResidencias(result.get().getResidenciaSeleccionada().getResidencia());
			
			actualizarEstancia(estanciaSeleccionada);
			
			
		}

	}
	

	/**
	 * Cuadro de dialogo personalizado que carga las listas necesarias para crear un objeto estancia
	 * @param listEstudiante
	 * @param listResidencias
	 */
	private void nuevaEstanciaDialog(ArrayList<EstudiantesBean> listEstudiante, ArrayList<ResidenciaBean> listResidencias) {
		
		EstanciaDialogController dialog = new EstanciaDialogController();
		
		dialog.cargarEstudiantes(listEstudiante);
		dialog.cargarResidencias(listResidencias);
		
		
		Estancias estancia = new Estancias();		
		
		Optional<EstanciaDialogModel> result = dialog.showAndWait(); 
	
		if (result.isPresent()) { // Si se han obtenido resultados rellenar objeto estancia
			
			estancia.setCodEstancia((int) result.get().getCodigoEstancia());			
			estancia.setEstudiantes(result.get().getEstudianteSeleccionado().getEstudiantes()); 
			
			
			LocalDate da = result.get().getFechaInicio();			
			Date dateA = java.sql.Date.valueOf(da);
			estancia.setFechaInicio(dateA);
			
			LocalDate db = result.get().getFechaFin();
			Date dateB = java.sql.Date.valueOf(db);
			estancia.setFechaFin(dateB);
			
			estancia.setPrecioPagado((short) result.get().getPrecioPagado());
			estancia.setResidencias(result.get().getResidenciaSeleccionada().getResidencia());
			
			registrarEstancia(estancia);
			
			
		}

	}
	
	/**
	 * Actualiza los elementos con los cambios producidos
	 */
	public void updateContent() {
		updateEstancias();
		updateResidencias();
		updateEstudiantes();
			
		
	}
	
	
	/**
	 * Actualiza la lista de estudiantes con consulta a la BBDD
	 */
	public void updateEstudiantes(){
		
		ArrayList<Estudiantes> list = (ArrayList<Estudiantes>) hibernateController.selectEstudiantes();
		listaEstudiantes = new ArrayList<EstudiantesBean>(); 
		for (Estudiantes e : list) {
			listaEstudiantes.add(new EstudiantesBean(e));
		}
	}
	
	/**
	 * Actualiza la lista de residencias con consulta a la BBDD
	 */
	public void updateResidencias() {
		
		ArrayList<Residencias> list = (ArrayList<Residencias>) hibernateController.selectResidencias();
		listaResidencias = new ArrayList<ResidenciaBean>();
		
		for(Residencias r : list) {
			listaResidencias.add(new ResidenciaBean(r));
		}
		
		
	}
	
	
	/**
	 * Actualiza la lista de estancias con consulta a la BBDD
	 */
	public void updateEstancias() {
		
		ArrayList<Estancias> lista  = (ArrayList<Estancias>) hibernateController.selectEstancias();
		ArrayList<EstanciasBean> listBean = new ArrayList<EstanciasBean>();
		
		for(Estancias e: lista) {
			listBean.add(new EstanciasBean(e));
		}
		
		ObservableList<EstanciasBean> obs = FXCollections.observableArrayList(listBean);		
		table.setItems(obs);
		
	}

	@FXML
	void onAdd(ActionEvent event) {
		nuevaEstanciaDialog(listaEstudiantes, listaResidencias);

	}

	@FXML
	void onEdit(ActionEvent event) {
		
		try {
			EstanciasBean estancia  = table.getSelectionModel().getSelectedItem();
			editEstanciaDialog(listaEstudiantes, listaResidencias, estancia);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@FXML
	void onRemove(ActionEvent event) {
		try {
    		
    		Estancias estancia = table.getSelectionModel().getSelectedItem().getEstancias();
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Eliminar estancia");
    		alert.setHeaderText("Va a eliminar la estancia: " + estancia);
    		alert.setContentText("¿Está seguro?");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){	    		  
    			hibernateController.deleteEstancia(estancia);    			
    			
    		} else {
    			alert.close();	    		    
    		}
    		
    		updateContent();
    		
    		
		} catch (Exception e) {
			
		}
    	

	}
	
	public AnchorPane getRoot() {
		return root;
	}

}
