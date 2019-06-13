package de.hdm.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.client.ShopIt;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Shop;
import de.hdm.shared.bo.UnitOfMeasure;

/*
 * Die Klasse <code>ListItemForm</code> dient dem Anlegen neuer Listeneinträge
 */

public class ListItemForm extends VerticalPanel{
	
	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();

	
	private CellTreeViewModel ViewModel = null;
	private ListForm ListToDisplay = null;

	/*
	 * Instanziieren von Unit- und Shop-Objekten, der Rest Realisierung über TextBox, da
	 * Inhalt komplett variabel
	 */
	
	private UnitOfMeasure selectedUnit = null;
	private Shop selectedShop = null;
	
	/*
	 * Verwendung einer „ArrayList“ als Datenstruktur zur Aufnahme der Händler,
	 * Personen und Mengenheiten entsprechend ihres generischen Typs
	 */
	
	private ArrayList<Shop> shopList;
	private ArrayList<UnitOfMeasure> unitList;
	private ArrayList<Person> personList;
	
	/*
	 * Instanziierung aller notwenigen GUI Elemente
	 */

	private VerticalPanel mainPanel = new VerticalPanel();
	private Grid ListGrid;
	

	private TextBox amountTextBox = new TextBox();
	
	/*
	 * Generiert Artikel-Vorschläge via via RPC Call zum Server
	 */
	
	private TextBox articleSuggestbox = new TextBox();
	
	/*
	 * ListBox, um die Auswahl aus vorgegebenen Werten zu ermöglichen
	 */
	
	private ListBox unitListBox = new ListBox();
	private ListBox personListBox = new ListBox();
	private ListBox shopListBox = new ListBox();

	private Button saveBtn = new Button("Speichern");
	private Button cancelBtn = new Button("Zurueck");
	
	public ListItemForm() {
		
		/*
		 * Strukturierung der Darstellung mit Hilfe von Grid
		 */
		
		
		ListGrid = new Grid(5, 2);
		
		Label newArticleLabel = new Label("Artikel: ");
		ListGrid.setWidget(0, 0, newArticleLabel);
		ListGrid.setWidget(0, 1, articleSuggestbox);
		
		Label newAmountLabel = new Label("Anzahl: ");
		ListGrid.setWidget(1, 0, newAmountLabel);
		ListGrid.setWidget(1, 1, amountTextBox);
	}
}
		
		