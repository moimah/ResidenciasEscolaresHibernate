package com.moimah.fx.dialogs;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.moimah.fx.beans.EstanciasBean;
import com.moimah.fx.beans.EstudiantesBean;
import com.moimah.fx.beans.ResidenciaBean;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import javafx.scene.layout.AnchorPane;

import moimah.javafx.textdecimal.TextDecimal;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;

public class EstanciaDialogController extends Dialog<EstanciaDialogModel> implements Initializable {
	
	
	@FXML
    private AnchorPane root;

    @FXML
    private ComboBox<EstudiantesBean> comboEstudiante;

    @FXML
    private TextDecimal txtCodEstancia;

    @FXML
    private ComboBox<ResidenciaBean> comboResidencia;

    @FXML
    private DatePicker dateFechaInicio;

    @FXML
    private DatePicker dateFechaFin;

    @FXML
    private TextDecimal txtPrecio;
	
	//Model
	
	private EstanciaDialogModel model = new EstanciaDialogModel(); 
	
	private ButtonType REGISTRAR_BUTTON_TYPE = new ButtonType("Registrar", ButtonData.OK_DONE);
			
	
	public EstanciaDialogController() {
		
		super();
		
		setTitle("Estancia");
		//setHeaderText("Introduzca .................");
		//setGraphic(new ImageView(getClass().getResource("/images/telefono.png").toString()));
		getDialogPane().getButtonTypes().addAll(
				REGISTRAR_BUTTON_TYPE, // botón personalizado
				ButtonType.CANCEL
			);
		getDialogPane().setContent(loadContent("/fxml/EstanciaDialog.fxml"));
		setResultConverter(dialogButton -> {
		    if (dialogButton.getButtonData() == ButtonData.OK_DONE) {
		    	
		    	EstanciaDialogModel estanciaForDialog = new EstanciaDialogModel();
		    	
		    	estanciaForDialog.setFechaFin(model.getFechaFin());
		    	estanciaForDialog.setFechaInicio(model.getFechaInicio());
		    	estanciaForDialog.setEstudianteSeleccionado(model.getEstudianteSeleccionado());
		    	estanciaForDialog.setResidenciaSeleccionada(model.getResidenciaSeleccionada());		    	
		    	estanciaForDialog.setPrecioPagado(model.getPrecioPagado());
		    	estanciaForDialog.setCodigoEstancia(model.getCodigoEstancia());	    	
   			    	
					    	
		    	return estanciaForDialog;
		       
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
		
		model.codigoEstanciaProperty().bindBidirectional(txtCodEstancia.decimalProperty());
		model.fechaFinProperty().bindBidirectional(dateFechaFin.valueProperty());
		model.fechaInicioProperty().bindBidirectional(dateFechaInicio.valueProperty());
		model.listEstudiantesBeanProperty().bindBidirectional(comboEstudiante.itemsProperty());
		
		model.estudianteSeleccionadoProperty().bindBidirectional(comboEstudiante.valueProperty());
		
		model.listResidenciaBeanProperty().bindBidirectional(comboResidencia.itemsProperty());
		model.residenciaSeleccionadaProperty().bindBidirectional(comboResidencia.valueProperty());
		model.precioPagadoProperty().bindBidirectional(txtPrecio.decimalProperty());
		
		
		Node registrarButton = getDialogPane().lookupButton(REGISTRAR_BUTTON_TYPE); //El boton se activa si los campos no están vacios
		
		registrarButton.disableProperty().bind(
				model.fechaFinProperty().isNull()
				.or(model.fechaInicioProperty().isNull())			
				.or(model.estudianteSeleccionadoProperty().isNull())
				.or(model.residenciaSeleccionadaProperty().isNull())
				);
		
	}
	
	public void cargarEstudiantes(ArrayList<EstudiantesBean> lista) {		
		ObservableList<EstudiantesBean> obs = FXCollections.observableArrayList(lista);
		model.setListEstudiantesBean(obs);
	}
	
	public void cargarResidencias(ArrayList<ResidenciaBean> lista) {
		ObservableList<ResidenciaBean> obs = FXCollections.observableArrayList(lista);
		model.setListResidenciaBean(obs);
	}
	
	public void cargarEstancia(EstanciasBean e) {
		
		model.setCodigoEstancia(e.getCodEstancia());
		txtCodEstancia.setDisable(true);
		
		Date da = e.getFechaInicio();
		LocalDate dateA= new java.sql.Date(da.getTime()).toLocalDate();
		model.setFechaInicio(dateA);
		
		Date db = e.getFechaFin();
		LocalDate dateB= new java.sql.Date(db.getTime()).toLocalDate();
		model.setFechaFin(dateB);
		
		
		model.setPrecioPagado(e.getPrecioPagado());
		
		model.setEstudianteSeleccionado(e.getEstudiante());
		model.setResidenciaSeleccionada(new ResidenciaBean(e.getResidencias()));				
		
		
	}
	
	

}
