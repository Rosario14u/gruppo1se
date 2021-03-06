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
import exception.MaintenanceActivityException;
import exception.NotValidParameterException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    private final String typology;
    private final MaintenanceActivityDAOImpl maintenanceActivityDAO;

    
    public MaintenanceActivityDAOImplTest() throws NotValidParameterException {
        site = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes");
        activityDescription = "ProvaActivityDescription";
        maintenanceProcedure = new MaintenanceProcedure("ProvaPDF");
        materials = new ArrayList<>();
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
                    "Planned",null,"ProvaTypologyName1","ProvaBranch","ProvaArea","smp1");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(1);
            assertMaintenanceActivity(activity,1,"ProvaDescrizione1",121,LocalDate.parse("2050-11-21"),true, 
                    "Planned",null,"ProvaTypologyName1","ProvaBranch","ProvaArea", "ProvaWorkSpaceNotes","smp1");
        }catch(SQLException | MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
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
                    "Unplanned","EWO","ProvaTypologyName2","ProvaBranch","ProvaArea","smp2");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(2);
            assertMaintenanceActivity(activity,2,"ProvaDescrizione2",122,LocalDate.parse("2050-11-22"),true, 
                    "Unplanned","EWO","ProvaTypologyName2","ProvaBranch","ProvaArea", "ProvaWorkSpaceNotes","smp2");
        }catch(SQLException | MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
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
                    "Unplanned","Extra","ProvaTypologyName3","ProvaBranch","ProvaArea","smp3");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(3);
            assertMaintenanceActivity(activity,3,"ProvaDescrizione3",123,LocalDate.parse("2050-11-23"),false, 
                    "Unplanned","Extra","ProvaTypologyName3","ProvaBranch","ProvaArea", "ProvaWorkSpaceNotes","smp3");
        }catch(SQLException | MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
   /**
     * This method asserts that retrieveMaintenanceActivityDao correctly returns null when the required<br>
     * maintenance activity is not in the database.
     */
    @Test
    public void testRetrieveMaintenanceActivityDaoNotInDatabase() {
        try {
            deleteMaintenaceActivity(1);
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(1);
            assertNull("MaintenanceActivityImpl error", activity);
        }catch(SQLException | MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }   
    }
    
    /**
     * This method asserts that retrieveMaintenanceActivityDao correctly raises a MaintenanceActivityException <br>
     * when SiteDao return Null.
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testRetrieveMaintenanceActivityDaoSiteExceptionCase1() throws MaintenanceActivityException  {
        try {
            deleteMaintenaceActivity(4);
            insertMaintenanceActivity(4,"ProvaDescrizione4",124,"2050-11-24",false, 
                    "Unplanned","Extra","ProvaTypologyName4","ProvaBranch4","ProvaArea4","smp4");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(4);
        }catch(SQLException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This method asserts that retrieveMaintenanceActivityDao correctly raises a MaintenanceActivityException <br>
     * when SiteDao raises an exception.
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testRetrieveMaintenanceActivityDaoSiteExceptionCase2() throws MaintenanceActivityException  {
        try {
            deleteMaintenaceActivity(5);
            insertMaintenanceActivity(5,"ProvaDescrizione5",125,"2050-11-25",false, 
                    "Unplanned","Extra","ProvaTypologyName5","ProvaBranch4","ProvaArea4", "smp5");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(5);
        }catch(SQLException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    
    //========================================= MODIFY ==================================================
    //=========== Test methods of modifyMaintenaceActivity developed by Antonio Gorrasi =================
    
    /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates 
     * Planned Maintenance activity to Unplanned EWO Maintenance activity
     */
    @Test
    public void testModifyPlannedToEwoMaintenaceActivity() {
        try{
            deleteMaintenaceActivity(1);
            insertMaintenanceActivity(1,"ProvaDescrizione1",121,"2050-11-21",true, 
                    "Planned",null,"ProvaTypologyName1","ProvaBranch1","ProvaArea1","smp1");          
            MaintenanceActivity newActivity = createMaintenanceActivity("EWO", 1);
            System.out.println(newActivity.toString());
            assertTrue(maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(1), newActivity);
        }catch(SQLException | MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates 
     * Planned Maintenance activity to Unplanned Extra Maintenance activity
     */
    @Test
    public void testModifyPlannedToExtraMaintenaceActivity(){
        try{
            deleteMaintenaceActivity(2);
            insertMaintenanceActivity(2,"ProvaDescrizione2",122,"2050-11-22",true, 
                    "Planned",null,"ProvaTypologyName2","ProvaBranch2","ProvaArea2",null);           
            MaintenanceActivity newActivity = createMaintenanceActivity("Extra", 2);
            assertTrue(maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(2), newActivity);
        }catch(SQLException | MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }

    
    /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates 
     * Unplanned EWO Maintenance activity to Unplanned Extra Maintenance activity
     */
    @Test
    public void testModifyEwoToExtraMaintenaceActivity(){
        try{
            deleteMaintenaceActivity(3);
            insertMaintenanceActivity(3,"ProvaDescrizione3",123,"2050-11-23",true, 
                    "Unplanned","EWO","ProvaTypologyName3","ProvaBranch3","ProvaArea3",null);
            MaintenanceActivity newActivity = createMaintenanceActivity("Extra", 3);
            assertTrue(maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(3), newActivity);
        }catch(SQLException | MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates 
     * Unplanned EWO Maintenance activity to Planned Maintenance activity
     */
    @Test
    public void testModifyEwoToPlannedMaintenaceActivity(){
        try{
            deleteMaintenaceActivity(4);
            insertMaintenanceActivity(4,"ProvaDescrizione4",124,"2050-11-24",false, 
                    "Unplanned","EWO","ProvaTypologyName4","ProvaBranch4","ProvaArea4",null);
            MaintenanceActivity newActivity = createMaintenanceActivity("Planned", 4);
            assertTrue(maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(4), newActivity);
        }catch(SQLException | MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
  
    
    /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates 
     * Unplanned Extra Maintenance activity to Planned Maintenance activity
     */
    @Test
    public void testModifyExtraToPlannedMaintenaceActivity(){
        try{
            deleteMaintenaceActivity(5);
            insertMaintenanceActivity(5,"ProvaDescrizione5",125,"2050-11-25",false, 
                    "Unplanned","Extra","ProvaTypologyName5","ProvaBranch5","ProvaArea5",null);
            MaintenanceActivity newActivity = createMaintenanceActivity("Planned", 5);
            assertTrue(maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(5), newActivity);
        }catch(SQLException | MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        }
    }
    
    
    /**
     * Test of modifyMaintenaceActivity method, of class MaintenanceActivityDao.<br>
     * This method assert that modifyMaintenaceActivity method correctly updates 
     * Unplanned Extra Maintenance activity to Unplanned EWO Maintenance activity
     */
    @Test
    public void testModifyExtraToEwoMaintenaceActivity(){
        try{
            deleteMaintenaceActivity(6);
            insertMaintenanceActivity(6,"ProvaDescrizione6",126,"2050-11-26",false, 
                    "Unplanned","Extra","ProvaTypologyName6","ProvaBranch6","ProvaArea6",null);
            MaintenanceActivity newActivity = createMaintenanceActivity("EWO", 6);
            assertTrue(maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(6), newActivity);
        }catch(SQLException | MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
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
            deleteMaintenaceActivity(7);
            MaintenanceActivity newActivity = createMaintenanceActivity("EWO", 7);
            assertFalse(maintenanceActivityDAO.modifyMaintenaceActivity(newActivity));
        }catch(SQLException | MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
    //=================== PRIVATE METHODS OF MODIFY ==============================================================================
    
    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
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
    
     public MaintenanceActivity createMaintenanceActivity(String typeOfActivity, int id) throws NotValidParameterException {
        if(typeOfActivity.equals("Planned")){
            return new PlannedMaintenanceActivity(id, new Site("ProvaBranchOfficeMod"+id, "ProvaAreaMod"+id), "typologyNameMod"+id,
                    "ProvaDescrizioneMod"+id, id, LocalDate.now(), null, null, id%2==0);
        }else if(typeOfActivity.equals("EWO")){
            return new Ewo(id, new Site("ProvaBranchOfficeMod"+id, "ProvaAreaMod"+id), "typologyNameMod"+id,
                    "ProvaDescrizioneMod"+id, id, LocalDate.now(), null, null, id%2==0);
        }else{
            return new ExtraActivity(id, new Site("ProvaBranchOfficeMod"+id, "ProvaAreaMod"+id), "typologyNameMod"+id,
                    "ProvaDescrizioneMod"+id, id, LocalDate.now(), null, null, id%2==0);
        } 
    }
    
//================================================Test of addMaintenanceActivity=======================================================================================
    /*Developed by Alessio Citro*/

    /**
     * Test of addMaintenanceActivity method, of class MaintenanceActivityDAOImpl.
     * @throws exception.MaintenanceActivityException
     */
    @Test
    public void testAddMaintenanceActivity() throws MaintenanceActivityException {
        try {
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(1, site, typology,
                    activityDescription, 300, LocalDate.of(2050, 11, 25), maintenanceProcedure, materials, false);
            deleteMaintenaceActivity(activity.getActivityId());
            maintenanceActivityDAO.addMaintenanceActivity(activity);
            verify(selectDefaultMaintenanceActivity(1), activity);
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        } catch (NotValidParameterException ex) {
           fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of addMaintenanceActivity method, of class MaintenanceActivityDAOImpl.
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testAddMaintenanceActivityWrongDate() throws MaintenanceActivityException {
        try {
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(2, site, typology,
                    activityDescription, 300, LocalDate.of(2020,11,24), maintenanceProcedure, materials, false);
            deleteMaintenaceActivity(activity.getActivityId());
            maintenanceActivityDAO.addMaintenanceActivity(activity);
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        } catch (NotValidParameterException ex) {
           fail("NotValidParameterException");
        }
    }

    /**
     * Test of addMaintenanceActivity method, of class MaintenanceActivityDAOImpl.
     * @throws exception.MaintenanceActivityException
     */
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
        } catch (NotValidParameterException ex) {
           fail("NotValidParameterException");
        }
    }
    
  //===================================== Test of deleteMaintenanceActivity ==========================================
    
    /**
     * Test of deleteMaintenanceActivity method, of class MaintenanceActivityDAOImpl.
     * @throws exception.MaintenanceActivityException
     * @throws java.sql.SQLException
     */
    @Test
    public void testDeleteMaintenanceActivity() throws MaintenanceActivityException, SQLException {
        try {
            deleteMaintenaceActivity(1); 
            insertMaintenanceActivity(1,"ProvaDescrizione1",121,"2050-11-21",true,
                    "Planned",null,"ProvaTypologyName1","ProvaBranch1","ProvaArea1",null);
            boolean result = maintenanceActivityDAO.deleteMaintenanceActivity(1);
            assertEquals(true, result);    
        } catch (NotValidParameterException ex) {
           fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of deleteMaintenanceActivity method, of class MaintenanceActivityDAOImpl.
     * @throws exception.MaintenanceActivityException
     * @throws java.sql.SQLException
     */   
    @Test
    public void testDeleteMaintenanceActivityWithWrongId() throws MaintenanceActivityException, SQLException {
        try {
            deleteMaintenaceActivity(2);
            boolean result = maintenanceActivityDAO.deleteMaintenanceActivity(2);
            assertEquals(false, result);
        } catch (NotValidParameterException ex) {
           fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of deleteMaintenanceActivity method, of class MaintenanceActivityDAOImpl.
     * @throws exception.MaintenanceActivityException
     * @throws java.sql.SQLException
     */   
    @Test
    public void testDeleteMaintenanceActivityEwo() throws MaintenanceActivityException, SQLException {
        try {
            deleteMaintenaceActivity(3); 
            insertMaintenanceActivity(3,"ProvaDescrizione3",123,"2050-11-23",false,
                    "Unplanned","EWO","ProvaTypologyName3","ProvaBranch3","ProvaArea3",null);
            boolean result = maintenanceActivityDAO.deleteMaintenanceActivity(3);
            assertEquals(true, result);    
        } catch (NotValidParameterException ex) {
           fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of deleteMaintenanceActivity method, of class MaintenanceActivityDAOImpl.
     * @throws exception.MaintenanceActivityException
     * @throws java.sql.SQLException
     */   
    @Test
    public void testDeleteMaintenanceActivityExtraActivity() throws MaintenanceActivityException, SQLException {
        try {
            deleteMaintenaceActivity(4); 
            insertMaintenanceActivity(4,"ProvaDescrizione4",124,"2050-11-24",false,
                    "Unplanned","Extra","ProvaTypologyName4","ProvaBranch4","ProvaArea4",null);
            boolean result = maintenanceActivityDAO.deleteMaintenanceActivity(4);
            assertEquals(true, result);    
        } catch (NotValidParameterException ex) {
           fail("NotValidParameterException");
        }
    }
    
    //================================================test retrieveMaintenanceActivity=========================================================================================
    
    /* test of retrieveMaintenanceActivity developed by Rosario Gaeta*/
    
    /**
     * This method asserts that retrieveMaintenanceFromRange correctly<br>
     * returns list of MaintenanceActivity objects with equals start and end date.
     */
    @Test
    public void testRetrieveMaintenanceActivityFromRangeEqualsStartDateAndStopDate(){
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(LocalDate.parse("2050-11-21"),
                    LocalDate.parse("2050-11-21"));
            assertEquals("Len resultList error",resultList.size(),1);
            assertMaintenanceActivity(resultList.get(0),1,"ProvaDescrizione1",121,LocalDate.parse("2050-11-21"),true, 
                    "Planned",null,"ProvaTypologyName1","ProvaBranch","ProvaArea", "ProvaWorkSpaceNotes","smp1");
        }catch(MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        } 
    }
    
    /**
     * This method asserts that retrieveMaintenanceFromRange correctly<br>
     * returns list of MaintenanceActivity objects with different start date and end date.
     */
    @Test
    public void testRetrieveMaintenanceActivityFromRangeDifferentStartDateAndStopDate(){
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(LocalDate.parse("2050-11-20"),
                    LocalDate.parse("2050-11-23"));
            assertEquals("Len resultList error",resultList.size(),3);
            Collections.sort(resultList);
            assertMaintenanceActivity(resultList.get(0),1,"ProvaDescrizione1",121,LocalDate.parse("2050-11-21"),true, 
                    "Planned",null,"ProvaTypologyName1","ProvaBranch","ProvaArea", "ProvaWorkSpaceNotes","smp1");
            assertMaintenanceActivity(resultList.get(1),2,"ProvaDescrizione2",122,LocalDate.parse("2050-11-22"),true, 
                    "Unplanned","EWO","ProvaTypologyName2","ProvaBranch","ProvaArea", "ProvaWorkSpaceNotes","smp2");
            assertMaintenanceActivity(resultList.get(2),3,"ProvaDescrizione3",123,LocalDate.parse("2050-11-23"),false, 
                    "Unplanned","Extra","ProvaTypologyName3","ProvaBranch","ProvaArea", "ProvaWorkSpaceNotes","smp3");
        }catch(MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
        
    }
    
    /**
     * This method asserts that retrieveMaintenanceFromRange correctly returns Empty list when<br>
     * there aren't activity in the database with the date in that range.
     */
    @Test
    public void testRetrieveMaintenanceActivityFromRangeEmpty(){
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(LocalDate.parse("2050-11-15"),
                    LocalDate.parse("2050-11-20"));
            Collections.sort(resultList);
            assertTrue(resultList.isEmpty());
        }catch(MaintenanceActivityException | NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }   
    }
    
    /**
     * This method asserts that retrieveMaintenanceFromRangeMoreElement correctly<br>
     * raises a MaintenanceActivityException when start date is after end date.
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testRetrieveMaintenanceActivityFromRangeStartGreaterThanStop() throws MaintenanceActivityException{
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(LocalDate.parse("2050-11-23"),
                    LocalDate.parse("2050-11-21"));
        }catch(NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This method asserts that retrieveMaintenanceFromRangeMoreElement correctly raises a MaintenanceActivityException<br>
     * when start date is null.
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testRetrieveMaintenanceActivityFromRangeStartNull() throws MaintenanceActivityException{
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(null,
                    LocalDate.parse("2050-11-21"));
        }catch(NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This method asserts that retrieveMaintenanceFromRangeMoreElement correctly raises a MaintenanceActivityException<br>
     * when end date is null.
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testRetrieveMaintenanceActivityFromRangeStopNull() throws  MaintenanceActivityException{
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(LocalDate.parse("2050-11-23"),
                    null);
        }catch(NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This method asserts that retrieveMaintenanceFromRangeMoreElement correctly raises MaintenanceActivityException<br>
     * when siteDao raises a SiteException..
     * @throws exception.MaintenanceActivityException
     */
    @Test(expected = MaintenanceActivityException.class)
    public void testRetrieveMaintenanceActivityFromRangeSiteException() throws MaintenanceActivityException{
        try {
            initializeRange();
            List<MaintenanceActivity> resultList = maintenanceActivityDAO.retrieveMaintenanceActivityFromRange(
                    LocalDate.parse("2050-11-21"), LocalDate.parse("2050-11-28"));
        }catch(NotValidParameterException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }   
    }
    
    private void initializeRange(){
        try {
            deleteMaintenaceActivity(1);
            deleteMaintenaceActivity(2);
            deleteMaintenaceActivity(3);
            deleteMaintenaceActivity(4);
            insertMaintenanceActivity(1, "ProvaDescrizione1", 121, "2050-11-21", true,
                    "Planned",null, "ProvaTypologyName1", "ProvaBranch", "ProvaArea","smp1");
            insertMaintenanceActivity(2, "ProvaDescrizione2", 122, "2050-11-22", true,
                    "Unplanned", "EWO", "ProvaTypologyName2", "ProvaBranch", "ProvaArea","smp2");
            insertMaintenanceActivity(3, "ProvaDescrizione3", 123, "2050-11-23", false,
                    "Unplanned", "Extra", "ProvaTypologyName3", "ProvaBranch", "ProvaArea","smp3");
            insertMaintenanceActivity(4, "ProvaDescrizione4", 124, "2050-11-24", false,
                    "Unplanned", "Extra", "ProvaTypologyName4", "ProvaBranch4", "ProvaArea4","smp4");
        }catch(SQLException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    
    
    
    //============================= Testing utilities ============================================
    
    /**
     * This method delete an activity from database
     * @param activityId
     * @throws SQLException 
     */
    private void deleteMaintenaceActivity(int activityId) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(DELETE_MAINTENANCE_ACTIVITY);
        pstm.setInt(1, activityId);
        pstm.executeUpdate();
    }
    
    /**
     * This method insert an activity with a series of parameters in database
     * @param activityId
     * @param descrizione
     * @param estimatedInterventionTime
     * @param date
     * @param InterruptibleActivity
     * @param typologyOfActivity
     * @param typologyOfUnplannedActivity
     * @param typologyName
     * @param branchOffice
     * @param area
     * @param smp
     * @throws SQLException 
     */
    private void insertMaintenanceActivity(int activityId, String descrizione, int estimatedInterventionTime,
        String date, boolean InterruptibleActivity, String typologyOfActivity, String typologyOfUnplannedActivity,
        String typologyName, String branchOffice, String area, String smp) throws SQLException{
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
    
    /**
     * This method performs the assert of activity attributes with a series of parameters
     * @param activity
     * @param activityId
     * @param descrizione
     * @param estimatedInterventionTime
     * @param date
     * @param InterruptibleActivity
     * @param typologyOfActivity
     * @param typologyOfUnplannedActivity
     * @param typologyName
     * @param branchOffice
     * @param area
     * @param workSpaceNotes
     * @param smp 
     */
    private void assertMaintenanceActivity(MaintenanceActivity activity, 
        int activityId,String descrizione,int estimatedInterventionTime,
        LocalDate date,boolean InterruptibleActivity, String typologyOfActivity,
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
            assertEquals("dateActivity error",date, activity.getDate());
            assertEquals("interruptibleActivity error",InterruptibleActivity, activity.isInterruptibleActivity());
            assertTrue("typologyOfActivity error", activityClass.isInstance(activity));
            assertEquals("typologyName error", typologyName, activity.getTypology());
            assertEquals("branchOffice error", branchOffice, activity.getSite().getBranchOffice());
            assertEquals("area error", area, activity.getSite().getArea());
            assertEquals("WorkSpaceNotes error", workSpaceNotes, activity.getSite().getWorkSpaceNotes());
            assertEquals("smp error", smp, activity.getMaintenanceProcedure().getSmp());
    }

}
