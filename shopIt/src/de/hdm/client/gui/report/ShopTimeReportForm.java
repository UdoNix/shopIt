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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ReportGeneratorAsync;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;

public class ShopTimeReportForm extends HorizontalPanel{

	//Erstellung der GUI-Elemente
		private Button startButton = new Button("Report starten");
		private Label shopLabel = new Label("Shop: ");
		private ListBox listBox = new ListBox();
		private Label startDateLabel = new Label("Startdatum");
		private DateBox startDateBox = new DateBox();
		private Label endDateLabel = new Label("Enddatum");
		private DateBox endDateBox = new DateBox();
		private ListBox teamListBox = new ListBox();
		private FlexTable flex = new FlexTable();
		
		private ReportGeneratorAsync reportVerwaltung = ClientsideSettings.getReportGenerator();
		private DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
		private VerticalPanel vpanel = new VerticalPanel();
		
		public ShopTimeReportForm(){
			
			flex.setWidget(0, 0, shopLabel);
			flex.setWidget(0, 1, listBox);
			flex.setWidget(1, 1, startDateLabel);
			flex.setWidget(2, 0, startDateBox);
			flex.setWidget(2, 1, endDateLabel);
			flex.setWidget(3, 0, endDateBox);
			flex.setWidget(3, 1, teamListBox);
			flex.setWidget(1, 1, startButton);
			
			startButton.addClickHandler(new StartReportClickHandler());
			
			//Entsprechende Methode fehlt (!!!)
			reportVerwaltung.getAllShops(new GetAllShopsCallback());
			reportVerwaltung.getAllTeams(new GetAllTeamsCallback());
			
			this.add(flex);
			
		}

		/**
		 * Callback zum Abruf aller Shops. Vector<Shop> wird zu ListBox hinzugefügt.
		 */
		
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
		
		private class GetAllShopsCallback implements AsyncCallback<Vector<Shop>>{
			
			public void onFailure(Throwable caught){
				Window.alert("Fehler - der Abruf der Shops hat nicht funktioniert: "+ caught.getMessage());
			}
			
			public void onSuccess(Vector<Shop> results){
				for(Shop shop : results){
					listBox.addItem(shop.getName());
				}
			}
		}
		
		private class StartReportClickHandler implements ClickHandler{
			
			public void onClick(ClickEvent event){
				if(listBox.getSelectedValue().equals("")){
					Window.alert("Bitte einen Shop auswählen :)");
				}
				else{
					flex.clear();
					flex.add(new ShopTimeReportCallback(teamListBox.getSelectedValue(), listBox.getSelectedValue(), startDateBox.getValue(), endDateBox.getValue()));	
					RootPanel.get("contentReport").add(flex);
				}
			}
		}
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
