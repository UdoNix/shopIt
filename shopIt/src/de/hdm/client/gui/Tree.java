package de.hdm.client.gui;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.ShoppingList;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.Team;

public class Tree extends CellTree {

	public Tree(Object rootValue) {
		super(new TreeModel(), rootValue);
	}

	public static class TreeModel implements TreeViewModel {

		private ShopITAdministrationAsync listenVerwaltung = ClientsideSettings.getShopItAdministration();

		@Override
		public <T> NodeInfo<?> getNodeInfo(T value) {
			if (value == null) {
				ListDataProvider<String> dataProvider = new ListDataProvider<String>(
						Arrays.asList("Account", "Gruppe", "Arikel", "Shop"));

				Cell<String> cell = new AbstractCell<String>() {
					@Override
					public void render(Context context, String value, SafeHtmlBuilder sb) {
						sb.appendHtmlConstant(value);
					}
				};

				return new DefaultNodeInfo<String>(dataProvider, cell);
			} else if (value.equals("Gruppe")) {

				AsyncDataProvider<Team> asyncDataProvider = new AsyncDataProvider<Team>() {
					@Override
					protected void onRangeChanged(HasData<Team> display) {
						AsyncCallback<Vector<Team>> callback = new AsyncCallback<Vector<Team>>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.getMessage());
							}

							@Override
							public void onSuccess(Vector<Team> result) {
								updateRowData(0, result);
							}
						};
						listenVerwaltung.getAllTeams(callback);
					}
				};

				Cell<Team> cell = new AbstractCell<Team>() {
					@Override
					public void render(Context context, Team value, SafeHtmlBuilder sb) {
						sb.appendHtmlConstant(value.getName());
					}
				};

				return new DefaultNodeInfo<Team>(asyncDataProvider, cell);
			} else if (value instanceof Team) {
				final Team team = (Team) value;

				AsyncDataProvider<ShoppingList> asyncDataProvider = new AsyncDataProvider<ShoppingList>() {
					@Override
					protected void onRangeChanged(HasData<ShoppingList> display) {
						AsyncCallback<Vector<ShoppingList>> callback = new AsyncCallback<Vector<ShoppingList>>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.getMessage());
							}

							@Override
							public void onSuccess(Vector<ShoppingList> result) {
								updateRowData(0, result);
							}
						};
						listenVerwaltung.getAllListsOf(team, callback);
					}
				};

				Cell<ShoppingList> cell = new AbstractCell<ShoppingList>() {
					@Override
					public void render(Context context, ShoppingList value, SafeHtmlBuilder sb) {
						sb.appendHtmlConstant(value.getName());
					}
				};

				return new DefaultNodeInfo<ShoppingList>(asyncDataProvider, cell);
			}

			return null;
		}

		@Override
		public boolean isLeaf(Object value) {
			if (value != null && value.equals("Account")) {
				return true;
			} else if (value != null && value.equals("Artikel")) {
				return true;
			} else if (value != null && value.equals("Shop")) {
				return true;
			}
			return false;
		}

	}

}
