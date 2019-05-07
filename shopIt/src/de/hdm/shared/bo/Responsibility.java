package de.hdm.shared.bo;
public class Responsibility {
//Die Klasse Responsibility/Verantwortlichkeit dient dazu, festzulegen, welcher User für welchen Eintrag zuständig ist.

	private User user;
	
	private Item item;
	
	private Salesman salesman;

	//Auslesen des Anwenders.
	public User getUser() {
		return user;
	}
	//Setzen des Anwenders.
	public void setUser(User user) {
		this.user = user;
	}
	//Auslesen des Eintrags.
	public Item getItem() {
		return item;
	}
	//Setzen des Eintrags.
	public void setItem(Item item) {
		this.item = item;
	}
	//Auslesen des Händlers.
	public Salesman getSalesman() {
		return salesman;
	}
	//Setzen des Händlers.
	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}
	

}

