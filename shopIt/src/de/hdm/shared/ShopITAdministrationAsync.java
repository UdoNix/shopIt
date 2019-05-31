package de.hdm.shared;

import java.util.Vector;

import de.hdm.shared.bo.Group;
import de.hdm.shared.bo.List;
import de.hdm.shared.bo.Person;

public interface ShopITAdministrationAsync {
	
public void init() throws IllegalArgumentException; 
	
	//Einen Kunden anlegen.
	
	public Person createPerson(String first, String last) throws IllegalArgumentException; 
	
	//Eine Gruppe mit Name, Person und Einkaufsliste erstellen
	
	public Group createGroup(String name, Person p, List l) throws IllegalArgumentException; 
	
	//Auslesen aller Personen der Gruppe
	
	public Vector<Person> getPersonsOf(Group g) throws IllegalArgumentException; 
	
	//Auslesen aller Einkauflisten der Gruppe
	
	public Vector<List> getListsOf(Group g) throws IllegalArgumentException; 
	

}
