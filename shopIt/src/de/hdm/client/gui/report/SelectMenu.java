package de.hdm.client.gui.report;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Start-Seite des ReportGenerators. Hier kann der Anwender zwischen den
 * verschiedenen Reports auswählen oder zurück zum Editor gelangen.
 * 
 * @author InesWerner
 */
public class SelectMenu extends VerticalPanel {

	/**
	 * Buttons für die 3 Reports und einen Button um zurück zu dem Editor zu
	 * kommen
	 */
	private Button shopButton = new Button("Shop");
	private Button timeButton = new Button("Time");
	private Button shopTimeButton = new Button("Shop and Time");
	private Button zurueckButton = new Button("Zurück");
	private ReportLayout reportLayout;

	public SelectMenu(ReportLayout reportLayout) {

		this.reportLayout = reportLayout;
		shopButton.setStylePrimaryName("button-style");
		shopButton.addClickHandler(new ShopReportClickHandler());
		timeButton.setStylePrimaryName("button-style");
		timeButton.addClickHandler(new TimeReportClickHandler());
		shopTimeButton.setStylePrimaryName("button-style");
		shopTimeButton.addClickHandler(new ShopTimeReportClickHandler());
		zurueckButton.setStylePrimaryName("button-style");
		zurueckButton.addClickHandler(new ZurueckClickHandler());

		// Widgets werden dem Panel hinzugefügt
		this.add(shopButton);
		this.add(timeButton);
		this.add(shopTimeButton);
		this.add(zurueckButton);
	}

	// ClickHandler zum Report, der den Händler berücksichtigt.
	class ShopReportClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			reportLayout.setTwo(new ShopReportForm(reportLayout));
		}
	}

	// ClickHandler zum Report, der den Zeitraum berücksichtigt.
	class TimeReportClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			reportLayout.setTwo(new TimeReportForm(reportLayout));
		}
	}

	// ClickHandler zum Report, der den Händler und den Zeitraum berücksichtigt.
	class ShopTimeReportClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			reportLayout.setTwo(new ShopTimeReportForm(reportLayout));
		}
	}

	class ZurueckClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			Window.Location.assign(GWT.getHostPageBaseURL() + "");
		}
	}
}
