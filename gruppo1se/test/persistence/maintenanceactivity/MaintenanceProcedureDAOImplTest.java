/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.MaintenanceProcedure;
import exception.ProcedureException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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
public class MaintenanceProcedureDAOImplTest {
    private static Connection conn;
    private MaintenanceProcedureDAOImpl procedureDao = new MaintenanceProcedureDAOImpl();
    private final static String INSERT_PROCEDURE = "INSERT INTO MaintenanceProcedure values(?)";
    private final static String DELETE_PROCEDURE = "DELETE FROM MaintenanceProcedure WHERE smp = ?";
    private final static String SELECT_PROCEDURE = "SELECT * FROM MaintenanceProcedure WHERE smp = ?";
    public MaintenanceProcedureDAOImplTest() {
        procedureDao = new MaintenanceProcedureDAOImpl();
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = ConnectionDB.getInstanceConnection().getConnection();
            conn.setAutoCommit(false);
            
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceProcedureDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceProcedureDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MaintenanceProcedureDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testAddSmpTrue() {
        try {
            delete_procedure("ProvaSmp1");
            procedureDao.addSmp(new MaintenanceProcedure("ProvaSmp1"));
            select_procedure("ProvaSmp1");
        } catch (SQLException ex) {
            fail("SQLEXception");
        } catch (ProcedureException ex) {
            fail("ProcedureException");
        }
        
    }
    
    @Test(expected = ProcedureException.class)
    public void testAddSmpAlreadyPresent() throws ProcedureException {
        try {
            delete_procedure("ProvaSmp2");
            insert_procedure("ProvaSmp2");
            procedureDao.addSmp(new MaintenanceProcedure("ProvaSmp2"));
        } catch (SQLException ex) {
            fail("SQLEXception");
        }
        
    }
    
    @Test(expected = ProcedureException.class)
    public void testAddSmpNull() throws ProcedureException {
        try {
            delete_procedure("ProvaSmp1");
            procedureDao.addSmp(null);
        } catch (SQLException ex) {
            fail("SQLEXception");
        }
        
    }
    
    private void insert_procedure(String smp) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_PROCEDURE);
        pstm.setString(1, smp);
        pstm.executeUpdate();
    }
    
    private void delete_procedure(String smp) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(DELETE_PROCEDURE);
        pstm.setString(1, smp);
        pstm.executeUpdate();
    }
    
    private void select_procedure(String smp) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(SELECT_PROCEDURE);
        pstm.setString(1, smp);
        ResultSet rs = pstm.executeQuery();
        boolean isEmpty = true;
        while(rs.next()){
            isEmpty = false;
            assertEquals("smp error",smp, rs.getString("smp"));
        }
        assertFalse(isEmpty);
        
    }
    
    @Test
    public void updateSmpSuccessfulUpdate() {
        MaintenanceProcedure procedure = new MaintenanceProcedure("ProvaSmpRin1");
        try {
            delete_procedure("ProvaSmp1");
            insert_procedure("ProvaSmp1");
            boolean retVal = procedureDao.updateSmp(procedure, "ProvaSmp1");
            assertTrue("SuccessfulUpdate", retVal);
            select_procedure("ProvaSmpRin1");
        } catch (SQLException ex) {
            fail("SQLEXception");
        } catch (ProcedureException ex) {
            fail("ProcedureException");
        } 
    }
    
    @Test
    public void updateSmpUnsuccessfulUpdate() {
        MaintenanceProcedure procedure = new MaintenanceProcedure("ProvaSmpRin1");
        try {
            delete_procedure("ProvaSmp1");
            boolean retVal = procedureDao.updateSmp(procedure, "ProvaSmp1");
            assertFalse("UnsuccessfulUpdate", retVal);
        } catch (SQLException ex) {
            fail("SQLEXception");
        } catch (ProcedureException ex) {
            fail("ProcedureException");
        } 
    }
    
    @Test(expected = ProcedureException.class)
    public void updateSmpUnsuccessfulUpdateException1() throws ProcedureException {
        MaintenanceProcedure procedure = new MaintenanceProcedure("ProvaSmp1");
        try {
            delete_procedure("ProvaSmp1");
            insert_procedure("ProvaSmp1");
            boolean retVal = procedureDao.updateSmp(procedure, null);
        } catch (SQLException ex) {
            fail("SQLEXception");
        }
    }
    
    @Test(expected = ProcedureException.class)
    public void updateSmpUnsuccessfulUpdateException2() throws ProcedureException {
        MaintenanceProcedure procedure = new MaintenanceProcedure("ProvaSmp1");
        try {
            delete_procedure("ProvaSmp1");
            insert_procedure("ProvaSmp1");
            boolean retVal = procedureDao.updateSmp(procedure, " ");
        } catch (SQLException ex) {
            fail("SQLEXception");
        }
    }
    
    @Test(expected = ProcedureException.class)
    public void updateSmpUnsuccessfulUpdateException3() throws ProcedureException {
        MaintenanceProcedure procedure = new MaintenanceProcedure("ProvaSmp1");
        try {
            delete_procedure("ProvaSmp1");
            insert_procedure("ProvaSmp1");
            boolean retVal = procedureDao.updateSmp(null, "ProvaSmp1");
        } catch (SQLException ex) {
            fail("SQLEXception");
        }
    }
    
    @Test(expected = ProcedureException.class)
    public void updateSmpUnsuccessfulUpdateException4() throws ProcedureException {
        MaintenanceProcedure procedure = new MaintenanceProcedure(" ");
        try {
            delete_procedure("ProvaSmp1");
            insert_procedure("ProvaSmp1");
            boolean retVal = procedureDao.updateSmp(procedure, "ProvaSmp1");
        } catch (SQLException ex) {
            fail("SQLEXception");
        } 
    }
    
    @Test(expected = ProcedureException.class)
    public void updateSmpUnsuccessfulUpdateException5() throws ProcedureException {
        MaintenanceProcedure procedure = new MaintenanceProcedure("ProvaSmp1");
        try {
            delete_procedure("ProvaSmp1");
            insert_procedure("ProvaSmp1");
            boolean retVal = procedureDao.updateSmp(procedure, "ProvaSmp1");
        } catch (SQLException ex) {
            fail("SQLEXception");
        } 
    }
}
