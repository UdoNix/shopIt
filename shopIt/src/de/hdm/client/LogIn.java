package de.hdm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.gui.TeamView;
import de.hdm.shared.LoginService;
import de.hdm.shared.LoginServiceAsync;
import de.hdm.client.gui.Tree;
import de.hdm.shared.bo.Person;

//author emily kretzschmar

@Deprecated
public class LogIn implements EntryPoint{

	//ShopITAdministrationAsync shopITVerwaltung = ClientsideSettings.getShopItAdministrationAsync();
	
//	static interface BankTreeResources extends CellTree.Resources {
//		@Source("cellTreeClosedItem.gif")
//	    ImageResource cellTreeClosedItem();
//
//		@Source("cellTreeOpenItem.gif")
//	    ImageResource cellTreeOpenItem();
//
//		@Source("BankCellTree.css")
//	    CellTree.Style cellTreeStyle(); 
//	}
	
	private LoginInformation loginInfo = null;
	Person p = new Person();
	
	private Label emailLabel = new Label ("Google-Email:");
	private Label vornameLabel = new Label("Vorname");
    private Label nachnameLabel = new Label("Nachname");
    
    private TextBox emailTextBox = new TextBox();
	private TextBox vornameEingabe = new TextBox();
	private TextBox nachnameEingabe = new TextBox();
		
    private VerticalPanel panel = new VerticalPanel();
    private Label message = new Label ("Bitte mit dem Google Account einloggen.");
    private Button register = new Button();

    
    private Label greet = new Label ("Herzlich Willkommen bei ShopIT ");
    private Anchor signInLink = new Anchor ("Einloggen");
    private Anchor signOutLink = new Anchor ("Ausloggen");
    private Button buttonlogin = new Button ("Login");
    
    @Override
	public void onModuleLoad() {
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInformation>() {
			
			@Override
			public void onSuccess(LoginInformation result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					return;
				}
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				Window.alert("Login fehlgeschlagen");
			}
		});
		
		//RootPanel.get().add (new ShopView());
		RootPanel.get().add( new Layout());
		
		
//		loginService.login(GWT.getHostPageBaseURL()+ "ShopIt.html", new AsyncCallback<LoginInformation>() {
//			public void onFailure (Throwable caught) {
//				RootPanel.get().add(new HTML(caught.toString()));
//			}
//			
//			public void onSuccess (LoginInformation result ) {
//				loginInfo = result;
//				if (loginInfo.isLoggedIn()) {
//					loadPersonAbruf(result);
//				} else {
//					loadLogin();
//					
//				}
//			}
//		});
	}
	
	/*private void loadLogin () {
		buttonlogin.addClickHandler (new LoginButtonClickHandler());
		panel.add(greet);
		panel.add(message);
		panel.add(buttonlogin);
		
		RootPanel.get().clear();
		RootPanel.get().add (panel);
	}
	
	class LoginButtonClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			signInLink.setHref(loginInfo.getLoginURL());
			Window.open(signInLink.getHref(), "_self", "");
			
		}
	}
	
	public void loadPersonAbruf (LoginInformation loginInfo) {
		ShopITAdministrationAsync.checkEmail(loginInfo.getEmailAddress(), new PersonAbrufCallback());
	}
	private void loadShopITAdministrationAsync() {
	
		
	*/	
	
	/*public void loadLogin() {
	RootPanel.get().add(new Authentification (loginInfo.getLoginURL()));
	
	}

	
	public void loadEditor (LoginInformation ) {
		shopITVerwaltung.getPersonByEmail(loginInfo.getEmailAddress(), new GetUserVallback());
		shopITVerwaltung.getPersonById(1,  new GetUserCallback);
		
		public void load()
		
		
	}
	*/
	}
	
	


	
	
