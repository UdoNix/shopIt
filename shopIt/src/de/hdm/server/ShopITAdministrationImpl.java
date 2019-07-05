package de.hdm.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.server.db.ArticleMapper;
import de.hdm.server.db.TeamMapper;
import de.hdm.server.db.ItemMapper;
import de.hdm.server.db.ListMapper;
import de.hdm.server.db.MembershipMapper;
import de.hdm.server.db.PersonMapper;
import de.hdm.server.db.ReportMapper;
import de.hdm.server.db.ResponsibilityMapper;
import de.hdm.server.db.ShopMapper;
import de.hdm.server.db.UnitOfMeasureMapper;
import de.hdm.shared.ShopITAdministration;
import de.hdm.shared.bo.Article;
import de.hdm.shared.bo.Team;
import de.hdm.shared.bo.Item;
import de.hdm.shared.bo.ShoppingList;
import de.hdm.shared.bo.Membership;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.ReportObject;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.UnitOfMeasure;
import de.hdm.shared.bo.Responsibility;

@SuppressWarnings("serial")
public class ShopITAdministrationImpl extends RemoteServiceServlet implements ShopITAdministration {

	// Referenz auf die MapperKlassen, um die Objekte mit der Datenbank abzugleichen
	// @autor InesWerner

	private PersonMapper pMapper = null;
	private ArticleMapper aMapper = null;
	private TeamMapper tMapper = null;
	private ItemMapper iMapper = null;
	private ListMapper lMapper = null;
	private ShopMapper sMapper = null;
	private ResponsibilityMapper rMapper = null;
	private UnitOfMeasureMapper uMapper = null;
	private MembershipMapper mMapper = null;
	private ReportMapper reportMapper = null;

	// Um die Klasse übersichtlicher zu gestalten, wird sie mithilfe von
	// Abschnitten
	// unterteilt.
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Initialisierung @autor InesWerner
	 * ***************************************************************************
	 */

	public ShopITAdministrationImpl() throws IllegalArgumentException {
		// No-Argument-Constructor.
	}

	public void init() throws IllegalArgumentException {

		this.pMapper = PersonMapper.personMapper();
		this.aMapper = ArticleMapper.articleMapper();
		this.tMapper = TeamMapper.teamMapper();
		this.iMapper = ItemMapper.itemMapper();
		this.lMapper = ListMapper.listMapper();
		this.sMapper = ShopMapper.shopMapper();
		this.rMapper = ResponsibilityMapper.responsibilityMapper();
		this.uMapper = UnitOfMeasureMapper.unitOfMeasureMapper();
		this.mMapper = MembershipMapper.membershipMapper();
		this.reportMapper = ReportMapper.reportMapper();

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Initialisierung
	 * ***************************************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden für Personen/Anwender-Objekte
	 * 
	 * @autor InesWerner
	 * ***************************************************************************
	 */

	// Erstellen eines neuen Anwender-Objekts mit Vorname, Nachname und
	// Email-Adresse.
	// Dies führt zu einem Speichern des Anwender-Objekts in der Datenbank.
	public Person createPerson(String firstName, String lastName, String email) throws IllegalArgumentException {
		Person p = new Person();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setEmail(email);

		// Setzen einer vorläufigen Anwender-Id, welche nach Kommunikation mit DB auf
		// den nächsthhöheren Wert gesetzt wird.
		p.setId(1);

		// Speichern des Anwender-Objekts in der DB.
		return this.pMapper.insert(p);
	}

	// Auslesen eines Anwenders anhand seiner Anwender-Id.
	public Person getPersonById(int id) throws IllegalArgumentException {
		return this.pMapper.findByKey(id);
	}

	// Auslesen eines Anwenders anhand seines Namen.
	public Vector<Person> getPersonByName(Person p) {
		return this.pMapper.findByName(p);
	}

	// Auslesen eines Anwenders anhand seines Nachnamen.
	// public Person getPersonByLastName(String lastName){
	// return this.pMapper.findPersonByLastName(lastName);
	// }

	// Auslesen eines Anwenders anhand seiner Email.
	public Person getPersonByEmail(String email) {
		try { 
			return this.pMapper.findPersonByEmail(email);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public Person getCurrentPerson() {
		try {
			return this.pMapper.findPersonByEmail(getThreadLocalRequest().getSession().getAttribute("email").toString());
		} catch (SQLException e) {
			throw new IllegalStateException(e);
		}
	}

	// Auslesen aller Anwender.
	public Vector<Person> getAllPersons() throws IllegalArgumentException {
		return this.pMapper.findAll();
	}

	// Speichern eines Anwenders.
	public void save(Person p) throws IllegalArgumentException {
		pMapper.update(p);
	}

	// Löschen eines Anwenders.
	public void delete(int personId) throws IllegalArgumentException {

		// Löschen von Responsibilityobjekten in denen der zu löschende Anwender als
		// Fremdschlüssel auftritt.
		Vector<Responsibility> responsibilities = this.rMapper.findByPerson(personId);
		if (responsibilities != null) {
			for (Responsibility r : responsibilities) {
				this.delete(personId);
			}
		}

		// Löschen von Membershipobjekten in denen der zu löschende Anwender auftritt.
//		Vector<Membership> memberships = this.mMapper.getAllMembershipsOf(personId);
//		if (memberships != null){
//			for (Membership m : memberships){
//				this.delete(personId);
//			}
//		}

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden für Anwender-Objekte
	 * ***************************************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden f�r Liste @author IlonaBrinkmann
	 * ***************************************************************************
	 */

	/*
	 * neue Liste erstellen
	 */

	public ShoppingList createListFor(Team t, String name) throws IllegalArgumentException {

		ShoppingList l = new ShoppingList();
		l.setId(1);
		l.setName(name);
		l.setTeamId(t.getId());
		l = this.lMapper.insert(l);

		Vector<Item> favoriteItems = iMapper.findFavoritesByTeam(t);
		for (Item item : favoriteItems) {
			Responsibility responsibility = rMapper.findByKey(item.getResponsibilityId());
			UnitOfMeasure unit = uMapper.findByKey(item.getUnitId());
			createItem(l.getId(), t.getId(), item.getAmount(), unit.getId(), item.getArticleId(),
					responsibility.getPersonId(), responsibility.getShopId());
		}

		return l;
	}

	/*
	 * Liste anhand der Id finden
	 */
	public ShoppingList getListById(int id) throws IllegalArgumentException {
		return this.lMapper.findByKey(id);
	}

	/*
	 * alle Eintr�ge einer Liste aufzeigen
	 */
	public Vector<Item> getAllItemsOfList(ShoppingList l, Person p, Shop s) throws IllegalArgumentException {
		if(p != null) {
			return this.iMapper.findByListAndPerson(l, p);
		} else if (s != null) {
			return this.iMapper.findByListAndShop(l, s);
		} else {
			return this.iMapper.findByList(l);
		}
	}

	/*
	 * eine Liste �ndern
	 */
	public void update(ShoppingList l) throws IllegalArgumentException {
		lMapper.update(l);
	}

	/*
	 * eine Liste l�schen
	 */
	public void delete(ShoppingList l) throws IllegalArgumentException {
		// alle Eintr�ge der Liste suchen und ggf. l�schen
		Vector<Item> items = iMapper.findByList(l);

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
	 * 
	 * ABSCHNITT, Beginn: Methoden f�r Eintrag @author IlonaBrinkmann
	 * 
	 * ***************************************************************************
	 */
	/*
	 * neuen Eintrag erstellen
	 */
	public Item createItem(int listId, int teamId, float count, int unitId, int articleId, int personId, int shopId)
			throws IllegalArgumentException {
		
		Item i = new Item();
		// i.setCreationDate();//aktuelles Datum einf�gen Muss nicht gesetzt werden,
		// das
		// macht die DB

		UnitOfMeasure unitOfMeasure = uMapper.findByKey(unitId);
		i.setListId(listId);
		i.setArticleId(articleId);
		i.setUnitId(unitOfMeasure.getId());
		i.setTeamId(teamId);
		i.setAmount(count);
		i = this.iMapper.insert(i);

		System.out.println("Created item at list " + i.getListId());

		Responsibility r = new Responsibility();
		r.setItemId(i.getId());
		r.setPersonId(personId);
		r.setShopId(shopId);
		this.rMapper.insert(r);

		return i;
	}

	/*
	 * Zust�ndigkeit zum Eintrag hinzuf�gen
	 */
	public Item addResponsibilityToItem(Responsibility r, Item i) {
		i.setResponsibilityId(r.getId());
		return i;
	}

	/*
	 * Eintrag anhand der Id finden
	 */
	public Item getItemById(int id) throws IllegalArgumentException {
		return this.iMapper.findByKey(id);
	}

	/*
	 * alle Eintr�ge aufzeigen
	 */
	public Vector<Item> getAllItems() throws IllegalArgumentException {
		return this.iMapper.findAll();
	}

	/*
	 * einen Eintrag �ndern
	 */
	public void update(Item i) throws IllegalArgumentException {
		iMapper.update(i);
		Responsibility r = rMapper.findByItem(i.getId());
		r.setPersonId(i.getPersonId());
		r.setShopId(i.getShopId());
		rMapper.update(r);
	}

	/*
	 * einen Eintrag l�schen
	 */
	public void delete(Item i) throws IllegalArgumentException {
		iMapper.delete(i);
		rMapper.delete(rMapper.findByKey(i.getResponsibilityId()));
	}

	// Status des Eintrags ändern. (Eintrag abhaken bzw. den Haken entfernen)
	public void changeStatus(Item i) throws IllegalArgumentException {
		if (i.isStatus() == false) {
			i.setStatus(true);
			this.iMapper.insert(i);
		} else if (i.isStatus() == true) {
			i.setStatus(false);
			this.iMapper.insert(i);
		}
	}

	// Setzen/Entfernen des Favoritenstatus (Standardartikel)
	public void changeFavorit(Item i) throws IllegalArgumentException {
		if (i.isFavorit() == false) {
			i.setFavorit(true);
			this.iMapper.insert(i);
		} else if (i.isFavorit() == true) {
			i.setFavorit(false);
			this.iMapper.insert(i);
		}
	}
	
	/**
	 * Liste nach Person filtern. @Larisa 
	 */
	
	public void sortByPerson(ShoppingList l, Person p) {
		 
		Vector<Item> item = this.iMapper.findAll(); 
		
		if (item != null) {
			for (Item i: item) {
				this.iMapper.findByListAndPerson(l, p); 
			}
		}
			
		}
	

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden f�r Eintrag
	 * ***************************************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden f�r Gruppe-Objekte 
	 * 
	 * @author Larisa
	 * ***************************************************************************
	 */

	/**
	 * Erstellen einer Gruppe mit Name
	 */
	public Team createTeam(String name) throws IllegalArgumentException {
		Team t = new Team();
		t.setName(name);

		// Setzen einer vorläufigen Gruppe-Id, welche nach Kommunikation mit DB auf den
		// nächsthhöheren Wert gesetzt wird.
		t.setId(1);

		t = this.tMapper.insert(t);

		//Membership der Person muss erstellt werden
		Membership m = new Membership();
		m.setPersonId(getCurrentPerson().getId());
		m.setTeamId(t.getId());
		m.setId(1);
		
		this.mMapper.insert(m);

		// Speichern des Gruppe-Objekts in der DB.
		return t;

	}

	/**
	 * Auslesen einer Gruppe anhand seiner Gruppe-Id.
	 */
	
	public Team getTeamById(int id) throws IllegalArgumentException {
		return this.tMapper.findByKey(id);
	}

	/**
	 * Auslesen aller Gruppen.
	 */
	
	public Vector<Team> getAllTeams() throws IllegalArgumentException {
		return this.tMapper.findAll();
	}

	/**
	 * Speichern einer Gruppe.
	 */
	
	public void save(Team t) throws IllegalArgumentException {
		tMapper.update(t);
	}

<<<<<<< HEAD
	// Auslesen aller Personen einer Gruppe.
	public Vector<Person> getAllPersonsOf(int teamId) throws IllegalArgumentException {
		return this.mMapper.findByMember(teamId);
=======
	/**
	 * Auslesen aller Personen einer Gruppe.
	 */
	
	public Vector<Person> getAllPersonsOf(Team t) throws IllegalArgumentException {
		return this.mMapper.findByMember(t.getId());
>>>>>>> refs/heads/Larisa
	}

	/**
	 * Auslesen aller Listen einer Gruppe.
	 */
	
	public Vector<ShoppingList> getAllListsOf(Team t) throws IllegalArgumentException {
		return this.lMapper.findByTeam(t.getId());
	}

	/**
	 * L�schen einer Gruppe.
	 */

	public void delete(Team t) throws IllegalArgumentException {
		/**
		 * Zun�chst werden alle Anwender und Einkaufslisten der Gruppe aus der Datenbank
		 * entfernt.
		 */

		Vector<Membership> membership = this.mMapper.findByTeam(t.getId());

		if (membership != null) {
			for (Membership m : membership) {
				this.mMapper.delete(m.getPersonId(), m.getTeamId());
			}
		}

		Vector<ShoppingList> lists = this.getAllListsOf(t);

		if (lists != null) {
			for (ShoppingList l : lists) {
				this.lMapper.delete(l);
			}
		}
		/**
		 * Anschlie�end die Gruppe entfernen
		 */
		this.tMapper.delete(t);

	}

	public Vector<Team> getAllTeamsByPerson() {
		Vector<Team> result = new Vector<Team>();
		result = this.tMapper.getTeamsOf(getCurrentPerson());
		return result;
	}
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden f�r Gruppe-Objekte
	 * ***************************************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden f�r Arikel-Objekte 
	 * 
	 * @author Larisa
	 * ***************************************************************************
	 */

	public Article createArticle(String name) throws IllegalArgumentException {
		Article a = new Article();
		a.setName(name);

		/**
		 * Setzen einer vorläufigen Artikel-Id, welche nach Kommunikation mit DB auf
		 * den nächsthhöheren Wert gesetzt wird.
		 */
		
		a.setId(1);

		/** 
		 * Speichern des Artikel-Objekts in der Datenbank
		 */
		return this.aMapper.insert(a);

	}

	/** 
	 * Auslesen eines Artikels anhand seiner Id
	 */
	public Article getArticleById(int id) throws IllegalArgumentException {
		return this.aMapper.findByKey(id);

	}

	/**
	 * Auslesen eines Artikels anhand seinem Namen
	 */
	
	public Vector<Article> getArticleByName(Article article) throws IllegalArgumentException {
		return this.aMapper.findByName(article);
	}

	/**
	 * Auslesen aller Artikel
	 */
	
	public Vector<Article> getAllArticles() throws IllegalArgumentException {
		return this.aMapper.findAll();
	}

	/** 
	 * Speichern eines Artikels
	 */
	
	public void save(Article a) throws IllegalArgumentException {
		aMapper.update(a);
	}

	/**
	 * L�schen eines Articles
	 */
	
	public void delete(Article a) throws IllegalArgumentException {
		this.aMapper.delete(a);
	}
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden f�r Artikel-Objekte
	 * ***************************************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden f�r H�ndler-Objekte @Larisa
	 * ***************************************************************************
	 */

	public Shop createShop(String name, String street, String postalCode, String city) throws IllegalArgumentException {
		Shop s = new Shop();
		s.setCity(city);
		s.setStreet(street);
		s.setPostalCode(postalCode);
		s.setName(name);

		/**
		 * Setzen einer vorläufigen H�ndler-Id, welche nach Kommunikation mit DB auf den
		 * nächsthhöheren Wert gesetzt wird.
		 *
		 */
		s.setId(1);

		// Objekt in der DB speichern.
		return this.sMapper.insert(s);

	}

	/**
	 * Auslesen einer H�ndler anhand seiner H�ndler-Id.
	 */
	public Shop getShopById(int id) throws IllegalArgumentException {
		return this.sMapper.findByKey(id);
	}

	/**
	 * Auslesen aller H�ndler.
	 */
	public Vector<Shop> getAllShops() throws IllegalArgumentException {
		return this.sMapper.findAll();
	}

	/**
	 * Speichern eines H�ndlers.
	 */
	public void save(Shop s) throws IllegalArgumentException {
		sMapper.update(s);
	}

	/**
	 * L�schen eines H�ndlers.
	 */

	public void delete(Shop s) throws IllegalArgumentException {

		this.sMapper.delete(s);

	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden f�r H�ndler-Objekte
	 * ***************************************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden f�r Ma�einheit-Objekte @Larisa
	 * ***************************************************************************
	 */

	public UnitOfMeasure createUnitOfMeasure(String unit) throws IllegalArgumentException {
		UnitOfMeasure u = new UnitOfMeasure();
		u.setUnit(unit);

		/**
		 * Setzen einer vorläufigen UnitOfMeasure-Id, welche nach Kommunikation mit DB
		 * auf den nächsthhöheren Wert gesetzt wird.
		 */

		u.setId(1);

		// Objekt in der DB speichern.
		return this.uMapper.insert(u);

	}
	/**
	 * Speichern einer Ma�einheit.
	 */

	public void save(UnitOfMeasure u) throws IllegalArgumentException {
		uMapper.update(u);
	}

	@Override
	public Vector<UnitOfMeasure> getAllUnits() {
		return uMapper.findAll();
	}
	
	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden f�r Ma�einheit-Objekte
	 * ***************************************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Beginn: Methoden f�r Zust�ndigkeits-Objekte @author IlonaBrinkmann
	 * ***************************************************************************
	 */

	/*
	 * Zust�ndigkeit erstellen
	 */

	public Responsibility createResponsibility(Person p, Shop s, Item i) throws IllegalArgumentException {
		Responsibility r = new Responsibility();
		r.setPersonId(p.getId());
		r.setShopId(s.getId());
		r.setItemId(i.getId());
		return this.rMapper.insert(r);

	}

	/*
	 * Zust�ndigkeit anhand der Id finden
	 */
	public Responsibility getResponsibilityById(int id) throws IllegalArgumentException {
		return this.rMapper.findByKey(id);
	}

	/*
	 * eine Zust�ndigkeit �ndern
	 */
	public void update(Responsibility r) throws IllegalArgumentException {
		rMapper.update(r);
	}

	/*
	 * eine Zust�ndigkeit l�schen
	 */
	public void delete(Responsibility r) throws IllegalArgumentException {
		this.rMapper.delete(r);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden f�r Zust�ndigkeits-Objekte
	 * ***************************************************************************
	 */

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Begin: Methoden f�r Gruppenmitgliedschaft-Objekte @author
	 * IlonaBrinkmann
	 * ***************************************************************************
	 */

	/*
	 * Gruppenmitgliedschaft anhand der Id finden
	 */
	public Membership getMembershipById(int id) throws IllegalArgumentException {
		return this.mMapper.findByKey(id);
	}

	/*
	 * alle Gruppen einer Person aufzeigen
	 */
	public Vector<Membership> getAllMembershipOfPerson(Person p) throws IllegalArgumentException {
		return this.mMapper.getAllMembershipsOf(p);

	}

	/*
	 * eine Gruppenmitgliedschaft �ndern
	 */
	public void update(Membership m) throws IllegalArgumentException {
		mMapper.update(m);
	}

	/*
	 * ***************************************************************************
	 * ABSCHNITT, Ende: Methoden f�r Gruppenmitgliedschaft-Objekte
	 * ***************************************************************************
	 */

	// @Override
	// public Person createPerson(String first, String last) throws
	// IllegalArgumentException {
	// TODO Auto-generated method stub
	// return null;
	// }

	@Override
	public void delete(Person p) throws IllegalArgumentException {
		p.setFirstName("---");
		p.setLastName("---");
		p.setEmail("---");
		pMapper.update(p);
	}

	@Override
	public Membership createMembership(int personId, int teamId) throws IllegalArgumentException {
		System.out.println("createMembership");

		Membership m = new Membership();
		m.setPersonId(personId);
		m.setTeamId(teamId);

		return this.mMapper.insert(m);
	}

	@Override
	public void setTeam(Team t) throws IllegalArgumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int personId, int teamId) throws IllegalArgumentException {
		this.mMapper.delete(personId, teamId);
	}

	/**
	 * ################################### #####Methoden des Report Generators#####
	 * ################################### @author Larisa, Ilona
	 **/

	@Override
	public Vector<ReportObject> getItemsByTeamWithTime(Team t, Timestamp firstDate, Timestamp lastDate) {
		Vector<ReportObject> result = this.reportMapper.createTeamTimeReport(t.getId(), firstDate, lastDate);
		return result;
	}

	@Override
	public Vector<ReportObject> getItemsbyTeamAndShop(Shop s, Team t) throws IllegalArgumentException {
		Vector<ReportObject> result = this.reportMapper.createTeamShopReport(t.getId(), s.getId());
		return result;
	}

	@Override
	public Vector<ReportObject> getItemsByTeamAndShopWithTime(Shop s, Team t, Timestamp firstDate, Timestamp lastDate) {
		Vector<ReportObject> result = this.reportMapper.createTeamTimeShopReport(t.getId(), firstDate, lastDate,
				s.getId());
		return result;
	}
}
