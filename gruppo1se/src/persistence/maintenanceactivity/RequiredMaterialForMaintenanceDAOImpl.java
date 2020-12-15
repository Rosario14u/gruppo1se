/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Material;
import exception.MaterialException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class RequiredMaterialForMaintenanceDAOImpl implements RequiredMaterialForMaintenanceDAO{
    private final static String SELECT_REQUIRED_MATERIAL_BY_ACTIVITY_ID = "SELECT * FROM RequiredMaterial "
            + "WHERE activityId = ? order by materialName";
    private final static String INSERT_REQUIRED_MATERIAL = "INSERT INTO requiredmaterial VALUES (?,?)";
    private final static String DELETE_REQUIRED_MATERIAL = "DELETE FROM requiredmaterial "
            + "WHERE (activityid = ?) and (materialname = ?)";
    private final static String SELECT_AVAILABLE_MATERIAL = "SELECT * FROM material EXCEPT "
            + "SELECT materialname FROM requiredmaterial WHERE activityid=?";
    
    /**
     * This method retrieve a list materials associated to the maintenance activity identified by the activityId.
     * @param activityId of the MaintenanceActivity
     * @return {@code List<String>} listMaterials, null otherwise
     * @throws exception.MaterialException it ther is an error in retrieving material associated <br> to this activity id
     */
    /*Method developed by Rosario Gaeta*/
    @Override
    public List<Material> retrieveMaterialsByActivityId(int activityId) throws MaterialException{
        List<Material> listMaterials = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_REQUIRED_MATERIAL_BY_ACTIVITY_ID);
            pstm.setInt(1,activityId);            
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                listMaterials.add(new Material(res.getString("materialName")));
            }
            return listMaterials;
        } catch (SQLException ex) {
            throw new MaterialException("Material retriving failed");
        }        
    }
    
 
    //Developed by Antonio Gorrasi
    @Override
     public boolean addRequiredMaterial(int activityId, List<Material> requiredMaterial) throws MaterialException{
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            for(Material material : requiredMaterial){
                PreparedStatement pstm = conn.prepareStatement(INSERT_REQUIRED_MATERIAL);
                pstm.setInt(1,activityId);            
                pstm.setString(2,material.getName());            
                pstm.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            throw new MaterialException("Inserting material failed");
        }
    }
    
    //Developed by Antonio Gorrasi
    @Override
    public boolean removeRequiredMaterial(int activityId, List<Material> requiredMaterial) throws MaterialException{
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            int notRemovedCounter = 0;
            for(Material material : requiredMaterial){
                PreparedStatement pstm = conn.prepareStatement(DELETE_REQUIRED_MATERIAL);
                pstm.setInt(1,activityId);            
                pstm.setString(2,material.getName());
                if(pstm.executeUpdate() == 0){
                    notRemovedCounter++;
                }
            }
            return !(notRemovedCounter>0);
        } catch (SQLException ex) {
            throw new MaterialException("Deleting material failed");
        }
    }
    
    //Developed by Antonio Gorrasi
    @Override
    public List<Material> retrieveAvailableMaterialToAdd(int activityId) throws MaterialException{
        List<Material> listMaterials = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_AVAILABLE_MATERIAL);
            pstm.setInt(1,activityId);           
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                listMaterials.add(new Material(res.getString("materialName")));
            }
            return listMaterials;
        } catch (SQLException ex) {
            throw new MaterialException("Retrieving avaliable material to use in Maintenance Activity failed");
        }
    }
}
