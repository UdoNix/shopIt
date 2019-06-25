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
import de.hdm.shared.bo.Shop;

public class ShopReportForm extends VerticalPanel{

	//Erstellung der GUI-Elemente
	private Button startButton = new Button("Report starten");
	private Label shopLabel = new Label("Shop: ");
	private ListBox listBox = new ListBox();
	private FlexTable flex = new FlexTable();
	
	private ReportGeneratorAsync reportVerwaltung = ClientsideSettings.getReportGenerator();
	
	public ShopReportForm(){
		
		flex.setWidget(0, 0, shopLabel);
		flex.setWidget(0, 1, listBox);
		flex.setWidget(1, 1, startButton);
		
		startButton.addClickHandler(new StartReportClickHandler());
//		reportVerwaltung.getAllShops(new GetAllShopsCallback());
		
		this.add(flex);
		
	}

	/**
	 * Callback zum Abruf aller Shops. Vector<Shop> wird zu ListBox hinzugefügt.
	 */
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
				//flex.add(new ShopReportCallback(listBox.getSelectedValue()));	
				RootPanel.get("contentReport").add(flex);
			}
		}
	}
}
