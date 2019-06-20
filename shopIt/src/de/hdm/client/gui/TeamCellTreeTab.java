package de.hdm.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import de.hdm.client.ClientsideSettings;
import de.hdm.client.gui.CellTreeViewModel.CellTreeResources;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.BusinessObject;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Team;

public class TeamCellTreeTab {
	private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
	
	private CellTreeViewModel ctvm;
	private CellList<Team> teamList;
	private Person user;
	
	private ListDataProvider<Team> teamDataProvider = null;
	private TeamKeyProvider teamKeyProvider = null;
	private SingleSelectionModel<Team> selectionModel = null;
	private CellTreeResources clRes = GWT.create(CellTreeResources.class);
	
	public TeamCellTreeTab(Person user, CellTreeViewModel ctvm) {
		this.ctvm = ctvm;
		this.user = user;
		
		teamKeyProvider = new TeamKeyProvider();
		
		selectionModel = new SingleSelectionModel<Team>(teamKeyProvider);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEventHandler());
		teamList = new CellList<Team>(new TeamForm(), clRes, teamKeyProvider);
	}
	
	/**
	 * Bildet Teams auf eindeutige Zahlenobjekte ab. 
	 *
	 */
	private class TeamKeyProvider implements ProvidesKey<Team> {

		@Override
		public Object getKey(Team item) {
			// TODO Auto-generated method stub
			
			return (item == null) ? null : item.getId();
		}
		
	}
	
	private class SelectionChangeEventHandler implements SelectionChangeEvent.Handler {

		@Override
		public void onSelectionChange(SelectionChangeEvent event) {
			BusinessObject selection = selectionModel.getSelectedObject();
			if(selection != null) {
				this.setSelectedTeam((Team) selection);
				
			}
		}
		
		void setSelectedTeam(Team team) {
			
			
		}
	}
	
	public interface CellTreeResources extends CellList.Resources {
		@Source("ShopItCellTree.css")
		CellTree.Style cellTreeStyle();
	}
	
	
}
