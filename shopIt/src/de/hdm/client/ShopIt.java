package de.hdm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ShopIt implements EntryPoint {
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		/**
		 * The Vertical Panel is a gwt standard, it content vertically
		 */
		VerticalPanel vPanel = new VerticalPanel();
		
		Label myLbl = new Label("Hallo");
		
		vPanel.add(myLbl);
		
		Button btn = new Button("Press");
		vPanel.add(btn);
		
		RootPanel.get().add(vPanel);
	}
}
