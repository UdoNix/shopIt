package de.hdm.client;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LoginHeader extends FlowPanel {

	//private FlexTable layout = null;
	private FlowPanel loginPanel = null;
	private LoginInformation loginInformation;
	private Label currentUserLabel = new Label();

	public LoginHeader(LoginInformation loginInfo) {

		this.loginInformation = loginInfo;

		currentUserLabel.setText("Eingeloggt als " + loginInformation.getEmailAddress());
		//layout.setWidget(0, 0, currentUserLabel);
		add(currentUserLabel);
		
		//layout.setText(0, 1, "     ");

		Anchor signOutLink = new Anchor("Ausloggen");
		signOutLink.setHref(loginInformation.getLogoutURL());
		//layout.setWidget(0, 2, signOutLink);
		add(signOutLink);
	}
	
	public void setPanel(Widget panel) {
		this.loginPanel.clear();
		this.loginPanel.add(panel);
	}
	
}
