/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.user;

import business.maintenanceactivity.Skill;
import exception.NotValidParameterException;
import exception.SkillException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.Connection;
import persistence.database.ConnectionDB;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;

/**
 *
 * @author gorra
 */
public class MaintainerSkillDAOImplTest {
    private static Connection conn;
    private MaintainerSkillDAOImpl maintainerSkill;
    private static String DELETE_SKILL = "DELETE FROM maintainerskill WHERE username = ?";
    private static String INSERT_SKILL = "INSERT INTO maintainerskill VALUES (?,?)";
    private static String SELECT_SKILL = "SELECT * FROM maintainerskill WHERE usernname = ?";
    
    
    
    public MaintainerSkillDAOImplTest() {
        maintainerSkill = new MaintainerSkillDAOImpl();
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
    

    /**
     * Test of getMaintainerSkills method, of class MaintainerSkillDAOImpl.
     * Assert that getMaintainerSkills correctly return a list of Skills 
     * associated to a Maintainer 
     */
    //Developed by Antonio Gorrasi
    @Test
    public void testSuccessfulGetMaintainerSkills() {
        try {
            List<Skill> expectedSkills = new ArrayList<>();
            deleteSkillOfMaintainer("maintainer");
            for(int i='a';i<='z';i++){
                insertSkillToMaintainer("maintainer", String.valueOf(Character.toChars(i)));
                expectedSkills.add(new Skill(String.valueOf(Character.toChars(i))));
            }
            List<Skill> actualSkills = maintainerSkill.getMaintainerSkills("maintainer");
            
            assertEquals(expectedSkills, actualSkills);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SkillException ex) {
            fail("SkillException");
        }
    }
    
    
    
    /**
     * Test of getMaintainerSkills method, of class MaintainerSkillDAOImpl.
     * Assert that getMaintainerSkills correctly return a list of Skills 
     * associated to a Maintainer 
     */
    //Developed by Antonio Gorrasi
    @Test
    public void testGetMaintainerSkillsEmptyList() {
        try {
            deleteSkillOfMaintainer("maintainer");
            List<Skill> actualSkills = maintainerSkill.getMaintainerSkills("maintainer");
            
            assertTrue(actualSkills.isEmpty());
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SkillException ex) {
            fail("SkillException");
        }
    }
    
    
//================================================================================================================================
    
    
    /**
     * Delete all skills previously associated with the maintainer
     * @param username 
     * @throws SQLException 
     */
    //Developed by Antonio Gorrasi
    private void deleteSkillOfMaintainer(String username) throws SQLException{
        PreparedStatement stm = conn.prepareStatement(DELETE_SKILL);
        stm.setString(1, username);
        stm.executeUpdate();
    }
    
    
    /**
     * Associate a skill with a maintainer
     * @param username
     * @param skill
     * @throws SQLException 
     */
    //Developed by Antonio Gorrasi
    private void insertSkillToMaintainer(String username, String skill) throws SQLException{
        PreparedStatement stm = conn.prepareStatement(INSERT_SKILL);
        stm.setString(1, username);
        stm.setString(2, skill);
        stm.executeUpdate();
    }    
    
////    private List<Skill> selectSkillOfMaintainer(String username) throws SQLException, NotValidParameterException{
////        List<Skill> listOfSkills = new ArrayList<>();
////        PreparedStatement stm = conn.prepareStatement(SELECT_SKILL);
////        stm.setString(1, username);
////        ResultSet set = stm.executeQuery();
////        
////        while(set.next()){
////            listOfSkills.add(new Skill(set.getString("skillname")));
////        }
////        return listOfSkills;
////    }
}
