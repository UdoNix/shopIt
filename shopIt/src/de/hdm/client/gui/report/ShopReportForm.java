package de.hdm.client.gui.report;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ReportGeneratorAsync;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;
import de.hdm.shared.report.AllArticlesOfShopReport;
import de.hdm.shared.report.ShopStatisticReport;

/**
 * Die Klasse <code>ShopReportForm</code> dient derAuflistung aller Artikel eines Shops, die in eine Liste eingetragen wurden.
 * 
 * @author ilonabrinkmann
 * @author dibasegmen
 *
 */
public class ShopReportForm extends VerticalPanel {

	/**
	 *  Erstellung der GUI-Elemente
	 */
	private Button startButton = new Button("Report starten");
	private Label shopLabel = new Label("Shop: ");
	private ListBox listBox = new ListBox();
	private Label teamLabel = new Label("Gruppe: ");
	private ListBox listBoxTeam = new ListBox();
	private FlexTable flex = new FlexTable();

	private ReportGeneratorAsync reportVerwaltung = ClientsideSettings.getReportGenerator();
	private ShopITAdministrationAsync verwaltung = ClientsideSettings.getShopItAdministration();

	private Vector<Shop> shops;
	private Vector<Team> teams;
	private ReportLayout reportLayout;

	public ShopReportForm(ReportLayout reportLayout) {

		this.reportLayout = reportLayout;
		flex.setWidget(0, 0, shopLabel);
		flex.setWidget(0, 1, listBox);
		flex.setWidget(1, 0, teamLabel);
		flex.setWidget(1, 1, listBoxTeam);
		flex.setWidget(2, 1, startButton);

		shopLabel.setStylePrimaryName("label-style");
		teamLabel.setStylePrimaryName("label-style");
		startButton.setStylePrimaryName("button-style");
		startButton.addClickHandler(new StartReportClickHandler());
		verwaltung.getAllShops(new GetAllShopsCallback());
		verwaltung.getAllTeams(new GetAllTeamsCallback());

		this.add(flex);

	}

	/**
	 * Callback zum Abruf aller Shops. Vector<Shop> wird zu ListBox hinzugefügt.
	 */
	private class GetAllShopsCallback implements AsyncCallback<Vector<Shop>> {

		public void onFailure(Throwable caught) {
			Window.alert("Fehler - der Abruf der Shops hat nicht funktioniert: " + caught.getMessage());
		}

		public void onSuccess(Vector<Shop> results) {
			shops = results;
			for (Shop shop : results) {
				listBox.addItem(shop.getName(), "" + shop.getId());
			}
		}
	}

	private class GetAllTeamsCallback implements AsyncCallback<Vector<Team>> {

		public void onFailure(Throwable caught) {
			Window.alert("Fehler - der Abruf des Teams hat nicht funktioniert: " + caught.getMessage());
		}

		public void onSuccess(Vector<Team> results) {
			teams = results;
			for (Team team : results) {
				listBoxTeam.addItem(team.getName(), "" + team.getId());
			}
		}
	}

	private class StartReportClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			if (listBox.getSelectedValue().equals("")) {
				Window.alert("Bitte einen Shop auswählen :)");
			} else if (listBoxTeam.getSelectedValue().equals("")) {
				Window.alert("Bitte ein Team auswählen :)");
			} else {
				for (Shop shop : shops) {
					for (Team team : teams) {
						if ((shop.getId() + "").equals(listBox.getSelectedValue())) {
							if ((team.getId() + "").equals(listBoxTeam.getSelectedValue())) {
								reportLayout.setThree(new ShopReportCallback(shop, team));
							}
						}
					}
				}
			}
		}
	}
}
