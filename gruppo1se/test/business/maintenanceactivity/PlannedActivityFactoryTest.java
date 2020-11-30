/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
public class PlannedActivityFactoryTest {
    private PlannedActivityFactory instance;
    public PlannedActivityFactoryTest() {
        instance = new PlannedActivityFactory();
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
     * Test of selectMaintenanceActivity method, of class PlannedActivityFactory.
     */
    @Test
    public void testSelectMaintenanceActivity() {
        MaintenanceActivityFactory.Typology type = MaintenanceActivityFactory.Typology.PLANNED;
        int activityId = 1;
        Site site = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes");
        String typology = "ProvaTipologia";
        String activityDescription = "ProvaDescrizione";
        int estimatedInterventionTime = 120;
        LocalDate date = LocalDate.parse("2020-11-30");
        MaintenanceProcedure maintenanceProcedure = new MaintenanceProcedure("Smp");
        List<Material> materials = new ArrayList<>() {{
            add(new Material("Material1"));
            add(new Material("Material2"));
            add(new Material("Material3"));
        }};
        boolean interruptibleActivity = false;
        MaintenanceActivity expResult = new PlannedMaintenanceActivity(activityId, site, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
        MaintenanceActivity result = instance.selectMaintenanceActivity(type, activityId, site, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
        assertEquals(expResult, result);
    }
    
}
