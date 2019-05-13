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

public class searchField extends HorizontalPanel {

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
	
	public searchField() {

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

		
		//searchButton.addClickHandler(new ShowPinnwandClickHandler());
		
		super.onLoad();
	}

	/**
	 * Nested Class für das Anzeigen des Nutzers
	 *
	 */
//	class SearchCallback implements AsyncCallback<Vector<Nutzer>> {
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Fehler beim Ausführen der Suche: " + caught.getMessage());
//
//		}
//
//		@Override
//		public void onSuccess(Vector<Nutzer> result) {
//
//			String searchResultString = new String();
//			for (int i = 0; i < result.size(); i++) {
//
//				searchResultString = "" + result.elementAt(i).getNickname();
//				oracle.add(searchResultString);
//
//			}
//
//		}
//
//	}

	/**
	 * Nested Class für das Anzeigen des Profils
	 *
	 */

//	class ShowGroupClickHandler implements ClickHandler {
//
//		public void onClick(ClickEvent event) {
//
//			listenVerwaltung.getAllMembersOfGroup(txtBox.getText(), new ShowGroupCallback());
//
//		}

	}
	
	/**
	 * Erstellung der Pinnwand des ausgewählten Profils
	 *
	 */

//	class ShowGroupCallback implements AsyncCallback<Group> {
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Fehler beim Anzeigen der Ergebnisse " + caught.getMessage());
//
//		}
//
//		@Override
//		public void onSuccess(Group result) {
//			// TODO Auto-generated method stub
//			
//		}
//
//
//		}
//
//		//public void onSuccess(Group result) {
//			/*
//			 * Wir erwarten diesen Ausgang, wollen aber keine Notifikation
//			 * ausgeben.
//			 */
//			//GroupForm group = new GroupForm(result.getId())
////			RootPanel.get("InhaltDiv").clear();
////			RootPanel.get("InhaltDiv").add(group);
////			txtBox.refreshSuggestionList();
//			
//		}
//
//	
//	/**
//	 * Löschen des Anzeigetextes innerhlab der Textbox
//	 */
//
//	class SuggestClickhandler implements ClickHandler {
//
//		@Override
//		public void onClick(ClickEvent event) {
//			SuggestBox.setValue("");
//
//		}
//
//	}
