package de.hdm.shared;

import java.util.Vector;

import de.hdm.shared.bo.Team;
import de.hdm.shared.bo.List;
import de.hdm.shared.bo.Person;

public interface ShopITAdministrationAsync {
	
public void init() throws IllegalArgumentException; 
	
	//Einen Kunden anlegen.
	
	public Person createPerson(String first, String last) throws IllegalArgumentException; 
	
	//Eine Gruppe mit Name, Person und Einkaufsliste erstellen
	
	public Team createGroup(String name, Person p, List l) throws IllegalArgumentException; 
	
	//Auslesen aller Personen der Gruppe
	
	public Vector<Person> getPersonsOf(Team g) throws IllegalArgumentException; 
	
	//Auslesen aller Einkauflisten der Gruppe
	
	public Vector<List> getListsOf(Team g) throws IllegalArgumentException; 
	

}
