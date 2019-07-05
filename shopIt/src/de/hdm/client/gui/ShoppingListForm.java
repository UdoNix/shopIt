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
import de.hdm.shared.bo.ShoppingList;
import de.hdm.shared.bo.Shop;


public class ShoppingListForm extends VerticalPanel {

//	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
//
//	public ShoppingListForm getListModel() {
//		return listModel;
//	}
//
//	public void setListModel(ShoppingListListCellTreeTab listModel) {
//		this.listModel = listModel;
//	}
//
//	private CellTreeViewModel viewModel = null;
//	private ShoppingListListCellTreeTab listModel = null;
//
//	private ShoppingList selectedList = null;
//
//	public ShoppingList getSelectedList() {
//		return selectedList;
//	}
//
//	public CellTreeViewModel getViewModel() {
//		return viewModel;
//	}
//
//	public void setViewModel(CellTreeViewModel viewModel) {
//		this.viewModel = viewModel;
//	}
//
//	/**
//	 * Anlegen relevanter Widgets
//	 */
//
//	TextBox nameTextBox = new TextBox();
//	Button editButton = new Button("bearbeiten");
//	Button deleteButton = new Button("löschen");
//
//	/**
//	 * Beim Anzeigen werden die Widgets z.T. erzeugt. Alle werden in einem Raster
//	 * angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen Widgets
//	 * bestimmt.
//	 */
//
//	public void onLoad() {
//
//		/**
//		 * Das Grid-Widget erlaubt die Anordnung anderer Widgets in einem Gitter.
//		 */
//
//		Grid ListGrid = new Grid(3, 2);
//		this.add(ListGrid);
//
//		deleteButton.addClickHandler(new DeleteClickHandler());
//		deleteButton.setEnabled(false);
//		teamGrid.setWidget(4, 1, deleteButton);
//
//		// changeButton.addClickHandler(new ChangeButtonClickHandler());
//		// changeButton.setEnabled(true);
//		// teamGrid.setWidget(2, 1, editButton);
//
//		Label textboxLabel = new Label("Name:");
//		teamGrid.setWidget(1, 0, textboxLabel);
//		teamGrid.setWidget(1, 1, nameTextBox);
//
//		editButton.addClickHandler(new EditClickHandler());
//		editButton.setEnabled(true);
//		teamGrid.setWidget(2, 0, editButton);
//
//		deleteButton.addClickHandler(new DeleteClickHandler());
//		deleteButton.setEnabled(true);
//		teamGrid.setWidget(3, 1, saveButton);
//
//	}
//
//	private class DeleteClickHandler implements ClickHandler {
//
//		@Override
//		public void onClick(ClickEvent event) {
//			if (selectedList == null) {
//				Window.alert("keine Liste ausgewählt");
//			} else {
//				listenVerwaltung.delete(selectedTeam, new deleteTeamCallback(selectedTeam));
//				listenVerwaltung.delete(selectedList, new deleteListCallback(selectedList));
//			}
//
//		}
//	}
//
//	class deleteListCallback implements AsyncCallback<Void> {
//
//		List list = null;
//
//		deleteListCallback(List l) {
//			list = l;
//		}
//
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Das Löschen der Liste ist fehlgeschlagen!");
//		}
//
//		@Override
//		public void onSuccess(Void result) {
//			if (list != null) {
//				setSelectedList(null);
//				listModel.removeList(list);
//			}
//		}
//
//	}
//
//	private class ChangeClickHandler implements ClickHandler {
//		@Override
//		public void onClick(ClickEvent event) {
//			if (selectedList != null) {
//				selectedList.setName(nameTextBox.getText());
//				// neuer Callback?
//				listenVerwaltung.save(selectedList, new saveCallback());
//			} else {
//				Window.alert("Kein Team ausgewählt!");
//			}
//		}
//	}
//
//	// save = bearbeiten!!
//
//	private class saveCallback implements AsyncCallback<Void> {
//		@Override
//		public void onFailure(Throwable caught) {
//			Window.alert("Die Änderung ist fehlgeschlagen!");
//		}
//
//		@Override
//		public void onSuccess(Void result) {
//			Window.alert("Success");
//		}
//	}
}