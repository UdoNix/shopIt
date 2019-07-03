package de.hdm.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.shared.LoginService;
import de.hdm.shared.LoginServiceAsync;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Person;

public class ShopIt implements EntryPoint {

	ShopITAdministrationAsync admin = ClientsideSettings.getShopItAdministration();
	Logger logger = ClientsideSettings.getLogger();
	private LoginInformation loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Klicken Sie hier, um sich mit ihrem Google Konto anzumelden!");
	private Anchor signInLink = new Anchor("Einloggen");

	public void onModuleLoad() {

		// RootPanel.get("content").add(new Layout(new LoginInformation()));

		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInformation>() {

			@Override
			public void onSuccess(LoginInformation result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {

					Window.alert(loginInfo.getEmailAddress());
					loadShopIt(loginInfo);

				} else {
					loadLogin();
				}
			}

			@Override
			public void onFailure(Throwable error) {
				Window.alert(error.getMessage());
				loadLogin();
			}
		});
	}

	private void loadLogin() {
		RootPanel.get("content").clear();
		signInLink.setHref(loginInfo.getLoginURL());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("content").add(loginPanel);

	}

	private void loadShopIt(LoginInformation loginInformation) {
		RootPanel.get("content").clear();
		RootPanel.get("content").add(new Layout(loginInformation));
		
		admin.getPersonByEmail(loginInformation.getEmailAddress(), new FindByMailCallback());
	}

	class FindByMailCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable error) {
			logger.log(Level.SEVERE, error.getMessage());
			Window.alert("Noch kein Benutzer mit dieser Email angelegt. Neuer Benutzer wurde erstellt!");
			Window.alert("�ndern Sie Ihren Vor- und Nachnamen im Account Bereich.");

			admin.createPerson("Ihr Vorname", "Ihr Nachname", loginInfo.getEmailAddress(), new CreatePersonCallback());
		}

		@Override
		public void onSuccess(Person p) {

			Window.alert(p.getEmail() + " existiert bereits und wurde aus der Datenbank geladen.");
			ClientsideSettings.setCurrentUser(p);
		}

	}

	class CreatePersonCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable error) {
			logger.log(Level.SEVERE, error.getMessage());
		}

		@Override
		public void onSuccess(Person p) {
			p.setEmail(loginInfo.getEmailAddress());
			ClientsideSettings.setCurrentUser(p);
			Window.alert("geklappt!");
		}
	}
}
