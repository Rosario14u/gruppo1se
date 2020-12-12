/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.maintenanceactivity;

import exception.NotValidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
 * @author rosar
 */
/*Test class developed by Rosario Gaeta*/
public class MaintenanceActivityTest {
    MaintenanceActivity instance;
    public MaintenanceActivityTest() throws NotValidParameterException {
        List<Material> listMaterial = createListMaterial("Materiale1","Materiale2","Materiale3");
        instance = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes"),
                "ProvaTipologia","ProvaDescrizione",120,LocalDate.parse("2021-11-20"),
                new MaintenanceProcedure("Provasmp"),listMaterial,true);
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
     * Test of getActivityId method, of class MaintenanceActivity.
     */
    @Test
    public void testGetActivityId() {
        int expResult = 1;
        int result = instance.getActivityId();
        assertEquals("GetActivityId error",expResult, result);
    }

    /**
     * Test of getSite method, of class MaintenanceActivity.
     */
    @Test
    public void testGetSite() {
        Site expResult = new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes") ;
        Site result = instance.getSite();
        assertEquals("GetActivityId error",expResult.getBranchOffice(), result.getBranchOffice());
        assertEquals("GetActivityId error",expResult.getArea(), result.getArea());
        assertEquals("GetActivityId error",expResult.getWorkSpaceNotes(), result.getWorkSpaceNotes());
    }

    /**
     * Test of getTypology method, of class MaintenanceActivity.
     */
    @Test
    public void testGetTypology() {
        String expResult = "ProvaTipologia";
        String result = instance.getTypology();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActivityDescription method, of class MaintenanceActivity.
     */
    @Test
    public void testGetActivityDescription() {
        String expResult = "ProvaDescrizione";
        String result = instance.getActivityDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEstimatedInterventionTime method, of class MaintenanceActivity.
     */
    @Test
    public void testGetEstimatedInterventionTime() {
        int expResult = 120;
        int result = instance.getEstimatedInterventionTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDate method, of class MaintenanceActivity.
     */
    @Test
    public void testGetDate() {
        LocalDate expResult = LocalDate.parse("2021-11-20");
        LocalDate result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMaintenanceProcedure method, of class MaintenanceActivity.
     */
    @Test
    public void testGetMaintenanceProcedure() {
        MaintenanceProcedure expResult = new MaintenanceProcedure("Provasmp");
        MaintenanceProcedure result = instance.getMaintenanceProcedure();
        assertEquals(expResult.getSmp(), result.getSmp());
    }

    /**
     * Test of getMaterials method, of class MaintenanceActivity.
     */
    @Test
    public void testGetMaterials() {
        try{
            List<Material> expResult = new ArrayList<>(){{
                        add(new Material("Materiale1"));
                        add(new Material("Materiale2"));
                        add(new Material("Materiale3"));
                    }};
            List<Material> result = instance.getMaterials();
            Collections.sort(result);
            Collections.sort(expResult);
            assertEquals(expResult, result);
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }

    /**
     * Test of isInterruptibleActivity method, of class MaintenanceActivity.
     */
    @Test
    public void testIsInterruptibleActivity() {
        boolean expResult = true;
        boolean result = instance.isInterruptibleActivity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaterials method, of class MaintenanceActivity.
     */
    @Test
    public void testSetMaterials() {
        try{
            List<Material> oldMaterials = instance.getMaterials();
            List<Material> materials =  new ArrayList<>(){{
                        add(new Material("Materiale4"));
                        add(new Material("Materiale5"));
                        add(new Material("Materiale6"));
                    }};
            instance.setMaterials(materials);
            assertEquals(materials, instance.getMaterials());
            instance.setMaterials(oldMaterials);
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }

    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testToString() {
        try{
            List<Material> listMaterial = new ArrayList<>(){{
                        add(new Material("Materiale1"));
                        add(new Material("Materiale2"));
                        add(new Material("Materiale3"));
                    }};
            String expResult = '{' + "activityId=" + 1 + ", site=" + 
                    new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes").toString() + ", typology=" + "ProvaTipologia" + 
                    ", activityDescription=" + "ProvaDescrizione" + ", estimatedInterventionTime=" + 
                    120 + ", date=" + LocalDate.parse("2021-11-20").toString() + ", maintenanceProcedure="
                    + new MaintenanceProcedure("Provasmp").toString() +  ", materials=" + listMaterial.toString() + 
                    ", interruptibleActivity=" + true + '}';
            String result = instance.toString();
            assertEquals(expResult, result);
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsSameInstance() {
        try{
            List<Material> listMaterial = new ArrayList<>(){{
                        add(new Material("Materiale1"));
                        add(new Material("Materiale2"));
                        add(new Material("Materiale3"));
                    }};
            MaintenanceActivity activity = instance;
            assertTrue("EqualsTrue error", instance.equals(activity));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsSameAttributes() {
        try {
            List<Material> listMaterial = createListMaterial("Materiale1","Materiale2","Materiale3");
            MaintenanceActivity activity = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes"),
                    "ProvaTipologia","ProvaDescrizione",120,LocalDate.parse("2021-11-20"),
                    new MaintenanceProcedure("Provasmp"),listMaterial,true);
            assertTrue(instance.equals(activity));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsDifferentAttributes1() {
        try {
            List<Material> listMaterial = createListMaterial("Materiale1","Materiale2","Materiale3");
            MaintenanceActivity activity = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes"),
                    "ProvaTipologia","ProvaDescrizione",120,LocalDate.parse("2021-11-20"),
                    new MaintenanceProcedure("Provasmp"),listMaterial,true);
            assertTrue( instance.equals(activity));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsDifferentAttributes2() {
        try {
            List<Material> listMaterial = createListMaterial("Materiale1","Materiale2","Materiale3");
            MaintenanceActivity activity = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea2","ProvaWorkspaceNotes"),
                    "ProvaTipologia","ProvaDescrizione",120,LocalDate.parse("2021-11-20"),
                    new MaintenanceProcedure("Provasmp"),listMaterial,true);
            assertFalse(instance.equals(activity));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsDifferentAttributes3() {
        try {
            List<Material> listMaterial = createListMaterial("Materiale1","Materiale2","Materiale3");
            MaintenanceActivity activity = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes2"),
                    "ProvaTipologia","ProvaDescrizione",120,LocalDate.parse("2021-11-20"),
                    new MaintenanceProcedure("Provasmp"),listMaterial,true);
            assertFalse(instance.equals(activity));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsDifferentAttributes4() {
        try {
            List<Material> listMaterial = createListMaterial("Materiale1","Materiale2","Materiale3");
            MaintenanceActivity activity = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes"),
                    "ProvaTipologia2","ProvaDescrizione",120,LocalDate.parse("2021-11-20"),
                    new MaintenanceProcedure("Provasmp"),listMaterial,true);
            assertFalse(instance.equals(activity));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsDifferentAttributes5() {
        try {
            List<Material> listMaterial = createListMaterial("Materiale1","Materiale2","Materiale3");
            MaintenanceActivity activity = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes"),
                    "ProvaTipologia","ProvaDescrizione2",120,LocalDate.parse("2021-11-20"),
                    new MaintenanceProcedure("Provasmp"),listMaterial,true);
            assertFalse(instance.equals(activity));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsDifferentAttributes6() {
        try {
            List<Material> listMaterial = createListMaterial("Materiale1","Materiale2","Materiale3");
            MaintenanceActivity activity = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes"),
                    "ProvaTipologia","ProvaDescrizione",121,LocalDate.parse("2021-11-20"),
                    new MaintenanceProcedure("Provasmp"),listMaterial,true);
            assertFalse(instance.equals(activity));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsDifferentAttributes7() {
        try {
            List<Material> listMaterial = createListMaterial("Materiale1","Materiale2","Materiale3");
            MaintenanceActivity activity = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes"),
                    "ProvaTipologia","ProvaDescrizione",120,LocalDate.parse("2021-11-21"),
                    new MaintenanceProcedure("Provasmp"),listMaterial,true);
            assertFalse(instance.equals(activity));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsDifferentAttributes8() {
        try {
            List<Material> listMaterial = createListMaterial("Materiale1","Materiale2","Materiale3");
            MaintenanceActivity activity = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes"),
                    "ProvaTipologia","ProvaDescrizione",120,LocalDate.parse("2021-11-20"),
                    new MaintenanceProcedure("Provasmp2"),listMaterial,true);
            assertFalse(instance.equals(activity));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsDifferentAttributes9() {
        try {
            List<Material> listMaterial = createListMaterial("Materiale1","Materiale2","Materiale4");
            MaintenanceActivity activity = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes"),
                    "ProvaTipologia","ProvaDescrizione",120,LocalDate.parse("2021-11-20"),
                    new MaintenanceProcedure("Provasmp"),listMaterial,true);
            assertFalse(instance.equals(activity));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsDifferentAttributes10() {
        try {
            List<Material> listMaterial = createListMaterial("Materiale1","Materiale2","Materiale3");
            MaintenanceActivity activity = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes"),
                    "ProvaTipologia","ProvaDescrizione",120,LocalDate.parse("2021-11-20"),
                    new MaintenanceProcedure("Provasmp"),listMaterial,false);
            assertFalse(instance.equals(activity));
        } catch (NotValidParameterException ex) {
            fail("NotValidParameterException");
        }
    }
    
    /**
     * Test of toString method, of class MaintenanceActivity.
     */
    @Test
    public void testEqualsListMaterials() {
        try {
            List<Material> listMaterial = createListMaterial("Materiale2","Materiale1","Materiale3");
            MaintenanceActivity activity = new MaintenanceActivityImpl(1,new Site("ProvaBranchOffice","ProvaArea","ProvaWorkspaceNotes"),
                    "ProvaTipologia","ProvaDescrizione",120,LocalDate.parse("2021-11-20"),
                    new MaintenanceProcedure("Provasmp"),listMaterial,true);
            assertTrue(instance.equals(activity));
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

    public class MaintenanceActivityImpl extends MaintenanceActivity {

        public MaintenanceActivityImpl(int activityId, Site site, String typology, String activityDescription,
            int estimatedInterventionTime, LocalDate date, MaintenanceProcedure maintenanceProcedure,
            List<Material> materials,boolean interruptibleActivity) throws NotValidParameterException {
            super(activityId, site, typology, activityDescription, estimatedInterventionTime,
                    date, maintenanceProcedure, materials,interruptibleActivity);
        }
    }
    
}
