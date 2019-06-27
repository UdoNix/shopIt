package de.hdm.client.gui.report;

import java.util.Date;
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
import de.hdm.shared.report.AllArticlesOfShopReport;
import de.hdm.shared.report.HTMLReportWriter;
import de.hdm.shared.report.ShopStatisticReport;
import de.hdm.shared.report.TeamStatisticReport;

public class ShopReportCallback extends VerticalPanel {

	ReportGeneratorAsync reportverwaltung = ClientsideSettings.getReportGenerator();

	public ShopReportCallback(Shop shop) {

		reportverwaltung.createAllArticlesOfShopReport(shop, new AsyncCallback<AllArticlesOfShopReport>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler");
			}

			@Override
			public void onSuccess(AllArticlesOfShopReport result) {
				HTMLReportWriter hrw = new HTMLReportWriter();
//				hrw.process(result);
//				String reportText = hrw.getReportText();
//
//				clear();
//				add(new Label(reportText));
				Window.alert("Success");
			}
		});

	}

}
