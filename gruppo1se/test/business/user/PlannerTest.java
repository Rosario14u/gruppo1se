/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.Ewo;
import business.maintenanceactivity.ExtraActivity;
import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.MaintenanceProcedure;
import business.maintenanceactivity.PlannedMaintenanceActivity;
import business.maintenanceactivity.Site;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.maintenanceactivity.MaintenanceActivityDAO;
import persistence.maintenanceactivity.MaintenanceActivityDAOTest;

/**
 *
 * @author gorra
 */
public class PlannerTest {
    private static final String URL = "jdbc:postgresql://localhost/Gruppo1_SE";
    private static final  String USER = "gruppo1";
    private static final  String PWD = "123456";
    private static Connection conn;
    
    public PlannerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = DriverManager.getConnection(URL, USER, PWD);
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceActivityDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(MaintenanceActivityDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test
    public void testSuccessfulModifyPlannedMaintenanceActivity(){
        try{
            Statement stm = conn.createStatement();
            MaintenanceActivityDAO instance = new MaintenanceActivityDAO();
        
            deleteDafaultMaintenanceActivity(stm);
            insertDefaultMaintenanceActivity("Planned", "null", stm);
            MaintenanceActivity newActivity = new PlannedMaintenanceActivity(1, new Site("ProvaBranchOfficeMod", "ProvaAreaMod"), "typologyNameMod", 
                                    "ProvaDescrizioneMod", 400, LocalDate.parse("2050-12-12"), null, null, false);

            boolean result = instance.modifyMaintenaceActivity(newActivity, conn);
            assertEquals(true, result);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(MaintenanceActivityDAOTest.class.getName()).log(Level.SEVERE, null, ex);            
        }   
    }
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test
    public void testSuccessfulModifyEWOMaintenanceActivity(){
        try{
            Statement stm = conn.createStatement();
            MaintenanceActivityDAO instance = new MaintenanceActivityDAO();
        
            deleteDafaultMaintenanceActivity(stm);
            insertDefaultMaintenanceActivity("Planned", "null", stm);
            MaintenanceActivity newActivity = new Ewo(1, new Site("ProvaBranchOfficeMod", "ProvaAreaMod"), "typologyNameMod", 
                                    "ProvaDescrizioneMod", 400, LocalDate.parse("2050-12-12"), null, null, false);
            
            boolean result = instance.modifyMaintenaceActivity(newActivity, conn);
            assertEquals(true, result);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(MaintenanceActivityDAOTest.class.getName()).log(Level.SEVERE, null, ex);            
        }   
    }
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test
    public void testSuccessfulModifyExtraMaintenanceActivity(){
        try{
            Statement stm = conn.createStatement();
            MaintenanceActivityDAO instance = new MaintenanceActivityDAO();
        
            deleteDafaultMaintenanceActivity(stm);
            insertDefaultMaintenanceActivity("Planned", "null", stm);
            MaintenanceActivity newActivity = new ExtraActivity(1, new Site("ProvaBranchOfficeMod", "ProvaAreaMod"), "typologyNameMod", 
                                    "ProvaDescrizioneMod", 400, LocalDate.parse("2050-12-12"), null, null, false);
            
            boolean result = instance.modifyMaintenaceActivity(newActivity, conn);
            assertEquals(true, result);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(MaintenanceActivityDAOTest.class.getName()).log(Level.SEVERE, null, ex);            
        }   
    }
    
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test
    public void testUnsuccessfulModifyPlannedMaintenanceActivity(){
        try{
            Statement stm = conn.createStatement();
            MaintenanceActivityDAO instance = new MaintenanceActivityDAO();
        
            deleteDafaultMaintenanceActivity(stm);
            MaintenanceActivity newActivity = new PlannedMaintenanceActivity(1, new Site("ProvaBranchOfficeMod", "ProvaAreaMod"), "typologyNameMod", 
                                    "ProvaDescrizioneMod", 400, LocalDate.parse("2050-12-12"), null, null, false);
            
            boolean result = instance.modifyMaintenaceActivity(newActivity, conn);
            assertEquals(false, result);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(MaintenanceActivityDAOTest.class.getName()).log(Level.SEVERE, null, ex);            
        }   
    }
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test
    public void testUnsuccessfulModifyEWOMaintenanceActivity(){
        try{
            Statement stm = conn.createStatement();
            MaintenanceActivityDAO instance = new MaintenanceActivityDAO();
        
            deleteDafaultMaintenanceActivity(stm);
            MaintenanceActivity newActivity = new Ewo(1, new Site("ProvaBranchOfficeMod", "ProvaAreaMod"), "typologyNameMod", 
                                    "ProvaDescrizioneMod", 400, LocalDate.parse("2050-12-12"), null, null, false);
            
            boolean result = instance.modifyMaintenaceActivity(newActivity, conn);
            assertEquals(false, result);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(MaintenanceActivityDAOTest.class.getName()).log(Level.SEVERE, null, ex);            
        }   
    }
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test
    public void testUnsuccessfulModifyExtraMaintenanceActivity(){
        try{
            Statement stm = conn.createStatement();
            MaintenanceActivityDAO instance = new MaintenanceActivityDAO();
        
            deleteDafaultMaintenanceActivity(stm);
            MaintenanceActivity newActivity = new ExtraActivity(1, new Site("ProvaBranchOfficeMod", "ProvaAreaMod"), "typologyNameMod", 
                                    "ProvaDescrizioneMod", 400, LocalDate.parse("2050-12-12"), null, null, false);
            
            boolean result = instance.modifyMaintenaceActivity(newActivity, conn);
            assertEquals(false, result);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(MaintenanceActivityDAOTest.class.getName()).log(Level.SEVERE, null, ex);            
        }   
    }
    
    
    /*=================== PRIVATE METHODS OF MODIFY ==============================================================================*/
    
    private void insertDefaultMaintenanceActivity(String typologyOfActivity, String typologyOfUnplannedActivity, Statement stm) throws SQLException {
        String query = "INSERT INTO MaintenanceActivity (activityId, activityDescription, "
                + "estimatedInterventionTime, dateActivity, interruptibleActivity, typologyOfActivity,"
                + " typologyOfUnplannedActivity, typologyName, branchOffice, area) "
                + "VALUES (1, 'ProvaDescrizione', 200, '2030-12-1', True, '"+ typologyOfActivity +"',"
                + " "+ typologyOfUnplannedActivity +", 'typologyName', 'ProvaBranchOffice', 'ProvaArea')";
        stm.executeUpdate(query);
    }
    
    private void deleteDafaultMaintenanceActivity(Statement stm) throws SQLException {
        String query = "DELETE FROM MaintenanceActivity WHERE activityId=1";
        stm.executeUpdate(query);
    }
    
}
