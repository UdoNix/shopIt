package de.hdm.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NavigatorForm {
	private HorizontalPanel mainPanel = new HorizontalPanel();
	private VerticalPanel topPanel = new VerticalPanel();
	private VerticalPanel mainMenuPanel = new VerticalPanel();
	
	
	private Button btnCreateGroup, btnLogout;
	private Button btnOpenGroup, btnOpenList, btnOpenListItem, btnOpenArticle;
	
	
	
	public NavigatorForm() {
		
		topPanel.add(btnCreateGroup);
		mainMenuPanel.add(btnOpenGroup);
		mainMenuPanel.add(btnOpenList);
		mainMenuPanel.add(btnOpenListItem);
		mainMenuPanel.add(btnOpenArticle);
		
		topPanel.setStyleName("topPanel");
		
		RootPanel.get("nav").add(topPanel);
		RootPanel.get("nav").add(mainMenuPanel);
		
	}
	
}
