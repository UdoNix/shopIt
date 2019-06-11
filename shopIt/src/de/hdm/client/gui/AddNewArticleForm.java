package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AddNewArticleForm extends VerticalPanel {
	
	private HorizontalPanel firstPanel = new HorizontalPanel();
	private HorizontalPanel secondPanel = new HorizontalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	
	private Button safeArticle = new Button("Artikel speichern");
	private Button cancelButton = new Button("Abbrechen");
	
	private Label nameArticle;
	private Label idArticle;
	private TextBox artName = new TextBox();
	private TextBox artNum = new TextBox();
	
	public void onLoad() {
		nameArticle = new Label("Name");
		idArticle = new Label("ID");
		
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
		
		RootPanel.get("main").clear();
		RootPanel.get("main").add(mainPanel);
		
	}
	
	private class SafeNewArticle implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class CancelNewArticle implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
