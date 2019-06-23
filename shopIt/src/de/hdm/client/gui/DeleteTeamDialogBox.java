package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministration;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.client.gui.CellTreeViewModel;
import de.hdm.shared.bo.Team;

	

public class DeleteTeamDialogBox extends DialogBox {
			
			private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
			
			private CellTreeViewModel ViewModel = new CellTreeViewModel();
			
			private Team selectedTeam;


			private VerticalPanel contentPanel = new VerticalPanel();
			private Label confirmLabel = new Label("Möchten Sie das Team wirklich löschen?");
			private HorizontalPanel btnPanel = new HorizontalPanel();
			private Button confirmButton = new Button("Löschen");
			private Button cancelButton = new Button("Abbrechen");
			
				
				public DeleteTeamDialogBox() {
				
				this.setGlassEnabled(true);
				
				cancelButton.setStylePrimaryName("cancelButton");
				confirmButton.setStylePrimaryName("confirmButton");
				
				cancelButton.addClickHandler(new CancelClickHandler());
				confirmButton.addClickHandler(new ConfirmClickHandler());
				
				btnPanel.add(cancelButton);
				btnPanel.add(confirmButton);
				
				contentPanel.add(confirmLabel);
				contentPanel.add(btnPanel);
				
				this.add(contentPanel);

				this.center();
				
				}
				
				public Team getSelectedTeam() {
					return selectedTeam;
				}


				public void setSelectedTeam(Team selectedTeam) {
					this.selectedTeam = selectedTeam;
				}
				
				
				private class CancelClickHandler implements ClickHandler{
					
					public void onClick (ClickEvent event) {
						
						DeleteTeamDialogBox.this.hide();
					}
				}
				
				
				private class ConfirmClickHandler implements ClickHandler{

					@Override
					public void onClick(ClickEvent event) {
						if (selectedTeam != null) {
							listenVerwaltung.delete(selectedTeam, new DeleteTeamCallback(selectedTeam));
							RootPanel.get("main").clear();
							RootPanel.get("aside").clear();
							DeleteTeamDialogBox.this.hide();
						} else {
							Window.alert("Sie haben keine Gruppe ausgewählt. :(");
					}
					
				}
				
				
				class DeleteTeamCallback implements AsyncCallback<Void>{
					
					Team team = null;
					
					DeleteTeamCallback(Team t){
						this.team = t;
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
						Window.alert("Folgender Fehler ist aufgetreten: /n" + caught.toString());
						
					}

					@Override
					public void onSuccess(Void result) {
						if (team != null) {
							setSelectedTeam(null);
							//ViewModel.removeTeam(team);
							RootPanel.get("main").clear();
					
							
						}
					}

				}
				
				

			}
			

		}


	

					
			
