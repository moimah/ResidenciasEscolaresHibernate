package com.moimah.fx.app_main;

import com.moimah.fx.controller.BBDDController;
import com.moimah.fx.controller.GestionEstanciasController;
import com.moimah.fx.controller.GestionResidenciasController;
import com.moimah.fx.controller.MainController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	

private MainController controller; 
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller = new  MainController();
		
		Scene scene = new Scene(controller.getRoot(), 800, 600);				
		
		primaryStage.setTitle("ResidenciasEscolares Hibernate - Moisés Abreu");
		primaryStage.setScene(scene);
		primaryStage.setMinHeight(800);
		primaryStage.setMinWidth(600);
		primaryStage.setMaximized(true);
		primaryStage.show();
		

	}

	public static void main(String[] args) {
		launch(args);

	}
	
	

}
