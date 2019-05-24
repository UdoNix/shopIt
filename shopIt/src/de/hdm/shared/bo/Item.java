package de.hdm.shared.bo;
public class Item extends BusinessObject{

	private static final long serialVersionUID = 1L;

	//Fremdschlüsselbeziehung zum Händler.
	private int salesmanId = 0;
	
	//Fremdschlüsselbeziehung zum Artikel.
	private int articleId = 0;
	
	//Fremdschlüsselbeziehung zur Liste.
	private int listId = 0;
	
	//Fremdschlüsselbeziehung zur UnitOfMeasure.
	private int unitId = 0;
	
	//Fremdschlüsselbeziehung zur Gruppe.
	private int groupId = 0;

	//Favorit zeigt an, ob Item Standartartikel ist bzw. favorisiert wurde.
	private boolean favorit;
	
	//Status zeigt an, ob Item abgehakt ist oder nicht.
	private boolean status;
	
	//Auslesen des Fremdschlüssels des Händlers.
	public int getSalesmanId() {
		return salesmanId;
	}
	
	//Setzen des Fremdschlüssels des Händlers.
	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}
	
	//Auslesen des Fremdschlüssels des Artikels.
	public int getArticleId() {
		return articleId;
	}
	
	//Setzen des Fremdschlüssels des Artikels.
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	
	//Auslesen des Fremdschlüssels der Liste.
	public int getListId() {
		return listId;
	}
	
	//Setzen des Fremdschlüssels der Liste.
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
	
	//Auslesen des Fremdschlüssels zur UnitOfMeasure.
	public int getUnitId() {
		return unitId;
	}
	
	//Setzen des Fremdschlüssels zur UnitOfMeasure.
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	
	//Auslesen des Fremdschlüssels zur Gruppe.
	public int getGroupId() {
		return groupId;
	}
	
	//Setzen des Fremdschlüssels zur Gruppe.
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}




	
}

