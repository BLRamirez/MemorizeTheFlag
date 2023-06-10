package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Tablero extends GridPane {

	public Tablero() {
		Game game = new Game();
		int indice = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int posicion = indice;
				Button button = new Button();
				button.setMaxHeight(Double.MAX_VALUE);
				button.setMaxWidth(Double.MAX_VALUE);

				
				button.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						
						Image img = new Image(getClass().getResourceAsStream("/banderas/"+game.getCasillas().get(posicion)+".png"));
						ImageView view = new ImageView(img);
						view.setFitWidth(100);
						view.setPreserveRatio(true);
						button.setGraphic(view);
						
					}
				});

				add(button, i, j, 1, 1);
				indice++;
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

	}
	
	
	
}
