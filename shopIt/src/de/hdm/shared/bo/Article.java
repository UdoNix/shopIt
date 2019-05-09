package de.hdm.shared.bo;
public class Article extends BusinessObject{
	
	private static final long serialVersionUID = 1L;

	//Name des Artikels.
	private String name = "";
	
	//Mengeneinheit des Artikels.
	private UnitOfMeasure quantityUnit;

	//Auslesen des Namens.
	public String getName() {
		return name;
	}

	//Setzen des Namens.
	public void setName(String name) {
		this.name = name;
	}

	//Auslese der Mengeneinheit.
	public UnitOfMeasure getQuantityUnit() {
		return quantityUnit;
	}

	//Setzen der Mengeneinheit
	public void setQuantityUnit(UnitOfMeasure quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	
	
}

