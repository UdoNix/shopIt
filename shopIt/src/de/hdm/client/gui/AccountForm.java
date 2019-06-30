package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Die Klasse <code>AccountForm</code> stellt die Formklasse für den Account eines Users dar.
 * 
 * @author dibasegmen
 *
 */
@Deprecated
public class AccountForm extends HorizontalPanel {
	
//	AccountForm account = new AccountForm();
//	Account accountToDisplay = null; //Person?!
//	
	/**
	 * Erstellung verschiedener Panels
	 */
	private HorizontalPanel hPanel = new HorizontalPanel();
	private VerticalPanel contentPanel = new VerticalPanel();
		
	/**
	 * Erstellung benötigter GUI-Elemente
	 */
	
	private Label name = new Label("Name: ");
	private Label idLabel = new Label("Konto: ");
	private Label creationTime = new Label("Datum: ");
	
	private Button editAccountBtn = new Button("Account bearbeiten");
	private Button deleteButton = new Button("Account löschen");
	private Button abortDeletionButton = new Button("Abbrechen");
	
	public void onLoad(){
		
	
	editAccountBtn = new Button("Account bearbeiten");
	deleteButton = new Button("Account löschen");
	abortDeletionButton = new Button("Abbrechen");
	
	hPanel.add(editAccountBtn);
	hPanel.add(deleteButton);
	contentPanel.add(abortDeletionButton);
	contentPanel.add(name);
	contentPanel.add(idLabel);
	contentPanel.add(creationTime);
	RootPanel.get("main").clear();
	RootPanel.get("main").add(hPanel);
	RootPanel.get("main").add(contentPanel);
	
}
	public class EditAccountFormDialogBox {
		
	}
	
	/**
	 * Der User wird auf seine Accountinformationen weitergeleitet
	 */

	class eigenenAccountAnzeigen implements ClickHandler {

		public void onClick(ClickEvent event) {

			Window.Location.assign("/");

		}
	}
	
}
