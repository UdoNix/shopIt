package de.hdm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.gui.CellTreeViewModel;
import de.hdm.client.gui.Navigation;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Editor implements EntryPoint {
	
	private CellTreeViewModel ctvModel;
	private VerticalPanel menu;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		ctvModel = new CellTreeViewModel();
		
		menu = new VerticalPanel();
		
		menu.add(ctvModel.getStackPanelMenu());
		
		RootPanel.get("nav").add(menu);
	
		
	}
}
