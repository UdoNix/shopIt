package de.hdm.shared;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.shared.bo.Article;
import de.hdm.shared.bo.Item;
import de.hdm.shared.bo.ShoppingList;
import de.hdm.shared.bo.Membership;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.ReportObject;
import de.hdm.shared.bo.Responsibility;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;
import de.hdm.shared.bo.UnitOfMeasure;


public interface ShopITAdministrationAsync {

	void addResponsibilityToItem(Responsibility r, Item i, AsyncCallback<Item> callback);

	void createArticle(String name, AsyncCallback<Article> callback);

	void createItem(int listId, int count, int articleId, int personId, int shopId, AsyncCallback<Item> callback);

	void createListFor(Team t, String name, AsyncCallback<ShoppingList> callback);

	void createMembership(int personId, int teamId, AsyncCallback<Membership> callback);

	void createPerson(String first, String last, String email, AsyncCallback<Person> callback);

	void createResponsibility(Person p, Shop s, Item i,  AsyncCallback<Responsibility> callback);

	void createShop(String name, String street, String postalCode, String city, AsyncCallback<Shop> callback);

	void createTeam(String name, AsyncCallback<Team> callback);

	void createUnitOfMeasure(float quantity, String unit, AsyncCallback<UnitOfMeasure> callback);

	void delete(Article a, AsyncCallback<Void> callback);

	void delete(Responsibility r, AsyncCallback<Void> callback);

	void delete(Person p, AsyncCallback<Void> callback);

	void delete(Item i, AsyncCallback<Void> callback);

	void delete(Shop s, AsyncCallback<Void> callback);

	void delete(ShoppingList l, AsyncCallback<Void> callback);

	void delete(int personId, int teamId, AsyncCallback<Void> callback);

	void delete(Team t, AsyncCallback<Void> callback);

	void getAllArticles(AsyncCallback<Vector<Article>> callback);

	void getAllItems(AsyncCallback<Vector<Item>> callback);

	void getAllItemsOfList(ShoppingList l, AsyncCallback<Vector<Item>> callback);

	void getAllListsOf(Team t, AsyncCallback<Vector<ShoppingList>> callback);

	void getAllMembershipOfPerson(Person p, AsyncCallback<Vector<Membership>> callback);

	void getAllPersons(AsyncCallback<Vector<Person>> callback);

	void getAllPersonsOf(Team t, AsyncCallback<Vector<Person>> callback);

	void getAllResponsibilityOfPerson(Person p, AsyncCallback<Vector<Responsibility>> callback);

	void getAllShops(AsyncCallback<Vector<Shop>> callback);

	void getAllTeams(AsyncCallback<Vector<Team>> callback);

	void getArticleById(int id, AsyncCallback<Article> callback);

	void getArticleByName(Article article, AsyncCallback<Vector<Article>> callback);

	void getItemById(int id, AsyncCallback<Item> callback);

	void getListById(int id, AsyncCallback<ShoppingList> callback);

	void getMembershipById(int id, AsyncCallback<Membership> callback);

	void getPersonByEmail(String email, AsyncCallback<Person> callback);

	void getPersonById(int id, AsyncCallback<Person> callback);

	void getPersonByName(Person person, AsyncCallback<Vector<Person>> callback);

	void getResponsibilityById(int id, AsyncCallback<Responsibility> callback);

	void getShopById(int id, AsyncCallback<Shop> callback);

	void getTeamById(int id, AsyncCallback<Team> callback);

	void init(AsyncCallback<Void> callback);

	void save(Team t, AsyncCallback<Void> callback);

	void save(Article a, AsyncCallback<Void> callback);

	void save(UnitOfMeasure u, AsyncCallback<Void> callback);

	void save(Shop s, AsyncCallback<Void> callback);

	void save(Person p, AsyncCallback<Void> callback);

	void update(Responsibility r, AsyncCallback<Void> callback);

	void update(Membership m, AsyncCallback<Void> callback);

	void update(Item i, AsyncCallback<Void> callback);

	void update(ShoppingList l, AsyncCallback<Void> callback);

	void setTeam(Team t, AsyncCallback<Void> callback);
	
	void getItemsbyTeamAndShop(Shop shop, Team team, AsyncCallback<Vector<ReportObject>> callback);

	void getItemsByTeamAndShopWithTime(Shop s, Team t, Timestamp firstDate, Timestamp lastDate, AsyncCallback<Vector<ReportObject>> callback);

	void getItemsByTeamWithTime(Team t, Timestamp firstDate, Timestamp lastDate, AsyncCallback<Vector<ReportObject>> callback);
	
	void getAllTeamsByPerson(Person p, AsyncCallback<Vector<Team>> callback);
	

}
