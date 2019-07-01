package de.hdm.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.shared.bo.Article;

//@author udo nix, emily kretzschmar

public class ArticleMapper {

	// Klasse ArticleMapper als Singleton
	// Variable durch <code> static </code> nur einmal für Instanzen der Klassen
	// vorhanden
	// Sie speichert einzige Instanz der Klasse
	private static ArticleMapper articleMapper = null;

// Konstruktor geschützt, es kann keine neue Instanz dieser Klasse mit <code>new</code> erzeugt werden

	protected ArticleMapper() {
	}

//Aufruf der statischen Methode durch <code>ArticleMapper.articleMapper()</code>. Singleton: Es kann nur eine 
//Instanz von <code>ArticleMapper</code> existieren
//@return articleMapper

	public static ArticleMapper articleMapper() {
		if (articleMapper == null) {
			articleMapper = new ArticleMapper();
		}
		return articleMapper;
	}

// Gruppe mit der vorgegebene Id suchen, Da sie eindeutig ist, wird nur ein Objekt zurueckgegeben
//@parameter id Primärschlüsselattribut
//@return Articleobjekt des übergebenen Schlüssel, null bei nicht vorhandenem Datenbank-Tupel

	public Article findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Anlegen einen leeren SQL-Statement
			Statement stmt = con.createStatement();
			// Ausfüllen des Statements, als Query an die DB schicken
			ResultSet rs = stmt.executeQuery("SELECT * from article WHERE article.id= " + id);

			// Da id Primärschlüssel ist, kann nur ein Tupel zurueckgeg werden.
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

// Auslesen aller Artikel.
	// @return Ein Vektor mit Article-Objekten, die sämtliche Gruppen
	// repräsentieren. Bei Exceptions: Ein partiell gefüllter
//        oder eben leerer Vetor wird zurückgeliefert.

	public Vector<Article> findAll() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor vorbereiten
		Vector<Article> result = new Vector<Article>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM article ORDER BY id");

			// Für jeden Eintrag im Suchergebnis wird nun ein Group-Objekt erstellt.
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

	// Einfügen eines <code>Article</code>-Objekts in die Datenbank. Es wird
	// auch der Primärschlüssel des übergebenen Objekts geprüft und im gegebenen
	// Falle
	// berichtigt.
	// @param a das zu speichernde Objekt
//@return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	// <code>id</code>.

	public Article insert(Article a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Pruefen, welches der momentan höchste Primärschlüsselwert ist.

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM article ");

			// Falls man etw. zurueck bekommt, ist dies nur einzeilig
			if (rs.next()) {
				// a erhält den bisher maximalen, nun um 1 inkrementierten Primärschlüssel.

				a.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// PreparedStatement stmt2 = con.prepareStatement("INSERT INTO article (id,
				// CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, name) VALUES (?, ?, ?, ?)");
				// stmt2.setInt(1, a.getId());
				// stmt2.setString(4, a.getName());

				// Es erfolgt die tatsächliche Einfuegeoperation
				PreparedStatement stmt2 = con
						.prepareStatement("INSERT INTO article (id, creationDate, changeDate, name) "
								+ "VALUES (?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, )");

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return a;
	}

	// Schreiben eines Objekts in die Datenbank.
	// @param a Objekt, das in die Datenbank geschrieben werden soll
	// @return das als Parameter übergebene Objekt

	public Article update(Article a) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE article SET name = '" + a.getName() + "', changeDate = CURRENT_TIMESTAMP "
					+ "WHERE id= " + a.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// a zueruck geben
		return a;
	}

	// Daten eines <code>Article</code>-Objekts aus der Datenbank loeschen.
	// @param a das aus der DB zu loeschende "Objekt"

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
	 * result = new Vector<Article>();
	 * 
	 * try { Statement stmt = con.createStatement();
	 * 
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
	 * 
	 * return result; }
	 */
}