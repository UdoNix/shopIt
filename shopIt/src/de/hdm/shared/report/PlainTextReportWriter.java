package de.hdm.shared.report;
import java.util.Vector;

/**Ein ReportWriter, der Reports mithilfe von Plain Text formatiert.
 * Das Ergebnis wird in der Variable reportText abgelegt. 
 * Nach Aufruf der Prozessierungsmethode kann es mit getReportText() ausgelesen werden.
 * @author InesWerner
 *
 */
public class PlainTextReportWriter {
	
	//Variable für das Ergebnis einer Umwandlung.
	private String reportText = "";
	
	//Variable reportText wird zurückgesetzt.
	public void resetReportText(){
		this.reportText = "";
	}
	
	//Header-Text wird erstellt.
	public String getHeader(){
		return "";
	}
	
	//Trailer-Text wird erstellt. Mit der Trennlinie wird Report-Ende markiert.
	public String getTrailer(){
		return "________________________________";
	}
	
	//Ablage des übergebenen Reports im Zielformat.
	/**
	 * Auslese erfolgt durch getReportText().
	 * 
	 * 
	 * 
	 * 	//Löschen des Ergebnisses einer vorhergehenden Prozessierung.
		this.resetReportText();
		
	//Buffer, in den Ergebnisse reingeschrieben werden.
		StringBuffer result = new StringBuffer();
		
	//Bestandteile 	des Reports werden ausgelesen und in Textform übersetzt.

	 * 
	 */
	
	
	

		
}