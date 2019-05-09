package de.hdm.shared.bo;
public class Responsibility {
//Die Klasse Responsibility/Verantwortlichkeit dient dazu, festzulegen, welcher User für welchen Eintrag zuständig ist.

	private Person person;
	
	private Item item;
	
	private Salesman salesman;

	//Auslesen des Anwenders.
	public Person getPerson() {
		return person;
	}
	//Setzen des Anwenders.
	public void setPerson(Person person) {
		this.person = person;
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

