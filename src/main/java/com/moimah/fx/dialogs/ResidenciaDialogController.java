package com.moimah.fx.dialogs;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.moimah.entities.Residencias;
import com.moimah.entities.Universidades;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;

import javafx.scene.layout.AnchorPane;

import moimah.javafx.textdecimal.TextDecimal;

public class ResidenciaDialogController extends Dialog<ResidenciaDialogModel> implements Initializable{
	@FXML
	private AnchorPane root;
	@FXML
	private TextField txtNomResidencia;
	@FXML
	private RadioButton radioComedorSi;
	@FXML
	private RadioButton radioComedorNo;
	@FXML
	private ToggleGroup groupComedor;
	@FXML
	private TextDecimal txtPrecioMensual;
	@FXML
    private ComboBox<Universidades> comboUniversidad;
	@FXML
	private TextArea txtObservacion;
	
	
		private ResidenciaDialogModel model = new ResidenciaDialogModel(); 	
		
	    private ButtonType REGISTRAR_BUTTON_TYPE = new ButtonType("Registrar", ButtonData.OK_DONE);
	    
	    public ResidenciaDialogController() {
			super();
			
			setTitle("Residencia");
			//setHeaderText("Introduzca .................");
			//setGraphic(new ImageView(getClass().getResource("/images/telefono.png").toString()));
			getDialogPane().getButtonTypes().addAll(
					REGISTRAR_BUTTON_TYPE, // botón personalizado
					ButtonType.CANCEL
				);
			getDialogPane().setContent(loadContent("/fxml/ResidenciaDialog.fxml"));
			setResultConverter(dialogButton -> {
			    if (dialogButton.getButtonData() == ButtonData.OK_DONE) {
			    	
			    	ResidenciaDialogModel residenciaForDialog= new ResidenciaDialogModel();
			    	residenciaForDialog.setComedor(model.isComedor());
			    	residenciaForDialog.setNombreResidencia(model.getNombreResidencia());
			    	residenciaForDialog.setPrecioMensual(model.getPrecioMensual());	
			    	residenciaForDialog.setUniversidades(model.getUniversidades());			    	
			    	residenciaForDialog.setUniversidadSeleccionada(model.getUniversidadSeleccionada());
			    	residenciaForDialog.setObservacion(model.getObservacion());
			    	
			    	
						    	
			    	return residenciaForDialog;
			       
			    }
			    return null;
			});	

	    	
		}
	    
	    private Node loadContent(String fxml) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
				loader.setController(this);
				return loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

	    
	    

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Bindeos
		model.comedorProperty().bindBidirectional(radioComedorSi.selectedProperty());
		model.nombreResidenciaProperty().bindBidirectional(txtNomResidencia.textProperty());
		model.precioMensualProperty().bindBidirectional(txtPrecioMensual.decimalProperty());
		model.universidadesProperty().bindBidirectional(comboUniversidad.itemsProperty());			
		model.universidadSeleccionadaProperty().bindBidirectional(comboUniversidad.valueProperty());
		
		model.observacionProperty().bindBidirectional(txtObservacion.textProperty());
					
		Node registrarButton = getDialogPane().lookupButton(REGISTRAR_BUTTON_TYPE); //El boton se activa si los campos no están vacios
		
				
		registrarButton.disableProperty().bind(
				model.nombreResidenciaProperty().isEmpty()
				.or(model.precioMensualProperty().isEqualTo(0))			
				.or(comboUniversidad.valueProperty().isNull())				
				);

		
	}
	
	
	/**
	 * Metodo que se encarga de cargar el combo con las universidades disponibles
	 */
	public void cargarUniversidades(ArrayList<Universidades> listaUniversidades) {
		ObservableList<Universidades> obs = FXCollections.observableArrayList(listaUniversidades);
		model.setUniversidades(obs);		
	}
	
	
	/**
	 * Se usa para editar una residencia, recibe residencia como parametro
	 * y carga modelo y vista con los datos recibidos
	 * @param residencia
	 */
	public void cargarResidencia(Residencias residencia) {
		model.setNombreResidencia(residencia.getNomResidencia());		
		model.setComedor(residencia.getComedor());
		model.setPrecioMensual(residencia.getPrecioMensual());
		model.setUniversidadSeleccionada(residencia.getUniversidades());
		try { //Puede generarse excepcion cuando no tiene observacion asociada
			model.setObservacion(residencia.getResidenciasobservaciones().getObservaciones());
		} catch (Exception e) {
			
		}
	
		
	}
	

}
