package de.hdm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.gui.CellTreeViewModel;
import de.hdm.client.gui.EditorAdminView;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
//Autor
public class Editor implements EntryPoint {
	
	private EditorAdminView editorAdminView;
	
	public void onModuleLoad() {
		editorAdminView = new EditorAdminView();
		
		editorAdminView.loadEditor();
	
	}
	
	public Editor getEditor() {
		return this;
	}
}


