package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;

import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;



public class ShopView extends VerticalPanel{
	//@emily kretzschmar

	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
	
	
	private Shop selectedShop = null;
	
public Shop getSelctedShop() {
	return selectedShop;
}
public void setSelctedShop(Shop selctedShop) {
	this.selectedShop = selctedShop;
}

	

		
	Shop shopToDisplay = null;
	
	TextBox nameTextBox = new TextBox();
	TextBox postalCodeTextBox = new TextBox();
	TextBox cityTextBox = new TextBox();
	TextBox streetTextBox = new TextBox();
	
	 Button deleteButton = new Button("Shop löschen");
	 Button newButton = new Button("Als neuer Shop speichern");
	 Button saveButton = new Button ("Speichern");

	 
	 public void onLoad() {
		 
	 Grid shopGrid = new Grid (7,2);
	 this.add(shopGrid);
		
	 deleteButton.addClickHandler(new DeleteClickHandler());
	 deleteButton.setEnabled(false);
     shopGrid.setWidget(4, 1, deleteButton);
     
     Label name = new Label ("Name:");
     shopGrid.setWidget(1, 0, name);
	 shopGrid.setWidget(1,1, nameTextBox );
		
	 Label plz = new Label ("Postleizahl:");
	 shopGrid.setWidget (3,1, plz);
	 shopGrid.setWidget(3,2,postalCodeTextBox);
	 
	 Label city = new Label ("City:");
	 shopGrid.setWidget (4,1, city);
	 shopGrid.setWidget(4,2, cityTextBox);
	 
	 Label street = new Label ("Street:");
	 shopGrid.setWidget (2,1, street);
	 shopGrid.setWidget(2,2, streetTextBox);
	 
	 newButton.addClickHandler(new NewClickHandler());
	 newButton.setEnabled(false);
     shopGrid.setWidget(5, 1, newButton);
     
 	saveButton.addClickHandler(new ChangeClickHandler());
	saveButton.setEnabled(true);
	shopGrid.setWidget(6,1,saveButton);
	
	deleteButton.addClickHandler(new DeleteClickHandler());
	deleteButton.setEnabled(false);
	shopGrid.setWidget(7, 1, deleteButton);
	 
	 }
		private class DeleteClickHandler implements ClickHandler {

			
			public void onClick(ClickEvent event) {
				if (selectedShop == null) {
					Window.alert("kein Shop ausgewählt");
				} else {
					listenVerwaltung.delete().getShopId();
							new deleteShopCallback(
									shopToDisplay);
				}
				
			}
		}
		class deleteShopCallback implements AsyncCallback<Void> {

			Shop shop = null;

			deleteShopCallback(Shop s) {
				shop = s;
			}

			
			public void onFailure(Throwable caught) {
				Window.alert("Das Löschen des Shops ist fehlgeschlagen!");
			}

			
			public void onSuccess(Void result) {
				if (shop != null) {
					setSelectedShop(null);
					CellTreeViewModel.delete(shop);
				}
			}
		}
		
		
		private class ChangeClickHandler implements ClickHandler {
			
			public void onClick(ClickEvent event) {
				if (shopToDisplay != null) {
					shopToDisplay.setName(nameTextBox.getText());
					shopToDisplay.setStreet(postalCodeTextBox.getText());
					shopToDisplay.setCity(cityTextBox.getText());
					shopToDisplay.setPostalCode(streetTextBox.getText());
					
					listenVerwaltung.save(shopToDisplay, new saveCallback());
					
				} else {
					Window.alert("Kein Shop ausgewählt!");
				}
			}
		}

		private class saveCallback implements AsyncCallback<Void> {
			
			public void onFailure(Throwable caught) {
				Window.alert("Die Änderung ist fehlgeschlagen!");
			}

			
			public void onSuccess(Void result) {
				// Die Änderung wird zum Kunden- und Kontenbaum propagiert.
				CellTreeViewModel.updateShop(shopToDisplay);
			}
		}
		

		private class NewClickHandler implements ClickHandler {

			
			public void onClick(ClickEvent event) {
				String name = nameTextBox.getText();
				String postalCode = postalCodeTextBox.getText();
				String city = cityTextBox.getText();
				String street = streetTextBox.getText();
				
				
				listenVerwaltung.createShop(name,postalCode, city, street, 
						new createShopCallback());
				
				
				class createShopCallback implements AsyncCallback<Team> {

					
					public void onFailure(Throwable caught) {
						Window.alert("Das Anlegen eines Shops ist fehlgeschlagen!");
					}

				
					public void onSuccess(Shop shop) {
						if (shop != null) {
							
							CellTreeViewModel.addShop(shop);
						}
					}
				}
			}
		}
		
}


			
	
	
	

	
	
	
	

