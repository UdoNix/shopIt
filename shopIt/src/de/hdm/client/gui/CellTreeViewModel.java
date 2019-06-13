package de.hdm.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Button;
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

	private GroupListView groupListView;
	private StackPanel menuPanel;
	
	private EditorAdminView editorAdminView;
	//private CellTreeViewModel cellTreeViewModel;
	private EditorAdminView editor;
	private AccountForm acForm;
	//private AddPersonToGroupForm adForm;
	private GroupForm grForm;
	private ArticleForm articleForm;
	
	private VerticalPanel vPanel;
	private Button btnShowListGroup;
	private Button btnShowAccountForm;
	private Button btnShowListArticleForm;
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
		
		this.groupListView = new GroupListView();
		this.showList();
		
		
		return this.showList();
		
	}
	
	public Widget showList() {
		this.vPanel = new VerticalPanel();
		this.btnShowListArticleForm = new Button("ArtikelForm");
		vPanel.add(btnShowListArticleForm);
		btnShowListArticleForm.addClickHandler(new ShowArticleFormClickHandler());
		
		this.btnShowListGroup = new Button("GroupForm");
		vPanel.add(btnShowListGroup);
		btnShowListGroup.addClickHandler(new ShowGroupListClickHandler());
		
		this.btnShowAccountForm = new Button("AccountForm");
		vPanel.add(btnShowAccountForm);
		btnShowAccountForm.addClickHandler(new ShowAccountFormClickHandler());
		
		return vPanel;
	}

	private class ShowGroupListClickHandler implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
//			groupListView = new GroupListView();
//			groupListView.onLoad();
			
			grForm = new GroupForm();
			grForm.onLoad();
		}
	}
	private class ShowArticleFormClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			articleForm = new ArticleForm();
			articleForm.onLoad();
		}
		
	}
	
	private class ShowAccountFormClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			acForm = new AccountForm();
			acForm.onLoad();
		}
		
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
