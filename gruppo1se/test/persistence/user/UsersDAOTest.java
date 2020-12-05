/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.user;

import business.user.*;
import exception.UsersException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
//===============================================================================================================================================
    
    private void insertUserDefault(Statement stm, User user) throws SQLException{
        String insert = "INSERT INTO Users VALUES ('"+user.getUsername()+"','"+user.getPassword()+"'";
        if (user.getClass().getSimpleName().equals("Planner"))
            insert+=",'Planner');";
        else if (user.getClass().getSimpleName().equals("SystemAdministrator"))
            insert+=",'System Administrator');";
        else 
            insert+=",'Maintainer');";
        stm.executeUpdate(insert);
    }    
    /**
     * Test of readUser method, of class UsersDAO.
     */
    @Test
    public void testReadUserRolePlanner() throws UsersException, SQLException{
        System.out.println("readUserPlanner");
        Statement stm = conn.createStatement();
        List<User> expectedList = new ArrayList<>();
        expectedList.add(new Planner("Planner1","PwdPlanner1",null,null));
        expectedList.add(new Planner("Planner2","PwdPlanner2",null,null));
        expectedList.add(new Planner("Planner3","PwdPlanner3",null,null));
        List<User> plannerList = new ArrayList<>();
        insertUserDefault(stm,new Planner("Planner1","PwdPlanner1",null,null));
        insertUserDefault(stm,new Planner("Planner2","PwdPlanner2",null,null));
        insertUserDefault(stm,new Planner("Planner3","PwdPlanner3",null,null));
        plannerList = instance.readUser(null,"Planner");
        assertEquals(true,plannerList.equals(expectedList));
        conn.rollback();
    }
    
        @Test
    public void testReadUserRoleSystemAdministrator() throws UsersException, SQLException{
        System.out.println("readUserSystemAdministrator");
        Statement stm = conn.createStatement();
        List<User> expectedList = new ArrayList<>();
        expectedList.add(new SystemAdministrator("SystemAdministrator1","PwdSystemAdministrator1"));
        expectedList.add(new SystemAdministrator("SystemAdministrator2","PwdSystemAdministrator2"));
        expectedList.add(new SystemAdministrator("SystemAdministrator3","PwdSystemAdministrator3"));
        List<User> systemAdministratorList = new ArrayList<>();
        insertUserDefault(stm,new SystemAdministrator("SystemAdministrator1","PwdSystemAdministrator1"));
        insertUserDefault(stm,new SystemAdministrator("SystemAdministrator2","PwdSystemAdministrator2"));
        insertUserDefault(stm,new SystemAdministrator("SystemAdministrator3","PwdSystemAdministrator3"));
        systemAdministratorList = instance.readUser(null,"System Administrator");
        assertEquals(true,systemAdministratorList.equals(expectedList));
        conn.rollback();
    }
        @Test
    public void testReadUserRoleMaintainer() throws UsersException, SQLException{
        System.out.println("readUserMaintainer");
        Statement stm = conn.createStatement();
        List<User> expectedList = new ArrayList<>();
        expectedList.add(new Maintainer("Maintainer1","PwdMaintainer1"));
        expectedList.add(new Maintainer("Maintainer2","PwdMaintainer2"));
        expectedList.add(new Maintainer ("Maintainer3","PwdMaintainer3"));
        List<User> maintainerList = new ArrayList<>();
        insertUserDefault(stm,new Maintainer("Maintainer1","PwdMaintainer1"));
        insertUserDefault(stm,new Maintainer("Maintainer2","PwdMaintainer2"));
        insertUserDefault(stm,new Maintainer("Maintainer3","PwdMaintainer3"));
        maintainerList = instance.readUser(null,"Maintainer");
        assertEquals(true,maintainerList.equals(expectedList));
        conn.rollback();
    }
    
    @Test
    public void testReadUserUsernamePlanner() throws UsersException, SQLException{
        System.out.println("readUsernamePlanner");
        Statement stm = conn.createStatement();
        List<User> expectedList = new ArrayList<>();
        expectedList.add(new Planner("Planner1","PwdPlanner1",null,null));
        List<User> plannerList = new ArrayList<>();
        insertUserDefault(stm,new Planner("Planner1","PwdPlanner1",null,null));
        plannerList = instance.readUser("Planner1",null);
        assertEquals(true,plannerList.equals(expectedList));
        conn.rollback();
    }
    
    @Test
    public void testReadUserUsernameSystemAdministrator() throws UsersException, SQLException{
        System.out.println("readUsernameSystemAdministrator");
        Statement stm = conn.createStatement();
        List<User> expectedList = new ArrayList<>();
        expectedList.add(new SystemAdministrator("SystemAdministrator1","PwdSystemAdministrator1"));
        List<User> systemAdministratorList = new ArrayList<>();
        insertUserDefault(stm,new SystemAdministrator("SystemAdministrator1","PwdSystemAdministrator1"));
        systemAdministratorList = instance.readUser("SystemAdministrator1",null);
        assertEquals(true,systemAdministratorList.equals(expectedList));
        conn.rollback();
    }
    
    @Test
    public void testReadUserUsernameMaintainer() throws UsersException, SQLException{
        System.out.println("readUsernameMaintainer");
        Statement stm = conn.createStatement();
        List<User> expectedList = new ArrayList<>();
        expectedList.add(new Maintainer("Maintainer1","PwdMaintainer1"));
        List<User> maintainerList = new ArrayList<>();
        insertUserDefault(stm,new Maintainer("Maintainer1","PwdMaintainer1"));
        maintainerList = instance.readUser("Maintainer1",null);
        assertEquals(true,maintainerList.equals(expectedList));
        conn.rollback();
    }
    
        
    @Test
    public void testReadUserWrongUsernameMaintainer() throws UsersException, SQLException{
        System.out.println("readUsernameMaintainer");
        Statement stm = conn.createStatement();
        List<User> expectedList = new ArrayList<>();
        expectedList.add(new Maintainer("Maintainer1","PwdMaintainer1"));
        List<User> maintainerList = new ArrayList<>();
        insertUserDefault(stm,new Maintainer("Maintainer1","PwdMaintainer1"));
        maintainerList = instance.readUser("Maintainer2",null);
        assertEquals(false,maintainerList.equals(expectedList));
        conn.rollback();
    }
    
}
