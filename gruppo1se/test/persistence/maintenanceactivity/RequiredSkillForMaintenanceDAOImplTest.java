package persistence.maintenanceactivity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import business.maintenanceactivity.Skill;
import exception.SkillException;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;
import persistence.database.ConnectionDB;

/**
 *
 * @author aless
 */
public class RequiredSkillForMaintenanceDAOImplTest {
    private static Connection conn;
    private static String DELETE_ASSOCIATION_SKILL_TO_ACTIVITY = "DELETE FROM RequiredSkill where activityId = ?";
    private static String ASSOCIATE_SKILL_TO_ACTIVITY = "INSERT INTO RequiredSkill values(?,?),(?,?),(?,?)";
    private static String SELECT_SKILL_WITH_ACTIVITY_ID = "SELECT skillname"
            + " FROM RequiredSkill WHERE activityId = ? ORDER BY skillname";
    private static String INSERT_SKILL_FOR_ACTIVITY = "INSERT INTO RequiredSkill VALUES (?,?)";
    private static String DELETE_SKILL = "DELETE FROM skill";
    private static String INSERT_SKILL = "INSERT INTO skill VALUES (?)";
    private RequiredSkillForMaintenanceDAOImpl skillForMaintenanceDao;
    
    public RequiredSkillForMaintenanceDAOImplTest() {
        skillForMaintenanceDao= new RequiredSkillForMaintenanceDAOImpl();
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = ConnectionDB.getInstanceConnection().getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(RequiredSkillForMaintenanceDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequiredSkillForMaintenanceDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RequiredSkillForMaintenanceDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method asserts that retrieveSkillsByActivityIdInDatabase correctly return List of skill
     */
    @Test
    public void testRetrieveSkillsByActivityIdInDatabase() {
        List<Skill> expectedResult = new ArrayList<>() {{
                    add(new Skill("Java knowledge"));
                    add(new Skill("English knowledge"));
                    add(new Skill("SQL knowledge"));
                }};
        try {
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_SKILL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            pstm = conn.prepareStatement(ASSOCIATE_SKILL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.setString(2, expectedResult.get(0).getName());
            pstm.setInt(3, 1);
            pstm.setString(4, expectedResult.get(1).getName());
            pstm.setInt(5, 1);
            pstm.setString(6, expectedResult.get(2).getName());
            pstm.executeUpdate();
            List<Skill> result= skillForMaintenanceDao.retrieveSkillsByActivityId(1);
            Collections.sort(expectedResult);
            Collections.sort(result);
            assertEquals("retrieveSkillsByActivityIdInDatabase error",expectedResult, result);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SkillException ex) {
            fail("SkillException");
        }
    }
    
    /**
     * This method asserts that retrieveSkillsByActivityIdInDatabase correctly return empty list when <br>
     * there aren't skill associated to activity
     */
    @Test
    public void testRetrieveSkillsByActivityIdNotInDatabase() {
        try {
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_SKILL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            List<Skill> result = skillForMaintenanceDao.retrieveSkillsByActivityId(1);
            assertTrue("requiredSkill error", result.isEmpty());        
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SkillException ex) {
            fail("SkillException");
        }
    }
    
    //===============================================================================================================
    
    //===========================================================================================================================
    
    @Test
    public void testAddRequiredSkill(){
        try {
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_SKILL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            List<Skill> listSkills = new ArrayList<>();
            
            for(int i='a';i<='z';i++){
                listSkills.add(new Skill(String.valueOf(Character.toChars(i))));
            }
            
            assertTrue(skillForMaintenanceDao.addRequiredSkill(1, listSkills));
            
            pstm = conn.prepareStatement(SELECT_SKILL_WITH_ACTIVITY_ID);
            pstm.setInt(1, 1);
            ResultSet res = pstm.executeQuery();
            
            for(int i=0;res.next();i++){
                assertEquals(listSkills.get(i).getName(), res.getString("skillName"));
                if(res.isLast()){
                    assertEquals(listSkills.size(),res.getRow());
                }
            }  
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SkillException ex) {
            fail("SkillException");
        }
    }
     
    @Test
    public void testRemoveRequiredSkillInDatabase(){
        try {
            List<Skill> listSkills = new ArrayList<>();
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_SKILL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            
            pstm = conn.prepareStatement(INSERT_SKILL_FOR_ACTIVITY);
            for(int i='a';i<='z';i++){
                pstm.setInt(1, 1);
                pstm.setString(2, String.valueOf(Character.toChars(i)));
                pstm.executeUpdate();
                listSkills.add(new Skill(String.valueOf(Character.toChars(i))));
            }
            
            assertTrue(skillForMaintenanceDao.removeRequiredSkill(1, listSkills));
            
            pstm = conn.prepareStatement(SELECT_SKILL_WITH_ACTIVITY_ID);
            pstm.setInt(1, 1);
            ResultSet res = pstm.executeQuery();
            
            //returns false if there are no more rows to return
            assertFalse(res.next());
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SkillException ex) {
            fail("SkillException");
        }
    }
    
    @Test
    public void testRemoveRequiredSkillNotInDatabase(){
        try {
            List<Skill> listSkills = new ArrayList<>();
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_SKILL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            
            for(int i='a';i<='z';i++){
                listSkills.add(new Skill(String.valueOf(Character.toChars(i))));
            }
            
            assertFalse(skillForMaintenanceDao.removeRequiredSkill(1, listSkills));
            
            pstm = conn.prepareStatement(SELECT_SKILL_WITH_ACTIVITY_ID);
            pstm.setInt(1, 1);
            ResultSet res = pstm.executeQuery();
            
            //returns false if there are no more rows to return
            assertFalse(res.next());
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SkillException ex) {
            fail("SkillException");
        } 
    }
    
    @Test
    public void testRetrieveAvailableSkillToAdd(){
        try {
            List<Skill> expectedSkillsList = new ArrayList<>();
            List<Skill> actualSkillsList = new ArrayList<>();
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_SKILL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            
            Statement stm = conn.createStatement();
            stm.executeUpdate(DELETE_SKILL);
            
            pstm = conn.prepareStatement(INSERT_SKILL);
            
            for(int i='a';i<='z';i++){
                pstm.setString(1, String.valueOf(Character.toChars(i)));
                pstm.executeUpdate();
                expectedSkillsList.add(new Skill(String.valueOf(Character.toChars(i))));
            }
            
            actualSkillsList = skillForMaintenanceDao.retrieveAvailableSkillToAdd(1);
            
            Collections.sort(actualSkillsList);
            assertEquals(expectedSkillsList,actualSkillsList);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SkillException ex) {
            fail("SkillException");
        }
    }
    
    @Test
    public void testRetrieveAvaliableSkillToAddEmpty(){
        try {
            List<Skill> expectedSkillsList = new ArrayList<>();
            List<Skill> actualSkillsList = new ArrayList<>();
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_SKILL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            
            Statement stm = conn.createStatement();
            stm.executeUpdate(DELETE_SKILL);
            
            actualSkillsList = skillForMaintenanceDao.retrieveAvailableSkillToAdd(1);
            
            assertEquals(expectedSkillsList,actualSkillsList);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SkillException ex) {
            fail("SkillException");
        }
    }
    
}

