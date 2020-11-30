/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
     * Test of make method, of class MaintenanceActivityFactory.
     */
    @Test
    public void testMakePlannedActivity() {
        MaintenanceActivity expectedActivity = createMaintenanceActivity("Planned",1, "branchOfficeProva", "areaProva", "workspaceNotesProva",
                "tipologiaProva", "DescrizioneProva", 500, "2020-12-30", "smpProva", true);
        MaintenanceActivity returnedActivity = MaintenanceActivityFactory.make(MaintenanceActivityFactory.Typology.PLANNED,1,
                expectedActivity.getSite().getBranchOffice(), expectedActivity.getSite().getArea(), expectedActivity.getSite().getWorkSpaceNotes(),
                expectedActivity.getTypology(), expectedActivity.getActivityDescription(),
                expectedActivity.getEstimatedInterventionTime(), String.valueOf(expectedActivity.getDate()),
                expectedActivity.getMaintenanceProcedure().getSmp(), expectedActivity.getMaterials(),
                expectedActivity.isInterruptibleActivity());
        assertMaintenanceActivity(expectedActivity,returnedActivity);
        
    }
    
    /**
     * Test of make method, of class MaintenanceActivityFactory.
     */
    @Test
    public void testMakeEwoActivity() {
        MaintenanceActivity expectedActivity = createMaintenanceActivity("Ewo",1, "branchOfficeProva", "areaProva", "workspaceNotesProva",
                "tipologiaProva", "DescrizioneProva", 500, "2020-12-30", "smpProva", true);
        MaintenanceActivity returnedActivity = MaintenanceActivityFactory.make(MaintenanceActivityFactory.Typology.EWO,1, expectedActivity.getSite().getBranchOffice(), 
                expectedActivity.getSite().getArea(),expectedActivity.getSite().getWorkSpaceNotes(), expectedActivity.getTypology(), expectedActivity.getActivityDescription(),
                expectedActivity.getEstimatedInterventionTime(), String.valueOf(expectedActivity.getDate()),
                expectedActivity.getMaintenanceProcedure().getSmp(), expectedActivity.getMaterials(),
                expectedActivity.isInterruptibleActivity());
        assertMaintenanceActivity(expectedActivity,returnedActivity);
        
    }
    
    /**
     * Test of make method, of class MaintenanceActivityFactory.
     */
    @Test
    public void testMakeExtraActivity() {
        MaintenanceActivity expectedActivity = createMaintenanceActivity("Extra",1, "branchOfficeProva", "areaProva", "workspaceNotesProva",
                "tipologiaProva", "DescrizioneProva", 500, "2020-12-30", "smpProva", true);
        MaintenanceActivity returnedActivity = MaintenanceActivityFactory.make(MaintenanceActivityFactory.Typology.EXTRA,1, expectedActivity.getSite().getBranchOffice(), 
                expectedActivity.getSite().getArea(), expectedActivity.getSite().getWorkSpaceNotes(), expectedActivity.getTypology(), expectedActivity.getActivityDescription(),
                expectedActivity.getEstimatedInterventionTime(), String.valueOf(expectedActivity.getDate()),
                expectedActivity.getMaintenanceProcedure().getSmp(), expectedActivity.getMaterials(),
                expectedActivity.isInterruptibleActivity());
        assertMaintenanceActivity(expectedActivity,returnedActivity);   
    }
    
    private void assertMaintenanceActivity(MaintenanceActivity expectedActivity, MaintenanceActivity returnedActivity){
        assertEquals("activityId error", expectedActivity.getActivityId(), returnedActivity.getActivityId());
        assertEquals("site error", expectedActivity.getSite(), returnedActivity.getSite());
        assertEquals("typology error", expectedActivity.getTypology(), returnedActivity.getTypology());
        assertEquals("description error", expectedActivity.getActivityDescription(), returnedActivity.getActivityDescription());
        assertEquals("estimated error", expectedActivity.getEstimatedInterventionTime(), returnedActivity.getEstimatedInterventionTime());
        assertEquals("date error", expectedActivity.getDate(), returnedActivity.getDate());
        assertEquals("procedure error", expectedActivity.getMaintenanceProcedure().getSmp(), returnedActivity.getMaintenanceProcedure().getSmp());
        assertEquals("material error", expectedActivity.getMaterials(), returnedActivity.getMaterials());
        assertEquals("interruptible error", expectedActivity.isInterruptibleActivity(), returnedActivity.isInterruptibleActivity());
        assertEquals("instance error", expectedActivity.getClass(), returnedActivity.getClass());
    }
    
    private MaintenanceActivity createMaintenanceActivity(String role, int activityId, String branchOffice,String area, 
            String workspaceNotes, String typology, String activityDescription, int estimatedInterventionTime, String dateString,
            String smp, boolean interruptibleActivity){
        MaintenanceActivity activity = null;
        List<Material> listMaterial = new ArrayList(Arrays.asList("MaterialeProva1","MaterialeProva2","MaterialeProva3"));
        Site site = new Site(branchOffice, area, workspaceNotes);
        MaintenanceProcedure procedure = new MaintenanceProcedure(smp);
        LocalDate date = LocalDate.parse(dateString);
        if (role.compareTo("Planned")== 0){
            activity = new PlannedMaintenanceActivity(1, site, typology, activityDescription, estimatedInterventionTime, 
                    date, procedure, listMaterial, interruptibleActivity);
        }else if (role.compareTo("Ewo")== 0) {
            activity = new Ewo(1, site, typology, activityDescription, estimatedInterventionTime, 
                    date, procedure, listMaterial, interruptibleActivity);
        }else if (role.compareTo("Extra")== 0) {
            activity = new ExtraActivity(1, site, typology, activityDescription, estimatedInterventionTime, 
                    date, procedure, listMaterial, interruptibleActivity);
        }
        return activity;
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
