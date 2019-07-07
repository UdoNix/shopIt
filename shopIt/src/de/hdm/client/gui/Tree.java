package de.hdm.client.gui;

import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.client.ClientsideSettings;
import de.hdm.client.Layout;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.ShoppingList;
import de.hdm.shared.bo.Team;

/**
 * Klasse zur Realisierung einer Navigation durch die Applikation mittels CellTree
 * 
 * @author dibasegmen
 *
 */

public class Tree extends CellTree {

	private final Layout layout;

	/**
	 * @param layout Das Layout zum setzen des Inhalt Panels
	 */
	
	public Tree(Layout layout) {
		super(new TreeModel(layout), null);
		this.layout = layout;
		((TreeModel)getTreeViewModel()).setTree(this);
	}

	public static class TreeModel implements TreeViewModel {

		private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
		private Map<Integer, ShoppingListsAsyncDataProvider> listProviderByTeamId = new HashMap<>();

		private final Layout layout;
		private Tree tree;

		public TreeModel(Layout layout) {
			this.layout = layout;
		}
		
		public void setTree(Tree tree) {
			this.tree = tree;
		}

		@Override
		public <T> NodeInfo<?> getNodeInfo(T value) {
			if (value == null) {
				ListDataProvider<String> dataProvider = new ListDataProvider<String>(
						Arrays.asList("Konto", "Gruppen", "Artikel", "Shop", "Report"));

				Cell<String> cell = new AbstractCell<String>("click") {
					@Override
					public void render(Context context, String value, SafeHtmlBuilder sb) {
						sb.appendHtmlConstant(value);
					}

					@Override
					public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event,
							ValueUpdater<String> valueUpdater) {
						if ("click".equals(event.getType())) {
							if (value.equals("Konto")) {
								layout.setPanel(new PersonForm());
							} else if (value.equals("Artikel")) {
								layout.setPanel(new ArticleForm());
							} else if (value.equals("Shop")) {
								layout.setPanel(new ShopView());
							} else if (value.equals("Gruppe")) {
								layout.setPanel(new NewTeamView(tree));
							} else if (value.equals("Report")) {
								Window.Location.replace("report.html");
							}
						}
					}
				};

				return new DefaultNodeInfo<String>(dataProvider, cell);
			} else if (value.equals("Gruppe")) {

				final TeamsAsyncDataProvder asyncDataProvider = new TeamsAsyncDataProvder();

				Cell<Team> cell = new AbstractCell<Team>("click") {
					@Override
					public void render(Context context, Team value, SafeHtmlBuilder sb) {
						sb.appendHtmlConstant(value.getName());
					}

					@Override
					public void onBrowserEvent(Context context, Element parent, Team value, NativeEvent event,
							ValueUpdater<Team> valueUpdater) {
						if ("click".equals(event.getType())) {
							
							TeamView teamView = new TeamView(tree, listProviderByTeamId.get(value.getId()));
							teamView.setSelectedTeam(value);
							
							layout.setPanel(teamView);
						}
					}

				};

				return new DefaultNodeInfo<Team>(asyncDataProvider, cell);
			} else if (value instanceof Team) {
				final Team team = (Team) value;

				final ShoppingListsAsyncDataProvider asyncDataProvider = new ShoppingListsAsyncDataProvider(team);
				listProviderByTeamId.put(team.getId(), asyncDataProvider);

				Cell<ShoppingList> cell = new AbstractCell<ShoppingList>("click") {
					@Override
					public void render(Context context, ShoppingList value, SafeHtmlBuilder sb) {
						sb.appendHtmlConstant(value.getName());
					}
					
					@Override
					public void onBrowserEvent(Context context, Element parent, ShoppingList value, NativeEvent event,
							ValueUpdater<ShoppingList> valueUpdater) {
						
						if ("click".equals(event.getType())) {
							
							ListItemForm listItemForm = new ListItemForm(value, asyncDataProvider);
							
							layout.setPanel(listItemForm);
						}
					}
				};

				return new DefaultNodeInfo<ShoppingList>(asyncDataProvider, cell);
			}

			return null;
		}

		@Override
		public boolean isLeaf(Object value) {
			if (value != null && value.equals("Konto")) {
				return true;
			} else if (value != null && value.equals("Artikel")) {
				return true;
			} else if (value != null && value.equals("Shop")) {
				return true;
			} else if (value != null && value.equals("Report")) {
				return true;
			} else if (value instanceof ShoppingList) {
				return true;
			}
			return false;
		}

	}
	
	public static class TeamsAsyncDataProvder extends AsyncDataProvider<Team> {
		
		private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
		
		/**
		 *  Wenn sich im Knoten Gruppe Änderungen ergeben, wird der Baum entsprechend aktualisiert 
		 */
		
		@Override
		protected void onRangeChanged(HasData<Team> display) {
			refresh();
		}
		
		public void refresh() {
			AsyncCallback<Vector<Team>> callback = new AsyncCallback<Vector<Team>>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(Vector<Team> result) {
					updateRowCount(result.size(), true);
					updateRowData(0, result);
				}
			};
			listenVerwaltung.getAllTeamsByPerson(callback);
		}
	};
	
	public static class ShoppingListsAsyncDataProvider extends AsyncDataProvider<ShoppingList> {
		
		private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();
		
		private Team team;
		
		public ShoppingListsAsyncDataProvider(Team team) {
			this.team = team;
		}
		
		/**
		 * Wenn sich im Knoten Liste Änderungen ergeben, wird der Baum entsprechend aktualisiert
		 * 
		 */
		
		@Override
		public void onRangeChanged(HasData<ShoppingList> display) {
			refresh();
		}
		
		public void refresh() {
			AsyncCallback<Vector<ShoppingList>> callback = new AsyncCallback<Vector<ShoppingList>>() {
				@Override
				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(Vector<ShoppingList> result) {
					updateRowCount(result.size(), true);
					updateRowData(0, result);
				}
			};
			listenVerwaltung.getAllListsOf(team, callback);
		}
	}

}
