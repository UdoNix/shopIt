package de.hdm.shared.bo;
public class Item extends BusinessObject{

	private static final long serialVersionUID = 1L;

	//Fremdschlüsselbeziehung zum Händler.
	private int salesmanId = 0;
	
	//Fremdschlüsselbeziehung zum Artikel.
	private int articleId = 0;
	
	//Fremdschlüsselbeziehung zur Liste.
	private int listId = 0;
	
	//Favorit zeigt an, ob Item Standartartikel ist bzw. favorisiert wurde.
	private boolean favorit;
	
	//Status zeigt an, ob Item abgehakt ist oder nicht.
	private boolean status;
	
	//
	public int getSalesmanId() {
		return salesmanId;
	}
	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getListId() {
		return listId;
	}
	public void setListId(int listId) {
		this.listId = listId;
	}
	//Auslesen, ob Artikel ein Standardartikel ist.
	public boolean isFavorit() {
		return favorit;
	}
	//Setzen eines Standardartikels als Favorit.
	public void setFavorit(boolean favorit) {
		this.favorit = favorit;
	}
	//Abfrage des Status.
	public boolean isStatus() {
		return status;
	}
	//Setzen des Status.
	public void setStatus(boolean status) {
		this.status = status;
	}


	
}

