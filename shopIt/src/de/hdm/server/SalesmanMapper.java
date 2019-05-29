package de.hdm.server;

import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.Vector;

import de.hdm.shared.bo.Salesman;

	
public class SalesmanMapper {
	
	// Klasse SalesmanMapper als Singleton
	//Variable durch <code> static </code> nur einmal für Instanzen der Klassen vorhanden
	//Sie speichert einzige Instanz der Klasse
 private static SalesmanMapper salesmanMapper = null;

// Konstruktor geschützt, es kann keine neue Instanz dieser Klasse mit <code>new</code> erzeugt werden

protected SalesmanMapper() {
}

//Aufruf der statischen Methode durch <code>SalesmanMapper.salesmanMapper()</code>. Singleton: Es kann nur eine 
//Instanz von <code>SalesmanMapper</code> existieren
//@return salesmanMapper

public static SalesmanMapper salesmanMapper() {
	if (salesmanMapper == null) {
		salesmanMapper = new SalesmanMapper();
	}
	return salesmanMapper;
}

// Salesman mit der vorgegebene Id suchen, Da sie eindeutig ist, wird nur ein Objekt zurueckgegeben
//@parameter id Primärschlüsselattribut
//@return Salesmanobjekt des übergebenen Schlüssel, null bei nicht vorhandenem Datenbank-Tupel

public Salesman findByKey (int id) {
	//DB-Verbindung holen
	Connection con =DBConnection.connection();
	
	try {
		//Anlegen einen leeren SQL-Statement
		Statement stmt =con.createStatement();
		// Ausfüllen des Statements, als Query an die DB schicken
		ResultSet rs =stmt.executeQuery("SELECT * from salesman" + "WHERE salesman.id =" + id );
		
		//Da id Primärschlüssel ist, kann nur ein Tupel zurueckgeg werden. 
		//Es wird geprueft, ob ein Ergebnis vorliegt.
		   if (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Salesman s = new Salesman();
		        s.setId(rs.getInt("id"));
		        s.setName(rs.getString("name"));
		        s.setStreet(rs.getString("street"));
		        s.setPostalCode(rs.getString("postalCode"));
		        s.setCity(rs.getString("city"));
		        s.setCreationDate(rs.getTimestamp("creationDate"));
		        s.setChangeDate(rs.getTimestamp("changeDate"));
		    	
		        return s;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return null;
		  }
			
// Auslesen aller Haendler.
 // @return Ein Vektor mit Salesman-Objekten, die sämtliche Gruppen
 //        repräsentieren. Bei Exceptions: Ein partiell gefüllter
//        oder eben leerer Vetor wird zurückgeliefert.

public Vector<Salesman> findAll() {
  Connection con = DBConnection.connection();

  // Ergebnisvektor vorbereiten
  Vector<Salesman> result = new Vector<Salesman>();

  try {
    Statement stmt = con.createStatement();

    ResultSet rs = stmt.executeQuery("SELECT * FROM salesman "
        + " ORDER BY id");

    // Für jeden Eintrag im Suchergebnis wird nun ein Salesman-Objekt erstellt.
    while (rs.next()) {
      Salesman s = new Salesman();
      s.setId(rs.getInt("id"));
      s.setName(rs.getString("name"));
      s.setStreet(rs.getString("street"));
      s.setPostalCode(rs.getString("postalcode"));
      s.setCity(rs.getString("city"));
      s.setCreationDate(rs.getTimestamp("creationDate"));
      s.setChangeDate(rs.getTimestamp("changeDate"));
  	
      
      // Das neue Objekts wird zum Ergebnisvektor hinzugefuegt
      result.addElement(s);
    }
  }
  catch (SQLException e2) {
    e2.printStackTrace();
  }

  //Der Ergebnisvektor wird zurueckgegeben
  return result;
}


 //Einfügen eines <code>Salesman</code>-Objekts in die Datenbank. Es wird
 // auch der Primärschlüssel des übergebenen Objekts geprüft und im gegebenen Falle
 // berichtigt.
 // @param s das zu speichernde Objekt
//@return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
 //        <code>id</code>.

public Salesman insert(Salesman s) {
  Connection con = DBConnection.connection();

  try {
    Statement stmt = con.createStatement();

    //Pruefen, welches der momentan höchste Primärschlüsselwert ist.
  
    ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
        + "FROM salesman ");

    // Falls man etw. zurueck bekommt, ist dies nur einzeilig 
    if (rs.next()) {
      //s erhält den bisher maximalen, nun um 1 inkrementierten Primärschlüssel.
       
      s.setId(rs.getInt("maxid") + 1);

      stmt = con.createStatement();

      // Es erfolgt die tatsächliche Einfuegeoperation
      stmt.executeUpdate("INSERT INTO Salesman (id, name, creationDate, street, plz, city) " + "VALUES ("
	          + s.getId() + "," + s.getName() + ","+ s.getPostalCode() + "," + s.getCity() + ","+ s.getStreet() + ","+ s.getCreationDate() +")");
      
  
  	
  
    }
  }

  catch (SQLException e2) {
    e2.printStackTrace(); 
  }
  return s;
  }

 // Schreiben eines Objekts in die Datenbank.
  // @param s  Objekt, das in die Datenbank geschrieben werden soll
  //@return das als Parameter übergebene Objekt
   
  public Salesman update(Salesman s) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE list " + "SET name=\"" + s.getName()
      + "\", " + "changeDate=\"" + s.getChangeDate() +"\", "+ "plz=\"" + s.getPostalCode()+"\", "+ "city=\"" + s.getCity() +"\", "+ "street=\"" + s.getStreet()+"\", "+"WHERE id=" + s.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // s zueruck geben
    return s;
  }
   

   // Daten eines <code>Salesman</code>-Objekts aus der Datenbank loeschen.
    // @param s das aus der DB zu loeschende "Objekt"
   
   public void delete(Salesman s) {
     Connection con = DBConnection.connection();

     try {
       Statement stmt = con.createStatement();

       stmt.executeUpdate("DELETE FROM salesman " + "WHERE id=" + s.getId());

     }
     catch (SQLException e2) {
       e2.printStackTrace();
     }
   }
}
