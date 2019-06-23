package de.hdm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.shared.LoginService;
import de.hdm.shared.LoginServiceAsync;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Person;

//author emily kretzschmar

public class LogIn implements EntryPoint{

	ShopITAdministrationAsync shopITVerwaltung = ClientsideSettings.getShopItAdministrationAsync();
	
	private LoginInformation loginInfo = null;
	Person p = new Person();
	
	private Label vornameLabel = new Label ("Vorname:");
    private Label nachnameLabel = new Label ("Nachname:");
    
    private TextBox eingabeVorname = new TextBox();
    private TextBox eingabeNachname = new TextBox();
    
    private VerticalPanel panel = new VerticalPanel();
    private Label message = new Label ("Bitte mit dem Google Account einloggen.");
    private Label greet = new Label ("Herzlich Willkommen bei ShopIT ");
    private Anchor signInLink = new Anchor ("Einloggen");
    private Anchor signOutLink = new Anchor ("Ausloggen");
    private Button buttonlogin = new Button ("Login");
    
	public void onModuleLoad() {
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL()+ "ShopIt.html", new AsyncCallback<LoginInformation>() {
			public void onFailure (Throwable caught) {
				RootPanel.get().add(new HTML(caught.toString()));
			}
			
			public void onSuccess (LoginInformation result ) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					loadPersonAbruf
				}
	
