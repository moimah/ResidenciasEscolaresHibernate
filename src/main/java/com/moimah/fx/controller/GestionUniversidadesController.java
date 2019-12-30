package com.moimah.fx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.moimah.entities.Estudiantes;
import com.moimah.entities.Universidades;
import com.moimah.fx.beans.EstudiantesBean;
import com.moimah.fx.beans.UniversidadesBean;
import com.moimah.hibernate.HibernateController;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import moimah.javafx.textdecimal.TextDecimal;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TableColumn;

public class GestionUniversidadesController implements Initializable {
	@FXML
	private AnchorPane root;
	@FXML
	private TableView<UniversidadesBean> table;
	@FXML
	private TableColumn<UniversidadesBean, String> columnCodigo;
	@FXML
	private TableColumn<UniversidadesBean, String> columnNombre;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnRemove;
	@FXML
	private Button btnEdit;
	
	private MainController mainController;	
	private HibernateController hibernateController;
	
	
	public GestionUniversidadesController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionUniversidadesView.fxml"));
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
		
		columnCodigo.setCellValueFactory(v -> v.getValue().codUniversidadProperty());
		columnNombre.setCellValueFactory(v -> v.getValue().nomUniversidadProperty());
		
	}
	
	/**
	 * Metodo que registra universidad con inserción a la BBDD
	 * @param universidad
	 */	
	private void registrarUniversidad(Universidades universidad) {		
		hibernateController.insertUniversidad(universidad);
		updateContent();
		
	}	
	
	/**
	 * Metodo que actualiza universidad con inserción a la BBDD
	 * @param universidad
	 */
	private void actualizarUniversidad(Universidades universidad) {
		
		hibernateController.updateUniversidad(universidad);
		updateContent();
	}

	/**
	 * Dialogo personalizado para editar un una universidad recibida como parámetro
	 * @param universidad
	 */	
	public void editUniversidadDialog(Universidades universidad) {
		
		// Crear un dialogo personalizado.
		Dialog<ArrayList<Object>> dialog = new Dialog<>();
		dialog.setTitle("Universidad");
		dialog.setHeaderText("Editar universidad");

		// Agregar icno
		//dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

		//Agregar ButtonTypes
		ButtonType REGISTRAR_BUTTON_TYPE = new ButtonType("Registrar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(REGISTRAR_BUTTON_TYPE, ButtonType.CANCEL);

		// Crear GridPane  y elementos
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
	
		TextField codigo = new TextField();
		codigo.setText(universidad.getCodUniversidad());
		codigo.setDisable(true);
		TextField nombre = new TextField();
		nombre.setText(universidad.getNomUniversidad());
				

		grid.add(new Label("Código universidad:"), 0, 0);
		grid.add(codigo, 1, 0);
		grid.add(new Label("Nombre:"), 0, 1);
		grid.add(nombre, 1, 1);
		
		
		//Obtener el nodo del boton
		Node registrarButton = dialog.getDialogPane().lookupButton(REGISTRAR_BUTTON_TYPE);	
			
		//Deshabilitar buton con condiciones		
		registrarButton.disableProperty().bind(
				codigo.textProperty().isEmpty()						
				.or(nombre.textProperty().isEmpty())					
				);
		

		dialog.getDialogPane().setContent(grid);

		// Dar focus a un elementos
		Platform.runLater(() -> codigo.requestFocus());

		// Convertir el resultado en un ArrayList de objetos.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == REGISTRAR_BUTTON_TYPE) {
		    	
		    	ArrayList<Object> list = new ArrayList<Object>();
		    	list.add(codigo.getText());
		        list.add(nombre.getText());
		    	
		    	
		       return list;
		    }
		    return null;
		});
		
		//Cargar el resultado
		Optional<ArrayList<Object>> result = dialog.showAndWait();
		
		//Si se ha obtenido resultado extraer contenido de lista y realizar acción
		result.ifPresent(resultado -> {
		   
			ArrayList<Object> listResult = result.get(); 
	
			String cod =  (String) listResult.get(0);		
			String name = (String) listResult.get(1);
			
			
			//Acciones a realizar			
			
			universidad.setCodUniversidad(cod);
			universidad.setNomUniversidad(name);
			
			
			
			actualizarUniversidad(universidad);
			
		});
	}
	
	/**
	 * Dialogo personalizado que registra un nuevo estudiante
	 */
	public void nuevaUniversidadDialog() {
		
		// Crear un dialogo personalizado.
		Dialog<ArrayList<Object>> dialog = new Dialog<>();
		dialog.setTitle("Universidad");
		dialog.setHeaderText("Añade una nueva universidad");

		// Agregar icno
		//dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

		//Agregar ButtonTypes
		ButtonType REGISTRAR_BUTTON_TYPE = new ButtonType("Registrar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(REGISTRAR_BUTTON_TYPE, ButtonType.CANCEL);

		// Crear GridPane  y elementos
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
	
		TextField codigo = new TextField();
		TextField nombre = new TextField();
				

		grid.add(new Label("Código universidad:"), 0, 0);
		grid.add(codigo, 1, 0);
		grid.add(new Label("Nombre:"), 0, 1);
		grid.add(nombre, 1, 1);
		
		
		//Obtener el nodo del boton
		Node registrarButton = dialog.getDialogPane().lookupButton(REGISTRAR_BUTTON_TYPE);	
			
		//Deshabilitar buton con condiciones		
		registrarButton.disableProperty().bind(
				codigo.textProperty().isEmpty()						
				.or(nombre.textProperty().isEmpty())					
				);
		

		dialog.getDialogPane().setContent(grid);

		// Dar focus a un elementos
		Platform.runLater(() -> codigo.requestFocus());

		// Convertir el resultado en un ArrayList de objetos.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == REGISTRAR_BUTTON_TYPE) {
		    	
		    	ArrayList<Object> list = new ArrayList<Object>();
		    	list.add(codigo.getText());
		        list.add(nombre.getText());
		    	
		    	
		       return list;
		    }
		    return null;
		});
		
		//Cargar el resultado
		Optional<ArrayList<Object>> result = dialog.showAndWait();
		
		//Si se ha obtenido resultado extraer contenido de lista y realizar acción
		result.ifPresent(resultado -> {
		   
			ArrayList<Object> listResult = result.get(); 
	
			String cod =  (String) listResult.get(0);		
			String name = (String) listResult.get(1);
			
			
			//Acciones a realizar
			
			Universidades universidad = new Universidades();
			universidad.setCodUniversidad(cod);
			universidad.setNomUniversidad(name);
						
			
			registrarUniversidad(universidad);
			
		});
	}
	

	/**
	 * Actualiza la tabla de universidades
	 */	
	public void updateContent() {
		
		ArrayList<Universidades> list = (ArrayList<Universidades>) hibernateController.selectUniversidades();
		ArrayList<UniversidadesBean> listBean  = new ArrayList<UniversidadesBean>();
		
		for(Universidades u : list) {
			listBean.add(new UniversidadesBean(u));
		}
		
		ObservableList<UniversidadesBean> obs = FXCollections.observableArrayList(listBean);
		table.setItems(obs);
		
	}

	

	// Event Listener on Button[#btnAdd].onAction
	@FXML
	public void onAdd(ActionEvent event) {
		nuevaUniversidadDialog();
	}
	// Event Listener on Button[#btnRemove].onAction
	@FXML
	public void onRemove(ActionEvent event) {
		
		try {				
			
			Universidades universidad = table.getSelectionModel().getSelectedItem().getUniversidad();
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Eliminar universidad");
			alert.setHeaderText("Va a eliminar la universidad: " + universidad);
			alert.setContentText("¿Está seguro?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {				
				hibernateController.deleteUniversidad(universidad);
			} else {
				alert.close();
			}

			updateContent();
						
			updateContent();
			
		} catch (Exception e) {
			
		}
	}
	// Event Listener on Button[#btnEdit].onAction
	@FXML
	public void onEdit(ActionEvent event) {
		
		try {
			Universidades universidad = table.getSelectionModel().getSelectedItem().getUniversidad();
			editUniversidadDialog(universidad);
			
		} catch (Exception e) {
			
		}
	}
	
	public AnchorPane getRoot() {
		return root;
	}
	
}
