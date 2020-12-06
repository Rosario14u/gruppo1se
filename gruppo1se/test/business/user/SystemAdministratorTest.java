/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import exception.ProcedureException;
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
import stub.UsersDAOStub;

/**
 *
 * @author rosar
 */
public class SystemAdministratorTest {
    private SystemAdministrator admin;
    private final SystemAdministrator instance = new SystemAdministrator("systemAdministratorUsername","systemAdministratorPassword",new UsersDAOStub());
    private final String username = "maintainerUsername"; 
    
    public SystemAdministratorTest() {
        admin = new SystemAdministrator("admin","admin",new MaintenanceProcedureDAOStub());
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
     * Test of saveSmpProcedure method, of class SystemAdministrator.
     */
    @Test
    public void testSaveSmpProcedureTrue() {
        try {
            boolean retResult = admin.saveSmpProcedure("ProvaSmp1");
            assertTrue("Procedure True", retResult);
        } catch (ProcedureException ex) {
            fail("Procedure Exception");
        }
    }
    
    /**
     * Test of saveSmpProcedure method, of class SystemAdministrator.
     */
    @Test
    public void testSaveSmpProcedureFalse() {
        try {
            boolean retResult = admin.saveSmpProcedure("ProvaSmp2");
            assertFalse("Procedure False", retResult);
        } catch (ProcedureException ex) {
            fail("Procedure Exception");
        }
    }
    
    /**
     * Test of saveSmpProcedure method, of class SystemAdministrator.
     */
    @Test(expected = ProcedureException.class)
    public void testSaveSmpProcedureNull() throws ProcedureException {
        boolean retResult = admin.saveSmpProcedure(null);
        
    }
    
    /**
     * Test of saveSmpProcedure method, of class SystemAdministrator.
     */
    @Test(expected = ProcedureException.class)
    public void testSaveSmpProcedureException() throws ProcedureException {
        boolean retResult = admin.saveSmpProcedure("ProvaSmp4");
        
    }
//=========================================================================================================================================
        /**
     * Test of viewUser method, of class SystemAdministrator.
     */
    
        
    @Test
    public void testViewUser() throws UsersException {
        System.out.println("viewUser");
        List<User> users = instance.viewUser(null,null);
        assertNull(users);      
    }
    
    
    @Test
    public void testViewUserUsername() throws UsersException {
        System.out.println("viewUserUsernameTest");
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new Maintainer(username,"maintainerPassword"));
        List<User> users = instance.viewUser(username, null);
        assertEquals(true,expectedUsers.equals(users));      
    }
    
        
    @Test
    public void testViewUserRole() throws UsersException {
        System.out.println("viewUserRoleTest");
        List<User> expectedUsers = new ArrayList<>(){{
            add(new Maintainer("maintainerUsername","maintainerPassword"));
            add(new Maintainer("maintainer2Username","maintainer2Password"));
            add(new Maintainer("maintainer3Username","maintainer3Password"));
        }};
        List<User> users = instance.viewUser(null,"Maintainer");
        assertEquals(true,expectedUsers.equals(users));      
    }
    
    @Test (expected = UsersException.class)
    public void testViewUserException() throws UsersException {
        System.out.println("viewUserExceptionTest");
        List<User> users = instance.viewUser(username,"Maintainer");
    }

    
}
