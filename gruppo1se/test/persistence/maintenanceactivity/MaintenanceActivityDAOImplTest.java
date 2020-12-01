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
import stub.MaintenanceActivityDAOStub;
import stub.SiteDaoStub;

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
            MaintenanceActivityDAO maintenanceActivityDAO = new MaintenanceActivityDAOImpl(new SiteDaoStub());
            deleteMaintenaceActivity(1);
            insertMaintenanceActivity(1,"ProvaDescrizione1",121,"2050-11-21",true, 
                    "Planned",null,"ProvaTypologyName1","ProvaBranch1","ProvaArea1");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(1);
            assertMaintenanceActivity(activity,1,"ProvaDescrizione1",121,"2050-11-21",true, 
                    "Planned",null,"ProvaTypologyName1","ProvaBranch1","ProvaArea1", "ProvaWorkSpaceNotes1");
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
            MaintenanceActivityDAO maintenanceActivityDAO = new MaintenanceActivityDAOImpl(new SiteDaoStub());
            deleteMaintenaceActivity(2);
            insertMaintenanceActivity(2,"ProvaDescrizione2",122,"2050-11-22",true, 
                    "Unplanned","EWO","ProvaTypologyName2","ProvaBranch2","ProvaArea2");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(2);
            assertMaintenanceActivity(activity,2,"ProvaDescrizione2",122,"2050-11-22",true, 
                    "Unplanned","EWO","ProvaTypologyName2","ProvaBranch2","ProvaArea2", "ProvaWorkSpaceNotes2");
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
            MaintenanceActivityDAO maintenanceActivityDAO = new MaintenanceActivityDAOImpl(new SiteDaoStub());
            deleteMaintenaceActivity(3);
            insertMaintenanceActivity(3,"ProvaDescrizione3",123,"2050-11-23",false, 
                    "Unplanned","Extra","ProvaTypologyName3","ProvaBranch3","ProvaArea3");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(3);
            assertMaintenanceActivity(activity,3,"ProvaDescrizione3",123,"2050-11-23",false, 
                    "Unplanned","Extra","ProvaTypologyName3","ProvaBranch3","ProvaArea3", "ProvaWorkSpaceNotes3");
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
            MaintenanceActivityDAO maintenanceActivityDAO = new MaintenanceActivityDAOImpl(new SiteDaoStub());
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
            MaintenanceActivityDAO maintenanceActivityDAO = new MaintenanceActivityDAOImpl(new SiteDaoStub());
            deleteMaintenaceActivity(4);
            insertMaintenanceActivity(4,"ProvaDescrizione4",124,"2050-11-24",false, 
                    "Unplanned","Extra","ProvaTypologyName4","ProvaBranch4","ProvaArea4");
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
            MaintenanceActivityDAO maintenanceActivityDAO = new MaintenanceActivityDAOImpl(new SiteDaoStub());
            deleteMaintenaceActivity(5);
            insertMaintenanceActivity(5,"ProvaDescrizione5",125,"2050-11-25",false, 
                    "Unplanned","Extra","ProvaTypologyName5","ProvaBranch5","ProvaArea5");
            MaintenanceActivity activity = maintenanceActivityDAO.retrieveMaintenanceActivityDao(5);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (MaintenanceActivityException ex) {
            fail("MaintenanceActivityException");
        } 
    }
    
    //=======================Utilities to test retrieveMaintenanceActivityDao=========================================
    
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

            deleteDefaultMaintenanceActivity(stm,1);
            insertDefaultMaintenanceActivity("Planned", "null", stm, 1);            
            MaintenanceActivity newActivity = createMaintenanceActivity("EWO", 1);
            assertEquals(true, instance.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(stm,1), newActivity);
            
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
        
            deleteDefaultMaintenanceActivity(stm,2);
            insertDefaultMaintenanceActivity("Planned", "null", stm, 2);            
            MaintenanceActivity newActivity = createMaintenanceActivity("Extra", 2);
            assertEquals(true, instance.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(stm,2), newActivity);
           
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
            deleteDefaultMaintenanceActivity(stm,3);
            insertDefaultMaintenanceActivity("Unplanned", "'EWO'", stm, 3);
            MaintenanceActivity newActivity = createMaintenanceActivity("Extra", 3);
            assertEquals(true, instance.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(stm,3), newActivity);
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
            deleteDefaultMaintenanceActivity(stm,4);
            insertDefaultMaintenanceActivity("Unplanned", "'EWO'", stm, 4);
            MaintenanceActivity newActivity = createMaintenanceActivity("Planned", 4);
            assertEquals(true, instance.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(stm,4), newActivity);
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
            deleteDefaultMaintenanceActivity(stm,5);
            insertDefaultMaintenanceActivity("Unplanned", "'Extra'", stm, 5);
            MaintenanceActivity newActivity = createMaintenanceActivity("Planned", 5);
            assertEquals(true, instance.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(stm,5), newActivity);
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
            deleteDefaultMaintenanceActivity(stm,6);
            insertDefaultMaintenanceActivity("Unplanned", "'Extra'", stm, 6);
            MaintenanceActivity newActivity = createMaintenanceActivity("EWO", 6);
            assertEquals(true, instance.modifyMaintenaceActivity(newActivity));
            verify(selectDefaultMaintenanceActivity(stm,6), newActivity);
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
            deleteDefaultMaintenanceActivity(stm,7);
            MaintenanceActivity newActivity = createMaintenanceActivity("EWO", 7);
            assertEquals(false, instance.modifyMaintenaceActivity(newActivity));
        }catch(SQLException ex){
            fail("SQLException was thrown");
        }catch(MaintenanceActivityException ex){
            fail("MaterialException was thrown");
        }   
    }
    
    //=================== PRIVATE METHODS OF MODIFY ==============================================================================
    
    private void insertDefaultMaintenanceActivity(String typologyOfActivity, String typologyOfUnplannedActivity, Statement stm, int id) throws SQLException {
        String query = "INSERT INTO MaintenanceActivity (activityId, activityDescription, "
                + "estimatedInterventionTime, dateActivity, interruptibleActivity, typologyOfActivity,"
                + " typologyOfUnplannedActivity, typologyName, branchOffice, area) "
                + "VALUES (" + id + ", 'DefaultDescrizione', 1, '2100-1-1', True, '"+ typologyOfActivity +"',"
                + " "+ typologyOfUnplannedActivity +", 'DefaultTypologyName', 'DefaultBranchOffice', 'DefaultArea')";
        stm.executeUpdate(query);
    }
    
    private void deleteDefaultMaintenanceActivity(Statement stm, int id) throws SQLException {
        String query = "DELETE FROM MaintenanceActivity WHERE activityId="+id;
        stm.executeUpdate(query);
    }
    
    private ResultSet selectDefaultMaintenanceActivity(Statement stm, int id) throws SQLException {
        String query = "SELECT * FROM MaintenanceActivity WHERE activityId="+id;
        return stm.executeQuery(query);
    }
    
    private void verify(ResultSet res, MaintenanceActivity newActivity) throws SQLException {
        assertNotNull(res);
        while(res.next()){
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
    
  //=======================================================================================================================================
    
    /**
     * Test of addMaintenanceActivity method, of class MaintenanceActivityDAO.
     */
    /**
     * Test of addMaintenanceActivity method, of class MaintenanceActivityDAOImpl.
     */
    @Test
    public void testAddMaintenanceActivity() throws MaintenanceActivityException {
        try {
            System.out.println("addMaintenanceActivity");
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(1, site, typology, activityDescription, 300, LocalDate.of(2050, 11, 25), maintenanceProcedure, materials, false);
            Statement stmt = conn.createStatement();
            deleteDefaultMaintenanceActivity(stmt, activity.getActivityId());
            instance.addMaintenanceActivity(activity);
            verify(selectDefaultMaintenanceActivity(stmt,1), activity);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test(expected = MaintenanceActivityException.class)
    public void testAddMaintenanceActivityWrongDate() throws MaintenanceActivityException {
        try {
            System.out.println("addMaintenanceActivityWrongDate");
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(2, site, typology, activityDescription, 300, LocalDate.of(2020,11,24), maintenanceProcedure, materials, false);
            Statement stmt = conn.createStatement();
            deleteDefaultMaintenanceActivity(stmt, activity.getActivityId());
            instance.addMaintenanceActivity(activity);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test(expected = MaintenanceActivityException.class)
    public void testAddMaintenanceActivityWrongActivityID() throws MaintenanceActivityException {
        try {
            System.out.println("addMaintenanceActivityWrongActivityID");
            PlannedMaintenanceActivity activity = new PlannedMaintenanceActivity(0, site, typology, activityDescription, 300, LocalDate.of(2050, 11, 25), maintenanceProcedure, materials, false);
            Statement stmt = conn.createStatement();
            deleteDefaultMaintenanceActivity(stmt, activity.getActivityId());
            instance.addMaintenanceActivity(activity);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    @Test
    public void testAddMaintenanceActivityUnplanned() throws MaintenanceActivityException {
        try {
            System.out.println("addMaintenanceActivityUnplanned");
            Ewo activity = new Ewo(3, site, typology, activityDescription, 300, LocalDate.of(2050, 11, 25), maintenanceProcedure, materials, false);
            Statement stmt = conn.createStatement();
            deleteDefaultMaintenanceActivity(stmt, activity.getActivityId());
            //instance.deleteMaintenanceActivity(activity.getActivityId());
            instance.addMaintenanceActivity(activity);
            verify(selectDefaultMaintenanceActivity(stmt,3), activity);
            conn.rollback();
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
        System.out.println("deleteMaintenanceActivity");
        deleteDefaultMaintenanceActivity(conn.createStatement(),1);
        insertDefaultMaintenanceActivity("Planned",null,conn.createStatement(),1);
        boolean result = instance.deleteMaintenanceActivity(1);
        assertEquals(true, result);    
    }
   
    
    @Test
    public void testDeleteMaintenanceActivityWithWrongId() throws MaintenanceActivityException, SQLException {
        deleteDefaultMaintenanceActivity(conn.createStatement(),2);
        boolean result = instance.deleteMaintenanceActivity(2);
        assertEquals(false, result);
    }
    
    @Test
    public void testDeleteMaintenanceActivityEwo() throws MaintenanceActivityException, SQLException {
        deleteDefaultMaintenanceActivity(conn.createStatement(),3);
        insertDefaultMaintenanceActivity("Unplanned","'EWO'",conn.createStatement(),3);
        boolean result = instance.deleteMaintenanceActivity(3);
        assertEquals(true, result);    
    }
    
    
    @Test
    public void testDeleteMaintenanceActivityExtraActivity() throws MaintenanceActivityException, SQLException {
        deleteDefaultMaintenanceActivity(conn.createStatement(),1);
        insertDefaultMaintenanceActivity("Unplanned","'Extra'",conn.createStatement(),4);
        boolean result = instance.deleteMaintenanceActivity(4);
        assertEquals(true, result);    
    }
}
