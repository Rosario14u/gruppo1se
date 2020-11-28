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
    private Site site = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes");
    
    public SiteTest() {
    }


    /**
     * Test of getBranchOffice method, of class Site.
     */
    @Test
    public void testGetBranchOffice() {
        System.out.println("getBranchOffice");
        String expResult = "ProvaBranchOffice";
        String result = site.getBranchOffice();
        assertEquals(expResult, result);
    }

    /**
     * Test of getArea method, of class Site.
     */
    @Test
    public void testGetArea() {
        System.out.println("getArea");
        String expResult = "ProvaArea";
        String result = site.getArea();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWorkSpaceNotes method, of class Site.
     */
    @Test
    public void testGetWorkSpaceNotes() {
        System.out.println("getWorkSpaceNotes");
        String expResult = "ProvaWorkspaceNotes";
        String result = site.getWorkSpaceNotes();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWorkSpaceNotes method, of class Site.
     */
    @Test
    public void testSetWorkSpaceNotes() {
        System.out.println("setWorkSpaceNotes");
        String workSpaceNotes = "AggiornaWorkspaceNotes";
        site.setWorkSpaceNotes(workSpaceNotes);
        assertEquals(workSpaceNotes, site.getWorkSpaceNotes());
    }
    
}
