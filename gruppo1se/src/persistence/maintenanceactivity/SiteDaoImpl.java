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
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class SiteDaoImpl implements SiteDao {
    private final static String SELECT_SITE = "SELECT * FROM Site WHERE branchOffice = ? and area = ?";
    private final String SQL_INSERT = "INSERT INTO Site VALUES (?,?,?)";
    private final String SQL_DELETE = "DELETE FROM Site WHERE (branchoffice, area) = (?,?)";
    private final String SQL_SELECT = "SELECT * FROM Site";
    private final String SQL_UPDATE = "UPDATE Site SET branchOffice = ? , area = ?, workspaceNotes = ? WHERE (branchOffice, area) = (?,?)";
    
    /**
     * This method returns the site according to branchOffice and area attributes of the site object
     * @param branchOffice branchoffice of the required site
     * @param area area of the required site 
     * @return {@code Site} site object if exists the corresponding site in the database, null otherwise
     * @throws exception.SiteException
     */
    /*Method developed by Rosario Gaeta*/
    @Override
    public Site retrieveSiteDao(String branchOffice, String area) throws SiteException{
       try {
           Site site = null;
           Connection conn = ConnectionDB.getInstanceConnection().getConnection();
           PreparedStatement pstm = conn.prepareStatement(SELECT_SITE);
           pstm.setString(1,branchOffice);
           pstm.setString(2,area);
           ResultSet rs = pstm.executeQuery();
           while(rs.next()){
            site = new Site(branchOffice, area, rs.getString("workspaceNotes"));
           }
           return site;

       } catch (SQLException ex) {
           throw new SiteException("Site retrieving failed");
       }
    }
    
    /**
     * 
     * @param site
     * @return {@code boolean} true if site is inserted into database
     * @throws SiteException if there's an SQL error inserting into site table
     */
    /*Developed by Alessio Citro*/
    @Override
    public boolean addSite(Site site) throws SiteException {
        if (site == null)
            throw new SiteException();
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, site.getBranchOffice());
            stmt.setString(2, site.getArea());
            stmt.setString(3, site.getWorkSpaceNotes());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new SiteException();
        }
    }

    /**
     * 
     * @param site
     * @return {@code boolean} true if site is deleted from database
     * @throws SiteException if there's an SQL error deleting from site table
     */
    /*Developed by Alessio Citro*/
    @Override
    public boolean deleteSite(Site site) throws SiteException {
        if (site == null)
            throw new SiteException();
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, site.getBranchOffice());
            stmt.setString(2, site.getArea());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new SiteException();
        }
    }
    
    /**
     * 
     * @return {@code List<Site>} the list of sites, representing all the sites stored into the database
     * @throws SiteException if there's an SQL error while selecting from the Site table
     */
    /*Developed by Vincenza Coppola*/
    @Override
    public List<Site> viewSites() throws SiteException {
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(SQL_SELECT);
            return this.makeSitesList(res);
        } catch (SQLException ex) {
            throw new SiteException();
        }
    }
    
    /**
     * 
     * @param res ResultSet containing all the entries from the site table
     * @return{@code List<Site>} a list of Sites, representing all the sites stored into the database
     * @throws SiteException if there's an SQL error while looping through the ResultSet
     */
    /*Developed by Vincenza Coppola*/
    private List<Site> makeSitesList (ResultSet res) throws SiteException{
        List<Site> sites = new ArrayList<>();
        try {
            while(res.next()){
                sites.add(new Site(res.getString("BranchOffice"),res.getString("Area"), res.getString("WorkspaceNotes")));
            }         
            return sites;
        } catch (SQLException ex) {
            throw new SiteException();
        }
    }
    
    /**
     * 
     * @param oldSite
     * @param newSite
     * @return{@code boolean }true if the site is updated into the database
     * @throws SiteException if there's an SQL error while updating into the Site table
     * @throws exception.NotValidParameterException if one of the parameters is null.
     */
    /*Developed by Vincenza Coppola*/
    @Override
    public boolean modifySite(Site oldSite, Site newSite) throws SiteException, NotValidParameterException {
        if(oldSite == null || newSite == null)
            throw new NotValidParameterException();
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, newSite.getBranchOffice());
            stmt.setString(2, newSite.getArea());
            stmt.setString(3, newSite.getWorkSpaceNotes());
            stmt.setString(4, oldSite.getBranchOffice());
            stmt.setString(5, oldSite.getArea());
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new SiteException();
        }
    }
}
