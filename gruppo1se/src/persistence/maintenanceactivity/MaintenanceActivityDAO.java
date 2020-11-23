/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;
import business.maintenanceactivity.*;
import java.sql.*;
/**
 *
 * @author aless & vincy
 */
public class MaintenanceActivityDAO {
    private static final String SQL_INSERT = "INSERT INTO MaintenanceActivity (activityId, activityDescription, estimatedInterventionTime, dateActivity, interruptibleActivity, typologyOfActivity, typologyOfUnplannedActivity, typologyName, branchOffice, area) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_DELETE = "DELETE FROM MAINTENANCEACTIVITY WHERE ACTIVITYID=?";
    
    public boolean addMaintenanceActivity(MaintenanceActivity activity, Connection conn){
        try {
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
    
    public boolean deleteMaintenanceActivity(MaintenanceActivity activity,Connection conn){
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1,activity.getActivityId());
            int row = preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException ex) {
            return false;
        }   
    }
}
