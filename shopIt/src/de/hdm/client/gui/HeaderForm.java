package de.hdm.client.gui;

	import com.google.gwt.user.client.ui.HorizontalPanel;
	import com.google.gwt.event.dom.client.ClickEvent;
	import com.google.gwt.event.dom.client.ClickHandler;
	import com.google.gwt.user.client.Cookies;
	import com.google.gwt.user.client.Window;
	import com.google.gwt.user.client.rpc.AsyncCallback;
	import com.google.gwt.user.client.ui.*;
	import de.hdm.client.ClientsideSettings;
	import de.hdm.shared.EditorServiceAsync;
	import de.hdm.shared.bo.Person;

	/**
	 * Klasse zur Erstellung der HeaderForm
	 * 
	 * @author Diba Segmen
	 */

	public class HeaderForm extends HorizontalPanel {

		/**
		 * Erzeugen eines Listenverwaltung-Objekts um die Applikationsverwaltung zu
		 * initialisieren.
		 */
		EditorServiceAsync listenVerwaltung = ClientsideSettings.getEditorServiceAsync();

		/**
		 * Instanziierung gewünschter Elemente
		 */

		//private ItemForm item = new ItemForm();
		private GroupForm group = new GroupForm();
		private ListForm list = new ListForm();
		private AccountForm account = new AccountForm();
		private SearchField SearchField = new SearchField();
		private VerticalPanel contentPanel = new VerticalPanel();

		private Button logoutBtn = new Button("Logout");
		private Button settings = new Button("Account bearbeiten");

		
	
		public HeaderForm() {

			this.addStyleName("headerForm");

			this.add(group);
			this.add(list);
			this.add(account);
			this.add(SearchField);
			this.add(logoutBtn);

			/*
			 * Anordnung (ALIGN) noch offen
			 */

			contentPanel.add(logoutBtn);
			

			
			logoutBtn.addClickHandler(new LogoutClickHandler());

			//super.onLoad();
		}


		/**
		 * Nested Class für den Logout-Button, der den Clickhandler zum Ausloggen aus dem System implementiert
		 */
		
		class LogoutClickHandler implements ClickHandler {

			
			public void onClick(ClickEvent event) {

				Anchor logOutLink = new Anchor();

				logOutLink.setHref(Cookies.getCookie("logout"));
				Window.open(logOutLink.getHref(), "_self", "");
			}
			
//			public void loadAccount(){
//				
//				
//				logoutBtn = new Button("Logout");
//				settings = new Button("Einstellungen");
//			
//				contentPanel.add(logoutBtn);
//				contentPanel.add(settings);
//			
//				RootPanel.get().add(contentPanel);
//				
//			}
		

		}
	

	}

