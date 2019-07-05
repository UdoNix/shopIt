package de.hdm.client.gui;

import com.google.gwt.cell.client.AbstractCell;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.shared.bo.Article;

public class ArticleCell extends AbstractCell<Article> {

	@Override
	public void render(Context context, Article value, SafeHtmlBuilder sb) {
		if(value == null) {
			return;
		}
		
		sb.appendHtmlConstant("<div>");
		sb.appendEscaped(value.getName());
		sb.appendHtmlConstant("</div>");
	}
	

}
