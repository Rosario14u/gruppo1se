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
/*Test class developed by Rosario Gaeta*/
public class MaintenanceActivityFactoryTest {
    
    public MaintenanceActivityFactoryTest() {
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
     * This method assert that make method correctly creates a planned maintenance activity.
     */
    @Test
    public void testMakePlannedActivity() {
        MaintenanceActivity expectedActivity = createMaintenanceActivity("Planned",1, "branchOfficeProva",
                "areaProva", "workspaceNotesProva", "tipologiaProva", "DescrizioneProva",
                500, "2020-12-30", "smpProva", true);
        MaintenanceActivity returnedActivity = MaintenanceActivityFactory.make(
                MaintenanceActivityFactory.Typology.PLANNED,expectedActivity.getActivityId(),
                expectedActivity.getSite(), expectedActivity.getTypology(), expectedActivity.getActivityDescription(),
                expectedActivity.getEstimatedInterventionTime(), expectedActivity.getDate(),
                expectedActivity.getMaintenanceProcedure(), expectedActivity.getMaterials(),
                expectedActivity.isInterruptibleActivity());
        assertEquals("Error in making of Planned Activity",returnedActivity, expectedActivity);
        
    }
    
    /**
     * This method assert that make method correctly creates an Ewo maintenance activity.
     */
    @Test
    public void testMakeEwoActivity() {
        MaintenanceActivity expectedActivity = createMaintenanceActivity("Ewo",1, "branchOfficeProva", "areaProva", "workspaceNotesProva",
                "tipologiaProva", "DescrizioneProva", 500, "2020-12-30", "smpProva", true);
        MaintenanceActivity returnedActivity = MaintenanceActivityFactory.make(
                MaintenanceActivityFactory.Typology.EWO,expectedActivity.getActivityId(),
                expectedActivity.getSite(), expectedActivity.getTypology(),
                expectedActivity.getActivityDescription(), expectedActivity.getEstimatedInterventionTime(),
                expectedActivity.getDate(), expectedActivity.getMaintenanceProcedure(),
                expectedActivity.getMaterials(), expectedActivity.isInterruptibleActivity());
        assertEquals("Error in making of Ewo Activity",returnedActivity, expectedActivity);
        
    }
    
    /**
     * This method assert that make method correctly creates an Extra maintenance activity.
     */
    @Test
    public void testMakeExtraActivity() {
        MaintenanceActivity expectedActivity = createMaintenanceActivity("Extra",1, "branchOfficeProva", "areaProva", "workspaceNotesProva",
                "tipologiaProva", "DescrizioneProva", 500, "2020-12-30", "smpProva", true);
        MaintenanceActivity returnedActivity = MaintenanceActivityFactory.make(
                MaintenanceActivityFactory.Typology.EXTRA,expectedActivity.getActivityId(),
                expectedActivity.getSite(), expectedActivity.getTypology(),
                expectedActivity.getActivityDescription(), expectedActivity.getEstimatedInterventionTime(),
                expectedActivity.getDate(), expectedActivity.getMaintenanceProcedure(),
                expectedActivity.getMaterials(),expectedActivity.isInterruptibleActivity());
        assertEquals("Error in making of Extra Activity",returnedActivity, expectedActivity);
    }
    /**
     * This method create 
     * @param role
     * @param activityId
     * @param branchOffice
     * @param area
     * @param workspaceNotes
     * @param typology
     * @param activityDescription
     * @param estimatedInterventionTime
     * @param dateString
     * @param smp
     * @param interruptibleActivity
     * @return
     * @throws NotValidParameterException 
     */
    private MaintenanceActivity createMaintenanceActivity(String role, int activityId, String branchOffice,String area, 
            String workspaceNotes, String typology, String activityDescription, int estimatedInterventionTime, String dateString,
            String smp, boolean interruptibleActivity) {
        MaintenanceActivity activity = null;
        List<Material> listMaterial = createListMaterial("MaterialeProva1", "MaterialeProva2", "MaterialeProva3");
        List<Skill> listSkill = createListSkill("SkillProva1", "SkillProva2", "SkillProva3");
        Site site = new Site(branchOffice, area, workspaceNotes);
        MaintenanceProcedure procedure = new MaintenanceProcedure(smp);
        LocalDate date = LocalDate.parse(dateString);
        if (role.equals("Planned")){
            activity = new PlannedMaintenanceActivity(1, site, typology, activityDescription, estimatedInterventionTime, 
                    date, procedure, listMaterial,interruptibleActivity);
        }else if (role.equals("Ewo")) {
            activity = new Ewo(1, site, typology, activityDescription, estimatedInterventionTime, 
                    date, procedure, listMaterial, interruptibleActivity);
        }else if (role.equals("Extra")) {
            activity = new ExtraActivity(1, site, typology, activityDescription, estimatedInterventionTime, 
                    date, procedure, listMaterial, interruptibleActivity);
        }
        return activity;
    }
    
    private List<Material> createListMaterial(String materialElement1, String materialElement2, String materialElement3) {
        return new ArrayList<>() {{
            add(new Material(materialElement1));
            add(new Material(materialElement2));
            add(new Material(materialElement3));    
        }};
    }
    
    private List<Skill> createListSkill(String skillElement1, String skillElement2, String skillElement3) {
        return new ArrayList<>() {{
            add(new Skill(skillElement1));
            add(new Skill(skillElement1));
            add(new Skill(skillElement1));    
        }};
    }
    
    /**
     * Test of selectMaintenanceActivity method, of class MaintenanceActivityFactory.
     */
    /*
    @Test
    public void testSelectMaintenanceActivity() {
        
    }

    public class MaintenanceActivityFactoryImpl extends MaintenanceActivityFactory {

        public MaintenanceActivity selectMaintenanceActivity(Typology type, int activityId, Site site, String typology, String activityDescription, int estimatedInterventionTime, LocalDate date, MaintenanceProcedure maintenanceProcedure, List<Material> materials, boolean interruptibleActivity) {
            return null;
        }
    }
    */
}
