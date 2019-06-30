package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
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
import de.hdm.shared.bo.Membership;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.Team;

public class TeamView extends VerticalPanel {

	// @emily kretzschmar

	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();

	public TeamListCellTreeTab getTeamModel() {
		return teamModel;
	}

	public void setTeamModel(TeamListCellTreeTab teamModel) {
		this.teamModel = teamModel;
	}

	private CellTreeViewModel viewModel = null;
	private TeamListCellTreeTab teamModel = null;

	private Team selectedTeam = null;
	private Person selectedPerson = null;

	public Team getSelectedTeam() {
		return selectedTeam;
	}

	public CellTreeViewModel getViewModel() {
		return viewModel;
	}

	public void setViewModel(CellTreeViewModel viewModel) {
		this.viewModel = viewModel;
	}

	/*
	 * Label creationTime = new Label("Creationtime:"); Label numberOfMembers = new
	 * Label("Number of Members:");
	 */
	
	//Widgets werden als Attribute angelegt.

	// Button changeButton = new Button("Name ändern");
	Button deleteButton = new Button("Team löschen");
	Button leaveButton = new Button("Gruppe verlassen");
	Button addButton = new Button("Mitglieder hinzufügen");
	TextBox nameTextBox = new TextBox();
	Button newButton = new Button("Als neue Gruppe speichern");
	Button saveButton = new Button("Speichern");
	TextBox emailTextBox = new TextBox();

	/*
	 * Beim Anzeigen werden die Widgets z.T. erzeugt. Alle werden in einem Raster
	 * angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen Widgets
	 * bestimmt.
	 */

	public void onLoad() {

		//Beim Anzeigen werden die Widgets z.T. erzeugt. Alle werden in einem
		 //Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
		 // Widgets bestimmt.

		Grid teamGrid = new Grid(6, 3);
		this.add(teamGrid);

		deleteButton.addClickHandler(new DeleteClickHandler());
		deleteButton.setEnabled(false);
		teamGrid.setWidget(4, 1, deleteButton);

		// changeButton.addClickHandler(new ChangeButtonClickHandler());
		// changeButton.setEnabled(true);
		// teamGrid.setWidget(2, 1, editButton);

		Label textboxLabel = new Label("Name:");
		teamGrid.setWidget(1, 0, textboxLabel);
		teamGrid.setWidget(1, 1, nameTextBox);

		newButton.addClickHandler(new NewClickHandler());
		newButton.setEnabled(true);
		teamGrid.setWidget(2, 0, newButton);

		saveButton.addClickHandler(new ChangeClickHandler());
		saveButton.setEnabled(true);
		teamGrid.setWidget(2, 1, saveButton);

		leaveButton.addClickHandler(new LeaveClickHandler());
		leaveButton.setEnabled(true);
		teamGrid.setWidget(5, 0, leaveButton);

		addButton.addClickHandler(new AddClickHandler());
		addButton.setEnabled(true);
		teamGrid.setWidget(5, 1, addButton);

		Label personTextBox = new Label("Hinzuzufügende Person (email)");
		teamGrid.setWidget(4, 0, personTextBox);
		teamGrid.setWidget(4, 1, emailTextBox);

	}
//Click handlers und abhängige AsyncCallback Klassen.
	
	//Clickhandler um ein Team zu löschen
	
	private class DeleteClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (selectedTeam == null) {
				Window.alert("kein Team ausgewählt");
			} else {
				listenVerwaltung.delete(selectedTeam, new deleteTeamCallback(selectedTeam));
			}

		}
	}

	class deleteTeamCallback implements AsyncCallback<Void> {

		Team team = null;

		deleteTeamCallback(Team t) {
			team = t;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen des Teams ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Void result) {
			if (team != null) {
				setSelectedTeam(null);
				teamModel.removeTeam(team);
			}
		}

	}

	/*
	 * private class ChangeClickHandler implements ClickHandler {
	 * 
	 * public void onClick(ClickEvent event) { if (teamToDisplay != null) {
	 * teamToDisplay.setName(nameTextBox.getText());
	 * listenVerwaltung.save(teamToDisplay, new SaveCallback()); } else {
	 * Window.alert("Kein Team ausgewählt"); } } }
	 * 
	 * private class SaveCallback implements AsyncCallback<Void> {
	 * 
	 * public void onFailure(Throwable caught) {
	 * Window.alert("Die Namensänderung ist fehlgeschlagen!"); }
	 * 
	 * public void onSuccess(Void result) { // Die Änderung wird zum Baum
	 * propagiert. CellTreeViewModel.updateTeam(teamToDisplay); } }
	 */

	
	//ClickHandler um ein Team zu verlassen
	
	private class LeaveClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			listenVerwaltung.getPersonByEmail(emailTextBox.getText(), new GetPersonForDeleteCallback());
		}

	}

	private class GetPersonForDeleteCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler!");
		}

		@Override
		public void onSuccess(Person result) {
			if (result == null) {
				Window.alert("Person nicht vorhanden!");
			} else {
				if (selectedTeam == null) {
					Window.alert("Kein Team ausgewählt");
				} else {
					listenVerwaltung.delete(selectedPerson.getId(), selectedTeam.getId(),
							new DeleteMembershipCallback(selectedTeam));
				}
			}
		}

	}

	class DeleteMembershipCallback implements AsyncCallback<Void> {

		Team t = null;

		DeleteMembershipCallback(Team t) {
			this.t = t;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Verlassen des Teams ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Success");
		}
	}
	
	//ClickHandler um ein neues Team zu speichern

	private class NewClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String name = nameTextBox.getText();

			listenVerwaltung.createTeam(name, new CreateTeamCallback());

		}

		class CreateTeamCallback implements AsyncCallback<Team> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Das Anlegen einer neuer Gruppe ist fehlgeschlagen!");
			}

			@Override
			public void onSuccess(Team team) {
				Window.alert("Success");
				setSelectedTeam(team);
			}
		}
	}

	public void setSelectedTeam(Team t) {
		if (t != null) {
			selectedTeam = t;

			nameTextBox.setText(t.getName());

		} else {
			nameTextBox.setText("");
		}
	}
	
	//ClickHandler um Team als neues Team speichern

	private class ChangeClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			if (selectedTeam != null) {
				selectedTeam.setName(nameTextBox.getText());
				listenVerwaltung.save(selectedTeam, new saveCallback());
			} else {
				Window.alert("Kein Team ausgewählt!");
			}
		}
	}

	private class saveCallback implements AsyncCallback<Void> {
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Änderung ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Success");
		}
	}

	//ClickHandler um eine Person der Gruppe zuzuordnen
	private class AddClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
//				Team selectedTeam = CellTreeViewModel.getSelectedTeam();
			if (selectedTeam == null) {
				Window.alert("Kein Team ausgewählt");
			} else {

				listenVerwaltung.getPersonByEmail(emailTextBox.getText(), new GetPersonCallback());

			}
		}
	}

	class GetPersonCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler!");

		}

		@Override
		public void onSuccess(Person result) {
			if (result == null) {
				Window.alert("Person nicht vorhanden!");
			} else {
				selectedPerson = result;
				listenVerwaltung.createMembership(selectedPerson.getId(), selectedTeam.getId(),
						new CreateMembershipCallback());
			}
		}

	}

	class CreateMembershipCallback implements AsyncCallback<Membership> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Error");
			// ("Fehler bei der Abfrage " + caught.getMessage());
		}

		@Override
		public void onSuccess(Membership membership) {
			Window.alert("Success");
//			if (membership != null && team != null) {
////					viewModel.addMembershipOfTeam(membership, team);
//			}
		}
	}

}
