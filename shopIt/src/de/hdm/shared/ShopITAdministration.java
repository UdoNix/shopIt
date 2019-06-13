package de.hdm.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.shared.bo.*;

/*
 * Synchrone Schnittstelle f�r eine RPC-f�hige Klasse zur Verwaltung.
 *
 *@author Thies, Ciupe
 */

public interface ShopITAdministration extends RemoteService {
	
	/*
	 * Initialisierung des Objekts. Diese Methode ist vor dem 
	 * Hintergrund von GWT RPC zus�tzlich zum No Argument Constructor 
	 * der implementierenden Klasse {@link EditorImpl} notwendig. 
	 * Bitte diese Methode direkt nach der Instantiierung aufrufen.
	 */
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
