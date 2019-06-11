package de.hdm.server.report;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.server.ShopITAdministrationImpl;
import de.hdm.shared.ReportGenerator;
import de.hdm.shared.ShopITAdministration;
import de.hdm.shared.report.CompositeParagraph;
import de.hdm.shared.report.Report;
import de.hdm.shared.report.Row;
import de.hdm.shared.report.SimpleParagraph;

/**
 * Implementierung des ReportGenerator. 
 */
@SuppressWarnings("serial")//Unterdrückung von Warnungen bezüglich fehlendem Feld 'serialVersionUID' für eine serialisierbare Klasse
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {
	
	/**
	 * Zugriff auf die ShopITAdministration um Methoden von Datenobjekten des BO-Packages zu erhalten.
	 * @author InesWerner
	 */
	private ShopITAdministration admin = null;
	
	public ReportGeneratorImpl() throws IllegalArgumentException{
		
	}
	
	//Initialisierungsmethode 
	public void init() throws IllegalArgumentException{
		/**
		 * Ein Objekt der ReportGeneratorImpl instanziiert eine eigene ShopITAdministrazionImpl-Instanz.
		 * @author InesWerner
		 */
		ShopITAdministrationImpl shopITAdministration = new ShopITAdministrationImpl();
		shopITAdministration.init();
		this.admin = shopITAdministration;
		
	}
	
	/**
	 * Auslesen der ShopITAdministration.
	 */
	public ShopITAdministration getShopITAdministration(){
		return this.admin;
	}
	
	/**
	 * Hinzufügen des Impressums zum Report.
	 * @author InesWerner
	 */
	public void addImprint(Report report){
		CompositeParagraph imprint = new CompositeParagraph();
		imprint.addSubParagraph(new SimpleParagraph("ShopIT"));
		imprint.addSubParagraph(new SimpleParagraph("Nobelstraße 8"));
		imprint.addSubParagraph(new SimpleParagraph("70569"));
		
		//Impressum wird dem Report hinzugefügt.
		report.setImprint(imprint);
	}

	
	/**Diese Methode soll eine Statistik �ber h�ufig einkaufte Artikel in einem Zeitraum
	 * von einem H�ndler anzeigen.
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
			
			//Impressumsbezeichnung hinzuf�gen
			header.addSubParagraph(new SimpleParagraph("Impressum: ")); 
			
			//Hinzuf�gen des zusammengestellten Kopfdaten 
			result.setHeaderData(header); 
			
			//Erstellen und Abrufen der ben�tigten Ergebnisvektoren mittels ShopITAdministration 
			Vector<Article> articles = this.getAllArticlesForShopWithTime(a, firstDate, lastDate); 
			
			//Kopfzeile f�r die H�ndlerstatistik-Tabelle. 
			Row headline = new Row(); 
		}
	}

	

}
