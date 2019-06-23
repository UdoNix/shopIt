package de.hdm.client.gui.report;

import com.google.gwt.user.client.ui.HTMLResultPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ShopReportCallback extends HTMLResultPanel {

	public ShopReportCallback(String selectedValue) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

}

public class BeitragStatistikCallback extends HTMLResultPanel{
	ReportGeneratorAsync reportverwaltung= ClientsideSettings.getReportGenerator();
	
	public BeitragStatistikCallback(Date firstDate, Date lastDate) {

		reportverwaltung.createBeitragStatistikReport(firstDate, lastDate, new BeitragStatistik());
		
}

	
	class BeitragStatistik implements AsyncCallback<BeitragStatistikReport>{

		@Override
		public void onFailure(Throwable caught) {
	
			
		}

		@Override
		public void onSuccess(BeitragStatistikReport result) {
	
			int resultSize = result.getRows().size();
			if(resultSize == 0){
				Window.alert("Es wurden keine Daten geladen.");
			} else {
				HTMLReportWriter hrw = new HTMLReportWriter();
				hrw.process(result);
				append(hrw.getReportText());
				
			}
		}
		
	}

}
