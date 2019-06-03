package de.hdm.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ArticleForm extends VerticalPanel {
	
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private HorizontalPanel contentPanel = new HorizontalPanel();
	private Button newArticle = new Button("neuer Artikel");
	private Button cancelButton = new Button("Abbrechen");
	
	private Label nameArticle;
	private Label numberArticle;
	private Grid grid = new Grid(1 ,1);
	
	public void onLoad() {
		buttonPanel.add(newArticle);
		buttonPanel.add(cancelButton);
		//this.add(buttonPanel);
		
		nameArticle = new Label("Name: ");
		numberArticle = new Label("Anzahl: ");
		contentPanel.add(buttonPanel);
		contentPanel.add(nameArticle);
		contentPanel.add(numberArticle);
		contentPanel.add(grid);
		//this.add(contentPanel);
		
		RootPanel.get("main").clear();
		RootPanel.get("main").add(buttonPanel);
		RootPanel.get("main").add(contentPanel);
		//RootPanel.get("main").add(this);
	}
	
	
}
