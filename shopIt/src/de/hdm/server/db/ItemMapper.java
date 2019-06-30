package de.hdm.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;

import de.hdm.shared.bo.Item;
import de.hdm.shared.bo.Person;
import de.hdm.shared.bo.ShoppingList;

//@udo nix, emily kretzschmar
	
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
		ResultSet rs =stmt.executeQuery("SELECT * from item WHERE item.id = " + id );
		
		//Da id Primärschlüssel ist, kann nur ein Tupel zurueckgeg werden. 
		//Es wird geprueft, ob ein Ergebnis vorliegt.
		   if (rs.next()) {
		        // Ergebnis-Tupel in Objekt umwandeln
		        Item i = new Item();
		        i.setId(rs.getInt("id"));
		        i.setCreationDate(rs.getTimestamp("creationDate"));
		        i.setChangeDate(rs.getTimestamp("changeDate"));
		        i.setUnitId(rs.getInt("unitId"));
		        i.setArticleId(rs.getInt("articleId"));
		        i.setTeamId(rs.getInt("teamId"));
		        i.setListId(rs.getInt("listId"));
		        i.setFavorit(rs.getBoolean("favorit"));
		        i.setStatus(rs.getBoolean("status"));

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
      Item i = new Item();
      i.setId(rs.getInt("id"));
      i.setCreationDate(rs.getTimestamp("creationDate"));
      i.setChangeDate(rs.getTimestamp("changeDate"));
      i.setUnitId(rs.getInt("unitId"));
      i.setArticleId(rs.getInt("articleId"));
      i.setTeamId(rs.getInt("teamId"));
      i.setListId(rs.getInt("listId"));
      i.setFavorit(rs.getBoolean("favorit"));
      i.setStatus(rs.getBoolean("status"));
      

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
public Vector<Item> findByList (ShoppingList l){
	
	int listId = l.getId();
	
	Connection con = DBConnection.connection();
	Vector<Item> result = new Vector<Item>();
	

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery(
	      "SELECT *, article.name, unit.measure, unit.amount, person.firstName, shop.name " + 
"FROM item " + 
"INNER JOIN article ON article.id = item.articleId " + 
"INNER JOIN unit ON unit.id = item.unitId " + 
"INNER JOIN responsibility ON responsibility.itemId = item.id " + 
"INNER JOIN person ON responsibility.personId = person.id " + 
"INNER JOIN shop ON responsibility.shopId = shop.id WHERE listId= " + listId + " ORDER BY item.id");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
	      while (rs.next()) {
	        Item i = new Item();
	        i.setId(rs.getInt("id"));
	        i.setCreationDate(rs.getTimestamp("creationDate"));
	        i.setChangeDate(rs.getTimestamp("changeDate"));
	        i.setUnitId(rs.getInt("unitId"));
	        i.setArticleId(rs.getInt("articleId"));
	        i.setTeamId(rs.getInt("teamId"));
	        i.setListId(rs.getInt("listId"));
	        i.setFavorit(rs.getBoolean("favorit"));
	        i.setStatus(rs.getBoolean("status"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.add(i);
	      }
	      System.out.println(result.size());
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
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


      PreparedStatement stmt2 = con.prepareStatement("INSERT INTO ITEM(id, creationDate, unitId, articleId, teamId, listId, favorit, status) VALUES (?,CURRENT_TIMESTAMP,?,?,?,?,?,?)");

      stmt2.setInt(1, i.getId());
      stmt2.setInt(2, i.getUnitId());
      stmt2.setInt(3, i.getArticleId());
      stmt2.setInt(4, i.getTeamId());
      stmt2.setInt(5, i.getListId());
      stmt2.setBoolean(6, i.isFavorit());
      stmt2.setBoolean(7, i.isStatus());
         
      stmt2.execute();

    }
  }

  catch (SQLException e2) {
    e2.printStackTrace(); 
  }
  return i;
}

 // Schreiben eines Objekts in die Datenbank.
  // @param i  Objekt, das in die Datenbank geschrieben werden soll
  //@return das als Parameter übergebene Objekt
   
// TODO correct it
  public Item update(Item i) {
    Connection con = DBConnection.connection();
    
    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE item SET id= " + i.getId() + ", teamId= " + i.getTeamId() + ", unitId= " + i.getUnitId() + ", articleId= " + i.getArticleId() + ", status= " + i.isStatus() + ", listid=  " + i.getListId() + ", favorit= " + i.isFavorit() + " WHERE id= " + i.getId());

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

       stmt.executeUpdate("DELETE FROM item " + "WHERE id= " + i.getId());

     }
     catch (SQLException e2) {
       e2.printStackTrace();
     }
   }
   
   //Wrong name and foreign key constrain doesn't exist anymore
   @Deprecated
   public Vector<Item> groupByShop (){
	    Connection con = DBConnection.connection();
	    Vector<Item> result = new Vector<Item>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, salesmanId FROM item "
	          + " Group By shopId");

	      // Für jeden Eintrag im Suchergebnis wird nun ein Account-Objekt erstellt.
	      while (rs.next()) {
	        Item i = new Item();
	        i.setId(rs.getInt("id"));
	        i.setShopId(rs.getInt("shopId"));

	        // Hinzufügen des neuen Objekts zum Ergebnisvektor
	        result.addElement(i);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    // Ergebnisvektor zurückgeben
	    return result;
	  }

   
  //Methode, die alle Einträge einer Person zurüchgibt
   public Vector<Item> getItemsOf(Person p) {
	   Connection con = DBConnection.connection();
	    Vector<Item> result = new Vector<Item>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT * FROM item "
	          + " INNER JOIN shop ON responsibility.shopId=Shop.Id" + "INNER JOIN responsibility ON responsibility.shopId=shop.Id");

	      
	      while (rs.next()) {
	        Item i = new Item();
	        i.setId(rs.getInt("id"));
	     
	        result.addElement(i);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;
		
}
	 public Vector<Item> getItemsbyTeamWithTime (int TeamId, Timestamp firstDate, Timestamp lastDate) {
		   Connection con = DBConnection.connection();
		    Vector<Item> result = new Vector<Item>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery(
		    		  "SELECT item.id AS id, COUNT(articleId) AS count, team.id AS 'team.id', item.changeDate AS changeDate"
		    		  + "FROM item INNER JOIN team ON item.teamId = team.id "
		    		  + "WHERE teamID= TeamId AND item.changeDate BETWEEN "+ firstDate +" AND "+ lastDate +""
		    		  		+ "GROUP BY item.id ORDER BY COUNT(item.articleId) DESC"
//		    		  "SELECT COUNT(articleId), item.id AS id, team.id AS 'team.id', item.changeDate FROM item INNER JOIN "
//		      		+ "team ON item.teamId = team.id" + "WHERE teamID= TeamId ( AND  item.changeDate  BETWEEN "+ firstDate +" AND "+ lastDate +" )  GROUP BY id" + 
//		      				"ORDER BY COUNT(articleId) DESC"
		      		);
 
		      
		      while (rs.next()) {
		        Item i = new Item();
		        i.setId(rs.getInt("id"));
		        i.setCount(rs.getInt("count"));
		        i.setId(rs.getInt("teamId"));
		        i.setChangeDate(rs.getTimestamp("changeDate"));
		     
		        result.addElement(i);
		      }
		    }
		    catch (SQLException e2) {
		      e2.printStackTrace();
		    }

		    return result;
}
	 

public Vector<Item> getItemsbyTeamAndShop(int teamId, int shopId) {
	   Connection con = DBConnection.connection();
	    Vector<Item> result = new Vector<Item>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT item.id AS id,  COUNT(item.id) AS count, responsibility.shopId AS shopId, teamId FROM item INNER JOIN responsibility"
	      		+ "ON item.id = responsibility.itemId"
	      +"WHERE item.teamID= " + teamId + " AND item.shopId= " + shopId);
	      				
	      
	      while (rs.next()) {
	        Item i = new Item();
	        i.setShopId (rs.getInt("id"));
	        i.setId(rs.getInt("count"));
	        i.setCount(rs.getInt("count"));
	        i.setShopId(rs.getInt("shopId"));
	        i.setTeamId(rs.getInt("teamId"));
	     
	        result.addElement(i);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;



}


public Vector<Item> getItemsByTeamAndShopWithTime (int teamId, int shopId, Timestamp firstDate, Timestamp lastDate) {
	   Connection con = DBConnection.connection();
	    Vector<Item> result = new Vector<Item>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT item.id, COUNT(item.id) AS 'count', shop.Id AS 'shopId', team.id AS 'team.id', item.changeDate"
	      		+ " FROM item INNER JOIN responsibility"
		      		+ "ON item.id = responsibility.itemId"
		  	      +"WHERE item.teamId= " + teamId + " AND responsibility.shopId= " + shopId + "AND item.changeDate BETWEEN "+ firstDate +" AND "+ lastDate +""
		  		+ "GROUP BY item.id ORDER BY COUNT(item.articleId) DESC");
	      				
	      
	      while (rs.next()) {
	        Item i = new Item();
	        i.setId(rs.getInt("id"));
	        i.setShopId (rs.getInt("shopId"));
	        i.setCount (rs.getInt("count"));
	        i.setTeamId(rs.getInt("itemId"));
	        i.setChangeDate(rs.getTimestamp("changeDate"));
	        
	        
	     
	        result.addElement(i);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;
}
}

   

