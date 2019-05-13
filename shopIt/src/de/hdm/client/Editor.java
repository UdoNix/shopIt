package de.hdm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.client.gui.Navigation;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Editor implements EntryPoint {
	
	private Navigation navbar;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		navbar = new Navigation();
		navbar.loadEditor();
	
		
	}
}
