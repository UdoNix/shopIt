package de.hdm.client.gui;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Article;
import de.hdm.shared.bo.BusinessObject;


/**
 * Die Klasse <code>ArticleCellListTab</code> zeigt alle Article im StackPanel des CellTrees an.
 * @author Alexander Gerlings
 *
 */
public class ArticleCellListTab {
	
	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
	private CellList<Article> articleCell;
	private ListDataProvider<Article> articleDataProvider;
	private ArticleKeyProvider articleKeyProvider = null;
	
	private SingleSelectionModel<Article> selectionModel = null;
//	private CellListResources cellListRes = GWT.create(CellListResources.class);
	
	public ArticleCellListTab() {
//		articleKeyProvider = new ArticleKeyProvider();
//		selectionModel = new SingleSelectionModel<Article>(articleKeyProvider);
//		selectionModel.addSelectionChangeHandler(new SelectionChangeEventHandler());
//		articleCell = new CellList<Article>(new ArticleCell(), cellListRes, articleKeyProvider);
//		articleDataProvider = new ListDataProvider<Article>();
//		articleDataProvider.addDataDisplay(articleCell);
//		articleCell.setSelectionModel(selectionModel);
		
	}
	
//	public interface CellListResources extends CellList.Resources {
//		@Override
//		@Source("ShopItCellTree.css")
//		CellList.Style cellListStyle();
//	}
	
	private class ArticleKeyProvider implements ProvidesKey<Article> {

		@Override
		public Object getKey(Article item) {
			// TODO Auto-generated method stub
			return (item == null) ? null : item.getId();
		}
	}
	
	public void onLoad() {
		listenVerwaltung.getAllArticles(new AsyncCallback<Vector<Article>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Article> articles) {
				if(articles != null) {
					for(Article a : articles) {
						articleDataProvider.getList().add(a);
						articleDataProvider.refresh();
					}
				}
				
			}
			
		});
	}
	
	private class SelectionChangeEventHandler implements SelectionChangeEvent.Handler {

		@Override
		public void onSelectionChange(SelectionChangeEvent event) {
			BusinessObject selection = selectionModel.getSelectedObject();
			if(selection != null) {
				this.setSelectedArticle((Article) selection);
			}
			
		}
		
		public void setSelectedArticle(Article a) {
			if(a != null) {
				
			}
		}
		
	}
	
	public void addArticle(Article a) {
		articleDataProvider.getList().add(a);
		selectionModel.setSelected(a, true);
	}
	
	public void removeArticle(Article a) {
		articleDataProvider.getList().remove(a);
	}
	
	public void updateArticle(Article a) {
		
	}
	
	public CellList<Article> getCellList() {
		return this.articleCell;
	}
}
