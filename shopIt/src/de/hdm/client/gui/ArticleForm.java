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
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Article;

@Deprecated
	/**
	 * 
	 * Die <code>ArticleForm</code> wird verwendet um alle angelegten Artikel anzuzeigen
	 * @author Alexander Gerlings
	 *
	 */


public class ArticleForm extends VerticalPanel {
	
	ShopITAdministrationAsync articleVerwaltung = ClientsideSettings.getShopItAdministration();
	
	
	
	/**
	 * Erzeugung der ben�tigten Panels der Klasse ArticleForm
	 */
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private HorizontalPanel contentPanel = new HorizontalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	
	/**
	 * Erzeugung der ben�tigten GUI-Elemente
	 */
	private Button newArticle = new Button("neuer Artikel");
	private Button cancelButton = new Button("Abbrechen");
	private AddNewArticleForm newArticleForm = new AddNewArticleForm();
	
	private Label nameArticle;
	private Label numberArticle;
	private Grid grid = new Grid(1 ,1);
	
	/**
	 * Erzeugen der onLoad Methode
	 */
	public void onLoad() {
		//articleVerwaltung.getAllArticles(new GetAllArticleCallback(this));
		newArticle.addClickHandler(new OpenNewArticleForm());
		buttonPanel.add(newArticle);
		
		buttonPanel.add(cancelButton);
		//this.add(buttonPanel);
		
		
		nameArticle = new Label("Name: ");
		
		numberArticle = new Label("Anzahl: ");
		contentPanel.add(buttonPanel);
		contentPanel.add(nameArticle);
		mainPanel.add(contentPanel);
		
		contentPanel.add(numberArticle);
		mainPanel.add(contentPanel);
		contentPanel.add(grid);
		mainPanel.add(contentPanel);
		//this.add(contentPanel);
		
		RootPanel.get("main").clear();
		RootPanel.get("main").add(buttonPanel);
		RootPanel.get("main").add(mainPanel);
		//RootPanel.get("main").add(this);
	}

	
	private class OpenNewArticleForm implements ClickHandler {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			newArticleForm.onLoad();
		}
	
	}
	
	class GetAllArticleCallback implements AsyncCallback<Article> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Es ist leider folgender Fehler aufgetreten: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Article result) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
