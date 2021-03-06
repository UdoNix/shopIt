package de.hdm.client.gui.report;

import java.util.Date;


import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ReportGeneratorAsync;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;
import de.hdm.shared.report.AllArticlesOfShopReport;
import de.hdm.shared.report.HTMLReportWriter;
import de.hdm.shared.report.ShopStatisticReport;
import de.hdm.shared.report.TeamStatisticReport;

/**
 * Die Klasse <code>ShopReportCallback</code> dient dem Report Shop Ohne Zeit-Callback
 * 
 * @author dibasegmen
 *
 */

public class ShopReportCallback extends VerticalPanel {

	ReportGeneratorAsync reportverwaltung = ClientsideSettings.getReportGenerator();

	public ShopReportCallback(Shop shop, Team team) {

		reportverwaltung.createAllArticlesOfShopReport(shop, team, new AsyncCallback<ShopStatisticReport>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Es ist ein Fehler aufgetreten. ");
			}

			@Override
			public void onSuccess(ShopStatisticReport result) {
				HTMLReportWriter hrw = new HTMLReportWriter();
				hrw.process(result);
				String reportText = hrw.getReportText();

				clear();
				add(new HTML(reportText));
				Window.alert("Die Operation war erfolgreich!");
			}
		});

	}

}
