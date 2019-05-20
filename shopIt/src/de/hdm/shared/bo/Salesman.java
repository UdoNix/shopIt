package de.hdm.shared.bo;
public class Salesman extends BusinessObject{

	private static final long serialVersionUID = 1L;

	//Name des Händlers.
	private String name = "";
	
	//Straßenname des Händlers.
	private String street = "";
	
	//Postleitzahl des Händlers.
	private String postalCode = "";
	
	//Stadt des Händlers.
	private String city = "";
	

	//Auslesen des Händlernamen.
	public String getName() {
		return name;
	}
	//Setzen des Händlernamen.
	public void setName(String name) {
		this.name = name;
	}
	//Auslesen des Straßennamen des Händlers.
	public String getStreet() {
		return street;
	}
	//Setzen des Straßennamen des Händlers.
	public void setStreet(String street) {
		this.street = street;
	}

	//Auslesen der Postleitzahl des Händlers.
	public String getPostalCode() {
		return postalCode;
	}
	//Setzen der Postleitzahl des Händlers.
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	//Auslesen, in welcher Stadt der Händler ist.
	public String getCity() {
		return city;
	}
	//Festlegen, in welcher Stadt der Händler ist.
	public void setCity(String city) {
		this.city = city;
	}

}

