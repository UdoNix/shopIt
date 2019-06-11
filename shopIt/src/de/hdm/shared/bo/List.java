package de.hdm.shared.bo;


public class List extends BusinessObject{

	private static final long serialVersionUID = 1L;

	
	//Name der Einkaufsliste.
	private String name = "";

	//Fremdschlüssel zur Gruppe.
	private int teamId;

	//Auslesen des Listennamen.
	public String getName() {
		return name;
	}
	
	//Setzen des Listennamen.
	public void setName(String name) {
		this.name = name;
	}

	
	//Auslesen des Fremdschlüssels zur GruppenId.
	public int getTeamId() {
		return teamId;
	}

	//Setzen der Fremdschlüsselbeziehung zur Gruppe.
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}



}
