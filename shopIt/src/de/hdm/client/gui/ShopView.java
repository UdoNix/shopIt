package de.hdm.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;

public class ShopView extends VerticalPanel{
	//@emily kretzschmar

	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
	
	TextBox nameTextBox = new TextBox();
	TextBox postalCodeTextBox = new TextBox();
	TextBox cityTextBox = new TextBox();
	TextBox street = new TextBox();
	
	 Button changeName = new Button("Als neuer Artikel speichern");
	 Button deleteShop = new Button("Team löschen");
	 Button change = new Button("Gruppe verlassen");
	 Button addButton = new Button ("Mitglieder hinzufügen");
	
	
	
	
	
	
	
	
	
}
