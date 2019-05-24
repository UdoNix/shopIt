package de.hdm.client.gui;

import com.google.gwt.i18n.client.DateTimeFormat;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.shared.bo.Group;
import de.hdm.shared.bo.List;


public class GroupForm extends Form {
	/**
	 * Erstellung verschiedener Panels
	 */
	private VerticalPanel contentPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private VerticalPanel MemberPanel = new VerticalPanel();

	/**
	 * Erstellung benötigter GUI-Elemente
	 */
	
	private Label name = new Label();
	private Label creationTime = new Label();
	private Label NumberOfMembers = new Label();
	
	private Button editGroupBtn = new Button("Gruppe bearbeiten");
	private Button deleteGroupBtn = new Button("Gruppe löschen");
	private Button abortDeletionButton = new Button("Abbrechen");

	/*
	 * 
	 */
	
	
	/*
	 * 
	 */

	private DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy k:mm");

	private Group group = new Group();
	
	/*
	 * 
	 */

	GroupForm(long serializableID) {

		Group g = new Group();
		
		g.setSerializableID(serializableID);

		this.addStyleName("groupForm");
		
		ListForm listForm = new ListForm(serializableID);

		this.add(listForm);

		//super.onLoad();

	}

	public GroupForm() {
		// TODO Auto-generated constructor stub
	}

	public void loadGroup(){
		
			editGroupBtn = new Button("Gruppe bearbeiten");
			deleteGroupBtn = new Button("Gruppe löschen");
			abortDeletionButton = new Button("Abbrechen");
			
			contentPanel.add(editGroupBtn);
			contentPanel.add(deleteGroupBtn);
			contentPanel.add(abortDeletionButton);
			contentPanel.add(name);
			//contentPanel.add(idLabel);
			contentPanel.add(creationTime);
			RootPanel.get().clear();
			RootPanel.get("main").add(contentPanel);
			
		}
}	
