package de.hdm.client;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.client.gui.Tree;

public class Layout extends HorizontalPanel {

	private VerticalPanel contentPanel = new VerticalPanel();
	private LoginInformation loginInformation;
	private Label currentUserLabel = new Label();

	public Layout(LoginInformation loginInformation) {
		
		this.loginInformation = loginInformation;
		add(new Tree(this));
		
		//contentPanel.addStyleName("contantPanel");
		add(contentPanel);
		
	}

	/**
	 * Aktiviert das Panel mit dem Inhalt
	 */
	public void setPanel(Widget panel) {
		this.contentPanel.clear();
		this.contentPanel.add(panel);
	}
}
