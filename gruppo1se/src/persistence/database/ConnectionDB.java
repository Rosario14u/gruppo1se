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
 * This class provides a connection to database
 * @author gorra
 */
public class ConnectionDB {
    private static ConnectionDB instance;
    private Connection connection;
    private final String url = "jdbc:postgresql://localhost/Gruppo1_SE";
    private final  String user = "gruppo1";
    private final  String pwd = "123456";

    
    /**
     * Constructor of {@code ConnectionDB} class.
     * @throws SQLException throws an exception 
     * if the connection to the database fails.
     */
    private ConnectionDB() throws SQLException {
        this.connection = DriverManager.getConnection(url, user, pwd);
    }

    
    /**
     * This method returns a connection.
     * @return a {@code Connection}.
     */
    public Connection getConnection() { 
        return connection;
    }

    
    /**
     * This method creates an istance of ConnectionDB
     * if it has not been previously created, 
     * otherwise it returns the already existing one.
     * @return An istance of {@code ConnectionDB}
     * @throws SQLException 
     */
    public synchronized static ConnectionDB getInstanceConnection() throws SQLException {
        if (instance == null) {
            instance = new ConnectionDB();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnectionDB();
        }
        return instance;
    }
}
