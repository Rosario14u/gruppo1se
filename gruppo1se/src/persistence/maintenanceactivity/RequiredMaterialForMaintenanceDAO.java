/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rosar
 */
public class RequiredMaterialForMaintenanceDAO {
    public List<String> retrieveMaterialsByActivityId(int activityId, Connection conn){
        List<String> listMaterials = new ArrayList<>();
        try {
            String query = "SELECT * FROM RequiredMaterial WHERE activityId = ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1,activityId);            
            ResultSet res = pstm.executeQuery(); 
            
            while(res.next()){
                listMaterials.add(res.getString("materialName"));
            }            
            return listMaterials;
        } catch (SQLException ex) {
            Logger.getLogger(RequiredMaterialForMaintenanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }
}
