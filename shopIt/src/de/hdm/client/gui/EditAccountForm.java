package de.hdm.client.gui;

import java.awt.Button;
import java.awt.Label;
import java.awt.Window;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.client.gui.AccountForm;
import de.hdm.client.gui.AccountForm.EditAccountFormDialogBox;
import de.hdm.client.ClientsideSettings;
import de.hdm.shared.*;
import de.hdm.shared.bo.Person;

	/**
	 * Die Klasse <code>EditAccountForm</code> dient der Veränderung der
	 * Account-Daten eines im Systems eingeloggten Users
	 * @author dibasegmen
	 */

	public class EditAccountForm extends FlowPanel {
		
		/*
		 *  Identifikation des Users und Verwaltung im System
		 */
		
		Person user = null;
		String logOutURL; //Benennung?
		EditorServiceAsync listenVerwaltung = ClientsideSettings.getEditorServiceAsync();
		
		/*
		 * Anordnung der Widgets
		 */
		
		private FlowPanel nickname = new FlowPanel();
		private FlowPanel name = new FlowPanel();
		private FlowPanel firstname = new FlowPanel();
		
		private Label nickName = new Label("Username");
		private Label lastName = new Label("Nachname");
		private Label firstName = new Label("Vorname");
		
		private TextBox nickInput = new TextBox();
		private TextBox lastInput = new TextBox();
		private TextBox firstInput = new TextBox();
		private Button safeBtn = new Button("Speichern");
		private Button deleteBtn = new Button("Löschen");
		
		private AccountForm parentSiteAccountForm;
		private EditAccountFormDialogBox accountDB;
		
		public EditAccountForm() {
		}
		
		/*
		 *  Bearbeitung des Konstruktors und der Realisierung onLoad()-Methode folgen noch
		 */
		
		public EditAccountForm(AccountForm parentSiteAccountForm, EditAccountFormDialogBox accountDB) {
		this.accountDB = parentSiteAccountForm;
		this.parentSiteAccountForm = accountDB;	
		}
		
		/*
		 *  Anordnung folgt noch
		 */
		
	}