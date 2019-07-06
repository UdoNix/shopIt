package de.hdm.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.shared.bo.Article;

/**@author udo nix, emily kretzschmar **/

public class ArticleMapper {

	/**Klasse ArticleMapper als Singleton
	 *Variable durch <code> static </code> nur einmal f�r Instanzen der Klassen
	 *vorhanden
	 *Sie speichert einzige Instanz der Klasse **/
	private static ArticleMapper articleMapper = null;

/** Konstruktor gesch�tzt, es kann keine neue Instanz dieser Klasse mit <code>new</code> erzeugt werden**/

	protected ArticleMapper() {
	}

/**Aufruf der statischen Methode durch <code>ArticleMapper.articleMapper()</code>. Singleton: Es kann nur eine 
 *Instanz von <code>ArticleMapper</code> existieren
 * @return articleMapper
 * 
 */

	public static ArticleMapper articleMapper() {
		if (articleMapper == null) {
			articleMapper = new ArticleMapper();
		}
		return articleMapper;
	}

/**
 *  Gruppe mit der vorgegebene Id suchen, Da sie eindeutig ist, wird nur ein Objekt zurueckgegeben
 * @param id
 * @return Articleobjekt des �bergebenen Schl�ssel, null bei nicht vorhandenem Datenbank-Tupel **/

	public Article findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Anlegen einen leeren SQL-Statement
			Statement stmt = con.createStatement();
			// Ausf�llen des Statements, als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT * from article WHERE article.id= " + id);

			// Da id Prim�rschl�ssel ist, kann nur ein Tupel zurueckgeg werden.
			// Es wird geprueft, ob ein Ergebnis vorliegt.
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				Article a = new Article();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setCreationDate(rs.getTimestamp("creationDate"));
				a.setChangeDate(rs.getTimestamp("changeDate"));
				return a;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

/** Auslesen aller Artikel.
	*Ein Vektor mit Article-Objekten, die s�mtliche Gruppen
	*repr�sentieren. Bei Exceptions: Ein partiell gef�llter
       *oder eben leerer Vetor wird zur�ckgeliefert.**/

	public Vector<Article> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Article> result = new Vector<Article>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM article ORDER BY id");

			// F�r jeden Eintrag im Suchergebnis wird nun ein Group-Objekt erstellt.
			while (rs.next()) {
				Article a = new Article();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));
				a.setCreationDate(rs.getTimestamp("creationDate"));
				a.setChangeDate(rs.getTimestamp("changeDate"));

				// Das neue Objekts wird zum Ergebnisvektor hinzugefuegt
				result.addElement(a);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Der Ergebnisvektor wird zurueckgegeben
		return result;
	}

	/** Einf�gen eines <code>Article</code>-Objekts in die Datenbank. Es wird
	* auch der Prim�rschl�ssel des �bergebenen Objekts gepr�ft und im gegebenen
	*Falle
	*berichtigt.
	* @param a das zu speichernde Objekt
    *@return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	* <code>id</code>.
	
	 */

	public Article insert(Article a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Pruefen, welches der momentan h�chste Prim�rschl�sselwert ist.

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM article ");

			// Falls man etw. zurueck bekommt, ist dies nur einzeilig
			if (rs.next()) {
				// a erh�lt den bisher maximalen, nun um 1 inkrementierten Prim�rschl�ssel.

				a.setId(rs.getInt("maxid") + 1);

				// PreparedStatement stmt2 = con.prepareStatement("INSERT INTO article (id,
				// CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, name) VALUES (?, ?, ?, ?)");
				// stmt2.setInt(1, a.getId());
				// stmt2.setString(4, a.getName());

				// Es erfolgt die tats�chliche Einfuegeoperation
				PreparedStatement stmt2 = con
						.prepareStatement("INSERT INTO article (id, creationDate, changeDate, name) "
								+ "VALUES (?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)");
				stmt2.setInt(1, a.getId());
				stmt2.setString(2, a.getName());
				stmt2.execute();

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return a;
	}

	/**
	 *  Schreiben eines Objekts in die Datenbank.
	 * @param a Objekt, das in die Datenbank geschrieben werden soll
	// 
	 * @return das als Parameter �bergebene Objekt
	 */
	
	

	public Article update(Article a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			String sql = "UPDATE article SET name = '" + a.getName() + "', changeDate = CURRENT_TIMESTAMP "
					+ "WHERE id= " + a.getId();
			System.out.println(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// a zueruck geben
		return a;
	}

	/**
	 *  Daten eines <code>Article</code>-Objekts aus der Datenbank loeschen.
	 * @param a das aus der DB zu loeschende "Objekt"
	 */


	public void delete(Article a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM article " + "WHERE id= " + a.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public Vector<Article> findByName(Article article) {

		Connection con = DBConnection.connection();
		// Anmerkung: Da der Name eines Artikels nicht nur einmal, sondern auch mehrfach
		// gleich
		// vergeben sein kann --> Vector verwendet
		Vector<Article> result = new Vector<Article>();
		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, name FROM article " + "WHERE name LIKE '" + article.getName() + "' ORDER BY name ASC");

			while (rs.next()) {

				Article a = new Article();
				a.setId(rs.getInt("id"));
				a.setName(rs.getString("name"));

				result.addElement(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}
	/*
	 * public Vector<Article> countArticles (int TeamId, Date firstDate, Date
	 * lastDate) { Connection con = DBConnection.connection(); Vector<Article>
	 * result = new Vector<Article>(); try { Statement stmt = con.createStatement();
	 * ResultSet rs =
	 * stmt.executeQuery("SELECT COUNT (articleId), articleId FROM item INNER JOIN"
	 * + "team ON item.teamId = team.id" +
	 * "WHERE teamID= TeamId AND (id.getChangeDate() BETWEEN firstDate AND lastDate) "
	 * );
	 * 
	 * 
	 * while (rs.next()) { Article a = new Article();
	 * a.setFrequency(rs.getInt("frequency")); a.setId(rs.getInt("id"));
	 * 
	 * result.addElement(a); } } catch (SQLException e2) { e2.printStackTrace(); }
	 * return result; }
	 */
}