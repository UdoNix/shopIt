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
import de.hdm.shared.bo.Team;

public class NewTeamView extends VerticalPanel {

	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();

	public NewTeamView(final Tree tree) {

		final TextBox nameTextBox = new TextBox();

		Grid grid = new Grid(2, 2);

		grid.setWidget(0, 0, new Label("Name: "));
		grid.setWidget(0, 1, nameTextBox);

		Button button = new Button("Anlegen");
		grid.setWidget(1, 1, button);

		add(grid);

		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String name = nameTextBox.getValue();
				if (name == null || name.isEmpty()) {
					Window.alert("Bitte Name angeben");
				} else {
					listenVerwaltung.createTeam(name, new AsyncCallback<Team>() {

						@Override
						public void onSuccess(Team result) {
							tree.getRootTreeNode().setChildOpen(1, false);
							tree.getRootTreeNode().setChildOpen(1, true);

							nameTextBox.setValue("");

							Window.alert("Die Liste wurde erfolgreich angelegt.");
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert(" Es ist ein Fehler aufgetreten. ");
						}
					});
				}
			}
		});
	}

}
