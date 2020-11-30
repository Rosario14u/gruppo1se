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
import java.util.Collections;
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
    public void testSuccessfulModifyPlannedMaintenanceActivity(){
        try{
            Planner planner1 = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(), null);
            assertTrue(planner1.modifyMaintenanceActivity(1, "branchOffice1", "area1", "typology1", "description1", 1, LocalDate.now().toString(), true, "PLANNED", null));
        }catch(MaintenanceActivityException ex){
            fail("MaintenanceActivityException");     
        }   
    }
    
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test
    public void testUnsuccessfulModifyPlannedMaintenanceActivity() {
        try{
            Planner planner1 = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(), null);
            assertFalse(planner1.modifyMaintenanceActivity(2, "branchOffice2", "area2", "typology2", "description2", 2, LocalDate.now().toString(), true, "PLANNED", null));
        }catch(MaintenanceActivityException ex){
            fail("MaintenanceActivityException");     
        }
    }
    
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testExceptionModifyPlannedMaintenanceActivity() throws MaintenanceActivityException{
        Planner planner1 = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(), null);
        planner1.modifyMaintenanceActivity(3, "branchOffice3", "area3", "typology3", "description3", 3, LocalDate.now().toString(), true, "PLANNED", null);  
    }
  
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test
    public void testSuccessfulModifyEwoMaintenanceActivity() {
        try{
            Planner planner1 = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(), null);
            assertTrue(planner1.modifyMaintenanceActivity(4, "branchOffice4", "area4", "typology4", "description4", 4, LocalDate.now().toString(), true, "UNPLANNED", "EWO"));
        }catch(MaintenanceActivityException ex){
            fail("MaintenanceActivityException");     
        }   
    }
    
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test
    public void testUnsuccessfulModifyEwoMaintenanceActivity() {
        try{
            Planner planner1 = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(), null);
            assertFalse(planner1.modifyMaintenanceActivity(5, "branchOffice5", "area5", "typology5", "description5", 5, LocalDate.now().toString(), true, "UNPLANNED", "EWO"));
        }catch(MaintenanceActivityException ex){
            fail("MaintenanceActivityException");     
        }
    }
    
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testExceptionModifyEwoMaintenanceActivity() throws MaintenanceActivityException{
        Planner planner1 = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(), null);
        planner1.modifyMaintenanceActivity(6, "branchOffice6", "area6", "typology6", "description6", 6, LocalDate.now().toString(), true, "UNPLANNED", "EWO");
    }
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test
    public void testSuccessfulModifyExtraMaintenanceActivity() {
        try{
            Planner planner1 = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(), null);
            assertTrue(planner1.modifyMaintenanceActivity(7, "branchOffice7", "area7", "typology7", "description7", 7, LocalDate.now().toString(), true, "UNPLANNED", "EXTRA"));
        }catch(MaintenanceActivityException ex){
            fail("MaintenanceActivityException");     
        }  
    }
    
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test
    public void testUnsuccessfulModifyExtraMaintenanceActivity() {
        try{
            Planner planner1 = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(), null);
            assertFalse(planner1.modifyMaintenanceActivity(8, "branchOffice8", "area8", "typology8", "description8", 8, LocalDate.now().toString(), true, "UNPLANNED", "EXTRA"));
        }catch(MaintenanceActivityException ex){
            fail("MaintenanceActivityException");     
        }  
    }
    
    
    /**
     * Test of modifyMaintenanceActivity method, of class Planner.
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testExceptionModifyExtraMaintenanceActivity() throws MaintenanceActivityException{
        Planner planner1 = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(), null);
        planner1.modifyMaintenanceActivity(9, "branchOffice9", "area9", "typology9", "description9", 9, LocalDate.now().toString(), true, "UNPLANNED", "EXTRA"); 
    }
    
    /*============================================================================================================================*/
    @Test
    public void testSuccessfulAddRequiredMaterial(){
        try{
            Planner planner1 = new Planner("ProvaUser","ProvaPassword", null, new RequiredMaterialForMaintenanceDAOStub());
            List<Material> listMaterialToAdd = new ArrayList<>();                       
            assertTrue(planner1.addRequiredMaterial(1, listMaterialToAdd));
        }catch (MaterialException ex){
            fail("MaterialException");
        }
    }
    
    @Test(expected = MaterialException.class)
    public void testUnsuccessfulAddRequiredMaterial() throws MaterialException{
        Planner planner1 = new Planner("ProvaUser","ProvaPassword", null, new RequiredMaterialForMaintenanceDAOStub());
        List<Material> listMaterialToAdd = new ArrayList<>();
        planner1.addRequiredMaterial(2, listMaterialToAdd);
    }
    
    @Test
    public void testSuccessfulRemoveRequiredMaterial(){
        try {
            Planner planner1 = new Planner("ProvaUser","ProvaPassword", null, new RequiredMaterialForMaintenanceDAOStub());
            List<Material> listMaterialToAdd = new ArrayList<>();
            assertTrue(planner1.removeRequiredMaterial(3, listMaterialToAdd));
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    
    @Test
    public void testRemoveRequiredMaterialNotComplete(){
        try {
            Planner planner1 = new Planner("ProvaUser","ProvaPassword", null, new RequiredMaterialForMaintenanceDAOStub());
            List<Material> listMaterialToAdd = new ArrayList<>();
            assertFalse(planner1.removeRequiredMaterial(4, listMaterialToAdd));
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    
    
    @Test(expected = MaterialException.class)
    public void testUnsuccessfulRemoveRequiredMaterial() throws MaterialException{
        Planner planner1 = new Planner("ProvaUser","ProvaPassword", null, new RequiredMaterialForMaintenanceDAOStub());
        List<Material> listMaterialToAdd = new ArrayList<>();
        planner1.removeRequiredMaterial(5, listMaterialToAdd);
    }
    
    @Test
    public void testRetrieveAvaliableMaterialToAdd(){
        try {
            Planner planner1 = new Planner("ProvaUser","ProvaPassword", null, new RequiredMaterialForMaintenanceDAOStub());
            List<Material> expectedMaterialList = new ArrayList<>(){{
                add(new Material("Material1"));
                add(new Material("Material2"));
            }};
            List<Material> actualMaterialList = planner1.retrieveAvaliableMaterialToAdd(6);
            assertEquals(expectedMaterialList, actualMaterialList);
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
        
    @Test
    public void testRetrieveAvaliableMaterialToAddEmpty(){
        try {
            Planner planner1 = new Planner("ProvaUser","ProvaPassword", null, new RequiredMaterialForMaintenanceDAOStub());
            List<Material> expectedMaterialList = new ArrayList<>();
            List<Material> actualMaterialList = planner1.retrieveAvaliableMaterialToAdd(7);
            assertEquals(expectedMaterialList, actualMaterialList);
        } catch (MaterialException ex) {
            fail("MaterialException");
        }       
    }
    
    @Test(expected = MaterialException.class)
    public void testUnsuccessfulRetrieveAvaliableMaterialToAdd() throws MaterialException{
        Planner planner1 = new Planner("ProvaUser","ProvaPassword", null, new RequiredMaterialForMaintenanceDAOStub());
        planner1.retrieveAvaliableMaterialToAdd(8);   
    }
    
    /*=================== PRIVATE METHODS OF MODIFY ==============================================================================*/
    
    private void insertDefaultMaintenanceActivity(String typologyOfActivity, String typologyOfUnplannedActivity, Statement stm, int id) throws SQLException {
        String query = "INSERT INTO MaintenanceActivity (activityId, activityDescription, "
                + "estimatedInterventionTime, dateActivity, interruptibleActivity, typologyOfActivity,"
                + " typologyOfUnplannedActivity, typologyName, branchOffice, area) "
                + "VALUES (" + id + ", 'DefaultDescrizione', 1, '2100-1-1', True, '"+ typologyOfActivity +"',"
                + " "+ typologyOfUnplannedActivity +", 'DefaultTypologyName', 'DefaultBranchOffice', 'DefaultArea')";
        stm.executeUpdate(query);
    }
    
    private void deleteDafaultMaintenanceActivity(Statement stm, int id) throws SQLException {
        String query = "DELETE FROM MaintenanceActivity WHERE activityId="+id;
        stm.executeUpdate(query);
    }
    
    public MaintenanceActivity createMaintenanceActivity(String typeOfActivity, int id) {
        if(typeOfActivity.compareTo("Planned")==0){
            System.out.println("sto creando Planned");
            return new PlannedMaintenanceActivity(id, new Site("ProvaBranchOfficeMod"+id, "ProvaAreaMod"+id), "typologyNameMod"+id,
                    "ProvaDescrizioneMod"+id, id, LocalDate.now(), null, null, id%2==0);
        }else if(typeOfActivity.compareTo("EWO")==0){
            System.out.println("sto creando EWO");
            return new Ewo(id, new Site("ProvaBranchOfficeMod"+id, "ProvaAreaMod"+id), "typologyNameMod"+id,
                    "ProvaDescrizioneMod"+id, id, LocalDate.now(), null, null, id%2==0);
        }else{
            System.out.println("sto creando Extra");
            return new ExtraActivity(id, new Site("ProvaBranchOfficeMod"+id, "ProvaAreaMod"+id), "typologyNameMod"+id,
                    "ProvaDescrizioneMod"+id, id, LocalDate.now(), null, null, id%2==0);
        }
    }
    
    /*===========================================================================================================================*/
    
    
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
    /*Test methods of viewMaintenanceActivity developed by Rosario Gaeta*/
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
                    add(new Material("Material1"));
                    add(new Material("Material2"));
                    add(new Material("Material3"));
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
                    add(new Material("Material4"));
                    add(new Material("Material5"));
                    add(new Material("Material6"));
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
                    add(new Material("Material7"));
                    add(new Material("Material8"));
                    add(new Material("Material9"));
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
    
    private void assertMaterial(List<Material> materials, List<Material> expectedMaterials){
        Collections.sort(materials);
        Collections.sort(expectedMaterials);
        assertEquals("Material error", materials, expectedMaterials);
    }
}
