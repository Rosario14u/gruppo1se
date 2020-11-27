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
import business.maintenanceactivity.Material;
import business.maintenanceactivity.PlannedMaintenanceActivity;
import business.maintenanceactivity.Site;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.database.ConnectionDB;
import persistence.maintenanceactivity.MaintenanceActivityDAO;

/**
 *
 * @author gorra
 */
public class PlannerTest {
    private static Connection conn;
    private int activityId = 1;
    private final String branchOffice = "ProvaBranchOffice";
    private final String area = "ProvaArea";
    private final String workspaceNotes = "ProvaWorkspaceNotes";
    private final String typology = "ProvaTypology";
    private final String activityDescription = "ProvaActivityDescription";
    private final int estimatedInterventionTime = 30;
    private String date = "2050-11-25";
    private final String maintenanceProcedure = "ProvaPDF";
    private final ArrayList<Material> materials = new ArrayList<>();
    private boolean interruptibleActivity = false;
    private boolean plannedActivity = true;
    private boolean extraActivity = false;
    private boolean ewo = false;
    private MaintenanceActivityDAO instance = new MaintenanceActivityDAO();
    private final Planner planner = new Planner("ProvaUser","ProvaPassword");
    private final Site site = new Site(branchOffice,area,workspaceNotes);
    private final MaintenanceProcedure mProc = new MaintenanceProcedure(maintenanceProcedure);
    
    public PlannerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = ConnectionDB.getInstanceConnection().getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(PlannerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
          conn.setAutoCommit(true);
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(PlannerTest.class.getName()).log(Level.SEVERE, null, ex);
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

            boolean result = instance.modifyMaintenaceActivity(newActivity);
            assertEquals(true, result);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(PlannerTest.class.getName()).log(Level.SEVERE, null, ex);            
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
            
            boolean result = instance.modifyMaintenaceActivity(newActivity);
            assertEquals(true, result);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(PlannerTest.class.getName()).log(Level.SEVERE, null, ex);            
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
            
            boolean result = instance.modifyMaintenaceActivity(newActivity);
            assertEquals(true, result);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(PlannerTest.class.getName()).log(Level.SEVERE, null, ex);            
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
            
            boolean result = instance.modifyMaintenaceActivity(newActivity);
            assertEquals(false, result);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(PlannerTest.class.getName()).log(Level.SEVERE, null, ex);            
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
            
            boolean result = instance.modifyMaintenaceActivity(newActivity);
            assertEquals(false, result);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(PlannerTest.class.getName()).log(Level.SEVERE, null, ex);            
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
            
            boolean result = instance.modifyMaintenaceActivity(newActivity);
            assertEquals(false, result);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(PlannerTest.class.getName()).log(Level.SEVERE, null, ex);            
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
    
    
    /**
     * Test of makeMaintenanceActivity method, of class Planner.
     */
    @Test
    public void testMakeMaintenanceActivity() {
        try {
            System.out.println("makeMaintenanceActivity");
            planner.removeMaintenanceActivity(activityId);
            boolean result = planner.makeMaintenanceActivity(activityId, branchOffice, area, workspaceNotes, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity, plannedActivity, extraActivity, ewo);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }

    @Test
    public void testMakeMaintenanceActivityWrongActivityID(){
        try {
            System.out.println("makeMaintenanceActivityWrongActivityID");
            activityId = 0;
            planner.removeMaintenanceActivity(activityId);
            boolean result = planner.makeMaintenanceActivity(activityId, branchOffice, area, workspaceNotes, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity, plannedActivity, extraActivity, ewo);
            assertEquals(false, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    @Test
    public void testMakeMaintenanceActivityWrongDate(){
        try {
            System.out.println("makeMaintenanceActivityWrongDate");
            date = "2010-11-25";
            planner.removeMaintenanceActivity(activityId);
            boolean result = planner.makeMaintenanceActivity(activityId, branchOffice, area, workspaceNotes, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity, plannedActivity, extraActivity, ewo);
            assertEquals(false, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test
    public void testMakeMaintenanceActivityExtra(){
        try {
            System.out.println("makeMaintenanceActivityExtra");
            plannedActivity = false;
            extraActivity = true;
            planner.removeMaintenanceActivity(activityId);
            boolean result = planner.makeMaintenanceActivity(activityId, branchOffice, area, workspaceNotes, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity, plannedActivity, extraActivity, ewo);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test
    public void testMakeMaintenanceActivityEwo(){
        try {
            System.out.println("makeMaintenanceActivityEwo");
            plannedActivity = false;
            ewo = true;
            planner.removeMaintenanceActivity(activityId);
            boolean result = planner.makeMaintenanceActivity(activityId, branchOffice, area, workspaceNotes, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity, plannedActivity, extraActivity, ewo);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test
    public void testMakeMaintenanceActivityInterruptible() {
        try {
            System.out.println("makeMaintenanceActivity");
            interruptibleActivity = true;
            planner.removeMaintenanceActivity(activityId);
            boolean result = planner.makeMaintenanceActivity(activityId, branchOffice, area, workspaceNotes, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity, plannedActivity, extraActivity, ewo);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
 //===========================================================================================================================================   
  /**
 * Test of removeMaintenanceActivity method, of class Planner.
 */
    @Test
    public void testRemoveMaintenanceActivity() {
        try {
            System.out.println("removeMaintenanceActivity");
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(activityId,site,typology,activityDescription,estimatedInterventionTime,LocalDate.of(2050,11,25),mProc,materials,true);
            instance.deleteMaintenanceActivity(activityId);
            instance.addMaintenanceActivity(activity);
            boolean result = planner.removeMaintenanceActivity(activityId);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on : connection rollback!");
        }
    }
    @Test
    public void testRemoveMaintenanceActivityEwo() {
        try {
            System.out.println("removeMaintenanceActivity");
            Ewo activity = new Ewo(activityId,site,typology,activityDescription,estimatedInterventionTime,LocalDate.of(2050,11,25),mProc,materials,true);
            instance.deleteMaintenanceActivity(activityId);
            instance.addMaintenanceActivity(activity);
            boolean result = planner.removeMaintenanceActivity(activityId);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on : connection rollback!");
        }
    }
    
    @Test
    public void testRemoveMaintenanceActivityExtraActivity() {
        try {
            System.out.println("removeMaintenanceActivity");
            ExtraActivity activity = new ExtraActivity(activityId,site,typology,activityDescription,estimatedInterventionTime,LocalDate.of(2050,11,25),mProc,materials,true);
            instance.deleteMaintenanceActivity(activityId);
            instance.addMaintenanceActivity(activity);
            boolean result = planner.removeMaintenanceActivity(activityId);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on : connection rollback!");
        }
    }
    
    @Test
    public void testRemoveMaintenanceActivityWithWrongId() {
        try {
            System.out.println("removeMaintenanceActivity");
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(activityId,site,typology,activityDescription,estimatedInterventionTime,LocalDate.of(2050,11,25),mProc,materials,true);
            instance.deleteMaintenanceActivity(activityId);
            instance.addMaintenanceActivity(activity);
            boolean result = planner.removeMaintenanceActivity(124);
            assertEquals(false, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on : connection rollback!");
        }
    }
}
