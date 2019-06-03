package de.hdm.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.shared.bo.Person;


public class PersonMapper {	
	// Klasse PersonMapper als Singleton
	//Variable durch <code> static </code> nur einmal für Instanzen der Klassen vorhanden
	//Sie speichert einzige Instanz der Klasse
 private static PersonMapper personMapper = null;

// Konstruktor geschützt, es kann keine neue Instanz dieser Klasse mit <code>new</code> erzeugt werden

protected PersonMapper() {
}

//Aufruf der statischen Methode durch <code>GroupMapper.groupMapper()</code>. Singleton: Es kann nur eine 
//Instanz von <code>PersonMapper</code> existieren
//@return personMapper

public static PersonMapper personMapper() {
	if (personMapper == null) {
		personMapper = new PersonMapper();
	}
	return personMapper;
}

// Liste mit der vorgegebene Id suchen, Da sie eindeutig ist, wird nur ein Objekt zurueckgegeben
//@parameter id Primärschlüsselattribut
//@return Listenobjekt des übergebenen Schlüssel, null bei nicht vorhandenem Datenbank-Tupel

public Person findByKey (int id) {
	//DB-Verbindung holen
	Connection con =DBConnection.connection();
	
	try {
		//Anlegen einen leeren SQL-Statement
		Statement stmt =con.createStatement();
		// Ausfüllen des Statements, als Query an die DB schicken
		ResultSet rs =stmt.executeQuery("SELECT * from person" + "WHERE list.id =" + id );
		
		//Da id Primärschlüssel ist, kann nur ein Tupel zurueckgeg werden. 
		//Es wird geprueft, ob ein Ergebnis vorliegt.
		   if (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Person p = new Person();
		        p.setId(rs.getInt("id"));
		        p.setFirstName(rs.getString("firstName"));
		        p.setLastName(rs.getString("lastName"));
		        p.setEmail(rs.getString("email"));
		        p.setCreationDate(rs.getTimestamp("creationDate"));
		        p.setChangeDate(rs.getTimestamp("changeDate"));		      
		
		        return p;
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		    }

		    return null;
		  }
			
// Auslesen aller Listen.
 // @return Ein Vektor mit Person-Objekten, die sämtliche Personen
 //        repräsentieren. Bei Exceptions: Ein partiell gefüllter
//        oder eben leerer Vetor wird zurückgeliefert.

public Vector<Person> findAll() {
  Connection con = DBConnection.connection();

  // Ergebnisvektor vorbereiten
  Vector<Person> result = new Vector<Person>();

  try {
    Statement stmt = con.createStatement();

    ResultSet rs = stmt.executeQuery("SELECT * FROM person "
        + " ORDER BY id");

    // Für jeden Eintrag im Suchergebnis wird nun ein List-Objekt erstellt.
    while (rs.next()) {
      Person p = new Person();
      p.setId(rs.getInt("id"));
      p.setFirstName(rs.getString("firstName"));
      p.setLastName(rs.getString("lastName"));
      p.setEmail(rs.getString("email"));
      p.setCreationDate(rs.getTimestamp("creationDate"));
      p.setChangeDate(rs.getTimestamp("changeDate"));
      // Das neue Objekts wird zum Ergebnisvektor hinzugefuegt
      result.addElement(p);
    }
  }
  catch (SQLException e2) {
    e2.printStackTrace();
  }

  //Der Ergebnisvektor wird zurueckgegeben
  return result;
}


 //Einfügen eines <code>Person</code>-Objekts in die Datenbank. Es wird
 // auch der Primärschlüssel des übergebenen Objekts geprüft und im gegebenen Falle
 // berichtigt.
 // @param p das zu speichernde Objekt
//@return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
 //        <code>id</code>.

public Person insert(Person p) {
  Connection con = DBConnection.connection();

  try {
    Statement stmt = con.createStatement();

    //Pruefen, welches der momentan höchste Primärschlüsselwert ist.
  
    ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
        + "FROM person ");

    // Falls man etw. zurueck bekommt, ist dies nur einzeilig 
    if (rs.next()) {
      //g erhält den bisher maximalen, nun um 1 inkrementierten Primärschlüssel.
       
      p.setId(rs.getInt("maxid") + 1);

      stmt = con.createStatement();

      // Es erfolgt die tatsächliche Einfuegeoperation
      stmt.executeUpdate("INSERT INTO person (id, firstName, lastName, email) " + "VALUES ("
          + p.getId() + ","+ p.getFirstName() +  ","+ p.getEmail() + "," + p.getLastName() + ")");
    }
  }
  catch (SQLException e2) {
    e2.printStackTrace();
  }
  return p;
}

 // Schreiben eines Objekts in die Datenbank.
  // @param p  Objekt, das in die Datenbank geschrieben werden soll
  //@return das als Parameter übergebene Objekt
   
  public Person update(Person p) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE person " + "SET namide=\"" + p.getId()
      + "\", "+ "firstName=\"" + p.getFirstName() + "\", " + "lastName=\"" + p.getLastName() +"\", " + "email=\"" + p.getEmail()+ "\", "+ "WHERE id=" + p.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    // p zueruck geben
    return p;
  }
   

   // Daten eines <code>Person</code>-Objekts aus der Datenbank loeschen.
    // @param p das aus der DB zu loeschende "Objekt"
   
   public void delete(Person p) {
     Connection con = DBConnection.connection();

     try {
       Statement stmt = con.createStatement();

       stmt.executeUpdate("DELETE FROM person " + "WHERE id=" + p.getId());

     }
     catch (SQLException e2) {
       e2.printStackTrace();
     }
   }
   
   public Vector<Person> findByName(Person person) {
		
		Connection con =DBConnection.connection();
		//Anmerkung: Da der Name einer Person nicht nur einmal, sondern auch mehrfach gleich 
		//vergeben sein kann --> Vector verwendet
		Vector<Person> result = new Vector<Person>();
		try {
			
			Statement stmt =con.createStatement();
			
			ResultSet rs =stmt.executeQuery("SELECT id,lastName,firstName from person" + "WHERE firstName LIKE'"+ person.getFirstName()+"'" +"OR lastName LIKE'"+ person.getLastName()+"'" );
			
			   while (rs.next()) {
			       
			        Person p = new Person();
			        p.setId(rs.getInt("id"));
			        p.setFirstName(rs.getString("firstName"));
			        p.setLastName(rs.getString("lastName"));
			  
					
					result.addElement(p);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
			return result;
		}

}
