package de.hdm.server.report;
import java.sql.Timestamp;
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
import de.hdm.shared.report.Column;
import de.hdm.shared.report.AllArticlesOfShopReport;
import de.hdm.shared.report.SimpleParagraph;
import de.hdm.shared.report.TeamAndShopStatistikReport;
import de.hdm.shared.report.TeamStatisticReport;
import de.hdm.shared.bo.*;
import de.hdm.server.db.*;
import de.hdm.server.*;

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
	  * Setzen der zugeh�rigen Gruppe
	  * 
	  */
	public void setTeam(Team t) {
		this.admin.setTeam(t);
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
	
	/**Diese Methode soll eine Statistik �ber h�ufig einkaufte Artikel von einem H�ndler anzeigen.
	 * @Larisa in Anlehnung Thies
	 */
	
	public AllArticlesOfShopReport createAllArticlesOfShopReport(Shop shop)
	throws IllegalArgumentException {
		
		if (this.getShopITAdministration() == null) {
			return null;
		}  
			
			//Einen leeren Report anlegen.
			AllArticlesOfShopReport result = new AllArticlesOfShopReport(); 
			
			//Jeder Report sollte einen Titel bzw. eine Bezeichnung haben.
			result.setTitle("Shop Statistic"); 
			
			//Impressum hinzuf�gen 
			this.addImprint(result);
			
			/**
			 * Datum der Erstellung hinzuf�gen. Mithilfe der Methode new Date()
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
			header.addSubParagraph(new SimpleParagraph(shop.getName()));
			
			//Hinzuf�gen des zusammengestellten Kopfdaten.
			result.setHeaderData(header); 
			
			
			//Kopfzeile f�r die H�ndlerstatistik-Tabelle. 
			Row headline = new Row(); 
			
			/**
			 * Die Tabelle wird Zeilen mit 2 Spalten haben. Die erste Spalten ent�hlt
			 * der Name des Artikels, die zweite die Anzahl des Artikels. 
			 * In der Kopfzeile werden die entsprechenden �berschriften angelegt. 
			 * 
			 * @author Larisa in Anlehnung Thies
			 */
			
			headline.addColumn(new Column("Article"));
			headline.addColumn(new Column("Article Quantity"));
			
			//Hinzuf�gen der Kopfzeile.
			result.addRow1(headline); 
			
			/**
			 * Nun werden alle Artikel eines H�ndlers ausgelesen und anhand deren
			 * H�ufigkeit in die Tabelle eingetragen. 
			 */
			
			Vector<Item> items = this.admin.getItemsbyTeamAndShop(shop);
			
			for (Item i : items) {
				//Eine leere Zeile anlegen.
				Row itemRow = new Row(); 
				
				//Erste Spalte: ArtikelId hinzuf�gen
				itemRow.addColumn(new Column(String.valueOf(i.getArticleId())));
				
				//Zweite Spalte: Anzahl des Artikels
				itemRow.addColumn(new Column(String.valueOf(i.getCount())));

				//Die Zeilen dem Report hinzuf�gen
				result.addRow(itemRow); 
				
			} 
			
			//Report zur�ckgeben 
			return result;
			
		}


	
	/*
	 * Die Methode soll die Anzahl der Artikel in einem Zeitraum 
	 * von einer Gruppe anzeigen
	 * 
	 * Ergebnisse werden im Form von einem Report angezeigt
	 * 
	 * @author IlonaBrinkmann, Thies
	 */
	
	public TeamStatisticReport createTeamStatisticReport(Team t, Timestamp firstDate, Timestamp lastDate) throws IllegalArgumentException {
		
		int teamId = t.getId();
		
		if (this.getShopITAdministration() == null) {
			return null;
		}
			
		//Einen leeren Report anlegen.
		TeamStatisticReport result = new TeamStatisticReport();
			
		//Jeder Report hat einen Titel
		result.setTitle("Teamstatistik");
			
		//Impressumsbezeichnung hinzuf�gen
		result.addImprint(result); 
			
		/*Datum der Erstellung hinzuf�gen. new Date() erzeugt autom. einen
		*"Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts
		*/
			
		result.setCreated(new Date()); 
			
		/*
		 * Jetzt erfolgt die Zusammenstellung der allgemeinen Daten.
		 * Diese Kopfdaten werden am Anfang des Reports erscheinen und
		 * mit Hife eines CompositeParagraph dargestellt, da dies mehrzeilig ist
		 */
			
		CompositeParagraph header = new CompositeParagraph();
			
		//Gruppenname aufnehmen
		header.addSubParagraph(new SimpleParagraph(t.getName()));
			
			
		//Hinzuf�gen des zusammengestellten Kopfdaten
		result.setHeaderData(header);
			
<<<<<<< HEAD
=======
			

		//Erstellen und Abrufen der ben�tigten Ergebnisvektoren mittels PinnwandVerwaltung
		Vector<Item> itemsTeamTime = this.admin.getItemsByTeamWithTime(teamId, firstDate, lastDate);
			
			
>>>>>>> refs/remotes/origin/master
		//Kopfzeile f�r die Teamstatistik Tabelle
		Row headline = new Row();
			
		/*
		 * In der Tabelle wird in der ersten Spalte
		 * der Name des Artikels angezeigt und
		 * in der zweiten Spalte wird die Anzahl augegeben,
		 * wie oft der Artikel auf die Liste geschrieben worden ist
		 * 
		 * -es wird nur gez�hlt, wie oft ein Artikel zu einer Liste hinzugef�gt wurde,
		 * es wird nicht nachgepr�pft ob der Artikel tats�chich gekauft wurde bzw. 
		 * abgehakt wurde
		 * desweiteren wird auch nicht anhand der Mengeneinheit zusammengerechnet
		 * sondern nur die Anzahl der H�ufigkeit der Auflistung angezeigt 
		 */
			
		//Headline Spalte: Artikel Id
		headline.addColumn(new Column("Artikel"));
		//Zweite Headline Spalte: Die Anzahl zeigt an wie h�ufig der Artikel gekauft wurde
		headline.addColumn(new Column("Anzahl"));
			
		//Hinzuf�gen der Kopfzeile
		result.addRow(headline);
			
		/*
		* Nun werden alle Artikel einer Gruppe ausgelesen und anhand deren
		* H�ufigkeit in die Tabelle eingetragen.
     	 */
			
		Vector<Item> items = this.admin.getItemsByTeamWithTime(t); 
			
			for (Item i: items) {
				//Eine leere Zeile anlegen.
				Row itemRow = new Row(); 
				
				//Erste Spalte: ArticleId hinzuf�gen
				itemRow.addColumn(new Column(String.valueOf(i.getArticleId())));
				itemRow.addColumn(new Column(String.valueOf(i.getCount())));
				
				//Schliesslich die Zeile dem Report hinzuf�gen
				result.addRow(itemRow);
		
			}
			//Report zur�ckgeben
			
			return result;
	}
	
	/*
	 * Die Methode soll alle Artikel eines gewissen Zeitraums anhand eines H�ndlers anzeigen 
	 * 
	 * Zur�ckgegeben wird ein fertiger Report
	 * 
	 * @author IlonaBrinkmann & Larisa
	 */
	
	//private int shopid;
	
	//private int teamid;
	
<<<<<<< HEAD
	public TeamAndShopStatistikReport createTeamAndShopStatistikReport(Shop s, Team t, Timestamp firstDate, Timestamp lastdate) throws IllegalArgumentException {
=======

	public TeamAndShopStatistikReport createTeamAndShopStatistikReport(Shop s, Team t, Date firstDate, Date lastdate) throws IllegalArgumentException {
>>>>>>> refs/remotes/origin/master
		
		if (this.getShopITAdministration() == null) {
			return null;
			
		}
			
		//Einen leeren Report anlegen
		TeamAndShopStatistikReport result = new TeamAndShopStatistikReport();
			
		//Jeder Report hat einen Titel
		result.setTitle("TeamAndShopStatistik:");
		
		//Impressum hinzuf�gen
		result.addImprint(result);
		
		/*
		 * Datum der Erstellung hinzuf�gen. new Timestamp() erzeugt autom. einen
         * "Timestamp" des Zeitpunkts der Instantiierung des Timestamp-Objekts.
		 */
		
		result.setCreated(new Date()); 
			
		/*
		 * 
		 * Nun folgt die Zusammenstellung der Kopfdaten des Reports
		 * Die Kopfdaten sind mehrzeilig, daher die Verwendung des
		 * CompositeParagraph
		 * 			
		 * 
		 */
			
		CompositeParagraph header = new CompositeParagraph();
		
		//Team Name aufnehmen
		header.addSubParagraph(new SimpleParagraph(t.getName()));
		
		//Shop Name aufnehmen
		header.addSubParagraph(new SimpleParagraph(s.getName()));
		
		//Hinzuf�gen des zusammengestellten Kopfdaten
		result.setHeaderData(header);
		
		//Kopfzeil d�r die Team/Shop Statistik Tabelle
		Row headline = new Row();
			
		/*
		 * Es wird eine Tabelle mit 3 Spalten erzeugt. In die erste Spalte 
		 * wird der Name des Artikels hingeschrieben. In die zweite Spalte kommt der Name des Shops und 
		 * in die dritte Spalte kommt die Anzahl der H�ufigkeit. Also wie oft der 
		 * Artikel den Listen aufgelistet war.
		 */
			
		headline.addColumn(new Column("Artikel"));
		headline.addColumn(new Column("Einzelh�ndler"));
		headline.addColumn(new Column("Anzahl"));
			
		//Hinzuf�gen der Kopfzeile
		result.addRow(headline);
		
		/*
		 * Nun werden alle Artikel einer Gruppe ausgelesen und anhand deren H�ufigkeit
		 * und deren H�ndler in die Tabelle eingetragen.
		 */
		
		Vector<Item> items = this.admin.getItemsByTeamAndShopWithTime(s,t);
		
		for (Item i: items) {
			//Eine leere Zeile anlegen.
			Row itemRow = new Row();
			
			//Erste Spalte: Artikel Id
			itemRow.addColumn(new Column(String.valueOf(i.getArticleId())));
			
			//Zweite Spalte: Anzahl
			itemRow.addColumn(new Column(String.valueOf(i.getCount())));
			
			//die Zeile werden zu dem Report hinzugef�gt
			result.addRow(itemRow);

		}
		
		//es wird zum Schluss wird der fertige Report abgegeben
		return result;
		
	}
		
	
	}


