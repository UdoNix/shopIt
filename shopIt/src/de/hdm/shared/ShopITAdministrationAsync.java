package de.hdm.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.shared.bo.Team;
import de.hdm.shared.bo.List;
import de.hdm.shared.bo.Person;

public interface ShopITAdministrationAsync {

	void createGroup(String name, Person p, List l, AsyncCallback<Team> callback);

	void createPerson(String first, String last, AsyncCallback<Person> callback);

	void getListsOf(Team g, AsyncCallback<Vector<List>> callback);

	void getPersonsOf(Team g, AsyncCallback<Vector<Person>> callback);

	void init(AsyncCallback<Void> callback);
	
}
