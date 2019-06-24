package de.hdm.client.gui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.shared.bo.List;
import de.hdm.shared.bo.Item;

@
public class ListForm extends Form{
	
	/*
	 * Instanziieren der benötigen Panels
	 */
	
	private VerticalPanel contentPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private VerticalPanel MemberPanel = new VerticalPanel();

	/*
	 *  Instanziieren benötigten Widgets
	 */
	
	private HTML headline = new HTML("<b>Einträge</b>");
	private Label creationTime = new Label();
	
	private Button EditListBtn = new Button("bearbeiten");
	private Button DeleteListBtn = new Button("löschen");
	

	/*
	 * 
	 */

	private DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd.MM.yyyy k:mm");

	private List list = new List();
	
	/*
	 * 
	 */

	public ListForm(long serializableID) {

		List l = new List();
		
		l.setSerializableID(serializableID);

		this.addStyleName("listForm");

		ItemForm itemForm = new ItemForm(serializableID);

		this.add(itemForm);

		//super.onLoad();

}
	public ListForm() {
		
	}

	
}
