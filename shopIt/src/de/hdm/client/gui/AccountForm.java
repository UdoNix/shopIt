package de.hdm.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AccountForm extends HorizontalPanel {
	
//	AccountForm account = new AccountForm();
//	
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
	
	public void loadAccount(){
		
	
	editAccountBtn = new Button("Account bearbeiten");
	deleteButton = new Button("Account löschen");
	abortDeletionButton = new Button("Abbrechen");
	
	hPanel.add(editAccountBtn);
	hPanel.add(deleteButton);
	contentPanel.add(abortDeletionButton);
	contentPanel.add(name);
	contentPanel.add(idLabel);
	contentPanel.add(creationTime);
	RootPanel.get("nav").add(hPanel);
	RootPanel.get("main").add(contentPanel);
	
}
	
}
