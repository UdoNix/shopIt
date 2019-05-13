package de.hdm.shared;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.shared.bo.*;
import de.hdm.thies.bankProjekt.shared.BankVerwaltungImpl;

/*
 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwaltung.
 *
 *@author Thies, Ciupe
 */

public interface ShopITAdministration extends RemoteService {
	
	/*
	 * Initialisierung des Objekts. Diese Methode ist vor dem 
	 * Hintergrund von GWT RPC zusätzlich zum No Argument Constructor 
	 * der implementierenden Klasse {@link EditorImpl} notwendig. 
	 * Bitte diese Methode direkt nach der Instantiierung aufrufen.
	 */
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
