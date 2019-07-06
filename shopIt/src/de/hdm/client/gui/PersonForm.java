package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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

public class PersonForm extends VerticalPanel {

	ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
	Person personToDisplay = null;

	/**
	 * Widgets mit variablen Inhalten werden als Attribut festgelegt
	 */
	Label kontoLabel = new Label("Mein Konto");
	Label firstNameLabel = new Label("Vorname: ");
	TextBox firstNameTextBox = new TextBox();
	Label lastNameLabel = new Label("Nachname: ");
	TextBox lastNameTextBox = new TextBox();
	Label idLabel = new Label("Kundennummer: ");
	Label creationTimeLabel = new Label("");
	Button changeButton = new Button("Name bearbeiten");
	Button deleteButton = new Button("Konto löschen");

	/**
	 * Widgets werden in einem Raster (Grid) angeordnet, dessen Größe sich aus dem
	 * Platzbedarf der enthaltenen Wigets bestimmt.
	 */
	public void onLoad() {

		Grid personGrid = new Grid(7, 2);
		this.add(personGrid);

		personGrid.setWidget(0, 0, kontoLabel);
		personGrid.setWidget(2, 0, idLabel);
		personGrid.setWidget(3, 0, creationTimeLabel);
		personGrid.setWidget(4, 0, firstNameLabel);
		personGrid.setWidget(4, 1, firstNameTextBox);
		personGrid.setWidget(5, 0, lastNameLabel);
		personGrid.setWidget(5, 1, lastNameTextBox);

		HorizontalPanel personButtonsPanel = new HorizontalPanel();
		personGrid.setWidget(6, 0, personButtonsPanel);

		changeButton.addClickHandler(new ChangeClickHandler());
		personButtonsPanel.add(changeButton);

		deleteButton.addClickHandler(new DeleteClickHandler());
		personButtonsPanel.add(deleteButton);

		listenVerwaltung.getCurrentPerson(new AsyncCallback<Person>() {
			@Override
			public void onSuccess(Person result) {
				setSelected(result);
				creationTimeLabel.setText("Mitglied seit: " + DateTimeFormat.getFormat("yyyy-MM-dd").format(result.getCreationDate()));
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler");
			}
		});

	}

	void setSelected(Person p) {
		if (p != null) {
			personToDisplay = p;
			deleteButton.setEnabled(true);
			idLabel.setText("Kundennummer: " + Integer.toString(personToDisplay.getId()));
			firstNameTextBox.setText(personToDisplay.getFirstName());
			lastNameTextBox.setText(personToDisplay.getLastName());
		} else {
			idLabel.setText("Kundennummer: ");
			firstNameTextBox.setText("");
			lastNameTextBox.setText("");
			deleteButton.setEnabled(false);
		}
	}

	/**
	 * ClickHandler und abhängige AsyncCallback Klassen.
	 * 
	 */

	class DeleteClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			if (personToDisplay == null) {
				Window.alert("\"Es ist ein Fehler aufgetreten.");
			} else {
				listenVerwaltung.delete(personToDisplay, new DeletePersonCallback());
			}
		}
	}

	class DeletePersonCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen des Accounts ist fehlgeschlagen.");
		}

		public void onSuccess(Void result) {
			Window.alert("Account wurde gelöscht.");
			Window.Location.replace(ClientsideSettings.getLoginInformation().getLogoutURL());
		}

	}

	class ChangeClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			if (personToDisplay != null) {
				personToDisplay.setFirstName(firstNameTextBox.getText());
				personToDisplay.setLastName(lastNameTextBox.getText());
				listenVerwaltung.save(personToDisplay, new SaveCallback());
			} else {
				Window.alert("Fehlgeschlagen.");
			}
		}
	}

	class SaveCallback implements AsyncCallback<Void> {
		public void onFailure(Throwable caught) {
			Window.alert("Leider hat die Namensänderung nicht funktioniert");
		}

		/**
		 *  Die Namensänderung wird zum Personenbaum weitergeleitet.
		 */
		
		public void onSuccess(Void result) {
			Window.alert("Ihr Name wurde erfolgreich geändert.");
		}
	}

}