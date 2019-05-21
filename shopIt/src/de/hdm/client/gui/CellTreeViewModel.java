package de.hdm.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;


/**
 * @author Alexander Gerlings
 * 
 * Vorlage @author Thies
 *
 */
public class CellTreeViewModel extends VerticalPanel {

	private HorizontalPanel mainPanel;
	private StackPanel menuPanel;
	String group;
	
	
	public void cellTreeViewModel() {
		group = "Alle Gruppen";
		menuPanel = new StackPanel();
		menuPanel.add(createGroupListView(), "Alle Gruppen");
		
	}
	
	public StackPanel getStackPanelMenu() {
		return this.menuPanel;
	}
	
	public void onLoad() {
		this.add(this.menuPanel);
	}

	private Widget createGroupListView() {
		// TODO Auto-generated method stub
		return null;
	}



	static interface CellTreeResource extends CellTree.Resources {
		@Override
		@Source("cellTreeClosedItem.jpg")
		ImageResource cellTreeClosedItem();
		
		@Override
		@Source("cellTreeOpenItem.jpg")
		ImageResource cellTreeOpenItem();
		
		@Override
		@Source("ShopItCellTree.css")
		CellTree.Style cellTreeStyle();
	}
	
	
}
