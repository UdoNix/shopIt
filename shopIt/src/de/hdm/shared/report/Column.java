package de.hdm.shared.report;

import java.io.Serializable; 

public class Column implements Serializable {
public static final long serialVersionUID = 1L;
	
	/**
	 * Value ist der Wert eines Spaltenobjekts und entspricht dem Zelleneintrag einer Tabelle.
	 * Es handelt sich um einen textuellen Wert.
	 * 
	 * @author Larisa
	 */

	public String value = "";
	
	/**
	 * Serialisierbare Klassen,die mittels GWT-RPC transportiert werden sollen, 
	 * m�ssen einen No-Argument-Konstruktor besitzen. Hier wird diesen Konstruktor
	 * expliziert implementiert.
	 */
	
	public Column() {
		
	}
	
	/**
	 * Konstruktor f�r die Wertangabe. 
	 */
	
	public Column(String s) {
		this.value = s; 
	}
	
	public String getValue() {
		return value; 
	}
	
	public void setValue(String value) {
		this.value = value; 
	}
	
	public String toString() {
		return this.value; 
	}


}
