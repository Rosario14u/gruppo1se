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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import persistence.database.ConnectionDB;
import persistence.maintenanceactivity.MaintenanceProcedureDAOImplTest;
import persistence.maintenanceactivity.MaintenanceActivityDAOImplTest;

/**
 *
 * @author aless
 */
public class UsersDAOImplTest{
    private static Connection conn;
    private static final String INSERTUSER = "INSERT INTO USERS (username, password, role) values (?,?,?)";
    private static final String DELETE_USERS = "DELETE FROM Users WHERE username=?";
    private static final String INSERT_USERS = "INSERT INTO Users (username, password, role) VALUES (?,?,?)";
    private static final String SELECT_USERS = "SELECT * FROM Users WHERE username=?";
    private final UsersDAOImpl instance = new UsersDAOImpl();
    private final UsersDAOImpl instance2 = new UsersDAOImpl();
    
    public UsersDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = ConnectionDB.getInstanceConnection().getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
          conn.setAutoCommit(true);
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(UsersDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @Before
    public void setUp() {
        
        
    }
    
    @After
    public void tearDown() {
        try {
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
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
     * //@throws exception.UsersException
     * //@throws java.sql.SQLException
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
     * //@throws exception.UsersException
     * //@throws java.sql.SQLException
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
     * //@throws exception.UsersException
     * //@throws java.sql.SQLException
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
    
    
    //========================================== MODIFY USER =============================================================================
    
    
    private static final String DELETE_USER = "DELETE FROM users WHERE username=?";
    private static final String INSERT_USER = "INSERT INTO users (username, password, role) VALUES (?,?,?)";
    private static final String SELECT_USER = "SELECT * FROM users WHERE username = ?";

    private void insertUser(String username, String password, String role) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_USER);
        pstm.setString(1, username);
        pstm.setString(2, password);
        pstm.setString(3, role);
        pstm.executeUpdate();
    }

    private void removeUser(String username) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(DELETE_USER);
        pstm.setString(1, username);
        pstm.executeUpdate();
    }
    
    private ResultSet selectUser(String username) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(SELECT_USER);
        pstm.setString(1, username);
        return pstm.executeQuery();
    }
    
    private User createUser(String username, String password, String role){
        if(role.equals("Planner")){
            return new Planner(username, password, null, null);
        }else if(role.equals("Maintainer")){
            return new Maintainer(username, password);
        }else{
            return new SystemAdministrator(username, password, null, null);
        }
    }
    
    
    private void verifyUser(ResultSet res, User newUser) throws SQLException {
        boolean isEmpty = true;
        while(res.next()){
            isEmpty = false;
            assertEquals(newUser.getUsername(), res.getString("username"));
            assertEquals(newUser.getPassword(), res.getString("password"));
            if(newUser instanceof Planner){
                assertEquals("Planner", res.getString("role"));
            }else if(newUser instanceof Maintainer){
                assertEquals("Maintainer", res.getString("role"));
            }else
                assertEquals("System Administrator", res.getString("role"));
        }
        assertFalse(isEmpty);
    }
    
    private void isEmptyResultSet(String username) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(SELECT_USER);
        pstm.setString(1, username);
        ResultSet rs = pstm.executeQuery();
        boolean isEmpty = true;
        while(rs.next()){
            isEmpty = false;
        }
        assertTrue("isEmpty",isEmpty);
    }
        
    
    //======================================= TEST MODIFY ====================================================================================

    @Test
    public void testUpdateUserPlannerToMaintainer() {        
        try {
            String username = "username";
            removeUser(username);
            insertUser(username, "password", "Planner");
            User modifiedUser = createUser("newUsername", "newPassword", "Maintainer");
            assertTrue(instance.updateUser(username, modifiedUser));
            verifyUser(selectUser("newUsername"), modifiedUser);
            isEmptyResultSet(username);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test
    public void testUpdateUserPlannerToSystemAdministrator() {        
        try {
            String username = "username";
            removeUser(username);
            insertUser(username, "password", "Planner");
            User modifiedUser = createUser("newUsername", "newPassword", "System Administrator");
            assertTrue(instance.updateUser(username, modifiedUser));
            verifyUser(selectUser("newUsername"), modifiedUser); 
            isEmptyResultSet(username);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test
    public void testUpdateUserMaintainerToPlanner() {        
        try {
            String username = "username";
            removeUser(username);
            insertUser(username, "password", "Maintainer");
            User modifiedUser = createUser("newUsername", "newPassword", "Planner");
            assertTrue(instance.updateUser(username, modifiedUser));
            verifyUser(selectUser("newUsername"), modifiedUser);   
            isEmptyResultSet(username);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test
    public void testUpdateUserMaintainerToSystemAdministrator() {        
        try {
            String username = "username";
            removeUser(username);
            insertUser(username, "password", "Maintainer");
            User modifiedUser = createUser("newUsername", "newPassword", "System Administrator");
            assertTrue(instance.updateUser(username, modifiedUser));
            verifyUser(selectUser("newUsername"), modifiedUser);   
            isEmptyResultSet(username);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test
    public void testUpdateUserSystemAdministratorToPlanner() {        
        try {
            String username = "username";
            removeUser(username);
            insertUser(username, "password", "System Administrator");
            User modifiedUser = createUser("newUsername", "newPassword", "Planner");
            assertTrue(instance.updateUser(username, modifiedUser));
            verifyUser(selectUser("newUsername"), modifiedUser); 
            isEmptyResultSet(username);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test
    public void testUpdateUserSystemAdministratorToMaintainer() {        
        try {
            String username = "username";
            removeUser(username);
            insertUser(username, "password", "System Administrator");
            User modifiedUser = createUser("newUsername", "newPassword", "Maintainer");
            assertTrue(instance.updateUser(username, modifiedUser));
            verifyUser(selectUser("newUsername"), modifiedUser);   
            isEmptyResultSet(username);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test
    public void testModifyActivityNotPresent(){
        try {
            String username = "username";
            removeUser(username);
            User modifiedUser = createUser("newUsername", "newPassword", "Maintainer");
            assertFalse(instance.updateUser(username, modifiedUser));            
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (UsersException ex){
            fail("UsersException");
        }
    }
    //===============================================================================================================================================
    
    private void insertUser(String username, String password, String role) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_USERS);
        pstm.setString(1, username);
        pstm.setString(2, password);
        pstm.setString(3, role);
        pstm.executeUpdate();
    }

    private void removeUser(String username) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(DELETE_USERS);
        pstm.setString(1, username);
        pstm.executeUpdate();
    }
    
    private void isEmptyResultSet(String username) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(SELECT_USERS);
        pstm.setString(1, username);
        ResultSet rs = pstm.executeQuery();
        boolean isEmpty = true;
        while(rs.next()){
            isEmpty = false;
        }
        assertTrue("isEmpty",isEmpty);
    }
    
    
    /**
     * this method assert that deleteUser correctly delete the rows in database
     */
    @Test
    public void testDeleteUser(){
        try {
            List<String> usernameList = new ArrayList<>(){{
                add("username1");
                add("username2");
                add("username3");
            }};
            removeUser("username1");
            removeUser("username2");
            removeUser("username3");
            insertUser("username1","password1","Planner");
            insertUser("username2","password2","Maintainer");
            insertUser("username3","password3","System Administrator");
            int numOfDeletedRow = instance.deleteUser(usernameList);
            assertEquals(numOfDeletedRow,usernameList.size());
            for(String username: usernameList){
                isEmptyResultSet(username);
            }
        } catch (SQLException ex) {
            fail("SQL_EXCEPTION");
        } catch (UsersException ex) {
            fail("USERS_EXCEPTION");
        }
    }
    
    
    /**
     * this method assert that deleteUser correctly return 0 if there aren't the searched username in database
     */
    @Test
    public void testDeleteUserZero(){
        try {
            List<String> usernameList = new ArrayList<>(){{
                add("username4");
                add("username5");
                add("username6");
            }};
            removeUser("username4");
            removeUser("username5");
            removeUser("username6");
            int numOfDeletedRow = instance.deleteUser(usernameList);
            assertEquals(numOfDeletedRow,0);
        } catch (SQLException ex) {
            fail("SQL_EXCEPTION");
        } catch (UsersException ex) {
            fail("USERS_EXCEPTION");
        }
    }
    
    
    /**
     * this method assert that deleteUser correctly return 0 if an empty list is passed
     */
    @Test
    public void testDeleteUserZero2(){
        try {
            List<String> usernameList = new ArrayList<>();
            int numOfDeletedRow = instance.deleteUser(usernameList);
            assertEquals(numOfDeletedRow,0);
        } catch (UsersException ex) {
            fail("USERS_EXCEPTION");
        }
    }
    
    
    /**
     * this method assert that deleteUser correctly return 0 if null is passed
     */
    @Test
    public void testDeleteUserZero3(){
        try {
            int numOfDeletedRow = instance.deleteUser(null);
            assertEquals(numOfDeletedRow,0);
        } catch (UsersException ex) {
            fail("USERS_EXCEPTION");
        }
    }
    
}
