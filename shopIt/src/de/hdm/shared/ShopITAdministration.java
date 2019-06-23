package de.hdm.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.shared.bo.*;

/*
 * Synchrone Schnittstelle f�r eine RPC-f�hige Klasse zur Verwaltung.
 *
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
	
	public Person createPerson(String first, String last, String email) throws IllegalArgumentException; 
	
	//Auslesen eines Anwenders anhand seiner Id.
	public Person getPersonById(int id) throws IllegalArgumentException;
	
	//Auslesen eines Anwenders anhand seines Namen.
	public Vector<Person> getPersonByName(Person person) throws IllegalArgumentException;
	
	//Auslesen eines Anwenders anhand seiner Email.
	public Person getPersonByEmail(String email) throws IllegalArgumentException;
	
	//Auslesen aller Anwender.
	public Vector<Person> getAllPersons() throws IllegalArgumentException;
	
	//Speichern eines Anwenders.
	public void save(Person p) throws IllegalArgumentException;
	
	//Löschen eines Anwenders.
	public void delete(Person p) throws IllegalArgumentException;
	
	//Erstellen einer neuen Liste.
	public List createListFor(Team t, String name) throws IllegalArgumentException;
	
	//Liste anhand Id finden.
	public List getListById(int id) throws IllegalArgumentException;
	
	//Einträge einer Liste aufzeigen.
	public Vector<Item> getAllItemsOfList(List l)throws IllegalArgumentException;
	
	public void update(List l) throws IllegalArgumentException;
	
	//Liste löschen.
	public void delete(List l)throws IllegalArgumentException;
	
	//Eintrag erstellen.
	public Item createItem(List l, Article a)throws IllegalArgumentException;
	
	//Zuständigkeit zum Eintrag hinzufügen
	public Item addResponsibilityToItem(Responsibility r, Item i)throws IllegalArgumentException;
	
	//Eintrag anhand der Id finden.
	public Item getItemById(int id)throws IllegalArgumentException;
	
	//alle Einträge aufzeigen.
	public Vector<Item> getAllItems()throws IllegalArgumentException;
	
	//Eintrag ändern.
	public void update(Item i)throws IllegalArgumentException;
	
	//einen Eintrag löschen.
	public void delete(Item i) throws IllegalArgumentException;
	
	//Eine Gruppe mit Name, Person erstellen.
	public Team createTeam(String name, Person p) throws IllegalArgumentException;
	
	//Auslesen einer Gruppe anhand seiner Gruppe-Id.
	public Team getTeamById(int id) throws IllegalArgumentException;
	
	//Auslesen aller Gruppen.
	public Vector<Team> getAllTeams() throws IllegalArgumentException;
	
	//Speichern einer Gruppe.
	public void save(Team t) throws IllegalArgumentException;
	
	//Auslesen aller Personen der Gruppe
	public Vector<Person> getAllPersonsOf(Team t) throws IllegalArgumentException;
	
	//Auslesen aller Listen einer Gruppe.
	public Vector<List> getAllListsOf(Team t) throws IllegalArgumentException;
	
	//L�schen einer Gruppe.
	public void delete(Team t) throws IllegalArgumentException;
	
	//Erstellen eines Artikels.
	public Article createArticle(String name) throws IllegalArgumentException;
	
	//Auslesen eines Artikels anhand seiner Id
	public Article getArticleById(int id) throws IllegalArgumentException;
	
	public Article getArticleByName(String article) throws IllegalArgumentException;
	
	//Auslesen aller Artikel 
	public Vector<Article> getAllArticles() throws IllegalArgumentException;
	
	//Speichern eines Artikels 
	public void save(Article a) throws IllegalArgumentException;
	
	//L�schen eines Articles 
	public void delete(Article a) throws IllegalArgumentException;
	
	//Händler erstellen.
	public Shop createShop(String name, String street, String postalCode, String city) throws IllegalArgumentException;
	
	//Auslesen eines Händerls anhand seiner Händler-Id.
	public Shop getShopById(int id) throws IllegalArgumentException;
	
	//Auslesen aller H�ndler.
	public Vector<Shop> getAllShops() throws IllegalArgumentException;
	
	//Speichern eines H�ndlers.
	public void save(Shop s) throws IllegalArgumentException;
	
	//L�schen eines H�ndlers. 
	public void delete(Shop s) throws IllegalArgumentException;
	
	//Mengeneinheit erstellen.
	public UnitOfMeasure createUnitOfMeasure(float quantity, String unit) throws IllegalArgumentException;
	
	//Speichern einer Ma�einheit. 
	public void save(UnitOfMeasure u) throws IllegalArgumentException;
	
	//Zust�ndigkeit erstellen.
	public Responsibility createResponsibility(Person p, Shop s, Item i) throws IllegalArgumentException;
	
	//Zust�ndigkeit anhand der Id finden.
	public Responsibility getResponsibilityById(int id) throws IllegalArgumentException;
	
	//alle Zust�ndigkeiten einer Person aufzeigen.
	public Vector<Responsibility> getAllResponsibilityOfPerson(Person p) throws IllegalArgumentException;
	
	//eine Zust�ndigkeit �ndern.
	public void update(Responsibility r) throws IllegalArgumentException;
	
	//eine Zust�ndigkeit l�schen.
	public void delete(Responsibility r) throws IllegalArgumentException;
	
	//Gruppenmitgliedschaft erstellen.
	public Membership createMembership(int personId, int teamId) throws IllegalArgumentException;
	
	//Gruppenmitgliedschaft anhand der Id finden.
	public Membership getMembershipById(int id) throws IllegalArgumentException;
	
	//alle Gruppen einer Person aufzeigen.
	public Vector<Membership> getAllMembershipOfPerson(Person p) throws IllegalArgumentException;
	
	//eine Gruppenmitgliedschaft �ndern.
	public void update(Membership m) throws IllegalArgumentException;
	
	//eine Gruppenmitgliedschaft l�schen.
	public void delete(Membership m) throws IllegalArgumentException;
	
	//Auslesen aller eingekauften Artikeln von einem H�ndler
	public Vector<Item> getItemsbyTeamAndShop(Shop shop) throws IllegalArgumentException;
	
	//Setzen des zugeh�rigen Team-Objekts. 
	public void setTeam(Team t) throws IllegalArgumentException;
	
	//Auslesen aller Eintr�ge eines Teams mit Zeitangabe.
	public Vector<Item> getItemsByTeamWithTime(Team t);
	
	
	
	
	


}
