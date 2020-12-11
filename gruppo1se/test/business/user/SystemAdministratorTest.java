/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import exception.ProcedureException;
import exception.TypologyException;
import exception.UsersException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.maintenanceactivity.MaintenanceProcedureDAOImpl;
import persistence.user.MaintainerSkillDAOImpl;
import persistence.maintenanceactivity.TypologyDAOImpl;
import persistence.user.UsersDAOImpl;
import stub.EmployeeAppointmentDAOStub;
import stub.MaintenanceActivityDAOStub;
import stub.MaintenanceProcedureDAOStub;
import stub.RequiredMaterialForMaintenanceDAOStub;
import stub.RequiredSkillForMaintenanceDAOStub;
import stub.TypologyDAOStub;
import stub.UsersDAOStub;

/**
 *
 * @author rosar
 */
public class SystemAdministratorTest {
    private final SystemAdministrator admin;
    private final User planner;
    private final User maintainer;
    private final User systemAdministrator;
    private final SystemAdministrator instance = new SystemAdministrator("systemAdministratorUsername","systemAdministratorPassword",
            new MaintenanceProcedureDAOStub(),
            new UsersDAOStub(),new TypologyDAOStub());
    private final String username = "maintainerUsername"; 
    private final String password = "maintainerPassword";
    
    public SystemAdministratorTest() {
        admin = new SystemAdministrator("admin","admin",new MaintenanceProcedureDAOStub(),new UsersDAOStub(),new TypologyDAOStub());
        planner =  new Planner("newUsername", "newPassword", new MaintenanceActivityDAOStub(),
                new RequiredMaterialForMaintenanceDAOStub(), new UsersDAOStub(),
                new EmployeeAppointmentDAOStub(), new RequiredSkillForMaintenanceDAOStub(),new MaintainerSkillDAOImpl());
        maintainer = new Maintainer("newUsername", "newPassword");
        systemAdministrator = new SystemAdministrator("newUsername", "newPassword");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * this test assert that saveSmpProcedure correctly returns true when addSmp returns true because there is an insert
     * and there is a ridenomination
     */
    @Test
    public void testSaveSmpProcedureInsert() {
        try {
            boolean retResult = admin.saveSmpProcedure("ProvaSmp1","ProvaSmp1Rin");
            assertTrue("Procedure True", retResult);
        } catch (ProcedureException ex) {
            fail("Procedure Exception");
        }
    }
    
    /**
     * this test assert that saveSmpProcedure correctly returns true when addSmp returns true because there is an insert
     * and there isn't a ridenomination
     */
    @Test
    public void testSaveSmpProcedureInsertNotRename1() {
        try {
            boolean retResult = admin.saveSmpProcedure("ProvaSmp1"," ");
            assertTrue("Procedure True", retResult);
        } catch (ProcedureException ex) {
            fail("Procedure Exception");
        }
    }
    
    /**
     * this test assert that saveSmpProcedure correctly returns true when addSmp returns true because there is an insert
     * and there isn't a ridenomination
     */
    @Test
    public void testSaveSmpProcedureInsertNotRename2() {
        try {
            boolean retResult = admin.saveSmpProcedure("ProvaSmp1",null);
            assertTrue("Procedure True", retResult);
        } catch (ProcedureException ex) {
            fail("Procedure Exception");
        }
    }
    
    /**
     * this test assert that saveSmpProcedure correctly returns true when updateProcedure returns true because there is an update
     * and there is a ridenomination
     */
    @Test
    public void testSaveSmpProcedureUpdate() {
        try {
            boolean retResult = admin.saveSmpProcedure("ProvaSmp2","ProvaSmp2Rin");
            assertTrue("Procedure True", retResult);
        } catch (ProcedureException ex) {
            fail("Procedure Exception");
        }
    }
    
    /**
     * this test assert that saveSmpProcedure correctly raises exception when addSmp raises exception because the procedure
     * is already present
     * @throws exception.ProcedureException
     */
    @Test(expected = ProcedureException.class)
    public void testSaveSmpUpdateThrowException() throws ProcedureException {
        boolean retResult = admin.saveSmpProcedure("ProvaSmp3", " ");
        
    }
    
    /**
     * this test assert that saveSmpProcedure correctly raises exception when procedure passed is empty string
     * @throws exception.ProcedureException
     */
    @Test(expected = ProcedureException.class)
    public void testSaveSmpEmptyProcedure() throws ProcedureException {
        boolean retResult = admin.saveSmpProcedure(" ", " ");
        
    }
    
    /**
     * this test assert that saveSmpProcedure correctly raises exception when procedure passed is null
     * @throws exception.ProcedureException
     */
    @Test(expected = ProcedureException.class)
    public void testSaveSmpNullProcedure() throws ProcedureException {
        boolean retResult = admin.saveSmpProcedure(null, " ");
    }
    
//=========================================================================================================================================
    /**
     * Test of viewUser method, of class SystemAdministrator.
     * @throws exception.UsersException
     */
    
    @Test
    public void testViewUser() throws UsersException {
        System.out.println("viewUser");
        List<User> expected = new ArrayList<>();
        expected.add(new Maintainer("UserMaintainer","PwdMaintainer"));
        expected.add(new Planner("UserPlanner","PwdPlanner", new MaintenanceActivityDAOStub(),
                new RequiredMaterialForMaintenanceDAOStub(), new UsersDAOStub(),
                new EmployeeAppointmentDAOStub(), new RequiredSkillForMaintenanceDAOStub(),null));
        expected.add(new SystemAdministrator("UserSystemAdministrator","PwdSystemAdministrator",new MaintenanceProcedureDAOImpl(),new UsersDAOImpl(),new TypologyDAOImpl()));
        List<User> users = instance.viewUsers();
        assertEquals(true,expected.equals(users));
    }
    
    /**
     * Test of makeTypology method, of class SystemAdministrator.
     * @throws exception.TypologyException
     */
    
    @Test
    public void testMakeTypology() throws TypologyException{
       System.out.println("makeTypology");
       String typology = " ";
       boolean result = instance.makeTypology(typology);
       assertEquals(true,result);
    }
    
    @Test(expected = TypologyException.class)
    public void testMakeTypologyException() throws TypologyException{
       System.out.println("makeTypologyException");
       String typology = "exception";
       instance.makeTypology(typology);
    }
    
    
    @Test
    public void testMakeTypologyFalse() throws TypologyException{
       System.out.println("makeTypologyFalse");
       String typology = "false";
       boolean result = instance.makeTypology(typology);
       assertEquals(false,result);
    }
    
    /**
     * Test of readTypologies method, of class SystemAdministrator.
     * @throws exception.TypologyException
     */
    
    @Test 
    public void testReadTypologies() throws TypologyException{
        System.out.println("readTypologiesTest");
        List<String> expected = new ArrayList<>();
        expected.add("Typology1");
        expected.add("Typology2");
        expected.add("Typology3");
        List<String> typologies = instance.readTypologies();
        assertEquals(true,expected.equals(typologies));  
    }
    
    /**
     * Tests of updateTypology method, of class SystemAdministrator.
     * @throws exception.TypologyException
     */
    
    @Test 
    public void testUpdateTypology() throws TypologyException {
        System.out.println("correct updateTypologyTest");
        String oldTypology = "oldTypology";
        String newTypology = "newTypology";
        assertEquals(true,instance.updateTypology(oldTypology, newTypology));
    }
    
    @Test 
    public void testUpdateTypologyFalse() throws TypologyException {
        System.out.println("incorrect updateTypologyTest");
        String oldTypology = "oldTypology";
        String newTypology = "oldTypology";
        assertEquals(false,instance.updateTypology(oldTypology, newTypology));
    }
    
    @Test(expected = TypologyException.class) 
    public void testUpdateTypologyException() throws TypologyException {
        System.out.println("updateTypologyExceptionTest");
        String oldTypology = "oldTypology";
        String newTypology = "Typology Exception";
        instance.updateTypology(oldTypology, newTypology);
    }
//=========================================================================================================================================
    
    /**
     * Test of makeUser method, of class SystemAdministrator.
     * @throws exception.UsersException
     */
    @Test
    public void testMakeUser() throws UsersException {
        System.out.println("makeUserTest");
        boolean result = instance.makeUser(username, password, "Maintainer");
        assertEquals(true, result);
    }
    
    @Test(expected = UsersException.class)
    public void testMakeUserException() throws UsersException {
        System.out.println("makeUserExceptionTest");
        boolean result = instance.makeUser(username, null, "Maintainer");
    }
    
    
    
    //=========================================================================================================================================
    
    /**
     * this test assert that removeUsers correctly returns the number of deleted rows when addSmp returns true because there is an insert
     * and there is a ridenomination
     */
    @Test
    public void testRemoveUsers(){
        try {
            List<String> usernameList = new ArrayList<>() {{
                add("username1");
                add("username2");
                add("username3");
            }};
            
            int returnedNumberOfDeletedRow = admin.removeUsers(usernameList);
            assertEquals(returnedNumberOfDeletedRow,usernameList.size());
        } catch (UsersException ex) {
            fail("UsersException");
        }
    }

    
    
//=========================================================================================================================================

    @Test
    public void testModifyUserSuccessfulUpdateToPlanner(){
        try{
            assertTrue(admin.modifyUser("oldUsername1", planner));
        } catch(UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test
    public void testRemoveUsersZero(){
        try {
            List<String> usernameList = new ArrayList<>() {{
                add("username1");
            }};
            int returnedNumberOfDeletedRow = admin.removeUsers(usernameList);
            assertEquals(returnedNumberOfDeletedRow,0);
        } catch (UsersException ex) {
            
        }        
    }
    
    public void testModifyUserUnsuccessfulUpdateToPlanner(){
        try{
            assertFalse(admin.modifyUser("oldUsername2", planner));
        } catch(UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test
    public void testRemoveUsersIsEmpty(){
        try {
            List<String> usernameList = new ArrayList<>();
            int returnedNumberOfDeletedRow = admin.removeUsers(usernameList);
            assertEquals(returnedNumberOfDeletedRow,usernameList.size());
        } catch (UsersException ex) {
            fail("UsersException");
        }
    }
    
    @Test
    public void testRemoveUsersNull(){
        try {
            int returnedNumberOfDeletedRow = admin.removeUsers(null);
            assertEquals(returnedNumberOfDeletedRow,0);
        } catch (UsersException ex) {
            fail("UsersException");
        }
    }
    
    @Test(expected = UsersException.class)
    public void testRemoveUsersRaisesException() throws UsersException{
        List<String> usernameList = new ArrayList<>() {{
            add("username1");
            add("username2");
        }};
        int returnedNumberOfDeletedRow = admin.removeUsers(usernameList);
    }
    
    
    @Test(expected = UsersException.class)
    public void testModifyUserUpdateToPlannerException() throws UsersException{
        admin.modifyUser("oldUsername3", planner);
    }
    
    @Test
    public void testModifyUserSuccessfulUpdateToMaintainer(){
        try{
            assertTrue(admin.modifyUser("oldUsername1", maintainer));
        } catch(UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test
    public void testModifyUserUnsuccessfulUpdateToMaintainer(){
        try{
            assertFalse(admin.modifyUser("oldUsername2", maintainer));
        } catch(UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test(expected = UsersException.class)
    public void testModifyUserUpdateToMaintainerException() throws UsersException{
        admin.modifyUser("oldUsername3", maintainer);
    }
    
    @Test
    public void testModifyUserSuccessfulUpdateTo(){
        try{
            assertTrue(admin.modifyUser("oldUsername1", systemAdministrator));
        } catch(UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test
    public void testModifyUserUnsuccessfulUpdateToSystemAdministrator(){
        try{
            assertFalse(admin.modifyUser("oldUsername2", systemAdministrator));
        } catch(UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test(expected = UsersException.class)
    public void testModifyUserUpdateToSystemAdministratorException() throws UsersException{
        admin.modifyUser("oldUsername3 ", systemAdministrator);
    }
    
    @Test(expected = UsersException.class)
    public void testModifyUserOldUsernameNull() throws UsersException{
        admin.modifyUser("null", systemAdministrator);        
    }
    
    @Test(expected = UsersException.class)
    public void testModifyUserNewUsernameNull() throws UsersException {
        User administrator = new SystemAdministrator(null, "newPassword");        
        admin.modifyUser("oldUsername1", administrator);
    }
    
}
