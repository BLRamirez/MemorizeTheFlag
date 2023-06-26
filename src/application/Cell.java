package application;

public class Cell {

	private String country;
	
	private boolean displayed;

	public Cell(String country) {
		super();
		this.country = country;
		this.displayed = false;
	}
	
	public void display() {
		this.displayed = true;
	}
	
	public void hide() {
		this.displayed=false;
	}

	public String getCountry() {
		return country;
	}

	public boolean isDisplayed() {
		return displayed;
	}
	
	
}
