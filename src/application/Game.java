package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

	private final static List<String> countries = List.of("colombia", "espana", "alemania", "mexico", "brasil",
			"argentina", "estados-unidos", "francia");

	private List<String> casillas;

	public Game() {
		casillas = new ArrayList<>();
		casillas.addAll(countries);
		casillas.addAll(countries);
		Collections.shuffle(casillas);
	}


	public List<String> getCasillas() {
		return casillas;
	}
	
	
	
	
}
