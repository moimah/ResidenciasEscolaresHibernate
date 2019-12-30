package com.moimah.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.boot.model.relational.Database;

import com.moimah.hibernate.HibernateController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Tab;

public class MainController implements Initializable {
	@FXML
	private TabPane root;
	@FXML
	private Tab tabEstudiantes;
	@FXML
	private Tab tabUniversidades;
	@FXML
	private Tab tabResidencias;
	@FXML
	private Tab tabEstancias;
	@FXML
	private Tab tabBBDD;
	
	//Controlador de conexion
	private HibernateController conexion = new HibernateController(); 
	
	//Subcontrollers	
	private GestionEstudiantesController subControllerEstudiantes;
	private GestionUniversidadesController subControllerUniversidades;
	private GestionEstanciasController subControllerEstancias; 
	private GestionResidenciasController subcontrollerResidencias; 
	private BBDDController subcontrollerBBDD;
	
	
	
	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Listeners
		root.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onTabSelected(nv)); // Listener cuando se pincha universidad
	
		
		try {
			subControllerEstudiantes = new GestionEstudiantesController();
			subControllerUniversidades = new GestionUniversidadesController();
			subControllerEstancias = new GestionEstanciasController(); 
			subcontrollerResidencias = new GestionResidenciasController();
			subcontrollerBBDD = new BBDDController(); 			
		} catch (Exception e) {
			System.out.println("Error al cargar las visatas");
		}
		
		//Obtener las vistas
		AnchorPane viewEstudiantes = subControllerEstudiantes.getRoot();
		AnchorPane viewUniversidades = subControllerUniversidades.getRoot();
		AnchorPane viewEstancias = subControllerEstancias.getRoot();		
		AnchorPane viewResidencias = subcontrollerResidencias.getRoot();
		AnchorPane viewBBDD = subcontrollerBBDD.getRoot();
		//Cargar las vistas en pestañas
		tabEstudiantes.setContent(viewEstudiantes);
		tabUniversidades.setContent(viewUniversidades);
		tabEstancias.setContent(viewEstancias);
		tabResidencias.setContent(viewResidencias);
		tabBBDD.setContent(viewBBDD);
		
		//Inyecciones
		subControllerEstudiantes.injectMainController(this);
		subControllerUniversidades.injectMainController(this);
		subControllerEstancias.injectMainController(this);
		subcontrollerResidencias.injectMainController(this);
		subcontrollerBBDD.injectMainController(this);
			
		
		//Inyeccion de conexión
		conexion.start();
		subControllerEstudiantes.injectDatabaseController(conexion);
		subControllerUniversidades.injectDatabaseController(conexion);
		subControllerEstancias.injectDatabaseController(conexion);
		subcontrollerResidencias.injectDatabaseController(conexion);
		subcontrollerBBDD.injectDatabaseController(conexion);
		
		//Actualizar contenido
		subControllerEstudiantes.updateContent();
		subControllerEstancias.updateContent();
		subcontrollerResidencias.updateContent();
		subControllerUniversidades.updateContent();
	}
	
	/**
	 * Listener que detecta que pestaña se ha seleccionado	
	 * @param nv
	 */
	private void onTabSelected(Tab nv) {
		
		if(nv.equals(tabResidencias)) {
			subcontrollerResidencias.updateContent();
		}else if(nv.equals(tabEstancias)) {
			subControllerEstancias.updateContent();
		}else if(nv.equals(tabBBDD)){
			subcontrollerBBDD.updateUniversidades();
		}else if(nv.equals(subControllerEstudiantes)) {
			subControllerEstudiantes.updateContent();
		}else if(nv.equals(subControllerUniversidades)){
			subControllerUniversidades.updateContent();
		}
		
	}

	
	public GestionEstanciasController getSubControllerEstancias() {
		return subControllerEstancias;
	}

	public GestionResidenciasController getSubcontrollerResidencias() {
		return subcontrollerResidencias;
	}

	public BBDDController getSubcontrollerBBDD() {
		return subcontrollerBBDD;
	}

	public TabPane getRoot() {
		return root;
	}

}
