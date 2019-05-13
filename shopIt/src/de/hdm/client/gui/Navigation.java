package de.hdm.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Navigation {
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private VerticalPanel vPanel = new VerticalPanel();
	private Button btnCreateGroup, btnCreateArticle, btnGroupNav, btnListNav, btnArticleNav;
	
	
	public void loadEditor() {
		btnCreateGroup = new Button("New Group");
		btnCreateGroup.addStyleName("button");
		hPanel.add(btnCreateGroup);
		
		btnCreateArticle = new Button("New Article");
		btnCreateArticle.addStyleName("button");
		hPanel.add(btnCreateArticle);
		
		btnGroupNav = new Button("open Groups");
		btnGroupNav.addStyleName("button");
		vPanel.add(btnGroupNav);
		
		btnListNav = new Button("open Lists");
		btnListNav.addStyleName("button");
		vPanel.add(btnListNav);
		
		btnArticleNav = new Button("open Article");
		btnArticleNav.addStyleName("button");
		vPanel.add(btnArticleNav);
		
		mainPanel.add(hPanel);
		mainPanel.add(vPanel);
		
		RootPanel.get("nav").add(mainPanel);
	}
	
}
