package de.hdm.client;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.client.gui.Tree;

public class Layout extends HorizontalPanel {
	
	private VerticalPanel contentPanel = new VerticalPanel();
	
	public Layout() {
		add(new Tree(this));
		add(contentPanel);
	}
	
	/**
	 * Aktiviert das Panel mit dem Inhalt
	 */
	public void setPanel(Widget panel) {
		this.contentPanel.clear();
		this.contentPanel.add(panel);
	}
}
