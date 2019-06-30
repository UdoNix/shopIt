package de.hdm.client.gui.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.Format;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ReportGeneratorAsync;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Team;

public class TimeReportForm extends HorizontalPanel {

	private Button startButton = new Button("Report starten");
	private Label startDateLabel = new Label("Startdatum");
	private final DateBox startDateBox = new DateBox();
	private Label endDateLabel = new Label("Enddatum");
	private final DateBox endDateBox = new DateBox();
	private ListBox teamListBox = new ListBox();

	private ReportGeneratorAsync reportVerwaltung = ClientsideSettings.getReportGenerator();
	private ShopITAdministrationAsync verwaltung = ClientsideSettings.getShopItAdministration();
	private DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
	private VerticalPanel vpanel = new VerticalPanel();
	private Grid timeGrid = new Grid(5, 2);
	private Vector<Team> teams;
	private ReportLayout reportLayout;

	public TimeReportForm(ReportLayout reportLayout) {

		this.reportLayout = reportLayout;
		timeGrid.setWidget(1, 0, startDateLabel);
		timeGrid.setWidget(1, 1, startDateBox);
		timeGrid.setWidget(2, 0, endDateLabel);
		timeGrid.setWidget(2, 1, endDateBox);
		timeGrid.setWidget(3, 0, teamListBox);
		timeGrid.setWidget(4, 0, startButton);

		startDateBox.setFormat(new DateBox.DefaultFormat(dateTimeFormat));
		endDateBox.setFormat(new DateBox.DefaultFormat(dateTimeFormat));

		startButton.addClickHandler(new StartReportClickHandler());
		verwaltung.getAllTeams(new GetAllTeamsCallback());

		this.add(timeGrid);

	}

	private class GetAllTeamsCallback implements AsyncCallback<Vector<Team>> {

		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Abrufen der Teams: " + caught.getMessage());
		}

		public void onSuccess(Vector<Team> results) {

			teams = results;
			for (Team team : results) {
				teamListBox.addItem(team.getName(), team.getId() + "");
			}
		}
	}

	class StartReportClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			if (teamListBox.getSelectedValue().equals("")) {
				Window.alert("Bitte wählen Sie eine Gruppe aus :)");
			} else {
				for (Team team : teams) {
					if (String.valueOf(team.getId()).equals(teamListBox.getSelectedValue())) {
						reportLayout.setThree(new TimeReportCallback(team, startDateBox.getValue(), endDateBox.getValue()));
					}
				}
			}
		}
	}

}
