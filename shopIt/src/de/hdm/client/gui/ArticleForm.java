package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/*
 * 
 *@author Alexander Gerlings
 * 
 */


public class ArticleForm extends VerticalPanel {
	
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private HorizontalPanel contentPanel = new HorizontalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	private Button newArticle = new Button("neuer Artikel");
	private Button cancelButton = new Button("Abbrechen");
	private AddNewArticleForm newArticleForm = new AddNewArticleForm();
	
	private Label nameArticle;
	private Label numberArticle;
	private Grid grid = new Grid(1 ,1);
	
	public void onLoad() {
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
}
