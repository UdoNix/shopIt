package de.hdm.shared.bo;

import java.sql.Timestamp;

public class ReportObject extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private String article;
	private String unit;
	private String shopName;
	private int shopId;
	private Float quantity;
	private Timestamp changeDate;
	private int count;
	
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shop) {
		this.shopId = shop;
	}
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	public Timestamp getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Timestamp changeDate) {
		this.changeDate = changeDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
