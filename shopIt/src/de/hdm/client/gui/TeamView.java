package de.hdm.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
import de.hdm.client.gui.Tree.ShoppingListsAsyncDataProvider;
import de.hdm.client.gui.Tree.TeamsAsyncDataProvder;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Membership;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.ShoppingList;
import de.hdm.shared.bo.Team;

public class TeamView extends VerticalPanel {

	// @emily kretzschmar

	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();

	private Team selectedTeam = null;
	private Person selectedPerson = null;

	private final Tree tree;

	private final ShoppingListsAsyncDataProvider asyncDataProvider;

	public TeamView(Tree tree, ShoppingListsAsyncDataProvider shoppingListsAsyncDataProvider) {
		this.tree = tree;
		this.asyncDataProvider = shoppingListsAsyncDataProvider;
	}

	public Team getSelectedTeam() {
		return selectedTeam;
	}

	/*
	 * Label creationTime = new Label("Creationtime:"); Label numberOfMembers = new
	 * Label("Number of Members:");
	 */

	// Widgets werden als Attribute angelegt.

	// Button changeButton = new Button("Name ändern");
	Button deleteButton = new Button("Gruppe löschen");
	Button addButton = new Button("Mitglieder hinzufügen");
	TextBox nameTextBox = new TextBox();
	Button saveButton = new Button("Speichern");
	TextBox emailTextBox = new TextBox();
	private AsyncCallback<Vector<Person>> getAllMembershipCallback;

	/*
	 * Beim Anzeigen werden die Widgets z.T. erzeugt. Alle werden in einem Raster
	 * angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen Widgets
	 * bestimmt.
	 */

	public void onLoad() {

		// Beim Anzeigen werden die Widgets z.T. erzeugt. Alle werden in einem
		// Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
		// Widgets bestimmt.

		Grid teamGrid = new Grid(7, 3);
		this.add(teamGrid);

		deleteButton.addClickHandler(new DeleteClickHandler());
		teamGrid.setWidget(2, 0, deleteButton);

		Label textboxLabel = new Label("Name:");
		teamGrid.setWidget(1, 0, textboxLabel);
		teamGrid.setWidget(1, 1, nameTextBox);

		saveButton.addClickHandler(new ChangeClickHandler());
		saveButton.setEnabled(true);
		teamGrid.setWidget(2, 1, saveButton);

		final TextBox listNameTextBox = new TextBox();
		teamGrid.setWidget(3, 0, new Label("Listen Name:"));
		teamGrid.setWidget(3, 1, listNameTextBox);

		Button listButton = new Button("Liste Anlegen");
		listButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String name = listNameTextBox.getValue();

				listenVerwaltung.createListFor(selectedTeam, name, new AsyncCallback<ShoppingList>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");
					}

					@Override
					public void onSuccess(ShoppingList result) {
						if (asyncDataProvider != null) {
							asyncDataProvider.refresh();
						}
						listNameTextBox.setValue("");
						Window.alert("Success");
					}
				});
			}
		});
		teamGrid.setWidget(4, 1, listButton);

		Label personTextBox = new Label("Hinzuzufügende Person (email)");
		teamGrid.setWidget(5, 0, personTextBox);
		teamGrid.setWidget(5, 1, emailTextBox);

		addButton.addClickHandler(new AddClickHandler());
		addButton.setEnabled(true);
		teamGrid.setWidget(6, 1, addButton);

		final CellTable<Person> membershipTable = new CellTable<Person>();

		getAllMembershipCallback = new AsyncCallback<Vector<Person>>() {
			@Override
			public void onSuccess(Vector<Person> result) {
				membershipTable.setRowCount(result.size(), true);
				membershipTable.setRowData(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler");
			}
		};

		Column<Person, String> deleteMembershipColumn = new Column<Person, String>(new ButtonCell()) {
			@Override
			public String getValue(Person object) {
				return "Entfernen";
			}
		};
		deleteMembershipColumn.setFieldUpdater(new FieldUpdater<Person, String>() {
			@Override
			public void update(int index, Person object, String value) {
				listenVerwaltung.delete(object.getId(), selectedTeam.getId(), new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						listenVerwaltung.getAllPersonsOf(selectedTeam.getId(), getAllMembershipCallback);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");
					}
				});
			}
		});

		TextColumn<Person> emailColumn = new TextColumn<Person>() {
			@Override
			public String getValue(Person object) {
				return object.getEmail();
			}
		};

		membershipTable.addColumn(emailColumn, "Email");
		membershipTable.addColumn(deleteMembershipColumn, "");

		add(membershipTable);

		listenVerwaltung.getAllPersonsOf(selectedTeam.getId(), getAllMembershipCallback);
	}
//Click handlers und abhängige AsyncCallback Klassen.

	// Clickhandler um ein Team zu löschen

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
				Window.alert("Gelöscht");
				tree.getRootTreeNode().setChildOpen(1, false);
				tree.getRootTreeNode().setChildOpen(1, true);
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

	// ClickHandler um ein Team zu verlassen

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

	// ClickHandler um ein neues Team zu speichern

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

	// ClickHandler um Team als neues Team speichern

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
			tree.getRootTreeNode().setChildOpen(1, false);
			tree.getRootTreeNode().setChildOpen(1, true);
		}
	}

	// ClickHandler um eine Person der Gruppe zuzuordnen
	private class AddClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
//				Team selectedTeam = CellTreeViewModel.getSelectedTeam();
			if (selectedTeam == null) {
				Window.alert("Kein Team ausgewählt");
			} else {

				listenVerwaltung.getPersonByEmail(emailTextBox.getText(), new GetPersonCallback());
				emailTextBox.setValue("");
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
		}

		@Override
		public void onSuccess(Membership membership) {
			Window.alert("Success");
			listenVerwaltung.getAllPersonsOf(selectedTeam.getId(), getAllMembershipCallback);
		}
	}

}
