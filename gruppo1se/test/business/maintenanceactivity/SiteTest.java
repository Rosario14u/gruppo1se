/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author aless
 */
public class SiteTest{
    private Site instance;
    
    public SiteTest() {
        instance = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes");
    }


    /**
     * Test of getBranchOffice method, of class Site.
     */
    @Test
    public void testGetBranchOffice() {
        String expResult = "ProvaBranchOffice";
        String result = instance.getBranchOffice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getArea method, of class Site.
     */
    @Test
    public void testGetArea() {
        String expResult = "ProvaArea";
        String result = instance.getArea();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWorkSpaceNotes method, of class Site.
     */
    @Test
    public void testGetWorkSpaceNotes() {
        String expResult = "ProvaWorkspaceNotes";
        String result = instance.getWorkSpaceNotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWorkSpaceNotes method, of class Site.
     */
    @Test
    public void testSetWorkSpaceNotes() {
        String workSpaceNotes = "AggiornaWorkspaceNotes";
        instance.setWorkSpaceNotes(workSpaceNotes);
        assertEquals(workSpaceNotes, instance.getWorkSpaceNotes());
    }
    
    /**
     * Test of equals method, of class Site.
     */
    @Test
    public void testEqualsSameInstance() {
        Site expSite = instance;
        boolean result = instance.equals(expSite);
        assertTrue(result);
    }
    
    
    /**
     * Test of equals method, of class Site.
     */
    @Test
    public void testEqualsSameAttributes() {
        Site expSite = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes");
        boolean result = instance.equals(expSite);
        assertTrue(result);
    }
    
    /**
     * Test of equals method, of class Site.
     */
    @Test
    public void testEqualsDifferentAttributes1() {
        Site expSite = new Site("ProvaBranchOffice2", "ProvaArea", "ProvaWorkspaceNotes");
        boolean result = instance.equals(expSite);
        assertFalse(result);
    }
    
    /**
     * Test of equals method, of class Site.
     */
    @Test
    public void testEqualsDifferentAttributes2() {
        Site expSite = new Site("ProvaBranchOffice", "ProvaArea2", "ProvaWorkspaceNotes");
        boolean result = instance.equals(expSite);
        assertFalse(result);
    }
    
    /**
     * Test of equals method, of class Site.
     */
    @Test
    public void testEqualsDifferentAttributes3() {
        Site expSite = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes2");
        boolean result = instance.equals(expSite);
        assertFalse(result);
    }
    
    /**
     * Test of toString method, of class Site.
     */
    @Test
    public void testToString() {
        String expResult = "Site{" + "branchOffice=" + "ProvaBranchOffice" + ", area=" + "ProvaArea" + ", workSpaceNotes=" + "ProvaWorkspaceNotes" + '}';
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
