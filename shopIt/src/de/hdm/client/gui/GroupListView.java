package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;

//public class GroupListView implements TreeViewModel {
public class GroupListView { 
	
	private EditorAdminView editorAdminView;
	//private CellTreeViewModel cellTreeViewModel;
	private EditorAdminView editor;
	private AccountForm acForm;
//	private AddPersonToGroupForm adForm;
//	private GroupForm grForm;
	private ArticleForm articleForm;
	
	private VerticalPanel vPanel;
	private Button btnShowListGroup;
	private Button btnShowAccountForm;
	
	public GroupListView() {
		
	}
	
	public void onLoad() {
		RootPanel.get("main").clear();
		//RootPanel.get("main").add(mainPanel);
	}
	
	public void setEditor(EditorAdminView editor) {
		this.editor = editor;
	}

	
	
//	@Override
//	public <T> NodeInfo<?> getNodeInfo(T value) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean isLeaf(Object value) {
//		// TODO Auto-generated method stub
//		return false;
//	}
}
