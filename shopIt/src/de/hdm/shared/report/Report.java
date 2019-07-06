package de.hdm.shared.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Basisklasse aller Reports. Der Zugriff auf Reports erfolgt also nach deren Bereitstellung lokal auf dem
 * Client. Ein Report besitzt eine Reihe von Standardelementen. Sie werden mittels
 * Attributen modelliert und dort dokumentiert.
 * 
 * @author Thies, Ciupe
 */

public abstract class Report implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Paragraph imprint = null;

	  /**
	   * Kopfdaten des Berichts.
	   */
	private Paragraph headerData = null;

	  /**
	   * Jeder Bericht kann einen individuellen Titel besitzen.
	   */
	private String title = "Report";
	  
	private Date created = new Date();
	  
	public Paragraph getImprint() {
		    return this.imprint;
	}
	
	public void setImprint(Paragraph imprint) {
	    this.imprint = imprint;
	}

	public Paragraph getHeaderData() {
		return headerData;
	}

	public void setHeaderData(Paragraph headerData) {
		this.headerData = headerData;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	  

}
