package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Team;

/*
 * Die Klasse <code>LeaveGroupDialogBox</code> dient der Darstellung einer Dialogbox, die
 * dem Nutzer das Verlassen einer Gruppe ermöglicht
 * 
 * @author dibasegmen
 *
*/

public class LeaveGroupDialogBox extends DialogBox {
	
	//Entsprechendes Interface wird noch angepasst
	
	private ShopITAdministrationAsync listenverwaltung = ClientsideSettings.getShopItAdministration();
	
	//private Person p = currentPerson.getPerson();
	private Team selectedGroup = null;
	
	private VerticalPanel contentPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	
	private Button confirmBtn = new Button ("Gruppe verlassen");
	private Button cancelBtn = new Button("Abbrechen");
	private Label confirmLabel = new Label ("Möchten Sie die Gruppe wirklich verlassen?");

	
	public LeaveGroupDialogBox(){
		
		//Aktivierung des Glass Backgrounds einer DialogBox
		
		this.setGlassEnabled(true);
		
		cancelBtn.setStylePrimaryName("cancelButton");
		confirmBtn.setStylePrimaryName("confirmButton");
		
		cancelBtn.addClickHandler(new CancelClickHandler());
		confirmBtn.addClickHandler(new ConfirmClickHandler());
		
		buttonPanel.add(confirmBtn);
		buttonPanel.add(cancelBtn);
		
		
		contentPanel.add(confirmBtn);
		contentPanel.add(cancelBtn);
		contentPanel.add(confirmLabel);
		contentPanel.add(buttonPanel);
		
		this.add(contentPanel);
		this.center();
		
	}

	
	//Anpassung in TreeViewModel fehlt noch!!!
	
	/*
	 * Anlegen der benötigten Clickhandler
	 */
	
	//CancelClickHandler, der das Abbrechen des Vorgangs ermöglicht.
	
	private class CancelClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent ev){
			LeaveGroupDialogBox.this.hide();
			
		}
		
	}
	
	
	// Bestätigungsklick zur Ausführung des Vorgangs
	
	private class ConfirmClickHandler implements ClickHandler {
		
		public void onClick(ClickEvent ev) {
			if(selectedGroup != null) {
				
				//Funktion fehlt noch
				
			} else {
				
				Window.alert("Es wurde keine Gruppe ausgewählt.");
				
					}
		}
	}
	

	

}
