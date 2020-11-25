/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;
import business.maintenanceactivity.*;
import java.sql.*;
import java.time.LocalDate;
/**
 *
 * @author aless & vincy
 */
public class MaintenanceActivityDAO {
    private String url = "jdbc:postgresql://localhost/Gruppo1_SE";
    private String user = "gruppo1";
    private String pwd = "123456";
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
    
    //Returns true if at least one row has been deleted
    public boolean deleteMaintenanceActivity(int activityId,Connection conn){
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1,activityId);
            int row = preparedStatement.executeUpdate();
            return row > 0;
        }
        catch (SQLException ex) {
            return false;
        }   
    }
    
    public MaintenanceActivity retrieveMaintenanceActivityDao(int activityId, Connection conn){
        try {
            String query = "SELECT * FROM MaintenanceActivity WHERE activityId = ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1,activityId);
            ResultSet rs = pstm.executeQuery();
            MaintenanceActivity ma = this.makeMaintenanceActivity(rs, conn);
            return ma;
        } catch (SQLException ex) {
           return null;
        }       
    }
   
    private MaintenanceActivity makeMaintenanceActivity(ResultSet rs, Connection conn) throws SQLException{
        try {
            while(rs.next()){
                String typologyOfActivity = rs.getString("typologyOfActivity").toUpperCase();
                String typologyOfUnplanned = rs.getString("typologyOfUnplannedActivity");
                typologyOfUnplanned = typologyOfUnplanned == null ? null : typologyOfUnplanned.toUpperCase();
                Site site = new SiteDao().retrieveSiteDao(new Site(rs.getString("branchOffice"), rs.getString("area")),conn);
                // Selection of the type of the object to create 
                MaintenanceActivityFactory.Typology type = typologyOfActivity.compareTo("PLANNED")==0 ? 
                        MaintenanceActivityFactory.Typology.PLANNED : MaintenanceActivityFactory.Typology.valueOf(typologyOfUnplanned); 
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
    
    public boolean modifyMaintenaceActivity(int activityId, MaintenanceActivity newActivity, Connection conn) {
        try {
            //conn = DriverManager.getConnection(url, user, pwd);
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
            pstm.setInt(10,activityId);
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        } 
    }
}
