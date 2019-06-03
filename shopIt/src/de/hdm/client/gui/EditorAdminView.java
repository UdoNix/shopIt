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
	private HorizontalPanel btnPanel = new HorizontalPanel();
	private VerticalPanel navPanel = new VerticalPanel();
	
	//private CellTreeViewModel cellTreeViewModel;
	
	public EditorAdminView() {
		createGroup = new Button("Neue Gruppe");
		createGroup.addStyleName("menuButton");
		
		createList = new Button("Neue Einkaufsliste");
		createGroup.addStyleName("menuButton");
		
		btnPanel.add(createGroup);
		btnPanel.add(createList);
		
		btnPanel.setStyleName("navigationPanel");
		
		cellTreeViewModel = new CellTreeViewModel();
		navPanel.add(cellTreeViewModel.getStackMenuPanel());

		//cellTreeViewModel = new CellTreeViewModel();
		//navigationPanel.add(cellTreeViewModel.getStackMenuPanel());

		

		navPanel.add(cellTreeViewModel);

		//navigationPanel.add(cellTreeViewModel);
		
		mainPanel.add(btnPanel);
		mainPanel.add(navPanel);
		
		
	}
	
	public void loadEditor() {
		RootPanel.get("nav").add(mainPanel);
	}
 	
}
