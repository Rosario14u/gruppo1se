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
    
 
    /**
     * this method allows to associate materials to maintenance
     * activities according to activityId parameter
     * @param activityId Identifier of maintenance activity
     * @param requiredMaterial List of materials to add
     * @return {@code true} if the materials are successfully 
     * associated, {@code false} otherwise
     * @throws MaterialException if there are problems in adding materials
     */
    //Developed by Antonio Gorrasi
    @Override
     public boolean addRequiredMaterial(int activityId, List<Material> requiredMaterial) throws MaterialException{
        try {
            if(requiredMaterial != null && !requiredMaterial.isEmpty()){
                String query = queryBuilder(requiredMaterial.size(), INSERT_REQUIRED_MATERIAL, ",(?,?)");
                Connection conn = ConnectionDB.getInstanceConnection().getConnection();
                PreparedStatement pstm = conn.prepareStatement(query);
                int i=0;
                for(Material material: requiredMaterial){
                    pstm.setInt(i+1, activityId);
                    pstm.setString(i+2, String.valueOf(material));
                    i += 2;
                }
                return pstm.executeUpdate() == requiredMaterial.size();
            }
            return false;
        } catch (SQLException ex) {
            throw new MaterialException("Inserting material failed");
        }
    }
    
     
    /**
     * this method allows to delete materials previously
     * associated with maintenance activities
     * according to activityId parameter
     * @param activityId Identifier of maintenance activity
     * @param requiredMaterial List of materials to remove
     * @return {@code true} if the materials are successfully 
     * removed, {@code false} otherwise
     * @throws MaterialException if there are problems in adding materials
     */ 
    //Developed by Antonio Gorrasi
    @Override
    public boolean removeRequiredMaterial(int activityId, List<Material> requiredMaterial) throws MaterialException{
        try {
            if(requiredMaterial != null && !requiredMaterial.isEmpty()){
                String query = queryBuilder(requiredMaterial.size(), DELETE_REQUIRED_MATERIAL, 
                        " or (activityid = ?) and (materialname = ?)");
                Connection conn = ConnectionDB.getInstanceConnection().getConnection();
                PreparedStatement pstm = conn.prepareStatement(query);
                int i=0;
                for(Material material: requiredMaterial){
                    pstm.setInt(i+1, activityId);
                    pstm.setString(i+2, String.valueOf(material));
                    i += 2;
                }
                return pstm.executeUpdate() == requiredMaterial.size();
            }
            return false;
        } catch (SQLException ex) {
            throw new MaterialException("Deleting material failed");
        }
    }
    
    
    /**
     * this method returns a list of available materials that
     * have not yet been associated with the maintenance activity
     * @param activityId Identifier of maintenance activity
     * @return a list of available materials
     * @throws MaterialException if there are problems in adding materials
     */
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
    
    
    /**
     * This method allows to create a query
     * @param size the number of times you need to chain strings
     * @param baseQuery the base query to concatenate strings
     * @param patter string to concatenate to the base query
     * @return a string representing the query to prepare
     */
    private String queryBuilder(int size, String baseQuery, String patter){
        StringBuilder builder = new StringBuilder(baseQuery);
        for(int i = 1; i < size; i++){
            builder.append(patter);
        }
        
        return builder.toString();   
    }
}
