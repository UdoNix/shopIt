package de.hdm.shared.bo;


public class Article extends BusinessObject{
	
	private static final long serialVersionUID = 1L;

	//Name des Artikels.
	private String name = "";

	//Auslesen des Namens.
	public String getName() {
		return name;
	}

	//Setzen des Namens.
	public void setName(String name) {
		this.name = name;
	}

	
	
}

