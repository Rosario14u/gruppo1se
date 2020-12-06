/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.user;

import business.maintenanceactivity.MaintenanceProcedure;
import exception.ProcedureException;
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
    
    
}
