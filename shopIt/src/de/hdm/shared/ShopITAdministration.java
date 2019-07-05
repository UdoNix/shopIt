package de.hdm.shared;

import java.sql.Timestamp;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.shared.bo.Article;
import de.hdm.shared.bo.Item;
import de.hdm.shared.bo.Membership;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.ReportObject;
import de.hdm.shared.bo.Responsibility;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.ShoppingList;
import de.hdm.shared.bo.Team;
import de.hdm.shared.bo.UnitOfMeasure;

/**
 * Synchrone Schnittstelle f�r eine RPC-f�hige Klasse zur Verwaltung.
 *
 */
@RemoteServiceRelativePath("shop")
public interface ShopITAdministration extends RemoteService {

	/**
	 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT
	 * RPC zus�tzlich zum No Argument Constructor der implementierenden Klasse
	 * {@link EditorImpl} notwendig. Bitte diese Methode direkt nach der
	 * Instantiierung aufrufen.
	 */
	
	public void init() throws IllegalArgumentException;

	/**
	 * Einen Kunden anlegen.
	 * 
	 * @param first
	 * @param last
	 * @param email
	 * @return
	 * @throws IllegalArgumentException
	 */

	public Person createPerson(String first, String last, String email) throws IllegalArgumentException;

	/**
	 * Auslesen eines Anwenders anhand seiner Id.
	 * 
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Person getPersonById(int id) throws IllegalArgumentException;

	/**
	 * Auslesen eines Anwenders anhand seines Namen.
	 * 
	 * @param person
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Vector<Person> getPersonByName(Person person) throws IllegalArgumentException;

	/**
	 * Auslesen eines Anwenders anhand seiner Email.
	 * 
	 * @param email
	 * @return
	 * @throws Exception
	 */
	
	public Person getPersonByEmail(String email) throws Exception;
	
	public Person getCurrentPerson();

	/**
	 * Auslesen aller Anwender.
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Vector<Person> getAllPersons() throws IllegalArgumentException;

	/**
	 * Speichern eines Anwenders.
	 * 
	 * @param p
	 * @throws IllegalArgumentException
	 */
	
	public void save(Person p) throws IllegalArgumentException;

	/**
	 * Löschen eines Anwenders.
	 * 
	 * @param p
	 * @throws IllegalArgumentException
	 */
	
	public void delete(Person p) throws IllegalArgumentException;

	/**
	 * Erstellen einer neuen Liste.
	 * 
	 * @param t
	 * @param name
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public ShoppingList createListFor(Team t, String name) throws IllegalArgumentException;

	/**
	 * Liste anhand Id finden.
	 * 
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public ShoppingList getListById(int id) throws IllegalArgumentException;

	/**
	 * Einträge einer Liste aufzeigen.
	 * 
	 * @param l
	 * @param p
	 * @param s
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Vector<Item> getAllItemsOfList(ShoppingList l, Person p, Shop s) throws IllegalArgumentException;
	
	/** 
	 * Aktualisierung der Liste
	 * 
	 * 
	 * @param l
	 * @throws IllegalArgumentException
 	*/
	
	public void update(ShoppingList l) throws IllegalArgumentException;
	
	/**
	 * Liste löschen.
	 * 
	 * @param l
	 * @throws IllegalArgumentException
	 */
	
	public void delete(ShoppingList l)throws IllegalArgumentException;
	
	/**
	 * Eintrag erstellen.
	 * 
	 * @param listId
	 * @param teamId
	 * @param count
	 * @param unitId
	 * @param articleId
	 * @param personId
	 * @param shopId
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Item createItem(int listId, int teamId, float count, int unitId, int articleId, int personId, int shopId) throws IllegalArgumentException;
	
	/**
	 * Zuständigkeit zum Eintrag hinzufügen
	 * 
	 * @param r
	 * @param i
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Item addResponsibilityToItem(Responsibility r, Item i)throws IllegalArgumentException;
	
	/**
	 * Eintrag anhand der Id finden.
	 * 
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Item getItemById(int id)throws IllegalArgumentException;
	
	/**
	 * alle Einträge aufzeigen.
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Vector<Item> getAllItems()throws IllegalArgumentException;
		
	/**
	 * Eintrag ändern.
	 * 
	 * @param i
	 * @throws IllegalArgumentException
	 */
	
	public void update(Item i)throws IllegalArgumentException;
	
	/**
	 * einen Eintrag löschen.
	 * 
	 * @param i
	 * @throws IllegalArgumentException
	 */
	
	public void delete(Item i) throws IllegalArgumentException;

	/**
	 * Eine Gruppe mit Name, Person erstellen.
	 * 
	 * @param name
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Team createTeam(String name) throws IllegalArgumentException;

	/**
	 * Auslesen einer Gruppe anhand seiner Gruppe-Id
	 * 
	 * Eine Gruppe mit Name, Person erstellen.
	 * 
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Team getTeamById(int id) throws IllegalArgumentException;

	/**
	 * Auslesen aller Gruppen.
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Vector<Team> getAllTeams() throws IllegalArgumentException;

	/**
	 * Speichern einer Gruppe.
	 * 
	 * @param t
	 * @throws IllegalArgumentException
	 */
	public void save(Team t) throws IllegalArgumentException;
	
	/**
	 *  Herausgabe aller Personen 
	 * 
	 * @param teamId
	 * @return
	 */

	Vector<Person> getAllPersonsOf(int teamId);

	/**
	 * Auslesen aller Listen einer Gruppe.
	 * 
	 * @param t
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<ShoppingList> getAllListsOf(Team t) throws IllegalArgumentException;

	/**
	 * Löschen einer Gruppe.
	 * 
	 * @param t
	 * @throws IllegalArgumentException
	 */
	public void delete(Team t) throws IllegalArgumentException;

	/**
	 * Erstellen eines Artikels.
	 * 
	 * @param name
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Article createArticle(String name) throws IllegalArgumentException;

	/**
	 * Auslesen eines Artikels anhand seiner Id
	 * 
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Article getArticleById(int id) throws IllegalArgumentException;

	/**
	 * Auslesen eines Artikels anhand seinem Namen
	 * 
	 * @param article
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Article> getArticleByName(Article article) throws IllegalArgumentException;

	/**
	 *  Auslesen aller Artikel
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Article> getAllArticles() throws IllegalArgumentException;

	/**
	 * Speichern eines Artikels
	 * 
	 * @param a
	 * @throws IllegalArgumentException
	 */
	public void save(Article a) throws IllegalArgumentException;

	/**
	 * Löschen eines Artikels
	 * 
	 * @param a
	 * @throws IllegalArgumentException
	 */
	public void delete(Article a) throws IllegalArgumentException;

	/**
	 * Händler erstellen.
	 * 
	 * @param name
	 * @param street
	 * @param postalCode
	 * @param city
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Shop createShop(String name, String street, String postalCode, String city) throws IllegalArgumentException;

	/**
	 * Auslesen eines Händerls anhand seiner Händler-Id.
	 * 
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Shop getShopById(int id) throws IllegalArgumentException;

	/**
	 *  Auslesen aller Händler.
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Vector<Shop> getAllShops() throws IllegalArgumentException;

	/**
	 * Speichern eines H�ndlers.
	 * 
	 * @param s
	 * @throws IllegalArgumentException
	 */
	public void save(Shop s) throws IllegalArgumentException;

	/**
	 * Löschen eines Händlers.
	 * 
	 * @param s
	 * @throws IllegalArgumentException
	 */
	public void delete(Shop s) throws IllegalArgumentException;

	/**
	 * Mengeneinheit erstellen.
	 * 
	 * @param unit
	 * @return
	 * @throws IllegalArgumentException
	 */
	public UnitOfMeasure createUnitOfMeasure(String unit) throws IllegalArgumentException;

	/**
	 *  Speichern von Mengeneinheit
	 * 
	 * @param u
	 * @throws IllegalArgumentException
	 */
	public void save(UnitOfMeasure u) throws IllegalArgumentException;

	/**
	 *  Zust�ndigkeit erstellen.
	 * @param p
	 * @param s
	 * @param i
	 * @return
	 * @throws IllegalArgumentException
	 */
	/**
	 * 
	 * @param p
	 * @param s
	 * @param i
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Responsibility createResponsibility(Person p, Shop s, Item i) throws IllegalArgumentException;

	/**
	 *  Zust�ndigkeit anhand der Id finden.
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	public Responsibility getResponsibilityById(int id) throws IllegalArgumentException;

	/**
	 *  eine Zust�ndigkeit �ndern.
	 * @param r
	 * @throws IllegalArgumentException
	 */
	public void update(Responsibility r) throws IllegalArgumentException;

	/**
	 *  eine Zust�ndigkeit l�schen.
	 * @param r
	 * @throws IllegalArgumentException
	 */
	public void delete(Responsibility r) throws IllegalArgumentException;

	/**
	 *  Gruppenmitgliedschaft erstellen.
	 * @param personId
	 * @param teamId
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Membership createMembership(int personId, int teamId) throws IllegalArgumentException;

	/**
	 *  Gruppenmitgliedschaft anhand der Id finden.
	 * @param id
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Membership getMembershipById(int id) throws IllegalArgumentException;

	/**
	 *  alle Gruppen einer Person aufzeigen.
	 * @param p
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Vector<Membership> getAllMembershipOfPerson(Person p) throws IllegalArgumentException;

	/**
	 *  eine Gruppenmitgliedschaft ändern.
	 * @param m
	 * @throws IllegalArgumentException
	 */
	public void update(Membership m) throws IllegalArgumentException;

	/**
	 *  eine Gruppenmitgliedschaft löschen.
	 *  
	 * @param personId
	 * @param teamId
	 * @throws IllegalArgumentException
	 */
	
	public void delete(int personId, int teamId) throws IllegalArgumentException;

	/**
	 *  Setzen des zugehörigen Team-Objekts.
	 *  
	 * @param t
	 * @throws IllegalArgumentException
	 */
	
	public void setTeam(Team t) throws IllegalArgumentException;

	/**
	 *  Auslesen aller eingekauften Artikeln von einem Händler
	 *  
	 * @param shop
	 * @param team
	 * @return
	 * @throws IllegalArgumentException
	 */
	
	public Vector<ReportObject> getItemsbyTeamAndShop(Shop shop, Team team) throws IllegalArgumentException;

	/**
	 *  Auslesen aller Einträge eines Teams mit Zeitangabe.
	 * @param t
	 * @param firstDate
	 * @param lastDate
	 * @return
	 */
	public Vector<ReportObject> getItemsByTeamWithTime(Team t, Timestamp firstDate, Timestamp lastDate);

	/**
	 *  Auslesen aller Einträge eines Teams, gefiltert nach Shop mit Zeitangabe.
	 *  
	 * @param s
	 * @param t
	 * @param firstDate
	 * @param lastDate
	 * @return
	 */
	
	public Vector<ReportObject> getItemsByTeamAndShopWithTime(Shop s, Team t, Timestamp firstDate, Timestamp lastDate);

	/**
	 * 
	 * @return
	 */
	
	public Vector<Team> getAllTeamsByPerson();
	
	/**
	 * 
	 * @return
	 */
	
	public Vector<UnitOfMeasure> getAllUnits();

}
