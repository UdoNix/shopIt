package de.hdm.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditorLayout extends HorizontalPanel{

	
	private VerticalPanel one = new VerticalPanel();
	private VerticalPanel two = new VerticalPanel();
	
	public EditorLayout() {
		
		add(one);
		add(two);
		
		one.addStyleName("one");
		two.addStyleName("two");
		
	}
	
	public void setOne(Widget one) {
		this.one.clear();
		this.one.add(one);
	}
	
	public void setTwo(Widget two) {
		this.two.clear();
		this.two.add(two);
	}
}
