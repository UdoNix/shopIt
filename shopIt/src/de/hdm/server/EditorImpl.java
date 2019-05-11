package de.hdm.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.text.DateFormat;

import java.util.ArrayList;
import java.util.Vector;

import de.hdm.shared.ShopITAdministration;
import de.hdm.shared.bo.Group;
>>>>>>> refs/remotes/origin/Larisa
import de.hdm.shared.bo.List;
import de.hdm.shared.bo.Person;

public class EditorImpl extends RemoteServiceServlet implements ShopITAdministration {
	
	//Referenz auf die MapperKlassen, um die Objekte mit der Datenbank abzugleichen.
	private PersonMapper pMapper = null;
	private ArticleMapper aMapper = null;
	private GroupMapper gMapper = null;
	private ItemMapper iMapper = null;
	private ListMapper lMapper = null;
	private SalesmanMapper sMapper = null;
	private UnitOfMeasureMapper uMapper = null;
	
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
		this.uMapper = UnitOfMeasureMapper.unitOfMeasureMapper();
		
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
	   * ABSCHNITT, Beginn: Methoden f�r Liste @author Ilona
	   * ***************************************************************************
	   */
	
	/*
	 * neue Liste erstellen
	 */
	public List createListFor(Group g, String name) throws IllegalArgumentException{
		List l = new List();
		//creationDate + modification Date noch hinzuf�gen
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
	public Vector<List> getAllLists() throws IllegalArgumentException{
		return this.lMapper.findAll();
	}
	/*
	 * eine Liste �ndern
	 */
	public void update(List l, Article a, ) throws IllegalArgumentException{
		lMapper.update(l);
	}
	/*
	 * eine Liste l�schen
	 */
	public void delete(List l) throws IllegalArgumentException{
		 ArrayList<Item> debits = this.getDebitsOf(a);
		 ArrayList<Transaction> credits = this.getCreditsOf(a);

		    if (debits != null) {
		      for (Transaction t : debits) {
		        this.delete(t);
		      }
		    }

		    if (credits != null) {
		      for (Transaction t : credits) {
		        this.delete(t);
		      }
		    }

		    // Account aus der DB entfernen
		    this.aMapper.delete(a);
		  }
		
		lMapper.delete(l);
	}
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Ende: Methoden f�r Liste
	   * ***************************************************************************
	   */
	/*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden f�r Eintrag @author Ilona
	   * ***************************************************************************
	   */
	/*
	 * neuen Eintrag erstellen
	 */
	public List createItem(String name) throws IllegalArgumentException{
		Item i = new Item();
		i.setCreationDate();//aktuelles Datum einf�gen
		i.setId(1);
		return this.iMapper.insert(i);
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


	   /*
	   * ***************************************************************************
	   * ABSCHNITT, Beginn: Methoden f�r Gruppe-Objekte
	   * ***************************************************************************
	   */
	
	//Erstellen einer Gruppe mit Name, Anwender und Einkaufsliste 
	public Group createGroup(String name, Person p, List l) throws IllegalArgumentException {
		Group g = new Group(); 
		g.setName(name);
		g.setPerson(p); 
		g.setList(l);
		
		//Setzen einer vorläufigen Gruppe-Id, welche nach Kommunikation mit DB auf den nächsthhöheren Wert gesetzt wird.
		p.setId(1);
				
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
		
		public void deleteGroup(Group g) throws IllegalArgumentException {
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
	
	

}
