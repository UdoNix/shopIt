package de.hdm.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.TreeViewModel;


/**
 * @author Alexander Gerlings
 * 
 * Vorlage @author Thies
 *
 */
public class CellTreeViewModel {
	
	static interface CellTreeResource extends CellTree.Resources {
		@Override
		@Source("cellTreeClosedItem.gif");
		ImageResource cellTreeClosedItem();
		
		@Override
		Source("cellTreeOpenItem.gif");
		ImageResource cellTreeOpenItem();
		
		@Override
		@Source("ShopItCellTree.css");
		CellTree.Style cellTreeStyle();
	}
	
	private static class createTreeModel extends VerticalPanel {
		
		private StackPanel menuPanel = new StackPanel(); 
		
		
	}
}
