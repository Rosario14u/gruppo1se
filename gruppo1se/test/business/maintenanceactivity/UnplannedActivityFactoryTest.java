/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import exception.NotValidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class UnplannedActivityFactoryTest {
    private UnplannedActivityFactory instance;
    public UnplannedActivityFactoryTest() {
        instance = new UnplannedActivityFactory();
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
     * Test of selectMaintenanceActivity method, of class UnplannedActivityFactory.
     */
    @Test
    public void testSelectMaintenanceActivityEwo() {
        try {
            System.out.println("selectMaintenanceActivity");
            MaintenanceActivityFactory.Typology type = MaintenanceActivityFactory.Typology.EWO;
            int activityId = 1;
            Site site = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes");
            String typology = "ProvaTipologia";
            String activityDescription = "ProvaDescrizione";
            int estimatedInterventionTime = 120;
            LocalDate date = LocalDate.parse("2020-11-30");
            MaintenanceProcedure maintenanceProcedure = new MaintenanceProcedure("Smp");
            List<Material> materials = createListMaterial("Materiale2","Materiale1","Materiale3");
            boolean interruptibleActivity = false;
            Ewo expResult = new Ewo(activityId, site, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials,interruptibleActivity);
            MaintenanceActivity result = instance.selectMaintenanceActivity(type, activityId, site, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
            assertEquals(expResult, result);
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of selectMaintenanceActivity method, of class UnplannedActivityFactory.
     */
    @Test
    public void testSelectMaintenanceActivityExtra() {
        try {
            System.out.println("selectMaintenanceActivity");
            MaintenanceActivityFactory.Typology type = MaintenanceActivityFactory.Typology.EXTRA;
            int activityId = 2;
            Site site = new Site("ProvaBranchOffice2", "ProvaArea2", "ProvaWorkspaceNotes2");
            String typology = "ProvaTipologia2";
            String activityDescription = "ProvaDescrizione2";
            int estimatedInterventionTime = 121;
            LocalDate date = LocalDate.parse("2020-12-01");
            MaintenanceProcedure maintenanceProcedure = new MaintenanceProcedure("Smp2");
            List<Material> materials = createListMaterial("Material4","Material5","Material6");
            boolean interruptibleActivity = true;
            ExtraActivity expResult = new ExtraActivity(activityId, site, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
            MaintenanceActivity result = instance.selectMaintenanceActivity(type, activityId, site, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
            assertEquals(expResult, result);
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    private List<Material> createListMaterial(String materialElement1, String materialElement2, String materialElement3) throws NotValidParameterException{
        return new ArrayList<>() {{
            add(new Material(materialElement1));
            add(new Material(materialElement2));
            add(new Material(materialElement3));    
        }};
    }    
}
