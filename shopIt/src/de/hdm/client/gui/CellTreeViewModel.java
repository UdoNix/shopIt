package de.hdm.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellList;
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

	private TeamListView groupListView;
	private StackPanel menuPanel;
	
	
	//private CellTreeResources groupListRes = GWT.create(CellTreeResources.class);
	
	
	
	public CellTreeViewModel() {
		menuPanel = new StackPanel();
		menuPanel.setStyleName("stackMenuPanel");
		
		menuPanel.setWidth("200px");
		
		//menuPanel.add(showGroupListView(), "Alle Gruppen");
		menuPanel.add(showGroupListView(), "Alle Gruppen");
	}

	public void onLoad() {
		this.add(this.menuPanel);
	}
	
	public void setEditor(EditorAdminView editor) {
		groupListView.setEditor(editor);
	}

	private Widget showGroupListView() {
		
		this.groupListView = new TeamListView();
		groupListView.showList();
		
		
		return groupListView.showList();
		
	}

	public StackPanel getStackMenuPanel() {
		return this.menuPanel;
	}

	static interface CellTreeResources extends CellTree.Resources {
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
