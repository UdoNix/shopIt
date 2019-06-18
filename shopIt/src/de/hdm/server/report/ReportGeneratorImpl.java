package de.hdm.server.report;
import java.util.Date;
import java.util.Vector;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.server.ShopITAdministrationImpl;
<<<<<<< HEAD
import de.hdm.shared.ReportGenerator;
=======
>>>>>>> branch 'master' of https://github.com/UdoNix/shopIt.git

<<<<<<< HEAD
=======
import de.hdm.shared.ReportGenerator;
>>>>>>> branch 'master' of https://github.com/UdoNix/shopIt.git
import de.hdm.shared.ShopITAdministration;
import de.hdm.shared.report.CompositeParagraph;
import de.hdm.shared.report.Report;
import de.hdm.shared.report.Row;
<<<<<<< HEAD
import de.hdm.shared.bo.Article;

import de.hdm.shared.report.Column;

import de.hdm.shared.report.CompositeParagraph;
import de.hdm.shared.report.Row;

import de.hdm.shared.report.Column;

=======
import de.hdm.shared.bo.Article;
import de.hdm.shared.report.AllArticlesOfShopReport;
import de.hdm.shared.report.Column;
>>>>>>> branch 'master' of https://github.com/UdoNix/shopIt.git
import de.hdm.shared.report.SimpleParagraph;
import de.hdm.shared.report.TeamAndShopStatistikReport;
import de.hdm.shared.report.TeamStatisticReport;
import de.hdm.shared.bo.*;
import de.hdm.server.db.*;
import de.hdm.server.*;

/**
 * Implementierung des ReportGenerator. 
 */
@SuppressWarnings("serial")//UnterdrÃ¼ckung von Warnungen bezÃ¼glich fehlendem Feld 'serialVersionUID' fÃ¼r eine serialisierbare Klasse
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {
<<<<<<< HEAD
	

=======
	
>>>>>>> branch 'master' of https://github.com/UdoNix/shopIt.git
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
	  * Setzen der zugehörigen Gruppe
	  * 
	  * @author ilonabrinkmann
	  * 
	  */
	public void setTeam(Team t) {
		this.admin.setTeam(t);
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

	
<<<<<<< HEAD

	/**
	 * Diese Methode soll eine Statistik über häufig einkaufte Artikel in einem Zeitraum 
	 * (falls angegeben) von einem Händler anzeigen.
	 * @Larisa
=======
	
	/**Diese Methode soll eine Statistik über häufig einkaufte Artikel von einem Händler anzeigen.
	 * @Larisa in Anlehnung Thies
>>>>>>> branch 'master' of https://github.com/UdoNix/shopIt.git
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
			header.addSubParagraph(new SimpleParagraph(shop.getName()));
			
			//Hinzufï¿½gen des zusammengestellten Kopfdaten.
<<<<<<< HEAD
			result.setHeaderData(header); 
			

			//Erstellen und Abrufen der benï¿½tigten Ergebnisvektoren mittels ShopITAdministration 
			Vector<Article> articles = this.getAllArticlesForShopWithTime(a, firstDate, lastDate); 
=======
			result.setHeaderData(header);
>>>>>>> branch 'master' of https://github.com/UdoNix/shopIt.git

			
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
			
			Vector<Item> items = this.admin.getItemsbyTeamAndShop(shop); 
			
			for (Item i : items) {
				//Eine leere Zeile anlegen.
				Row itemRow = new Row(); 
				
				//Erste Spalte: Artikelname hinzufügen
				itemRow.addColumn(new Column(String.valueOf(i.getArticleId())));
				
				//Zweite Spalte: Anzahl des Artikels
				itemRow.addColumn(new Column(String.valueOf(i.getCount())));

				//Die Zeilen dem Report hinzufügen
				result.addRow(itemRow); 
				
			} 
			
			//Report zurückgeben 
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
	
	public TeamStatisticReport createTeamStatisticReport(Team t, Date firstDate, Date lastDate) throws IllegalArgumentException {
		
		
		int teamid = t.getId();
		
		if (this.getShopITAdministration() == null) {
			return null;
		}
		Team t = admin.getTeamById(teamid);
		
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
	
	/*
	 * Die Methode soll alle Artikel eines gewissen Zeitraums anhand eines Händlers anzeigen 
	 * 
	 * Zurückgegeben wird ein fertiger Report
	 * 
	 * @author IlonaBrinkmann
	 */
	
	private int shopid;
	
	private int teamid;
	
	public TeamAndShopStatistikReport createTeamAndShopStatistikReport(String tname, String sname, Date firstDate, Date lastdate) throws IllegalArgumentException {
		
		Shop s1 = this.getShopbyName(sname);
		
		shopid = s1.getId();
		
		Team t1 = this.getTeambyName(tname);
		
		teamid = t1.getId();
		
		Shop s = this.getShopITAdministration().getShopById(shopid);
		
		Team t = this.getShopITAdministration().getTeamById(teamid);
		
		if ( s != null) {
			
			// einen leeren Report anlegen
			TeamAndShopStatistikReport result = new TeamAndShopStatistikReport();
			
			//Jeder Report hat einen Titel
			result.setTitle("TeamAndShopStatistik:");
			
			/*
			 * 
			 * Nun folgt die Zusammenstellung der Kopfdaten des Reports
			 * Die Kopfdaten sind mehrzeilig, daher die Verwendung des
			 * CompositeParagraph
			 * 
			 * 
			 */
			
			CompositeParagraph header = new CompositeParagraph();
			//Impressumsbezeichnung hinzufügen
			header.addSubParagraph(new SimpleParagraph("Impressum: "));
			//Hinzufügen des zusammengestellten Kopfdaten
			result.setHeaderDate(header);
			
			//Erstellen und Abrufen der benötigten Ergebnisvektoren mittels ShopITVerwaltung
			Vector<Article> articles = this.getShopITVerwaltung().getArticlesbyTeamAndShopWithTime(t, s, firstDate, lastdate);
			
			
			//Kopfzeil dür die Team/Shop Statistik Tabelle
			Row headline = new Row();
			
			/*
			 * Es wird eine Tabelle mit 3 Spalten erzeugt. In die erste Spalte 
			 * wird der Name des Artikels hingeschrieben. In die zweite Spalte kommt der Name des Shops und 
			 * in die dritte Spalte kommt die Anzahl der Häufigkeit. Also wie oft der 
			 * Artikel den Listen aufgelistet war.
			 */
			
			headline.addColumn(new Column("Artikel"));
			headline.addColumn(new Column("Einzelhändler"));
			headline.addColumn(new Column("Anzahl"));
			
			//Hinzufügen der Kopfzeile
			result.addRow(headline);
			
			//Eine leere Zeile anlegen
			Row row = new Row();
			
			//Erste Spalte: Artikelname
			row.addColumn(new Column(articles.size() + "");
			
			//die Zeilen werden zu dem Report hinzugefügt
			result.addRow(row);
			
			//Impressum hinzufügen
			this.addImprint(result);
			
			//es wird zum Schluss wird der fertige Report abgegeben
			return result;

			
		}else {
			return null;
		}
		
		
	}

}
