package de.hdm.client.gui.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
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
import de.hdm.shared.bo.Team;

public class TimeReportForm extends HorizontalPanel{

	private Button startButton = new Button("Report starten");
	private Label startDateLabel = new Label("Startdatum");
	private DateBox startDateBox = new DateBox();
	private Label endDateLabel = new Label("Enddatum");
	private DateBox endDateBox = new DateBox();
	private ListBox teamListBox = new ListBox();

	private ReportGeneratorAsync reportVerwaltung = ClientsideSettings.getReportGenerator();
	private DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
	private VerticalPanel vpanel = new VerticalPanel();
	private Grid timeGrid = new Grid(4,2);

	public TimeReportForm(){
		
		timeGrid.setWidget(0, 0, startDateLabel);
		timeGrid.setWidget(0, 1, startDateBox);
		timeGrid.setWidget(1, 0, endDateLabel);
		timeGrid.setWidget(1, 1, endDateBox);
		timeGrid.setWidget(2, 0, teamListBox);
		timeGrid.setWidget(3, 0, startButton);
		
		startDateBox.setFormat((Format) dateTimeFormat);
		startDateBox.addValueChangeHandler(new StartDate());
		endDateBox.setFormat((Format) dateTimeFormat);
		endDateBox.addValueChangeHandler(new EndDate());
		
		startButton.addClickHandler(new StartReportClickHandler());
//		reportVerwaltung.getAllTeams(new GetAllTeamsCallback());
		
		this.add(timeGrid);
	
	}
	
	private class GetAllTeamsCallback implements AsyncCallback<Vector<Team>>{
		
		public void onFailure(Throwable caught){
			Window.alert("Fehler beim Abrufen der Teams: "+ caught.getMessage());
		}
		
		public void onSuccess(Vector<Team> results){
			for(Team team : results){
				teamListBox.addItem(team.getName());
			}
		}
	}
	
	class StartReportClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event){
			if(teamListBox.getSelectedValue().equals("")){
				Window.alert("Bitte wählen Sie eine Gruppe aus :)");
			}
			else{
			vpanel.clear();
			vpanel.add(new TimeReportCallback(teamListBox.getSelectedValue(), startDateBox.getValue(), endDateBox.getValue()));
			RootPanel.get("contentReport").add(vpanel);
			}
		}
	}
	
	
	//Implementierung des ValueChangeHandler für das StartDatum des Report
	class StartDate implements ValueChangeHandler<Date>{
		
		public void onValueChange(ValueChangeEvent<Date> event){
			if(startDateBox.getValue().after(endDateBox.getValue())){
				Window.alert("Das Startdatum muss vor dem Enddatum liegen. Bitte erneut versuchen!");
				startDateBox.setValue(null);
			}
		}
	}

	
	// Implementierung des ValueChangeHandler für das EndDatum des Reports
	class EndDate implements ValueChangeHandler<Date>{

		public void onValueChange(ValueChangeEvent<Date> event){
			if(startDateBox.getValue().after(endDateBox.getValue())){
				Window.alert("Das Startdatum muss vor dem Enddatum liegen. Bitte erneut versuchen!");
				startDateBox.setValue(null);
			}
		}
	}
}
