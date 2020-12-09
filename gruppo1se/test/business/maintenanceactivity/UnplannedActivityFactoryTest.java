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
        System.out.println("selectMaintenanceActivity");
        MaintenanceActivityFactory.Typology type = MaintenanceActivityFactory.Typology.EWO;
        int activityId = 1;
        Site site = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes");
        String typology = "ProvaTipologia";
        String activityDescription = "ProvaDescrizione";
        int estimatedInterventionTime = 120;
        LocalDate date = LocalDate.parse("2020-11-30");
        MaintenanceProcedure maintenanceProcedure = new MaintenanceProcedure("Smp");
        List<Material> listMaterial = createListMaterial("Materiale2","Materiale1","Materiale3");
        List<Skill> listSkill = createListSkill("Skill2","Skill1","Skill3");
        boolean interruptibleActivity = false;
        Ewo expResult = new Ewo(activityId, site, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, listMaterial, listSkill,interruptibleActivity);
        MaintenanceActivity result = instance.selectMaintenanceActivity(type, activityId, site, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of selectMaintenanceActivity method, of class UnplannedActivityFactory.
     */
    @Test
    public void testSelectMaintenanceActivityExtra() {
        System.out.println("selectMaintenanceActivity");
        MaintenanceActivityFactory.Typology type = MaintenanceActivityFactory.Typology.EXTRA;
        int activityId = 2;
        Site site = new Site("ProvaBranchOffice2", "ProvaArea2", "ProvaWorkspaceNotes2");
        String typology = "ProvaTipologia2";
        String activityDescription = "ProvaDescrizione2";
        int estimatedInterventionTime = 121;
        LocalDate date = LocalDate.parse("2020-12-01");
        MaintenanceProcedure maintenanceProcedure = new MaintenanceProcedure("Smp2");
        List<Material> materials = new ArrayList<>() {{
            add(new Material("Material4"));
            add(new Material("Material5"));
            add(new Material("Material6"));
        }};
        boolean interruptibleActivity = true;
        ExtraActivity expResult = new ExtraActivity(activityId, site, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
        MaintenanceActivity result = instance.selectMaintenanceActivity(type, activityId, site, typology, activityDescription, estimatedInterventionTime, date, maintenanceProcedure, materials, interruptibleActivity);
        assertEquals(expResult, result);
    }
    
    private List<Material> createListMaterial(String materialElement1, String materialElement2, String materialElement3){
        return new ArrayList<>() {{
            add(new Material(materialElement1));
            add(new Material(materialElement2));
            add(new Material(materialElement3));    
        }};
    }
    
    private List<Skill> createListSkill(String skillElement1, String skillElement2, String skillElement3){
        return new ArrayList<>() {{
            add(new Skill(skillElement1));
            add(new Skill(skillElement1));
            add(new Skill(skillElement1));    
        }};
    }
    
}
