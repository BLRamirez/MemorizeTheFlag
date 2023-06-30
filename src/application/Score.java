package application;

import java.time.Duration;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Es el ranking de los 5 mejores tiempos
 * @author LorenaRamirez
 *
 */
public class Score extends BorderPane{

	public Score(Game game,Stage primaryStage) {
		List<Duration> durations = game.getPreviousDurations();
		VBox vbox = new VBox();
		BackgroundFill backgroundFill = new BackgroundFill(Color.AQUAMARINE, null, null);
		Background background = new Background(backgroundFill);
		vbox.setBackground(background);
		for (Duration duration : durations) {
			int hours = duration.toHoursPart();
			int minutes = duration.toMinutesPart();
			int seconds = duration.toSecondsPart();
			Label label = new Label("Time: "+ String.format("%02d:%02d:%02d", hours, minutes, seconds));
			label.setFont(new Font(50));
			vbox.getChildren().add(label);
		}
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(20);
		
		Button buttonHome = new Button("Home");
		buttonHome.setStyle("-fx-font-size: 18px;");
		buttonHome.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Scene scene = new Scene(new Home(primaryStage), 500, 500);
				primaryStage.setScene(scene);
			}
		});
		VBox buttonVbox = new VBox(buttonHome);
		buttonVbox.setBackground(background);
		buttonVbox.setAlignment(Pos.CENTER);
		buttonVbox.setPadding(new Insets(10));
		buttonVbox.setSpacing(20);
		
		setCenter(vbox);
		setBottom(buttonVbox);
		
	}

	
}
