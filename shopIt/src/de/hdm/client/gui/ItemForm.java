package de.hdm.client.gui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.shared.bo.Item;
import de.hdm.shared.bo.Article;

public class ItemForm extends Form{
	/**
	 * Erstellung verschiedener Panels
	 */
	private VerticalPanel contentPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private VerticalPanel MemberPanel = new VerticalPanel();

	/**
	 * Erstellung benötigter GUI-Elemente
	 */
	
	private Label name = new Label();
	private Label creationTime = new Label();
	private Label NumberOfArticles = new Label();
	
	private Button EditItemlistBtn = new Button("Gruppe bearbeiten");
	private Button DeleteItemBtn = new Button("Gruppe löschen");

	/*
	 * 
	 */

	private DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy k:mm");

	private Item item = new Item();
	
	/*
	 * 
	 */

	public ItemForm(long serializableID) {

		Item i = new Item();
		
		i.setSerializableID(serializableID);

		this.addStyleName("itemForm");
		
//		Widget articleForm = new ArticleForm();
//
//		this.add(articleForm);

		super.onLoad();

}
	public ItemForm() {
		
	}

	
}


