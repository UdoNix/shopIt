package de.hdm.shared.bo;
public class Responsibility {
//Die Klasse Responsibility/Verantwortlichkeit dient dazu, festzulegen, welcher User für welchen Eintrag zuständig ist.

	//Fremdschlüsselbeziehung zur Person.
	private int personId = 0;
	
	//Fremdschlüsselbeziehung zum Eintrag.
	private int itemId = 0;
	
	//Fremdschlüsselbeziehung zum Händler.
	private int salesmanId = 0;

	//Auslesen des Fremdschlüssels zur Person.
	public int getPersonId() {
		return personId;
	}
	//Setzen des Fremdschlüssels zur Person.
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}



	

}

