package de.hdm.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.NumberFormat;
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

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministration;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Article;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.ShoppingList;
import de.hdm.shared.bo.UnitOfMeasure;

/*
 * Die Klasse <code>ListItemForm</code> dient dem Anlegen neuer Listeneinträge
 */

public class ListItemForm extends VerticalPanel {

	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();

	/*
	 * Instanziieren und Zugriff ermöglichen auf die <code>ViewModel, ListToDisplay,
	 * ListShowCase</code>
	 */

	private CellTreeViewModel ViewModel = null;

	private ShoppingList selectedShoppingList = null;

	private NumberFormat decimalFormatter = NumberFormat.getDecimalFormat();

	public CellTreeViewModel getViewModel() {
		return ViewModel;
	}

	public void setViewModel(CellTreeViewModel viewModel) {
		ViewModel = viewModel;
	}

	

	/*
	 * Instanziieren von den genannten Objekten, der Rest Realisierung über TextBox,
	 * da Inhalt komplett variabel
	 */

	private Article selectedArticle = null;
	private UnitOfMeasure selectedUnit = null;
	private Shop selectedShop = null;
	private Person selectedPerson = null;

	/*
	 * Verwendung eines Vectors als Datenstruktur zur Aufnahme der Artikel, Händler,
	 * Personen und Mengenheiten entsprechend ihres generischen Typs.
	 */

	private Vector<Article> articleList;
	private Vector<Shop> shopList;
	private Vector<UnitOfMeasure> unitList;
	private Vector<Person> personList;

	/*
	 * Instanziierung aller notwendigen GUI Elemente
	 */

	private VerticalPanel contentPanel = new VerticalPanel(); // welches Panel??
	private HorizontalPanel btnPanel = new HorizontalPanel();
	private Grid ListGrid;
	private Button saveBtn = new Button("Speichern");
	private Button cancelBtn = new Button("Zurueck");

	private TextBox amountTextBox = new TextBox();

	/*
	 * Generiert Artikel-Vorschläge via RPC Call zum Server
	 */

	private TextBox articleTextBox = new TextBox();

	/*
	 * ListBox, um die Auswahl aus vorgegebenen Werten zu ermöglichen
	 */

	private ListBox unitListBox = new ListBox();
	private ListBox personListBox = new ListBox();
	private ListBox shopListBox = new ListBox();

	/*
	 * Anlegen eines Favorisieren-Buttons und einer Abhaken-CheckBox
	 */

	private Button standardizeBtn = new Button();
	private CheckBox check = new CheckBox();

	public ListItemForm() {

		/*
		 * Strukturierung der Darstellung mit Hilfe von Grid
		 */

		ListGrid = new Grid(7, 2);

		Label newArticleLabel = new Label("Artikel: ");
		ListGrid.setWidget(1, 0, newArticleLabel);
		ListGrid.setWidget(1, 1, articleTextBox);

		Label newAmountLabel = new Label("Anzahl: ");
		ListGrid.setWidget(2, 0, newAmountLabel);
		ListGrid.setWidget(2, 1, amountTextBox);

		/*
		 * Hinzufügen von ClickHandlern
		 */

		Label newUnitLabel = new Label("Mengeneinheit: ");
		ListGrid.setWidget(3, 0, newUnitLabel);
		ListGrid.setWidget(3, 1, unitListBox);
//		unitListBox.addChangeHandler(new UnitListBoxChangeHandler());

		Label newPersonLabel = new Label("Person: ");
		ListGrid.setWidget(4, 0, newPersonLabel);
		ListGrid.setWidget(4, 1, personListBox);
//		personListBox.addChangeHandler(new PersonListBoxChangeHandler());

		Label shopLabel = new Label("Haendler: ");
		ListGrid.setWidget(5, 0, shopLabel);
		ListGrid.setWidget(5, 1, shopListBox);
//		shopListBox.addChangeHandler(new ShopListBoxChangeHandler());

		Label standardizeLabel = new Label("favorisieren: ");
		ListGrid.setWidget(6, 0, standardizeLabel);
		ListGrid.setWidget(6, 1, standardizeBtn);
//		standardizeBtn.addClickHandler(new StandardizeClickHandler);

		HorizontalPanel updateBtnPanel = new HorizontalPanel();
//		ListGrid.setWidget(, , updateBtnPanel);

//		saveBtn.addClickHandler(new NewListitemClickHandler());
		saveBtn.setEnabled(true);

//		cancelBtn.addClickHandler(new CancelClickhandler());
		cancelBtn.setEnabled(true);

		btnPanel.add(saveBtn);
		btnPanel.add(cancelBtn);
		
		contentPanel.add(new ListForm());
		contentPanel.add(ListGrid);
		contentPanel.add(btnPanel);
		
		add(contentPanel);

	}
	
	public void setSelectedShoppingList(ShoppingList selectedShoppingList) {
		this.selectedShoppingList = selectedShoppingList;
	}

	private class GetAllArticlesCallback implements AsyncCallback<Vector<Article>> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Vector<Article> result) {

			articleList = result;

			for (int i = 0; i < result.size(); i++) {

				selectedArticle = result.get(0);
			}

		}

		private class GetAllPersonsOfTeamCallback implements AsyncCallback<Vector<Person>> {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Vector<Person> result) {

				personList = result;

				for (int i = 0; i < result.size(); i++) {
					personListBox.addItem(result.get(i).getFirstName());
					selectedPerson = result.get(0);
				}

			}

		}

		/**
		 * Zum Befüllen der Dropdown-Liste mit <code>Unit</code>.
		 */

		private class GetAllUnitOfMeasureCallback implements AsyncCallback<Vector<UnitOfMeasure>> {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Vector<UnitOfMeasure> result) {
				unitList = result;
				for (int i = 0; i < result.size(); i++) {
					unitListBox.addItem(result.get(i).getUnit());
					selectedUnit = result.get(0);
				}
			}

		}

		/*
		 * Callback, der die Überführung von Serverdaten der Händler in die
		 * Dropdown-Liste ermöglicht.
		 */

		private class GetAllShopsCallback implements AsyncCallback<Vector<Shop>> {

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Vector<Shop> result) {
				shopList = result;
				for (int i = 0; i < result.size(); i++) {
					shopListBox.addItem(result.get(i).getName());
					selectedShop = result.get(0);
				}

			}
		}

		/*
		 * Mit Hilfe des SelectionHandlers wird ermöglicht einen vorgeschlagenen Artikel
		 * auszuwählen
		 */

		private class ArticleSuggestBoxSelectionHandler implements SelectionHandler<Article> {

			@Override
			public void onSelection(SelectionEvent<Article> event) {
				Article selectedItem;
				if (event.getSelectedItem() != null)
					selectedItem = (Article) event.getSelectedItem();
				else
					selectedItem = (Article) event.getSource();

			}

		}

		/*
		 * Mit Hilfe des ChangeHandlers wird ermöglicht einen Mengeneinheit aus der
		 * DropDown-Liste auszuwählen
		 */

		private class UnitListBoxChangeHandler implements ChangeHandler {
			public void onChange(ChangeEvent event) {

				int item = unitListBox.getSelectedIndex();
				selectedUnit = unitList.get(item);

			}
		}

		private class StandardizeClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {

			}
		}

		/*
		 * Mit Hilfe des ChangeHandlers wird ermöglicht einen Person aus der
		 * DropDown-Liste auszuwählen
		 */

		private class PersonListBoxChangeHandler implements ChangeHandler {
			public void onChange(ChangeEvent event) {

				int item = personListBox.getSelectedIndex();
				selectedPerson = personList.get(item);
			}
		}

		/*
		 * Mit Hilfe des ChangeHandlers wird ermöglicht einen Shop aus der
		 * DropDown-Liste auszuwählen
		 */

		private class ShopListBoxChangeHandler implements ChangeHandler {

			@Override
			public void onChange(ChangeEvent event) {

				int item = shopListBox.getSelectedIndex();
				selectedShop = shopList.get(item);

			}

		}

		/*
		 * Mit Hilfe des CancelClickhandlers wird ermöglicht, das Ausfüllen des Eintrags
		 * abzubrechen
		 */

		private class CancelClickhandler implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				
			}

		}

	}
}