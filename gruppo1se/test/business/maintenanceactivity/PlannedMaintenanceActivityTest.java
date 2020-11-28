/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author aless
 */
public class PlannedMaintenanceActivityTest {
    private final LinkedList<Material> materials = new LinkedList<>();
    private final MaintenanceProcedure maintenanceProcedure = new MaintenanceProcedure("ProvaPDF");
    private final Site site = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes");
    private final LocalDate date = LocalDate.of(2050, 11, 9);
    private MaintenanceActivity instance = null;
    
    public PlannedMaintenanceActivityTest() {
        materials.add(new Material("material1"));
        instance = new PlannedMaintenanceActivity(1, site, "ProvaTypology", "ProvaActivityDescription", 30, date, maintenanceProcedure, materials, true);
    }
    

    /**
     * Test of getActivityId method, of class PlannedMaintenanceActivity.
     */
    @Test
    public void testGetActivityId() {
        System.out.println("getActivityId");
        int expResult = 1;
        int result = instance.getActivityId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getSite method, of class PlannedMaintenanceActivity.
     */
    @Test
    public void testGetSite() {
        System.out.println("getSite");
        Site expResult = site;
        Site result = instance.getSite();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTypology method, of class PlannedMaintenanceActivity.
     */
    @Test
    public void testGetTypology() {
        System.out.println("getTypology");
        String expResult = "ProvaTypology";
        String result = instance.getTypology();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActivityDescription method, of class PlannedMaintenanceActivity.
     */
    @Test
    public void testGetActivityDescription() {
        System.out.println("getActivityDescription");
        String expResult = "ProvaActivityDescription";
        String result = instance.getActivityDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstimatedInterventionTime method, of class PlannedMaintenanceActivity.
     */
    @Test
    public void testGetEstimatedInterventionTime() {
        System.out.println("getEstimatedInterventionTime");
        int expResult = 30;
        int result = instance.getEstimatedInterventionTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDate method, of class MaintenanceActivity.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        LocalDate expResult = date;
        LocalDate result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaintenanceProcedure method, of class PlannedMaintenanceActivity.
     */
    @Test
    public void testGetMaintenanceProcedure() {
        System.out.println("getMaintenanceProcedure");
        MaintenanceProcedure expResult = maintenanceProcedure;
        MaintenanceProcedure result = instance.getMaintenanceProcedure();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaterials method, of class PlannedMaintenanceActivity.
     */
    @Test
    public void testGetMaterials() {
        System.out.println("getMaterials");
        List<Material> expResult = materials;
        List<Material> result = instance.getMaterials();
        assertEquals(expResult, result);
    }

    /**
     * Test of isInterruptibleActivity method, of class PlannedMaintenanceActivity.
     */
    @Test
    public void testIsInterruptibleActivity() {
        System.out.println("isInterruptibleActivity");
        boolean expResult = true;
        boolean result = instance.isInterruptibleActivity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaterials method, of class PlannedMaintenanceActivity.
     */
    @Test
    public void testSetMaterials() {
        System.out.println("setMaterials");
        List<Material> materialsAdd = new LinkedList<>();
        materialsAdd.add(new Material("materials2"));
        instance.setMaterials(materialsAdd);
        assertEquals(instance.getMaterials(), materialsAdd);
    }
    
    @Test
    public void isInstanceOfPlanned(){
        System.out.println("Instance of Planned");
        assertEquals(PlannedMaintenanceActivity.class.isInstance(this.instance), true);
    }

}
