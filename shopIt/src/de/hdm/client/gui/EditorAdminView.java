package de.hdm.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditorAdminView {
	private Button createGroup;
	private Button createList;
	private Button Logout;
	
	private HorizontalPanel topPanel = new HorizontalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	private VerticalPanel navigationPanel = new VerticalPanel();
	
	private CellTreeViewModel cellTreeViewModel;
	
	public EditorAdminView() {
		createGroup = new Button("Neue Gruppe");
		createGroup.addStyleName("menuButton");
		
		createList = new Button("Neue Einkaufsliste");
		createGroup.addStyleName("menuButton");
		
		navigationPanel.add(createGroup);
		navigationPanel.add(createList);
		
		navigationPanel.setStyleName("navigationPanel");
		
		cellTreeViewModel = new CellTreeViewModel();
		navigationPanel.add(cellTreeViewModel.getStackMenuPanel());
		
		navigationPanel.add(cellTreeViewModel);
		
		topPanel.add(navigationPanel);
		
		
	}
	
	public void loadEditor() {
		RootPanel.get().add(topPanel);
	}
 	
}
