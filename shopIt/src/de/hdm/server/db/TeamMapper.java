package de.hdm.server.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.shared.bo.Team;
import de.hdm.shared.bo.Person;

//@udo nix, emily kretzschmar

public class TeamMapper {
	
	// Klasse TeamMapper als Singleton
	//Variable durch <code> static </code> nur einmal für Instanzen der Klassen vorhanden
	//Sie speichert einzige Instanz der Klasse
 private static TeamMapper teamMapper = null;

// Konstruktor geschützt, es kann keine neue Instanz dieser Klasse mit <code>new</code> erzeugt werden

protected TeamMapper() {
}

//Aufruf der statischen Methode durch <code>TeamMapper.TeamMapper()</code>. Singleton: Es kann nur eine 
//Instanz von <code>TeamMapper</code> existieren
//@return TeamMapper

public static TeamMapper teamMapper() {
	if (teamMapper == null) {
		teamMapper = new TeamMapper();
	}
	return teamMapper;
}

// Gruppe mit der vorgegebene Id suchen, Da sie eindeutig ist, wird nur ein Objekt zurueckgegeben
//@parameter id Primärschlüsselattribut
//@return Gruppenobjekt des übergebenen Schlüssel, null bei nicht vorhandenem Datenbank-Tupel

public Team findByKey (int id) {
	//DB-Verbindung holen
	Connection con =DBConnection.connection();
	
	try {
		//Anlegen einen leeren SQL-Statement
		Statement stmt =con.createStatement();
		// Ausfüllen des Statements, als Query an die DB schicken
		ResultSet rs =stmt.executeQuery("SELECT * from team WHERE id = " + id );
		
		//Da id Primärschlüssel ist, kann nur ein Tupel zurueckgeg werden. 
		//Es wird geprueft, ob ein Ergebnis vorliegt.
		   if (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Team t = new Team();
		        t.setId(rs.getInt("id"));
		        t.setName(rs.getString("name"));
		        t.setCreationDate(rs.getTimestamp("creationDate"));
		        t.setChangeDate(rs.getTimestamp("changeDate"));
		        return t;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return null;
		  }
			
// Auslesen aller Teams.
 // @return Ein Vektor mit Team-Objekten, die sämtliche Gruppen
 //        repräsentieren. Bei Exceptions: Ein partiell gefüllter
//        oder eben leerer Vetor wird zurückgeliefert.

public Vector<Team> findAll() {
  Connection con = DBConnection.connection();

  // Ergebnisvektor vorbereiten
  Vector<Team> result = new Vector<Team>();

  try {
    Statement stmt = con.createStatement();

    ResultSet rs = stmt.executeQuery("SELECT * FROM team "
        + " ORDER BY id");

    // Für jeden Eintrag im Suchergebnis wird nun ein Team-Objekt erstellt.
    while (rs.next()) {
      Team t = new Team();
      t.setId(rs.getInt("id"));
      t.setName(rs.getString("name"));
      t.setCreationDate(rs.getTimestamp("creationDate"));
      t.setChangeDate(rs.getTimestamp("changeDate"));

      // Das neue Objekts wird zum Ergebnisvektor hinzugefuegt
      result.addElement(t);
    }
  }
  catch (SQLException e2) {
    e2.printStackTrace();
  }

  //Der Ergebnisvektor wird zurueckgegeben
  return result;
}


 //Einfügen eines <code>Team</code>-Objekts in die Datenbank. Es wird
 // auch der Primärschlüssel des übergebenen Objekts geprüft und im gegebenen Falle
 // berichtigt.
 // @param g das zu speichernde Objekt
//@return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
 //        <code>id</code>.

public Team insert(Team t) {
  Connection con = DBConnection.connection();

  try {
    Statement stmt = con.createStatement();

    //Pruefen, welches der momentan höchste Primärschlüsselwert ist.
  
    ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
        + "FROM team ");

    // Falls man etw. zurueck bekommt, ist dies nur einzeilig 
    if (rs.next()) {
      //g erhält den bisher maximalen, nun um 1 inkrementierten Primärschlüssel.
       
      t.setId(rs.getInt("maxid") + 1);

      stmt = con.createStatement();

      // Es erfolgt die tatsächliche Einfuegeoperation
      
      PreparedStatement stmt2 = con.prepareStatement("INSERT INTO TEAM (id, name) " + "VALUES (?, ?)");
      stmt2.setInt(1, t.getId());
      stmt2.setString(2, t.getName());
      
//      stmt.executeUpdate("INSERT INTO team (id, name) " + "VALUES ("
//          + t.getId() + ", '"  + t.getName() + "' )");
      
      stmt2.execute();
    }
  }
  catch (SQLException e2) {
    e2.printStackTrace();
  }
  return t;
}

 // Schreiben eines Objekts in die Datenbank.
  // @param g  Objekt, das in die Datenbank geschrieben werden soll
  //@return das als Parameter übergebene Objekt
  
  public Team update(Team t) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE team " + "SET name=\"" + t.getName()
          + "\" "+ "WHERE id= " + t.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // t zueruck geben
    return t;
  }
   

   // Daten eines <code>Team</code>-Objekts aus der Datenbank loeschen.
    // @param g das aus der DB zu loeschende "Objekt"
   
   public void delete(Team t) {
     Connection con = DBConnection.connection();

     try {
       Statement stmt = con.createStatement();

       stmt.executeUpdate("DELETE FROM team " + "WHERE id= " + t.getId());

     }
     catch (SQLException e2) {
       e2.printStackTrace();
     }
   }
   
   public Vector<Person> getPersonsOf(Team t) {
 		//Wir bedienen uns hier einfach des MembershipMapper.
 		return MembershipMapper.membershipMapper().findByMember(t);
   
}

	 public Vector<Team> getTeamsOf(Person p) {
		   Connection con = DBConnection.connection();
		    Vector<Team> result = new Vector<Team>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT id FROM team "
		          + " Inner JOIN Membership ON Team.Id=Membership.TeamId" + "INNER JOIN person ON person.id=membership.personId");

		      
		      while (rs.next()) {
		        Team t = new Team();
		        t.setId(rs.getInt("id"));
		     
		        result.addElement(t);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    return result;
}
}
