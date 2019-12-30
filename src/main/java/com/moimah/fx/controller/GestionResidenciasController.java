package com.moimah.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.moimah.entities.Residencias;
import com.moimah.entities.Residenciasobservaciones;
import com.moimah.entities.Universidades;
import com.moimah.fx.beans.ResidenciaBean;
import com.moimah.fx.beans.ResidenciasObservacionesBean;
import com.moimah.fx.beans.UniversidadesBean;
import com.moimah.fx.dialogs.ResidenciaDialogController;
import com.moimah.fx.dialogs.ResidenciaDialogModel;
import com.moimah.hibernate.HibernateController;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import javafx.scene.layout.AnchorPane;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class GestionResidenciasController implements Initializable {
			
	//Model
	ResidenciasObservacionesBean residenciasObservacionesBean; 
	
	@FXML
	private AnchorPane root;
	@FXML
	private TableView<ResidenciaBean> table;
	@FXML
	private TableColumn<ResidenciaBean, Number> columnCodigo;
	@FXML
	private TableColumn<ResidenciaBean, String> columnNombre;
	@FXML
	private TableColumn<ResidenciaBean, Number> columnPrecio;
	@FXML
	private TableColumn<ResidenciaBean, Boolean> columnComedor;
	@FXML
	private TableColumn<ResidenciaBean, Universidades> columnUniversidad;
	@FXML
	private TextArea txtObservacion;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnRemove;
	@FXML
    private Button btnEdit;
	
	//Controllers secundarios
	private HibernateController hibernateController;
	private MainController mainController;
	
	private ArrayList<Universidades> listaUniversidades; 
	
	public GestionResidenciasController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GestionResidenciasView.fxml"));
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
		
		columnCodigo.setCellValueFactory(v -> v.getValue().codResidenciaProperty());
		columnNombre.setCellValueFactory(v -> v.getValue().nomResidenciaProperty());
		columnPrecio.setCellValueFactory(v -> v.getValue().precioMensualProperty());
		columnComedor.setCellValueFactory(v -> v.getValue().comedorProperty());
		columnUniversidad.setCellValueFactory(v -> v.getValue().universidadesProperty());
		
		
		table.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onResidenciaSelected(nv)); // Listener cuando se pincha estancia

	}
	

	
	/**
	 * Listener que detecta la residencia seleccionada y bindea con la observación
	 * @param nv
	 */
	private void onResidenciaSelected(ResidenciaBean nv) {
		
		try {
			
			try {
				txtObservacion.setText("");
				txtObservacion.textProperty().unbindBidirectional(residenciasObservacionesBean.observacionesProperty());			
			} catch (Exception e) {
				
			}
			
			Residenciasobservaciones observacion = nv.getResidenciasObservaciones();
			
		
			residenciasObservacionesBean = new ResidenciasObservacionesBean(observacion);
					
			
			txtObservacion.textProperty().bindBidirectional(residenciasObservacionesBean.observacionesProperty());
			
		} catch (Exception e) {
		
		}
		
		
		
	}
	
	/**
	 * Actualiza la ventana con los cambios producidos
	 */
	public void updateContent() {
		
		updateResidencias();
		updateUniversidades();
		
	}

	/**
	 * Actualiza la tabla de universidades con una consulta a la BBDD
	 */

	public void updateResidencias() {

		ArrayList<Residencias> list = (ArrayList<Residencias>) hibernateController.selectResidencias();
		ArrayList<ResidenciaBean> listBean = new ArrayList<ResidenciaBean>();

		for (Residencias r : list) {
			listBean.add(new ResidenciaBean(r));
		}

		ObservableList<ResidenciaBean> ObsUniversidades = FXCollections.observableArrayList(listBean);
		table.setItems(ObsUniversidades);		

	}
	
	/**
	 * Actualiza lista de universidades con una consulta a la BBDD
	 */
	public void updateUniversidades() {
		
		listaUniversidades = (ArrayList<Universidades>)hibernateController.selectUniversidades();
	}
	
	/**
	 * Edita una residencia en la BBDD diferenciando si contiene o no observacion
	 * Actualiza la tabla con los nuevos cambios
	 * @param residencia
	 */
	private void editarResidencia(Residencias residencia, Residenciasobservaciones obs) {
		
		
		if(obs==null) {				
			hibernateController.updateResidencia(residencia);			
			
		}else {			
			hibernateController.updateResidenciaObservacion(residencia, obs);
		}
		
		updateContent();	
		
	}
	
	
	/**
	 * Registra una residencia en la BBDD diferenciando si contiene o no observacion
	 * Actualiza la tabla con los nuevos cambios
	 * @param residencia
	 */
	private void registrarResidencia(Residencias residencia) {
								
		Residenciasobservaciones observacion = residencia.getResidenciasobservaciones();	
				
		if(observacion==null) {			
			hibernateController.insertResidencia(residencia);
			
		}else {			
			hibernateController.insertResidenciaObservacion(residencia, observacion);		
		}
		
		updateContent();	
		
	}
	
	
	
	/**
	 * Ejecuta un dialogo personalizado para registrar residencias
	 * llama al método registrar residencias
	 * @param listUniversidades
	 */
	
	private void editResidenciaDialog(ArrayList<Universidades> listUniversidades, Residencias residencia) {		
		
		Residencias residenciaSeleccionada =  table.getSelectionModel().getSelectedItem().getResidencia();
		Residenciasobservaciones obs = null;

        obs = residenciaSeleccionada.getResidenciasobservaciones();		

		ResidenciaDialogController dialog = new ResidenciaDialogController(); 
		dialog.cargarUniversidades(listUniversidades);
		dialog.cargarResidencia(residencia);
				
		Optional<ResidenciaDialogModel> result = dialog.showAndWait(); 
	
		if (result.isPresent()) { // Si se han obtenido resultados rellenar objeto residencia
			residenciaSeleccionada.setNomResidencia(result.get().getNombreResidencia());
			residenciaSeleccionada.setPrecioMensual((short)result.get().getPrecioMensual());
			residenciaSeleccionada.setComedor(result.get().isComedor());
			residenciaSeleccionada.setUniversidades(result.get().getUniversidadSeleccionada());
			
			//Si ya contiene observacion editarla con el contenido del dialogo
			if(obs!=null) {
				obs.setObservaciones(result.get().getObservacion());				
			//Si no contiene observacion y ha escrito observacion crear una nueva
			}else if(obs==null && result.get().getObservacion().length()>0) {				
				obs = new Residenciasobservaciones(); 
				obs.setObservaciones(result.get().getObservacion());
			}
			
						
				
			editarResidencia(residencia, obs);
			
		}

				
		
	}
	
	/**
	 * Ejecuta un dialogo personalizado para registrar residencias
	 * llama al método registrar residencias
	 * @param listUniversidades
	 */
	
	private void nuevaResidenciaDialog(ArrayList<Universidades> listUniversidades) {		
		
		
		ResidenciaDialogController dialog = new ResidenciaDialogController(); 
		dialog.cargarUniversidades(listUniversidades);
		
		Residencias residencia = new Residencias();		
		
		Optional<ResidenciaDialogModel> result = dialog.showAndWait(); 
	
		if (result.isPresent()) { // Si se han obtenido resultados rellenar objeto residencia
			residencia.setNomResidencia(result.get().getNombreResidencia());
			residencia.setPrecioMensual((short)result.get().getPrecioMensual());
			residencia.setComedor(result.get().isComedor());
			residencia.setUniversidades(result.get().getUniversidadSeleccionada());
			
			if(result.get().getObservacion().length()>0) {
				residencia.setResidenciasobservaciones(new Residenciasobservaciones(result.get().getObservacion()));
			}
			
				
			registrarResidencia(residencia);
			
		}

				
		
	}
		
	 	@FXML
	    void onAddResidencia(ActionEvent event) {
	 		nuevaResidenciaDialog(listaUniversidades);

	    }

	    @FXML
	    void onEditResidencia(ActionEvent event) {
	    	
	    	try {

		    	Residencias residencia = table.getSelectionModel().getSelectedItem().getResidencia();
		    	
		    	if(residencia!=null) {
		    		editResidenciaDialog(listaUniversidades, residencia);
		    	}
		    	
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	

	    }

	    @FXML
	    void onRemoveResidencia(ActionEvent event) {
	    	
	    	try {
	    		
	    		Residencias residencia = table.getSelectionModel().getSelectedItem().getResidencia();
	    		
	    		Alert alert = new Alert(AlertType.CONFIRMATION);
	    		alert.setTitle("Eliminar residencia");
	    		alert.setHeaderText("Va a eliminar la residencia: " + residencia);
	    		alert.setContentText("¿Está seguro?");

	    		Optional<ButtonType> result = alert.showAndWait();
	    		if (result.get() == ButtonType.OK){	    		  
	    			hibernateController.deleteResidencia(residencia);	    			
	    			
	    		} else {
	    			alert.close();	    		    
	    		}
	    		
	    		updateResidencias();
	    		updateUniversidades();
	    		
	    		
			} catch (Exception e) {
				// TODO: handle exception
			}
	    	

	    }
	
	
	
	

	public AnchorPane getRoot() {
		return root;
	}

	
	
	
	
	

}
