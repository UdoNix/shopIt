package de.hdm.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;
import de.hdm.shared.report.AllArticlesOfShopReport;
import de.hdm.shared.report.Report;
import de.hdm.shared.report.TeamAndShopStatistikReport;
import de.hdm.shared.report.TeamStatisticReport;
import de.hdm.shared.ReportGeneratorAsync;

/**
 * Das ist die Synchrone Schnittstelle für eine RPC-fähige Klasse zur Erstellung von Reports. Diese Schnittstelle benutzt die gleiche Realisierungsgrundlage wir
 * das Paar ShopITAdministration und ShopITAdministrationImpl. 
 * <p>
 * Durch den ReportGenerator werden die Berichte, also Reports erstellt, welche *  die Menge von Daten bzgl. Bestimmter Sachverhalte des Systems zweckspezifisch darstellen.
 * </p>
 * <p>
 * Die Klasse erstellt mit Hilfe von create�-Methoden den Report. Jede dieser Methoden besitzt eine dem Anwendungsfall entsprechende Parameterliste. Diese Parameter ben�tigt der der Generator, um den Report erstellen zu können.
 * </p>
 * <p> 
 * Bei neu hinzukommenden Bedarfen an Berichten, kann diese Klasse auf einfache
 * Weise erweitert werden. Hierzu k�nnen zus�tzliche create�-Methoden implementiert werden. Die bestehenden Methoden bleiben davon
 * unbeeinflusst, so dass bestehende Programmlogik nicht ver�ndert werden muss.
 * </p>
 * @author ilona
 *
 */

public interface ReportGenerator extends RemoteService{

/**
 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrundon GWT
 * RPC zus�tzlich zum No Argument Constructor, der implementieren Klasse
 * ShopITAdministrationImpl notwendig. Diese Methode wird direkt nach der
 * Instantiierung aufgerufen.
 * 
 * @throws IllegalArgumentException
 * 
 */
	public void init() throws IllegalArgumentException;
	
	/**
	 * Auslesen der ShopITAdministration.
	 */
	public ShopITAdministration getShopITAdministration();
	
	/**
	  * Setzen der zugeh�rigen Gruppe
	  * 
	  */
	public void setTeam(Team t) ;
	
	/**
	 * Hinzufügen des Impressums zum Report.
	 */
	public void addImprint(Report report);
	
	/**Diese Methode soll eine Statistik �ber h�ufig einkaufte Artikel von einem H�ndler anzeigen.
	 * 
	 */
	
	public AllArticlesOfShopReport createAllArticlesOfShopReport(Shop shop)
	throws IllegalArgumentException;
	
	/**
	 * Die Methode soll die Anzahl der Artikel in einem Zeitraum 
	 * von einer Gruppe anzeigen
	 * 
	 * Ergebnisse werden im Form von einem Report angezeigt
	 * 
	 */
	
	public TeamStatisticReport createTeamStatisticReport(Team t, Date firstDate, Date lastDate) throws IllegalArgumentException;


		
	/**
	 * Die Methode soll alle Artikel eines gewissen Zeitraums anhand eines H�ndlers anzeigen 
	 * 
     * Zur�ckgegeben wird ein fertiger Report
	 *
	 */
		
		public TeamAndShopStatistikReport createTeamAndShopStatistikReport(String tname, String sname, Date firstDate, Date lastdate) throws IllegalArgumentException;
		
		
}