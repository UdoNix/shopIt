package de.hdm.server.db;


	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.Vector;

import de.hdm.shared.bo.List;
import de.hdm.shared.bo.Person;

public class ListMapper {	
		// Klasse ListMapper als Singleton
		//Variable durch <code> static </code> nur einmal für Instanzen der Klassen vorhanden
		//Sie speichert einzige Instanz der Klasse
	 private static ListMapper listMapper = null;

	// Konstruktor geschützt, es kann keine neue Instanz dieser Klasse mit <code>new</code> erzeugt werden

	protected ListMapper() {
	}

	//Aufruf der statischen Methode durch <code>ListMapper.groupMapper()</code>. Singleton: Es kann nur eine 
	//Instanz von <code>ListMapper</code> existieren
	//@return listMapper

	public static ListMapper listMapper() {
		if (listMapper == null) {
			listMapper = new ListMapper();
		}
		return listMapper;
	}

	// Liste mit der vorgegebene Id suchen, Da sie eindeutig ist, wird nur ein Objekt zurueckgegeben
	//@parameter id Primärschlüsselattribut
	//@return Listenobjekt des übergebenen Schlüssel, null bei nicht vorhandenem Datenbank-Tupel

	public List findByKey (int id) {
		//DB-Verbindung holen
		Connection con =DBConnection.connection();
		
		try {
			//Anlegen einen leeren SQL-Statement
			Statement stmt =con.createStatement();
			// Ausfüllen des Statements, als Query an die DB schicken
			ResultSet rs =stmt.executeQuery("SELECT * from list" + "WHERE list.id =" + id );
			
			//Da id Primärschlüssel ist, kann nur ein Tupel zurueckgeg werden. 
			//Es wird geprueft, ob ein Ergebnis vorliegt.
			   if (rs.next()) {
			        // Ergebnis-Tupel in Objekt umwandeln
			        List l = new List();
			        l.setId(rs.getInt("id"));
			        l.setName(rs.getString("name"));
			        l.setCreationDate(rs.getTimestamp("creationDate"));
			        l.setChangeDate(rs.getTimestamp("changeDate"));
			        return l;
			      }
			    }
			    catch (SQLException e2) {
			      e2.printStackTrace();
			      return null;
			    }

			    return null;
			  }
				
	// Auslesen aller Listen.
	 // @return Ein Vektor mit List-Objekten, die sämtliche Gruppen
	 //        repräsentieren. Bei Exceptions: Ein partiell gefüllter
//	        oder eben leerer Vetor wird zurückgeliefert.

	public Vector<List> findAll() {
	  Connection con = DBConnection.connection();

	  // Ergebnisvektor vorbereiten
	  Vector<List> result = new Vector<List>();

	  try {
	    Statement stmt = con.createStatement();

	    ResultSet rs = stmt.executeQuery("SELECT * FROM list "
	        + " ORDER BY id");

	    // Für jeden Eintrag im Suchergebnis wird nun ein List-Objekt erstellt.
	    while (rs.next()) {
	      List l = new List();
	      l.setId(rs.getInt("id"));
	      l.setName(rs.getString("name"));
	      l.setCreationDate(rs.getTimestamp("creationDate"));
	      l.setChangeDate(rs.getTimestamp("changeDate"));
	      l.setTeamId(rs.getInt("groupId"));

	      // Das neue Objekts wird zum Ergebnisvektor hinzugefuegt
	      result.addElement(l);
	    }
	  }
	  catch (SQLException e2) {
	    e2.printStackTrace();
	  }

	  //Der Ergebnisvektor wird zurueckgegeben
	  return result;
	}
	
	public Vector<List> findByGroup(int groupID) {
		Connection con = DBConnection.connection();
		
		Vector<List> result = new Vector<List>();
		
		try {
			Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT id, name FROM list "
		          + "WHERE goup=" + groupID + " ORDER BY id");
		      while (rs.next()) {
		    	  List l = new List();
			        l.setId(rs.getInt("id"));
			        l.setName(rs.getString("name"));
			        l.setCreationDate(rs.getTimestamp("creationDate"));
				    l.setChangeDate(rs.getTimestamp("changeDate"));
				    l.setTeamId(rs.getInt("groupId"));

			        result.addElement(l);
			        
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Ergebnisvektor zurückgeben
		    return result;
		  }
	
	/*
	 * @author Udo Nix
	 */
	public Vector<List>getAllListsOf(Person p) {
		Connection con = DBConnection.connection();
		
		Vector<List> result = new Vector<List>();
		
		
		
		try {
			Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT * "
		      		+ "FROM List L "
		      		+ "JOIN Group G ON L.groupId = G.id"
		      		+ "JOIN Membership M ON G.Id = M.groupId"
		      		+ "WHERE personId = " + p.getId() + " ORDER BY id");
		      while (rs.next()) {
		    	  List l = new List();
			        l.setId(rs.getInt("id"));
			        l.setName(rs.getString("name"));
			        l.setCreationDate(rs.getTimestamp("creationDate"));
				    l.setChangeDate(rs.getTimestamp("changeDate"));
				    l.setTeamId(rs.getInt("groupId"));

			        result.addElement(l);
			        
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    // Ergebnisvektor zurückgeben
		    return result;
		  }
		


	 //Einfügen eines <code>List</code>-Objekts in die Datenbank. Es wird
	 // auch der Primärschlüssel des übergebenen Objekts geprüft und im gegebenen Falle
	 // berichtigt.
	 // @param l das zu speichernde Objekt
	//@return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 //        <code>id</code>.

	public List insert(List l) {
	  Connection con = DBConnection.connection();

	  try {
	    Statement stmt = con.createStatement();

	    //Pruefen, welches der momentan höchste Primärschlüsselwert ist.
	  
	    ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	        + "FROM list ");

	    // Falls man etw. zurueck bekommt, ist dies nur einzeilig 
	    if (rs.next()) {
	      //g erhält den bisher maximalen, nun um 1 inkrementierten Primärschlüssel.
	       
	      l.setId(rs.getInt("maxid") + 1);

	      stmt = con.createStatement();

	      // Es erfolgt die tatsächliche Einfuegeoperation
	      stmt.executeUpdate("INSERT INTO list (id, name, groupId) " + "VALUES ("
	          + l.getId() + "," + l.getName() + ","+ l.getTeamId() +")");
	    }
	  }
	  catch (SQLException e2) {
	    e2.printStackTrace();
	  }
	  return l;
	}

	 // Schreiben eines Objekts in die Datenbank.
	  // @param l  Objekt, das in die Datenbank geschrieben werden soll
	  //@return das als Parameter übergebene Objekt
	   
	  public List update(List l) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE list " + "SET name=\"" + l.getName()
          + "\", " + "groupId=\"" + l.getTeamId()+"\", "+ "WHERE id=" + l.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // l zueruck geben
	    return l;
	  }
	   

	   // Daten eines <code>List</code>-Objekts aus der Datenbank loeschen.
	    // @param l das aus der DB zu loeschende "Objekt"
	   
	   public void delete(List l) {
	     Connection con = DBConnection.connection();

	     try {
	       Statement stmt = con.createStatement();

	       stmt.executeUpdate("DELETE FROM list " + "WHERE id=" + l.getId());

	     }
	     catch (SQLException e2) {
	       e2.printStackTrace();
	     }
	   }

}
