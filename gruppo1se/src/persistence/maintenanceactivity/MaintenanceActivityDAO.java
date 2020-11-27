/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;
import business.maintenanceactivity.*;
import java.sql.*;
import java.time.LocalDate;
import persistence.database.ConnectionDB;
/**
 *
 * @author aless & vincy
 */
public class MaintenanceActivityDAO {
    private static final String SQL_INSERT = "INSERT INTO MaintenanceActivity (activityId, activityDescription, estimatedInterventionTime, dateActivity, interruptibleActivity, typologyOfActivity, typologyOfUnplannedActivity, typologyName, branchOffice, area) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM MAINTENANCEACTIVITY WHERE ACTIVITYID=?";
    
    
    public boolean addMaintenanceActivity(MaintenanceActivity activity){
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            if (PlannedMaintenanceActivity.class.isInstance(activity)){
                preparedStatement.setInt(1, activity.getActivityId());
                preparedStatement.setString(2, activity.getActivityDescription());
                preparedStatement.setInt(3, activity.getEstimatedInterventionTime());
                preparedStatement.setObject(4, activity.getDate());
                preparedStatement.setBoolean(5, activity.isInterruptibleActivity());
                preparedStatement.setString(6, "Planned");
                preparedStatement.setNull(7, java.sql.Types.VARCHAR);
                preparedStatement.setString(8, activity.getTypology());
                preparedStatement.setString(9, activity.getSite().getBranchOffice());
                preparedStatement.setString(10, activity.getSite().getArea());
                int row = preparedStatement.executeUpdate();
            } else if(Ewo.class.isInstance(activity)){
                preparedStatement.setInt(1, activity.getActivityId());
                preparedStatement.setString(2, activity.getActivityDescription());
                preparedStatement.setInt(3, activity.getEstimatedInterventionTime());
                preparedStatement.setObject(4, activity.getDate());
                preparedStatement.setBoolean(5, activity.isInterruptibleActivity());
                preparedStatement.setString(6, "Unplanned");
                preparedStatement.setString(7, "EWO");
                preparedStatement.setString(8, activity.getTypology());
                preparedStatement.setString(9, activity.getSite().getBranchOffice());
                preparedStatement.setString(10, activity.getSite().getArea());
                int row = preparedStatement.executeUpdate();
            } else {
                preparedStatement.setInt(1, activity.getActivityId());
                preparedStatement.setString(2, activity.getActivityDescription());
                preparedStatement.setInt(3, activity.getEstimatedInterventionTime());
                preparedStatement.setObject(4, activity.getDate());
                preparedStatement.setBoolean(5, activity.isInterruptibleActivity());
                preparedStatement.setString(6, "Unplanned");
                preparedStatement.setString(7, "Extra");
                preparedStatement.setString(8, activity.getTypology());
                preparedStatement.setString(9, activity.getSite().getBranchOffice());
                preparedStatement.setString(10, activity.getSite().getArea());
                int row = preparedStatement.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    //Returns true if at least one row has been deleted
    public boolean deleteMaintenanceActivity(int activityId){
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1,activityId);
            int row = preparedStatement.executeUpdate();
            return row > 0;
        }
        catch (SQLException ex) {
            return false;
        }   
    }
    /**
     * 
     * This method recover from a database the maintenance activity if exists, according to activityId parameter.
     * @param activityId activity id of the maintenance activity to retrieve
     * @param conn
     * @return {@code MaintenanceActivity} MaintenanceActivity if exists a maintenance activity
     * with corresponding id in database, null otherwise.
     */
    /*Method developed by Rosario Gaeta*/
    public MaintenanceActivity retrieveMaintenanceActivityDao(int activityId){
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            String query = "SELECT * FROM MaintenanceActivity WHERE activityId = ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1,activityId);
            ResultSet rs = pstm.executeQuery();
            MaintenanceActivity ma = this.makeMaintenanceActivity(rs);
            return ma;
        } catch (SQLException ex) {
           return null;
        }       
    }
   /**
    * This method build and retrieve the correct MaintenanceActivity object according to ResultSet
    * @param rs ResultSet from which to build maintenance activity object
    * @param conn
    * @return {@code MaintenaceActivity} MaintenanceActivity according to the content of resultSet,
    * null if Result set is empty or there is an error
    * @throws SQLException 
    */
    /*Method developed by Rosario Gaeta*/
    private MaintenanceActivity makeMaintenanceActivity(ResultSet rs) throws SQLException{
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            while(rs.next()){
                String typologyOfActivity = rs.getString("typologyOfActivity").toUpperCase();
                String typologyOfUnplanned = rs.getString("typologyOfUnplannedActivity");
                typologyOfUnplanned = typologyOfUnplanned == null ? null : typologyOfUnplanned.toUpperCase();
                Site site = new SiteDao().retrieveSiteDao(rs.getString("branchOffice"),
                        rs.getString("area"));
                // Selection of the type of the object to create 
                MaintenanceActivityFactory.Typology type = typologyOfActivity.compareTo("PLANNED")==0 ? 
                        MaintenanceActivityFactory.Typology.PLANNED : 
                        MaintenanceActivityFactory.Typology.valueOf(typologyOfUnplanned); 
                return MaintenanceActivityFactory.make(type, rs.getInt("activityId"), site, 
                            rs.getString("typologyName"), rs.getString("activityDescription"), 
                            rs.getInt("estimatedInterventionTime"), LocalDate.parse(rs.getString("dateActivity")),
                            null, null,rs.getBoolean("interruptibleActivity"));
            }
            return null;
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
        /**
     * This method allows to modify an existent Maintenance activity into databse, acccording to actvityId parameter
     * @param newActivity intance of Mintenance activity that contains the new fields to set
     * @param conn
     * @return {@code true} if the the change is successful, false otherwise
     */
    /*Developed by Antonio Gorrasi*/
    public boolean modifyMaintenaceActivity(MaintenanceActivity newActivity) {
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            String query = "UPDATE MaintenanceActivity SET activityDescription=?, "
                    + "estimatedInterventionTime=?, dateActivity=?, "
                    + "interruptibleActivity=?, branchOffice=?, area=?, "
                    + "typologyName=?, typologyOfActivity=?, typologyOfUnplannedActivity=? "
                    + "WHERE activityId = ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1,newActivity.getActivityDescription());
            pstm.setInt(2,newActivity.getEstimatedInterventionTime());
            pstm.setDate(3,Date.valueOf(newActivity.getDate()));
            pstm.setBoolean(4,newActivity.isInterruptibleActivity());
            pstm.setString(5,newActivity.getSite().getBranchOffice());
            pstm.setString(6,newActivity.getSite().getArea());
            pstm.setString(7,newActivity.getTypology());  
            if(newActivity instanceof PlannedMaintenanceActivity){
                pstm.setString(8,"Planned");
                pstm.setString(9, null);
            }
            else if (newActivity instanceof Ewo){
                pstm.setString(8,"Unplanned");
                pstm.setString(9, "EWO");
            }
            else{
                pstm.setString(8,"Unplanned");
                pstm.setString(9,"Extra");
            }
            pstm.setInt(10,newActivity.getActivityId());
            if(pstm.executeUpdate()==0){
                return false;
            }
            return true;
        } catch (SQLException ex) {
            return false;
        } 
    }
}
