package de.hdm.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Article;

/**
 * 
 * Die <code>ArticleForm</code> wird verwendet um alle angelegten Artikel
 * anzuzeigen
 * 
 * @author Alexander Gerlings
 *
 */

public class ArticleForm extends VerticalPanel {

	ShopITAdministrationAsync listVerwaltung = ClientsideSettings.getShopItAdministrationAsync();
	Article articleToDisplay = null;
	
	/**
	 * Erzeugung der benötigten Panels der Klasse ArticleForm
	 */
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private HorizontalPanel contentPanel = new HorizontalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();

	/**
	 * Erzeugung der benötigten GUI-Elemente
	 */
	private SuggestBox articleBox = new SuggestBox(getAllArticles());
	private Button newArticle = new Button("Als neuen Atrikel anlegen");
	private Button saveArticle = new Button("Artikel speichern");
	private Button cancelButton = new Button("Abbrechen");

	/**
	 * Erzeugen der onLoad Methode
	 */
	public void onLoad() {
		// articleVerwaltung.getAllArticles(new GetAllArticleCallback(this));

		newArticle.addClickHandler(new AddClickHandler());
		saveArticle.addClickHandler(new SaveClickHandler());
		contentPanel.add(articleBox);

		buttonPanel.add(newArticle);
		buttonPanel.add(saveArticle);
		buttonPanel.add(cancelButton);

		mainPanel.add(contentPanel);
		mainPanel.add(buttonPanel);

		RootPanel.get("main").clear();
		RootPanel.get("main").add(mainPanel);
		// RootPanel.get("main").add(this);
	}

	private class AddClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String newArticle = articleBox.getText();
			listVerwaltung.createArticle(newArticle, new AddArticleCallback());
		}
	}

	class AddArticleCallback implements AsyncCallback<Article> {
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Es ist leider folgender Fehler aufgetreten: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Article arg0) {
			Window.alert("Artikel "+ arg0.getName() + "wurde erstellt!" );
		}
	}

	private class SaveClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			Article toChange = new Article();
			String name = toChange.getName();
			toChange = listVerwaltung.getArticleByName(toChange, new GetArticleCallback());
			
			listVerwaltung.save(a, callback);
		}
	}
	
	class GetArticleCallback implements AsyncCallback<Article>{

		@Override
		public void onFailure(Throwable arg0) {
			Window.alert("Artikel konnte nicht geladen werden!");
			
		}

		@Override
		public void onSuccess(Article arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

		private MultiWordSuggestOracle getAllArticles() {
			MultiWordSuggestOracle articleOracle = new MultiWordSuggestOracle();
			Vector<Article> articles = new Vector<Article>();
			articles = listVerwaltung.getAllArticles(new GetAllArticlesCallback());

			for (Article a : articles) {
				articleOracle.add(a.getName());
			}

			return articleOracle;
		}
		
		class GetAllArticlesCallback() {
			
		}
}
