package de.hdm.shared.report;
import java.util.*;
/*
 * @author Thies Ilona
 */

public class HTMLReportWriter extends ReportWriter{

	private String reportText ="";
	
	/* 
	 * Zurücksetzen der Variable 
	 */
	public void resetReport() {
		this.reportText = "";
	}
	/*
	 * Umwandeln eines Paragraph-Objekts in HTML
	 */
	public String paragraph2HTML(Paragraph p) {
		if(p instanceof CompositeParagraph) {
			return this.paragraph2HTML((CompositeParagraph) p);
		}
	}
	/*
	 * Umwandeln eines CompositeParagraph-Objekts in HTML
	 */
	public String paragraph2HTML(CompositeParagraph p) {
		StringBuffer result = new StringBuffer();
		
		for (int i = 0; i < p.getNumParagraphs(); i++) {
			result.append("<p>" + p.getParagraphAt(i) + "</p>");
		}
		
		return result.toString();
	}
	/*
	 * Umwandeln eines SimpleParagraph-Objekts in HTML 
	 */
	public String paragraph2HTML(SimpleParagraph p) {
		return "<p>" + p.toString() + "</p>";
	}
	/*
	 * HTML Header Text produzieren
	 */
	public String getHeader() {
		StringBuffer result = new StringBuffer();
		
		result.append("<html><head><title></title></head><body>");
		return result.toString();
	}
	/*
	 * HTML Trailer Text produzieren
	 */
	public String getTrailer() {
		return "</body></html>";
	}
	/*
	 * Prozessieren des übergebenen Reports und Ablage im Zielformat
	 * Auslesen der Ergebnisse durch getReportText()
	 */
	public void process(AllItemsOfPerson r) {
		this.resetReportText();
		//Ergebnisse werden eingetragen
		StringBuffer result = new StringBuffer();
		//einzelne Bestandteile des Reports auslesen und in HTML Form übersezten
		result.append("<H1>" + r.getTitle() + "</H1>");
		result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
	    result.append("<td valign=\"top\"><b>" + paragraph2HTML(r.getHeaderData())
	        + "</b></td>");
	    result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint())
	        + "</td>");
	    result.append("</tr><tr><td></td><td>" + r.getCreated().toString()
	        + "</td></tr></table>");

	    Vector<Row> rows = r.getRows();
	    result.append("<table style=\"width:400px\">");

	    for (int i = 0; i < rows.size(); i++) {
	      Row row = rows.elementAt(i);
	      result.append("<tr>");
	      for (int k = 0; k < row.getNumColumns(); k++) {
	        if (i == 0) {
	          result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnAt(k)
	              + "</td>");
	        }
	        else {
	          if (i > 1) {
	            result.append("<td style=\"border-top:1px solid silver\">"
	                + row.getColumnAt(k) + "</td>");
	          }
	          else {
	            result.append("<td valign=\"top\">" + row.getColumnAt(k) + "</td>");
	          }
	        }
	      }
	      result.append("</tr>");
	    }

	    result.append("</table>");

	    /*
	     * Umwandlung des Arbeitsbuffers in einen Sting und Zuweisung der reportText-Variable
	     * Auslesen des Ergebnisses durch getReportText()
	     */
	    this.reportText = result.toString();
	    
	  
	    
	}  
		/*
	     * Prozessieren des übergebenden Report und Ablage im Zielformat
	     * Auslesen durch getReportText()
	     * r ist der zu prozessierende Report
	     */
	public void process(AllItemsOfAllPersonsReport r) {
		// Zunächst löschen wir das Ergebnis vorhergehender Prozessierungen
		this.resetReportText();
		//Ergebnisse werden in diesen Buffer geschrieben
		StringBuffer result = new StringBuffer();
		//Nun werden alle Bestandteile des Reports ausgelesen und in HTML
		//Form übersetzt
		result.append("<H1>" + r.getTitle() + "</H1>");
	    result.append("<table><tr>");

	    if (r.getHeaderData() != null) {
	      result.append("<td>" + paragraph2HTML(r.getHeaderData()) + "</td>");
	    }

	    result.append("<td>" + paragraph2HTML(r.getImprint()) + "</td>");
	    result.append("</tr><tr><td></td><td>" + r.getCreated().toString()
	        + "</td></tr></table>");
	    //für alle Teilreports von AllItemsOfPerson wird processAllItemsOfPersonReport aufgerufen
	    //Das Ergebnis wird dann in den Buffer hinzugefügt
	    for (int i = 0; i < r.getNumSubReports(); i++) {
	    	//Wenn ein Bestandteil des Reports nicht mehr gilt, sollte hier eine 
	    	// detaillierte Implementierung erfolgen
	    	AllItemsOfPersonReport subReport = (AllItemsOfPersonReport) r.getSubReportAt(i);
	    	
	    	this.process(subReport);
	    	
	    	result.append(this.reportText + "\n");
	    	//nach jeder Änderung eines Teilreport und anschließendem auslesen, sollte die 
	    	//Ergebnisvariable zurückgesetzt werden
	    	this.resetReport();
	    }
	    this.reportText = result.toString();
	}
	/*
	 * auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmetghoden
	 * ein String im HTML Format wird zurück gegeben
	 */
	public String getReportText() {
		return this.getHeader() + this.reportText + this.getTrailer();
	}
}
