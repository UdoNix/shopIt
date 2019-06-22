package de.hdm.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.shared.bo.Person;


/**
 * @author Alexander Gerlings
 * 
 * Vorlage @author Thies
 *
 */
public class CellTreeViewModel extends VerticalPanel {

	private TeamListView groupListView;
	private StackPanel firstStackPanel;
	private Person user;
	
	private CellTreeResources groupListRes = GWT.create(CellTreeResources.class);

	
	public CellTreeViewModel() {
		
		
		firstStackPanel = new StackPanel();
		firstStackPanel.setStyleName("stackMenuPanel");
		firstStackPanel.setWidth("200px");
		
		//menuPanel.add(showGroupListView(), "Alle Gruppen");
		firstStackPanel.add(showGroupListView(), "Alle Gruppen");
		firstStackPanel.add(displayTeamListView(user), "Gruppen");
		
	}

	private Widget displayTeamListView(Person user2) {
		// TODO Auto-generated method stub
		this.groupListView = new TeamListView();
		CellTree tree = new CellTree(null, groupListView);
		tree.setAnimationEnabled(true);
		return tree;
	}

	public void onLoad() {
		this.add(this.firstStackPanel);
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
		return this.firstStackPanel;
	}
	
	public void emptyTreeView() {
		firstStackPanel.showStack(0);
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
