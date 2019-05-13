package de.hdm.shared.bo;
import java.sql.Date;

public class List extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private Item item;
	
	//Name der Einkaufsliste.
	private String name = "";
	
	//Änderungsdatum der letzten Veränderung der Liste.
	private Date creationDate;

	//Auslesen des Listennamen.
	public String getName() {
		return name;
	}
	//Setzen des Listennamen.
	public void setName(String name) {
		this.name = name;
	}
	
	//Auslesen des Erstellungsdatum.
	public Date getCreationDate() {
		return creationDate;
	}
	//Setzen des Erstellungsdatum.
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	//Auslesen des Listeneintrags.
	public Item getItem() {
		return item;
	}
	//Setzen eines Listeneintrags.
	public void setItem(Item item) {
		this.item = item;
	}


}
