package de.hdm.server.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import com.google.appengine.api.utils.SystemProperty;

import de.hdm.server.ServersideSettings;

//@udo nix, emily kretzschmar


// Verwalten der DB-Verbindung 

public class DBConnection {
	Logger logger = ServersideSettings.getLogger();

   // Klasse DBConnection wird nur einmal instantiiert --> Singleton
   // Variable ist durch den Bezeichner <code>static</code> nur einmal
   // für Instanzen dieser Klasse vorhanden. Sie
   
    private static Connection con = null;

    // Die URL, um die Datenbank anzusprechen
     
    private static String googleUrl = "jdbc:google:mysql://shopit-241614:europe-west1:shopit?user=demo&password=demo";
    private static String localUrl = "jdbc:mysql://127.0.0.1:8889/shopit?characterEncoding=UTF-8&user=root&password=root";

   // statische Methode --> Aufrufbr durch
   // <code>DBConnection.connection()</code>. Sie stellt die
     // Singleton-Eigenschaft sicher (es kann nur eine einzige Instanz von <code>DBConnection</code> existiert)
    //
     // @return DAS <code>DBConncetion</code>-Objekt.
  
    public static Connection connection() {
        // Wenn es noch keine Conncetion zur DB gab, ...
        if (con == null) {
            String url = null;
            try {
                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                    
                    Class.forName("cmysql.jdbc.GoogleDriver");
                    url = googleUrl;
                } else {
                    // Local MySQL instance to use during development.
                    Class.forName("com.mysql.jdbc.Driver");
                    url = localUrl;
                }
                
                 // Erst dann kann uns der DriverManager eine Verbindung mit den
                 //oben in der Variable url angegebenen Verbindungsinformationen
                // aufbauen.
                // Die Verbindung wird in der statischen Variable con
                  //abgespeichert und verwendet.
                 System.out.println(url);
                con = DriverManager.getConnection(url);
            } catch (Exception e) {
                con = null;
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        // Zurückgegeben der Verbindung
        return con;
    }

}
