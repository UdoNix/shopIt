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


public class GroupForm extends VerticalPanel {
	/**
	 * Erstellung verschiedener Panels
	 */
	private VerticalPanel contentPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	//private VerticalPanel MemberPanel = new VerticalPanel();

	/**
	 * Erstellung benötigter GUI-Elemente
	 */
	
	private Label name = new Label();
	private Label creationTime = new Label();
	private Label NumberOfMembers = new Label();
	
	private Button editGroupBtn = new Button("Gruppe bearbeiten");
	private Button deleteGroupBtn = new Button("Gruppe löschen");
	private Button cancelBtn = new Button("Abbrechen");

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

	public void onLoad(GroupForm group){
		
			editGroupBtn = new Button("Gruppe bearbeiten");
			deleteGroupBtn = new Button("Gruppe löschen");
			cancelBtn = new Button("Abbrechen");
			
			buttonPanel.add(editGroupBtn);
			buttonPanel.add(deleteGroupBtn);
			buttonPanel.add(cancelBtn);
			contentPanel.add(name);
			//contentPanel.add(idLabel);
			contentPanel.add(creationTime);
			RootPanel.get("main").add(contentPanel);
			
		}

	public void setSelected(Group selectedGroup) {
		// TODO Auto-generated method stub
		
	}
}	
