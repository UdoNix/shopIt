package de.hdm.client.gui;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

/**
 * Die Klasse <code>TeamCellTreeTab</code> bildet alle Gruppen eines Users ab,
 * sodass diese in einem StackPanel im TreeViewModel aufgerufen werden können.
 * @author Alexander Gerlings
 * 
 * Aufbau und Funktionen @author Thies
 *
 */

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.client.ClientsideSettings;
import de.hdm.client.gui.CellTreeViewModel.CellTreeResources;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.BusinessObject;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Team;

public class TeamCellTreeTab implements TreeViewModel{
	
	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
	private CellTreeViewModel ctvm;
	
	private Team selectedTeam;
	private ArrayList<Team> teamList;
	private Person user;
	private EditorAdminView editor;
	
	private ListDataProvider<Team> teamDataProvider = null;
	//private TeamKeyProvider teamKeyProvider = null;
	
	private CellTreeResources clRes = GWT.create(CellTreeResources.class);
	
	private Map<Team, ListDataProvider<Person>> teamListDataProvider = null;
	
	private BusinessObjectKeyProvider boKeyProvider;
	private SingleSelectionModel<BusinessObject> selectionModel = null;
	
	public TeamCellTreeTab(Person user) {
		
		this.user = user;
		
		boKeyProvider = new BusinessObjectKeyProvider();
		
		selectionModel = new SingleSelectionModel<BusinessObject>(boKeyProvider);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEventHandler());
		teamListDataProvider = new HashMap<Team, ListDataProvider<Person>>();
	}
	
	public TeamCellTreeTab(Person user, CellTreeViewModel ctvm) {
		this.ctvm = ctvm;
		this.user = user;
		
		boKeyProvider = new BusinessObjectKeyProvider();
		
		selectionModel = new SingleSelectionModel<BusinessObject>(boKeyProvider);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEventHandler());
		teamListDataProvider = new HashMap<Team, ListDataProvider<Person>>();
	}
	
	/**
	 * Bildet BusinessObject auf eindeutige Zahlenobjekte ab. 
	 *
	 */
	private class BusinessObjectKeyProvider implements ProvidesKey<BusinessObject> {
		@Override
		public Integer getKey(BusinessObject bo) {
			if (bo == null) {
				return null;
			} if (bo instanceof Team) {
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
				setSelectedTeam((Team) selection);
			} else if (selection instanceof Person) {
				setSelectedPerson((Person) selection);
				ctvm.emptyTreeView();
			}
		}
	}
	
	public void setSelectedTeam(Team t) {
		if(t != null) {
			editor.showTeam(t);
		}
	}
	
	public Team getSelectedTeam() {
		return selectedTeam;
	}
	
	public void setSelectedPerson(Person p) {
		if (p != null) {
			editor.showPerson(p);
		}
	}
	
	public Person getSelectedPerson() {
		return selectedPerson;
	}
	
	public interface CellTreeResources extends CellList.Resources {
		@Source("ShopItCellTree.css")
		CellTree.Style cellTreeStyle();
	}

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if(value.equals("Root")) {
			teamDataProvider = new ListDataProvider<Team>();
			listenVerwaltung.getAllTeams(new AsyncCallback<Vector<Team>> () {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Vector<Team> team) {
					for(Team t : team) {
						teamDataProvider.getList().add(t);
					}	
				}
			});
			return new DefaultNodeInfo<Team>(teamDataProvider, new TeamCell(), selectionModel, null);
		}
		
//		if(value instanceof Team) {
//			final ListDataProvider<Person> personProvider = new ListDataProvider<Person>();
//			personDataProvider.put((Team) value, personProvider);
//			
//			listenVerwaltung.getAllTeams((Team) value,
//				new AsyncCallback<Vector<Person>>() {
//
//					@Override
//					public void onFailure(Throwable caught) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void onSuccess(Vector<Person> person) {
//						for(Person p : person) {
//							personProvider.getList().add(p);
//						}
//						
//					}
//			});
//			return new DefaultNodeInfo<Person>(personProvider,
//					new PersonCell(), selectionModel, null);
//		}
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		// TODO Auto-generated method stub
		return (value instanceof Person);
	}
	
	
}
