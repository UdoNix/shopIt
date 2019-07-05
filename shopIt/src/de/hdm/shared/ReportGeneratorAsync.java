package de.hdm.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;

import de.hdm.shared.report.AllArticlesOfShopReport;
import de.hdm.shared.report.Report;
import de.hdm.shared.report.ShopStatisticReport;
import de.hdm.shared.report.TeamAndShopStatistikReport;
import de.hdm.shared.report.TeamStatisticReport;





/**
 * Das asynchrone Gegenst�ck des Interface ReportGenerator. Es
 * wird semiautomatisch durch das Google Plugin erstellt und gepflegt.
 * 
 * @author ilonaBrinkmann
 *
 */

public interface ReportGeneratorAsync {

	void addImprint(Report report, AsyncCallback<Void> callback);

	void createAllArticlesOfShopReport(Shop shop, Team team, AsyncCallback<ShopStatisticReport> callback);

	void createTeamAndShopStatistikReport(Shop s, Team t, Date firstDate, Date lastDate, AsyncCallback<TeamAndShopStatistikReport> callback);

	void createTeamStatisticReport(Team t, Date firstDate, Date lastDate, AsyncCallback<TeamStatisticReport> callback);

//	void getShopITAdministration(AsyncCallback<ShopITAdministration> callback);

	void init(AsyncCallback<Void> callback);

	void setTeam(Team t, AsyncCallback<Void> callback);

	//void getShopITAdministration(AsyncCallback<ShopITAdministration> callback);
	
	

}
