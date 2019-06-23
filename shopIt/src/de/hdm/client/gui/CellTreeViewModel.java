package de.hdm.client.gui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Article;
import de.hdm.shared.bo.BusinessObject;
import de.hdm.shared.bo.Item;
import de.hdm.shared.bo.List;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Team;

/**
 * @author Alexander Gerlings
 * 
 *         Vorlage @author Thies
 *
 */
public class CellTreeViewModel implements TreeViewModel {

	private PersonForm personForm;
	private TeamForm teamForm;

	private TeamListView groupListView;
	private StackPanel menuPanel;

	private Person selectedPerson = null;
	private Team selectedTeam = null;
//	private List selectedList = null;
//	private Article selectedArticle = null;
//	private Item selectedItem = null;

	private ShopITAdministrationAsync listAdmin = null;

	private ListDataProvider<Person> personDataProvider = null;
	private Map<Person, ListDataProvider<Team>> teamDataProviders = null;
//	private Map<Team, ListDataProvider<List>> teamDataProviders = null;
//	private Map<List, ListDataProvider<Item>> listDataProviders = null;
//	private ListDataProvider<Article> articleDataProvider = null;

	private class BusinessObjectKeyProvider implements ProvidesKey<BusinessObject> {
		@Override
		public Integer getKey(BusinessObject bo) {
			if (bo == null) {
				return null;
			}
			if (bo instanceof Team) {
				return new Integer(bo.getId());
			} else {
				return new Integer(-bo.getId());
			}
		}
	};

	private BusinessObjectKeyProvider boKeyProvider = null;
	private SingleSelectionModel<BusinessObject> selectionModel = null;

	private class SelectionChangeEventHandler implements SelectionChangeEvent.Handler {
		@Override
		public void onSelectionChange(SelectionChangeEvent event) {
			BusinessObject selection = selectionModel.getSelectedObject();
			if (selection instanceof Person) {
				setSelectedPerson((Person) selection);
			} else if (selection instanceof Team) {
				setSelectedTeam((Team) selection);
			}
//				else if (selection instanceof Article) {
//				setSelectedArticle((Article) selection);
//			} else if (selection instanceof List) {
//				setSelectedList((List) selection);
//			} else if (selection instanceof Item) {
//				setSelectedList(Item) selection);
		}
	}

	}

	public CellTreeViewModel() {
		listAdmin = ClientsideSettings.getShopItAdministration();
		boKeyProvider = new BusinessObjectKeyProvider();
		selectionModel = new SingleSelectionModel<BusinessObject>(boKeyProvider);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEventHandler());
//		personDataProviders=new HashMap<Person,ListDataProvider<Team>>();
		teamDataProviders = new HashMap<Person, ListDataProvider<Team>>();
//		listDataProviders=new HashMap<List,ListDataProvider<Item>>();
//		articleDataProvider=new ListDataProvider<Article>();
//		allPersonDataProvider = new ListDataProvider<Person>();
	}

	void setPersonForm(PersonForm pf) {
		personForm = pf;
	}

	void setTeamForm(TeamForm tf) {
		teamForm = tf;
	}

	Person getSelectedPerson() {
		return selectedPerson;
	}

	void setSelectedPerson(Person p) {
		selectedPerson = p;
		personForm.setSelected(p);
		selectedTeam = null;
		teamForm.setSelected(null);
	}

	Team getSelectedTeam() {
		return selectedTeam;
	}

	void setSelectedTeam(Team t) {
		selectedTeam = t;
		teamForm.setSelected(t);
		
		if(t != null) {
			listAdmin.getPersonById(t.get, callback);
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

	@Override
	public <T> NodeInfo<?> getNodeInfo(T arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
