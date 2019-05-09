package de.hdm.shared.bo;
public class Group extends BusinessObject{

	private static final long serialVersionUID = 1L;
	
	//Name der Gruppe.
	private String name = "";
	
	private Person person;
	
	private List list;

	//Auslesen des Gruppennamen.
	public String getName() {
		return name;
	}
	//Setzen des Gruppennamen.
	public void setName(String name) {
		this.name = name;
	}



}
