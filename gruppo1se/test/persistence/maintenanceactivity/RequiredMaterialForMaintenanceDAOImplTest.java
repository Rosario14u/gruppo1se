/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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
import static org.junit.Assert.*;
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class RequiredMaterialForMaintenanceDAOImplTest {
    private static Connection conn;
    private static String DELETEASSOCIATIONMATERIALTOACTIVITY = "DELETE FROM RequiredMaterial where activityId = ?"
            + " or activityId = ?";
    private static String ASSOCIATEMATERIALTOACTIVITY = "INSERT INTO RequiredMaterial values(?,?),(?,?),(?,?),(?,?)";
    
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
    }

    /**
     * Test of retrieveMaterialsByActivityId method, of class RequiredMaterialForMaintenanceDAOImpl.
     */
    @Test
    public void testRetrieveMaterialsByActivityIdInDatabase() {
        try {
            PreparedStatement pstm = conn.prepareStatement(DELETEASSOCIATIONMATERIALTOACTIVITY);
            pstm.setInt(1, 1);
            pstm.setInt(2, 2);
            pstm.executeUpdate();
            pstm = conn.prepareStatement(ASSOCIATEMATERIALTOACTIVITY);
            pstm.setInt(1, 1);
            pstm.setString(2, "Rame");
            pstm.setInt(3, 1);
            pstm.setString(4, "Ferro");
            pstm.setInt(5, 2);
            pstm.setString(6, "Acciaio");
            pstm.setInt(7, 1);
            pstm.setString(8, "Legno");
            pstm.executeUpdate();
            RequiredMaterialForMaintenanceDAOImpl materialForMaintenanceDao = new RequiredMaterialForMaintenanceDAOImpl();
            List<Material> listMaterials= materialForMaintenanceDao.retrieveMaterialsByActivityId(1);
            assertEquals("listMaterials lenght error", 3,listMaterials.size());
            assertEquals("requiredMaterial error","Ferro",listMaterials.get(0).getName());
            assertEquals("requiredMaterial error","Legno",listMaterials.get(1).getName());
            assertEquals("requiredMaterial error","Rame",listMaterials.get(2).getName());
            listMaterials = materialForMaintenanceDao.retrieveMaterialsByActivityId(2);
            assertEquals("listMaterials lenght error", 1,listMaterials.size());
            assertEquals("requiredMaterial error","Acciaio",listMaterials.get(0).getName());
            conn.rollback();
        } catch (SQLException ex) {
            assertNull("SQLException", ex);
            Logger.getLogger(RequiredMaterialForMaintenanceDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       /**
     * Test of retrieveMaterialsByActivityId method, of class RequiredMaterialForMaintenanceDAOImpl.
     */
    @Test
    public void testRetrieveMaterialsByActivityIdNotInDatabase() {
        try {
            PreparedStatement pstm = conn.prepareStatement(DELETEASSOCIATIONMATERIALTOACTIVITY);
            pstm.setInt(1, 1);
            pstm.setInt(2, 2);
            pstm.executeUpdate();
            RequiredMaterialForMaintenanceDAOImpl materialForMaintenanceDao = new RequiredMaterialForMaintenanceDAOImpl();
            List<Material> listMaterials;
            for(int i = 1; i < 3; i++){
                listMaterials= materialForMaintenanceDao.retrieveMaterialsByActivityId(i);
                assertTrue("requiredMaterial error", listMaterials.isEmpty());
            }
            conn.rollback();
        } catch (SQLException ex) {
            assertNull("SQLException", ex);
            Logger.getLogger(RequiredMaterialForMaintenanceDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
