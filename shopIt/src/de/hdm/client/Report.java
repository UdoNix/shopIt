package de.hdm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.gui.report.SelectMenu;
import de.hdm.shared.LoginService;
import de.hdm.shared.LoginServiceAsync;
import de.hdm.shared.ReportGeneratorAsync;
import de.hdm.shared.bo.Person;

public class Report implements EntryPoint {

	ReportGeneratorAsync reportVerwaltung = ClientsideSettings.getReportGenerator();
	private LoginInformation loginInformation = null;

	Person p = new Person();

	private Label firstNameLabel = new Label("Vorname");
	private TextBox firstNameBox = new TextBox();
	private Label lastNameLabel = new Label("Nachname");
	private TextBox lastNameBox = new TextBox();

	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginGreet = new Label("Willkommen!");
	private Label loginMessage = new Label("Bitte logge dich mit deinem Google Account ein");
	private Anchor signInLink = new Anchor("Einloggen");
	private Anchor signOutLink = new Anchor("Ausloggen");
	private Button loginButton = new Button("Login :)");

	public Report() {
		
	}

	public void onModuleLoad() {
		Window.alert("Report");
	}

	public void onFailure(Throwable caught) {
		RootPanel.get().add(new HTML(caught.toString()));
	}

	public void onSuccess(LoginInformation result) {

		loginInformation = result;

		if (loginInformation.isLoggedIn()) {
			loadPersonAbruf(result);
		} else {
			loadLogin();
		}
	}

	private void loadLogin() {

		loginPanel.add(loginGreet);
		loginPanel.add(loginMessage);
		loginButton.addClickHandler(new LoginButtonClickHandler());
		loginPanel.add(loginButton);

		RootPanel.get("contentReport").clear();
		RootPanel.get("contentReport").add(loginPanel);
	}

	class LoginButtonClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			signInLink.setHref(loginInformation.getLoginURL());
			Window.open(signInLink.getHref(), "_self", "");
		}
	}

	private void loadPersonAbruf(LoginInformation loginInformation) {
		// reportVerwaltung.findPersonByEmail(loginInformation.getEmailAddress(),
		// new PersonAbrufCallback());
	}

	private void loadReportGenerator() {
		SelectMenu reportMenu = new SelectMenu();
		RootPanel.get("header").add(reportMenu);
	}

	class PersonAbrufCallback implements AsyncCallback<Person> {

		public void onFailure(Throwable caught) {
			Window.alert("Loginfehler :'(" + caught.getMessage());
		}

		public void onSuccess(Person result) {
			if (result != null) {
				Cookies.setCookie("email", result.getEmail());
				Cookies.setCookie("id", result.getId() + "");
				RootPanel.get("contentPanel").clear();
				loadReportGenerator();
			} else {
				Window.alert("Bitte erst registrieren.");
				signOutLink.setHref(loginInformation.getLogoutURL());
				Window.open(signOutLink.getHref(), "_self", "");
			}

		}

	}

}
