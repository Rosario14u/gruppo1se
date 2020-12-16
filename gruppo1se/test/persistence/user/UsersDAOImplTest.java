/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.user;

import dto.MaintainerDTO;
import dto.PlannerDTO;
import dto.SystemAdministratorDTO;
import dto.UserDTO;
import exception.NotValidParameterException;
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

/**
 *
 * @author aless
 */
public class UsersDAOImplTest{
    private static Connection conn;
    private static final String DELETE_USER = "DELETE FROM users WHERE username=?";
    private static final String INSERT_USER = "INSERT INTO users (username, password, role) VALUES (?,?,?)";
    private static final String SELECT_USER = "SELECT * FROM users WHERE username = ?";
    private static final String DELETE_ALL_USERS = "DELETE FROM USERS";
    private final UsersDAOImpl instance = new UsersDAOImpl();
    
    
    
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
    
    private void deleteAllUsersDefault(Statement stmt) throws SQLException{
        String delete = "DELETE FROM USERS";
        stmt.executeUpdate(delete);
    }
    private ResultSet selectUserDefault(Statement stmt, String username) throws SQLException{
        String select = "SELECT * FROM USERS WHERE username = '" + username + "'";
        return stmt.executeQuery(select);
    }
    
    private void verify(ResultSet set, UserDTO user) throws SQLException{
        assertNotNull(set);
        while(set.next()){
            assertEquals(user.getUsername(), set.getString("username"));
            assertEquals(user.getPassword(), set.getString("password"));
            if(PlannerDTO.class.isInstance(user)){
                assertEquals("Planner", set.getString("role"));
            }
            else if(MaintainerDTO.class.isInstance(user)){
                assertEquals("Maintainer", set.getString("role"));
            }
            else{
                assertEquals("System Administrator", set.getString("role"));            
            }
        }
    }
    //=====================================================Test of addUser============================================================================
    /*Test methods developed by Alessio Citro*/
    
    /**
     * Test of addUser method, of class UsersDAO.
     * @throws exception.UsersException
     * @throws java.sql.SQLException
     */
    @Test
    public void testAddUserPlanner() throws UsersException, SQLException{
        System.out.println("addUserPlanner");
        UserDTO user = new PlannerDTO("ProvaUsername","ProvaPassword");
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
        UserDTO user = new MaintainerDTO("ProvaUsername","ProvaPassword");
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
        UserDTO user = new SystemAdministratorDTO("ProvaUsername","ProvaPassword");
        Statement stmt = conn.createStatement();
        deleteUserDefault(stmt, user.getUsername());
        instance.addUser(user);
        verify(selectUserDefault(stmt, "ProvaUsername"), user);
        conn.rollback();
    }
    
//==================================Test of method readUser==============================================================================
    /*Test method developed by Vincenza Coppola*/
    
    private void insertUserDefault(Statement stm, UserDTO user) throws SQLException{
        String insert = "INSERT INTO Users VALUES ('"+user.getUsername()+"','"+user.getPassword()+"'";
        if (user.getClass().getSimpleName().equals("PlannerDTO"))
            insert+=",'Planner');";
        else if (user.getClass().getSimpleName().equals("SystemAdministratorDTO"))
            insert+=",'System Administrator');";
        else 
            insert+=",'Maintainer');";
        stm.executeUpdate(insert);
    }    
    
    /**
     * Test of readUser method, of class UsersDAOImpl.
     * @throws exception.UsersException
     * @throws java.sql.SQLException
     */
    @Test
    public void testReadUsers() throws UsersException, SQLException{
        try {
            System.out.println("readUsers");
            Statement stm = conn.createStatement();
            deleteAllUsersDefault(stm);
            List<UserDTO> expectedList = new ArrayList<>();
            expectedList.add(new PlannerDTO("Planner1","PwdPlanner1"));
            expectedList.add(new SystemAdministratorDTO("SystemAdministrator1","PwdSystemAdministrator1"));
            expectedList.add(new MaintainerDTO("Maintainer1","PwdMaintainer1"));
            List<UserDTO> plannerList = new ArrayList<>();
            insertUserDefault(stm,new PlannerDTO("Planner1","PwdPlanner1"));
            insertUserDefault(stm,new SystemAdministratorDTO("SystemAdministrator1","PwdSystemAdministrator1"));
            insertUserDefault(stm,new MaintainerDTO("Maintainer1","PwdMaintainer1"));
            plannerList = instance.readUsers();
            System.out.println(" --> " + expectedList.toString());
            System.out.println(plannerList);
            assertEquals(true,plannerList.equals(expectedList));
            conn.rollback();
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
   
        
    
    //======================================= TEST MODIFY ====================================================================================
    // Test of updateUser method developed by Antonio Gorrasi
    
    
    /**
     * This test asserts that the updateUser method 
     * correctly updates a user from planner to maintainer
     */
    @Test
    public void testUpdateUserPlannerToMaintainer() {        
        try {
            String username = "username";
            removeUser(username);
            insertUser(username, "password", "Planner");
            UserDTO modifiedUser = createUser("newUsername", "newPassword", "Maintainer");
            assertTrue(instance.updateUser(username, modifiedUser));
            verifyUser(selectUser("newUsername"), modifiedUser);
        } catch (SQLException | UsersException | NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * This test asserts that the updateUser method 
     * correctly updates a user from planner to System 
     * Administrator
     */
    @Test
    public void testUpdateUserPlannerToSystemAdministrator() {        
        try {
            String username = "username";
            removeUser(username);
            insertUser(username, "password", "Planner");
            UserDTO modifiedUser = createUser("newUsername", "newPassword", "System Administrator");
            assertTrue(instance.updateUser(username, modifiedUser));
            verifyUser(selectUser("newUsername"), modifiedUser); 
        } catch (SQLException | UsersException | NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * This test asserts that the updateUser method 
     * correctly updates a user from maintainer to planner
     */
    @Test
    public void testUpdateUserMaintainerToPlanner() {        
        try {
            String username = "username";
            removeUser(username);
            insertUser(username, "password", "Maintainer");
            UserDTO modifiedUser = createUser("newUsername", "newPassword", "Planner");
            assertTrue(instance.updateUser(username, modifiedUser));
            verifyUser(selectUser("newUsername"), modifiedUser);   
        } catch (SQLException | UsersException | NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * This test asserts that the updateUser method 
     * correctly updates a user from maintainer to
     * System Administrator
     */
    @Test
    public void testUpdateUserMaintainerToSystemAdministrator() {        
        try {
            String username = "username";
            removeUser(username);
            insertUser(username, "password", "Maintainer");
            UserDTO modifiedUser = createUser("newUsername", "newPassword", "System Administrator");
            assertTrue(instance.updateUser(username, modifiedUser));
            verifyUser(selectUser("newUsername"), modifiedUser);
        } catch (SQLException | UsersException | NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * This test asserts that the updateUser method 
     * correctly updates a user from System Administrator
     * to planner
     */
    @Test
    public void testUpdateUserSystemAdministratorToPlanner() {        
        try {
            String username = "username";
            removeUser(username);
            insertUser(username, "password", "System Administrator");
            UserDTO modifiedUser = createUser("newUsername", "newPassword", "Planner");
            assertTrue(instance.updateUser(username, modifiedUser));
            verifyUser(selectUser("newUsername"), modifiedUser);
        } catch (SQLException | UsersException | NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * This test asserts that the updateUser method 
     * correctly updates a user from System Administrator 
     * to Maintainer
     */
    @Test
    public void testUpdateUserSystemAdministratorToMaintainer() {        
        try {
            String username = "username";
            removeUser(username);
            insertUser(username, "password", "System Administrator");
            UserDTO modifiedUser = createUser("newUsername", "newPassword", "Maintainer");
            assertTrue(instance.updateUser(username, modifiedUser));
            verifyUser(selectUser("newUsername"), modifiedUser);
        } catch (SQLException | UsersException | NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * this test asserts that the updateUser method 
     * correctly returns false when attempting to 
     * modify a user who is not there
     */
    @Test
    public void testModifyActivityNotPresent(){
        try {
            String username = "username";
            removeUser(username);
            UserDTO modifiedUser = createUser("newUsername", "newPassword", "Maintainer");
            assertFalse(instance.updateUser(username, modifiedUser));            
        } catch (SQLException | UsersException | NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    //====================================== PRIVATE METHODS =============================================
    //Developed by Antonio Gorrasi
    
    /**
     * This method inserts a user into the database
     * @param username username of user
     * @param password password of user
     * @param role role of user
     * @throws SQLException 
     */
    private void insertUser(String username, String password, String role) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_USER);
        pstm.setString(1, username);
        pstm.setString(2, password);
        pstm.setString(3, role);
        pstm.executeUpdate();
    }

    
    /**
     * This method remove a user from the database
     * @param username username of the user to remove
     * @throws SQLException 
     */
    private void removeUser(String username) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(DELETE_USER);
        pstm.setString(1, username);
        pstm.executeUpdate();
    }
    
    
    /**
     * This method select a user into the database
     * @param username username of the user
     * @return {@code ResultSet} containing user information
     * @throws SQLException 
     */
    private ResultSet selectUser(String username) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(SELECT_USER);
        pstm.setString(1, username);
        return pstm.executeQuery();
    }
    
    
    /**
     * This method creates a user based on the given role parameter 
     * @param username username of the user
     * @param password password of the user
     * @param role role of the user
     * @return {@code UserDTO}
     * @throws NotValidParameterException 
     */
    private UserDTO createUser(String username, String password, String role) throws NotValidParameterException{
        if(role.equals("Planner")){
            return new PlannerDTO(username, password);
        }else if(role.equals("Maintainer")){
            return new MaintainerDTO(username, password);
        }else if(role.equals("System Administrator")){
            return new SystemAdministratorDTO(username, password);
        }else{
            throw new NotValidParameterException("Invalid role");
        }            
    }
    
    
    /**
     * This method checks whether the information contained
     * in the ResultSet is the same as desired
     * @param res {@code ResulSet} containing the information
     * @param newUser UserDTO containing the desired information
     * @throws SQLException 
     */
    private void verifyUser(ResultSet res, UserDTO newUser) throws SQLException {
        boolean isEmpty = true;
        while(res.next()){
            isEmpty = false;
            assertEquals(newUser.getUsername(), res.getString("username"));
            assertEquals(newUser.getPassword(), res.getString("password"));
            if(newUser instanceof PlannerDTO){
                assertEquals("Planner", res.getString("role"));
            }else if(newUser instanceof MaintainerDTO){
                assertEquals("Maintainer", res.getString("role"));
            }else
                assertEquals("System Administrator", res.getString("role"));
        }
        assertFalse(isEmpty);
    }
    
    
    /**
     * This method verifies that the ResultSet is not empty
     * @param username
     * @throws SQLException 
     */
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
    
    
    
    /*==================================================test deleteUsers==================================================================================*/
    
    /*Test of deleteUsers method developed by Rosario Gaeta*/
    
    /**
     * This method assert that deleteUsers correctly deletes the rows in database.
     */
    @Test
    public void testDeleteUsers(){
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
            int numOfDeletedRow = instance.deleteUsers(usernameList);
            assertEquals(numOfDeletedRow,usernameList.size());
            for(String username: usernameList){
                isEmptyResultSet(username);
            }
        }catch(SQLException | UsersException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    
    /**
     * This method assert that deleteUsers correctly return 0 if the searched username is not present in the database.
     */
    @Test
    public void testDeleteUsersZero(){
        try {
            List<String> usernameList = new ArrayList<>(){{
                add("username4");
                add("username5");
                add("username6");
            }};
            removeUser("username4");
            removeUser("username5");
            removeUser("username6");
            int numOfDeletedRow = instance.deleteUsers(usernameList);
            assertEquals(numOfDeletedRow,0);
        }catch(SQLException | UsersException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    
    /**
     * This method asserts that deleteUsers correctly return 0 if an empty list is passed.
     */
    @Test
    public void testDeleteUsersZero2(){
        try {
            List<String> usernameList = new ArrayList<>();
            int numOfDeletedRow = instance.deleteUsers(usernameList);
            assertEquals(numOfDeletedRow,0);
        }catch(UsersException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    
    /**
     * This method assert that deleteUsers correctly return 0 if null is passed.
     */
    @Test
    public void testDeleteUsersZero3(){
        try {
            int numOfDeletedRow = instance.deleteUsers(null);
            assertEquals(numOfDeletedRow,0);
        }catch(UsersException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
 //======================================================================================================================
    
    
    
    @Test
    public void testCorrectlyReadMaintainers(){
        try {
            deleteUsers();
            insertUser("username1", "pwd1", "Maintainer");
            insertUser("username2", "pwd2", "Maintainer");
            insertUser("username3", "pwd3", "Maintainer");
            insertUser("username4", "pwd4", "Planner");
            insertUser("username5", "pwd5", "System Administrator");
            
            List<MaintainerDTO> maintainers = instance.readMaintainers();
            
            for(int i=1;i<=maintainers.size();i++){
                assertTrue(maintainers.get(i-1) instanceof MaintainerDTO);
                assertEquals("username"+String.valueOf(i), maintainers.get(i-1).getUsername());
                assertEquals("pwd"+String.valueOf(i), maintainers.get(i-1).getPassword());
            }            
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (UsersException ex) {
            fail("UsersException");
        }
    }    
    
    
    @Test
    public void testReadMaintainersEmptyList(){
        try {
            deleteUsers();
            insertUser("username4", "pwd4", "Planner");
            insertUser("username5", "pwd5", "System Administrator");
            
            List<MaintainerDTO> maintainers = instance.readMaintainers();
            
            assertTrue(maintainers.size()==0);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (UsersException ex) {
            fail("UsersException");
        }
    }    
    
    
    private void deleteUsers() throws SQLException{
        Statement stm = conn.createStatement();
        stm.executeUpdate(DELETE_ALL_USERS);
    }
}
