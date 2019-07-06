package de.hdm.server.db;


/**
 * @udo nix, emily kretzschmar
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.shared.bo.Responsibility;

public class ResponsibilityMapper {

	
	/**
	 * Klasse ResponsibilityMapper als Singleton
	 * Variable durch <code> static </code> nur einmal für Instanzen der Klassen vorhanden
	 * Sie speichert einzige Instanz der Klasse
	 */
 private static ResponsibilityMapper responsibilityMapper = null;

/**
 * Konstruktor geschützt, es kann keine neue Instanz dieser Klasse mit <code>new</code> erzeugt werden
 */
protected ResponsibilityMapper() {
}

/**
 * Aufruf der statischen Methode durch <code>ResponsibilityMapper.responsibilityMapper()</code>. Singleton: Es kann nur eine 
 * Instanz von <code>ResponsibilityMapper</code> existieren
 * @return responsibilityMapper
 */
public static ResponsibilityMapper responsibilityMapper() {
	if (responsibilityMapper == null) {
		responsibilityMapper = new ResponsibilityMapper();
	}
	return responsibilityMapper;
}

/**
 * Verantwortlichkeit mit der vorgegebene Id suchen, Da sie eindeutig ist, wird nur ein Objekt zurueckgegeben
 * @param id Primärschlüssel
 * @return Responsibiltyobjekt des übergebenen Schlüssel, null bei nicht vorhandenem Datenbank-Tupel
 */
public Responsibility findByKey (int id) {
	/**
	 * //DB-Verbindung holen
	 */
	Connection con =DBConnection.connection();
	
	try {
		/**
		 * Anlegen einen leeren SQL-Statement
		 */
		Statement stmt =con.createStatement();
		/**
		 * Ausfüllen des Statements, als Query an die DB schicken
		 */
		ResultSet rs = stmt.executeQuery("SELECT * FROM responsibility WHERE id=" + id);
		
		/**
		 * Da id Primärschlüssel ist, kann nur ein Tupel zurueckgeg werden. 
		 * Es wird geprueft, ob ein Ergebnis vorliegt.
		 */
		   if (rs.next()) {
			  /**
			   * Ergebnis-Tupel in Objekt umwandeln
			   */
			   Responsibility r = new Responsibility();
		        r.setId(rs.getInt("id"));
		        r.setPersonId(rs.getInt("personId"));
		        r.setShopId(rs.getInt("shopId"));
		        r.setItemId(rs.getInt("itemId"));
		        return r;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return null;
		  }
			
/**
 * Auslesen aller Responsiblilities.
 * @return Vektor mit Responsibility-Objekten, die sämtliche Zuständigkeiten
 * repräsentieren. Bei Exceptions: Ein partiell gefüllter
 * oder eben leerer Vetor wird zurückgeliefert.
 */
public Vector<Responsibility> findAll() {
  Connection con = DBConnection.connection();

  /**
   * Ergebnisvektor vorbereiten
   */
  Vector<Responsibility> result = new Vector<Responsibility>();

  try {
    Statement stmt = con.createStatement();

    ResultSet rs = stmt.executeQuery("SELECT * FROM responsibility "
        + " ORDER BY id");

    /**
     * Für jeden Eintrag im Suchergebnis wird nun ein Responsibility-Objekt erstellt.
     */
    while (rs.next()) {
    	Responsibility r = new Responsibility();
      r.setId(rs.getInt("id"));
      r.setPersonId(rs.getInt("personId"));
      r.setShopId(rs.getInt("shopId"));
      r.setItemId(rs.getInt("itemId"));

      
      /**
       * Das neue Objekts wird zum Ergebnisvektor hinzugefuegt
       */
      result.addElement(r);
    }
  }
  catch (SQLException e2) {
    e2.printStackTrace();
  }

  /**
   * Der Ergebnisvektor wird zurueckgegeben
   */
  return result;
}


 
/**
 *Einfügen eines <code>Responsibility</code>-Objekts in die Datenbank. Es wird
 *auch der Primärschlüssel des übergebenen Objekts geprüft und im gegebenen Falle berichtigt. 
 * @param r das zu speichernde Objekt
 * @returndas bereits übergebene Objekt, jedoch mit ggf. korrigierter <code>id</code>.
 */
public Responsibility insert(Responsibility r) {
  Connection con = DBConnection.connection();

  try {
    Statement stmt = con.createStatement();

  /**
   * Pruefen, welches der momentan höchste Primärschlüsselwert ist.
   */
    ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
        + "FROM responsibility ");

    /**
     * Falls man etw. zurueck bekommt, ist dies nur einzeilig 
     */
    if (rs.next()) {
       /**
        * r erhält den bisher maximalen, nun um 1 inkrementierten Primärschlüssel.
        */
      r.setId(rs.getInt("maxid") + 1);

      stmt = con.createStatement();

      /**
       * Es erfolgt die tatsächliche Einfuegeoperation
       */
      PreparedStatement stmt2 = con.prepareStatement("INSERT INTO responsibility (id, personId, shopId, itemId) " + "VALUES (?, ?, ?, ?)");
      stmt2.setInt(1, r.getId());
      stmt2.setInt(2, r.getPersonId());
      stmt2.setInt(3, r.getShopId());
      stmt2.setInt(4, r.getItemId());
      
      stmt2.execute();
      
      
    }
  }
  catch (SQLException e2) {
    e2.printStackTrace();
  }
  return r;
}

   
	/**
	 * Schreiben eines Objekts in die Datenbank.
	 * @param r Objekt, das in die Datenbank geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
  public Responsibility update(Responsibility r) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE responsibility SET id=\"" + r.getId() + "\", itemId=\"" + r.getItemId()
      +"\", personId=\"" + r.getPersonId() + "\", shopId=\"" + r.getShopId() + "\" WHERE id=" + r.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
    
    return r;
  }
   

   /**
    * Daten eines <code>Responsibility</code>-Objekts aus der Datenbank loeschen.
    * @param r das aus der DB zu loeschende "Objekt"
    */
   public void delete(Responsibility r) {
     Connection con = DBConnection.connection();

     try {
       Statement stmt = con.createStatement();

       stmt.executeUpdate("DELETE FROM responsibility " + "WHERE id=" + r.getId());

     }
     catch (SQLException e2) {
       e2.printStackTrace();
     }
   }
   
   /**
    * Responsibility von einem Eintrag mit der übergebenen itemId wird ausgegeben
    * @param itemId
    * @return
    */
   public Responsibility findByItem(int itemId) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM responsibility WHERE itemId=" + itemId + " ORDER BY id");

			if (rs.next()) {
				Responsibility r = new Responsibility();
				r.setId(rs.getInt("id"));
				r.setPersonId(rs.getInt("personId"));
				r.setItemId(rs.getInt("itemId"));
				r.setShopId(rs.getInt("shopId"));
			
				return r;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
   
   /**
    * Vector<Responsibility> mit Zuständigkeiten von einer Person
    * @param personId
    * @return
    */
   public Vector<Responsibility> findByPerson(int personId) {
		Connection con = DBConnection.connection();
		Vector<Responsibility> result = new Vector<Responsibility>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, personId " + "FROM responsibility "
					+ "WHERE personID=" + personId + " ORDER BY id");

			/**
			 * Für jeden Eintrag im Suchergebnis wird nun ein Objekt erstellt.
			 */
			while (rs.next()) {
				Responsibility r = new Responsibility();
				r.setId(rs.getInt("id"));
				r.setPersonId(rs.getInt("personId"));
			
				/**
				 * Hinzufügen des neuen Objekts zum Ergebnisvektor
				 */
				result.addElement(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/**
		 * Ergebnisvektor zurückgeben
		 */
		return result;
	}

}