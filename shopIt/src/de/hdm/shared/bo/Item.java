package de.hdm.shared.bo;
public class Item extends BusinessObject{

	private static final long serialVersionUID = 1L;

	//
	private Salesman salesman;
	
	private Article article;
	
	private int listId;
	
	//Favorit zeigt an, ob Item Standartartikel ist bzw. favorisiert wurde.
	private boolean favorit;
	
	//Status zeigt an, ob Item abgehakt ist oder nicht.
	private boolean status;
	
	//Auslesen des Händlers.
	public Salesman getSalesman() {
		return salesman;
	}
	//Setzen des Händlers.
	public void setSalesman(Salesman salesman) {
		this.salesman = salesman;
	}
	//Auslesen des Artikels.
	public Article getArticle() {
		return article;
	}
	//Setzen des Artikels.
	public void setArticle(Article article) {
		this.article = article;
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
	//Auslesen der Liste
	public int getListId() {
		return listId;
	}
	//Setzen der Liste
	public void setList(int listId) {
		this.listId = listId;
	}

	
}

