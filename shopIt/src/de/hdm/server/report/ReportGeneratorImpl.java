package de.hdm.server.report;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.shared.ReportGenerator;
import de.hdm.shared.report.CompositeParagraph;
import de.hdm.shared.report.Row;
import de.hdm.shared.report.SimpleParagraph;

@SuppressWarnings("serial")//UnterdrÃ¼ckung von Warnungen bezÃ¼glich fehlendem Feld 'serialVersionUID' fÃ¼r eine serialisierbare Klasse
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {
	
	/**Diese Methode soll eine Statistik über häufig einkaufte Artikel in einem Zeitraum
	 * von einem Händler anzeigen.
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
			 * @author in Anlehnung Thies
			 */
			CompositeParagraph header = new CompositeParagraph(); 
			
			//Impressumsbezeichnung hinzufügen
			header.addSubParagraph(new SimpleParagraph("Impressum: ")); 
			
			//Hinzufügen des zusammengestellten Kopfdaten 
			result.setHeaderData(header); 
			
			//Erstellen und Abrufen der benötigten Ergebnisvektoren mittels ShopITAdministration 
			Vector<Article> articles = this.getAllArticlesForShopWithTime(a, firstDate, lastDate); 
			
			//Kopfzeile für die Händlerstatistik-Tabelle. 
			Row headline = new Row(); 
		}
	}

	

}
