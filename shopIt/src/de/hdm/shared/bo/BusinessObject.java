package de.hdm.shared.bo;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


public abstract class BusinessObject implements Serializable{

	  private static final long serialVersionUID = 1L;
	  
	  //Eindeutige Id einer Instanz der Klasse.
	  private int id = 0;
	  //Auslesen der Id.
	  public int getId() {
	    return this.id;
	  }
	  //Setzen der Id.
	  public void setId(int id) {
	    this.id = id;
	  }

	private long serializableID = 1L;
	
	//Auslesen der SerializableID.
	public long getSerializableID() {
		return serializableID;
		}
	//Setzen der SerializableID.
	public void setSerializableID(long serializableID) {
		this.serializableID = serializableID;
		}
	
	//Erstellungszeitpunkt einer Instanz dieser Klasse.
	private Timestamp creationDate;
	
	//Auslesen des Erstellungszeitpunkts einer Instanz.
	public Timestamp getCreationDate() {
		return creationDate;
	}
	  
	//Setzen des Erstellungszeitpunkts einer Instanz.
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	

	//Änderungszeitpunkts einer Instanz dieser Klasse.
	private Date changeDate;
	
	//Auslesen des Änderungszeitpunkts einer Instanz.
	public Date getChangeDate() {
		return changeDate;
	}
	
	//Setzen des Änderungszeitpunkts einer Instanz.
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	
	//Erzeugt eine textuelle Darstellung der Instanz.
	public String toString() {
		//Gibt den Klassennamen, die Id und das Erstellungsdatum des Objektes zurück.
	    return this.getClass().getName() + " Id: " + this.id + " Erstellungsdatum: " + this.creationDate;
	  }


	/**
	   * <p>
	   * Feststellen der <em>inhaltlichen</em> Gleichheit zweier
	   * <code>BusinessObject</code>-Objekte. Die Gleichheit wird in diesem Beispiel auf eine
	   * identische ID beschränkt.
	   * </p>
	   * <p>
	   * <b>ACHTUNG:</b> Die inhaltliche Gleichheit nicht mit dem Vergleich der
	   * <em>Identität</em> eines Objekts mit einem anderen verwechseln!!! Dies
	   * würde durch den Operator <code>==</code> bestimmt. Bei Unklarheit hierzu
	   * können Sie nocheinmal in die Definition des Sprachkerns von Java schauen.
	   * Die Methode <code>equals(...)</code> ist für jeden Referenzdatentyp
	   * definiert, da sie bereits in der Klasse <code>Object</code> in einfachster
	   * Form realisiert ist. Dort ist sie allerdings auf die simple Bestimmung der
	   * Gleicheit der Java-internen Objekt-ID der verglichenen Objekte beschränkt.
	   * In unseren eigenen Klassen können wir diese Methode überschreiben und ihr
	   * mehr Intelligenz verleihen.
	   * </p>
	   * 
	   * @author thies
	   */
	
	public boolean equals(Object o) {
		//Abfrage, ob Objekt ungleich NULL ist.
	    if (o != null && o instanceof BusinessObject) {
	      BusinessObject bo = (BusinessObject) o;
	      try {
	        if (bo.getId() == this.id)
	          return true;
	      }
	      catch (IllegalArgumentException e) {
	    	//Auffangen der Exception durch Rückgabe von false.
	        return false;
	      }
	    }
	    //Wenn keine Gleichheit festgestellt wurde, wird false zurückgegeben.
	    return false;
	  }

	public int hashCode() {
		  return this.id;
	  }

	}
