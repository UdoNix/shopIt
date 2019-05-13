package de.hdm.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.text.DateFormat;

import java.util.ArrayList;
import java.util.Vector;

import de.hdm.shared.ShopITAdministration;
import de.hdm.shared.bo.Group;
import de.hdm.shared.bo.Item;
import de.hdm.shared.bo.List;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Salesman;

public class EditorImpl extends RemoteServiceServlet implements ShopITAdministration {
	
	//Referenz auf die MapperKlassen, um die Objekte mit der Datenbank abzugleichen.
	private PersonMapper pMapper = null;
	private ArticleMapper aMapper = null;
	private GroupMapper gMapper = null;
	private ItemMapper iMapper = null;
	private ListMapper lMapper = null;
	private SalesmanMapper sMapper = null;
	private UnitOfMeasureMapper uMapper = null;
	
	//Um die Klasse Ã¼bersichtlicher zu gestalten, wird sie mithilfe von Abschnitten unterteilt.
	 /*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Initialisierung
	   * ***************************************************************************
	   */
	
	public EditorImpl() throws IllegalArgumentException{
		//No-Argument-Constructor.
	}

	public void init() throws IllegalArgumentException{
	
		this.pMapper = PersonMapper.personMapper();
		this.aMapper = ArticleMapper.articleMapper();
		this.gMapper = GroupMapper.groupMapper();
		this.iMapper = ItemMapper.itemMapper();
		this.lMapper = ListMapper.listMapper();
		this.sMapper = SalesmanMapper.salesmanMapper();
		this.uMapper = UnitOfMeasureMapper.unitOfMeasureMapper();
		
	}
	
	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Initialisierung
	   * ***************************************************************************
	   */
	
	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden fÃ¼r Personen/Anwender-Objekte
	   * ***************************************************************************
	   */
	
	//Erstellen eines Anwenders-Objekts mit Vorname, Nachname und Email-Adresse.
	public Person createPerson(String firstName, String lastName, String email) throws IllegalArgumentException{
		Person p = new Person();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setEmail(email);
		
		//Setzen einer vorlÃ¤ufigen Anwenders-Id, welche nach Kommunikation mit DB auf den nÃ¤chsthhÃ¶heren Wert gesetzt wird.
		p.setId(1);
		
		//Speichern des Anwenders-Objekts in der DB.
		return this.pMapper.insert(p);
	}
	
	//Auslesen eines Anwenders anhand seiner Anwender-Id.
	public Person getPersonById(int id) throws IllegalArgumentException{
		return this.pMapper.findByKey(id);
	}
	
	//Auslesen aller Anwender.
	public Vector<Person> getAllPersons() throws IllegalArgumentException{
		return this.pMapper.findAll();
	}
	
	//Speichern eines Anwenders.
	public void save(Person p) throws IllegalArgumentException{
		pMapper.update(p);
	}
	
	//LÃ¶schen eines Anwenders.
	//
	public void delete(Person p) throws IllegalArgumentException{
	
	}
	
	
	
	
	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden fÃ¼r Anwender-Objekte
	   * ***************************************************************************
	   */
	

	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Liste @author Ilona
	   * ***************************************************************************
	   */
	
	/*
	 * neue Liste erstellen
	 */
	public List createListFor(Group g, String name) throws IllegalArgumentException{
		List l = new List();
		//creationDate + modification Date noch hinzufügen
		l.setId(1);
		l.setName(name);
		l.setGroup(g);
		
		return this.iMapper.insert(l);
		
	}
	/*
	 * Liste anhand der Id finden
	 */
	public List getListById(int id) throws IllegalArgumentException{
		return this.lMapper.findByKey(id);
	}
	/*
	 * alle Listen aufzeigen
	 */
	public Vector<Item> getAllItemsOf(List l) throws IllegalArgumentException{
		return this.iMapper.findByList(l);
	}
	/*
	 * eine Liste ändern
	 */
	public void update(List l) throws IllegalArgumentException{
		lMapper.update(l); // noch nicht fertig
	}
	/*
	 * eine Liste löschen
	 */
	public void delete(List l) throws IllegalArgumentException{
		 ArrayList<Item> items = this.getAllItemsOf(l); // muss noch erstellt werden, siehe oben
		 
		    if (items != null) {
		      for (Item item : items) {
		        this.delete(item);
		      }
		    }

		    // Account aus der DB entfernen
		    this.lMapper.delete(l);
		  }
		
		
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Liste
	   * ***************************************************************************
	   */
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Eintrag @author Ilona
	   * ***************************************************************************
	   */
	/*
	 * neuen Eintrag erstellen
	 */
	public List createItem(List l, String name) throws IllegalArgumentException{
		Item i = new Item();
		i.setCreationDate();//aktuelles Datum einfügen
		i.setId(1);
		i.setList(l);
		return this.iMapper.insert(i);
	}
	/*
	 * Eintrag anhand der Id finden
	 */
	public Item getItemById(int id) throws IllegalArgumentException{
		return this.iMapper.findByKey(id);
	}
	/*
	 * alle Einträge aufzeigen
	 */
	public Vector<Item> getAllItems() throws IllegalArgumentException{
		return this.iMapper.findAll();
	}
	/*
	 * einen Eintrag ändern
	 */
	public void update(Item i) throws IllegalArgumentException{
		iMapper.update(i);
	}
	/*
	 * einen Eintrag löschen
	 */
	public void delete(Item i) throws IllegalArgumentException{
		iMapper.delete(i);
	}


	   /*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Gruppe-Objekte
	   * ***************************************************************************
	   */
	
	//Erstellen einer Gruppe mit Name, Anwender und Einkaufsliste 
	public Group createGroup(String name, Person p) throws IllegalArgumentException {
		Group g = new Group(); 
		g.setName(name);
		g.setPerson(p); 
		
		//Setzen einer vorlÃ¤ufigen Gruppe-Id, welche nach Kommunikation mit DB auf den nÃ¤chsthhÃ¶heren Wert gesetzt wird.
		p.setId(1);
		
		//Einen Anwender hinzufügen
		g.addPerson(p); 
				
		//Speichern des Gruppe-Objekts in der DB.
		return this.gMapper.insert(g); 
	}
	
	//Auslesen einer Gruppe anhand seiner Gruppe-Id.
	public Group getGroupById(int id) throws IllegalArgumentException{
			return this.gMapper.findByKey(id);
	}
		
	//Auslesen aller Gruppen.
	public Vector<Group> getAllGroups() throws IllegalArgumentException{
		return this.gMapper.findAll();
	}
		
	//Speichern einer Gruppe.
	public void save(Group g) throws IllegalArgumentException{
		gMapper.update(g);
	}
		
		
	//Löschen einer Gruppe.
		
	public void delete(Group g) throws IllegalArgumentException {
		/*
		 * Zunächst werden alle Anwender und Einkaufslisten der Gruppe aus
		 * der Datenbank entfernt.	
		 */
		
		Vector<Person> persons = this.getPersonsOf(g); 
		
		if (persons != null) {
			for (Person p: persons) {
				this.delete(p);
			}
		}
		
		Vector<List> lists = this.getListsOf(g);
		
		if (lists != null) {
			for (List l: lists) {
				this.delete(l);
			}
		}
		/*
		 * Anschließend die Gruppe entfernen
		 */
		this.gMapper.delete(g);
		
	}

	

	
	
	
	   /*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Gruppe-Objekte
	   * ***************************************************************************
	   */
		

	   /*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Händler-Objekte
	   * ***************************************************************************
	   */
	
	public Salesman createSalesman(String name, String street, int plz, String city) throws IllegalArgumentException {
		Salesman s = new Salesman();
		s.setCity(city);
		s.setStreet(street);
		s.setPlz(plz);
		s.setName(name);
		
		/* Setzen einer vorlÃ¤ufigen Händler-Id, welche nach Kommunikation 
		*mit DB auf den nÃ¤chsthhÃ¶heren Wert gesetzt wird.
		*
		*/
		s.setId(id);
		
		//Objekt in der DB speichern.
		return this.sMapper.insert(s); 
		
	}
	
	/*
	 * Auslesen einer Händler anhand seiner Händler-Id.
	 */
	public Vector<Salesman> getSalesmanById(int id) throws IllegalArgumentException {
		return this.sMapper.findByKey(id); 
	}
	
	/*
	 * Auslesen aller Händler.
	 */
	public Vector<Salesman> getAllSalesman() throws IllegalArgumentException {
		return this.sMapper.findAll(); 
	}
	
	/*
	 * Speichern eines Händlers.
	 */
	public void save(Salesman c) thros IllegalArgumentException {
		sMapper.update(s); 
	}
	
	/*
	 * Löschen eines Händlers. 
	 */
	
	public void delete(Salesman s) throws IllegalArgumentException {
		/*
		 * Zunächst werden alle Einträge dieses Händler gelöscht werden.
		 */
		Vector<Item> items = this.getItemsOf(s); 
		
		if (items != null) {
			for (Item i : items) {
				this.delete(i); 
			}
		}
		
		//Anschließend den Händler entfernen
		this.sMapper.delete(s); 
		
	}
		
		
	   /*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Händler-Objekte
	   * ***************************************************************************
	   */
	
		
	
	

}
