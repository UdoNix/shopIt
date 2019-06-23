package de.hdm.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;
import de.hdm.shared.report.AllArticlesOfShopReport;
import de.hdm.shared.report.Report;
import de.hdm.shared.report.TeamAndShopStatistikReport;
import de.hdm.shared.report.TeamStatisticReport;

/**
 * Das ist die Synchrone Schnittstelle fÃ¼r eine RPC-fÃ¤hige Klasse zur Erstellung von Reports. Diese Schnittstelle benutzt die gleiche Realisierungsgrundlage wir
 * das Paar ShopITAdministration und ShopITAdministrationImpl. 
 * <p>
 * Durch den ReportGenerator werden die Berichte, also Reports erstellt, welche *  die Menge von Daten bzgl. Bestimmter Sachverhalte des Systems zweckspezifisch darstellen.
 * </p>
 * <p>
 * Die Klasse erstellt mit Hilfe von create…-Methoden den Report. Jede dieser Methoden besitzt eine dem Anwendungsfall entsprechende Parameterliste. Diese Parameter benötigt der der Generator, um den Report erstellen zu kÃ¶nnen.
 * </p>
 * <p> 
 * Bei neu hinzukommenden Bedarfen an Berichten, kann diese Klasse auf einfache
 * Weise erweitert werden. Hierzu können zusätzliche create…-Methoden implementiert werden. Die bestehenden Methoden bleiben davon
 * unbeeinflusst, so dass bestehende Programmlogik nicht verändert werden muss.
 * </p>
 * @author ilona
 *
 */

public interface ReportGenerator extends RemoteService{

/**
 * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrundon GWT
 * RPC zusätzlich zum No Argument Constructor, der implementieren Klasse
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
	  * Setzen der zugehörigen Gruppe
	  * 
	  */
	public void setTeam(Team t) ;
	
	/**
	 * HinzufÃ¼gen des Impressums zum Report.
	 */
	public void addImprint(Report report);
	
	/**Diese Methode soll eine Statistik über häufig einkaufte Artikel von einem Händler anzeigen.
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
	
	public TeamStatisticReport createTeamStatisticReport(Team t, Date firstDate, Date lastDate) throws IllegalArgumentException {


		
	/**
	 * Die Methode soll alle Artikel eines gewissen Zeitraums anhand eines Händlers anzeigen 
	 * 
     * Zurückgegeben wird ein fertiger Report
	 *
	 */
		
		public TeamAndShopStatistikReport createTeamAndShopStatistikReport(String tname, String sname, Date firstDate, Date lastdate) throws IllegalArgumentException;