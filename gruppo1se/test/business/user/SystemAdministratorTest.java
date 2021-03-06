/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.Site;
import dto.MaintainerDTO;
import dto.PlannerDTO;
import dto.SystemAdministratorDTO;
import dto.UserDTO;
import exception.NotValidParameterException;
import exception.ProcedureException;
import exception.SiteException;
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
import stub.MaintenanceProcedureDAOStub;
import stub.SiteDaoStub;
import stub.TypologyDAOStub;
import stub.UsersDAOStub;

/**
 *
 * @author rosar
 */
public class SystemAdministratorTest {
    private final SystemAdministrator admin;
    private final UserDTO planner;
    private final UserDTO maintainer;
    private final UserDTO systemAdministrator;
    private final SystemAdministrator instance = new SystemAdministrator("systemAdministratorUsername","systemAdministratorPassword",
            new MaintenanceProcedureDAOStub(),
            new UsersDAOStub(),new TypologyDAOStub(), new SiteDaoStub());
    private final String username = "maintainerUsername"; 
    private final String password = "maintainerPassword";
    
    public SystemAdministratorTest() throws NotValidParameterException {
        admin = new SystemAdministrator("admin","admin",new MaintenanceProcedureDAOStub(),new UsersDAOStub(),new TypologyDAOStub(), new SiteDaoStub());
        planner =  new PlannerDTO("newUsername", "newPassword");
        maintainer = new MaintainerDTO("newUsername", "newPassword");
        systemAdministrator = new SystemAdministratorDTO(username, password);
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
    
    /*=======================================================saveSmpProcedure Test=====================================================*/
    /*Test of saveSmpProcedure developed by Rosario Gaeta*/
    /**
     * this test assert that saveSmpProcedure correctly returns true when addSmp returns true because there is an insert<br>
     * and there is a ridenomination.
     */
    @Test
    public void testSaveSmpProcedureInsert() {
        try {
            boolean retResult = admin.saveSmpProcedure("ProvaSmp1","ProvaSmp1Rin");
            assertTrue("Procedure True", retResult);
        }catch(ProcedureException | NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * this test assert that saveSmpProcedure correctly returns true when addSmp returns true because there is an insert<br>
     * and there isn't a ridenomination.
     */
    @Test
    public void testSaveSmpProcedureInsertNotRename1() {
        try {
            boolean retResult = admin.saveSmpProcedure("ProvaSmp1"," ");
            assertTrue("Procedure True", retResult);
        }catch(ProcedureException | NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * this test assert that saveSmpProcedure correctly returns true when addSmp returns true because there is an insert<br>
     * and there isn't a ridenomination.
     */
    @Test
    public void testSaveSmpProcedureInsertNotRename2() {
        try {
            boolean retResult = admin.saveSmpProcedure("ProvaSmp1",null);
            assertTrue("Procedure True", retResult);
        }catch(ProcedureException | NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * this test assert that saveSmpProcedure correctly returns true when updateProcedure returns true because there is an update<br>
     * and there is a ridenomination.
     */
    @Test
    public void testSaveSmpProcedureUpdate() {
        try {
            boolean retResult = admin.saveSmpProcedure("ProvaSmp2","ProvaSmp2Rin");
            assertTrue("Procedure True", retResult);
        }catch(ProcedureException | NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * this test assert that saveSmpProcedure correctly raises exception when addSmp <br>
     * raises exception because the procedure is already present.
     * @throws exception.ProcedureException
     */
    @Test(expected = ProcedureException.class)
    public void testSaveSmpUpdateThrowException() throws ProcedureException {
        try {
            boolean retResult = admin.saveSmpProcedure("ProvaSmp3", " ");
        }catch(NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
        
    }
    
    /**
     * this test assert that saveSmpProcedure correctly raises exception when the passed procedure is empty string.
     * @throws exception.ProcedureException
     */
    @Test(expected = ProcedureException.class)
    public void testSaveSmpEmptyProcedure() throws ProcedureException {
        try {
            boolean retResult = admin.saveSmpProcedure(" ", " ");
        }catch(NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
        
    }
    
    /**
     * this test assert that saveSmpProcedure correctly raises exception when the passed procedure is null.
     * @throws exception.ProcedureException
     */
    @Test(expected = ProcedureException.class)
    public void testSaveSmpNullProcedure() throws ProcedureException {
        try {
            boolean retResult = admin.saveSmpProcedure(null, " ");
        }catch(NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
//=================== Test of viewUser, makeTypology, readTypology, removeTypology, updateTypology, readSite and updateSite  =======================
    /*Test methods developed by Vincenza Coppola*/
   
    /**
     * Test of viewUser method, of class SystemAdministrator.
     * @throws exception.UsersException
     */
    @Test
    public void testViewUser() throws UsersException {
        try {
            System.out.println("viewUser");
            List<UserDTO> expected = new ArrayList<>();
            expected.add(new MaintainerDTO("UserMaintainer","PwdMaintainer"));
            expected.add(new PlannerDTO("UserPlanner","PwdPlanner"));
            expected.add(new SystemAdministratorDTO("UserSystemAdministrator","PwdSystemAdministrator"));
            List<UserDTO> users = instance.viewUsers();
            assertEquals(true,expected.equals(users));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of makeTypology method, of class SystemAdministrator.
     * @throws exception.TypologyException
     */
    @Test
    public void testMakeTypology() throws TypologyException{
        try {
            System.out.println("makeTypology");
            String typology = " ";
            boolean result = instance.makeTypology(typology);
            assertEquals(true,result);
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of makeTypology method, of class SystemAdministrator.
     * @throws exception.TypologyException
     */    
    @Test(expected = TypologyException.class)
    public void testMakeTypologyException() throws TypologyException{
        try {
            System.out.println("makeTypologyException");
            String typology = "exception";
            instance.makeTypology(typology);
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of makeTypology method, of class SystemAdministrator.
     * @throws exception.TypologyException
     */    
    @Test
    public void testMakeTypologyFalse() throws TypologyException{
        try {
            System.out.println("makeTypologyFalse");
            String typology = "false";
            boolean result = instance.makeTypology(typology);
            assertEquals(false,result);
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of readTypologies method, of class SystemAdministrator.
     * @throws exception.TypologyException
     * @throws exception.NotValidParameterException
     */
    @Test 
    public void testReadTypologies() throws TypologyException, NotValidParameterException{
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
        try {
            System.out.println("correct updateTypologyTest");
            String oldTypology = "oldTypology";
            String newTypology = "newTypology";
            assertEquals(true,instance.updateTypology(oldTypology, newTypology));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Tests of updateTypology method, of class SystemAdministrator.
     * @throws exception.TypologyException
     */   
    @Test 
    public void testUpdateTypologyFalse() throws TypologyException {
        System.out.println("incorrect updateTypologyTest");
        String oldTypology = "oldTypology";
        String newTypology = "oldTypology";
        try {
            assertEquals(false,instance.updateTypology(oldTypology, newTypology));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Tests of updateTypology method, of class SystemAdministrator.
     * @throws exception.TypologyException
     * @throws exception.NotValidParameterException
     */   
    @Test(expected = TypologyException.class) 
    public void testUpdateTypologyException() throws TypologyException, NotValidParameterException {
        System.out.println("updateTypologyExceptionTest");
        String oldTypology = "oldTypology";
        String newTypology = "Typology Exception";
        instance.updateTypology(oldTypology, newTypology);
    }
    
    /**
     * Tests of removeTypology method, of class SystemAdministrator.
     * @throws exception.TypologyException
     * @throws exception.NotValidParameterException
     */
    @Test
    public void testRemoveTypology() throws TypologyException, NotValidParameterException {
        System.out.println("removeTypologyTest");
        String typology = "TypologyTrue";
        assertEquals(true,instance.removeTypology(typology));
    }

    /**
     * Tests of removeTypology method, of class SystemAdministrator.
     * @throws exception.TypologyException
     * @throws exception.NotValidParameterException
     */    
    @Test
    public void testRemoveTypologyFalse() throws TypologyException, NotValidParameterException {
        System.out.println("removeTypologyFalseTest");
        String typology = "TypologyFalse";
        assertEquals(false,instance.removeTypology(typology));
    }

    /**
     * Tests of removeTypology method, of class SystemAdministrator.
     * @throws exception.TypologyException
     * @throws exception.NotValidParameterException
     */    
    @Test(expected = TypologyException.class)
    public void testRemoveTypologyException() throws TypologyException, NotValidParameterException {
        System.out.println("removeTypologyExceptionTest");
        String typology = "TypologyException";
        instance.removeTypology(typology);
    }
    
        /**
     * Tests of readSites method, of class SystemAdministrator.
     * @throws SiteException
     * @throws NotValidParameterException 
     */
    @Test 
    public void testReadSites() throws SiteException, NotValidParameterException{
        System.out.println("readSitesTest");
        List<Site> expected = new ArrayList<>();
        expected.add(new Site("BranchOffice1","Area1","WorkspaceNotes1"));
        expected.add(new Site("BranchOffice2","Area2","WorkspaceNotes2"));
        expected.add(new Site("BranchOffice3","Area3","WorkspaceNotes3"));
        List<Site> sites = instance.readSites();
        assertEquals(true,expected.equals(sites));  
    }
    /**
     * Tests of updateSite method, of class SystemAdministrator.
     * @throws exception.SiteException
     */
    @Test 
    public void testUpdateSite() throws SiteException {
        try {
            System.out.println("correct updateSiteTest");
            Site oldSite = new Site("BranchOffice1","Area1","WorkspaceNotes1");
            Site newSite = new Site("BranchOffice2","Area2","WorkspaceNotes2");
            assertEquals(true,instance.updateSite(oldSite, newSite));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Tests of updateSite method, of class SystemAdministrator.
     * @throws SiteException 
     */
    @Test 
    public void testUpdateSiteFalse() throws SiteException {
        System.out.println("incorrect updateSiteTest");
        Site oldSite = new Site("BranchOffice1","Area1","WorkspaceNotes1");
        try {
            assertEquals(false,instance.updateSite(oldSite,oldSite));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Tests of updateSite method, of class SystemAdministrator.
     * @throws exception.SiteException
     * @throws exception.NotValidParameterException
     */   
    @Test(expected = SiteException.class) 
    public void testUpdateSiteException() throws SiteException, NotValidParameterException {
        System.out.println("updateSiteExceptionTest");
        Site oldSite = new Site("BranchOffice1","Area1","WorkspaceNotes1");
        Site newSite = new Site("Exception","Area2","WorkspaceNotes2");
        instance.updateSite(oldSite, newSite);
    }
    
 /*=======================================================Test of makeUser, makeSite and removeSite==================================================================================*/
    /*Test methods developed by Alessio Citro*/
    
    /**
     * Test of makeUser method, of class SystemAdministrator.
     * @throws exception.UsersException
     */
    @Test
    public void testMakeUser() throws UsersException {
        try {
            System.out.println("makeUserTest");
            boolean result = instance.makeUser(username, password, UserRole.MAINTAINER);
            assertEquals(true, result);
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of makeUser method, of class SystemAdministrator.
     * @throws exception.UsersException
     */
    @Test(expected = UsersException.class)
    public void testMakeUserException() throws UsersException {
        try {
            System.out.println("makeUserExceptionTest");
            boolean result = instance.makeUser(username, null, UserRole.MAINTAINER);
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of makeSite method, of class SystemAdministrator.
     * @throws SiteException
     */
    @Test
    public void testMakeSite() throws SiteException {
        try{
            System.out.println("makeSiteTest");
            boolean result = instance.makeSite(new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes"));
            assertEquals(true, result);
        } catch (NotValidParameterException ex){
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of makeSite method, of class SystemAdministrator.
     * @throws exception.SiteException
     */
    @Test(expected = SiteException.class)
    public void testMakeSiteException() throws SiteException {
        try{
            System.out.println("makeSiteExceptionTest");
            boolean result = instance.makeSite(new Site("Exception", "ProvaArea", "ProvaWorkspaceNotes"));
        } catch (NotValidParameterException ex){
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of makeSite method, of class SystemAdministrator.
     * @throws SiteException
     */
    @Test
    public void testMakeSiteFalse() throws SiteException {
        try{
            System.out.println("makeSiteFalseTest");
            boolean result = instance.makeSite(new Site("False", "ProvaArea", "ProvaWorkspaceNotes"));
            assertEquals(false, result);
        } catch (NotValidParameterException ex){
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of removeSite method, of class SystemAdministrator
     * @throws SiteException 
     */
    @Test 
    public void testRemoveSite() throws SiteException {
        try{
            System.out.println("removeSiteTest");
            boolean result = instance.removeSite(new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes"));
            assertEquals(true, result);
        } catch (NotValidParameterException ex){
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of removeSite method, of class SystemAdministrator
     * @throws SiteException 
     */
    @Test(expected = SiteException.class)
    public void testRemoveSiteException() throws SiteException {
        try{
            System.out.println("removeSiteExceptionTest");
            boolean result = instance.removeSite(new Site("Exception", "ProvaArea", "ProvaWorkspaceNotes"));
        } catch (NotValidParameterException ex){
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of removeSite method, of class SystemAdministrator
     * @throws SiteException 
     */
    @Test 
    public void testRemoveSiteFalse() throws SiteException {
        try{
            System.out.println("removeSiteFalseTest");
            boolean result = instance.removeSite(new Site("False", "ProvaArea", "ProvaWorkspaceNotes"));
            assertEquals(false, result);
        } catch (NotValidParameterException ex){
            fail("NotValidParameterException");
        }
    }
    
    
    
    /*=======================================================removeUsers Test=====================================================*/
    
    /*Test of removeUsers developed by Rosario Gaeta*/
    
    /**
     * This test assert that removeUsers correctly returns the number of rows deleted.
     */
    @Test
    public void testRemoveUsers() {
        try {
            List<String> usernameList = new ArrayList<>() {{
                add("username1");
                add("username2");
                add("username3");
            }};
            
            int returnedNumberOfDeletedRow = admin.removeUsers(usernameList);
            assertEquals(returnedNumberOfDeletedRow,usernameList.size());
        }catch(UsersException | NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }
    }
    
    /**
     * This test assert that removeUsers correctly returns the number of rows deleted.
     */
    @Test
    public void testRemoveUsersZero() {
        try {
            List<String> usernameList = new ArrayList<>() {{
                add("username1");
            }};
            int returnedNumberOfDeletedRow = admin.removeUsers(usernameList);
            assertEquals(returnedNumberOfDeletedRow,0);
        }catch(UsersException | NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        }       
    }
    
    /**
     * This test assert that removeUsers correctly returns 0 if an empty list is passed.
     */
    @Test
    public void testRemoveUsersIsEmpty() {
        try {
            List<String> usernameList = new ArrayList<>();
            int returnedNumberOfDeletedRow = admin.removeUsers(usernameList);
            assertEquals(returnedNumberOfDeletedRow,usernameList.size());
        }catch(UsersException | NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        } 
    }
    
    /**
     * This test assert that removeUsers correctly returns 0 if null is passed.
     */
    @Test
    public void testRemoveUsersNull(){
        try {
            int returnedNumberOfDeletedRow = admin.removeUsers(null);
            assertEquals(returnedNumberOfDeletedRow,0);
        }catch(UsersException | NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        } 
    }
    
    /**
     * This test assert that removeUsers correctly raises an UsersException <br>
     * when deleteUsers raises an exception.
     * @throws exception.UsersException
     */
    @Test(expected = UsersException.class)
    public void testRemoveUsersRaisesException() throws UsersException{
        try {
            List<String> usernameList = new ArrayList<>() {{
                add("username1");
                add("username2");
            }};
            int returnedNumberOfDeletedRow = admin.removeUsers(usernameList);
        }catch(NotValidParameterException  ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());  
        } 
    }
    
//=========================================================================================================================================

    @Test
    public void testModifyUserSuccessfulUpdateToPlanner() throws NotValidParameterException{
        try{
            assertTrue(admin.modifyUser("oldUsername1", planner));
        } catch(UsersException ex){
            fail("UsersException");
        }
    }
    
    
    
    public void testModifyUserUnsuccessfulUpdateToPlanner() throws NotValidParameterException{
        try{
            assertFalse(admin.modifyUser("oldUsername2", planner));
        } catch(UsersException ex){
            fail("UsersException");
        }
    }
   
    
    @Test(expected = UsersException.class)
    public void testModifyUserUpdateToPlannerException() throws UsersException, NotValidParameterException{
        admin.modifyUser("oldUsername3", planner);
    }
    
    @Test
    public void testModifyUserSuccessfulUpdateToMaintainer() throws NotValidParameterException{
        try{
            assertTrue(admin.modifyUser("oldUsername1", maintainer));
        } catch(UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test
    public void testModifyUserUnsuccessfulUpdateToMaintainer() throws NotValidParameterException{
        try{
            assertFalse(admin.modifyUser("oldUsername2", maintainer));
        } catch(UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test(expected = UsersException.class)
    public void testModifyUserUpdateToMaintainerException() throws UsersException, NotValidParameterException{
        admin.modifyUser("oldUsername3", maintainer);
    }
    
    @Test
    public void testModifyUserSuccessfulUpdateTo() throws NotValidParameterException{
        try{
            assertTrue(admin.modifyUser("oldUsername1", systemAdministrator));
        } catch(UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test
    public void testModifyUserUnsuccessfulUpdateToSystemAdministrator() throws NotValidParameterException{
        try{
            assertFalse(admin.modifyUser("oldUsername2", systemAdministrator));
        } catch(UsersException ex){
            fail("UsersException");
        }
    }
    
    @Test(expected = UsersException.class)
    public void testModifyUserUpdateToSystemAdministratorException() throws UsersException, NotValidParameterException{
        admin.modifyUser("oldUsername3 ", systemAdministrator);
    }
    
    @Test(expected = UsersException.class)
    public void testModifyUserOldUsernameNull() throws UsersException, NotValidParameterException{
        admin.modifyUser("null", systemAdministrator);        
    }
    
//    @Test(expected = UsersException.class)
//    public void testModifyUserNewUsernameNull() throws UsersException {
//        User administrator = new SystemAdministrator(null, "newPassword");        
//        admin.modifyUser("oldUsername1", administrator);
//    }
//    
}
