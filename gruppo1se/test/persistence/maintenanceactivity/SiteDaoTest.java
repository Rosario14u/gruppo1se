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
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class SiteDaoTest {
    private static Connection conn;
    private static final String DELETEFROMSITE = "DELETE FROM Site WHERE branchOffice = ? and area = ?";
    private static final String INSERTFROMSITE = "INSERT INTO Site (branchOffice,area,workspaceNotes) "
                    + "VALUES (?,?,?)";
    public SiteDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        try {
            conn = ConnectionDB.getInstanceConnection().getConnection();
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(MaintenanceActivityDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            conn.setAutoCommit(true);
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
     * This method asserts that retrieveSiteDao correctly returns a Site object<br>
     * when there is in database a site with the required branchOffice and area.
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
            Site site = new SiteDao().retrieveSiteDao("ProvaBranch", "ProvaArea");
            assertEquals("branchOffice error", "ProvaBranch", site.getBranchOffice());
            assertEquals("area error", "ProvaArea", site.getArea());
            assertEquals("workspacenotes error", "ProvaWorkSpaceNotes",site.getWorkSpaceNotes());
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(SiteDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method asserts that retrieveSiteDao correctly returns null<br>
     * when there is not the required site in database. 
     */
    @Test
    public void testRetrieveSiteDaoNotDatabase() {
        try {
            deleteFromSite("ProvaBranch", "ProvaBranch");
            Site site = new SiteDao().retrieveSiteDao("ProvaBranch", "ProvaArea");
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
