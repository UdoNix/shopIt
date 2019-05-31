package de.hdm.shared.report;
import java.io.Serializable;
import java.util.Vector;


/**
 * Zeile der Tabelle eines SimpleReports-Objekts.
 * @author InesWerner
 *
 */

public class Row implements Serializable{

	private static final long serialVersionUID = 1L;

	//Speicherplatz für die Spalten der Zeile.
	private Vector<Column> columns = new Vector<Column>();
	
	//Spalte wird hinzugefügt.
	public void addColumn (Column c){
		this.columns.addElement(c);
	}
	
	//Spalte wird entfernt.
	public void removeColumn(Column c){
		this.columns.removeElement(c);
	}
	
	//Auslesen aller Spalten.
	public Vector<Column> getColumn(){
		return this.columns;
	}
	
	//Auslesen der Anzahl aller Spalten.
	public int getNumColumns(){
		return this.columns.size();
	}
	
	//Auslesen eines einzelnen Spalten-Objekts.
	public Column getColumnAt(int i){
		return this.columns.elementAt(i);
	}
	



}
