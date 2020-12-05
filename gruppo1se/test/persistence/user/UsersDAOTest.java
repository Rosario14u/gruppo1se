/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.user;

import business.user.*;
import exception.UsersException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.database.ConnectionDB;

/**
 *
 * @author aless
 */
public class UsersDAOTest{
    private static Connection conn;
    private static final String INSERTUSER = "INSERT INTO USERS (username, password, role) values (?,?,?)";
    private UsersDAO instance = new UsersDAO();
    
    public UsersDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = ConnectionDB.getInstanceConnection().getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
          conn.setAutoCommit(true);
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void deleteUserDefault(Statement stmt, String username) throws SQLException{
        String delete = "DELETE FROM USERS WHERE username = '" + username + "'";
        stmt.executeUpdate(delete);
    }
    
    private ResultSet selectUserDefault(Statement stmt, String username) throws SQLException{
        String select = "SELECT * FROM USERS WHERE username = '" + username + "'";
        return stmt.executeQuery(select);
    }
    
    private void verify(ResultSet set, User user) throws SQLException{
        assertNotNull(set);
        while(set.next()){
            assertEquals(user.getUsername(), set.getString("username"));
            assertEquals(user.getPassword(), set.getString("password"));
            if(Planner.class.isInstance(user))
                assertEquals("Planner", set.getString("role"));
            else if(Maintainer.class.isInstance(user))
                assertEquals("Maintainer", set.getString("role"));
            else
                assertEquals("System Administrator", set.getString("role"));
        }
    }
    /**
     * Test of addUser method, of class UsersDAO.
     * @throws exception.UsersException
     * @throws java.sql.SQLException
     */
    @Test
    public void testAddUserPlanner() throws UsersException, SQLException{
        System.out.println("addUserPlanner");
        User user = new Planner("ProvaUsername","ProvaPassword", null, null);
        Statement stmt = conn.createStatement();
        deleteUserDefault(stmt, user.getUsername());
        instance.addUser(user);
        verify(selectUserDefault(stmt, "ProvaUsername"), user);
        conn.rollback();
    }
    
    /**
     * Test of addUser method, of class UsersDAO.
     * @throws exception.UsersException
     * @throws java.sql.SQLException
     */
    @Test
    public void testAddUserMaintainer() throws UsersException, SQLException{
        System.out.println("addUserMaintainer");
        User user = new Maintainer("ProvaUsername","ProvaPassword");
        Statement stmt = conn.createStatement();
        deleteUserDefault(stmt, user.getUsername());
        instance.addUser(user);
        verify(selectUserDefault(stmt, "ProvaUsername"), user);
        conn.rollback();
    }
    
    /**
     * Test of addUser method, of class UsersDAO.
     * @throws exception.UsersException
     * @throws java.sql.SQLException
     */
    @Test
    public void testAddUserSystemAdministrator() throws UsersException, SQLException{
        System.out.println("addUserSystemAdministrator");
        User user = new SystemAdministrator("ProvaUsername","ProvaPassword");
        Statement stmt = conn.createStatement();
        deleteUserDefault(stmt, user.getUsername());
        instance.addUser(user);
        verify(selectUserDefault(stmt, "ProvaUsername"), user);
        conn.rollback();
    }
    
    
}
