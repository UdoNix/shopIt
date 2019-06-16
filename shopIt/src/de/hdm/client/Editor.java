package de.hdm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.gui.CellTreeViewModel;
import de.hdm.client.gui.EditorAdminView;
import de.hdm.client.gui.TestSite;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Editor implements EntryPoint {
	
	private TestSite Test;
	
	public void onModuleLoad() {
		Test = new TestSite();
		
		Test.loadEditor();
	
	}
	
	public Editor getEditor() {
		return this;
	}
}


