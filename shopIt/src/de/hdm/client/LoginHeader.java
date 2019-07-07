package de.hdm.client;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class LoginHeader extends FlowPanel {

	private FlowPanel loginPanel = null;
	private LoginInformation loginInformation;
	private Label currentUserLabel = new Label();

	public LoginHeader(LoginInformation loginInfo) {

		this.loginInformation = loginInfo;

		currentUserLabel.setText("Eingeloggt als " + loginInformation.getEmailAddress());
		add(currentUserLabel);
		

		Anchor signOutLink = new Anchor("Ausloggen");
		signOutLink.setHref(loginInformation.getLogoutURL());
		add(signOutLink);
	}
	
	public void setPanel(Widget panel) {
		this.loginPanel.clear();
		this.loginPanel.add(panel);
	}
	
}