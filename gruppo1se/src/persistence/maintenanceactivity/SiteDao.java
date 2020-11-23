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
    public Site retrieveSiteDao(Site site){
        String url = "jdbc:postgresql://localhost/Gruppo1_SE";
       String user = "gruppo1";
       String pwd = "123456"; 
       try {
           Connection conn = DriverManager.getConnection(url, user, pwd);
           String query = "SELECT * FROM Site WHERE branchOffice = ? and area = ?";
           PreparedStatement pstm = conn.prepareStatement(query);
           pstm.setString(1,site.getBranchOffice());
           pstm.setString(2,site.getArea());
           ResultSet rs = pstm.executeQuery();
           rs.next();
           site.setWorkSpaceNotes(rs.getString("workSpaceNotes"));
           return site;

       } catch (SQLException ex) {
           return null;
       }
    }
}
