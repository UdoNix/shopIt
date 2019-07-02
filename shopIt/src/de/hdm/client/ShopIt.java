package de.hdm.client;

import java.util.Vector;

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

	private LoginInformation loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Klicken Sie hier, um sich mit ihrem Google Konto anzumelden!");
	private Anchor signInLink = new Anchor("Einloggen");
	private Anchor signOutLink = new Anchor("Ausloggen");

	public void onModuleLoad() {

		RootPanel.get("content").add(new Layout(new LoginInformation()));

		ShopITAdministrationAsync admin = ClientsideSettings.getShopItAdministration();
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInformation>() {

			@Override
			public void onSuccess(LoginInformation result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					loadShopIt(result);

					Person currentUser = new Person();
					currentUser.setEmail(result.getEmailAddress());
					currentUser.setFirstName("Ihr Vorname");
					currentUser.setLastName("Ihr Nachname");

					admin.getPersonByEmail(result.getEmailAddress(), new FindByMailCallback());

				} else {
					loadLogin();
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				loadLogin();
			}
		});
	}

	private void loadLogin() {
		RootPanel.get("content").clear();
		signInLink.setHref(loginInfo.getLoginURL());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get("content").add(signInLink);

	}

	private void loadShopIt(LoginInformation loginInformation) {
		RootPanel.get("content").clear();
		RootPanel.get("content").add(new Layout(loginInformation));
	}

	private class FindByMailCallback implements AsyncCallback<Person> {

		@Override
		public void onFailure(Throwable arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Person p) {
			// TODO Auto-generated method stub

		}

	}

}
