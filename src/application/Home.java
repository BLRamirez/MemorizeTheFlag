package application;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Home extends BorderPane{

	public Home(Stage primaryStage) {
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
						Scene scene = new Scene(new Tablero(primaryStage), 500, 500);
						primaryStage.setScene(scene);
					}
				});

				Button button2 = new Button("Explanation");
				button2.setStyle("-fx-font-size: 18px;");

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

				

				button2.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						BorderPane pane = new BorderPane();
						Image img = new Image(getClass().getResourceAsStream("/img/howtoplay.jpg"));
						ImageView view = new ImageView(img);
						view.setFitWidth(450);
						view.setPreserveRatio(true);
						pane.setCenter(view);

						Button buttonHome = new Button("Home");
						buttonHome.setStyle("-fx-font-size: 18px;");
						buttonHome.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								Scene scene = new Scene(new Home(primaryStage), 500, 500);
								primaryStage.setScene(scene);
							}
						});
						VBox vbox = new VBox(buttonHome);
						vbox.setAlignment(Pos.CENTER);
						vbox.setPadding(new Insets(10));
						vbox.setSpacing(20);
						pane.setBottom(vbox);
						Scene scene = new Scene(pane, 500, 500);
						primaryStage.setScene(scene);
					}
				});
				setCenter(vbox);
	}

	
}
