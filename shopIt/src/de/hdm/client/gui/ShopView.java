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

import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Shop;

public class ShopView extends VerticalPanel {
	// @emily kretzschmar

	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();

	private Shop selectedShop = null;

	public Shop getSelctedShop() {
		return selectedShop;
	}

	Shop shopToDisplay = null;
	// Widgets, deren Inhalte variable sind, werden als Attribute angelegt.

	TextBox nameTextBox = new TextBox();
	TextBox postalCodeTextBox = new TextBox();
	TextBox cityTextBox = new TextBox();
	TextBox streetTextBox = new TextBox();

	Button deleteButton = new Button("Shop löschen");
	Button newButton = new Button("Als neuen Shop speichern");
	Button saveButton = new Button("Speichern");

	private final  AsyncCallback<Vector<Shop>> getAllCallback;

	public ShopView() {

		// Beim Anzeigen werden die Widgets z.T. erzeugt. Alle werden in einem
		// Raster angeordnet, dessen Größe sich aus dem Platzbedarf der enthaltenen
		// Widgets bestimmt.

		Grid shopGrid = new Grid(7, 2);
		this.add(shopGrid);

		deleteButton.addClickHandler(new DeleteClickHandler());
		deleteButton.setEnabled(false);
		shopGrid.setWidget(4, 1, deleteButton);

		Label name = new Label("Name:");
		shopGrid.setWidget(1, 0, name);
		shopGrid.setWidget(1, 1, nameTextBox);

		Label plz = new Label("Postleizahl:");
		shopGrid.setWidget(3, 0, plz);
		shopGrid.setWidget(3, 1, postalCodeTextBox);

		Label city = new Label("Stadt:");
		shopGrid.setWidget(4, 0, city);
		shopGrid.setWidget(4, 1, cityTextBox);

		Label street = new Label("Strasse:");
		shopGrid.setWidget(2, 0, street);
		shopGrid.setWidget(2, 1, streetTextBox);

		newButton.addClickHandler(new NewClickHandler());
		newButton.setEnabled(true);
		shopGrid.setWidget(5, 0, newButton);

		saveButton.addClickHandler(new ChangeClickHandler());
		saveButton.setEnabled(true);
		shopGrid.setWidget(5, 1, saveButton);

		deleteButton.addClickHandler(new DeleteClickHandler());
		deleteButton.setEnabled(true);
		shopGrid.setWidget(6, 1, deleteButton);

		final CellTable<Shop> cellTable = new CellTable<Shop>();
		getAllCallback = new AsyncCallback<Vector<Shop>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler");
			}

			@Override
			public void onSuccess(Vector<Shop> result) {
				cellTable.setRowData(result);
			}
		};
		TextColumn<Shop> idColumn = new TextColumn<Shop>() {
			@Override
			public String getValue(Shop object) {
				return object.getId() + "";
			}
		};
		TextColumn<Shop> nameColumn = new TextColumn<Shop>() {
			@Override
			public String getValue(Shop object) {
				return object.getName();
			}
		};
		TextColumn<Shop> postalCodeColumn = new TextColumn<Shop>() {
			@Override
			public String getValue(Shop object) {
				return object.getPostalCode();
			}
		};
		TextColumn<Shop> streetColumn = new TextColumn<Shop>() {
			@Override
			public String getValue(Shop object) {
				return object.getStreet();
			}
		};
		TextColumn<Shop> cityColumn = new TextColumn<Shop>() {
			@Override
			public String getValue(Shop object) {
				return object.getCity();
			}
		};
		Column<Shop, String> editColumn = new Column<Shop, String>(new ButtonCell()) {
			@Override
			public String getValue(Shop object) {
				return "Bearbeiten";
			}
		};
		editColumn.setFieldUpdater(new FieldUpdater<Shop, String>() {
			@Override
			public void update(int index, Shop object, String value) {
				setSelectedShop(object);
			}
		});
		
		cellTable.addColumn(idColumn, "Id");
		cellTable.addColumn(nameColumn, "Name");
		cellTable.addColumn(postalCodeColumn, "PLZ");
		cellTable.addColumn(streetColumn, "Straße");
		cellTable.addColumn(cityColumn, "Stadt");
		cellTable.addColumn(editColumn, "");
		add(cellTable);
		
		listenVerwaltung.getAllShops(getAllCallback);
	}

	// Click handlers und abhängige AsyncCallback Klassen.

	// ClickHandler zum Löschen eines Shops

	private class DeleteClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			if (shopToDisplay == null) {
				Window.alert("kein Shop ausgewählt");
			} else {
				listenVerwaltung.delete(shopToDisplay, new deleteShopCallback(shopToDisplay));
			}

		}
	}

	class deleteShopCallback implements AsyncCallback<Void> {

		Shop shop = null;

		deleteShopCallback(Shop s) {
			shop = s;
		}

		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen des Shops ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			if (shop != null) {
				setSelectedShop(null);
				listenVerwaltung.getAllShops(getAllCallback);
			}
		}
	}

	// Clickhandler zum Speichern eines Shops
	private class ChangeClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			if (shopToDisplay != null) {
				shopToDisplay.setName(nameTextBox.getText());
				shopToDisplay.setStreet(postalCodeTextBox.getText());
				shopToDisplay.setCity(cityTextBox.getText());
				shopToDisplay.setPostalCode(streetTextBox.getText());

				listenVerwaltung.save(shopToDisplay, new saveCallback());

			} else {
				Window.alert("Kein Shop ausgewählt!");
			}
		}
	}

	private class saveCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Die Änderung ist fehlgeschlagen!");
		}

		public void onSuccess(Void result) {
			listenVerwaltung.getAllShops(getAllCallback);
		}
	}

	// ClickHandler für einen neuen Shop
	private class NewClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			String name = nameTextBox.getText();
			String postalCode = postalCodeTextBox.getText();
			String city = cityTextBox.getText();
			String street = streetTextBox.getText();

			listenVerwaltung.createShop(name, postalCode, city, street, new CreateShopCallback());
		}

		class CreateShopCallback implements AsyncCallback<Shop> {

			public void onFailure(Throwable caught) {
				Window.alert("Das Anlegen eines Shops ist fehlgeschlagen!");
			}

			public void onSuccess(Shop shop) {
				Window.alert("Success");
				if (shop != null) {
					setSelectedShop(shop);
					listenVerwaltung.getAllShops(getAllCallback);
				}
			}
		}
	}

	void setSelectedShop(Shop s) {
		if (s != null) {
			shopToDisplay = s;

			nameTextBox.setText(shopToDisplay.getName());
			postalCodeTextBox.setText(shopToDisplay.getPostalCode());
			streetTextBox.setText(shopToDisplay.getStreet());
			cityTextBox.setText(shopToDisplay.getStreet());

		} else {
			nameTextBox.setText("");
			postalCodeTextBox.setText("");
			streetTextBox.setText("");
			cityTextBox.setText("");
		}
	}

}
