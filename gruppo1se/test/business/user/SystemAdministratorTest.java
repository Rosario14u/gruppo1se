/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.MaintenanceProcedure;
import exception.ProcedureException;
import exception.UsersException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import persistence.database.ConnectionDB;
import persistence.maintenanceactivity.MaintenanceActivityDAOImpl;
import persistence.maintenanceactivity.MaintenanceProcedureDAOImpl;
import persistence.maintenanceactivity.RequiredMaterialForMaintenanceDAOImpl;
import persistence.maintenanceactivity.SiteDaoImpl;
import persistence.user.UsersDAOImpl;
import stub.MaintenanceProcedureDAOStub;
import stub.UsersDAOStub;

/**
 *
 * @author rosar
 */
public class SystemAdministratorTest {
    private SystemAdministrator admin;
    private final SystemAdministrator instance = new SystemAdministrator("systemAdministratorUsername","systemAdministratorPassword",
            new MaintenanceProcedureDAOStub(),
            new UsersDAOStub());
    private final String username = "maintainerUsername"; 
    private final String password = "maintainerPassword";
    
    public SystemAdministratorTest() {
        admin = new SystemAdministrator("admin","admin",new MaintenanceProcedureDAOStub(),null);
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
//=========================================================================================================================================
        /**
     * Test of viewUser method, of class SystemAdministrator.
     */
    
    /**
     * Test of viewUser method, of class SystemAdministrator.
     * @throws exception.UsersException
     */
    
    @Test
    public void testViewUser() throws UsersException {
        System.out.println("viewUser");
        List<User> expected = new ArrayList<>();
        expected.add(new Maintainer("UserMaintainer","PwdMaintainer"));
        expected.add(new Planner("UserPlanner","PwdPlanner", new MaintenanceActivityDAOImpl(new SiteDaoImpl()),new RequiredMaterialForMaintenanceDAOImpl()));
        expected.add(new SystemAdministrator("UserSystemAdministrator","PwdSystemAdministrator",new MaintenanceProcedureDAOImpl(),new UsersDAOImpl()));
        List<User> users = instance.viewUsers();
        assertEquals(true,expected.equals(users));
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
    
    /**
     * this test assert that saveSmpProcedure correctly raises exception when procedure passed is null
     * @throws exception.ProcedureException
     */
    @Test(expected = ProcedureException.class)
    public void testSaveSmpNullProcedure() throws ProcedureException {
        boolean retResult = admin.saveSmpProcedure(null, " ");
    }
    
    
}
