package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Team;

@Deprecated
/**
 * Die Klasse <code>AddPersonToGroupForm</code> dient zur Darstellung einer
 * DialogBox, die das Hinzufügen neuer Personen zu einer Gruppe ermöglicht.
 * 
 * @author dibasegmen
 *
 */
public class AddPersonToGroupForm extends VerticalPanel {

	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
	// private Person p = Person.GetPerson(); funktioniert nicht
	private Person newGroupMember = null;
	private Team selectedGroup = null;

	private VerticalPanel contentPanel = new VerticalPanel();
	private Label infoLabel = new Label("Neues Gruppenmitglied hinzufügen.");
	private Grid grid = new Grid(1, 2);
	private Label emailLabel = new Label("Gmail-Adresse: ");
	private TextBox emailTextBox = new TextBox();

	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Button saveButton = new Button("Speichern");
	private Button cancelButton = new Button("Abbrechen");

	public AddPersonToGroupForm() {

		grid.setWidget(0, 0, emailLabel);
		grid.setWidget(0, 1, emailTextBox);

		saveButton.addClickHandler(new SaveClickHandler());
		cancelButton.addClickHandler(new CancelClickHandler());

		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);

		contentPanel.add(infoLabel);
		contentPanel.add(grid);
		contentPanel.add(buttonPanel);
	}

	public void onLoad() {
		RootPanel.get("main").add(contentPanel);
	}

	public Team getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(Team selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	private class CancelClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get("main").clear();
			TeamForm group = new TeamForm();
			group.setSelected(selectedGroup);
			RootPanel.get("main").add(group);
		}

	}

	private class SaveClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
//				if (selectedGroup != null) {
//					String email = emailTextBox.getValue();
//					listenVerwaltung.getPersonByEmail(email, new GetPersonCallback());
//					listenVerwaltung.createMembership(person, team, callback);
////					(newGroupMember, selectedGroup, new GetPersonCallback());
//					TeamForm group = new TeamForm();
//					group.setSelected(selectedGroup);
//					RootPanel.get("main").clear();
//					RootPanel.get("main").add(group);
//					} else {
//					Window.alert("Es wurde keine Gruppe ausgewählt.");
//				}
		}

	}

	private class GetPersonCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Es ist leider folgender Fehler aufgetreten: " + caught.toString());
		}

		public void onSuccess(Person result) {
			newGroupMember = result;
		}

	}

	private class AddUserCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Es ist leider folgender Fehler aufgetreten: " + caught.toString());
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Gruppenmitglied wurde erfolgreich hinzugefügt :).");
		}

	}

}
