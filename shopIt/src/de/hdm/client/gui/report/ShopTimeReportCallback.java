package de.hdm.client.gui.report;

import java.util.Date;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ReportGeneratorAsync;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;
import de.hdm.shared.report.HTMLReportWriter;
import de.hdm.shared.report.TeamAndShopStatistikReport;

public class ShopTimeReportCallback extends Widget  {
	ReportGeneratorAsync reportverwaltung = ClientsideSettings.getReportGenerator(); 
	

	public ShopTimeReportCallback(Shop s, Team t, Date firstdate, Date lastdate) {
		
		reportverwaltung.createTeamAndShopStatistikReport(s, t, firstdate, lastdate, new TeamAndShopStatistikReport()); 
		
	}
	
	public class TeamAndShopStatistikReport implements AsyncCallback<TeamAndShopStatistikReport> {
		
		public void onFailure(Throwable caught) {
			
			
		}
		
		public void onSuccess(TeamAndShopStatistikReport result) {
			int resultSize = result.getRows().size();  
			if (resultSize == 0) {
				Window.alert("Es wurden keine Daten geladen");
			} else {
				HTMLReportWriter hrw = new HTMLReportWriter();
				hrw.process(result);
				append(hrw.getReportText());
			}
		}

		
	}

}
