package de.hdm.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.text.DateFormat;

import java.util.ArrayList;
import java.util.Vector;

import de.hdm.shared.ShopITAdministration;
import de.hdm.shared.bo.Article;
import de.hdm.shared.bo.Group;
import de.hdm.shared.bo.Item;
import de.hdm.shared.bo.List;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Responsibility;
import de.hdm.shared.bo.Salesman;

public class EditorImpl extends RemoteServiceServlet implements ShopITAdministration {
	
	//Referenz auf die MapperKlassen, um die Objekte mit der Datenbank abzugleichen.
	private PersonMapper pMapper = null;
	private ArticleMapper aMapper = null;
	private GroupMapper gMapper = null;
	private ItemMapper iMapper = null;
	private ListMapper lMapper = null;
	private SalesmanMapper sMapper = null;
	private ResponsibilityMapper rMapper = null;
	private UnitOfMeasureMapper uMapper = null;
	private MembershipMapper mMapper = null;
	
	//Um die Klasse übersichtlicher zu gestalten, wird sie mithilfe von Abschnitten unterteilt.
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
		this.rMapper = ResponsibilityMapper.responsibilityMapper();
		this.uMapper = UnitOfMeasureMapper.unitOfMeasureMapper();
		this.mMapper = MembershipMapper.membershipMapper();
		
	}
	
	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Initialisierung
	   * ***************************************************************************
	   */
	
	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden für Personen/Anwender-Objekte
	   * ***************************************************************************
	   */
	
	//Erstellen eines Anwenders-Objekts mit Vorname, Nachname und Email-Adresse.
	public Person createPerson(String firstName, String lastName, String email) throws IllegalArgumentException{
		Person p = new Person();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setEmail(email);
		
		//Setzen einer vorläufigen Anwenders-Id, welche nach Kommunikation mit DB auf den nächsthhöheren Wert gesetzt wird.
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
	
	//Löschen eines Anwenders.
	//
	public void delete(Person p) throws IllegalArgumentException{
	
	}
	
	
	
	
	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden für Anwender-Objekte
	   * ***************************************************************************
	   */
	

	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden f�r Liste @author Thies Ilona
	   * ***************************************************************************
	   */
	
	/*
	 * neue Liste erstellen
	 */
	public List createListFor(Group g, String name) throws IllegalArgumentException{
		List l = new List();
		l.setId(1);
		l.setName(name);
		l.setGroupId(g.getId());
		
		
		return this.lMapper.insert(l);
		
	}
	
	/*
	 * Liste anhand der Id finden
	 */
	public List getListById(int id) throws IllegalArgumentException{
		return this.lMapper.findByKey(id);
	}
	/*
	 * alle Eintr�ge einer Liste aufzeigen
	 */
	public Vector<Item> getAllItemsOfList(List l) throws IllegalArgumentException{
		return this.iMapper.findByList(l);
	}
	/*
	 * eine Liste �ndern
	 */
	public void update(List l) throws IllegalArgumentException{
		lMapper.update(l);
	}
	/*
	 * eine Liste l�schen
	 */
	public void delete(List l) throws IllegalArgumentException{
		 //alle Eintr�ge der Liste suchen und ggf. l�schen
		Vector<Item> items = this.getAllItemsOf(l);
		 
		    if (items != null) {
		      for (Item item : items) {
		        this.delete(item);
		      }
		    }

		    // Liste aus der DB entfernen
		    this.lMapper.delete(l);
		  }
		
		
	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden f�r Liste
	   * ***************************************************************************
	   */
	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden f�r Eintrag @author Thies Ilona
	   * ***************************************************************************
	   */
	/*
	 * neuen Eintrag erstellen
	 */
	public Item createItem(List l, Article a) throws IllegalArgumentException{
		Item i = new Item();
		i.setId(1);
		i.setListId(l.getId());
		return this.iMapper.insert(i);
	}
	/*
	 * Zust�ndigkeit zum Eintrag hinzuf�gen
	 */
	public Item addResponsibilityToItem(Responsibility r, Item i) {
		i.setResponsibility(r);
	}
	/*
	 * Eintrag anhand der Id finden
	 */
	public Item getItemById(int id) throws IllegalArgumentException{
		return this.iMapper.findByKey(id);
	}
	/*
	 * alle Eintr�ge aufzeigen
	 */
	public Vector<Item> getAllItems() throws IllegalArgumentException{
		return this.iMapper.findAll();
	}
	/*
	 * einen Eintrag �ndern
	 */
	public void update(Item i) throws IllegalArgumentException{
		iMapper.update(i);
	}
	/*
	 * einen Eintrag l�schen
	 */
	public void delete(Item i) throws IllegalArgumentException{
		iMapper.delete(i);
	}
	
	//Eintrag favorit und abhacken

	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden f�r Eintrag 
	   * ***************************************************************************
	   */

	   /*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden f�r Gruppe-Objekte
	   * ***************************************************************************
	   */
	
	//Erstellen einer Gruppe mit Name, Anwender und Einkaufsliste 
	public Group createGroup(String name, Person p) throws IllegalArgumentException {
		Group g = new Group(); 
		g.setName(name);
		g.setPerson(p); 
		
		//Setzen einer vorläufigen Gruppe-Id, welche nach Kommunikation mit DB auf den nächsthhöheren Wert gesetzt wird.
		p.setId(1);
		
		//Einen Anwender hinzuf�gen
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
		
		
	//L�schen einer Gruppe.
		
	public void delete(Group g) throws IllegalArgumentException {
		/*
		 * Zun�chst werden alle Anwender und Einkaufslisten der Gruppe aus
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
		 * Anschlie�end die Gruppe entfernen
		 */
		this.gMapper.delete(g);
		
	}

	

	
	
	
	   /*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden f�r Gruppe-Objekte
	   * ***************************************************************************
	   */
		

	   /*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden f�r H�ndler-Objekte
	   * ***************************************************************************
	   */
	
	public Salesman createSalesman(String name, String street, int plz, String city) throws IllegalArgumentException {
		Salesman s = new Salesman();
		s.setCity(city);
		s.setStreet(street);
		s.setPlz(plz);
		s.setName(name);
		
		/* Setzen einer vorläufigen H�ndler-Id, welche nach Kommunikation 
		*mit DB auf den nächsthhöheren Wert gesetzt wird.
		*
		*/
		s.setId(id);
		
		//Objekt in der DB speichern.
		return this.sMapper.insert(s); 
		
	}
	
	/*
	 * Auslesen einer H�ndler anhand seiner H�ndler-Id.
	 */
	public Vector<Salesman> getSalesmanById(int id) throws IllegalArgumentException {
		return this.sMapper.findByKey(id); 
	}
	
	/*
	 * Auslesen aller H�ndler.
	 */
	public Vector<Salesman> getAllSalesman() throws IllegalArgumentException {
		return this.sMapper.findAll(); 
	}
	
	/*
	 * Speichern eines H�ndlers.
	 */
	public void save(Salesman c) thros IllegalArgumentException {
		sMapper.update(s); 
	}
	
	/*
	 * L�schen eines H�ndlers. 
	 */
	
	public void delete(Salesman s) throws IllegalArgumentException {
		/*
		 * Zun�chst werden alle Eintr�ge dieses H�ndler gel�scht werden.
		 */
		Vector<Item> items = this.getItemsOf(s); 
		
		if (items != null) {
			for (Item i : items) {
				this.delete(i); 
			}
		}
		
		//Anschlie�end den H�ndler entfernen
		this.sMapper.delete(s); 
		
	}
		
		
	   /*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden f�r H�ndler-Objekte
	   * ***************************************************************************
	   */
	
	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden f�r Zust�ndigkeits-Objekte
	   * ***************************************************************************
	   */
		
	/*
	 * Zust�ndigkeit erstellen
	 */
	
	public Responsibility createResponsibility(Person p, Salesman s) throws IllegalArgumentException{
		Responsibility r = new Responsibility();
		r.setPerson(p);
		r.setSalesman(s);
		
		return this.rMapper.insert(r);
		
	}
	/*
	 * Zust�ndigkeit anhand der Id finden
	 */
	public Responsibility getResponsibilityById(int id) throws IllegalArgumentException{
		return this.rMapper.findByKey(id);
	}
	/*
	 * alle Zust�ndigkeiten einer Person aufzeigen
	 */
	public Vector<Item> getAllResponsibilityOfPerson(Person p) throws IllegalArgumentException{
		return this.rMapper.findByPerson(p);
	}
	/*
	 * eine Zust�ndigkeit �ndern
	 */
	public void update(Responsibility r) throws IllegalArgumentException{
		rMapper.update(r);
		
	}
	/*
	 * eine Zust�ndigkeit l�schen
	 */
	public void delete(Responsibility r) throws IllegalArgumentException{
		 
		    this.rMapper.delete(r);
		  }
	
	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden f�r Zust�ndigkeits-Objekte
	   * ***************************************************************************
	   */
	
	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Begin: Methoden f�r Gruppenmitgliedschaft-Objekte
	   * ***************************************************************************
	   */
	
	/*
	 * Gruppenmitgliedschaft erstellen
	 */
	
	public Membership createMembership(Person p, Group g) throws IllegalArgumentException{
		Membership m = new Membership();
		m.setPerson(p);
		m.setGroup(g);
		m.setId(1);
		
		return this.mMapper.insert(m);
		
	}
	/*
	 * Gruppenmitgliedschaft anhand der Id finden
	 */
	public Membership getMembershipById(int id) throws IllegalArgumentException{
		return this.mMapper.findByKey(id);
	}
	/*
	 * alle Gruppen einer Person aufzeigen
	 */
	public Vector<Group> getAllMembershipOfPerson(Person p) throws IllegalArgumentException{
		return this.mMapper.findByPerson(p);
	}
	/*
	 * eine Gruppenmitgliedschaft �ndern
	 */
	public void update(Membership m) throws IllegalArgumentException{
		mMapper.update(m);
	}
	/*
	 * eine Gruppenmitgliedschaft l�schen
	 */
	public void delete(Membership m) throws IllegalArgumentException{
		 
		    this.mMapper.delete(m);
		  }
	  /*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden f�r Gruppenmitgliedschaft-Objekte
	   * ***************************************************************************
	   */

}
