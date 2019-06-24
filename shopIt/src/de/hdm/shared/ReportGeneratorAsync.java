package de.hdm.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;
import de.hdm.shared.report.TeamAndShopStatistikReport;

/**
 * Das asynchrone Gegenst�ck des Interface ReportGenerator. Es
 * wird semiautomatisch durch das Google Plugin erstellt und gepflegt.
 * 
 * @author ilonaBrinkmann
 *
 */

public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> initReportGeneratorCallback);

	void getAllTeams(GetAllTeamsCallback getAllTeamsCallback);

	void getAllShops(GetAllShopsCallback getAllShopsCallback);

	void createTeamAndShopStatistikReport(Shop s, Team t, Date firstdate, Date lastdate, AsyncCallback<TeamAndShopStatistikReport> callback);

	

}
