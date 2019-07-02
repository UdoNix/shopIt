package de.hdm.client.gui;

import java.util.Vector;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.client.ClientsideSettings;
import de.hdm.shared.ShopITAdministrationAsync;
import de.hdm.shared.bo.Article;

/**
 * Die <code>ArticleForm</code> wird verwendet um alle angelegten Artikel
 * anzuzeigen
 */
public class ArticleForm extends VerticalPanel {

	ShopITAdministrationAsync articleVerwaltung = ClientsideSettings.getShopItAdministration();

	private Grid grid = new Grid(2, 2);

	private Article article;

	private TextBox nameTextBox;

	public ArticleForm() {

		add(grid);

		nameTextBox = new TextBox();
		Button button2 = new Button("Speichern");
		Button button = new Button("Anlegen");

		grid.setWidget(0, 0, new Label("Name"));
		grid.setWidget(0, 1, nameTextBox);
		grid.setWidget(1, 0, button);
		grid.setWidget(1, 1, button2);

		final CellTable<Article> cellTable = new CellTable<Article>();
		
		final AsyncCallback<Vector<Article>> getAllCallback = new AsyncCallback<Vector<Article>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler");
			}

			@Override
			public void onSuccess(Vector<Article> result) {
				cellTable.setRowCount(result.size(), true);
				cellTable.setRowData(result);
			}
		};
		
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				articleVerwaltung.createArticle(nameTextBox.getValue(), new AsyncCallback<Article>() {

					@Override
					public void onSuccess(Article result) {
						articleVerwaltung.getAllArticles(getAllCallback);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");
					}
				});
			}
		});

		button2.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (article == null) {
					Window.alert("Kein Artikel ausgewählt");
				} else {
					article.setName(nameTextBox.getValue());
					articleVerwaltung.save(article, new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
							articleVerwaltung.getAllArticles(getAllCallback);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler");
						}
					});
				}
			}
		});

		TextColumn<Article> idColumn = new TextColumn<Article>() {
			@Override
			public String getValue(Article object) {
				return object.getId() + "";
			}
		};
		TextColumn<Article> nameColumn = new TextColumn<Article>() {
			@Override
			public String getValue(Article object) {
				return object.getName();
			}
		};
		Column<Article, String> deleteColumn = new Column<Article, String>(new ButtonCell()) {
			@Override
			public String getValue(Article object) {
				return "Löschen";
			}
		};
		deleteColumn.setFieldUpdater(new FieldUpdater<Article, String>() {
			@Override
			public void update(int index, Article object, String value) {
				articleVerwaltung.delete(object, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						articleVerwaltung.getAllArticles(getAllCallback);
					}

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler");
					}
				});
			}
		});
		Column<Article, String> editColumn = new Column<Article, String>(new ButtonCell()) {
			@Override
			public String getValue(Article object) {
				return "Bearbeiten";
			}
		};
		editColumn.setFieldUpdater(new FieldUpdater<Article, String>() {
			@Override
			public void update(int index, Article object, String value) {
				setSelectedArticle(object);
			}
		});
		cellTable.addColumn(idColumn, "Id");
		cellTable.addColumn(nameColumn, "Name");
		cellTable.addColumn(deleteColumn, "");
		cellTable.addColumn(editColumn, "");

		add(cellTable);

		articleVerwaltung.getAllArticles(getAllCallback);
	}

	public void setSelectedArticle(Article article) {
		this.article = article;
		nameTextBox.setValue(article.getName());
	}

}
