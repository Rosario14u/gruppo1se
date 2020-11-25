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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rosar
 */
public class SiteDao {
    /**
     * This method returns the site according to branchOffice and area attributes of the site object
     * @param branchOffice branchoffice of the required site
     * @param area area of the required site 
     * @param conn
     * @return {@code Site} site object if exists the corresponding site in the database, null otherwise
     */
    /*Method developed by Rosario Gaeta*/
    public Site retrieveSiteDao(String branchOffice, String area, Connection conn){ 
       try {
           String query = "SELECT * FROM Site WHERE branchOffice = ? and area = ?";
           PreparedStatement pstm = conn.prepareStatement(query);
           pstm.setString(1,branchOffice);
           pstm.setString(2,area);
           ResultSet rs = pstm.executeQuery();
           rs.next();
           Site site = new Site(branchOffice, area, rs.getString("workspaceNotes"));
           return site;

       } catch (SQLException ex) {

           return null;
       }
    }
}
