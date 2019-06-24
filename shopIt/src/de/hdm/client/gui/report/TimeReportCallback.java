package de.hdm.client.gui.report;

import java.util.Date;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ReportGeneratorAsync;
import de.hdm.shared.bo.Team;
import de.hdm.shared.report.HTMLReportWriter;
import de.hdm.shared.report.ShopStatisticReport;
import de.hdm.shared.report.TeamStatisticReport;



public class TimeReportCallback extends Widget {
	
ReportGeneratorAsync reportverwaltung = ClientsideSettings.getReportGenerator();

	public TimeReportCallback(String string, Date firstDate, Date lastDate) {
		
			
			reportverwaltung.createTeamStatisticReport(Team t, Date firstDate, Date lastDate);
			
			
			
		class TeamReport implements AsyncCallback<TeamStatisticReport>{
			
		
			public void onFailure(Throwable caught) {
				
			}
			
			public void onSuccess(TeamStatisticReport result) {
				
				int resultSize = result.getRows().size();
				if(resultSize == 0) {
					Window.alert("Es wurden keine Daten geladen.");
					
				}else {
					HTMLReportWriter hrw = new HTMLReportWriter();
					hrw.process(result);
					append(hrw.getReportText());
				}
			}
		}

		}
	}

}