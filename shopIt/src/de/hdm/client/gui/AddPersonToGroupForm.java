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
import de.hdm.shared.EditorServiceAsync;
import de.hdm.shared.bo.Group;
import de.hdm.shared.bo.Person;

	/**
	 * Die Klasse <code>AddPersonToGroupForm</code> dient zur Darstellung einer DialogBox, die das Hinzufügen neuer Personen
	 * zu einer Gruppe ermöglicht.
	 * 
	 * @author dibasegmen
	 *
	 */
	public class AddPersonToGroupForm extends VerticalPanel{
		
		EditorServiceAsync listenVerwaltung = ClientsideSettings.getEditorService();
		private Person p = CurrentPerson.person();
		private Person newGroupMember = null;
		private Group selectedGroup = null;
		
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
		
		
		public Group getSelectedGroup() {
			return selectedGroup;
		}

		public void setSelectedGroup(Group selectedGroup) {
			this.selectedGroup = selectedGroup;
		}



		private class CancelClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("main").clear();
				GroupForm group = new GroupForm();
				group.setSelected(selectedGroup);
				RootPanel.get("main").add(group);
			}
			
		}
		
		private class SaveClickHandler implements ClickHandler{

			public void onClick(ClickEvent event) {
				if (selectedGroup != null) {
					String email = emailTextBox.getValue();
					listenVerwaltung.getPersonByMail(email, new GetPersonCallback());
					listenVerwaltung.addUserToGroup(newGroupMember, selectedGroup, new AddUserCallback());
					GroupForm group = new GroupForm();
					group.setSelected(selectedGroup);
					RootPanel.get("main").clear();
					RootPanel.get("main").add(group);
					} else {
					Window.alert("Es wurde keine Gruppe ausgewählt.");
				}
			}
			
		}
		
		private class GetPersonCallback implements AsyncCallback<Person>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Es ist leider folgender Fehler aufgetreten: " + caught.toString());
			}

			public void onSuccess(Person result) {
				newGroupMember = result;
			}
			
		}
		
		private class AddUserCallback implements AsyncCallback<Void>{

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

