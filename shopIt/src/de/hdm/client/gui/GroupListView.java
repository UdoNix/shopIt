package de.hdm.client.gui;

import com.google.gwt.view.client.TreeViewModel;

public class GroupListView implements TreeViewModel {

	private EditorAdminView editorAdminView;
	private CellTreeViewModel cellTreeViewModel;
	private EditorAdminView editor;
	
	public GroupListView() {
		
	}
	
	public void setEditor(EditorAdminView editor) {
		this.editor = editor;
	}

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		// TODO Auto-generated method stub
		return false;
	}
}
