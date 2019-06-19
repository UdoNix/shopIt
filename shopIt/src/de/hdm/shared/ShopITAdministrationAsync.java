package de.hdm.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.shared.bo.Team;
import de.hdm.shared.bo.UnitOfMeasure;
import de.hdm.client.gui.AddPersonToGroupForm.GetPersonCallback;
import de.hdm.shared.bo.Article;
import de.hdm.shared.bo.Item;
import de.hdm.shared.bo.List;
import de.hdm.shared.bo.Membership;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Responsibility;
import de.hdm.shared.bo.Shop;

public interface ShopITAdministrationAsync {

	void addResponsibilityToItem(Responsibility r, Item i, AsyncCallback<Item> callback);

	void createArticle(String name, AsyncCallback<Article> callback);

	void createItem(List l, Article a, AsyncCallback<Item> callback);

	void createListFor(Team t, String name, AsyncCallback<List> callback);

	void createMembership(Person p, Team g, AsyncCallback<Membership> callback);

	void createPerson(String first, String last, AsyncCallback<Person> callback);

	void createResponsibility(Person p, Shop s, AsyncCallback<Responsibility> callback);

	void createShop(String name, String street, String postalCode, String city, AsyncCallback<Shop> callback);

	void createTeam(String name, Person p, AsyncCallback<Team> callback);

	void createUnitOfMeasure(float quantity, String unit, AsyncCallback<UnitOfMeasure> callback);

	void delete(Article a, AsyncCallback<Void> callback);

	void delete(Responsibility r, AsyncCallback<Void> callback);

	void delete(Person p, AsyncCallback<Void> callback);

	void delete(Item i, AsyncCallback<Void> callback);

	void delete(Shop s, AsyncCallback<Void> callback);

	void delete(List l, AsyncCallback<Void> callback);

	void delete(Membership m, AsyncCallback<Void> callback);

	void delete(Team t, AsyncCallback<Void> callback);

	void getAllArticles(AsyncCallback<Vector<Article>> callback);

	void getAllItems(AsyncCallback<Vector<Item>> callback);

	void getAllItemsOfList(List l, AsyncCallback<Vector<Item>> callback);

	void getAllListsOf(Team t, AsyncCallback<Vector<List>> callback);

	void getAllMembershipOfPerson(Person p, AsyncCallback<Vector<Team>> callback);

	void getAllPersons(AsyncCallback<Vector<Person>> callback);

	void getAllPersonsOf(Team t, AsyncCallback<Vector<Person>> callback);

	void getAllResponsibilityOfPerson(Person p, AsyncCallback<Vector<Item>> callback);

	void getAllShops(AsyncCallback<Vector<Shop>> callback);

	void getAllTeams(AsyncCallback<Vector<Team>> callback);

	void getArticleById(int id, AsyncCallback<Article> callback);

	void getArticleByName(Article article, AsyncCallback<Vector<Article>> callback);

	void getItemById(int id, AsyncCallback<Item> callback);

	void getListById(int id, AsyncCallback<List> callback);

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

	void update(List l, AsyncCallback<Void> callback);

	void addPersonToTeam(Person newGroupMember, Team selectedGroup, GetPersonCallback getPersonCallback);
	

}
