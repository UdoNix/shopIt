package de.hdm.client.gui.report;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ReportLayout extends HorizontalPanel {

	private VerticalPanel one = new VerticalPanel();
	private VerticalPanel two = new VerticalPanel();
	private VerticalPanel three = new VerticalPanel();
	
	public ReportLayout() {
		add(one);
		add(two);
		add(three);
		
		one.addStyleName("one");
		two.addStyleName("two");
		three.addStyleName("three");
	}
	
	public void setOne(Widget one) {
		this.one.clear();
		this.one.add(one);
	}
	
	public void setTwo(Widget two) {
		this.two.clear();
		this.three.clear();
		this.two.add(two);
	}
	
	public void setThree(Widget three) {
		this.three.clear();
		this.three.add(three);
	}
}
