package de.hdm.server;

import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.Vector;
	
public class ItemMapper {
	
	// Klasse ItemMapper als Singleton
	//Variable durch <code> static </code> nur einmal für Instanzen der Klassen vorhanden
	//Sie speichert einzige Instanz der Klasse
 private static ItemMapper itemMapper = null;

// Konstruktor geschützt, es kann keine neue Instanz dieser Klasse mit <code>new</code> erzeugt werden

protected ItemMapper() {
}

//Aufruf der statischen Methode durch <code>ItemMapper.itemMapper()</code>. Singleton: Es kann nur eine 
//Instanz von <code>ItemMapper</code> existieren
//@return itemMapper

public static ItemMapper itemMapper() {
	if (itemMapper == null) {
		itemMapper = new ItemMapper();
	}
	return itemMapper;
}

// Item mit der vorgegebene Id suchen, Da sie eindeutig ist, wird nur ein Objekt zurueckgegeben
//@parameter id Primärschlüsselattribut
//@return Itemobjekt des übergebenen Schlüssel, null bei nicht vorhandenem Datenbank-Tupel

public Item findByKey (int id) {
	//DB-Verbindung holen
	Connection con =DBConnection.connection();
	
	try {
		//Anlegen einen leeren SQL-Statement
		Statement stmt =con.createStatement();
		// Ausfüllen des Statements, als Query an die DB schicken
		ResultSet rs =stmt.executeQuery("SELECT * from item" + "WHERE item.id =" + id );
		
		//Da id Primärschlüssel ist, kann nur ein Tupel zurueckgeg werden. 
		//Es wird geprueft, ob ein Ergebnis vorliegt.
		   if (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Item i = new Item();
		        i.setId(rs.getInt("id"));
		        i.setName(rs.getString("name"));
		        return i;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return null;
		  }
			
// Auslesen aller Einträge.
 // @return Ein Vektor mit Item-Objekten, die sämtliche Gruppen
 //        repräsentieren. Bei Exceptions: Ein partiell gefüllter
//        oder eben leerer Vetor wird zurückgeliefert.

public Vector<Item> findAll() {
  Connection con = DBConnection.connection();

  // Ergebnisvektor vorbereiten
  Vector<Item> result = new Vector<Item>();

  try {
    Statement stmt = con.createStatement();

    ResultSet rs = stmt.executeQuery("SELECT * FROM item "
        + " ORDER BY id");

    // Für jeden Eintrag im Suchergebnis wird nun ein Item-Objekt erstellt.
    while (rs.next()) {
      Item s = new Item();
      i.setId(rs.getInt("id"));
      i.setName(rs.getString("name"));

      // Das neue Objekts wird zum Ergebnisvektor hinzugefuegt
      result.addElement(i);
    }
  }
  catch (SQLException e2) {
    e2.printStackTrace();
  }

  //Der Ergebnisvektor wird zurueckgegeben
  return result;
}


 //Einfügen eines <code>Item</code>-Objekts in die Datenbank. Es wird
 // auch der Primärschlüssel des übergebenen Objekts geprüft und im gegebenen Falle
 // berichtigt.
 // @param i das zu speichernde Objekt
//@return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
 //        <code>id</code>.

public Item insert(Item i) {
  Connection con = DBConnection.connection();

  try {
    Statement stmt = con.createStatement();

    //Pruefen, welches der momentan höchste Primärschlüsselwert ist.
  
    ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
        + "FROM item ");

    // Falls man etw. zurueck bekommt, ist dies nur einzeilig 
    if (rs.next()) {
      //s erhält den bisher maximalen, nun um 1 inkrementierten Primärschlüssel.
       
      i.setId(rs.getInt("maxid") + 1);

      stmt = con.createStatement();

      // Es erfolgt die tatsächliche Einfuegeoperation
      stmt.executeUpdate("INSERT INTO Item (id, aenderungsdatum, erstellungsdatum) " + "VALUES ("
	          + i.getId() + "," +i.getName() + "," + i.getErstellungsDatum() +")");
  
    }
  }

  catch (SQLException e2) {
    e2.printStackTrace(); 
  }

 // Schreiben eines Objekts in die Datenbank.
  // @param i  Objekt, das in die Datenbank geschrieben werden soll
  //@return das als Parameter übergebene Objekt
   */
  public Item update(Item i) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE list " + "SET eintragsdatum=\"" + i.getName()
      + "\" " + "," + "erstellungDatum=\"" + i.getErstellungsDatum() + "WHERE id=" + i.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // i zueruck geben
    return i;
  }
   

   // Daten eines <code>Item</code>-Objekts aus der Datenbank loeschen.
    // @param s das aus der DB zu loeschende "Objekt"
   
   public void delete(Item i) {
     Connection con = DBConnection.connection();

     try {
       Statement stmt = con.createStatement();

       stmt.executeUpdate("DELETE FROM item " + "WHERE id=" + i.getId());

     }
     catch (SQLException e2) {
       e2.printStackTrace();
     }
   }

   
}
