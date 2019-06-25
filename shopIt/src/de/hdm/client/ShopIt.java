package de.hdm.client;

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

public class ShopIt implements EntryPoint {

	private LoginInformation loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Klicken Sie hier, um sich mit ihrem Google Konto anzumelden!");
	private Anchor signInLink = new Anchor ("Einloggen");
	private Anchor signOutLink = new Anchor ("Ausloggen");


	public void onModuleLoad() {

		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInformation>() {

			@Override
			public void onSuccess(LoginInformation result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					loadShopIt();
				} else {
					loadLogin();
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Login fehlgeschlagen");

			}
		});
	}

	private void loadLogin() {
		signInLink.setHref(loginInfo.getLoginURL());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		//TODO richtiges Rootpanel setzen
		RootPanel.get("ROOTPANEL").add(loginPanel);
		
	}

	private void loadShopIt() {
		signOutLink.setHref(loginInfo.getLogoutURL());
	}
}
