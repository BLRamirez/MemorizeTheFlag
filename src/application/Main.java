package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crear el label
    	
    	
        Image image = new Image(getClass().getResourceAsStream("/application/global-223.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);

    	
   
        Label label = new Label("Memorize the flag");
        label.setStyle("-fx-font-size: 24px;");

        // Crear el botón
        Button button = new Button("Start");
        button.setStyle("-fx-font-size: 18px;");
        button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Scene scene = new Scene(new Tablero(), 500, 500);
		        primaryStage.setScene(scene);
			}
		});

        Button button2 = new Button("Explanation");
        button.setStyle("-fx-font-size: 18px;");
        // Establecer el color de fondo
        BackgroundFill backgroundFill = new BackgroundFill(Color.AQUAMARINE, null, null);
        Background background = new Background(backgroundFill);

        // Crear el VBox y establecer propiedades
        VBox vbox = new VBox(imageView, label, button, button2);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        // Establecer el padding en el VBox
        vbox.setPadding(new Insets(10));

        // Establecer el color de fondo en el VBox
        vbox.setBackground(background);

        // Crear la escena y configurarla en el escenario principal
        Scene scene = new Scene(vbox, 500, 500);
        primaryStage.setScene(scene);

        // Mostrar la ventana
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}