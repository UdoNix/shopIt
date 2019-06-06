package de.hdm.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.shared.bo.Group;

public interface ShopItAdministrationAsync {

	void getAllMembersOfGroup(String text, ShowGroupCallback showGroupCallback);

	void findByMembers(Group g, AsyncCallback<Vector<Group>> callback);

	void getPersonByEmail(String email, GetPersonCallback getPersonCallback);
	
	//Import der Methoden aus dem Klassendiagramm

}
