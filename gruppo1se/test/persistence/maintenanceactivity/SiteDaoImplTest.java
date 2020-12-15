/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Site;
import exception.NotValidParameterException;
import exception.SiteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
//==========================================Test viewSites and updateSite=============================================================
    /*Test methods developed by Vincenza Coppola*/
    
    /**
     * 
     * @throws SiteException
     * @throws SQLException 
     */
    @Test
    public void testViewSites() throws SiteException, SQLException{
        System.out.println("viewSites");
        Site site1 = new Site("BranchOffice1","Area1","WorkspaceNotes1");
        Site site2 = new Site("BranchOffice2","Area2","WorkspaceNotes2");
        Site site3 = new Site("BranchOffice3","Area3","WorkspaceNotes3");
        deleteAllFromSite();
        List<Site> expectedList = new ArrayList<>();
        expectedList.add(site1);
        expectedList.add(site2);
        expectedList.add(site3);
        List<Site> siteList = new ArrayList<>();
        insertIntoSite(site1.getBranchOffice(),site1.getArea(),site1.getWorkSpaceNotes());
        insertIntoSite(site2.getBranchOffice(),site2.getArea(),site2.getWorkSpaceNotes());
        insertIntoSite(site3.getBranchOffice(),site3.getArea(),site3.getWorkSpaceNotes());
        siteList = siteDao.viewSites();
        assertEquals(true,siteList.equals(expectedList));
        conn.rollback();
    }

    /**
     * 
     * @throws SiteException
     * @throws SQLException
     * @throws NotValidParameterException 
     */
    @Test
    public void testModifySite() throws SiteException, SQLException, NotValidParameterException{
        System.out.println("modifySite");
        Site site1 = new Site("BranchOffice1","Area1","WorkspaceNotes1");
        Site site2 = new Site("BranchOffice2","Area2","WorkspaceNotes2");
        deleteAllFromSite();
        insertIntoSite(site1.getBranchOffice(),site1.getArea(),site1.getWorkSpaceNotes());
        boolean result = siteDao.modifySite(site1,site2);
        ResultSet set = selectSiteDefault();
        if(set.next()){
            assertEquals(set.getString("BranchOffice"),site2.getBranchOffice());
            assertEquals(set.getString("Area"),site2.getArea());
            assertEquals(set.getString("WorkspaceNotes"),site2.getWorkSpaceNotes());
            assertEquals(true, result);
        }
    }    
    
    /*Utility methods developed by Vincenza Coppola*/
    /**
     * Deletes all rows from Site table.
     * @throws SQLException 
     */
    private void deleteAllFromSite() throws SQLException{
        String delete = "DELETE FROM Site";
        Statement stm = conn.createStatement();
        stm.executeUpdate(delete);
    }
    
    /**
     * 
     * @return {@code ResultSet} the ResultSet containing all Site table's rows.
     * @throws SQLException 
     */
    private ResultSet selectSiteDefault() throws SQLException{
        String select = "SELECT * FROM Site";
        Statement stm = conn.createStatement();
        return stm.executeQuery(select);
    }
}
