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
import exception.MaintenanceActivityException;
import exception.MaterialException;
import exception.SiteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.database.ConnectionDB;
import persistence.maintenanceactivity.MaintenanceActivityDAOImpl;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAOImpl;
import persistence.maintenanceactivity.SiteDaoImpl;
import stub.MaintenanceActivityDAOStub;
import stub.RequiredMaterialForMaintenanceDAOStub;

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
    private MaintenanceActivityDAOImpl instance = new MaintenanceActivityDAOImpl(new SiteDaoImpl());
    private final Planner planner = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOImpl(new SiteDaoImpl()),
                new RequiredMaterialForMaintenanceDAOImpl());
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
    public void testSuccessfulModifyPlannedMaintenanceActivity() throws MaintenanceActivityException{
        try{
            Statement stm = conn.createStatement();

        
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
    public void testSuccessfulModifyEWOMaintenanceActivity() throws MaintenanceActivityException {
        try{
            Statement stm = conn.createStatement();

        
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
    public void testSuccessfulModifyExtraMaintenanceActivity() throws MaintenanceActivityException{
        try{
            Statement stm = conn.createStatement();

        
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
    public void testUnsuccessfulModifyPlannedMaintenanceActivity() throws MaintenanceActivityException{
        try{
            Statement stm = conn.createStatement();

        
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
    public void testUnsuccessfulModifyEWOMaintenanceActivity() throws MaintenanceActivityException{
        try{
            Statement stm = conn.createStatement();

        
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
    public void testUnsuccessfulModifyExtraMaintenanceActivity() throws MaintenanceActivityException{
        try{
            Statement stm = conn.createStatement();

        
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
    public void testMakeMaintenanceActivity() throws MaterialException {
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
    public void testMakeMaintenanceActivityWrongActivityID() throws MaterialException{
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
    public void testMakeMaintenanceActivityWrongDate() throws MaterialException{
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
    public void testMakeMaintenanceActivityExtra() throws MaterialException{
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
    public void testMakeMaintenanceActivityEwo() throws MaterialException{
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
    public void testMakeMaintenanceActivityInterruptible() throws MaterialException {
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
    
    //========================Test of viewMaintenanceActivity================================================   
    /**
     * This test method assert that viewMaintenanceActivity correctly return a planned activity
     */
    @Test
    public void testviewMaintenanceActivityReturnPlanned() {
        try {
            Planner planner = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(),
                new RequiredMaterialForMaintenanceDAOStub());
            MaintenanceActivity activity = planner.viewMaintenanceActivity(1);
            List<Material> materials= new ArrayList<>(){{
                    add(new Material("Materiale1"));
                    add(new Material("Materiale2"));
                    add(new Material("Materiale3"));
                }};
            assertViewMaintenanceActivity(activity, 1, "ProvaDescription1", 121, "2020-12-21", true, "Planned", null,
                    "ProvaTypology1", "ProvaBranchOffice1", "ProvaArea1", "ProvaWorkspaceNotes1",materials);
        } catch (SiteException ex) {
            fail("SiteException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    /**
     * This test method assert that viewMaintenanceActivity correctly return a Ewo activity
     */
    @Test
    public void testviewMaintenanceActivityReturnEwo() {
        try {
            Planner planner = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(),
                new RequiredMaterialForMaintenanceDAOStub());
            MaintenanceActivity activity = planner.viewMaintenanceActivity(2);
            List<Material> materials= new ArrayList<>(){{
                    add(new Material("Materiale4"));
                    add(new Material("Materiale5"));
                    add(new Material("Materiale6"));
                }};
            assertViewMaintenanceActivity(activity, 2, "ProvaDescription2", 122, "2020-12-22", false, "Unplanned", "EWO",
                    "ProvaTypology2", "ProvaBranchOffice2", "ProvaArea2", "ProvaWorkspaceNotes2",materials);
        } catch (SiteException ex) {
            fail("SiteException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    /**
     * This test method assert that viewMaintenanceActivity correctly return a Extra activity
     */
    @Test
    public void testviewMaintenanceActivityReturnExtra() {
        try {
            Planner planner = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(),
                new RequiredMaterialForMaintenanceDAOStub());
            MaintenanceActivity activity = planner.viewMaintenanceActivity(3);
            List<Material> materials= new ArrayList<>(){{
                    add(new Material("Materiale7"));
                    add(new Material("Materiale8"));
                    add(new Material("Materiale9"));
                }};
            assertViewMaintenanceActivity(activity, 3, "ProvaDescription3", 123, "2020-12-23", false, "Unplanned", "Extra",
                    "ProvaTypology3", "ProvaBranchOffice3", "ProvaArea3", "ProvaWorkspaceNotes3",materials);
        } catch (SiteException ex) {
            fail("SiteException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    
    /**
     * This test method assert that viewMaintenanceActivity correctly return null when activity is not present
     */
    @Test
    public void testviewMaintenanceActivityNull(){
        try {
            Planner planner = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(),
                new RequiredMaterialForMaintenanceDAOStub());
            MaintenanceActivity activity = planner.viewMaintenanceActivity(4);
            assertNull("testviewMaintenanceActivityNull error", activity);
        }catch (SiteException ex) {
            fail("SiteException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    
    /**
     * This test method assert that viewMaintenanceActivity correctly raises SiteException
     */
    @Test(expected = SiteException.class)
    public void testviewMaintenanceActivitySiteException() throws SiteException {
        try {
            Planner planner = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(),
                new RequiredMaterialForMaintenanceDAOStub());
            MaintenanceActivity activity = planner.viewMaintenanceActivity(5);
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    /**
     * This test method assert that viewMaintenanceActivity correctly raises MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testviewMaintenanceActivityMaintenanceActivityException() throws MaintenanceActivityException {
        try {
            Planner planner = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(),
                new RequiredMaterialForMaintenanceDAOStub());
            MaintenanceActivity activity = planner.viewMaintenanceActivity(6);
        } catch (SiteException ex) {
            fail("SiteException");
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    /**
     * This test method assert that viewMaintenanceActivity correctly return a maintenance activity with no material associated
     */
    @Test
    public void testviewMaintenanceActivityMaterialEmpty() {
        try {
            Planner planner = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(),
                new RequiredMaterialForMaintenanceDAOStub());
            MaintenanceActivity activity = planner.viewMaintenanceActivity(7);
            List<Material> materials= new ArrayList<>();
            assertViewMaintenanceActivity(activity, 7, "ProvaDescription7", 127, "2020-12-27", false, "Planned", null,
                    "ProvaTypology7", "ProvaBranchOffice7", "ProvaArea7", "ProvaWorkspaceNotes7", materials);
        } catch (SiteException ex) {
            fail("SiteException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    /**
     * This test method assert that viewMaintenanceActivity correctly raises MaterialException
     */
    @Test(expected = MaterialException.class)
    public void testviewMaintenanceActivityMaterialException() throws MaterialException {
        try {
            Planner planner = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(),
                new RequiredMaterialForMaintenanceDAOStub());
            MaintenanceActivity activity = planner.viewMaintenanceActivity(8);
        } catch (SiteException ex) {
            fail("SiteException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        }
    }
    
    private void assertViewMaintenanceActivity(MaintenanceActivity activity, 
    int activityId,String descrizione,int estimatedInterventionTime,
    String date,boolean InterruptibleActivity, String typologyOfActivity,
    String typologyOfUnplannedActivity, String typologyName,String branchOffice,
    String area, String workSpaceNotes, List<Material> listMaterial){
        Class activityClass = returnMaintenanceActivityClass(typologyOfActivity,typologyOfUnplannedActivity );
        assertEquals("activityId error",activityId, activity.getActivityId());
        assertEquals("activityDescription error",descrizione, activity.getActivityDescription());
        assertEquals("estimatedInterventionTime error",estimatedInterventionTime, activity.getEstimatedInterventionTime());
        assertEquals("dateActivity error",date,
                activity.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        assertEquals("interruptibleActivity error",InterruptibleActivity, activity.isInterruptibleActivity());
        assertTrue("typologyOfActivity error", activityClass.isInstance(activity));
        assertEquals("typologyName error", typologyName, activity.getTypology());
        assertEquals("branchOffice error", branchOffice, activity.getSite().getBranchOffice());
        assertEquals("area error", area, activity.getSite().getArea());
        assertEquals("workSpaceNotes error", workSpaceNotes, activity.getSite().getWorkSpaceNotes());
        assertMaterial(activity.getMaterials(), listMaterial);
    }
    
    private Class returnMaintenanceActivityClass(String typologyOfActivity,String typologyOfUnplannedActivity){
        Class activityClass = null;
        if (typologyOfActivity.compareTo("Planned") == 0){
            activityClass= PlannedMaintenanceActivity.class;
        }else if(typologyOfUnplannedActivity.compareTo("EWO") == 0){
            activityClass= Ewo.class;
        }else{
            activityClass= ExtraActivity.class;
        }
        return activityClass;
    }
    
    private void assertMaterial(List<Material> material, List<Material> expectedMaterials){
        if (material.size() == 0){
            assertEquals("Material null error", expectedMaterials.size(), material.size());
        }
        for (int i = 0; i < material.size(); i++){
            assertEquals("Material error", material.get(i).getName(), expectedMaterials.get(i).getName());
        }
    }
}
