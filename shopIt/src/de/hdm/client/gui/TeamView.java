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


	public class TeamView  extends VerticalPanel {
		
		//@emily kretzschmar
		
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
		private Membership selectedMembership = null;
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

		Team teamToDisplay = null;
		Person personToDisplay = null;
		
		void setSelected(Team t){
			if(t != null){
				teamToDisplay = t;
				deleteButton.setEnabled(true);
				nameTextBox.setText(teamToDisplay.getName());
			}
			else{
				teamToDisplay = null;
				deleteButton.setEnabled(false);
			}
		}
		

		
		 /*Label creationTime = new Label("Creationtime:");
		 Label numberOfMembers = new Label("Number of Members:");
		 */
		
		
		
		// Button changeButton = new Button("Name ändern");
		 Button deleteButton = new Button("Team löschen");
		 Button leaveButton = new Button("Gruppe verlassen");
		 Button addButton = new Button ("Mitglieder hinzufügen");
		 TextBox nameTextBox = new TextBox();
		 Button newButton = new Button("Als neue Gruppe speichern");
		 Button saveButton = new Button ("Speichern");
		 TextBox emailTextBox = new TextBox ();


		/*
		 * Beim Anzeigen werden die Widgets z.T. erzeugt. Alle werden in einem
		 * Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
		 * Widgets bestimmt.
		 */
		
		public void onLoad() {

			
			/**
			 * Das Grid-Widget erlaubt die Anordnung anderer Widgets in einem
			 * Gitter.
			 */
			Grid teamGrid = new Grid(5, 3);
			this.add(teamGrid);

			deleteButton.addClickHandler(new DeleteClickHandler());
			deleteButton.setEnabled(false);
			teamGrid.setWidget(4, 1, deleteButton);

			//changeButton.addClickHandler(new ChangeButtonClickHandler());
			//changeButton.setEnabled(true);
			//teamGrid.setWidget(2, 1, editButton);
			
			Label textboxLabel = new Label ("Name:");
			teamGrid.setWidget(1, 0, textboxLabel);
			teamGrid.setWidget(1,1, nameTextBox );
			
			Label personTextBox = new Label ("Hinzuzufügende Person (email)");
			teamGrid.setWidget(3, 0, personTextBox);
			teamGrid.setWidget(3, 1 , emailTextBox);
			
			leaveButton.addClickHandler(new LeaveClickHandler());
			leaveButton.setEnabled(true);
			teamGrid.setWidget(5, 0, leaveButton);
			
			addButton.addClickHandler(new AddClickHandler());
			addButton.setEnabled(true);
			teamGrid.setWidget(3, 3, addButton);
			
			newButton.addClickHandler(new NewClickHandler());
			newButton.setEnabled(false);
			teamGrid.setWidget(4, 1, newButton);
			
			saveButton.addClickHandler(new ChangeClickHandler());
			saveButton.setEnabled(true);
			teamGrid.setWidget(3,4,saveButton);
			

		}
		
		private class DeleteClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				if (selectedTeam == null) {
					Window.alert("kein Team ausgewählt");
				} else {
					listenVerwaltung.delete(teamToDisplay,
							new deleteTeamCallback(
									teamToDisplay));
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
		
		/*private class ChangeClickHandler implements ClickHandler {

			public void onClick(ClickEvent event) {
				if (teamToDisplay != null) {
					teamToDisplay.setName(nameTextBox.getText());
					listenVerwaltung.save(teamToDisplay, new SaveCallback());
				} else {
					Window.alert("Kein Team ausgewählt");
				}
			}
		}

		private class SaveCallback implements AsyncCallback<Void> {
		
			public void onFailure(Throwable caught) {
				Window.alert("Die Namensänderung ist fehlgeschlagen!");
			}

			public void onSuccess(Void result) {
				// Die Änderung wird zum Baum propagiert.
				CellTreeViewModel.updateTeam(teamToDisplay);
			}
		}
		*/
		
		private class LeaveClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				if (selectedTeam == null) {
					Window.alert("Kein Team ausgewählt");
				} else {
					listenVerwaltung.delete(selectedMembership, new DeleteMembershipCallback(selectedTeam));
							
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
				if (t != null) {
					teamModel.delete(t);
				}
			}
		}
		
		
		private class NewClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				String name = nameTextBox.getText();
				
				listenVerwaltung.createTeam(name,
						new createTeamCallback());
				
				
				
				class CreateTeamCallback implements AsyncCallback<Team> {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Das Anlegen einer neuer Gruppe ist fehlgeschlagen!");
					}

					@Override
					public void onSuccess(Team team) {
						if (team != null) {
						
							viewModel.addTeam(team);
						}
					}
				}
			}
		}
		
		
		private class ChangeClickHandler implements ClickHandler {
			@Override
			public void onClick(ClickEvent event) {
				if (teamToDisplay != null) {
					teamToDisplay.setName(nameTextBox.getText());
					listenVerwaltung.save(teamToDisplay, new saveCallback());
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
				
				teamModel.updateTeam(teamToDisplay);
			}
		}
			
		private class AddClickHandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				Team selectedTeam = CellTreeViewModel.getSelectedTeam();
				if (selectedTeam == null) {
					Window.alert("Kein Team ausgewählt");
				} else {
					listenVerwaltung.getPersonByEmail(emailTextBox.getText(), new GetPersonCallback());
					listenVerwaltung.createMembership(selectedPerson.getId(), selectedTeam.getId(), new createMembershipCallback());
					
				}
			}
		}
		
		class GetPersonCallback implements AsyncCallback{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler!");
			
				
			}

			@Override
			public void onSuccess(Object result) {
				
				
			}
			
		}
		
		private class CreateMembershipCallback implements AsyncCallback<Membership> {

			Team team = null;

			CreateMembershipCallback(Team t) {
				team = t;
			}

			@Override
			public void onFailure(Throwable caught) {
				//("Fehler bei der Abfrage " +  caught.getMessage());
			}

			@Override
			public void onSuccess(Membership membership) {
				if (membership != null && team != null) {
					viewModel.addMembershipOfTeam(membership, team);
				}
			}
		}
	

		
		void setSelectedTeam(Team t) {
			if (t != null) {
				teamToDisplay = t;
				deleteButton.setEnabled(true);
				newButton.setEnabled(true);
				addButton.setEnabled(true);
				leaveButton.setEnabled(true);
				
				
		
				
				emailTextBox.setText(personToDisplay.getEmail());
				nameTextBox.setText(teamToDisplay.getName());
				
				
			} else {
				nameTextBox.setText("");
			    emailTextBox.setText("");
			    deleteButton.setEnabled(false);
				newButton.setEnabled(false);
				addButton.setEnabled(false);
				leaveButton.setEnabled(false);
			}
		}
		
		
		}

	
		
		
			
		
		

		

		

