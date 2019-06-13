package de.hdm.server.report;
import java.util.Date;
import java.util.Vector;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.server.ShopITAdministrationImpl;
import de.hdm.shared.ReportGenerator;
import de.hdm.shared.ShopITAdministration;
import de.hdm.shared.report.CompositeParagraph;
import de.hdm.shared.report.Report;
import de.hdm.shared.report.Row;
import de.hdm.shared.bo.Article;
import de.hdm.shared.report.AllArticlesOfShopReport;
import de.hdm.shared.report.Column;
import de.hdm.shared.report.CompositeParagraph;
import de.hdm.shared.report.Row;
import de.hdm.shared.report.Column;
import de.hdm.shared.report.SimpleParagraph;
import de.hdm.shared.bo.*;
import de.hdm.server.db.*;
import de.hdm.server.*;

/**
 * Implementierung des ReportGenerator. 
 */
@SuppressWarnings("serial")//UnterdrÃ¼ckung von Warnungen bezÃ¼glich fehlendem Feld 'serialVersionUID' fÃ¼r eine serialisierbare Klasse
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
	 * HinzufÃ¼gen des Impressums zum Report.
	 * @author InesWerner
	 */
	public void addImprint(Report report){
		CompositeParagraph imprint = new CompositeParagraph();
		imprint.addSubParagraph(new SimpleParagraph("ShopIT"));
		imprint.addSubParagraph(new SimpleParagraph("NobelstraÃŸe 8"));
		imprint.addSubParagraph(new SimpleParagraph("70569"));
		
		//Impressum wird dem Report hinzugefÃ¼gt.
		report.setImprint(imprint);
	}

	
	
	/**Diese Methode soll eine Statistik über häufig einkaufte Artikel von einem Händler anzeigen.
	 * @Larisa
	 */
	
	public AllArticlesOfShopReport createAllArticlesOfShopReport(Shop shop, Date firstDate, Date lastDate)
	throws IllegalArgumentException {
		
		if (this.getShopITAdministration() == null) {
			return null;
		}
		
		Shop s = this.findByName(shop); 
		
		if (s != null) {
			
			//Ein leeren Report anlegen.
			AllArticlesOfShopReport result = new AllArticlesOfShopReport(); 
			
			//Jeder Report sollte einen Titel bzw. eine Bezeichnunh haben.
			result.setTitle("Shop Statistic"); 
			
			//Impressum hinzufügen 
			this.addImprint(result);
			
			/**
			 * Datum der Erstellung hinzufügen. Mithilfe der Methode new Date()
			 * wird automatisch einen "Timestamp" des Zeitpunkts der Instantiierung
			 * des Date-Objekts. 
			 */
			
			result.setCreated(new Date());
			
			/**Zusammenstellung der Kopfdaten (das, was oben auf dem Report steht).
			 * Die Kopfdaten sind mehrzeilig, deswegen wird die Klasse CompositeParagraph verwendet.
			 * 
			 * @author Larisa in Anlehnung Thies
			 */
			CompositeParagraph header = new CompositeParagraph(); 
			
			//Name des Shops aufnehmen.
			header.addSubParagraph(new SimpleParagraph(s.getName()));
			
			//Hinzufï¿½gen des zusammengestellten Kopfdaten.
			result.setHeaderData(header); 
			
			//Erstellen und Abrufen der benötigten Ergebnisvektoren mittels ShopITAdministration. 
			//Vector<Article> articles = this.aMapper.getAllArticlesForShopWithTime(a, firstDate, lastDate); 

			
			//Kopfzeile fï¿½r die Hï¿½ndlerstatistik-Tabelle. 
			Row headline = new Row(); 
			
			/**
			 * Die Tabelle wird Zeilen mit 2 Spalten haben. Die erste Spalten entählt
			 * der Name des Artikels, die zweite die Anzahl des Artikels. 
			 * In der Kopfzeile werden die entsprechenden Überschriften angelegt. 
			 * 
			 * @author Larisa in Anlehnung Thies
			 */
			
			headline.addColumn(new Column("Article"));
			headline.addColumn(new Column("Article Quantity"));
			
			//Hinzufügen der Kopfzeile.
			result.addRow1(headline); 
			
			/**
			 * Nun werden alle Artikel eines Händlers ausgelesen und anhand deren
			 * Häufigkeit in die Tabelle eingetragen. 
			 */
			
			Vector<Article> articles = this.admin.getAllArticlesOfShop(s); 
			
			for (Article a : articles) {
				//Eine leere Zeile anlegen.
				Row articleRow = new Row(); 
				
				//Erste Spalte: Artikelname hinzufügen
				articleRow.addColumn(new Column(s.getName()));
				
				//Zweite Spalte: Anzahl des Artikels
				articleRow.addColumn(new Column(articles.size() + ""));

				//Die Zeilen dem Report hinzufügen
				result.addRow(articleRow); 
				
				//Report zurückgeben 
				return result;
			} 
			
		} else {
			
			return null; 
		}
	}


	
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
			
			//Headline Spalte: Name des Artikels
			headline.addColumn(new Column("Artikel"));
			//Zweite Headline Spalte: Die Anzahl zeigt an wie häufig der Artikel gekauft wurde
			headline.addColumn(new Column("Anzahl"));
			
			//Hinzufügen der Kopfzeile
			result.addRow(headline);
			
			//Eine leere Zeile anlegen
			Row row = new Row();
			
			//Erste Spalte: Artikel
			row.addColumn(new Column(articles.size() + " "));
			
			
			
		}
	}

}
