package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * es la clase principal que contiene el menu del juego
 * @author LorenaRamirez
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		
		// Crear la escena y configurarla en el escenario principal
		Scene scene = new Scene(new Home(primaryStage), 500, 500);

		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
		// Mostrar la ventana
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}