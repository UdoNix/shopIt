package de.hdm.server.db;


	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.Vector;

import de.hdm.shared.bo.Team;
import de.hdm.shared.bo.Membership;
import de.hdm.shared.bo.Person;

	public class MembershipMapper {
		// Klasse MembershipMapper als Singleton
		//Variable durch <code> static </code> nur einmal für Instanzen der Klassen vorhanden
		//Sie speichert einzige Instanz der Klasse
	 private static MembershipMapper membershipMapper = null;

	// Konstruktor geschützt, es kann keine neue Instanz dieser Klasse mit <code>new</code> erzeugt werden

	protected MembershipMapper() {
	}

	//Aufruf der statischen Methode durch <code>MembershipMapper.membershipMapper()</code>. Singleton: Es kann nur eine 
	//Instanz von <code>MembershipMapper</code> existieren
	//@return membershipMapper

	public static MembershipMapper membershipMapper() {
		if (membershipMapper == null) {
			membershipMapper = new MembershipMapper();
		}
		return membershipMapper;
	}

	// Gruppe mit der vorgegebene Id suchen, Da sie eindeutig ist, wird nur ein Objekt zurueckgegeben
	//@parameter id Primärschlüsselattribut
	//@return Membershipobjekt des übergebenen Schlüssel, null bei nicht vorhandenem Datenbank-Tupel

	public Membership findByKey (int id) {
		//DB-Verbindung holen
		Connection con =DBConnection.connection();
		
		try {
			//Anlegen einen leeren SQL-Statement
			Statement stmt =con.createStatement();
			// Ausfüllen des Statements, als Query an die DB schicken
			ResultSet rs =stmt.executeQuery("SELECT * from membership" + "WHERE membership.id =" + id );
			
			//Da id Primärschlüssel ist, kann nur ein Tupel zurueckgeg werden. 
			//Es wird geprueft, ob ein Ergebnis vorliegt.
			   if (rs.next()) {
			        // Ergebnis-Tupel in Objekt umwandeln
			        Membership m = new Membership();
			        m.setId(rs.getInt("id"));
			        m.setPersonId(rs.getInt("personId"));
			        m.setTeamId(rs.getInt("groupId"));
			        return m;
			      }
			    }
			    catch (SQLException e2) {
			      e2.printStackTrace();
			      return null;
			    }

			    return null;
			  }
				
	// Auslesen aller Memberships.
	 // @return Ein Vektor mit Membership-Objekten, die sämtliche Memberships
	 //        repräsentieren. Bei Exceptions: Ein partiell gefüllter
//	        oder eben leerer Vetor wird zurückgeliefert.

	public Vector<Membership> findAll() {
	  Connection con = DBConnection.connection();

	  // Ergebnisvektor vorbereiten
	  Vector<Membership> result = new Vector<Membership>();

	  try {
	    Statement stmt = con.createStatement();

	    ResultSet rs = stmt.executeQuery("SELECT * FROM membership "
	        + " ORDER BY id");

	    // Für jeden Eintrag im Suchergebnis wird nun ein Membership-Objekt erstellt.
	    while (rs.next()) {
	      Membership m = new Membership();
	      m.setId(rs.getInt("id"));
	      m.setPersonId(rs.getInt("personId"));
	      m.setTeamId(rs.getInt("groupId"));

	      // Das neue Objekts wird zum Ergebnisvektor hinzugefuegt
	      result.addElement(m);
	    }
	  }
	  catch (SQLException e2) {
	    e2.printStackTrace();
	  }

	  //Der Ergebnisvektor wird zurueckgegeben
	  return result;
	}
	
	public Vector<Membership>getAllMembershipsOf(int personId) {
		  Connection con = DBConnection.connection();

		  // Ergebnisvektor vorbereiten
		  Vector<Membership> result = new Vector<Membership>();

		  try {
		    Statement stmt = con.createStatement();

		    ResultSet rs = stmt.executeQuery("SELECT * FROM membership  WHERE personId = " + personId 
		        + " ORDER BY id");

		    // Für jeden Eintrag im Suchergebnis wird nun ein Membership-Objekt erstellt.
		    while (rs.next()) {
		      Membership m = new Membership();
		      m.setId(rs.getInt("id"));
		      m.setPersonId(rs.getInt("personId"));
		      m.setTeamId(rs.getInt("groupId"));

		      // Das neue Objekts wird zum Ergebnisvektor hinzugefuegt
		      result.addElement(m);
		    }
		  }
		  catch (SQLException e2) {
		    e2.printStackTrace();
		  }

		  //Der Ergebnisvektor wird zurueckgegeben
		  return result;
		}


	 //Einfügen eines <code>Membership</code>-Objekts in die Datenbank. Es wird
	 // auch der Primärschlüssel des übergebenen Objekts geprüft und im gegebenen Falle
	 // berichtigt.
	 // @param m das zu speichernde Objekt
	//@return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	 //        <code>id</code>.

	public Membership insert(Membership m) {
	  Connection con = DBConnection.connection();

	  try {
	    Statement stmt = con.createStatement();

	    //Pruefen, welches der momentan höchste Primärschlüsselwert ist.
	  
	    ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
	        + "FROM membership ");

	    // Falls man etw. zurueck bekommt, ist dies nur einzeilig 
	    if (rs.next()) {
	      //a erhält den bisher maximalen, nun um 1 inkrementierten Primärschlüssel.
	       
	      m.setId(rs.getInt("maxid") + 1);

	      stmt = con.createStatement();

	      // Es erfolgt die tatsächliche Einfuegeoperation
	      stmt.executeUpdate("INSERT INTO membership (id, personId, groupId) " + "VALUES ("
	          + m.getId() + "," + m.getTeamId() + "," + m.getPersonId() + ")");
	    }
	  }
	  catch (SQLException e2) {
	    e2.printStackTrace();
	  }
	  return m;
	  }

	 // Schreiben eines Objekts in die Datenbank.
	  // @param m  Objekt, das in die Datenbank geschrieben werden soll
	  //@return das als Parameter übergebene Objekt
	  //
	  public Membership update(Membership m) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("UPDATE membership " + "SET personId=\"" + m.getPersonId()
	          + "\", "+ "groupId=\"" + m.getTeamId() + "\", " +"WHERE id=" + m.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // m zueruck geben
	    return m;
	  }
	   

	   // Daten eines <code>Membership</code>-Objekts aus der Datenbank loeschen.
	    // @param m das aus der DB zu loeschende "Objekt"
	   
	   public void delete(Membership m) {
	     Connection con = DBConnection.connection();

	     try {
	       Statement stmt = con.createStatement();

	       stmt.executeUpdate("DELETE FROM membership " + "WHERE id=" + m.getId());

	     }
	     catch (SQLException e2) {
	       e2.printStackTrace();
	     }
	   }
	   
	   public Vector<Person> findByMember(Team group) {

		    return findByMember(group.getId());
	   }
		    
		   public Vector<Person> findByMember(int groupId) {
		        Connection con = DBConnection.connection();
		        Vector<Person> result = new Vector<Person>();

		        try {
		          Statement stmt = con.createStatement();

		          ResultSet rs = stmt.executeQuery("SELECT personId FROM membership"
		              + "WHERE groupId=" + groupId + " ORDER BY id");

		          
		          while (rs.next()) {
		            Person p = new Person();
		            p.setId(rs.getInt("id"));
		            p.setFirstName(rs.getString("firstName"));
			        p.setLastName(rs.getString("lastName"));
			        p.setEmail(rs.getString("email"));
		            // Hinzufügen des neuen Objekts zum Ergebnisvektor
		            result.addElement(p);
		          }
		        }
		        catch (SQLException e2) {
		          e2.printStackTrace();
		        }

		        // Ergebnisvektor zurückgeben
		        return result;
		      }
		   
		   public Vector<Membership> findByGroup(int groupId) {
			    Connection con = DBConnection.connection();
			    Vector<Membership> result = new Vector<Membership>();

			    try {
			      Statement stmt = con.createStatement();

			      ResultSet rs = stmt.executeQuery("SELECT personId, groupId FROM membership "
			          + "WHERE groupId=" + groupId );

			    
			      while (rs.next()) {
			        Membership m = new Membership();
			        m.setPersonId(rs.getInt("personId"));
			        m.setTeamId(rs.getInt("teamId"));

			        // Hinzufügen des neuen Objekts zum Ergebnisvektor
			        result.addElement(m);
			      }
			    }
			    catch (SQLException e2) {
			      e2.printStackTrace();
			    }

			    // Ergebnisvektor zurückgeben
			    return result;
			  }		   
	}

