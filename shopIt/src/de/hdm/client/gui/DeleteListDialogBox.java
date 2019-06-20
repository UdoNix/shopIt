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
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.List;


public class DeleteListDialogBox extends DialogBox {
	
		
		private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
		
		private CellTreeViewModel ViewModel = new CellTreeViewModel();
		
		private List selectedList = null;
		
		private VerticalPanel contentPanel = new VerticalPanel();
		private Label confirmLabel = new Label("Möchten Sie die Liste wirklich löschen?");
		private HorizontalPanel btnPanel = new HorizontalPanel();
		private Button confirmButton = new Button("Löschen");
		private Button cancelButton = new Button("Abbrechen");
		
			
			public DeleteListDialogBox() {
			
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
				
		
		public List getSelectedList() {
			return selectedList;
		}
		
		
		
		public void setSelectedList(List selectedList) {
			this.selectedList = selectedList;
		}
		
		
		private class CancelClickHandler implements ClickHandler{
			
			public void onClick (ClickEvent event) {
				
				DeleteListDialogBox.this.hide();
			}
		}
		
		
		private class ConfirmClickHandler implements ClickHandler{

			@Override
			public void onClick(ClickEvent event) {
				if (selectedList != null) {
					listenVerwaltung.delete(selectedList, new DeleteListCallback(selectedList));
					RootPanel.get("main").clear();
					RootPanel.get("aside").clear();
					DeleteListDialogBox.this.hide();
				} else {
					Window.alert("Sie haben keine Liste ausgewählt. :(");
			}
			
		}
		
		
		private class DeleteListCallback implements AsyncCallback<Void>{
			
			List list = null;
			
			DeleteListCallback(List l){
				this.list = l;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
				Window.alert("Folgender Fehler ist aufgetreten: /n" + caught.toString());
				
			}

			@Override
			public void onSuccess(Void result) {
				if (list != null) {
					setSelectedList(null);
					//ViewModel.removeList(list); funktioniert nicht!!
					RootPanel.get("main").clear();
			
					
				}
			}

		}
		
		

	}
	

}
