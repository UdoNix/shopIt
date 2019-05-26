package de.hdm.server;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.shared.bo.Group;
import de.hdm.shared.bo.Person;

public class GroupMapper {
	
	// Klasse GroupMapper als Singleton
	//Variable durch <code> static </code> nur einmal für Instanzen der Klassen vorhanden
	//Sie speichert einzige Instanz der Klasse
 private static GroupMapper groupMapper = null;

// Konstruktor geschützt, es kann keine neue Instanz dieser Klasse mit <code>new</code> erzeugt werden

protected GroupMapper() {
}

//Aufruf der statischen Methode durch <code>GroupMapper.groupMapper()</code>. Singleton: Es kann nur eine 
//Instanz von <code>GroupMapper</code> existieren
//@return groupMapper

public static GroupMapper groupMapper() {
	if (groupMapper == null) {
		groupMapper = new GroupMapper();
	}
	return groupMapper;
}

// Gruppe mit der vorgegebene Id suchen, Da sie eindeutig ist, wird nur ein Objekt zurueckgegeben
//@parameter id Primärschlüsselattribut
//@return Gruppenobjekt des übergebenen Schlüssel, null bei nicht vorhandenem Datenbank-Tupel

public Group findByKey (int id) {
	//DB-Verbindung holen
	Connection con =DBConnection.connection();
	
	try {
		//Anlegen einen leeren SQL-Statement
		Statement stmt =con.createStatement();
		// Ausfüllen des Statements, als Query an die DB schicken
		ResultSet rs =stmt.executeQuery("SELECT * from group" + "WHERE group.id =" + id );
		
		//Da id Primärschlüssel ist, kann nur ein Tupel zurueckgeg werden. 
		//Es wird geprueft, ob ein Ergebnis vorliegt.
		   if (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Group g = new Group();
		        g.setId(rs.getInt("id"));
		        g.setName(rs.getString("name"));
		        g.setCreationDate(rs.getTimestamp("creationDate"));
		        g.setChangeDate(rs.getTimestamp("changeDate"));
		        return g;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return null;
		  }
			
// Auslesen aller Gruppen.
 // @return Ein Vektor mit Group-Objekten, die sämtliche Gruppen
 //        repräsentieren. Bei Exceptions: Ein partiell gefüllter
//        oder eben leerer Vetor wird zurückgeliefert.

public Vector<Group> findAll() {
  Connection con = DBConnection.connection();

  // Ergebnisvektor vorbereiten
  Vector<Group> result = new Vector<Group>();

  try {
    Statement stmt = con.createStatement();

    ResultSet rs = stmt.executeQuery("SELECT * FROM group "
        + " ORDER BY id");

    // Für jeden Eintrag im Suchergebnis wird nun ein Group-Objekt erstellt.
    while (rs.next()) {
      Group g = new Group();
      g.setId(rs.getInt("id"));
      g.setName(rs.getString("name"));
      g.setCreationDate(rs.getTimestamp("creationDate"));
      g.setChangeDate(rs.getTimestamp("changeDate"));

      // Das neue Objekts wird zum Ergebnisvektor hinzugefuegt
      result.addElement(g);
    }
  }
  catch (SQLException e2) {
    e2.printStackTrace();
  }

  //Der Ergebnisvektor wird zurueckgegeben
  return result;
}


 //Einfügen eines <code>Group</code>-Objekts in die Datenbank. Es wird
 // auch der Primärschlüssel des übergebenen Objekts geprüft und im gegebenen Falle
 // berichtigt.
 // @param g das zu speichernde Objekt
//@return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
 //        <code>id</code>.

public Group insert(Group g) {
  Connection con = DBConnection.connection();

  try {
    Statement stmt = con.createStatement();

    //Pruefen, welches der momentan höchste Primärschlüsselwert ist.
  
    ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
        + "FROM group ");

    // Falls man etw. zurueck bekommt, ist dies nur einzeilig 
    if (rs.next()) {
      //g erhält den bisher maximalen, nun um 1 inkrementierten Primärschlüssel.
       
      g.setId(rs.getInt("maxid") + 1);

      stmt = con.createStatement();

      // Es erfolgt die tatsächliche Einfuegeoperation
      stmt.executeUpdate("INSERT INTO group (id, name, CreationDate, ChangeDate) " + "VALUES ("
          + g.getId() + "," + g.getCreationDate() + "," + g.getChangeDate() + ","  + g.getName() + ")");
    }
  }
  catch (SQLException e2) {
    e2.printStackTrace();
  }
  return g;
}

 // Schreiben eines Objekts in die Datenbank.
  // @param g  Objekt, das in die Datenbank geschrieben werden soll
  //@return das als Parameter übergebene Objekt
  
  public Group update(Group g) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE accounts " + "SET name=\"" + g.getName()
          + "\" " + "changeDate=\"" + g.getChangeDate() + "\" "+ "WHERE id=" + g.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // g zueruck geben
    return g;
  }
   

   // Daten eines <code>Group</code>-Objekts aus der Datenbank loeschen.
    // @param g das aus der DB zu loeschende "Objekt"
   
   public void delete(Group g) {
     Connection con = DBConnection.connection();

     try {
       Statement stmt = con.createStatement();

       stmt.executeUpdate("DELETE FROM group " + "WHERE id=" + g.getId());

     }
     catch (SQLException e2) {
       e2.printStackTrace();
     }
   }
   
   public Vector<Person> getPersonsOf(Group g) {
 		//Wir bedienen uns hier einfach des PersonMapper.
 		return MembershipMapper.membershipMapper().findByMember(g);
   
}
}
