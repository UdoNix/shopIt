package de.hdm.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AccountForm extends Form {
	
//	AccountForm account = new AccountForm();
//	
//	Account accountToDisplay = null; //Person?!
//	
	/**
	 * Erstellung verschiedener Panels
	 */
	private VerticalPanel contentPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();

	/**
	 * Erstellung benötigter GUI-Elemente
	 */
	
	private Label name = new Label();
	private Label idLabel = new Label("Konto: ");
	private Label creationTime = new Label();
	
	
	private Button editAccountBtn, deleteButton, abortDeletionButton;
	
	//private Button editAccountBtn = new Button("Gruppe bearbeiten");
	//private Button deleteButton = new Button("Account löschen");
	//private Button abortDeletionButton = new Button("Abbrechen");
	
	public void loadAccount(){
		
		editAccountBtn = new Button("Account bearbeiten");
		deleteButton = new Button("Account löschen");
		abortDeletionButton = new Button("Abbrechen");
		
		contentPanel.add(editAccountBtn);
		contentPanel.add(deleteButton);
		contentPanel.add(abortDeletionButton);
		contentPanel.add(name);
		contentPanel.add(idLabel);
		contentPanel.add(creationTime);
		RootPanel.get().add(contentPanel);
		
	}
	

	
}
