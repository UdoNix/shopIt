package de.hdm.client.gui;

import java.text.DecimalFormat;
import java.util.Vector;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;

import de.hdm.client.ClientsideSettings;
import de.hdm.client.gui.Tree.ShoppingListsAsyncDataProvider;
import de.hdm.shared.ShopITAdministration;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Article;
import de.hdm.shared.bo.Item;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.ShoppingList;
import de.hdm.shared.bo.UnitOfMeasure;

/**
 * Die Klasse <code>ListItemForm</code> dient dem Anlegen neuer Listeneinträge
 */

public class ListItemForm extends VerticalPanel {

	private final ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();

	/**
	 * Instanziieren und Zugriff ermöglichen auf die <code>ViewModel, ListToDisplay,
	 * ListShowCase</code>
	 */

	private CellTreeViewModel ViewModel = null;

	private final ShoppingList selectedShoppingList;
	private Item selectedItem;

	private NumberFormat decimalFormatter = NumberFormat.getDecimalFormat();

	public CellTreeViewModel getViewModel() {
		return ViewModel;
	}

	public void setViewModel(CellTreeViewModel viewModel) {
		ViewModel = viewModel;
	}

	/**
	 * Verwendung eines Vectors als Datenstruktur zur Aufnahme der Artikel, Händler,
	 * Personen und Mengenheiten entsprechend ihres generischen Typs.
	 */

	private Vector<Article> articleList;
	private Vector<Shop> shopList;
	private Vector<UnitOfMeasure> unitList;
	private Vector<Person> personList;

	/**
	 * Instanziierung aller notwendigen GUI Elemente
	 */

	private VerticalPanel contentPanel = new VerticalPanel(); // welches Panel??
	private HorizontalPanel btnPanel = new HorizontalPanel();
	private Grid ListGrid;
	private Button anlegenBtn = new Button("Anlegen");
	private Button saveBtn = new Button("Speichern");
	private Button deleteBtn = new Button("Liste löschen");
	private Button cancelBtn = new Button("Zurueck");

	private final TextBox amountTextBox = new TextBox();

	/**
	 * Generiert Artikel-Vorschläge via RPC Call zum Server
	 */

	private final ListBox articleListBox = new ListBox();

	/**
	 * ListBox, um die Auswahl aus vorgegebenen Werten zu ermöglichen
	 */

	private final ListBox unitListBox = new ListBox();
	private final ListBox personListBox = new ListBox();
	private final ListBox shopListBox = new ListBox();

	/**
	 * Anlegen eines Favorisieren-Buttons und einer Abhaken-CheckBox
	 */

	private Button standardizeBtn = new Button();
	private CheckBox check = new CheckBox();
	private final AsyncDataProvider<ShoppingList> asyncDataProvider;

	public ListItemForm(final ShoppingList selectedShoppingList,
			final ShoppingListsAsyncDataProvider asyncDataProvider) {
		this.selectedShoppingList = selectedShoppingList;
		this.asyncDataProvider = asyncDataProvider;

		/**
		 * Strukturierung der Darstellung mit Hilfe von Grid
		 */

		ListGrid = new Grid(7, 2);

		Label newArticleLabel = new Label("Artikel: ");
		ListGrid.setWidget(1, 0, newArticleLabel);
		ListGrid.setWidget(1, 1, articleListBox);

		Label newAmountLabel = new Label("Anzahl: ");
		ListGrid.setWidget(2, 0, newAmountLabel);
		ListGrid.setWidget(2, 1, amountTextBox);

		/*
		 * Hinzufügen von ClickHandlern
		 */

		Label newUnitLabel = new Label("Mengeneinheit: ");
		ListGrid.setWidget(3, 0, newUnitLabel);
		ListGrid.setWidget(3, 1, unitListBox);

		Label newPersonLabel = new Label("Person: ");
		ListGrid.setWidget(4, 0, newPersonLabel);
		ListGrid.setWidget(4, 1, personListBox);

		Label shopLabel = new Label("Haendler: ");
		ListGrid.setWidget(5, 0, shopLabel);
		ListGrid.setWidget(5, 1, shopListBox);

		btnPanel.add(anlegenBtn);
		btnPanel.add(saveBtn);
		btnPanel.add(deleteBtn);

		contentPanel.add(new ShoppingListForm());
		contentPanel.add(ListGrid);
		contentPanel.add(btnPanel);

		add(contentPanel);

		listenVerwaltung.getAllUnits(new AsyncCallback<Vector<UnitOfMeasure>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler");
			}

			@Override
			public void onSuccess(Vector<UnitOfMeasure> result) {
				unitList = result;
				for(UnitOfMeasure unit : result) {
					unitListBox.addItem(unit.getUnit(), unit.getId() + "");	
				}
			}
		});

		listenVerwaltung.getAllArticles(new AsyncCallback<Vector<Article>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler");
			}

			@Override
			public void onSuccess(Vector<Article> result) {
				articleList = result;
				for (Article article : result) {
					articleListBox.addItem(article.getName(), article.getId() + "");
				}
			}
		});
		listenVerwaltung.getAllPersonsOf( selectedShoppingList.getTeamId(), new AsyncCallback<Vector<Person>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler");
			}

			@Override
			public void onSuccess(Vector<Person> result) {
				personList = result;
				for (Person person : result) {
					personListBox.addItem(person.getFirstName() + " " + person.getLastName(), person.getId() + "");
				}
			}
		});
		listenVerwaltung.getAllShops(new AsyncCallback<Vector<Shop>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler");
			}

			@Override
			public void onSuccess(Vector<Shop> result) {
				shopList = result;
				for (Shop shop : result) {
					shopListBox.addItem(shop.getName(), shop.getId() + "");
				}
			}
		});

		final CellTable<Item> cellTable = new CellTable<Item>();

		final AsyncCallback<Vector<Item>> getAllCallback = new AsyncCallback<Vector<Item>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler");
			}

			@Override
			public void onSuccess(Vector<Item> result) {
				cellTable.setRowCount(result.size(), true);
				cellTable.setRowData(result);
			}
		};

		TextColumn<Item> idColumn = new TextColumn<Item>() {
			@Override
			public String getValue(Item object) {
				return "" + object.getId();
			}
		};
		TextColumn<Item> personCoumn = new TextColumn<Item>() {
			@Override
			public String getValue(Item object) {
				return object.getPersonName();
			}
		};
		TextColumn<Item> articleNameColumn = new TextColumn<Item>() {
			@Override
			public String getValue(Item object) {
				return object.getArticleName();
			}
		};
		TextColumn<Item> amountColumn = new TextColumn<Item>() {
			@Override
			public String getValue(Item object) {
				return NumberFormat.getFormat("###.###").format(object.getAmount());
			}
		};
		TextColumn<Item> unitColumn = new TextColumn<Item>() {
			@Override
			public String getValue(Item object) {
				return object.getUnitName();
			}
		};
		TextColumn<Item> shopColumn = new TextColumn<Item>() {
			@Override
			public String getValue(Item object) {
				return object.getShopName();
			}
		};
		TextColumn<Item> dateColumn = new TextColumn<Item>() {
			@Override
			public String getValue(Item object) { 
				return DateTimeFormat.getFormat("MM.dd.yyyy").format(object.getCreationDate());
			}
		};

		Column<Item, String> likeColumn = new Column<Item, String>(new ButtonCell()) {
			public String getValue(Item object) {
				return object.isFavorit() ? "Unlike" : "Like";
			}
		};
		likeColumn.setFieldUpdater(new FieldUpdater<Item, String>() {

			@Override
			public void update(int index, Item object, String value) {

				object.setFavorit(!object.isFavorit());
				listenVerwaltung.update(object, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						cellTable.redraw();
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");
					}
				});

			}
		});
		Column<Item, String> checkColumn = new Column<Item, String>(new ButtonCell()) {
			public String getValue(Item object) {
				return object.isStatus() ? "Uncheck" : "Check";
			}
		};
		checkColumn.setFieldUpdater(new FieldUpdater<Item, String>() {

			@Override
			public void update(int index, Item object, String value) {

				object.setStatus(!object.isStatus());
				listenVerwaltung.update(object, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						cellTable.redraw();
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");
					}
				});

			}
		});

		Column<Item, String> previewColumn = new Column<Item, String>(new ButtonCell()) {
			public String getValue(Item object) {
				return "Entfernen";
			}
		};
		previewColumn.setFieldUpdater(new FieldUpdater<Item, String>() {

			@Override
			public void update(int index, Item object, String value) {
				listenVerwaltung.delete(object, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");
					}

					@Override
					public void onSuccess(Void result) {
						listenVerwaltung.getAllItemsOfList(getSelectedShoppingList(), getAllCallback);
					}
				});
			}
		});

		Column<Item, String> editColumn = new Column<Item, String>(new ButtonCell()) {
			@Override
			public String getValue(Item object) {
				return "Editieren";
			}
		};
		editColumn.setFieldUpdater(new FieldUpdater<Item, String>() {
			@Override
			public void update(int index, Item object, String value) {
				setSelectedItem(object);
			}
		});

		cellTable.addColumn(idColumn, "Id");
		cellTable.addColumn(articleNameColumn, "Artikel");
		cellTable.addColumn(amountColumn, "Anzahl");
		cellTable.addColumn(unitColumn, "Einheit");
		cellTable.addColumn(personCoumn, "Person");
		cellTable.addColumn(shopColumn, "Shop");
		cellTable.addColumn(dateColumn, "Datum");
		cellTable.addColumn(likeColumn, "");
		cellTable.addColumn(checkColumn, "");
		cellTable.addColumn(previewColumn, "");
		cellTable.addColumn(editColumn, "");

		listenVerwaltung.getAllItemsOfList(selectedShoppingList, getAllCallback);

		add(cellTable);

		anlegenBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				int articleId = Integer.valueOf(articleListBox.getSelectedValue());
				float count = Float.valueOf(amountTextBox.getValue());
				int personId = Integer.valueOf(personListBox.getSelectedValue());
				int shopId = Integer.valueOf(shopListBox.getSelectedValue());
				int unitId = Integer.valueOf(unitListBox.getSelectedValue());

				listenVerwaltung.createItem(selectedShoppingList.getId(), selectedShoppingList.getTeamId(), count, unitId,
						articleId, personId, shopId, new AsyncCallback<Item>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Fehler");
							}

							@Override
							public void onSuccess(Item result) {
								listenVerwaltung.getAllItemsOfList(getSelectedShoppingList(), getAllCallback);
							}
						});
			}
		});
		saveBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (selectedItem != null) {
					int articleId = Integer.valueOf(articleListBox.getSelectedValue());
					float count = Float.valueOf(amountTextBox.getValue());
					int personId = Integer.valueOf(personListBox.getSelectedValue());
					int shopId = Integer.valueOf(shopListBox.getSelectedValue());
					int unitId = Integer.valueOf(unitListBox.getSelectedValue());

					selectedItem.setArticleId(articleId);
					selectedItem.setAmount(count);
					selectedItem.setPersonId(personId);
					selectedItem.setShopId(shopId);
					selectedItem.setUnitId(unitId);

					listenVerwaltung.update(selectedItem, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler");
						}

						@Override
						public void onSuccess(Void result) {
							listenVerwaltung.getAllItemsOfList(getSelectedShoppingList(), getAllCallback);
						}
					});
				} else {
					Window.alert("Es wurde nichts ausgewählt.");
				}
			}
		});
		deleteBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				listenVerwaltung.delete(selectedShoppingList, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Gelöscht");
						asyncDataProvider.refresh();
					}
				});
			}
		});
	}

	public ShoppingList getSelectedShoppingList() {
		return selectedShoppingList;
	}

	public void setSelectedItem(Item item) {
		selectedItem = item;

		for (int i = 0; i < unitList.size(); i++) {
			UnitOfMeasure unit = unitList.get(i);
			if(unit.getId() == item.getUnitId()) {
				unitListBox.setSelectedIndex(i);
			}
		}

		for (int i = 0; i < articleList.size(); i++) {
			Article article = articleList.get(i);
			if (article.getId() == item.getArticleId()) {
				articleListBox.setSelectedIndex(i);
			}
		}

		for (int i = 0; i < personList.size(); i++) {
			Person person = personList.get(i);
			if (person.getId() == item.getPersonId()) {
				personListBox.setSelectedIndex(i);
			}
		}

		for (int i = 0; i < shopList.size(); i++) {
			Shop shop = shopList.get(i);
			if (shop.getId() == item.getShopId()) {
				shopListBox.setSelectedIndex(i);
			}
		}

		amountTextBox.setValue(item.getAmount() + "");
	}

}