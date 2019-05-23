package de.hdm.server;


import java.sql.Connection;
import java.sql.DriverManager;

// import com.google.appengine.api.utils.SystemProperty;


// Verwalten der DB-Verbindung 

public class DBConnection {

   // Klasse DBConnection wird nur einmal instantiiert --> Singleton
   // Variable ist durch den Bezeichner <code>static</code> nur einmal
   // für Instanzen dieser Klasse vorhanden. Sie
   
    private static Connection con = null;

    // Die URL, um die Datenbank anzusprechen
     
    private static String googleUrl = "";
    private static String localUrl = "jdbc:mysql://127.0.0.1:3306/shopit?user=user&password=demo";

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
//                if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
//                    
//                    Class.forName("");
//                    url = googleUrl;
//                } else {
                    // Local MySQL instance to use during development.
                    Class.forName("com.mysql.jdbc.Driver");
                    url = localUrl;
//                }
                
                 // Erst dann kann uns der DriverManager eine Verbindung mit den
                 //oben in der Variable url angegebenen Verbindungsinformationen
                // aufbauen.
                // Die Verbindung wird in der statischen Variable con
                  //abgespeichert und verwendet.
                 
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