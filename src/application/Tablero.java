package application;

import java.time.Duration;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * tablero donde se realiza la partida
 * @author LorenaRamirez
 *
 */
public class Tablero extends BorderPane{
	
	private Button[][] buttons= new Button[4][4];

	public Tablero(Stage primaryStage) {
		BackgroundFill backgroundFill = new BackgroundFill(Color.AQUAMARINE, null, null);
		Background background = new Background(backgroundFill);
		setBackground(background);
		Game game = new Game();
		GridPane grid = new GridPane();
		for (int i = 0; i < game.getCells().length; i++) {
			for (int j = 0; j < game.getCells()[i].length; j++) {
				Cell cell = game.getCells()[i][j];
				Button button = new Button();
				button.setMaxHeight(Double.MAX_VALUE);
				button.setMaxWidth(Double.MAX_VALUE);

				
				button.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {						
						game.display(cell);
					}
				});

				grid.add(button, i, j, 1, 1);
				buttons[i][j]=button;
			}
		}

		for (int i = 0; i < 4; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(25);
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(25);
			grid.getColumnConstraints().add(column);
			grid.getRowConstraints().add(row);
		}
		
		setCenter(grid);
		showTime(game);
		
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(!game.isFinished()) {
						Thread.sleep(100);
						Platform.runLater(new Runnable() {
						    @Override
						    public void run() {
						       draw(game);
						       showTime(game);
						    }
						});
					}
					Platform.runLater(new Runnable() {
					    @Override
					    public void run() {
					    	Scene scene = new Scene(new Score(game,primaryStage), 500, 500);
							primaryStage.setScene(scene);
					    }
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	
	private void draw(Game game) {
		for (int i = 0; i < game.getCells().length; i++) {
			for (int j = 0; j < game.getCells()[i].length; j++) {
				Cell cell = game.getCells()[i][j];
				Button button = buttons[i][j];
				if(cell.isDisplayed()) {
					Image img = new Image(getClass().getResourceAsStream("/banderas/"+cell.getCountry()+".png"));
					ImageView view = new ImageView(img);
					view.setFitWidth(100);
					view.setPreserveRatio(true);
					button.setGraphic(view);
				}else {
					button.setGraphic(null);
				}
			}
		}
	}
	
	
	private void showTime(Game game) {
		Duration duration = game.getDuration();
		int hours = duration.toHoursPart();
		int minutes = duration.toMinutesPart();
		int seconds = duration.toSecondsPart();
		Label label = new Label("Time: "+ String.format("%02d:%02d:%02d", hours, minutes, seconds));
		label.setFont(new Font(30));
		label.setAlignment(Pos.CENTER);
		label.setMinHeight(50);
		setBottom(label);
	}
	
}
