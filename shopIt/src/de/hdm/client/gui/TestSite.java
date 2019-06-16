package de.hdm.client.gui;

import java.util.ArrayList;

import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Article;

public class TestSite {

	ShopITAdministrationAsync admin = ClientsideSettings.getShopItAdministration();
	Logger logger = ClientsideSettings.getLogger();

	private VerticalPanel mainPanel = new VerticalPanel();
	private TextBox addBox = new TextBox();
	private HorizontalPanel addArticlePanel = new HorizontalPanel();
	private Button addArticleButton = new Button("Add Article");

	public void onModuleLoad() {

		addArticlePanel.add(addBox);
		addArticlePanel.add(addArticleButton);
		addArticleButton.addClickHandler(new AddArticleClickHandler());

		// Assemble Main panel.
		mainPanel.add(addArticlePanel);

		// Associate the Main panel with the HTML host page.
		RootPanel.get("nav").add(mainPanel);
	}

	class AddArticleClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			if (!addBox.getText().isEmpty()) {
				Article a = new Article();
				a.setName(addBox.getText());
				admin.createArticle(addBox.getText(), new AddArticleCallback());
			}
		}
	}

	public class AddArticleCallback implements AsyncCallback<Article> {

		@Override
		public void onFailure(Throwable caught) {
			logger.info(caught.getMessage());
		}

		@Override
		public void onSuccess(Article a) {
			Window.alert(a.getName() + " wurde angelegt!");

		}

	}
}