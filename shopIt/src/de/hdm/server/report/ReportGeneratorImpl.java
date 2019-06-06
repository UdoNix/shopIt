package de.hdm.server.report;
import java.util.Vector;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.shared.ReportGenerator;
import de.hdm.shared.bo.Article;
import de.hdm.shared.report.CompositeParagraph;
import de.hdm.shared.report.Row;
import de.hdm.shared.report.SimpleParagraph;

@SuppressWarnings("serial")//UnterdrÃ¼ckung von Warnungen bezÃ¼glich fehlendem Feld 'serialVersionUID' fÃ¼r eine serialisierbare Klasse
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator{


	
	/*
	 * Die Methode soll die Anzahl der Artikel in einem Zeitraum 
	 * von einer Gruppe anzeigen
	 * 
	 * Ergebnisse werden im Form von einem Report angezeigt
	 * 
	 * @author IlonaBrinkmann, Thies
	 */
	
	public TeamStatisticReport createTeamStatisticReport(String name, Date firstDate, Date lastDate) throws IllegalArgumentException {
		
		if (this.getAticles() == null) {
			return null;
		}
		Team t = this.getArticlesbyTeam(name);
		
		if (t != null) {
			
			//Einen leeren Report anlegen.
			TeamStatisticReport result = new TeamStatisticReport();
			//Jeder Report hat einen Titel
			result.setTitle("Teamstatistik");
			
			/*
			 * Jetzt erfolgt die Zusammenstellung der allgemeinen Daten.
			 * Diese Kopfdaten werden am Anfang des Reports erscheinen und
			 * mit Hife eines CompositeParagraph dargestellt, da dies mehrzeilig ist
			 */
			CompositeParagraph header = new CompositeParagraph();
			//Impressumsbezeichnung hinzufügen
			header.addSubParagraph(new SimpleParagraph("Impressum: "));
			//Hinzufügen des zusammengestellten Kopfdaten
			result.setHeaderData(header);
			
			//Erstellen und Abrufen der benötigten Ergebnisvektoren mittels PinnwandVerwaltung
			Vector<Article> articles = this.getArticlesbyTeamWithTime(teamId, firstDate, lastDate);
			
			//Kopfzeile für die Teamstatistik Tabelle
			Row headline = new Row();
			
			/*
			 * In der Tabelle wird in der ersten Spalte
			 * der Name des Artikels angezeigt und
			 * in der zweiten Spalte wird die Anzahl augegeben,
			 * wie oft der Artikel auf die Liste geschrieben worden ist
			 * 
			 * -es wird nur gezählt, wie oft ein Artikel zu einer Liste hinzugefügt wurde,
			 * es wird nicht nachgeprüpft ob der Artikel tatsächich gekauft wurde bzw. 
			 * abgehakt wurde
			 * desweiteren wird auch nicht anhand der Mengeneinheit zusammengerechnet
			 * sondern nur die Anzahl der Häufigkeit der Auflistung angezeigt 
			 */
			Column c1 = new Column("Artikel");
			headline.addColumn(c1);
			headline.addColumn(new Column("Anzahl"));
			
			
		}
	}

}
