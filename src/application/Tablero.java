package application;

import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Tablero extends GridPane {
	
	private Button[][] buttons= new Button[4][4];

	public Tablero() {
		Game game = new Game();
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

				add(button, i, j, 1, 1);
				buttons[i][j]=button;
			}
		}

		for (int i = 0; i < 4; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(25);
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(25);
			getColumnConstraints().add(column);
			getRowConstraints().add(row);
		}
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true) {
						Thread.sleep(100);
						Platform.runLater(new Runnable() {
						    @Override
						    public void run() {
						       draw(game);
						    }
						});
					}
				} catch (Exception e) {
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
	
	
	
}
