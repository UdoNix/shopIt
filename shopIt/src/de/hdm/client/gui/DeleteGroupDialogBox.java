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
import de.hdm.shared.bo.Team;
	

public class DeleteGroupDialogBox extends DialogBox {
			
			private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
			
			private CellTreeViewModel ViewModel = new CellTreeViewModel();
			
			private Team selectedTeam = null;
			
			private VerticalPanel contentPanel = new VerticalPanel();
			private Label confirmLabel = new Label("Möchten Sie das Gruppe wirklich löschen?");
			private HorizontalPanel btnPanel = new HorizontalPanel();
			private Button confirmButton = new Button("Löschen");
			private Button cancelButton = new Button("Abbrechen");
			
				
				public DeleteGroupDialogBox() {
				
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
					
			
}