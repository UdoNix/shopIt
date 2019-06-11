package de.hdm.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.shared.bo.Team;

public interface EditorServiceAsync {

	void getAllMembersOfGroup(String text, ShowGroupCallback showGroupCallback);

	void getAllMembersOf(Team g, AsyncCallback<Vector<Team>> callback);

	void getPersonByMail(String email, GetPersonCallback getPersonCallback);
	
	//Import der Methoden aus dem Klassendiagramm

}
