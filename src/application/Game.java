package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
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

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public Game() {
		this.initializeCells();
		this.startTime = LocalDateTime.now();
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
				if (isFinished()) {
					endTime = LocalDateTime.now();
					this.saveDuration();
				}
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

	public boolean isFinished() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (!cells[i][j].isDisplayed()) {
					return false;
				}
			}
		}
		return true;
	}

	public Duration getDuration() {
		LocalDateTime end = endTime != null ? endTime : LocalDateTime.now();
		return Duration.between(startTime, end);
	}

	private void saveDuration() {
		int MAX_NUMBER_OF_RECORDS = 5;

		
		List<Duration> previousDurations = getPreviousDurations();

		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(getSavePath().toFile())))) {
			List<Duration> allDurations = new ArrayList<>(previousDurations);
			allDurations.add(getDuration());
			Collections.sort(allDurations);

			int counter = 0;
			for (Duration duration : allDurations) {
				if (counter < MAX_NUMBER_OF_RECORDS) {
					pw.append(String.valueOf(duration.toMillis()));
					pw.append(System.lineSeparator());
				}
				counter++;
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	private Path getSavePath() {
		Path path = Paths.get(System.getProperty("user.dir") + "/records.txt");
		return path;
	}

	public List<Duration> getPreviousDurations() {
		try {
			Path path = getSavePath();
			List<String> lines = Files.readAllLines(path);
			return lines.stream().map(line -> Duration.ofMillis(Long.parseLong(line))).toList();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

}
