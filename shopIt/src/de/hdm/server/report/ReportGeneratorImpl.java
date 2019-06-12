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

@SuppressWarnings("serial")//Unterdrückung von Warnungen bezüglich fehlendem Feld 'serialVersionUID' für eine serialisierbare Klasse
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {
	
	/**Diese Methode soll eine Statistik �ber h�ufig einkaufte Artikel in einem Zeitraum 
	 * (falls angegeben) von einem H�ndler anzeigen.
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
			
			//Impressumsbezeichnung hinzuf�gen
			header.addSubParagraph(new SimpleParagraph("Impressum: ")); 
			
			//Hinzuf�gen des zusammengestellten Kopfdaten 
			result.setHeaderData(header); 
			
			//Erstellen und Abrufen der ben�tigten Ergebnisvektoren mittels ShopITAdministration 
			Vector<Article> articles = this.aMapper.getAllArticlesForShopWithTime(a, firstDate, lastDate); 
			
			//Kopfzeile f�r die H�ndlerstatistik-Tabelle. 
			Row headline = new Row(); 
			
			/**
			 * Die Tabelle wird Zeilen mit 3 Spalten haben. Die erste Spalten ent�hlt
			 * der Name des Artikels, die zweite die Anzahl des Artikels und die dritte
			 * Spalte den gew��nschten Zeitraum, falls angegeben. 
			 * In der Kopfzeile werden die entsprechenden �berschriften angelegt. 
			 * 
			 * @author Larisa in Anlehnung Thies
			 */
			
			headline.addColumn(new Column("Article"));
			headline.addColumn(new Column("Article Quantity"));
			headline.addColumn(new Column("Period of Time"));
			
			//Hinzuf�gen der Kopfzeile.
			result.addRow(headline); 
			
			//Eine leere Zeile anlegen.
			Row row = new Row(); 
			
			//Die erste Spalte: Artikelname 
			row.addColumn(new Column(a.getAllArticlesByShop()));
			row.addColumn(new Column(articles.size() + ""));
			
			//Die Zeilen dem Report hinzuf�gen
			result.addRow(row); 
			
			//Impressum hinzuf�gen
			result.addImprint(result); 
			
			//Report zur�ckgeben 
			return result;

		} else {
			
			return null; 
		}
	}

	

}
