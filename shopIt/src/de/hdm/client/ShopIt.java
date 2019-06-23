package de.hdm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.gui.AccountForm;
import de.hdm.client.gui.AddPersonToGroupForm;
import de.hdm.client.gui.ListForm;
import de.hdm.client.gui.ListItemForm;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ShopIt implements EntryPoint{
	

ListItemForm liForm = new ListItemForm();

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
VerticalPanel vPanel = new VerticalPanel();

vPanel.add(liForm);

			
	RootPanel.get().add(vPanel);
	
}
}
