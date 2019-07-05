package de.hdm.shared.bo;
public class UnitOfMeasure extends BusinessObject{

	private static final long serialVersionUID = 1L;

	//Einheit
	private String unit;

	//Auslesen der Einheit eines Produkts eines Eintrags 
	public String getUnit() {
		return unit;
	}
	//Setzen der Einheit eines Produkts eines Eintrags (Bsp.: Liter, Palette)
	public void setUnit(String unit) {
		this.unit = unit;
	}
}
