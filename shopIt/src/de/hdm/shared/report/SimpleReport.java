package de.hdm.shared.report;
import java.io.Serializable;
import java.util.Vector;

/**
 * SimpleReport, der neben Informationen der Superklasse Report eine Tabelle mit Positionsdaten aufweist.
 * Hilfsklassen: Row und Column
 * @author InesWerner
 *
 */

public class SimpleReport implements Serializable{

	private static final long serialVersionUID = 1L;

	//Tabelle mit Positionsdaten. Die Tabelle wird im Vector table abgelegt.
	private Vector<Row> table = new Vector<Row>();
	
	//Zeile hinzufügen.
	public void addRow(Row r){
		this.table.addElement(r);
	}
	
	//Zeile entfernen.
	public void removeRow(Row r){
		this.table.removeElement(r);
	}
	
	//Auslesen der Positionsdaten
	public Vector<Row> getRows(){
		return this.table;
	}
	


}
