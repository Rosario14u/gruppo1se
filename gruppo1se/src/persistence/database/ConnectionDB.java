/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gorra
 */
public class ConnectionDB {
    private static ConnectionDB instance;
    private Connection connection;
    private final String url = "jdbc:postgresql://localhost/Gruppo1_SE";
    private final  String user = "gruppo1";
    private final  String pwd = "123456";

    private ConnectionDB() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() { 
        return connection;
    }

    public synchronized static ConnectionDB getInstanceConnection() throws SQLException {
        if (instance == null) {
            System.out.println("Sto creando una connessione al DB");
            instance = new ConnectionDB();
        } else if (instance.getConnection().isClosed()) {
            System.out.println("Connessione chiusa. Sto ricreando una connessione al DB");
            instance = new ConnectionDB();
        }else{
            System.out.println("Sto riusando la connessione");
        }
        return instance;
    }
}
