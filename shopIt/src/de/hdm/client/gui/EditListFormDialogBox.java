package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
//import de.hdm.shared.ShopItAdministrationAsync;
import de.hdm.shared.bo.ShoppingList;

@Deprecated
public class EditListFormDialogBox{
	{
		
	
	/**
	 * Instanziierung des EditorService-Objekts um eine
	 * Applikationsverwaltung zu initialisieren
	 */
//	EditorServiceAsync pinnwandVerwaltung = ClientsideSettings.getEditorServiceAsync();

	/**
	 * Deklarierung des verwendeten BO
	 */
	
//	List list = new List();

	/**
	 * Erstellung der benötigten GUI-Elemente
	 */
//	private VerticalPanel contentPanel = new VerticalPanel();
//	private VerticalPanel editPanel = new VerticalPanel();
//	private HorizontalPanel btnPanel = new HorizontalPanel();
	//CellList ListContent = new CellList(null); //?!
	//private Button safeBtn = new Button("Speichern");
	//private Button closeBtn = new Button("Schliessen");


	//public EditAccountFormDialogBox() {

	//}
	
	//public EditAccountFormDialogBox(List l) {
		
	
		//safeBtn.addClickHandler(new BeitragAendernClickHandler());
	//	closeBtn.addClickHandler(new SchliessenClickHandler());
//
//		btnPanel.add(safeBtn);
//		btnPanel.add(closeBtn);
//		
//		contentPanel.add(editPanel);
//		contentPanel.add(btnPanel);
//
//		this.add(contentPanel);
//	}
	
	/**
	 * <b>Nested Class für den speichern-Button</b> implementiert den entsprechenden
	 * ClickHandler
	 */
	class EditListClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			//list.setText(beitragText.getText());

		//	EditorService.save(list, new saveAllChangesofListCallback());

		}
	}
	
	}
}
	
	


