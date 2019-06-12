package de.hdm.server.report;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.shared.ReportGenerator;
import de.hdm.shared.report.Column;
import de.hdm.shared.report.CompositeParagraph;
import de.hdm.shared.report.Row;
import de.hdm.shared.report.SimpleParagraph;
import de.hdm.shared.bo.*;
import de.hdm.server.db.*;
import de.hdm.server.*;

@SuppressWarnings("serial")//UnterdrÃ¼ckung von Warnungen bezÃ¼glich fehlendem Feld 'serialVersionUID' fÃ¼r eine serialisierbare Klasse
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {
	
	/**Diese Methode soll eine Statistik über häufig einkaufte Artikel in einem Zeitraum 
	 * (falls angegeben) von einem Händler anzeigen.
	 * @Larisa
	 */
	
	public ShopStatisticReport createShopStatisticReport(String name, Date firstDate, Date lastDate)
	throws IllegalArgumentException {
		
		Shop s = this.getShopByName(name); 
		
		if (s != null) {
			
			//Ein leeren Report anlegen.
			ShopStatisticReport result = new ShopStatisticReport(); 
			
			result.setTitle("Shop Statistic"); 
			
			/**Zusammenstellung der Kopfdaten (das, was oben auf dem Report steht).
			 * Die Kopfdaten sind mehrzeilig, deswegen wird die Klasse CompositeParagraph verwendet.
			 * 
			 * @author Larisa in Anlehnung Thies
			 */
			CompositeParagraph header = new CompositeParagraph(); 
			
			//Impressumsbezeichnung hinzufügen
			header.addSubParagraph(new SimpleParagraph("Impressum: ")); 
			
			//Hinzufügen des zusammengestellten Kopfdaten 
			result.setHeaderData(header); 
			
			//Erstellen und Abrufen der benötigten Ergebnisvektoren mittels ShopITAdministration 
			Vector<Article> articles = this.aMapper.getAllArticlesForShopWithTime(a, firstDate, lastDate); 
			
			//Kopfzeile für die Händlerstatistik-Tabelle. 
			Row headline = new Row(); 
			
			/**
			 * Die Tabelle wird Zeilen mit 3 Spalten haben. Die erste Spalten entählt
			 * der Name des Artikels, die zweite die Anzahl des Artikels und die dritte
			 * Spalte den gewßünschten Zeitraum, falls angegeben. 
			 * In der Kopfzeile werden die entsprechenden Überschriften angelegt. 
			 * 
			 * @author Larisa in Anlehnung Thies
			 */
			
			headline.addColumn(new Column("Article"));
			headline.addColumn(new Column("Article Quantity"));
			headline.addColumn(new Column("Period of Time"));
			
			//Hinzufügen der Kopfzeile.
			result.addRow(headline); 
			
			//Eine leere Zeile anlegen.
			Row row = new Row(); 
			
			//Die erste Spalte: Artikelname 
			row.addColumn(new Column(a.getAllArticlesByShop()));
			row.addColumn(new Column(articles.size() + ""));
			
			//Die Zeilen dem Report hinzufügen
			result.addRow(row); 
			
			//Impressum hinzufügen
			result.addImprint(result); 
			
			//Report zurückgeben 
			return result;

		} else {
			
			return null; 
		}
	}

	

}
