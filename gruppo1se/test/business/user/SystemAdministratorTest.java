/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import exception.ProcedureException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import stub.MaintenanceProcedureDAOStub;

/**
 *
 * @author rosar
 */
public class SystemAdministratorTest {
    private SystemAdministrator admin;
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
    
}
