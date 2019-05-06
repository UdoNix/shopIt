package de.hdm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
		
		HorizontalPanel hPanel = new HorizontalPanel();
		
		Button btn1 = new Button("horizontal");
		hPanel.add(btn1);
		
		Button btn2 = new Button("buton 2");
		
		hPanel.add(btn2);
		vPanel.add(hPanel);
		
		HorizontalPanel hPanel2 = new HorizontalPanel();
		
		Button btn3 = new Button("horizontal");
		
		hPanel2.add(btn3);
		
		Button btn4 = new Button("buton 2");
		
		
		hPanel2.add(btn4);
		vPanel.add(hPanel2);
		//vPanel.add(myLbl);
		
		Button btn = new Button("Press");
		vPanel.add(btn);
		
		RootPanel.get().add(vPanel);
	}
}
