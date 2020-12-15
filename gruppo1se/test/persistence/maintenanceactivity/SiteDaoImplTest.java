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
    private static final String INSERT_INTO_SITE = "INSERT INTO Site (branchOffice,area,workspaceNotes) "
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
    
    
    /*======================================================test retrieveSiteDao==========================================================*/
    
    /*Test of retrieveSiteDao method developed by Rosario Gaeta*/
    
    /**
     * This method asserts that retrieveSiteDao correctly returns a Site object<br>
     * when there is in database a site with the required branchOffice and area.
     */
    @Test
    public void testRetrieveSiteDao() {
        try {
            deleteFromSite("ProvaBranch", "ProvaBranch");
            PreparedStatement pstm = conn.prepareStatement(INSERT_INTO_SITE);
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
    
    /**
     * This method allows to delete a site from database.
     * @param officeBranch
     * @param area
     * @throws SQLException 
     */
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
    
    //==========================================Test addSite and deleteSite=============================================================
    /*Test methods developed by Alessio Citro*/
    
    /*Utility method developed by Alessio Citro*/
    private void insertIntoSite(String branchOffice, String area, String workspaceNotes) throws SQLException{
        PreparedStatement pstm = conn.prepareStatement(INSERT_INTO_SITE);
        pstm.setString(1, branchOffice);
        pstm.setString(2, area);
        pstm.setString(3, workspaceNotes);
        pstm.executeUpdate();
    }
    
    /**
     * 
     * @throws SiteException 
     */
    @Test
    public void testAddSite() throws SiteException{
        try {
            System.out.println("addSite");
            Site site = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes");
            deleteFromSite(site.getBranchOffice(), site.getArea());
            boolean result = siteDao.addSite(site);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    /**
     * 
     * @throws SiteException 
     */
    @Test(expected = SiteException.class)
    public void testAddSiteException() throws SiteException{
        try {
            System.out.println("addSiteException");
            Site site = null;
            boolean result = siteDao.addSite(site);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    /**
     * 
     * @throws SiteException 
     */
    @Test
    public void testDeleteSite() throws SiteException{
        try {
            System.out.println("deleteSite");
            Site site = new Site("ProvaBranchOffice", "ProvaArea", "ProvaWorkspaceNotes");
            deleteFromSite(site.getBranchOffice(), site.getArea());
            insertIntoSite(site.getBranchOffice(), site.getArea(), site.getWorkSpaceNotes());
            boolean result = siteDao.deleteSite(site);
            assertEquals(true, result);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
    /**
     * 
     * @throws SiteException 
     */
    @Test(expected = SiteException.class)
    public void testDeleteSiteException() throws SiteException{
        try {
            System.out.println("deleteSiteException");
            Site site = null;
            boolean result = siteDao.deleteSite(site);
            conn.rollback();
        } catch (SQLException ex) {
            System.out.println("Error on: connection rollback");
        }
    }
    
}
