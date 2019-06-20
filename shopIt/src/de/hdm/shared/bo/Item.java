package de.hdm.shared.bo;

import java.util.Date;

public class Item extends BusinessObject{

	private static final long serialVersionUID = 1L;

	
	//Fremdschlüsselbeziehung zum Händler.
	private int shopId = 0;
	
	//Fremdschlüsselbeziehung zum Artikel.
	private int articleId = 0;
	
	//Fremdschlüsselbeziehung zur Liste.
	private int listId = 0;
	
	//Fremdschlüsselbeziehung zur Zust�ndigkeit.
	private int responsibilityId = 0;
	
	//Fremdschlüsselbeziehung zur UnitOfMeasure.
	private int unitId = 0;
	
	//Fremdschlüsselbeziehung zur Gruppe.
	private int teamId = 0;

	//Favorit zeigt an, ob Item Standartartikel ist bzw. favorisiert wurde.
	private boolean favorit;
	
	//Status zeigt an, ob Item abgehakt ist oder nicht.
	private boolean status;
	
	//Anzahl der Einträge, zur Berechnung der häufig gekauften Artikel.
	private int count = 0;

	//Auslesen des Fremdschlüssels des Händlers.
	public int getShopId() {
		return shopId;
	}
	
	//Setzen des Fremdschlüssels des Händlers.
	public void setShopId(int shopId) {
		this.shopId = shopId;
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
	
	//Auslesen des Fremdschl�ssels der Zust�ndigkeit. 
	public int getResponsibilityId() {
		return responsibilityId; 
	}
	
	//Setzen des Fremdschl�ssels der Zust�ndigkeit. 
	public void setResponsibilityId(int responsibilityId) {
		this.responsibilityId = responsibilityId; 
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
	public int getTeamId() {
		return teamId;
	}
	
	//Setzen des Fremdschlüssels zur Gruppe.
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	//Auslesen der Anzahl von häufig eingekauften Artikel.
	public int getCount() {
		return count;
	}

	//Setzen der Anzahl von häufig eingekauften Artikel.
	public void setCount(int count) {
		this.count = count;
	}





	
}

