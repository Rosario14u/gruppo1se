/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import exception.TypologyException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    
    /**
     * Test of viewTypologies method, of class TypologyDAOImpl.
     */
    
    private void deleteAllTypologiesDefault(Statement stmt) throws SQLException{
        String delete = "DELETE FROM Typology";
        stmt.executeUpdate(delete);
    }
    
    private void insertTypologyDefault(Statement stm, String typology) throws SQLException{
        String insert = "INSERT INTO Typology VALUES ('"+ typology +"')";
        stm.executeUpdate(insert);
    }   
    
    @Test
    public void testViewTypologies() throws TypologyException, SQLException{
        System.out.println("viewTypologies");
        Statement stm = conn.createStatement();
        deleteAllTypologiesDefault(stm);
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Typology1");
        expectedList.add("Typology2");
        expectedList.add("Typology3");
        List<String> typologyList = new ArrayList<>();
        insertTypologyDefault(stm,"Typology1");
        insertTypologyDefault(stm,"Typology2");
        insertTypologyDefault(stm,"Typology3");
        typologyList = instance.viewTypologies();
        assertEquals(true,typologyList.equals(expectedList));
        conn.rollback();
    }
    
}
