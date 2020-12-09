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
import business.maintenanceactivity.Skill;
import exception.DateException;
import exception.MaintenanceActivityException;
import exception.SiteException;
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
import stub.SiteDaoStub;

/**
 *
 * @author rosar
 */
public class MaintenanceActivityDAOImplTest {
    private static Connection conn;
    private static final String DELETE_MAINTENANCE_ACTIVITY = "DELETE FROM MAINTENANCEACTIVITY WHERE ACTIVITYID=?";
    private static final String INSERT_MAINTENANCE_ACTIVITY = "INSERT INTO MaintenanceActivity "
            + "(activityId, activityDescription,"
            + " estimatedInterventionTime, dateActivity, interruptibleActivity, "
            + "typologyOfActivity, typologyOfUnplannedActivity, typologyName, "
            + "branchOffice, area,smp) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SELECT_MAINTENANCE_ACTIVITY = "SELECT * FROM MaintenanceActivity WHERE activityId= ?";
    private final Site site;
    private final String activityDescription;
    private final MaintenanceProcedure maintenanceProcedure;
    private final ArrayList<Material> materials;
    private final ArrayList<Skill> skills;
    private final String typology;
    private final MaintenanceActivityDAOImpl maintenanceActivityDAO;

    
    public MaintenanceActivityDAOImplTest() {
        site = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes");
        activityDescription = "ProvaActivityDescription";
        maintenanceProcedure = new MaintenanceProcedure("ProvaPDF");
        materials = new ArrayList<>();
        skills = new ArrayList<>();
        typology = "ProvaTypology";
        maintenanceActivityDAO = new MaintenanceActivityDAOImpl(new SiteDaoStub());
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = ConnectionDB.getInstanceConnection().getConnection();
            conn.setAutoCommit(false);
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
        try {
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceActivityDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //=======================Test of retrieveMaintenanceActivityDao=========================================
    /*Test methods of retrieveMaintenanceActivityDao developed by Rosario Gaeta*/
    /**
     * This method asserts that retrieveMaintenanceActivityDao correctly returns a PlannedMaintenanceActivity object<br>
     * when there is in database a planned activity with the required activityId.
     */
    @Test
    public void testRetrieveMaintenanceActivityDaoIsInDatabasePlanned() {
        try {
            deleteMaintenaceActivity(1);
            insertMaintenanceActivity(1,"ProvaDescrizione1",121,"2050-11-21",true, 
                    "Planned",null,"ProvaTypologyName1","ProvaBranch1","ProvaArea1","smp1");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(1);
            assertMaintenanceActivity(activity,1,"ProvaDescrizione1",121,"2050-11-21",true, 
                    "Planned",null,"ProvaTypologyName1","ProvaBranch1","ProvaArea1", "ProvaWorkSpaceNotes1","smp1");
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SiteException ex) {
            fail("SiteException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        }
    }
    /**
     * This method asserts that retrieveMaintenanceActivityDao correctly returns an Ewo object<br>
     * when there is in database a Ewo activity with the required activityId.
     */
    @Test
    public void testRetrieveMaintenanceActivityDaoIsInDatabaseEWO() {
        try {
            deleteMaintenaceActivity(2);
            insertMaintenanceActivity(2,"ProvaDescrizione2",122,"2050-11-22",true, 
                    "Unplanned","EWO","ProvaTypologyName2","ProvaBranch2","ProvaArea2","smp2");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(2);
            assertMaintenanceActivity(activity,2,"ProvaDescrizione2",122,"2050-11-22",true, 
                    "Unplanned","EWO","ProvaTypologyName2","ProvaBranch2","ProvaArea2", "ProvaWorkSpaceNotes2","smp2");
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SiteException ex) {
            fail("SiteException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        }
    }
    /**
     * This method asserts that retrieveMaintenanceActivityDao correctly returns an ExtraActivity object<br>
     * when there is in database a extra activity with the required activityId.
     */
    @Test
    public void testRetrieveMaintenanceActivityDaoIsInDatabaseExtra() {
        try {
            deleteMaintenaceActivity(3);
            insertMaintenanceActivity(3,"ProvaDescrizione3",123,"2050-11-23",false, 
                    "Unplanned","Extra","ProvaTypologyName3","ProvaBranch3","ProvaArea3","smp3");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(3);
            assertMaintenanceActivity(activity,3,"ProvaDescrizione3",123,"2050-11-23",false, 
                    "Unplanned","Extra","ProvaTypologyName3","ProvaBranch3","ProvaArea3", "ProvaWorkSpaceNotes3","smp3");
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SiteException ex) {
            fail("SiteException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        }
    }
    
   /**
     * This method asserts that retrieveMaintenanceActivityDao correctly returns null when the required<br>
     * maintenance activity is not in the database
     */
    @Test
    public void testRetrieveMaintenanceActivityDaoNotInDatabase() {
        try {
            deleteMaintenaceActivity(1);
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(1);
            assertNull("MaintenanceActivityImpl error", activity);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SiteException ex) {
            fail("SiteException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        }    
    }
    
    /**
     * This method asserts that retrieveMaintenanceActivityDao correctly raises a SiteException <br>
     * when SiteDao return Null
     */
    @Test(expected = SiteException.class)
    public void testRetrieveMaintenanceActivityDaoSiteExceptionCase1() throws  SiteException {
        try {
            deleteMaintenaceActivity(4);
            insertMaintenanceActivity(4,"ProvaDescrizione4",124,"2050-11-24",false, 
                    "Unplanned","Extra","ProvaTypologyName4","ProvaBranch4","ProvaArea4","smp4");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(4);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        } 
    }
    
    /**
     * This method asserts that retrieveMaintenanceActivityDao correctly raises a SiteException <br>
     * when SiteDao raises an exception
     */
    @Test(expected = SiteException.class)
    public void testRetrieveMaintenanceActivityDaoSiteExceptionCase2() throws   SiteException {
        try {
            deleteMaintenaceActivity(5);
            insertMaintenanceActivity(5,"ProvaDescrizione5",125,"2050-11-25",false, 
                    "Unplanned","Extra","ProvaTypologyName5","ProvaBranch5","ProvaArea5", "smp5");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(5);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        } 
    }
    
    //=======================Utilities to test retrieveMaintenanceActivityDao=========================================
    
    private void deleteMaintenaceActivity(int activityId) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(DELETE_MAINTENANCE_ACTIVITY);
        pstm.setInt(1, activityId);
        pstm.executeUpdate();
    }
    
    private void insertMaintenanceActivity(int activityId,String descrizione,int estimatedInterventionTime,
        String date,boolean InterruptibleActivity, String typologyOfActivity,String typologyOfUnplannedActivity,
        String typologyName,String branchOffice,String area,String smp) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_MAINTENANCE_ACTIVITY);
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
        pstm.setString(11, smp);
        pstm.executeUpdate();
    }
    
    private void assertMaintenanceActivity(MaintenanceActivity activity, 
        int activityId,String descrizione,int estimatedInterventionTime,
        String date,boolean InterruptibleActivity, String typologyOfActivity,
        String typologyOfUnplannedActivity, String typologyName,String branchOffice,
        String area, String workSpaceNotes,String smp){
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
            assertEquals("smp error", smp, activity.getMaintenanceProcedure().getSmp());
    }

    //====================================== MODIFY ==============================================================================
    
    /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates 
     * Planned Maintenance activity to Unplanned EWO Maintenance actiity
     */
    @Test
    public void testModifyPlannedToEwoMaintenaceActivity() {
        try{
            Statement stm = conn.createStatement();
            deleteMaintenaceActivity(1);
            insertMaintenanceActivity(1,"ProvaDescrizione1",121,"2050-11-21",true, 
                    "Planned",null,"ProvaTypologyName1","ProvaBranch1","ProvaArea1",null);          
            MaintenanceActivity newActivity = createMaintenanceActivity("EWO", 1);
            assertEquals(true, maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(1), newActivity);
        }catch(SQLException ex){
            fail("SQLException was thrown");
        }catch(MaintenanceActivityException ex){
            fail("MaterialException was thrown");
        }
    }
    
    
     /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates 
     * Planned Maintenance activity to Unplanned Extra Maintenance actiity
     */
    @Test
    public void testModifyPlannedToExtraMaintenaceActivity(){
        try{
            Statement stm = conn.createStatement();
            deleteMaintenaceActivity(2);
            insertMaintenanceActivity(2,"ProvaDescrizione2",122,"2050-11-22",true, 
                    "Planned",null,"ProvaTypologyName2","ProvaBranch2","ProvaArea2",null);           
            MaintenanceActivity newActivity = createMaintenanceActivity("Extra", 2);
            assertEquals(true, maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(2), newActivity);
        }catch(SQLException ex){
            fail("SQLException was thrown");
        }catch(MaintenanceActivityException ex){
            fail("MaterialException was thrown");
        }
    }
//    
     /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates 
     * Unplanned EWO Maintenance activity to Unplanned Extra Maintenance actiity
     */
    @Test
    public void testModifyEwoToExtraMaintenaceActivity(){
        try{
            Statement stm = conn.createStatement();
            deleteMaintenaceActivity(3);
            insertMaintenanceActivity(3,"ProvaDescrizione3",123,"2050-11-23",true, 
                    "Unplanned","EWO","ProvaTypologyName3","ProvaBranch3","ProvaArea3",null);
            MaintenanceActivity newActivity = createMaintenanceActivity("Extra", 3);
            assertEquals(true, maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(3), newActivity);
        }catch(SQLException ex){
            fail("SQLException was thrown");
        }catch(MaintenanceActivityException ex){
            fail("MaterialException was thrown");
        }
    }
    
     /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates 
     * Unplanned EWO Maintenance activity to Planned Maintenance actiity
     */
    @Test
    public void testModifyEwoToPlannedMaintenaceActivity(){
        try{
            Statement stm = conn.createStatement();
            deleteMaintenaceActivity(4);
            insertMaintenanceActivity(4,"ProvaDescrizione4",124,"2050-11-24",false, 
                    "Unplanned","EWO","ProvaTypologyName4","ProvaBranch4","ProvaArea4",null);
            MaintenanceActivity newActivity = createMaintenanceActivity("Planned", 4);
            assertEquals(true, maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(4), newActivity);
        }catch(SQLException ex){
            fail("SQLException was thrown");
        }catch(MaintenanceActivityException ex){
            fail("MaterialException was thrown");
        } 
    }
  
     /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates 
     * Unplanned Extra Maintenance activity to Planned Maintenance actiity
     */
    @Test
    public void testModifyExtraToPlannedMaintenaceActivity(){
        try{
            Statement stm = conn.createStatement();
            deleteMaintenaceActivity(5);
            insertMaintenanceActivity(5,"ProvaDescrizione5",125,"2050-11-25",false, 
                    "Unplanned","Extra","ProvaTypologyName5","ProvaBranch5","ProvaArea5",null);
            MaintenanceActivity newActivity = createMaintenanceActivity("Planned", 5);
            assertEquals(true, maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(5), newActivity);
        }catch(SQLException ex){
            fail("SQLException was thrown");
        }catch(MaintenanceActivityException ex){
            fail("MaterialException was thrown");
        }    
    }
    
     /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates 
     * Unplanned Extra Maintenance activity to Unplanned EWO Maintenance actiity
     */
    @Test
    public void testModifyExtraToEwoMaintenaceActivity(){
        try{
            Statement stm = conn.createStatement();
            deleteMaintenaceActivity(6);
            insertMaintenanceActivity(6,"ProvaDescrizione6",126,"2050-11-26",false, 
                    "Unplanned","Extra","ProvaTypologyName6","ProvaBranch6","ProvaArea6",null);
            MaintenanceActivity newActivity = createMaintenanceActivity("EWO", 6);
            assertEquals(true, maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(6), newActivity);
        }catch(SQLException ex){
            fail("SQLException was thrown");
        }catch(MaintenanceActivityException ex){
            fail("MaterialException was thrown");
        }   
    }
    
    /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly return 
     * false if the maintenance activity is not present in the database
     */
    @Test
    public void testModifyActivityNotPresent(){
        try{
            Statement stm = conn.createStatement();
            deleteMaintenaceActivity(7);
            MaintenanceActivity newActivity = createMaintenanceActivity("EWO", 7);
            assertEquals(false, maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
        }catch(SQLException ex){
            fail("SQLException was thrown");
        }catch(MaintenanceActivityException ex){
            fail("MaterialException was thrown");
        }   
    }
    
    //=================== PRIVATE METHODS OF MODIFY ==============================================================================
    

    private ResultSet selectDefaultMaintenanceActivity(int id) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(SELECT_MAINTENANCE_ACTIVITY);
        pstm.setInt(1, id);
        return pstm.executeQuery();
    }
    
    private void verify(ResultSet res, MaintenanceActivity newActivity) throws SQLException {
        boolean isEmpty = true;
        while(res.next()){
            isEmpty = false;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //System.out.println(newActivity.getDate().toString());
            assertEquals(newActivity.getActivityDescription(), res.getString("activityDescription"));
            assertEquals(newActivity.getEstimatedInterventionTime(), res.getInt("estimatedInterventionTime"));
            assertEquals(newActivity.getDate().toString(), df.format(res.getDate("dateActivity")));
            assertEquals(newActivity.getSite().getBranchOffice(), res.getString("branchOffice"));
            assertEquals(newActivity.getSite().getArea(), res.getString("area"));
            assertEquals(newActivity.getTypology(), res.getString("typologyName"));
            assertEquals(newActivity.isInterruptibleActivity(), res.getBoolean("interruptibleActivity"));
            if(newActivity instanceof PlannedMaintenanceActivity){
                assertEquals("Planned", res.getString("typologyOfActivity"));
                assertEquals(null, res.getString("typologyOfUnplannedActivity"));
            }else if(newActivity instanceof Ewo){
                assertEquals("Unplanned", res.getString("typologyOfActivity"));
                assertEquals("EWO", res.getString("typologyOfUnplannedActivity"));
            }else{
                assertEquals("Unplanned", res.getString("typologyOfActivity"));
                assertEquals("Extra", res.getString("typologyOfUnplannedActivity"));
            }
        }
        assertFalse(isEmpty);
    }  
    
     public MaintenanceActivity createMaintenanceActivity(String typeOfActivity, int id) {
        if(typeOfActivity.compareTo("Planned")==0){
            return new PlannedMaintenanceActivity(id, new Site("ProvaBranchOfficeMod"+id, "ProvaAreaMod"+id), "typologyNameMod"+id,
                    "ProvaDescrizioneMod"+id, id, LocalDate.now(), null, null, null,id%2==0);
        }else if(typeOfActivity.compareTo("EWO")==0){
            return new Ewo(id, new Site("ProvaBranchOfficeMod"+id, "ProvaAreaMod"+id), "typologyNameMod"+id,
                    "ProvaDescrizioneMod"+id, id, LocalDate.now(), null, null, null,id%2==0);
        }else{
            return new ExtraActivity(id, new Site("ProvaBranchOfficeMod"+id, "ProvaAreaMod"+id), "typologyNameMod"+id,
                    "ProvaDescrizioneMod"+id, id, LocalDate.now(), null, null, null,id%2==0);
        }
    }
    
  //=======================================================================================================================================
    

    /**
     * Test of addMaintenanceActivity method, of class MaintenanceActivityDAOImpl.
     */
    @Test
    public void testAddMaintenanceActivity() throws MaintenanceActivityException {
        try {
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(1, site, typology,
                    activityDescription, 300, LocalDate.of(2050, 11, 25), maintenanceProcedure, materials, skills, false);
            deleteMaintenaceActivity(activity.getActivityId());
            maintenanceActivityDAO.addMaintenanceActivity(activity);
            verify(selectDefaultMaintenanceActivity(1), activity);
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test(expected = MaintenanceActivityException.class)
    public void testAddMaintenanceActivityWrongDate() throws MaintenanceActivityException {
        try {
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(2, site, typology,
                    activityDescription, 300, LocalDate.of(2020,11,24), maintenanceProcedure, materials, skills, false);
            deleteMaintenaceActivity(activity.getActivityId());
            maintenanceActivityDAO.addMaintenanceActivity(activity);
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test(expected = MaintenanceActivityException.class)
    public void testAddMaintenanceActivityWrongActivityID() throws MaintenanceActivityException {
        try {
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(0, site, typology,
                    activityDescription, 300, LocalDate.of(2050, 11, 25), maintenanceProcedure, materials, skills,false);
            deleteMaintenaceActivity(activity.getActivityId());
            maintenanceActivityDAO.addMaintenanceActivity(activity);
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test
    public void testAddMaintenanceActivityUnplanned() throws MaintenanceActivityException {
        try {
            Ewo activity = new Ewo(3, site, typology, activityDescription, 300,
                    LocalDate.of(2050, 11, 25), maintenanceProcedure, materials, false);
            deleteMaintenaceActivity(activity.getActivityId());
            maintenanceActivityDAO.addMaintenanceActivity(activity);
            verify(selectDefaultMaintenanceActivity(3), activity);
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
  //=========================================================================================================================================
   
    /**
     * Test of deleteMaintenanceActivity method, of class MaintenanceActivityDAOImpl.
     */
    
    @Test
    public void testDeleteMaintenanceActivity() throws MaintenanceActivityException, SQLException {
        deleteMaintenaceActivity(1);
        insertMaintenanceActivity(1,"ProvaDescrizione1",121,"2050-11-21",true, 
                    "Planned",null,"ProvaTypologyName1","ProvaBranch1","ProvaArea1",null);
        boolean result = maintenanceActivityDAO.deleteMaintenanceActivity(1);
        assertEquals(true, result);    
    }
   
    
    @Test
    public void testDeleteMaintenanceActivityWithWrongId() throws MaintenanceActivityException, SQLException {
        deleteMaintenaceActivity(2);
        boolean result = maintenanceActivityDAO.deleteMaintenanceActivity(2);
        assertEquals(false, result);
    }
    
    @Test
    public void testDeleteMaintenanceActivityEwo() throws MaintenanceActivityException, SQLException {
        deleteMaintenaceActivity(3);
        insertMaintenanceActivity(3,"ProvaDescrizione3",123,"2050-11-23",false, 
                    "Unplanned","EWO","ProvaTypologyName3","ProvaBranch3","ProvaArea3",null);
        boolean result = maintenanceActivityDAO.deleteMaintenanceActivity(3);
        assertEquals(true, result);    
    }
    
    
    @Test
    public void testDeleteMaintenanceActivityExtraActivity() throws MaintenanceActivityException, SQLException {
        deleteMaintenaceActivity(4);
        insertMaintenanceActivity(4,"ProvaDescrizione4",124,"2050-11-24",false, 
                    "Unplanned","Extra","ProvaTypologyName4","ProvaBranch4","ProvaArea4",null);
        boolean result = maintenanceActivityDAO.deleteMaintenanceActivity(4);
        assertEquals(true, result);    
    }
    
    //================================================test retrieveMaintenanceActivitu=========================================================================================
    
    /**
     * This method asserts that retrieveMaintenanceFromRangeOneElement correctly returns list of MaintenanceActivity objects
     */
    @Test
    public void testRetrieveMaintenanceActivityFromRangeEqualsStartDateAndStopDate(){
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(LocalDate.parse("2050-11-21"),
                    LocalDate.parse("2050-11-21"));
            assertEquals("Len resultList error",resultList.size(),1);
            assertMaintenanceActivity(resultList.get(0),1,"ProvaDescrizione1",121,"2050-11-21",true, 
                    "Planned",null,"ProvaTypologyName1","ProvaBranch1","ProvaArea1", "ProvaWorkSpaceNotes1","smp1");
        }catch(MaintenanceActivityException ex){
            fail("MaterialException was thrown");
        } catch (SiteException ex) {
            fail("SiteException");
        } catch (DateException ex) {
            fail("DateException");
        }    
    }
    
    /**
     * This method asserts that retrieveMaintenanceFromRangeMoreElement correctly returns list of MaintenanceActivity objects
     */
    @Test
    public void testRetrieveMaintenanceActivityFromRangeDifferentStartDateAndStopDate(){
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(LocalDate.parse("2050-11-20"),
                    LocalDate.parse("2050-11-23"));
            assertEquals("Len resultList error",resultList.size(),3);
            Collections.sort(resultList);
            assertMaintenanceActivity(resultList.get(0),1,"ProvaDescrizione1",121,"2050-11-21",true, 
                    "Planned",null,"ProvaTypologyName1","ProvaBranch1","ProvaArea1", "ProvaWorkSpaceNotes1","smp1");
            assertMaintenanceActivity(resultList.get(1),2,"ProvaDescrizione2",122,"2050-11-22",true, 
                    "Unplanned","EWO","ProvaTypologyName2","ProvaBranch2","ProvaArea2", "ProvaWorkSpaceNotes2","smp2");
            assertMaintenanceActivity(resultList.get(2),3,"ProvaDescrizione3",123,"2050-11-23",false, 
                    "Unplanned","Extra","ProvaTypologyName3","ProvaBranch3","ProvaArea3", "ProvaWorkSpaceNotes3","smp3");
        }catch(MaintenanceActivityException ex){
            fail("MaintenanceActivityException was thrown");
        }catch (SiteException ex) {
            fail("SiteException");
        } catch (DateException ex) {
            fail("DateException");
        } 
        
    }
    
    /**
     * This method asserts that retrieveMaintenanceFromRangeMoreElement correctly returns Empty list
     */
    @Test
    public void testRetrieveMaintenanceActivityFromRangeEmpty(){
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(LocalDate.parse("2050-11-15"),
                    LocalDate.parse("2050-11-20"));
            Collections.sort(resultList);
            assertTrue(resultList.isEmpty());
        }catch(MaintenanceActivityException ex){
            fail("MaintenanceActivityException was thrown");
        } catch (SiteException ex) {
            fail("SiteException");
        } catch (DateException ex) {
            fail("DateException");
        }     
    }
    
    /**
     * This method asserts that retrieveMaintenanceFromRangeMoreElement correctly raises MaintenanceActivityException
     */
    @Test(expected = DateException.class)
    public void testRetrieveMaintenanceActivityFromRangeStartGreaterThanStop() throws DateException{
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(LocalDate.parse("2050-11-23"),
                    LocalDate.parse("2050-11-21"));
        } catch (SiteException ex) {
            fail("SiteException");
        }catch(MaintenanceActivityException ex){
            fail("MaintenanceActivityException was thrown");
        } 
    }
    
    /**
     * This method asserts that retrieveMaintenanceFromRangeMoreElement correctly raises MaintenanceActivityException
     */
    @Test(expected = DateException.class)
    public void testRetrieveMaintenanceActivityFromRangeStartNull() throws DateException{
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(null,
                    LocalDate.parse("2050-11-21"));
        } catch (SiteException ex) {
            fail("SiteException");
        }catch(MaintenanceActivityException ex){
            fail("MaintenanceActivityException was thrown");
        } 
    }
    
    /**
     * This method asserts that retrieveMaintenanceFromRangeMoreElement correctly raises MaintenanceActivityException
     */
    @Test(expected = DateException.class)
    public void testRetrieveMaintenanceActivityFromRangeStopNull() throws DateException{
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(LocalDate.parse("2050-11-23"),
                    null);
        } catch (SiteException ex) {
            fail("SiteException");
        }catch(MaintenanceActivityException ex){
            fail("MaintenanceActivityException was thrown");
        } 
    }
    
    /**
     * This method asserts that retrieveMaintenanceFromRangeMoreElement correctly raises SiteException
     */
    @Test(expected = SiteException.class)
    public void testRetrieveMaintenanceActivityFromRangeSiteException() throws SiteException{
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(LocalDate.parse("2050-11-21"),
                    LocalDate.parse("2050-11-28"));
        }catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException was thrown");
        }   catch (DateException ex) {
            fail("DateException");
        }   
    }
    
    private void initializeRange(){
        try {
            deleteMaintenaceActivity(1);
            deleteMaintenaceActivity(2);
            deleteMaintenaceActivity(3);
            deleteMaintenaceActivity(5);
            insertMaintenanceActivity(1, "ProvaDescrizione1", 121, "2050-11-21", true,
                    "Planned",null, "ProvaTypologyName1", "ProvaBranch1", "ProvaArea1","smp1");
            insertMaintenanceActivity(2, "ProvaDescrizione2", 122, "2050-11-22", true,
                    "Unplanned", "EWO", "ProvaTypologyName2", "ProvaBranch2", "ProvaArea2","smp2");
            insertMaintenanceActivity(3, "ProvaDescrizione3", 123, "2050-11-23", false,
                    "Unplanned", "Extra", "ProvaTypologyName3", "ProvaBranch3", "ProvaArea3","smp3");
            insertMaintenanceActivity(5, "ProvaDescrizione5", 125, "2050-11-25", false,
                    "Unplanned", "Extra", "ProvaTypologyName5", "ProvaBranch5", "ProvaArea5","smp5");
        } catch(SQLException ex){
            fail("SQLException was thrown");
        }
    }
}
