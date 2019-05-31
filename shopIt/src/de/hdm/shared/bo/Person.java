package de.hdm.shared.bo;
public class Person extends BusinessObject{
	
	private static final long serialVersionUID = 1L;

	//Vorname des Anwenders.
	private String firstName = "";
	
	//Nachname des Anwenders.
	private String lastName = "";
	
	//Email-Adresse des Anwenders.
	private String email = "";

	//Auslesen Vorname des Anwenders.
	public String getFirstName() {
		return firstName;
	}
	//Setzen des Vornamen des Anwenders.
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	//Auslesen des Nachnamen des Anwenders.
	public String getLastName() {
		return lastName;
	}
	
	//Setzen des Nachnamen des Anwenders.
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	//Auslesen der Email-Adresse des Anwenders.
	public String getEmail() {
		return email;
	}

	//Setzen der Email-Adresse des Anwenders.
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}

