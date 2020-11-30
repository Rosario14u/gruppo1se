/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gorra
 */
public class MaintenanceProcedureTest {
    private MaintenanceProcedure instance;
    public MaintenanceProcedureTest() {
        instance = new MaintenanceProcedure("Smp");
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
     * Test of getSmp method, of class MaintenanceProcedure.
     */
    @Test
    public void testGetSmp() {
        String expResult = "Smp";
        String result = instance.getSmp();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSmp method, of class MaintenanceProcedure.
     */
    @Test
    public void testSetSmp() {
        String smp = "Smp2";
        instance.setSmp(smp);
        assertEquals(instance.getSmp(),smp);
    }

    /**
     * Test of equals method, of class MaintenanceProcedure.
     */
    @Test
    public void testEqualsSameInstance() {
        MaintenanceProcedure expProcedure = instance;
        boolean result = instance.equals(expProcedure);
        assertTrue(result);
    }
    
    
    /**
     * Test of equals method, of class MaintenanceProcedure.
     */
    @Test
    public void testEqualsSameAttributes() {
        MaintenanceProcedure expProcedure = new MaintenanceProcedure("Smp");
        boolean result = instance.equals(expProcedure);
        assertTrue(result);
    }
    
    /**
     * Test of equals method, of class MaintenanceProcedure.
     */
    @Test
    public void testEqualsDifferentAttributes() {
        MaintenanceProcedure expProcedure = new MaintenanceProcedure("Smp2");
        boolean result = instance.equals(expProcedure);
        assertFalse(result);
    }
    

    /**
     * Test of toString method, of class MaintenanceProcedure.
     */
    @Test
    public void testToString() {
        String expResult = "MaintenanceProcedure{" + "smp=" + "Smp" + '}';
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
