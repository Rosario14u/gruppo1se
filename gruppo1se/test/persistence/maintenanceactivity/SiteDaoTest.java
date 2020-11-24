/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Site;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class SiteDaoTest {
    private static  String URL = "jdbc:postgresql://localhost/Gruppo1_SE";
    private static  String USER = "gruppo1";
    private static  String PWD = "123456";
    private static Connection conn;
    private static final String DELETEFROMSITE = "DELETE FROM Site WHERE branchOffice = ? and area = ?";
    private static final String INSERTFROMSITE = "INSERT INTO Site (branchOffice,area,workspaceNotes) "
                    + "VALUES (?,?,?)";
    public SiteDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
          conn = DriverManager.getConnection(URL, USER, PWD);
          conn.setAutoCommit(false);
        } catch (SQLException ex) {
          Logger.getLogger(MaintenanceActivityDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(MaintenanceActivityDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of retrieveSiteDao method, of class SiteDao.
     */
    @Test
    public void testRetrieveSiteDao() {
        try {
            deleteFromSite("ProvaBranch", "ProvaBranch");
            PreparedStatement pstm = conn.prepareStatement(INSERTFROMSITE);
            pstm.setString(1, "ProvaBranch");
            pstm.setString(2, "ProvaArea");
            pstm.setString(3, "ProvaWorkSpaceNotes");
            pstm.executeUpdate();
            Site site = new SiteDao().retrieveSiteDao(new Site("ProvaBranch", "ProvaArea"), conn);
            assertEquals("branchOffice error", "ProvaBranch", site.getBranchOffice());
            assertEquals("area error", "ProvaArea", site.getArea());
            assertEquals("workspacenotes error", "ProvaWorkSpaceNotes",site.getWorkSpaceNotes());
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(SiteDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testRetrieveSiteDaoNotDatabase() {
        try {
            deleteFromSite("ProvaBranch", "ProvaBranch");
            Site site = new SiteDao().retrieveSiteDao(new Site("ProvaBranch", "ProvaArea"), conn);
            assertNull("Site is not null", site);
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(SiteDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void deleteFromSite(String officeBranch, String area) throws SQLException{
        try {
            PreparedStatement pstm = conn.prepareStatement(DELETEFROMSITE);
            pstm.setString(1, officeBranch);
            pstm.setString(2, area);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
}
