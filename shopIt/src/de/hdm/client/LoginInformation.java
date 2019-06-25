package de.hdm.client;

import java.io.Serializable;

public class LoginInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean loggedIn = false;
	private String loginURL = "";
	private String logoutURL = "";
	private String emailAddress = "";

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getLoginURL() {
		return loginURL;
	}

	public void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}

	public String getLogoutURL() {
		return logoutURL;
	}

	public void setLogoutURL(String logoutURL) {
		this.logoutURL = logoutURL;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}



}
