package de.hdm.shared.bo;

public class Responsibility extends BusinessObject{

	//Die Klasse Responsibility/Verantwortlichkeit dient dazu, festzulegen, welcher User für welchen Eintrag zuständig ist.
	
	private static final long serialVersionUID = 1L;


	//Fremdschlüsselbeziehung zur Person.
	private int personId = 0;
	
	//Fremdschlüsselbeziehung zum Händler.
	private int shopId = 0;
	
	//Fremdschlüsselbeziehung zum Item.
	private int itemId = 0;

	//Auslesen des Fremdschlüssels zur Person.
	public int getPersonId() {
		return personId;
	}
	
	//Setzen des Fremdschlüssels zur Person.
	public void setPersonId(int personId) {
		this.personId = personId;
	}

	//Auslesen des Fremdschlüssels zum Händler.
	public int getShopId() {
		return shopId;
	}
	
	//Setzen des Fremdschlüssels zum Händler.
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}


	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	

}

