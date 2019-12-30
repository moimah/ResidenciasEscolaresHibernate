package com.moimah.fx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.moimah.entities.Estancias;
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
import javafx.scene.control.TableColumn;

public class GestionEstudiantesController implements Initializable {
	@FXML
	private AnchorPane root;
	@FXML
	private TableView<EstudiantesBean> table;
	@FXML
	private TableColumn<EstudiantesBean, Number> columnCodigo;
	@FXML
	private TableColumn<EstudiantesBean, String> columnDNI;
	@FXML
	private TableColumn<EstudiantesBean, String> columnNombre;
	@FXML
	private TableColumn<EstudiantesBean, String> columnTelefono;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnRemove;
	@FXML
	private Button btnEdit;
	
	private MainController mainController;	
	private HibernateController hibernateController;
	
		
	public GestionEstudiantesController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionEstudiantesView.fxml"));
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
		
		columnCodigo.setCellValueFactory(v -> v.getValue().codEstudianteProperty());
		columnDNI.setCellValueFactory(v -> v.getValue().dniProperty());
		columnNombre.setCellValueFactory(v -> v.getValue().nomEstudianteProperty());
		columnTelefono.setCellValueFactory(v -> v.getValue().telefonoEstudianteProperty());
		
	}
	
	
	
	/**
	 * Metodo que registra estudiantes con inserción a la BBDD
	 * @param estudiantes
	 */	
	private void registratEstudiante(Estudiantes estudiantes) {
		
		hibernateController.insertEstudiante(estudiantes);
		updateContent();
		
	}
	
	/**
	 * Metodo que actualiza estudiantes con inserción a la BBDD
	 * @param estudiantes
	 */		
	private void actualizarEstudiante(Estudiantes estudiantes) {
		
		hibernateController.updateEstudiante(estudiantes);
		updateContent();
	}
	
	
	/**
	 * Actualiza la tabla de estudiantes con consulta a la BBDD
	 */	
	public void updateContent() {
		
		ArrayList<Estudiantes> list = (ArrayList<Estudiantes>) hibernateController.selectEstudiantes();
		ArrayList<EstudiantesBean> listBean  = new ArrayList<EstudiantesBean>();
		
		for(Estudiantes e : list) {
			listBean.add(new EstudiantesBean(e));
		}
		
		ObservableList<EstudiantesBean> obs = FXCollections.observableArrayList(listBean);
		table.setItems(obs);
		
	}
	
	/**
	 * Dialogo personalizado para editar un estudiante recibido como parámetro
	 * @param estudiante
	 */		
	public void editEstudianteDialog(Estudiantes estudiante) {
		
		// Crear un dialogo personalizado.
		Dialog<ArrayList<Object>> dialog = new Dialog<>();
		dialog.setTitle("Estudiante");
		dialog.setHeaderText("Editar estudiante");

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
	
		TextDecimal codigo = new TextDecimal();
		codigo.setDecimal(estudiante.getCodEstudiante());
		codigo.setDisable(true);
		TextField DNI = new TextField();
		DNI.setText(estudiante.getDni());
		TextField nombre = new TextField();
		nombre.setText(estudiante.getNomEstudiante());
		TextField telefono = new TextField();		
		telefono.setText(estudiante.getTelefonoEstudiante());

		grid.add(new Label("Código estudiante:"), 0, 0);
		grid.add(codigo, 1, 0);
		grid.add(new Label("DNI:"), 0, 1);
		grid.add(DNI, 1, 1);
		grid.add(new Label("Nombre:"), 0, 2);
		grid.add(nombre, 1, 2);
		grid.add(new Label("Teléfono"), 0, 3);
		grid.add(telefono, 1, 3);
		
		//Obtener el nodo del boton
		Node registrarButton = dialog.getDialogPane().lookupButton(REGISTRAR_BUTTON_TYPE);	
			
		//Deshabilitar buton con condiciones		
		registrarButton.disableProperty().bind(
				codigo.decimalProperty().isEqualTo(0)
				.or(DNI.textProperty().isEmpty())			
				.or(nombre.textProperty().isEmpty())	
				.or(telefono.textProperty().isEmpty())	
				);
		

		dialog.getDialogPane().setContent(grid);

		// Dar focus a un elementos
		Platform.runLater(() -> codigo.requestFocus());

		// Convertir el resultado en un ArrayList de objetos.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == REGISTRAR_BUTTON_TYPE) {
		    	
		    	ArrayList<Object> list = new ArrayList<Object>();
		    	list.add(codigo.getDecimal());
		    	list.add(DNI.getText());
		    	list.add(nombre.getText());
		    	list.add(telefono.getText());
		    	
		       return list;
		    }
		    return null;
		});
		
		//Cargar el resultado
		Optional<ArrayList<Object>> result = dialog.showAndWait();
		
		//Si se ha obtenido resultado extraer contenido de lista y realizar acción
		result.ifPresent(resultado -> {
		   
			ArrayList<Object> listResult = result.get(); 
	
			double cod =  (double) listResult.get(0);
			String dni = (String) listResult.get(1);
			String name = (String) listResult.get(2);
			String phone = (String) listResult.get(3);
			
			//Acciones a realizar
			
			
			estudiante.setCodEstudiante((int) cod);
			estudiante.setDni(dni);
			estudiante.setNomEstudiante(name);
			estudiante.setTelefonoEstudiante(phone);
			
			
			actualizarEstudiante(estudiante);
			
		});
	}
	
	
	
	/**
	 * Dialogo personalizado que registra un nuevo estudiante
	 */
	public void nuevoEstudianteDialog() {
		
		// Crear un dialogo personalizado.
		Dialog<ArrayList<Object>> dialog = new Dialog<>();
		dialog.setTitle("Estudiante");
		dialog.setHeaderText("Añade un nuevo estudiante");

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
	
		TextDecimal codigo= new TextDecimal();
		TextField DNI = new TextField();
		TextField nombre = new TextField();
		TextField telefono = new TextField();		

		grid.add(new Label("Código estudiante:"), 0, 0);
		grid.add(codigo, 1, 0);
		grid.add(new Label("DNI:"), 0, 1);
		grid.add(DNI, 1, 1);
		grid.add(new Label("Nombre:"), 0, 2);
		grid.add(nombre, 1, 2);
		grid.add(new Label("Teléfono"), 0, 3);
		grid.add(telefono, 1, 3);
		
		//Obtener el nodo del boton
		Node registrarButton = dialog.getDialogPane().lookupButton(REGISTRAR_BUTTON_TYPE);	
			
		//Deshabilitar buton con condiciones		
		registrarButton.disableProperty().bind(
				codigo.decimalProperty().isEqualTo(0)
				.or(DNI.textProperty().isEmpty())			
				.or(nombre.textProperty().isEmpty())	
				.or(telefono.textProperty().isEmpty())	
				);
		

		dialog.getDialogPane().setContent(grid);

		// Dar focus a un elementos
		Platform.runLater(() -> codigo.requestFocus());

		// Convertir el resultado en un ArrayList de objetos.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == REGISTRAR_BUTTON_TYPE) {
		    	
		    	ArrayList<Object> list = new ArrayList<Object>();
		    	list.add(codigo.getDecimal());
		    	list.add(DNI.getText());
		    	list.add(nombre.getText());
		    	list.add(telefono.getText());
		    	
		       return list;
		    }
		    return null;
		});
		
		//Cargar el resultado
		Optional<ArrayList<Object>> result = dialog.showAndWait();
		
		//Si se ha obtenido resultado extraer contenido de lista y realizar acción
		result.ifPresent(resultado -> {
		   
			ArrayList<Object> listResult = result.get(); 
	
			double cod =  (double) listResult.get(0);
			String dni = (String) listResult.get(1);
			String name = (String) listResult.get(2);
			String phone = (String) listResult.get(3);
			
			//Acciones a realizar
			
			Estudiantes estudiante = new Estudiantes();
			estudiante.setCodEstudiante((int) cod);
			estudiante.setDni(dni);
			estudiante.setNomEstudiante(name);
			estudiante.setTelefonoEstudiante(phone);
			
			
			registratEstudiante(estudiante);
			
		});
	}
		

	// Event Listener on Button[#btnAdd].onAction
	@FXML
	public void onAdd(ActionEvent event) {
		nuevoEstudianteDialog();
	}
	// Event Listener on Button[#btnRemove].onAction
	@FXML
	public void onRemove(ActionEvent event) {
		try {

			Estudiantes estudiantes = table.getSelectionModel().getSelectedItem().getEstudiantes();

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Eliminar estudiante");
			alert.setHeaderText("Va a eliminar el estudiante: " + estudiantes);
			alert.setContentText("¿Está seguro?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				hibernateController.deleteEstudiante(estudiantes);

			} else {
				alert.close();
			}

			updateContent();

		} catch (Exception e) {

		}
		
		
	}
	// Event Listener on Button[#btnEdit].onAction
	@FXML
	public void onEdit(ActionEvent event) {
		try {
			Estudiantes estudiante = table.getSelectionModel().getSelectedItem().getEstudiantes();
			editEstudianteDialog(estudiante);
		} catch (Exception e) {
			
		}
	}
	
	public AnchorPane getRoot() {
		return root;
	}
	
}
