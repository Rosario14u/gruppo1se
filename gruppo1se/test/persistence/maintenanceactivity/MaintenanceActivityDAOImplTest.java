/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Ewo;
import business.maintenanceactivity.ExtraActivity;
import business.maintenanceactivity.MaintenanceActivity;
import business.maintenanceactivity.MaintenanceProcedure;
import business.maintenanceactivity.Material;
import business.maintenanceactivity.PlannedMaintenanceActivity;
import business.maintenanceactivity.Site;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class MaintenanceActivityDAOImplTest {
    private static Connection conn;
    private static String[] arrayActivity;
    private static final String DELETEMAINTENANCEACTIVITY = "DELETE FROM MAINTENANCEACTIVITY WHERE ACTIVITYID=?";
    private static final String INSERTMAINTEANCEACTIVITY = "INSERT INTO MaintenanceActivity "
            + "(activityId, activityDescription,"
            + " estimatedInterventionTime, dateActivity, interruptibleActivity, "
            + "typologyOfActivity, typologyOfUnplannedActivity, typologyName, "
            + "branchOffice, area) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETESITE = "DELETE FROM Site WHERE branchOffice = ? and  area = ?";
    private static final String INSERTSITE = 
            "INSERT INTO Site (branchOffice,area,workspaceNotes) "
            + "VALUES (?,?,?)";
    private final Site site = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes");
    private final String activityDescription = "ProvaActivityDescription";
    private final MaintenanceProcedure maintenanceProcedure = new MaintenanceProcedure("ProvaPDF");
    private final LinkedList<Material> materials = new LinkedList<>();
    private final String typology = "ProvaTypology";
    private final MaintenanceActivityDAOImpl instance = new MaintenanceActivityDAOImpl(new SiteDaoImpl());

    
    public MaintenanceActivityDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = ConnectionDB.getInstanceConnection().getConnection();
            conn.setAutoCommit(false);
            arrayActivity = new String[]{"Planned","EWO","Extra"};
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
          conn.setAutoCommit(true);
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * This method asserts that retrieveMaintenanceActivityDao correctly returns a PlannedMaintenanceActivity object<br>
     * when there is in database a planned activity with the required activityId.
     */
    @Test
    public void testRetrieveMaintenanceActivityDaoIsInDatabasePlanned() {
        try {
            deleteMaintenaceActivity(1);
            insertMaintenanceActivity(1,"ProvaDescrizione",120,"2050-11-23",true, 
                    "Planned",null,"ProvaTypologyName","ProvaBranch","ProvaArea");
            deleteSite("ProvaBranch", "ProvaArea");
            insertSite("ProvaBranch","ProvaArea","ProvaWorkSpaceNotes");
            MaintenanceActivity activity = instance.retrieveMaintenanceActivityDao(1);
            assertMaintenanceActivity(activity,1,"ProvaDescrizione",120,"2050-11-23",true, 
                    "Planned",null,"ProvaTypologyName","ProvaBranch","ProvaArea", "ProvaWorkSpaceNotes");
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    /**
     * This method asserts that retrieveMaintenanceActivityDao correctly returns an Ewo object<br>
     * when there is in database a Ewo activity with the required activityId.
     */
    @Test
    public void testRetrieveMaintenanceActivityDaoIsInDatabaseEWO() {
        try {
            deleteMaintenaceActivity(1);
            insertMaintenanceActivity(1,"ProvaDescrizione",120,"2050-11-23",true, 
                    "Unplanned","EWO","ProvaTypologyName","ProvaBranch","ProvaArea");
            deleteSite("ProvaBranch", "ProvaArea");
            insertSite("ProvaBranch","ProvaArea","ProvaWorkSpaceNotes");
            MaintenanceActivity activity = instance.retrieveMaintenanceActivityDao(1);
            assertMaintenanceActivity(activity,1,"ProvaDescrizione",120,"2050-11-23",true, 
                    "Unplanned","EWO","ProvaTypologyName","ProvaBranch","ProvaArea", "ProvaWorkSpaceNotes");
            conn.rollback(); 
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    /**
     * This method asserts that retrieveMaintenanceActivityDao correctly returns an ExtraActivity object<br>
     * when there is in database a extra activity with the required activityId.
     */
    @Test
    public void testRetrieveMaintenanceActivityDaoIsInDatabaseExtra() {
        try {
            deleteMaintenaceActivity(1);
            insertMaintenanceActivity(1,"ProvaDescrizione",120,"2050-11-23",true, 
                    "Unplanned","Extra","ProvaTypologyName","ProvaBranch","ProvaArea");
            deleteSite("ProvaBranch", "ProvaArea");
            insertSite("ProvaBranch","ProvaArea","ProvaWorkSpaceNotes");
            MaintenanceActivity activity = instance.retrieveMaintenanceActivityDao(1);
            assertMaintenanceActivity(activity,1,"ProvaDescrizione",120,"2050-11-23",true, 
                    "Unplanned","Extra","ProvaTypologyName","ProvaBranch","ProvaArea", "ProvaWorkSpaceNotes");
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    /**
     * This method asserts that retrieveMaintenanceActivityDao correctly returns null when the required<br>
     * maintenance activity is not in the database
     */
    @Test
    public void testRetrieveMaintenanceActivityDaoIsNotInDatabase() {
        try {
            String query = "DELETE FROM MAINTENANCEACTIVITY WHERE ACTIVITYID=1";
            Statement stm = conn.createStatement();
            stm.executeUpdate(query);
            MaintenanceActivity activity = instance.retrieveMaintenanceActivityDao(1);
            assertNull(activity);
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    private void deleteMaintenaceActivity(int activityId) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(DELETEMAINTENANCEACTIVITY);
        pstm.setInt(1, activityId);
        pstm.executeUpdate();
    }
    
    private void insertMaintenanceActivity(int activityId,String descrizione,int estimatedInterventionTime,
        String date,boolean InterruptibleActivity, String typologyOfActivity,String typologyOfUnplannedActivity,
        String typologyName,String branchOffice,String area) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERTMAINTEANCEACTIVITY);
        pstm.setInt(1, activityId);
        pstm.setString(2, descrizione);
        pstm.setInt(3, estimatedInterventionTime);
        pstm.setDate(4,Date.valueOf(date));
        pstm.setBoolean(5, InterruptibleActivity);
        pstm.setString(6, typologyOfActivity);
        pstm.setString(7, typologyOfUnplannedActivity);
        pstm.setString(8, typologyName);
        pstm.setString(9, branchOffice);
        pstm.setString(10, area);
        pstm.executeUpdate();
    }
    private void deleteSite(String branchOffice, String area) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(DELETESITE);
        pstm.setString(1, branchOffice);
        pstm.setString(2, area);
        pstm.executeUpdate();
    }
    
    private void insertSite(String branchOffice, String area, String workSpaceNotes) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERTSITE);
        pstm.setString(1, branchOffice);
        pstm.setString(2, area);
        pstm.setString(3, workSpaceNotes);
        pstm.executeUpdate();
    }
    
    private void assertMaintenanceActivity(MaintenanceActivity activity, 
        int activityId,String descrizione,int estimatedInterventionTime,
        String date,boolean InterruptibleActivity, String typologyOfActivity,
        String typologyOfUnplannedActivity, String typologyName,String branchOffice,
        String area, String workSpaceNotes){
            Class activityClass = null;
            if (typologyOfActivity.compareTo("Planned") == 0){
                activityClass= PlannedMaintenanceActivity.class;
            }else if(typologyOfUnplannedActivity.compareTo("EWO") == 0){
                activityClass= Ewo.class;
            }else{
                activityClass= ExtraActivity.class;
            }
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
            assertEquals("WorkSpaceNotes error", workSpaceNotes, activity.getSite().getWorkSpaceNotes());
    }

//===================================================================================================================

    /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates Planned Maintenance activity
     * to Unplanned EWO Maintenance actiity
     */
    @Test
    public void testModifyPlannedToEwoMaintenaceActivity() {
        try{
            Statement stm = conn.createStatement();

        
            deleteDafaultMaintenanceActivity(stm);
            insertDefaultMaintenanceActivity("Planned", "null", stm);
            MaintenanceActivity newActivity = new Ewo(1, new Site("ProvaBranchOfficeMod", "ProvaAreaMod"), "typologyNameMod", 
                                    "ProvaDescrizioneMod", 400, LocalDate.parse("2050-12-12"), null, null, false);

            boolean result = instance.modifyMaintenaceActivity(newActivity);
            assertEquals(true, result);
            ResultSet res = selectDefaultMaintenanceActivity(stm);
            
            verify(res, "Unplanned", "EWO");
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);            
        }        
    }
    
     /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates Planned Maintenance activity
     * to Unplanned Extra Maintenance actiity
     */
    @Test
    public void testModifyPlannedToExtraMaintenaceActivity(){
        try{
            Statement stm = conn.createStatement();

        
            deleteDafaultMaintenanceActivity(stm);
            insertDefaultMaintenanceActivity("Planned", "null", stm);
            MaintenanceActivity newActivity = new ExtraActivity(1, new Site("ProvaBranchOfficeMod", "ProvaAreaMod"), "typologyNameMod", 
                                    "ProvaDescrizioneMod", 400, LocalDate.parse("2050-12-12"), null, null, false);

            boolean result = instance.modifyMaintenaceActivity(newActivity);
            assertEquals(true, result);
            ResultSet res = selectDefaultMaintenanceActivity(stm);
            
            verify(res, "Unplanned", "Extra");
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);            
        }   
    }
    
     /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates Unplanned EWO Maintenance activity
     * to Unplanned Extra Maintenance actiity
     */
    @Test
    public void testModifyEwoToExtraMaintenaceActivity(){
        try{
            Statement stm = conn.createStatement();

        
            deleteDafaultMaintenanceActivity(stm);
            insertDefaultMaintenanceActivity("Planned", "null", stm);
            MaintenanceActivity newActivity = new ExtraActivity(1, new Site("ProvaBranchOfficeMod", "ProvaAreaMod"), "typologyNameMod", 
                                    "ProvaDescrizioneMod", 400, LocalDate.parse("2050-12-12"), null, null, false);

            boolean result = instance.modifyMaintenaceActivity(newActivity);
            assertEquals(true, result);
            ResultSet res = selectDefaultMaintenanceActivity(stm);
            
            verify(res, "Unplanned", "Extra");
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);            
        }   
    }
    
     /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates Unplanned EWO Maintenance activity
     * to Planned Maintenance actiity
     */
    @Test
    public void testModifyEwoToPlannedMaintenaceActivity(){
        try{
            Statement stm = conn.createStatement();

        
            deleteDafaultMaintenanceActivity(stm);
            insertDefaultMaintenanceActivity("Planned", "null", stm);
            MaintenanceActivity newActivity = new PlannedMaintenanceActivity(1, new Site("ProvaBranchOfficeMod", "ProvaAreaMod"), "typologyNameMod", 
                                    "ProvaDescrizioneMod", 400, LocalDate.parse("2050-12-12"), null, null, false);

            boolean result = instance.modifyMaintenaceActivity(newActivity);
            assertEquals(true, result);
            ResultSet res = selectDefaultMaintenanceActivity(stm);
            
            verify(res, "Planned", null);
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);            
        }   
    }
    
     /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates Unplanned Extra Maintenance activity
     * to Planned Maintenance actiity
     */
    @Test
    public void testModifyExtraToPlannedMaintenaceActivity(){
        try{
            Statement stm = conn.createStatement();

        
            deleteDafaultMaintenanceActivity(stm);
            insertDefaultMaintenanceActivity("Planned", "null", stm);
            MaintenanceActivity newActivity = new PlannedMaintenanceActivity(1, new Site("ProvaBranchOfficeMod", "ProvaAreaMod"), "typologyNameMod", 
                                    "ProvaDescrizioneMod", 400, LocalDate.parse("2050-12-12"), null, null, false);

            boolean result = instance.modifyMaintenaceActivity(newActivity);
            assertEquals(true, result);
            ResultSet res = selectDefaultMaintenanceActivity(stm);
            
            verify(res, "Planned", null);
 
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);            
        }   
    }
    
     /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates Unplanned Extra Maintenance activity
     * to Unplanned EWO Maintenance actiity
     */
    @Test
    public void testModifyExtraToEwoMaintenaceActivity(){
        try{
            Statement stm = conn.createStatement();

        
            deleteDafaultMaintenanceActivity(stm);
            insertDefaultMaintenanceActivity("Planned", "null", stm);
            MaintenanceActivity newActivity = new Ewo(1, new Site("ProvaBranchOfficeMod", "ProvaAreaMod"), "typologyNameMod", 
                                    "ProvaDescrizioneMod", 400, LocalDate.parse("2050-12-12"), null, null, false);

            boolean result = instance.modifyMaintenaceActivity(newActivity);
            assertEquals(true, result);
            ResultSet res = selectDefaultMaintenanceActivity(stm);
            
            verify(res, "Unplanned", "EWO");
            
            conn.rollback();
        }catch(SQLException ex){
            Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);            
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
    
    private ResultSet selectDefaultMaintenanceActivity(Statement stm) throws SQLException {
        String query = "SELECT * FROM MaintenanceActivity WHERE activityId=1";
        return stm.executeQuery(query);
    }
    
    private void verify(ResultSet res, String typologyOfActivity, String typologyOfUnplannedActivity) throws SQLException{
        assertNotNull(res);
        while(res.next()){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            assertEquals("ProvaDescrizioneMod", res.getString("activityDescription"));
            assertEquals(400, res.getInt("estimatedInterventionTime"));
            assertEquals("2050-12-12", df.format(res.getDate("dateActivity")));
            assertEquals("ProvaBranchOfficeMod", res.getString("branchOffice"));
            assertEquals("ProvaAreaMod", res.getString("area"));
            assertEquals("typologyNameMod", res.getString("typologyName"));
            assertEquals(false, res.getBoolean("interruptibleActivity"));
            assertEquals(typologyOfActivity, res.getString("typologyOfActivity"));
            assertEquals(typologyOfUnplannedActivity, res.getString("typologyOfUnplannedActivity"));
        }   
    }    
  /*=======================================================================================================================================*/
    /**
     * Test of addMaintenanceActivity method, of class MaintenanceActivityDAO.
     */
    /**
     * Test of addMaintenanceActivity method, of class MaintenanceActivityDAOImpl.
     */
    @Test
    public void testAddMaintenanceActivity() {
        try {
            System.out.println("addMaintenanceActivity");
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(1, site, typology, activityDescription, 300, LocalDate.of(2050, 11, 25), maintenanceProcedure, materials, false);

            instance.deleteMaintenanceActivity(activity.getActivityId());
            boolean result = instance.addMaintenanceActivity(activity);
            assertEquals(result, true);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test
    public void testAddMaintenanceActivityWrongDate() {
        try {
            System.out.println("addMaintenanceActivity");
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(1, site, typology, activityDescription, 300, LocalDate.of(2020,11,24), maintenanceProcedure, materials, false);

            instance.deleteMaintenanceActivity(activity.getActivityId());
            boolean result = instance.addMaintenanceActivity(activity);
            assertEquals(result, false);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test
    public void testAddMaintenanceActivityWrongActivityId() {
        try {
            System.out.println("addMaintenanceActivity");
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(0, site, typology, activityDescription, 300, LocalDate.now(), maintenanceProcedure, materials, false);

            instance.deleteMaintenanceActivity(activity.getActivityId());
            boolean result = instance.addMaintenanceActivity(activity);
            assertEquals(result, false);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test
    public void testAddMaintenanceActivityUnplanned() {
        try {
            System.out.println("addMaintenanceActivity");
            Ewo activity = new Ewo(2, site, typology, activityDescription, 300, LocalDate.of(2050, 11, 25), maintenanceProcedure, materials, false);

            instance.deleteMaintenanceActivity(activity.getActivityId());
            boolean result = instance.addMaintenanceActivity(activity);
            assertEquals(result, true);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
  /*=======================================================================================================================================*/
   
    /**
     * Test of deleteMaintenanceActivity method, of class MaintenanceActivityDAOImpl.
     */
    
    @Test
    public void testDeleteMaintenanceActivity() {
        System.out.println("deleteMaintenanceActivity");
        PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(123,site,typology,activityDescription,30,LocalDate.of(2050,11,25),maintenanceProcedure,materials,true);
        instance.deleteMaintenanceActivity(activity.getActivityId());
        instance.addMaintenanceActivity(activity);
        boolean result = instance.deleteMaintenanceActivity(activity.getActivityId());
        assertEquals(true, result);
    }
    
    
    @Test
    public void testDeleteMaintenanceActivityWithWrongId() {
        System.out.println("deleteMaintenanceActivity");
        PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(123,site,typology,activityDescription,30,LocalDate.of(2050,11,25),maintenanceProcedure,materials,true);
        instance.deleteMaintenanceActivity(activity.getActivityId());
        instance.addMaintenanceActivity(activity);
        boolean result = instance.deleteMaintenanceActivity(124);
        assertEquals(false, result); 
    }
}