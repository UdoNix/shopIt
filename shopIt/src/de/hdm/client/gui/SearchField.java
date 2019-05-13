package de.hdm.client.gui;
import de.hdm.client.ClientsideSettings;


import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.shared.EditorServiceAsync;
import de.hdm.shared.bo.Group;

/**
 * Klasse um gesuchte Gruppen, Listen oder Artikel über das Suchfeld aufzurufen.
 * 
 * @author Diba Segmen
 *
 */

public class SearchField extends HorizontalPanel {

	/**
	 * Erzeugen eines EditorService-Objekts um eine Applikationsverwaltung zu
	 * initialisieren.
	 */

	EditorServiceAsync listenVerwaltung = ClientsideSettings.getEditorServiceAsync();

	/**
	 * Instanziierung der GUI Elemente
	 */

	private HorizontalPanel searchField = new HorizontalPanel();
	private Button searchButton = new Button("Ergebnisse anzeigen");

	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle(); //Googeln!!
	private TextBox suggestTextBox = new TextBox(); //auch!
	private SuggestBox txtBox = new SuggestBox(oracle, suggestTextBox);//auch!
	
	public SearchField() {

		this.add(searchField);
		this.add(txtBox);

		suggestTextBox.setValue("Suchbegriff eingeben ...");
		txtBox.setStyleName("suchBox");

	//	suggestTextBox.addClickHandler(new SuggestClickhandler());

		this.addStyleName("suchleistePanel");
		this.add(searchField);

		searchField.add(searchButton);
		
		/**
		 * Die Methode dient dem Zurückgeben der resultierenden Suchergebnisse
		 */
		//listenVerwaltung.getAllNutzer(new SearchCallback()); getPersonsOf(Group g)
		//listenVerwaltung.getAllResults(new SearchCallback());

		
		
		
		super.onLoad();
	}
}
	