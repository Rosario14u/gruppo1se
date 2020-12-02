/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Site;
import exception.SiteException;
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
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class SiteDaoImplTest {
    private static Connection conn;
    private static final String DELETE_FROM_SITE = "DELETE FROM Site WHERE branchOffice = ? and area = ?";
    private static final String INSERT_FROM_SITE = "INSERT INTO Site (branchOffice,area,workspaceNotes) "
                    + "VALUES (?,?,?)";
    SiteDao siteDao;
    public SiteDaoImplTest() {
        siteDao = new SiteDaoImpl();
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = ConnectionDB.getInstanceConnection().getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(SiteDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SiteDaoImplTest.class.getName()).log(Level.SEVERE, null, ex);
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
     * This method asserts that retrieveSiteDao correctly returns a Site object<br>
     * when there is in database a site with the required branchOffice and area.
     */
    /*Test method developed by Rosario Gaeta*/
    @Test
    public void testRetrieveSiteDao() {
        try {
            deleteFromSite("ProvaBranch", "ProvaBranch");
            PreparedStatement pstm = conn.prepareStatement(INSERT_FROM_SITE);
            pstm.setString(1, "ProvaBranch");
            pstm.setString(2, "ProvaArea");
            pstm.setString(3, "ProvaWorkspaceNotes");
            pstm.executeUpdate();
            Site site = siteDao.retrieveSiteDao("ProvaBranch", "ProvaArea");
            assertEquals("retrieveSiteDao", site, new Site("ProvaBranch", "ProvaArea", "ProvaWorkspaceNotes"));
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SiteException ex) {
            fail("SiteException");
        }
    }
    /**
     * This method asserts that retrieveSiteDao correctly returns null<br>
     * when there is not the required site in database. 
     */
    /*Test method developed by Rosario Gaeta*/
    @Test
    public void testRetrieveSiteDaoNotDatabase() {
        try {
            deleteFromSite("ProvaBranch", "ProvaBranch");
            Site site = new SiteDaoImpl().retrieveSiteDao("ProvaBranch", "ProvaArea");
            assertNull("retrieveSiteDaoNotDatabase error", site);
        } catch (SQLException ex) {
            fail("SQLException");
        } catch (SiteException ex) {
            fail("SiteException");
        }

    }
    /*Utility method developed by Rosario Gaeta*/
    private void deleteFromSite(String officeBranch, String area) throws SQLException{
        try {
            PreparedStatement pstm = conn.prepareStatement(DELETE_FROM_SITE);
            pstm.setString(1, officeBranch);
            pstm.setString(2, area);
            pstm.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
}
