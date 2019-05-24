package de.hdm.shared.report;
import java.util.Vector;

/**
 * Ein ReportWriter, der Reports mithilfe von Plain Text formatiert.
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
		return ".........................................";
	}
	
//Ablage des übergebenen Reports im Zielformat.Auslese erfolgt durch getReportText().
public void process(AllItemsOfPersonReport r){
		
	
	//Löschen des Ergebnisses einer vorhergehenden Prozessierung.
		this.resetReportText();
		
	//Buffer, in den Ergebnisse reingeschrieben werden.
		StringBuffer result = new StringBuffer();
		
	//Bestandteile 	des Reports werden ausgelesen und in Textform übersetzt.
		result.append("*** " + r.getTitle() + " ***\n\n");
		result.append(r.getHeaderData() + "\n");
		result.append("Erstellt am: "+ r.getCreated().toString() + "\n\n");
		Vector<Row> rows = r.getRows();
		
		for (Row row : rows){
			for(int i = 0; i< row.getNumColumns(); i++){
				result.append(row.getColumnAt(i) + "\t; \t");
		
			}
			
			result.append("\n");
		}
		
		result.append("\n");
		result.append(r.getImprint() + "\n");
		
		//Buffer wird in String umgewandelt und der Variable reportText zugewiesen.
		//Man kann ihn durch getReportText() auslesen.
		this.reportText = result.toString();
		
		}
	
	//AllItemsOfAllPersonsReport ist ein CompositeReport.
	public void process(AllItemsOfAllPersonsReport r){

	//Löschen des Ergebnisses einer vorhergehenden Prozessierung.
		this.resetReportText();
			
	//Buffer, in den Ergebnisse reingeschrieben werden.
		StringBuffer result = new StringBuffer();
			
		//Bestandteile 	des Reports werden ausgelesen und in Textform übersetzt.
		result.append("*** " + r.getTitle() + " ***\n\n");
		result.append(r.getHeaderData() + "\n");			
		result.append("Erstellt am: "+ r.getCreated().toString() + "\n\n");
		
		//Der CompositeReport enthält eine Menge von Teil-Reports des Typs AllAccountsOfCustomerReport.
		for (int i = 0; i < r.getNumSubReports(); i++){
			AllItemsOfPersonReport subReport = (AllItemsOfPersonReport)r.getSubReportAt(i);
			
			this.process(subReport);
			
			result.append(this.reportText+ "\n\n\n\n\n");

			//Ergebnisvariable wird zurückgesetzt.
			this.resetReportText();
		}
			
			//Buffer wird in String umgewandelt und der Variable reportText zugewiesen.
			//Man kann ihn durch getReportText() auslesen.
			this.reportText = result.toString();
			
			}
		
	//Auslesen des Ergebnisses 
	public String getReportText(){
		return this.getHeader()+this.reportText+ this.getTrailer();
	}

	}
	

		
