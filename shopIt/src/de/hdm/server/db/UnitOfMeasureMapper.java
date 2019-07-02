package de.hdm.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.Vector;

import de.hdm.shared.bo.UnitOfMeasure;
//@udo nix, emily kretzschmar

public class UnitOfMeasureMapper {
	
	// Klasse UnitOfMeasureMapper als Singleton
	//Variable durch <code> static </code> nur einmal für Instanzen der Klassen vorhanden
	//Sie speichert einzige Instanz der Klasse
 private static UnitOfMeasureMapper unitOfMeasureMapper = null;

// Konstruktor geschützt, es kann keine neue Instanz dieser Klasse mit <code>new</code> erzeugt werden

protected UnitOfMeasureMapper() {
}

//Aufruf der statischen Methode durch <code>UnitOfMeasureMapper.unitOfMeasureMapper()</code>. Singleton: Es kann nur eine 
//Instanz von <code>UnitOfMeasureMapper</code> existieren
//@return unitOfMeasureMapper

public static UnitOfMeasureMapper unitOfMeasureMapper() {
	if (unitOfMeasureMapper == null) {
		unitOfMeasureMapper = new UnitOfMeasureMapper();
	}
	return unitOfMeasureMapper;
}

// UnitOfMeasure mit der vorgegebene Id suchen, Da sie eindeutig ist, wird nur ein Objekt zurueckgegeben
//@parameter id Primärschlüsselattribut
//@return UnitOfMeasureobjekt des übergebenen Schlüssel, null bei nicht vorhandenem Datenbank-Tupel

public UnitOfMeasure findByKey (int id) {
	//DB-Verbindung holen
	Connection con =DBConnection.connection();
	
	try {
		//Anlegen einen leeren SQL-Statement
		Statement stmt =con.createStatement();
		// Ausfüllen des Statements, als Query an die DB schicken
		ResultSet rs =stmt.executeQuery("SELECT * from unitOfMeasure" + "WHERE unitOfMeasure.id = " + id );
		
		//Da id Primärschlüssel ist, kann nur ein Tupel zurueckgeg werden. 
		//Es wird geprueft, ob ein Ergebnis vorliegt.
		   if (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
			   UnitOfMeasure u = new UnitOfMeasure();
		        u.setId(rs.getInt("id"));
		        u.setUnit(rs.getString("unit"));
		        u.setQuantity(rs.getFloat("quantity"));
		        u.setCreationDate(rs.getTimestamp("creationDate"));
		        u.setChangeDate(rs.getTimestamp("changeDate"));
		        return u;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return null;
		  }
			
// Auslesen aller Maßeinheiten.
 // @return Ein Vektor mit UnitOfMeasure-Objekten, die sämtliche Maßeinheiten
 //        repräsentieren. Bei Exceptions: Ein partiell gefüllter
//        oder eben leerer Vetor wird zurückgeliefert.

public Vector<UnitOfMeasure> findAll() {
  Connection con = DBConnection.connection();

  // Ergebnisvektor vorbereiten
  Vector<UnitOfMeasure> result = new Vector<UnitOfMeasure>();

  try {
    Statement stmt = con.createStatement();

    ResultSet rs = stmt.executeQuery("SELECT * FROM unitOfMeasure "
        + " ORDER BY id");

    // Für jeden Eintrag im Suchergebnis wird nun ein Salesman-Objekt erstellt.
    while (rs.next()) {
    	UnitOfMeasure u = new UnitOfMeasure();
    	u.setId(rs.getInt("id"));
        u.setUnit(rs.getString("unit"));
        u.setQuantity(rs.getFloat("quantity"));
        u.setCreationDate(rs.getTimestamp("creationDate"));
        u.setChangeDate(rs.getTimestamp("changeDate"));
       

      // Das neue Objekts wird zum Ergebnisvektor hinzugefuegt
      result.addElement(u);
    }
  }
  catch (SQLException e2) {
    e2.printStackTrace();
  }

  //Der Ergebnisvektor wird zurueckgegeben
  return result;
}


 //Einfügen eines <code>UnitOfMeasure</code>-Objekts in die Datenbank. Es wird
 // auch der Primärschlüssel des übergebenen Objekts geprüft und im gegebenen Falle
 // berichtigt.
 // @param u das zu speichernde Objekt
//@return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
 //        <code>id</code>.

public UnitOfMeasure insert(UnitOfMeasure u) {
  Connection con = DBConnection.connection();

  try {
    Statement stmt = con.createStatement();

    //Pruefen, welches der momentan höchste Primärschlüsselwert ist.
  
    ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
        + "FROM unit");

    // Falls man etw. zurueck bekommt, ist dies nur einzeilig 
    if (rs.next()) {
      //s erhält den bisher maximalen, nun um 1 inkrementierten Primärschlüssel.
       
      u.setId(rs.getInt("maxid") + 1);

      stmt = con.createStatement();

      // Es erfolgt die tatsächliche Einfuegeoperation
      PreparedStatement stmt2 = con.prepareStatement("INSERT INTO unit (id, unit, quantity) VALUES (?, ?, ?)");
      		stmt2.setInt(1, u.getId());
      		stmt2.setString(2, u.getUnit());
      		stmt2.setFloat(3, u.getQuantity());
//      stmt.executeUpdate("INSERT INTO UnitOfMeasure (id, unit, quantity) " + "VALUES ("
//	          + u.getId() +"," + u.getUnit() + "," + u.getQuantity() +")");
      		
      		stmt2.execute();
    }
  }

  catch (SQLException e2) {
    e2.printStackTrace();
  }
  return u;
}
 // Schreiben eines Objekts in die Datenbank.
  // @param u  Objekt, das in die Datenbank geschrieben werden soll
  //@return das als Parameter übergebene Objekt
   
  public UnitOfMeasure update(UnitOfMeasure u) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE unit SET unit=\"" + u.getUnit()
      + "\", " + "quantity=\"" + u.getQuantity()+"\", "+ "WHERE id= " + u.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // u zueruck geben
    return u;
  }
   

   // Daten eines <code>UnitOfMeasure</code>-Objekts aus der Datenbank loeschen.
    // @param u das aus der DB zu loeschende "Objekt"
   
   public void delete(UnitOfMeasure u) {
     Connection con = DBConnection.connection();

     try {
       Statement stmt = con.createStatement();

       stmt.executeUpdate("DELETE FROM unit WHERE id= " + u.getId());

     }
     catch (SQLException e2) {
       e2.printStackTrace();
     }
   }

   
}