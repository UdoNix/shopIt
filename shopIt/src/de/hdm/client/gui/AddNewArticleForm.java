package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Article;

	/**
	 * Die Klasse <code>AddNewArticleForm</code> dient zur Erstellung neuer Artikel
	 * welche durch den eingeloggten User angelegt werden k�nnen.
	 * @author Alexander Gerlings
	 */

public class AddNewArticleForm extends VerticalPanel {
	
	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
	
	/*
	 * Erstellung der Panels
	 */
	private ArticleForm acForm;
	private HorizontalPanel firstPanel = new HorizontalPanel();
	private HorizontalPanel secondPanel = new HorizontalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	
	/*
	 * Erstellen der benötigten GUI-Elemente
	 */
	
	private Button safeArticle = new Button("Artikel speichern");
	private Button cancelButton = new Button("Abbrechen");
	
	private Label nameArticle = new Label("Name");
	private Label idArticle = new Label("ID");
	private Grid grid = new Grid(1, 1);
	private TextBox artName = new TextBox();
	private TextBox artNum = new TextBox();
	
	/*
	 * Anlegen des Konstruktors
	 */
	public AddNewArticleForm() {		
		
//		grid.setWidget(0, 0, idArticle);
//		grid.setWidget(0, 1, artNum);
//		
//		grid.setWidget(1, 1, nameArticle);
//		grid.setWidget(1, 2, artName);
//		
//		mainPanel.add(grid);
		/*
		 * Hier werden die Panels angeordnet und mit den Gui-Elementen zusammengef�gt
		 */
		firstPanel.add(idArticle);
		firstPanel.add(artNum);
		mainPanel.add(firstPanel);
		
		secondPanel.add(nameArticle);
		secondPanel.add(artName);
		mainPanel.add(secondPanel);
		
		buttonPanel.add(safeArticle);
		safeArticle.addClickHandler(new SafeNewArticle());
		buttonPanel.add(cancelButton);
		cancelButton.addClickHandler(new CancelNewArticle());
		mainPanel.add(buttonPanel);
	}
	
	public void onLoad() {
		RootPanel.get("main").clear();
		RootPanel.get("main").add(mainPanel);
		
	}
	
	private class SafeNewArticle implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String articleName = artName.getValue();
			listenVerwaltung.createArticle(articleName, new CreateNewArticleCallback());
			}
		
	}
	
	/**
	 * <code>CancelNewArticle</code> stellt den ClickHandler dar, 
	 * mit welchem man das Anlegen eines Artikels abbrechen kann.
	 * Durch onClick wird man zur�ck auf die ArticleForm gebracht.
	 * @author Alexander Gerlings
	 *
	 */
	private class CancelNewArticle implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			acForm = new ArticleForm();
			acForm.onLoad();
		}
		
	}
	
	/**
	 * Callback Klasse 
	 * @author Alexander Gerlings
	 *
	 */
	private class CreateNewArticleCallback implements AsyncCallback<Article> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Es ist leider folgender Fehler aufgetreten: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Article result) {
			// TODO Auto-generated method stub
			Window.alert("Der Artikel " + artName.getValue() + "wurde angelegt");
			
		}
	}
	
}
