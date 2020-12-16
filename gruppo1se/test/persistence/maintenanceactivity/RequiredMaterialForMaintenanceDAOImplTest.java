/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import business.maintenanceactivity.Material;
import exception.MaterialException;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.Assert.*;
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class RequiredMaterialForMaintenanceDAOImplTest {
    private static Connection conn;
    private static String DELETE_ASSOCIATION_MATERIAL_TO_ACTIVITY = "DELETE FROM RequiredMaterial where activityId = ?";
    private static String ASSOCIATE_MATERIAL_TO_ACTIVITY = "INSERT INTO RequiredMaterial values(?,?),(?,?),(?,?)";
    private static String SELECT_MATERIAL_WITH_ACTIVITY_ID = "SELECT materialname"
            + " FROM RequiredMaterial WHERE activityId = ? ORDER BY materialname";
    private static String INSERT_MATERIAL_FOR_ACTIVITY = "INSERT INTO RequiredMaterial VALUES (?,?)";
    private static String DELETE_MATERIAL = "DELETE FROM material";
    private static String INSERT_MATERIAL = "INSERT INTO material VALUES (?)";
    private RequiredMaterialForMaintenanceDAOImpl materialForMaintenanceDao;
    public RequiredMaterialForMaintenanceDAOImplTest() {
        materialForMaintenanceDao= new RequiredMaterialForMaintenanceDAOImpl();
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = ConnectionDB.getInstanceConnection().getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(RequiredMaterialForMaintenanceDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(RequiredMaterialForMaintenanceDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        try {
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(RequiredMaterialForMaintenanceDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*=====================================================test retrieveMaterialsByActivityId=================================================*/

    /*Test retrieveMaterialsByActivityId method developed by Rosario Gaeta*/
    
    /**
     * This method assert that retrieveMaterialsByActivityIdInDatabase correctly return a List of materials.
     */
    @Test
    public void testRetrieveMaterialsByActivityIdInDatabase() {
        try {
            List<Material> expectedResult = new ArrayList<>() {{
                        add(new Material("Rame"));
                        add(new Material("Ferro"));
                        add(new Material("Legno"));
                    }};
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_MATERIAL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            pstm = conn.prepareStatement(ASSOCIATE_MATERIAL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.setString(2, expectedResult.get(0).getName());
            pstm.setInt(3, 1);
            pstm.setString(4, expectedResult.get(1).getName());
            pstm.setInt(5, 1);
            pstm.setString(6, expectedResult.get(2).getName());
            pstm.executeUpdate();
            List<Material> result= materialForMaintenanceDao.retrieveMaterialsByActivityId(1);
            assertTrue("retrieveMaterialsByActivityIdInDatabase error",(expectedResult.size() == result.size()) 
                    && (result.containsAll(expectedResult)));
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    
    /**
     * This method assert that retrieveMaterialsByActivityIdInDatabase correctly return empty list when <br>
     * there aren't material associated to activity.
     */
    @Test
    public void testRetrieveMaterialsByActivityIdNotInDatabase() {
        try {
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_MATERIAL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            List<Material> result = materialForMaintenanceDao.retrieveMaterialsByActivityId(1);
            assertTrue("requiredMaterial error", result.isEmpty());        
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    
    
//================================================================================================================================
    
    
    /**
     * This test assert that the method addRequiredMaterial 
     * correctly associate a list of materials to maintenance activity
     */
    @Test
    public void testAddRequiredMaterial(){
        try {
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_MATERIAL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            List<Material> listMaterials = new ArrayList<>();
            
            for(int i='a';i<='z';i++){
                listMaterials.add(new Material(String.valueOf(Character.toChars(i))));
            }
            
            assertTrue(materialForMaintenanceDao.addRequiredMaterial(1, listMaterials));
            
            pstm = conn.prepareStatement(SELECT_MATERIAL_WITH_ACTIVITY_ID);
            pstm.setInt(1, 1);
            ResultSet res = pstm.executeQuery();
            
            for(int i=0;res.next();i++){
                assertEquals(listMaterials.get(i).getName(), res.getString("materialName"));
                if(res.isLast()){
                    assertEquals(listMaterials.size(),res.getRow());
                }
            }  
        }catch(SQLException | MaterialException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
    
    /**
     * This test assert that the method removeRequiredMaterial 
     * correctly removes a list of materials associated 
     * with a maintenance task (present in the database)
     */
    @Test
    public void testRemoveRequiredMaterialInDatabase(){
        try {
            List<Material> listMaterials = new ArrayList<>();
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_MATERIAL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            
            pstm = conn.prepareStatement(INSERT_MATERIAL_FOR_ACTIVITY);
            for(int i='a';i<='z';i++){
                pstm.setInt(1, 1);
                pstm.setString(2, String.valueOf(Character.toChars(i)));
                pstm.executeUpdate();
                listMaterials.add(new Material(String.valueOf(Character.toChars(i))));
            }
            
            assertTrue(materialForMaintenanceDao.removeRequiredMaterial(1, listMaterials));
            
            pstm = conn.prepareStatement(SELECT_MATERIAL_WITH_ACTIVITY_ID);
            pstm.setInt(1, 1);
            ResultSet res = pstm.executeQuery();
            
            //returns false if there are no more rows to return
            assertFalse(res.next());
        }catch(SQLException | MaterialException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
    
    /**
     * This test assert that the method removeRequiredMaterial 
     * correctly returns false in an attempt to delete
     * materials not present in the database
     */
    @Test
    public void testRemoveRequiredMaterialNotInDatabase(){
        try {
            List<Material> listMaterials = new ArrayList<>();
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_MATERIAL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            
            for(int i='a';i<='z';i++){
                listMaterials.add(new Material(String.valueOf(Character.toChars(i))));
            }
            
            assertFalse(materialForMaintenanceDao.removeRequiredMaterial(1, listMaterials));
            
            pstm = conn.prepareStatement(SELECT_MATERIAL_WITH_ACTIVITY_ID);
            pstm.setInt(1, 1);
            ResultSet res = pstm.executeQuery();
            
            //returns false if there are no more rows to return
            assertFalse(res.next());
        }catch(SQLException | MaterialException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
    
    /**
     * This test asserts that the method retrieveAvailableMaterialToAdd 
     * correctly returns a list of all the materials available but 
     * which have not yet been associated with the maintenance activity
     */
    @Test
    public void testRetrieveAvailableMaterialToAdd(){
        try {
            List<Material> expectedMaterialsList = new ArrayList<>();
            List<Material> actualMaterialsList = new ArrayList<>();
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_MATERIAL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            
            Statement stm = conn.createStatement();
            stm.executeUpdate(DELETE_MATERIAL);
            
            pstm = conn.prepareStatement(INSERT_MATERIAL);
            
            for(int i='a';i<='z';i++){
                pstm.setString(1, String.valueOf(Character.toChars(i)));
                pstm.executeUpdate();
                expectedMaterialsList.add(new Material(String.valueOf(Character.toChars(i))));
            }
            
            actualMaterialsList = materialForMaintenanceDao.retrieveAvailableMaterialToAdd(1);
            
            Collections.sort(actualMaterialsList);
            assertEquals(expectedMaterialsList,actualMaterialsList);
        }catch(SQLException | MaterialException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
    
    /**
     * This test asserts that the method correctly returns
     * an empty list 
     */
    @Test
    public void testRetrieveAvaliableMaterialToAddEmpty(){
        try {
            List<Material> expectedMaterialsList = new ArrayList<>();
            List<Material> actualMaterialsList = new ArrayList<>();
            PreparedStatement pstm = conn.prepareStatement(DELETE_ASSOCIATION_MATERIAL_TO_ACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            
            Statement stm = conn.createStatement();
            stm.executeUpdate(DELETE_MATERIAL);
            
            actualMaterialsList = materialForMaintenanceDao.retrieveAvailableMaterialToAdd(1);
            
            assertEquals(expectedMaterialsList,actualMaterialsList);
        }catch(SQLException | MaterialException ex){
            fail(ex.getClass().getName() + " - " + ex.getMessage());
        } 
    }
    
}
