package de.hdm.client.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.BusinessObject;
import de.hdm.shared.bo.ShoppingList;
import de.hdm.shared.bo.Membership;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Team;

/**
 * Die Klasse <code>TeamListCellTreeTab</code> stellt alle Einkaufslisten eines Nutzers da.
 * Und wird im Cell Tree Men� dargestellt
 * @author Alexander Gerlings, Ines Werner
 *
 */

public class TeamListCellTreeTab implements TreeViewModel {

	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
	
	private ListForm listForm;
	private TeamView teamView;
	
	private Team selectedTeam;
	private ShoppingList selectedList;
	private Team team;
	
	private Person user;
	
	private EditorAdminView editorView;
	
	private CellTreeViewModel cellTreeViewModel;
	
	private java.util.List<ShoppingList> teamList;
	
	/*
	 * Durch den <code>ListDataProvider</code> werden alle �nderungen aktualisiert.
	 * 
	 */
	
	//private ListDataProvider<List> teamListDataProviders;
	//Unten verwenden
	private ListDataProvider<Team> teamDataProviders;
	private ListDataProvider<Membership> membershipDataProviders;
	
	/*
	 * in der Map wird die Liste mit den Gruppen verbunden, die Werte werden durch einzigartige Keys
	 * zusammen gemappt. 
	 * Die Map wird als Assoziativspeicher verwendet und somit sind die Werte jederzeit anhand der Keys aufrufbar
	 */
	
	//private Map<List, ListDataProvider<Team>> teamDataProvider = null;
	private Map<Team, ListDataProvider<ShoppingList>> teamListDataProvider = null;
	private Map <Person, ListDataProvider<Membership>> personMembershipDataProvider = null;
	private BusinessObjectKeyProvider boKeyProvider = null;
	private SingleSelectionModel<BusinessObject> selectionModel = null;
	
	/**
	 * Die Klasse BusinessObjectKeyProvider bildet BusinessObjects auf eindeutige
	 * Zahlenobjekte ab, welche dann als Schl�ssel f�r Baumknoten dienen.
	 * 
	 * Da das Teamobject eine positive Zahl und das Listenobject eine negative Zahl
	 * ist, k�nnen diese unterschieden werden, auch wenn diese die selbe ID haben.
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
	
	private class SelectionChangeEventHandler implements SelectionChangeEvent.Handler {

		@Override
		public void onSelectionChange(SelectionChangeEvent event) {
			BusinessObject selection = selectionModel.getSelectedObject();
			if(selection instanceof Team) {
				setSeletedTeam((Team) selection);
			} else if(selection instanceof ShoppingList) {
				setSelectedList((ShoppingList) selection);
			}
			/*
			 * M�ssen die Tabs noch geleert werden???
			 * 
			 */
			//cellTreeViewModel.clearSelectionModelAccount();
			
		}
		
	}
	
	public TeamListCellTreeTab() {
		listenVerwaltung = ClientsideSettings.getShopItAdministration();
		boKeyProvider = new BusinessObjectKeyProvider();
		
		selectionModel = new SingleSelectionModel<BusinessObject>(boKeyProvider);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEventHandler());
		
		teamListDataProvider = new HashMap<Team, ListDataProvider<ShoppingList>>();
		personMembershipDataProvider = new HashMap<Person, ListDataProvider<Membership>>();
	}
	
	void setListForm(ListForm lf) {
		listForm = lf;
	}
	
	void setTeamForm(TeamView tf) {
		teamView = tf;
	}
	
	public Team getSelectedTeam() {
		return selectedTeam;
	}
	
	void setSeletedTeam(Team t) {
		selectedTeam = t;
		teamView.setSelectedTeam(t);
		selectedList = null;
		//listForm.setSelected(null);
	}
	
	public ShoppingList getSelectedList() {
		return selectedList;
	}
	
	
	
	public void setSelectedList(ShoppingList l) {
		selectedList = l;
		// listForm.setSelected(l);
		
		if(l != null) {
			listenVerwaltung.getTeamById(0, new AsyncCallback<Team>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Team team) {
					// TODO Auto-generated method stub
					selectedTeam = team;
					teamView.setSelectedTeam(team);
				}
				
				
			});
		}
	}
	
	
	void addTeam(Team team) {
		teamDataProviders.getList().add(team);
		selectionModel.setSelected(team, true);
	}
	
	void updateTeam(Team team) {
		java.util.List<Team> teamList = teamDataProviders.getList();
		int i = 0;
		for(Team t : teamList) {
			if(t.getId() == team.getId()) {
				teamList.set(i, team);
				break;
			} else {
				i++;
			}
		}
		teamDataProviders.refresh();
	}
	
	void removeTeam(Team team) {
		teamDataProviders.getList().remove(team);
		teamListDataProvider.remove(team);
	}
	
	void addListOfTeam(ShoppingList list, Team team) {
		if(!teamListDataProvider.containsKey(team)) {
			return;
		}
		
		ListDataProvider<ShoppingList> listProvider = teamListDataProvider.get(team);
		if(!listProvider.getList().contains(list)) {
			listProvider.getList().add(list);
			
		}
		selectionModel.setSelected(list, true);
	}
	
	void removeListOfTeam(ShoppingList list, Team team) {
		if(!teamListDataProvider.containsKey(team)) {
			return;
		}
		
		teamListDataProvider.get(team).getList().remove(list);
		selectionModel.setSelected(team, true);
	}
	
	void updateList(ShoppingList l) {
		//listenVerwaltung.getTeamById(l.getOwnerID(), new UpdateListCallback(l));
	}
	
	private class UpdateListCallback implements AsyncCallback<Team> {
		ShoppingList list = null;
		
		UpdateListCallback(ShoppingList l) {
			list = l;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Team result) {
			// TODO Auto-generated method stub
			java.util.List<ShoppingList> lists = teamListDataProvider.get(team).getList();
			
			for(int i = 0; i<lists.size(); i++) {
				if (list.getId() == lists.get(i).getId()) {
					lists.set(i, list);
					break;
				}
			}
		}
		
		/**Diese Methode löscht nicht das Team, sondern nur die Membership des Users zum ausgewählten Team. **/
		public void delete(Team t){
			int teamId = t.getId();
			int userId = user.getId();
			// java.util.List<List> lists = membershipDataProviders.getList();
			
		}
	}

	
	public SingleSelectionModel<BusinessObject> getSelectionModel() {
		return this.selectionModel;
	}
	
//	public void updateList(List list) {
//		java.util.List<List> teamList = teamListDataProviders.getList();
//		int i = 0;
//		for(List l : teamList) {
//			if(l.getId() == list.getId()) {
//				teamList.set(i, list);
//				break;
//			} else {
//				i++;
//			}
//		}
//		teamListDataProviders.refresh();
//	}
	
	
	
	
	
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		// TODO Auto-generated method stub
		if(value.equals("Root")) {
			teamDataProviders = new ListDataProvider<Team>();
			listenVerwaltung.getAllTeams(new AsyncCallback<Vector<Team>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Vector<Team> teams) {
					for(Team t : teams) {
						teamDataProviders.getList().add(t);					}
					
				}

			});
			
			return new DefaultNodeInfo<Team>(teamDataProviders,	
					new TeamCell(), selectionModel, null);
		}
		
		
		if(value instanceof Team) {
			final ListDataProvider<ShoppingList> listProvider = new ListDataProvider<ShoppingList>();
			teamListDataProvider.put((Team) value, listProvider);
			
			/**
			 * ??????????
			 */
//			listenVerwaltung.getListOf((Team) value, 
//					new AsyncCallback<Vector<List>>() {
//				@Override
//				public void onFailure(Throwable t) {
//				}
//
//				@Override
//				public void onSuccess(Vector<List> list) {
//					for (List l : list) {
//						listProvider.getList().add(l);
//					}
//				}
//				
//			});
			
			return new DefaultNodeInfo<ShoppingList>(listProvider, 
					new ListCell(), selectionModel, null);
		}
		
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		// TODO Auto-generated method stub
		return (value instanceof Team);
	}
	

}
