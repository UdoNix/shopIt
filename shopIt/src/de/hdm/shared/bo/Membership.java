package de.hdm.shared.bo;

public class Membership extends BusinessObject {

	private static final long serialVersionUID = 1L;

	//Fremdschlüsselbeziehung zur Gruppe.
	private int groupId = 0;

	//Fremdschlüsselbeziehung zur Person.
	private int personId = 0;

	
	//Auslesen des Fremdschlüssels zur Gruppe.
	public int getGroupId() {
		return groupId;
	}

	//Setzen des Fremdschlüssels zur Gruppe.
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	//Auslesen des Fremdschlüssels zur Person.
	public int getPersonId() {
		return personId;
	}

	//Setzen des Fremdschlüssels zur Person.
	public void setPersonId(int personId) {
		this.personId = personId;
	}

}
