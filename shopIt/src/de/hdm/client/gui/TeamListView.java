package de.hdm.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;

//public class GroupListView implements TreeViewModel {
public class TeamListView {
	
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
	
	public TeamListView() {
		
	}
	
	public void setEditor(EditorAdminView editor) {
		this.editor = editor;
	}

	public Widget showList() {
		this.vPanel = new VerticalPanel();
		this.btnShowListGroup = new Button("Gruppe neu");
		
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
			articleForm = new ArticleForm();
//			articleForm.onLoad();
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
