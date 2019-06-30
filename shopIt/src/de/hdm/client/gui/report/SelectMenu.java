package de.hdm.client.gui.report;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Start-Seite des ReportGenerators.
 * Hier kann der Anwender zwischen den verschiedenen Reports auswählen oder 
 * zurück zum Editor gelangen.
 * @author InesWerner
 */
public class SelectMenu extends VerticalPanel {

	/**
	 * Buttons für die 3 Reports und einen Button um zurück zu 
	 * dem Editor zu kommen
	 */
	private Button shopButton = new Button("Shop");
	private Button timeButton = new Button("Time");
	private Button shopTimeButton = new Button("Shop and Time");
	private Button zurueckButton = new Button("Zurück");
	
	public SelectMenu(){
		
		shopButton.addClickHandler(new ShopReportClickHandler());
		timeButton.addClickHandler(new TimeReportClickHandler());
		shopTimeButton.addClickHandler(new ShopTimeReportClickHandler());
		zurueckButton.addClickHandler(new ZurueckClickHandler());

		//Widgets werden dem Panel hinzugefügt
		this.add(shopButton);
		this.add(timeButton);
		this.add(shopTimeButton);
		this.add(zurueckButton);
	}
	
	//ClickHandler zum Report, der den Händler berücksichtigt.
	class ShopReportClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event){
			RootPanel.get("content").clear();
			RootPanel.get("content").add(new ShopReportForm());
		}
	}
	
	//ClickHandler zum Report, der den Zeitraum berücksichtigt.
	class TimeReportClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event){
			RootPanel.get("content").clear();
			RootPanel.get("content").add(new TimeReportForm());
		}
	}
	
	//ClickHandler zum Report, der den Händler und den Zeitraum berücksichtigt.
	class ShopTimeReportClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event){
			RootPanel.get("content").clear();
			RootPanel.get("content").add(new ShopTimeReportForm());
		}
	}
	
	class ZurueckClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event){
			Window.Location.assign(GWT.getHostPageBaseURL() + "");
		}
	}
}



