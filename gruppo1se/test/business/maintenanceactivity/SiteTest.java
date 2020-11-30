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
    
}
