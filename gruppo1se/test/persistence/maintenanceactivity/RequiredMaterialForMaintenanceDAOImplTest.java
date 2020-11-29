/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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
import static org.junit.Assert.*;
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class RequiredMaterialForMaintenanceDAOImplTest {
    private static Connection conn;
    private static String DELETEASSOCIATIONMATERIALTOACTIVITY = "DELETE FROM RequiredMaterial where activityId = ?";
    private static String ASSOCIATEMATERIALTOACTIVITY = "INSERT INTO RequiredMaterial values(?,?),(?,?),(?,?)";
    
    public RequiredMaterialForMaintenanceDAOImplTest() {
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

    /**
     * This method assert that retrieveMaterialsByActivityIdInDatabase correctly return List of material
     */
    @Test
    public void testRetrieveMaterialsByActivityIdInDatabase() {
        try {
            PreparedStatement pstm = conn.prepareStatement(DELETEASSOCIATIONMATERIALTOACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            pstm = conn.prepareStatement(ASSOCIATEMATERIALTOACTIVITY);
            pstm.setInt(1, 1);
            pstm.setString(2, "Rame");
            pstm.setInt(3, 1);
            pstm.setString(4, "Ferro");
            pstm.setInt(5, 1);
            pstm.setString(6, "Legno");
            pstm.executeUpdate();
            RequiredMaterialForMaintenanceDAOImpl materialForMaintenanceDao = new RequiredMaterialForMaintenanceDAOImpl();
            List<Material> listMaterials= materialForMaintenanceDao.retrieveMaterialsByActivityId(1);
            assertEquals("listMaterials lenght error", 3,listMaterials.size());
            assertEquals("requiredMaterial error","Ferro",listMaterials.get(0).getName());
            assertEquals("requiredMaterial error","Legno",listMaterials.get(1).getName());
            assertEquals("requiredMaterial error","Rame",listMaterials.get(2).getName());
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    
    /**
     * This method assert that retrieveMaterialsByActivityIdInDatabase correctly return empty list when <br>
     * there aren't material associated to activity
     */
    @Test
    public void testRetrieveMaterialsByActivityIdNotInDatabase() {
        try {
            PreparedStatement pstm = conn.prepareStatement(DELETEASSOCIATIONMATERIALTOACTIVITY);
            pstm.setInt(1, 1);
            pstm.executeUpdate();
            RequiredMaterialForMaintenanceDAOImpl materialForMaintenanceDao = new RequiredMaterialForMaintenanceDAOImpl();
            List<Material> listMaterials;
            listMaterials= materialForMaintenanceDao.retrieveMaterialsByActivityId(1);
            assertTrue("requiredMaterial error", listMaterials.isEmpty());        
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (MaterialException ex) {
            fail("MaterialException");
        }
    }
    
}
