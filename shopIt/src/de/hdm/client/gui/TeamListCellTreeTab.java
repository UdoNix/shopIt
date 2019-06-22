package de.hdm.client.gui;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.view.client.TreeViewModel.DefaultNodeInfo;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.BusinessObject;
import de.hdm.shared.bo.List;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Team;
import de.hdm.thies.bankProjekt.shared.bo.Account;

/**
 * Die Klasse <code>TeamListCellTreeTab</code> stellt alle Einkaufslisten eines Nutzers da.
 * Und wird im Cell Tree Menü dargestellt
 * @author Alexander Gerlings
 *
 */

public class TeamListCellTreeTab implements TreeViewModel {

	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
	private Team selectedTeam;
	private List selectedList;
	
	private Person user;
	private EditorAdminView editorView;
	private CellTreeViewModel cellTreeViewModel;
	
	private ArrayList<List> teamList;
	
	/*
	 * Durch den <code>ListDataProvider</code> werden alle Änderungen aktualisiert.
	 * 
	 */
	
	private ListDataProvider<List> teamListDataProviders;
	
	/*
	 * in der Map wird die Liste mit den Gruppen verbunden, die Werte werden durch einzigartige Keys
	 * zusammen gemappt. 
	 * Die Map wird als Assoziativspeicher verwendet und somit sind die Werte jederzeit anhand der Keys aufrufbar
	 */
	
	private Map<List, ListDataProvider<Team>> teamDataProvider = null;
	
	private BusinessObjectKeyProvider boKeyProvider = null;
	private SingleSelectionModel<BusinessObject> selectionModel = null;
	
	/**
	 * Die Klasse BusinessObjectKeyProvider bildet BusinessObjects auf eindeutige
	 * Zahlenobjekte ab, welche dann als Schlüssel für Baumknoten dienen.
	 * 
	 * Da das Teamobject eine positive Zahl und das Listenobject eine negative Zahl
	 * ist, können diese unterschieden werden, auch wenn diese die selbe ID haben.
	 * 
	 * Orientiert an @author Thies
	 * 
	 */
	
	private class BusinessObjectKeyProvider implements ProvidesKey<BusinessObject> {

		@Override
		public Integer getKey(BusinessObject bo) {
			if(bo == null) {
				return null;
			}
			if(bo instanceof Team) {
				return new Integer(bo.getId());
			} else {
				return new Integer(-bo.getId());
			}
		}
		
	}
	
	public TeamListCellTreeTab(Person p) {
		this.user = p;
		
		boKeyProvider = new BusinessObjectKeyProvider();
		
		selectionModel = new SingleSelectionModel<BusinessObject>(boKeyProvider);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEventHandler());
		
		teamDataProvider = new HashMap<List, ListDataProvider<Team>>();
	}
	
	
	public TeamListCellTreeTab(Person p, CellTreeViewModel ctvm) {
		this.user = p;
		this.cellTreeViewModel = ctvm;
		
		boKeyProvider = new BusinessObjectKeyProvider();
		
		selectionModel = new SingleSelectionModel<BusinessObject>(boKeyProvider);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEventHandler());
		
		teamDataProvider = new HashMap<List, ListDataProvider<Team>>();
	}
	
	
	private class SelectionChangeEventHandler implements SelectionChangeEvent.Handler {

		@Override
		public void onSelectionChange(SelectionChangeEvent event) {
			BusinessObject selection = selectionModel.getSelectedObject();
			if(selection instanceof Team) {
				setSelectedTeam((Team) selection);
			} else if(selection instanceof List) {
				setSelectedList((List) selection);
			}
			/*
			 * Müssen die Tabs noch geleert werden???
			 * 
			 */
			//cellTreeViewModel.clearSelectionModelAccount();
			
		}
		
	}
	
	public Team getSelectedTeam() {
		return selectedTeam;
	}
	
	public void setSelectedTeam(Team t) {
		if(t != null) {
			editorView.showTeam(t);
		}
	}
	
	public List getSelectedList() {
		return selectedList;
	}
	
	public void setSelectedList(List l) {
		if(l != null) {
			editorView.showList(l);
		}
	}
	
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		// TODO Auto-generated method stub
		if(value.equals("Root")) {
			teamListDataProviders = new ListDataProvider<List>();
			listenVerwaltung.getAllListsOf(t, new AsyncCallback<Vector<List>>(){

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Vector<List> lists) {
					for(List l : lists) {
						teamListDataProviders.getList().add(l);					}
					
				}

			});
			
			return new DefaultNodeInfo<List>(teamListDataProviders, 
					new ListCell(), selectionModel, null);
		}
		
//		if(value instanceof List) {
//			final ListDataProvider<Team> teamProvider = new ListDataProvider<Team>();
//			teamDataProvider.put((List) value, teamProvider);
//			
//			/**
//			 * ??????????
//			 */
//			listenVerwaltung.getTeamById((Person) value, 
//					new AsyncCallback<Vector<List>>() {
//				@Override
//				public void onFailure(Throwable t) {
//				}
//
//				@Override
//				public void onSuccess(Vector<Team> teams) {
//					for (team t : teams) {
//						teamProvider.getList().add(t);
//					}
//				}
//				
//			});
//			
//			return new DefaultNodeInfo<List>(teamProvider, 
//					new TeamCell(), selectionModel, null);
//		}
	}

	@Override
	public boolean isLeaf(Object value) {
		// TODO Auto-generated method stub
		return (value instanceof Team);
	}
	

}
