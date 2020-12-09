/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import exception.TypologyException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.*;
import persistence.database.ConnectionDB;

/**
 *
 * @author aless
 */
public class TypologyDAOImplTest{
    private static Connection conn;
    private final TypologyDAOImpl instance = new TypologyDAOImpl();
    
    public TypologyDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = ConnectionDB.getInstanceConnection().getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(TypologyDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
          conn.setAutoCommit(true);
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(TypologyDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Test of addTypology method, of class TypologyDAOImpl.
     * @throws exception.TypologyException
     */
    @Test
    public void testAddTypology() throws TypologyException{
        try {
            System.out.println("addTypology");
            String typology = "ProvaTypology";
            boolean result = instance.addTypology(typology);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test(expected = TypologyException.class)
    public void testAddTypologyException() throws TypologyException{
        try {
            System.out.println("addTypologyException");
            String typology = null;
            boolean result = instance.addTypology(typology);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
}
