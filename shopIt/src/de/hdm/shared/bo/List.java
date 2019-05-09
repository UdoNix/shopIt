package de.hdm.shared.bo;
import java.sql.Date;

public class List extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private Item item;
	
	//Name der Einkaufsliste.
	private String name = "";
	
	//Änderungsdatum der letzten Veränderung der Liste.
	private Date modificationDate;

	//Auslesen des Listennamen.
	public String getName() {
		return name;
	}
	//Setzen des Listennamen.
	public void setName(String name) {
		this.name = name;
	}
	
	//Auslesen des Änderungsdatums.
	public Date getModificationDate() {
		return modificationDate;
	}
	//Setzen des Änderungsdatums.
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
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
