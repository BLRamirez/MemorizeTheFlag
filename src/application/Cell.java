package application;

/**
 * representa una celda con su respectiva bandera
 * @author LorenaRamirez
 *
 */
public class Cell {

	private String country;
	
	private boolean displayed;

	/**
	 * permite construir una celda con una bandera asociada
	 * por defecto esta no se mustra
	 * @param country
	 */
	public Cell(String country) {
		super();
		this.country = country;
		this.displayed = false;
	}
	
	/**
	 * muesta la bandera
	 */
	public void display() {
		this.displayed = true;
	}
	
	/**
	 * oculta la bandera
	 */
	public void hide() {
		this.displayed=false;
	}

	/**
	 * devuelve la bandera
	 * @return
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * devuelve true si la bandera se esta mostrando
	 * @return
	 */
	public boolean isDisplayed() {
		return displayed;
	}
	
	
}
