package de.hdm.client.gui.report;

import java.util.Date;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;
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

/**
 * 
 * @author ilona
 *
 */

public class TimeReportCallback extends VerticalPanel {

	ReportGeneratorAsync reportverwaltung = ClientsideSettings.getReportGenerator();

	public TimeReportCallback( Team team, Date firstDate, Date lastDate) {

		reportverwaltung.createTeamStatisticReport(team, firstDate, lastDate, new TeamReport());

	}

	class TeamReport implements AsyncCallback<TeamStatisticReport> {

		public void onFailure(Throwable caught) {
			Window.alert("Fehler");
		}

		public void onSuccess(TeamStatisticReport result) {
			HTMLReportWriter hrw = new HTMLReportWriter();
			hrw.process(result);
			String reportText = hrw.getReportText();

			clear();
			add(new HTML(reportText));
			Window.alert("Success");
		}
	}

}
