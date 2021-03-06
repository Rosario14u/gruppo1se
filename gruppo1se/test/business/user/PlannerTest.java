/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.Appointment;
import business.maintenanceactivity.Ewo;
import business.maintenanceactivity.ExtraActivity;
import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.MaintenanceActivityFactory.Typology;
import business.maintenanceactivity.MaintenanceProcedure;
import business.maintenanceactivity.Material;
import business.maintenanceactivity.PlannedMaintenanceActivity;
import business.maintenanceactivity.Site;
import business.maintenanceactivity.Skill;
import dto.MaintainerDTO;
import exception.AppointmentException;
import exception.MaintenanceActivityException;
import exception.MaterialException;
import exception.NotValidParameterException;
import exception.UsersException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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
import persistence.maintenanceactivity.SiteDaoImpl;
import stub.EmployeeAppointmentDAOStub;
import stub.MaintainerSkillDAOStub;
import stub.MaintenanceActivityDAOStub;
import stub.RequiredMaterialForMaintenanceDAOStub;
import stub.RequiredSkillForMaintenanceDAOStub;
import stub.TypologyDAOStub;
import stub.UsersDAOStub;

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
    private LocalDate date = LocalDate.parse("2050-11-25");
    private final MaintenanceProcedure maintenanceProcedure;
    private final ArrayList<Material> materials;
    private boolean interruptibleActivity = false;
    private MaintenanceActivityDAOImpl instance;
    private final Planner planner;
    
    
    public PlannerTest() throws NotValidParameterException {
        this.instance = new MaintenanceActivityDAOImpl(new SiteDaoImpl());
        this.materials  = new ArrayList<>();
        this.maintenanceProcedure = new MaintenanceProcedure("ProvaPDF");        
        this.planner = new Planner("ProvaUser","ProvaPassword", new MaintenanceActivityDAOStub(),
            new RequiredMaterialForMaintenanceDAOStub(), new UsersDAOStub(),
            new EmployeeAppointmentDAOStub(), new RequiredSkillForMaintenanceDAOStub(), 
            new MaintainerSkillDAOStub(), new TypologyDAOStub());
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

    
    //================================= modifyMaintenanceActivity ==========================================
    //Developed by Antonio Gorrasi
    
    /**
     * This test assert that the method
     * modifyMaintenanceActivity
     * correctly returns true in modifyng
     * planned activity
     */
    @Test
    public void testSuccessfulModifyPlannedMaintenanceActivity(){
        try{
            assertTrue(planner.modifyMaintenanceActivity(1, new Site("branchOffice1", "area1"), 
                    "typology1", "description1", 1, LocalDate.now(), true, Typology.PLANNED));
        } catch (NotValidParameterException | MaintenanceActivityException  ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
    
    /**
     * This test assert that the method
     * modifyMaintenanceActivity
     * correctly returns false in modifyng
     * planned activity
     */
    @Test
    public void testUnsuccessfulModifyPlannedMaintenanceActivity() {
        try{
            assertFalse(planner.modifyMaintenanceActivity(2, new Site("branchOffice2", "area2"),
                    "typology2", "description2", 2, LocalDate.now(), true, Typology.PLANNED));
        } catch (NotValidParameterException | MaintenanceActivityException  ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
    
    /**
     * This test assert that the method
     * modifyMaintenanceActivity
     * correctly throws exception in 
     * modifyng planned activity
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testExceptionModifyPlannedMaintenanceActivity() throws MaintenanceActivityException{
        try {
            planner.modifyMaintenanceActivity(3, new Site ("branchOffice3", "area3"),
                    "typology3", "description3", 3, LocalDate.now(), true, Typology.PLANNED);
        } catch (NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
  
    
    /**
     * This test assert that the method
     * modifyMaintenanceActivity
     * correctly returns true in modifyng
     * EWO activity
     */
    @Test
    public void testSuccessfulModifyEwoMaintenanceActivity() {
        try{
            assertTrue(planner.modifyMaintenanceActivity(4,new Site("branchOffice4", "area4"),
                    "typology4", "description4", 4, LocalDate.now(), true, Typology.EWO));
        } catch (NotValidParameterException | MaintenanceActivityException  ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
    
    /**
     * This test assert that the method
     * modifyMaintenanceActivity
     * correctly returns false in modifyng
     * EWO activity
     */
    @Test
    public void testUnsuccessfulModifyEwoMaintenanceActivity() {
        try{
            assertFalse(planner.modifyMaintenanceActivity(5, new Site("branchOffice5", "area5"),
                    "typology5", "description5", 5, LocalDate.now(), true, Typology.EWO));
        } catch (NotValidParameterException | MaintenanceActivityException  ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
    
    /**
     * This test assert that the method
     * modifyMaintenanceActivity
     * correctly throws exception in 
     * modifyng EWO activity
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testExceptionModifyEwoMaintenanceActivity() throws MaintenanceActivityException{
        try {
            planner.modifyMaintenanceActivity(6, new Site("branchOffice6", "area6"),
                    "typology6", "description6", 6, LocalDate.now(), true, Typology.EWO);
        } catch (NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
    
    /**
     * This test assert that the method
     * modifyMaintenanceActivity
     * correctly returns true in modifyng
     * Extra activity
     */
    @Test
    public void testSuccessfulModifyExtraMaintenanceActivity() {
        try{
            assertTrue(planner.modifyMaintenanceActivity(7, new Site("branchOffice7", "area7"), 
                    "typology7", "description7", 7, LocalDate.now(), true, Typology.EXTRA));
        } catch (NotValidParameterException | MaintenanceActivityException  ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
    
    /**
     * This test assert that the method
     * modifyMaintenanceActivity
     * correctly returns false in modifyng
     * Extra activity
     */
    @Test
    public void testUnsuccessfulModifyExtraMaintenanceActivity() {
        try{
            assertFalse(planner.modifyMaintenanceActivity(8, new Site("branchOffice8", "area8"),
                    "typology8", "description8", 8, LocalDate.now(), true, Typology.EXTRA));
        } catch (NotValidParameterException | MaintenanceActivityException  ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
    
    /**
     * This test assert that the method
     * modifyMaintenanceActivity
     * correctly throws exception in 
     * modifyng Extra activity
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testExceptionModifyExtraMaintenanceActivity() throws MaintenanceActivityException{
        try {
            planner.modifyMaintenanceActivity(9, new Site("branchOffice9", "area9"),
                    "typology9", "description9", 9, LocalDate.now(), true, Typology.EXTRA);
        } catch (NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
   
    
    /*============================================================================================================================*/
    //Developed by Antonio Gorrasi
    
    /**
     * This test assert that the method addRequiredMaterial
     * correctly return true in adding materials
     */
    @Test
    public void testSuccessfulAddRequiredMaterial(){
        try{
            List<Material> listMaterialToAdd = new ArrayList<>();                       
            assertTrue(planner.addRequiredMaterial(1, listMaterialToAdd));
        } catch (MaterialException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * This test assert that the method addRequiredMaterial
     * correctly raises an exception in adding materials
     */
    @Test(expected = MaterialException.class)
    public void testUnsuccessfulAddRequiredMaterial() throws MaterialException{
        try {
            List<Material> listMaterialToAdd = new ArrayList<>();
            planner.addRequiredMaterial(2, listMaterialToAdd);
        } catch (NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * This test assert that the method removeRequiredMaterial
     * correctly return true in removing materials
     */
    @Test
    public void testSuccessfulRemoveRequiredMaterial(){
        try {
            List<Material> listMaterialToAdd = new ArrayList<>();
            assertTrue(planner.removeRequiredMaterial(3, listMaterialToAdd));
        } catch (MaterialException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * This test assert that the method removeRequiredMaterial
     * correctly return false in removing materials
     */
    @Test
    public void testRemoveRequiredMaterialNotComplete(){
        try {
            List<Material> listMaterialToAdd = new ArrayList<>();
            assertFalse(planner.removeRequiredMaterial(4, listMaterialToAdd));
        } catch (MaterialException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * This test assert that the method removeRequiredMaterial
     * correctly raises exception in removing materials
     */
    @Test(expected = MaterialException.class)
    public void testUnsuccessfulRemoveRequiredMaterial() throws MaterialException{
        try {
            List<Material> listMaterialToAdd = new ArrayList<>();
            planner.removeRequiredMaterial(5, listMaterialToAdd);
        } catch (NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    } 
    
    
    /**
     * This test assert that the method retrieveAvaliableMaterialToAdd
     * correctly returns a list of materials non associated yet
     */
    @Test
    public void testRetrieveAvaliableMaterialToAdd(){
        try {
            List<Material> expectedMaterialList = new ArrayList<>(){{
                add(new Material("Material1"));
                add(new Material("Material2"));
            }};
            List<Material> actualMaterialList = planner.retrieveAvaliableMaterialToAdd(6);
            assertEquals(expectedMaterialList, actualMaterialList);
        } catch (MaterialException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
        
    
    /**
     * This test assert that the method retrieveAvaliableMaterialToAdd
     * correctly returns an empty list
     */
    @Test
    public void testRetrieveAvaliableMaterialToAddEmpty(){
        try {
            List<Material> expectedMaterialList = new ArrayList<>();
            List<Material> actualMaterialList = planner.retrieveAvaliableMaterialToAdd(7);
            assertEquals(expectedMaterialList, actualMaterialList);
        } catch (MaterialException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }     
    }
    
    
    /**
     * This test assert that the method retrieveAvaliableMaterialToAdd
     * correctly raises an exception
     */
    @Test(expected = MaterialException.class)
    public void testUnsuccessfulRetrieveAvaliableMaterialToAdd() throws MaterialException{
        try{
            planner.retrieveAvaliableMaterialToAdd(8); 
        } catch (NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /*=================================================Test of makeMaintenanceActivity==========================================================================*/
    /*Test methods developed by Alessio Citro*/
    
    /**
     * Test of makeMaintenanceActivity method, of class Planner.
     * @throws exception.MaintenanceActivityException
     */
    @Test
    public void testMakeMaintenanceActivity() throws  MaintenanceActivityException {
        try {
            System.out.println("makeMaintenanceActivity");
            //planner.removeMaintenanceActivity(activityId);
            boolean result = planner.makeMaintenanceActivity(activityId, new Site(branchOffice, area, workspaceNotes), typology,
                    activityDescription, estimatedInterventionTime, date, maintenanceProcedure,
                    materials, interruptibleActivity, Typology.PLANNED);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }

    /**
     * Test of makeMaintenanceActivity method, of class Planner.
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testMakeMaintenanceActivityWrong() throws  MaintenanceActivityException {
        try {
            System.out.println("makeMaintenanceActivityWrong");
            activityId = 2;
            date = LocalDate.parse("2020-11-24");
            //planner.removeMaintenanceActivity(activityId);
            boolean result = planner.makeMaintenanceActivity(activityId, new Site(branchOffice, area, workspaceNotes), typology, activityDescription,
                    estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity, Typology.PLANNED);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of makeMaintenanceActivity method, of class Planner.
     * @throws exception.MaintenanceActivityException
     */
    @Test
    public void testMakeMaintenanceActivityExtraActivity() throws  MaintenanceActivityException{
        try {
            System.out.println("makeMaintenanceActivityExtraActivity");
            //planner.removeMaintenanceActivity(activityId);
            boolean result = planner.makeMaintenanceActivity(activityId, new Site(branchOffice, area, workspaceNotes),
                    typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials,
                    interruptibleActivity, Typology.EXTRA);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of makeMaintenanceActivity method, of class Planner.
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testMakeMaintenanceActivityExtraActivityWrong() throws  MaintenanceActivityException{
        try {
            System.out.println("makeMaintenanceActivityExtraActivityWrong");
            activityId = 2;
            date = LocalDate.parse("2020-11-24");
            //planner.removeMaintenanceActivity(activityId);
            boolean result = planner.makeMaintenanceActivity(activityId, new Site(branchOffice, area, workspaceNotes),
                    typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials,
                    interruptibleActivity, Typology.EXTRA);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of makeMaintenanceActivity method, of class Planner.
     * @throws exception.MaintenanceActivityException
     */
    @Test
    public void testMakeMaintenanceActivityEwo() throws  MaintenanceActivityException{
        try {
            System.out.println("makeMaintenanceActivityEwo");
            //planner.removeMaintenanceActivity(activityId);
            boolean result = planner.makeMaintenanceActivity(activityId, new Site(branchOffice, area,
                    workspaceNotes), typology, activityDescription, estimatedInterventionTime, date,
                    maintenanceProcedure, materials, interruptibleActivity, Typology.EWO);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of makeMaintenanceActivity method, of class Planner.
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testMakeMaintenanceActivityEwoWrong() throws MaintenanceActivityException {
        try {
            System.out.println("makeMaintenanceActivityEwoWrong");
            activityId = 2;
            date = LocalDate.parse("2020-11-24");
            //planner.removeMaintenanceActivity(activityId);
            boolean result = planner.makeMaintenanceActivity(activityId, new Site (branchOffice, area, workspaceNotes),
                    typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials,
                    interruptibleActivity, Typology.EWO);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
 //================================== Test of removeMaintenanceActivity =======================================================================   
    /*Test methods developed by Vincenza Coppola*/
    /**
    * Test of removeMaintenanceActivity method, of class Planner.
    * @throws exception.MaintenanceActivityException
    */
    @Test
    public void testRemoveMaintenanceActivity() throws MaintenanceActivityException {
        try {
            System.out.println("removeMaintenanceActivity");
            boolean result = planner.removeMaintenanceActivity(1);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on : connection rollback!");
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
    * Test of removeMaintenanceActivity method, of class Planner.
    * @throws exception.MaintenanceActivityException
    */
    @Test
    public void testRemoveMaintenanceActivityWithWrongId() throws MaintenanceActivityException {
        try {
            System.out.println("removeMaintenanceActivityWithWrongId");
            boolean result = planner.removeMaintenanceActivity(2);
            assertEquals(false, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on : connection rollback!");
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }

    /**
    * Test of removeMaintenanceActivity method, of class Planner.
    * @throws exception.MaintenanceActivityException
    */    
    @Test(expected = MaintenanceActivityException.class)
    public void testRemoveMaintenanceActivityException() throws MaintenanceActivityException {
        try {
            System.out.println("removeMaintenanceActivity that throws Exception");
            boolean result = planner.removeMaintenanceActivity(3);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on : connection rollback!");
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    //========================Test of viewMaintenanceActivity================================================
    /*Test methods of viewMaintenanceActivity developed by Rosario Gaeta*/
    /**
     * This test method asserts that viewMaintenanceActivity
     * correctly return a planned activity.
     */
    @Test
    public void testviewMaintenanceActivityReturnPlanned()  {
        try {
            List<Material> materials= new ArrayList<>(){{
                    add(new Material("Material1"));
                    add(new Material("Material2"));
                    add(new Material("Material3"));
                }};
            MaintenanceActivity expectedActivity = new PlannedMaintenanceActivity(3, new Site("ProvaBranchOffice",
                    "ProvaArea","ProvaWorkspaceNotes"), "ProvaTypology", "ProvaDescription",
                    120, LocalDate.parse("2020-12-20"), new MaintenanceProcedure("ProvaSmp"), materials, true);
            MaintenanceActivity returnedActivity = planner.viewMaintenanceActivity(3);
            assertEquals(expectedActivity, returnedActivity);
        }catch(NotValidParameterException | MaintenanceActivityException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    /**
     * This test method asserts that viewMaintenanceActivity 
     * correctly return an Ewo activity.
     */
    @Test
    public void testviewMaintenanceActivityReturnEwo() {
        try {
            List<Material> materials= new ArrayList<>(){{
                    add(new Material("Material1"));
                    add(new Material("Material2"));
                    add(new Material("Material3"));
                }};
            MaintenanceActivity expectedActivity = new Ewo(4, new Site("ProvaBranchOffice",
                    "ProvaArea","ProvaWorkspaceNotes"), "ProvaTypology", "ProvaDescription",
                    120, LocalDate.parse("2020-12-20"), new MaintenanceProcedure("ProvaSmp"), materials, true);
            MaintenanceActivity returnedActivity = planner.viewMaintenanceActivity(4);
            assertEquals(expectedActivity, returnedActivity);
        }catch(NotValidParameterException | MaintenanceActivityException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    /**
     * This test method asserts that viewMaintenanceActivity
     * correctly return a Extra activity.
     */
    @Test
    public void testviewMaintenanceActivityReturnExtra() {
        try {
            List<Material> materials= new ArrayList<>(){{
                    add(new Material("Material1"));
                    add(new Material("Material2"));
                    add(new Material("Material3"));
                }};
            MaintenanceActivity expectedActivity = new ExtraActivity(5, new Site("ProvaBranchOffice",
                    "ProvaArea","ProvaWorkspaceNotes"), "ProvaTypology", "ProvaDescription",
                    120, LocalDate.parse("2020-12-20"), new MaintenanceProcedure("ProvaSmp"), materials, true);
            MaintenanceActivity returnedActivity = planner.viewMaintenanceActivity(5);
            assertEquals(expectedActivity, returnedActivity);
        }catch(NotValidParameterException | MaintenanceActivityException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This test method asserts that viewMaintenanceActivity 
     * correctly return null when activity is not present.
     */
    @Test
    public void testviewMaintenanceActivityNull(){
        try {
            MaintenanceActivity activity = planner.viewMaintenanceActivity(6);
            assertNull("testviewMaintenanceActivityNull error", activity);
        }catch(NotValidParameterException | MaintenanceActivityException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This test method asserts that viewMaintenanceActivity
     * correctly raises MaintenanceActivityException.
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testviewMaintenanceActivitySiteException() throws MaintenanceActivityException{
        try {
            MaintenanceActivity activity = planner.viewMaintenanceActivity(7);
        }catch(NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    /**
     * This test method assert that viewMaintenanceActivity
     * correctly raises MaintenanceActivityException.
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testviewMaintenanceActivityMaintenanceActivityException() throws MaintenanceActivityException{
        try {
            MaintenanceActivity activity = planner.viewMaintenanceActivity(8);
        }catch(NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    /**
     * This test method assert that viewMaintenanceActivity correctly 
     * return a maintenance activity with no material associated.
     */
    @Test
    public void testviewMaintenanceActivityMaterialEmpty() {
        try {
            List<Material> materials= new ArrayList<>();
            MaintenanceActivity expectedActivity = new PlannedMaintenanceActivity(1, new Site("ProvaBranchOffice",
                    "ProvaArea","ProvaWorkspaceNotes"), "ProvaTypology", "ProvaDescription",
                    120, LocalDate.parse("2020-12-20"), null, materials, false);
            MaintenanceActivity returnedActivity = planner.viewMaintenanceActivity(1);
            assertEquals(expectedActivity, returnedActivity);
        }catch(NotValidParameterException | MaintenanceActivityException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    /**
     * This test method assert that viewMaintenanceActivity 
     * correctly raises MaintenanceException.
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testviewMaintenanceActivityMaterialException() throws MaintenanceActivityException {
        try {
            MaintenanceActivity activity = planner.viewMaintenanceActivity(2);
        }catch(NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }



    //====================================== test viewMaintenanceActivityByWeek ==================================
    //Test developed by Antonio Gorrasi.
    
    /**
     * This test asserts that viewMaintenanceActivityByWeek<br> 
     * correctly raises MaintenanceActivityException <br>
     * when wrong week number
     * @throws MaintenanceActivityException 
     */
    @Test(expected = MaintenanceActivityException.class)
    public void viewMaintenanceActivityByWeekMaintenanceActivityException() 
            throws MaintenanceActivityException{
        try {
            planner.viewMaintenanceActivityByWeek(0, 2020);
        } catch (NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    } 
    
    
    /**
     * This test asserts that viewMaintenanceActivityByWeek<br> 
     * correctly returns a list of MaintenanceActivity and<br>
     * skills related to the procedure associate to the activity
     * @throws exception.MaintenanceActivityException
     */
    @Test
    public void viewMaintenanceActivityByWeekSiteException() throws MaintenanceActivityException{
        try {
            List<MaintenanceActivity> expectedList = new ArrayList<>(){{
                add(new PlannedMaintenanceActivity(1, new Site("ProvaBranchOffice1", "ProvaArea1"),
                        "ProvaTypology1", "ProvaDescription1", 1, LocalDate.of(2050, Month.JANUARY, 1),
                        new MaintenanceProcedure("smp1"), null, true));
                add(new PlannedMaintenanceActivity(2, new Site("ProvaBranchOffice2", "ProvaArea2"),
                        "ProvaTypology2", "ProvaDescription2", 2, LocalDate.of(2050, Month.JANUARY, 2),
                        new MaintenanceProcedure("smp7"), null, true));
            }};
            
            expectedList.get(0).getMaintenanceProcedure().setSkills(new ArrayList<>(){{
                    add(new Skill("Skill1"));
                    add(new Skill("Skill2"));
                    add(new Skill("Skill3"));
                }});
            
            List<MaintenanceActivity> list = planner.viewMaintenanceActivityByWeek(1, 2021);

            assertEquals(expectedList.get(0), list.get(0));
            assertEquals(expectedList.get(1), list.get(1));

        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    
    /**
     * This test asserts that viewMaintenanceActivityByWeek<br> 
     * correctly return an empty list of MaintenanceActivity 
     */
    @Test
    public void viewMaintenanceActivityByWeekEmpty(){
        try{
            List<MaintenanceActivity> listOfMaintenaceActivity= planner.viewMaintenanceActivityByWeek(5, 2021);
            assertEquals(listOfMaintenaceActivity.size(), 0);
        } catch(MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    //====================================== test viewEmployeeAvailability ==================================
    //Test developed by Antonio Gorrasi.
    
    /**
     * this test simulates a wrong week number
     * @throws UsersException 
     */
    @Test(expected = UsersException.class)
    public void testViewEmployeeAvailabilityWrongDate() throws UsersException {
        try {
            List<MaintainerDTO> list = planner.viewEmployeeAvailability(0, 2020);
        } catch (NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }         
    }
    
    
    /**
     * This test asserts that viewEmployeeAvailability<br> 
     * correctly returns a list of maintainers and<br>
     * related skills and appointments
     * @throws UsersException 
     */
    @Test
    public void testViewEmployeeAvailability() throws UsersException {
        try {
            List<MaintainerDTO> list = planner.viewEmployeeAvailability(1, 2020);
            List<Skill> expectedMaintainer1Skills = new ArrayList<>(){{
                add(new Skill("Skill1"));
                add(new Skill("Skill2"));
            }};
            List<Appointment> expectedMaintainer1Appointments = new ArrayList<>(){{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                add(new Appointment(1, LocalDateTime.parse("2020-01-05 00:00:00", formatter), 20));
                add(new Appointment(2, LocalDateTime.parse("2020-01-05 00:00:00", formatter), 40));
            }};
            
            MaintainerDTO maintainer1 = list.get(0);
            MaintainerDTO maintainer2 = list.get(1);
            
            assertTrue(maintainer2.getSkills().isEmpty());
            assertTrue(maintainer2.getAppointmentsInWeek().isEmpty());
            
            assertEquals(expectedMaintainer1Appointments, maintainer1.getAppointmentsInWeek());
            assertEquals(expectedMaintainer1Skills, maintainer1.getSkills());
        } catch (NotValidParameterException ex) {
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }         
    }
    
    
    
    //=================================================testSaveAppointments================================================================
    /*Test developed by Rosario Gaeta.*/
    
    /**
     * This method assert that saveAppointments correctly return true when both dao returns true.
     */
    @Test
    public void testSaveAppointmentsBothDAOReturnTrue(){
        try {
            MaintenanceActivity activity = new PlannedMaintenanceActivity(1, new Site("branchOffice1","area1",null),
                    "typology1", "description1", 1, LocalDate.now(), null, null, true);
            String username = "username1";
            List<Appointment> listAppointment = new ArrayList<>(){{
                add(new Appointment(1, LocalDateTime.now(),50));
            }};
            boolean retVal = planner.saveAppointments(username, activity, listAppointment);
            assertTrue(retVal);
        }catch(AppointmentException | NotValidParameterException | MaintenanceActivityException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This method assert that saveAppointments correctly raises an AppointmentException if<br>
     * addEmployeeAvailability raises an AppointmentException.
     * @throws exception.AppointmentException
     */
    @Test(expected = AppointmentException.class)
    public void testSaveAppointmentsEmployeeActivityDAOThrowsException() throws AppointmentException{
        try {
            MaintenanceActivity activity = new PlannedMaintenanceActivity(1, new Site("branchOffice1","area1",null),
                    "typology1", "description1", 1, LocalDate.now(), null, null, true);
            String username = "username2";
            List<Appointment> listAppointment = new ArrayList<>(){{
                add(new Appointment(1, LocalDateTime.now(),50));
            }};
            boolean retVal = planner.saveAppointments(username, activity, listAppointment);
        }catch(NotValidParameterException | MaintenanceActivityException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This method assert that saveAppointments correctly raises an AppointmentException if<br>
     * username is null.
     * @throws exception.AppointmentException
     */
    @Test(expected = AppointmentException.class)
    public void testSaveAppointmentsUsernameNull()throws AppointmentException{
        try {
            MaintenanceActivity activity = new PlannedMaintenanceActivity(1, new Site("branchOffice1","area1",null),
                    "typology1", "description1", 1, LocalDate.now(), null, null, true);
            String username = null;
            List<Appointment> listAppointment = new ArrayList<>(){{
                add(new Appointment(1, LocalDateTime.now(),50));
            }};
            boolean retVal = planner.saveAppointments(username, activity, listAppointment);
        }catch(NotValidParameterException | MaintenanceActivityException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This method assert that saveAppointments correctly raises an AppointmentException if<br>
     * username is an empty string.
     * @throws exception.AppointmentException
     */
    @Test(expected = AppointmentException.class)
    public void testSaveAppointmentsUsernameEmpty()throws AppointmentException{
        try {
            MaintenanceActivity activity = new PlannedMaintenanceActivity(1, new Site("branchOffice1","area1",null),
                    "typology1", "description1", 1, LocalDate.now(), null, null, true);
            String username = " ";
            List<Appointment> listAppointment = new ArrayList<>(){{
                add(new Appointment(1, LocalDateTime.now(),50));
            }};
            boolean retVal = planner.saveAppointments(username, activity, listAppointment);
        }catch(NotValidParameterException | MaintenanceActivityException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This method assert that saveAppointments correctly raises an MaintenanceActivityException if<br>
     * activity is null.
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testSaveAppointmentsActivityNull() throws MaintenanceActivityException{
        try {
            MaintenanceActivity activity = null;
            String username = "username1";
            List<Appointment> listAppointment = new ArrayList<>(){{
                add(new Appointment(1, LocalDateTime.now(),50));
            }};
            boolean retVal = planner.saveAppointments(username, activity, listAppointment);
        }catch(NotValidParameterException | AppointmentException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This method assert that saveAppointments correctly raises an AppointmentException if<br>
     * listAppointment is null.
     * @throws exception.AppointmentException
     */
    @Test(expected = AppointmentException.class)
    public void testSaveAppointmentsListAppointmentNull()throws AppointmentException{
        try {
            MaintenanceActivity activity = new PlannedMaintenanceActivity(1, new Site("branchOffice1","area1",null),
                    "typology1", "description1", 1, LocalDate.now(), null, null, true);
            String username = "username1";
            List<Appointment> listAppointment = null;
            boolean retVal = planner.saveAppointments(username, activity, listAppointment);
        }catch(NotValidParameterException | MaintenanceActivityException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This method assert that saveAppointments correctly returns false if<br>
     * listAppointment is an empty List.
     */
    @Test
    public void testSaveAppointmentsEmployeeActivityDAOListEmpty(){
        try {
            MaintenanceActivity activity = new PlannedMaintenanceActivity(1, new Site("branchOffice1","area1",null),
                    "typology1", "description1", 1, LocalDate.now(), null, null, true);
            String username = "username1";
            List<Appointment> listAppointment = new ArrayList<>();
            boolean retVal = planner.saveAppointments(username, activity, listAppointment);
            assertFalse(retVal);
        }catch(AppointmentException | NotValidParameterException | MaintenanceActivityException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This method assert that saveAppointments correctly returns false if<br>
     * the activity update fails.
     */
    @Test
    public void testSaveAppointmentsMaintenanceActivityDAOReturnsFalse(){
        try {
            MaintenanceActivity activity = new PlannedMaintenanceActivity(2, new Site("branchOffice2","area2",null),
                    "typology2", "description2", 2, LocalDate.now(), null, null, true);
            String username = "username1";
            List<Appointment> listAppointment = new ArrayList<>(){{
                add(new Appointment(1, LocalDateTime.now(),50));
            }};
            boolean retVal = planner.saveAppointments(username, activity, listAppointment);
            assertFalse(retVal);
        }catch(AppointmentException | NotValidParameterException | MaintenanceActivityException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    
    /**
     * This method assert that saveAppointments correctly raises an MaintenanceActivityException if<br>
     * if the method that updates the activity throws a MaintenanceActivityException.
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testSaveAppointmentsMaintenanceActivityDAOThrowsException() throws MaintenanceActivityException{
        try {
            MaintenanceActivity activity = new PlannedMaintenanceActivity(3, new Site("branchOffice3","area3",null),
                    "typology3", "description3", 3, LocalDate.now(), null, null, true);
            String username = "username1";
            List<Appointment> listAppointment = new ArrayList<>(){{
                add(new Appointment(1, LocalDateTime.now(),50));
            }};
            boolean retVal = planner.saveAppointments(username, activity, listAppointment);
        }catch(AppointmentException | NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /*===============================================test verifyActivityAssignment============================================================*/
    /**
     * This test assert that verifyActivityAssignment 
     * correctly returns true when total duration of<br>
     * of appointments connected to the activity is 
     * equal to estimatedInterventionTime
     */
    @Test
    public void testVerifyActivityAssignmentTrue()  {
        try {
            int estimatedInterventionTime = 100;
            int activityId = 1;
            boolean returnedResult = planner.verifyActivityAssignment(activityId, estimatedInterventionTime);
            assertTrue("verifyActivityAssignmentTrue", returnedResult);
        }catch(AppointmentException | NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This test assert that verifyActivityAssignment correctly 
     * returns false when total duration of<br>
     * of appointments connected to the activity 
     * is not equal to estimatedInterventionTime
     */
    @Test
    public void testVerifyActivityAssignmentFalse()  {
        try {
            int estimatedInterventionTime = 110;
            int activityId = 1;
            boolean returnedResult = planner.verifyActivityAssignment(activityId, estimatedInterventionTime);
            assertFalse("verifyActivityAssignmentFalse", returnedResult);
        }catch(AppointmentException | NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This test assert that verifyActivityAssignment 
     * correctly returns false when total duration of<br>
     * of appointments connected to the activity is 
     * not equal to estimatedInterventionTime
     */
    @Test(expected = AppointmentException.class)
    public void testVerifyActivityAssignmentAppointmentException() throws AppointmentException  {
        try {
            int estimatedInterventionTime = 110;
            int activityId = 2;
            boolean returnedResult = planner.verifyActivityAssignment(activityId, estimatedInterventionTime);
        }catch(NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
}
