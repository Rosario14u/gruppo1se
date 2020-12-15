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
 * @author rosar
 */
public class MaterialTest {
    private final Material instance;
    public MaterialTest() {
        instance = new Material("Ferro");
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
     * Test of getName method, of class Material.
     */
    @Test
    public void testGetName() {
        String expResult = "Ferro";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Material.
     */
    @Test
    public void testToString() {
        String expResult = "Ferro";
        String result = instance.toString();
        assertEquals(expResult, result);
    }


    /**
     * Test of equals method, of class Material.
     */
    @Test
    public void testEqualsSameInstance() {
        Object obj = instance;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Material.
     */
    @Test
    public void testEqualsSameAttribute() {
        Object obj = new Material("Ferro");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class Material.
     */
    @Test
    public void testEqualsDifferentAttribute() {
        Object obj = new Material("Rame");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    
}
