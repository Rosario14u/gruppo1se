/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class RequiredMaterialForMaintenanceDAO {
    /**
     * This method retrieve a list materials associated to the maintenance activity identified by the activityId.
     * @param activityId of the MaintenanceActivity
     * @return {@code List<String>} listMaterials, null otherwise
     */
    /*Method developed by Rosario Gaeta*/
    public List<Material> retrieveMaterialsByActivityId(int activityId){
        List<Material> listMaterials = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            String query = "SELECT * FROM RequiredMaterial WHERE activityId = ? order by materialName";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1,activityId);            
            ResultSet res = pstm.executeQuery(); 
            
            while(res.next()){
                listMaterials.add(new Material(res.getString("materialName")));
            }            
            return listMaterials;
        } catch (SQLException ex) {
            Logger.getLogger(RequiredMaterialForMaintenanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }
    
    
    public boolean addRequiredMaterial(int activityId, List<Material> requiredMaterial){
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            for(Material material : requiredMaterial){
                String query = "INSERT INTO requiredmaterial VALUES (?,?)";
                PreparedStatement pstm = conn.prepareStatement(query);
                pstm.setInt(1,activityId);            
                pstm.setString(2,material.getName());            
                pstm.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RequiredMaterialForMaintenanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean removeRequiredMaterial(int activityId, List<Material> requiredMaterial){
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            for(Material material : requiredMaterial){
                String query = "DELETE FROM requiredmaterial WHERE (activityid = ?) and (materialname = ?)";
                PreparedStatement pstm = conn.prepareStatement(query);
                pstm.setInt(1,activityId);            
                pstm.setString(2,material.getName());            
                pstm.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RequiredMaterialForMaintenanceDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
