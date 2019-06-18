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
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministration;
import de.hdm.server.ShopITAdministrationImpl;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.client.ShopIt;
import de.hdm.shared.bo.Article;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.UnitOfMeasure;

/*
 * Die Klasse <code>ListItemForm</code> dient dem Anlegen neuer Listeneinträge
 */

public class ListItemForm extends VerticalPanel{
	
	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
	
	/*
	 * Instanziieren und Zugriff ermöglichen auf die <code>ViewModel, ListToDisplay, ListShowCase</code>
	 */

	
	private CellTreeViewModel ViewModel = null;

	private ListForm ListToDisplay = null;

	private NumberFormat decimalFormatter = NumberFormat.getDecimalFormat();
	
	public CellTreeViewModel getViewModel() {
		return ViewModel;
	}

	public void setViewModel(CellTreeViewModel viewModel) {
		ViewModel = viewModel;
	}

	public ListForm getListToDisplay() {
		return ListToDisplay;
	}

	public void setListToDisplay(ListForm listToDisplay) {
		ListToDisplay = listToDisplay;
	}
	
	/*
	 * Instanziieren von den genannten Objekten, der Rest Realisierung über TextBox, da
	 * Inhalt komplett variabel
	 */
	
	private Article selectedArticle = null;
	private UnitOfMeasure selectedUnit = null;
	private Shop selectedShop = null;
	private Person selectedPerson= null;
	
	/*
	 * Verwendung einer „ArrayList“ als Datenstruktur zur Aufnahme der Händler,
	 * Personen und Mengenheiten entsprechend ihres generischen Typs
	 */
	
	private Vector<Article> articleList;
	private Vector<Shop> shopList;
	private Vector<UnitOfMeasure> unitList;
	private Vector<Person> personList;
	
	/*
	 * Instanziierung aller notwendigen GUI Elemente
	 */

	private VerticalPanel contentPanel = new VerticalPanel();
	private HorizontalPanel btnPanel = new HorizontalPanel();
	private Grid ListGrid;
	private Button saveBtn = new Button("Speichern");
	private Button cancelBtn = new Button("Zurueck");
	

	private TextBox amountTextBox = new TextBox();
	
	/*
	 * Generiert Artikel-Vorschläge via RPC Call zum Server
	 */
	
	private TextBox articleSuggestbox = new TextBox();
	
	/*
	 * ListBox, um die Auswahl aus vorgegebenen Werten zu ermöglichen
	 */
	
	private ListBox unitListBox = new ListBox();
	private ListBox personListBox = new ListBox();
	private ListBox shopListBox = new ListBox();


	
	public ListItemForm() {
		
		/*
		 * Strukturierung der Darstellung mit Hilfe von Grid
		 */
		
		ListGrid = new Grid(5, 2);
		
		Label newArticleLabel = new Label("Artikel: ");
		ListGrid.setWidget(0, 0, newArticleLabel);
		ListGrid.setWidget(0, 1, articleSuggestbox);
		articleSuggestbox.addChangeHandler((ChangeHandler) new ArticleSuggestBoxSelectionHandler());
		
		Label newAmountLabel = new Label("Anzahl: ");
		ListGrid.setWidget(1, 0, newAmountLabel);
		ListGrid.setWidget(1, 1, amountTextBox);
		
		/*
		 * Hinzufügen von ClickHandlern
		 */
		
		Label newUnitLabel = new Label("Mengeneinheit: ");
		ListGrid.setWidget(2, 0, newUnitLabel);
		ListGrid.setWidget(2, 1, unitListBox);
		unitListBox.addChangeHandler(new UnitListBoxChangeHandler());
		
		Label newPersonLabel = new Label("Person: ");
		ListGrid.setWidget(3, 0, newPersonLabel);
		ListGrid.setWidget(3, 1, personListBox);
		personListBox.addChangeHandler(new PersonListBoxChangeHandler());
		
		
		Label shopLabel = new Label("Haendler: ");
		ListGrid.setWidget(4, 0, shopLabel);
		ListGrid.setWidget(4, 1, shopListBox);
		shopListBox.addChangeHandler(new ShopListBoxChangeHandler());

		HorizontalPanel updateBtnPanel = new HorizontalPanel();
		ListGrid.setWidget(4, 1, updateBtnPanel);

		saveBtn.addClickHandler(new NewListitemClickHandler());
		saveBtn.setEnabled(true);
		

		cancelBtn.addClickHandler(new CancelClickhandler());
		cancelBtn.setEnabled(true);
		
		
		btnPanel.add(saveBtn);
		btnPanel.add(cancelBtn);
		contentPanel.add(ListGrid);
		contentPanel.add(btnPanel);

	}

	public void onLoad() {
		RootPanel.get("main").add(contentPanel);
	}
	
	private class GetAllArticlesCallback implements AsyncCallback<Vector<Article>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Article> result) {
			articleList = result;
			for (int i = 0; i < result.size(); i++) {
				articleList.addItem(result.get(i).getFirstName();
				articleList.addItem(result.get(i).getName());
				articleList = result.get(0);
		}
		
		
	}
	
	private class GetAllPersonsCallback implements AsyncCallback<Vector<Person>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Person> result) {
			personList = result;
			for (int i = 0; i < result.size(); i++) {
				personList.addItem(result.get(i).getFirstName();
				personList = result.get(0);
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
	 * Callback, der die Überführung von Serverdaten der Händler in die Dropdown-Liste ermöglicht.
	 */
	
	private class GetAllShopsCallback implements AsyncCallback<Vector<Shop>> {

		@Override
		public void onFailure(Throwable caught) {

		}
		
		@Override
		public void onSuccess(Vector<Shop> result) {
			shopList = result;
			for(int i = 0; i < result.size(); i++) {
				shopListBox.addItem(result.get(i).getName());
				selectedShop = result.get(0);
			}
			
		}
	}


	/*
	 * Mit Hilfe des SelectionHandlers wird ermöglicht einen vorgeschlagenen Artikel auszuwählen
	 */
	
	private class ArticleSuggestBoxSelectionHandler implements SelectionHandler<Article>{

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
	 * Mit Hilfe des ChangeHandlers wird ermöglicht einen Mengeneinheit aus der DropDown-Liste auszuwählen
	 */
	
	private class UnitListBoxChangeHandler implements ChangeHandler {
		public void onChange(ChangeEvent event) {

			int item = unitListBox.getSelectedIndex();
			selectedUnit = unitList.get(item);

		}
	}
	
	/*
	 * Mit Hilfe des ChangeHandlers wird ermöglicht einen Person aus der DropDown-Liste auszuwählen
	 */
	
	private class PersonListBoxChangeHandler implements ChangeHandler {
		public void onChange(ChangeEvent event) {
			
			int item = personListBox.getSelectedIndex();
			selectedPerson = personList.get(item);
		}
	}
	
	/*
	 * Mit Hilfe des ChangeHandlers wird ermöglicht einen Shop aus der DropDown-Liste auszuwählen
	 */
	
	private class ShopListBoxChangeHandler implements ChangeHandler {

		@Override
		public void onChange(ChangeEvent event) {
			
			int item = shopListBox.getSelectedIndex();
			selectedShop = shopList.get(item);
			
		}
		
	}
	

	/*
	 * Mit Hilfe des CancelClickhandlers wird ermöglicht, das Ausfüllen des Eintrags abzubrechen
	 */
	
	private class CancelClickhandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (ListToDisplay != null) {
				RootPanel.get("main").clear();
			}
		}

	}

	
	/*
	 * Der ClickHandler ermöglicht das Erstellen des Listeneintrags
	 */
	
	private class NewListitemClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
		
			if (ListToDisplay != null) {
				
				String article = articleSuggestbox.getSelectedText();
				float amount = 0.0F;
				
				try {
					
					NumberFormat decimalFormatter;
					amount = (float) decimalFormatter.parse(amountTextBox.getText());
					
				} catch (NumberFormatException formatexcep) {
					
				}
					Window.alert("ungültiger Wert!");
					return;
				}
				
				Article article = selectedArticle;
				UnitOfMeasure unit = selectedUnit;
				Shop shop = selectedShop;
				Person person = selectedPerson;
				

				ShopITAdministration.createListItem(ListToDisplay, article, amount, unit, person, shop,
						new CreateListItemCallback());
			
		} else {
			
				Window.alert("Keine Einkaufsliste ausgewaehlt :(. ");
		}
	}
}