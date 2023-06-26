package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {

	private final static List<String> countries = List.of("colombia", "espana", "alemania", "mexico", "brasil",
			"argentina", "estados-unidos", "francia");

	private ExecutorService executor = Executors.newSingleThreadExecutor();

	private Cell[][] cells = new Cell[4][4];

	private Cell first = null;
	private Cell second = null;

	public Game() {
		this.initializeCells();
	}

	private List<String> getRandomCountries() {
		List<String> randomCountries = new ArrayList<>();
		randomCountries.addAll(countries);
		randomCountries.addAll(countries);
		Collections.shuffle(randomCountries);
		return randomCountries;
	}

	private void initializeCells() {
		List<String> randomCountries = getRandomCountries();

		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				cells[i][j] = new Cell(randomCountries.get(index));
				index++;
			}
		}
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void display(Cell cell) {
		if (first == null) {
			first = cell;
			first.display();
		} else if (second == null) {
			second = cell;
			second.display();
			if (first.getCountry().equals(second.getCountry())) {
				first = null;
				second = null;
			} else {
				resetCells();
			}
		}
	}

	private void resetCells() {
		executor.execute(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					first.hide();
					second.hide();
					first = null;
					second = null;
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
	}

}
