package de.hdm.client.gui.report;

import java.util.Date;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ReportGeneratorAsync;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;
import de.hdm.shared.report.HTMLReportWriter;
import de.hdm.shared.report.TeamAndShopStatistikReport;


public class ShopTimeReportCallback extends VerticalPanel  {
	ReportGeneratorAsync reportverwaltung = ClientsideSettings.getReportGenerator(); 
	

	public ShopTimeReportCallback(Shop s, Team t, Date firstdate, Date lastdate) {
		
		reportverwaltung.createTeamAndShopStatistikReport(s, t, firstdate, lastdate, new TeamAndShopStatistikReportCallback());
	}
	
	public class TeamAndShopStatistikReportCallback implements AsyncCallback<TeamAndShopStatistikReport> {
		
		public void onFailure(Throwable caught) {
			Window.alert("Fehler");
		}
		
		public void onSuccess(TeamAndShopStatistikReport result) {
			HTMLReportWriter hrw = new HTMLReportWriter();
			hrw.process(result);
			String reportText = hrw.getReportText();

			clear();
			add(new HTML(reportText));
			Window.alert("Success");
		}



		
	}

}
