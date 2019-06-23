package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Person;

/**
 * Darstellung der Accountinformationen des Kunden
 * 
 * @author InesWerner
 */

public class PersonForm extends VerticalPanel{

	ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
	CellTreeViewModel ctvm = null;
	Person personToDisplay = null;
	
	/**
	 * Widgets mit variablen Inhalten werden als Attribut festgelegt
	 */
	Label firstNameLabel = new Label("Vorname");
	TextBox firstNameTextBox = new TextBox();
	Label lastNameLabel = new Label("Nachname");
	TextBox lastNameTextBox = new TextBox();
	Label idLabel = new Label("Kundennummer: ");
	Label creationTimeLabel = new Label("Mitglied seit: ");
	Button changeButton = new Button("Name bearbeiten");
	Button deleteButton = new Button("Account löschen");
	
	
	/**
	 * Widgets werden in einem Raster (Grid) angeordnet, dessen Größe sich aus dem Platzbedarf
	 * der enthaltenen Wigets bestimmt.
	 */
	public void onLoad(){
		
		Grid personGrid = new Grid(5,2);
		this.add(personGrid);
		
		personGrid.setWidget(0, 0, idLabel);
		personGrid.setWidget(1, 0, creationTimeLabel);
		personGrid.setWidget(2, 0, firstNameLabel);
		personGrid.setWidget(2, 1, firstNameTextBox);
		personGrid.setWidget(3, 0, lastNameLabel);
		personGrid.setWidget(3, 1, lastNameTextBox);
		
		HorizontalPanel personButtonsPanel = new HorizontalPanel();
		personGrid.setWidget(4, 0, personButtonsPanel);
		
		changeButton.addClickHandler(new ChangeClickHandler());
		changeButton.setEnabled(true);
		personButtonsPanel.add(changeButton);
		
		deleteButton.addClickHandler(new DeleteClickHandler());
		deleteButton.setEnabled(false);
		personButtonsPanel.add(deleteButton);
		
		
		/**
		 * ClickHandler und abhängige AsyncCallback Klassen.
		 * 
		 */
		
		class DeleteClickHandler implements ClickHandler{
			
			public void onClick(ClickEvent event){
			if(personToDisplay == null){
				Window.alert("Fehlgeschlagen");
			}
			else{
				listenVerwaltung.delete(personToDisplay
						, new deletePersonChallback(personToDisplay));
			}
		}
	}
		
		class deletePersonCallback implements AsyncCallback<Void>{
			
			private Person person = null;
			
			deletePersonCallback(Person p){
				person = p;
			}
			
			public void onFailure(Throwable caught){
				Window.alert("Das Löschen des Accounts ist fehlgeschlagen.");
			}
			
			public void onSuccess(Void result){
				if(person != null){
					setSelected(null);
					ctvm.removePerson(person);
				}
			}
			
		}
		
		class ChangeClickHandler implements ClickHandler{
			
			public void onClick(ClickEvent event){
				if(personToDisplay != null){
					personToDisplay.setFirstName(firstNameTextBox.getText());
					personToDisplay.setLastName(lastNameTextBox.getText());
					personToDisplay.save(personToDisplay , new SaveCallBack());
				}
				else{
					Window.alert("Fehlgeschlagen.");
				}
			}
		}
		
		class SaveCallback implements AsyncCallback<Void>{
			public void onFailure(Throwable caught){
				Window.alert("Leider hat die Namensänderung nicht funktioniert");
			}
			
			//Die Namensänderung wird zum Personenbaum  weitergeleitet.
			public void onSuccess(Void result){
				ctvm.updatePerson(personToDisplay);
			}
		}	
	}
	
	void setCtvm(CellTreeViewModel ctvm){
		this.ctvm = ctvm;
	}
	
	void setSelected(Person p){
		if(p != null){
			personToDisplay = p;
			deleteButton.setEnabled(true);
			idLabel.setText("Kundennummer: " + Integer.toString(personToDisplay.getId()));
			firstNameTextBox.setText(personToDisplay.getFirstName());
			lastNameTextBox.setText(personToDisplay.getLastName());
		}
		else{
			idLabel.setText("Kundennummer: ");
			firstNameTextBox.setText("");
			lastNameTextBox.setText("");
			deleteButton.setEnabled(false);
		}
	}

}