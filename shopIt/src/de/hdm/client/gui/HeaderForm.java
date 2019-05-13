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
		 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu
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
		private searchField SearchField = new searchField();
		private VerticalPanel logoutEditPanel = new VerticalPanel();

		private Button logoutButton = new Button("Logout");
		private Button profilEditButton = new Button("Account bearbeiten");


	
		public HeaderForm() {

			this.addStyleName("headerForm");

			this.add(group);
			this.add(list);
			this.add(account);
			this.add(SearchField);
			this.add(logoutEditPanel);

			//group.setHorizontalAlignment(ALIGN_LEFT);
			SearchField.setHorizontalAlignment(ALIGN_CENTER);
			logoutEditPanel.setHorizontalAlignment(ALIGN_RIGHT);

			logoutEditPanel.add(logoutButton);
			

			
			logoutButton.addClickHandler(new LogoutClickHandler());

			super.onLoad();
		}


		/**
		 * Nested Class für den Logout-Button, der den Clickhandler zum Ausloggen aus dem System implementiert
		 */
		class LogoutClickHandler implements ClickHandler {

			
			public void onClick(ClickEvent event) {

				Anchor signOutLink = new Anchor();

				signOutLink.setHref(Cookies.getCookie("logout"));
				Window.open(signOutLink.getHref(), "_self", "");
			}

		}
	

	}

