package application;

import java.time.Duration;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Score extends BorderPane{

	public Score(Game game) {
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
		setCenter(vbox);
		
	}

	
}
