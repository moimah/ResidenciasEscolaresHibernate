package com.moimah.fx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import com.moimah.entities.Universidades;
import com.moimah.fx.beans.EstanciasBean;
import com.moimah.fx.beans.EstudiantesBean;
import com.moimah.fx.beans.ResidenciaBean;
import com.moimah.fx.beans.UniversidadesBean;
import com.moimah.hibernate.HibernateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class BBDDController implements Initializable {

	@FXML
	private AnchorPane root;
	@FXML
	private ListView listUniversidades;
	@FXML
	private ListView<ResidenciaBean> listResidencias;
	@FXML
	private TableView<EstanciasBean> tableEstancias;
	@FXML
	private TableColumn<EstanciasBean, Date> columFechaInicio;
	@FXML
	private TableColumn<EstanciasBean, Date> columnFechaFin;
	@FXML
	private TableColumn<EstanciasBean, Integer> columnPrecioPagado;
	@FXML
	private TableView<EstudiantesBean> tableEstudiante;
	@FXML
	private TableColumn<EstudiantesBean, String> columnNombreEstudiante;
	@FXML
	private TableColumn<EstudiantesBean, String> columnDNI;
	@FXML
	private TableColumn<EstudiantesBean, String> columnTelefono;
	@FXML
	private VBox vbox_estancias;
	@FXML
	private VBox vbox_residencias;
	@FXML
	private VBox vbox_estudiante;

	//Controllers secundarios
	private HibernateController hibernateController = new HibernateController();
	private MainController mainController; 


	// MODELO
	private UniversidadesBean universidadesBean;
	private ResidenciaBean residenciaBean;
	private EstanciasBean estanciasBean; 
	private EstudiantesBean estudiantesBean;

	public BBDDController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BBDDView.fxml"));
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


		// Tabla estancias	(CellValuueFactorys)
		columFechaInicio.setCellValueFactory(v -> v.getValue().fechaInicioProperty());
		columnFechaFin.setCellValueFactory(v -> v.getValue().fechaFinProperty());
		columnPrecioPagado.setCellValueFactory(v -> v.getValue().precioPagadoProperty());

		// Tabla estudiante (CellValuueFactorys)
		columnNombreEstudiante.setCellValueFactory(v -> v.getValue().nomEstudianteProperty());
		columnDNI.setCellValueFactory(v -> v.getValue().dniProperty());
		columnTelefono.setCellValueFactory(v -> v.getValue().telefonoEstudianteProperty());

		// Listeners
		listUniversidades.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onUniversidadSelected(nv)); // Listener cuando se pincha universidad				
		listResidencias.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onResidenciaSelected(nv)); // Listener cuando se pincha residencia																															
		tableEstancias.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> onEstanciaSelected(nv)); // Listener cuando se pincha estancia
																													
	

	}

	/**
	 * Listener que recibe Bean de universidades purga las tablas/vistas
	 * dependientes y carga lista de residencias
	 * 
	 * @param nv
	 */
	private void onEstanciaSelected(Object nv) {
		
		try {

			// Ocultar vistas dependientes
			vbox_estudiante.setVisible(false);

			// Aqui unbind
			try {
				tableEstudiante.itemsProperty().unbindBidirectional(estanciasBean.listEstudianteProperty());
			} catch (Exception e) {
			}

			// Obtener nuevo Bean
			estanciasBean = (EstanciasBean) nv;

			// Bindeo
			tableEstudiante.itemsProperty().bindBidirectional(estanciasBean.listEstudianteProperty());

			// Comprobación de contenido para mostrar
			if (estanciasBean.getListEstudiante().size() > 0) {
				vbox_estudiante.setVisible(true);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	

	/**
	 * Listener que recibe Bean de residencias purga las tablas/vistas dependientes
	 * y carga lista de estancias
	 * @param nv
	 */
	private void onResidenciaSelected(Object nv) {

		try {
			// Ocultar vistas dependientes
			vbox_estancias.setVisible(false);
			vbox_estudiante.setVisible(false);

			// Aqui unbind
			try {
				tableEstancias.itemsProperty().unbindBidirectional(residenciaBean.estanciasesProperty());
			} catch (Exception e) {
			}

			// Obtener nuevo Bean
			residenciaBean = (ResidenciaBean) nv;

			// Bindeo
			tableEstancias.itemsProperty().bindBidirectional(residenciaBean.estanciasesProperty());

			// Comprobación de contenido para mostrar
			if (residenciaBean.getEstanciases().size() > 0) {
				vbox_estancias.setVisible(true);
			}
			
		} catch (Exception e) {
		}

	}

	/**
	 * Listener que recibe Bean de universidades purga las tablas/vistas
	 * dependientes y carga lista de residencias
	 * 
	 * @param nv
	 */

	private void onUniversidadSelected(Object nv) {
		
		try {

			// Ocultar vistas dependientes
			vbox_residencias.setVisible(false);
			vbox_estancias.setVisible(false);
			vbox_estudiante.setVisible(false);

			// Aqui unbind
			try {
				listResidencias.itemsProperty().unbindBidirectional(universidadesBean.residenciasesProperty());
			} catch (Exception e) {

			}

			// Obtener nuevo Bean
			universidadesBean = (UniversidadesBean) nv;

			// Bindeo
			listResidencias.itemsProperty().bindBidirectional(universidadesBean.residenciasesProperty());

			// Comprobación de contenido para mostrar
			if (listResidencias.getItems().size() > 0) {
				vbox_residencias.setVisible(true);
			}

		} catch (Exception e) {

		}
		
	}

	/**
	 * Actualiza la lista de universidades con una consulta a la BBDD
	 */

	public void updateUniversidades() {

		ArrayList<Universidades> list = (ArrayList<Universidades>) hibernateController.selectUniversidades();
		ArrayList<UniversidadesBean> listBean = new ArrayList<UniversidadesBean>();

		for (Universidades u : list) {
			listBean.add(new UniversidadesBean(u));
		}

		ObservableList<UniversidadesBean> ObsUniversidades = FXCollections.observableArrayList(listBean);
		listUniversidades.setItems(ObsUniversidades);

	}

	public AnchorPane getRoot() {
		return root;
	}

}
