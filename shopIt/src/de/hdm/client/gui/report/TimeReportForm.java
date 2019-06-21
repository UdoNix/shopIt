package de.hdm.client.gui.report;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.Format;

public class TimeReportForm extends HorizontalPanel{

	private Button startButton = new Button("Report starten");
	private Label startDateLabel = new Label("Startdatum");
	private DateBox startDateBox = new DateBox();
	private Label endDateLabel = new Label("Enddatum");
	private DateBox endDateBox = new DateBox();

	private DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
	private VerticalPanel vpanel = new VerticalPanel();
	private Grid timeGrid = new Grid(3,2);

	
	public TimeReportForm(){
		
		timeGrid.setWidget(0, 0, startDateLabel);
		timeGrid.setWidget(0, 1, startDateBox);
		timeGrid.setWidget(1, 0, endDateLabel);
		timeGrid.setWidget(1, 1, endDateBox);
		timeGrid.setWidget(2, 0, startButton);
		
		startDateBox.setFormat((Format) dateTimeFormat);
		startDateBox.addValueChangeHandler(new StartDate());
		endDateBox.setFormat((Format) dateTimeFormat);
		endDateBox.addValueChangeHandler(new EndDate());
		
		startButton.addClickHandler(new StartReportClickHandler());
		
		this.add(timeGrid);
		
	}
	
	class StartReportClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event){
			vpanel.clear();
			vpanel.add(new TimeReportCallback(startDateBox.getValue(), endDateBox.getValue()));
			RootPanel.get("content").add(vpanel);
			
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
