/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;
import business.maintenanceactivity.*;
import exception.MaintenanceActivityException;
import exception.NotValidParameterException;
import exception.SiteException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import persistence.database.ConnectionDB;


/**
 *
 * @author aless & vcoppola38
 */
public class MaintenanceActivityDAOImpl implements MaintenanceActivityDAO {
    private static final String INSERT_ACTIVITY = "INSERT INTO MaintenanceActivity (activityDescription,"
            + " estimatedInterventionTime, dateActivity, interruptibleActivity, typologyOfActivity,"
            + " typologyOfUnplannedActivity, typologyName, branchOffice, area, activityId) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static final String DELETE_ACTIVITY = "DELETE FROM MAINTENANCEACTIVITY WHERE ACTIVITYID=?";
    private static final String SELECT_ACTIVITY = "SELECT * FROM MaintenanceActivity WHERE activityId = ?";
    private static final String UPDATE_ACTIVITY = "UPDATE MaintenanceActivity SET activityDescription=?, "
            + "estimatedInterventionTime=?, dateActivity=?, interruptibleActivity=?, typologyOfActivity=?,"
            + " typologyOfUnplannedActivity=?, typologyName=?, branchOffice=?, area=? WHERE activityId = ?";
    private static final String SELECT_ACTIVITY_DATE_RANGE = "SELECT * FROM MaintenanceActivity WHERE dateActivity between ? and ?"
            + "order by activityId";
    private final SiteDao siteDao;
    
    /**
     * Constructor of MaintenanceActivityDAOImpl
     * @param siteDao 
     */
    public MaintenanceActivityDAOImpl(SiteDao siteDao) {
        this.siteDao = siteDao;
    }
    
    /**
     * 
     * @param activity
     * @return {@code boolean} true if the MaintenanceActivity is inserted into the database
     * @throws MaintenanceActivityException if there's an SQL error while inserting into the MaintenanceActivity table
     * @throws NotValidParameterException if this MaintenanceActivityDAOImpl has no SiteDao
     */
    /*Developed by Alessio Citro*/
    @Override
    public boolean addMaintenanceActivity(MaintenanceActivity activity) throws MaintenanceActivityException, NotValidParameterException{
        checkDao(siteDao,"Error in storing activity");
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_ACTIVITY);
            setPreparedStatement(preparedStatement, activity);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            throw new MaintenanceActivityException();
        }
    }
    
    /**
     * 
     * @param activityId
     * @return {@code boolean} true if at least one row has been deleted, false otherwise.
     * @throws MaintenanceActivityException if there is an SQL error while deleting an activity from the MaintenanceActivity table.
     * @throws NotValidParameterException if this MaintenanceActivityDAOImpl has no SiteDAO. 
     */
    /*Developed by Vincenza Coppola*/
    @Override
    public boolean deleteMaintenanceActivity(int activityId) throws MaintenanceActivityException, NotValidParameterException{
        checkDao(siteDao,"Error in deleting activity");
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_ACTIVITY);
            preparedStatement.setInt(1,activityId);
            int row = preparedStatement.executeUpdate();
            return row > 0;
        }
        catch (SQLException ex) {
            throw new MaintenanceActivityException("Maintenance Activity's deletion failed!");
        }   
    }
    
    
    /**
     * 
     * This method recover from a database the maintenance activity if exists, according to activityId parameter.<br>
     * SiteDAO required
     * @param activityId activity id of the maintenance activity to retrieve
     * @return {@code MaintenanceActivity} MaintenanceActivity if exists a maintenance activity
     * with corresponding id in database, null otherwise.
     * @throws exception.MaintenanceActivityException if there are problems in retrieving activity
     * @throws exception.NotValidParameterException if sitedao object is not correctly
     */
    /*Method developed by Rosario Gaeta*/
    @Override
    public MaintenanceActivity retrieveMaintenanceActivityDao(int activityId) throws MaintenanceActivityException, NotValidParameterException{
        checkDao(siteDao,"Error in deleting activity");
        try {
            MaintenanceActivity ma = null;
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_ACTIVITY);
            pstm.setInt(1,activityId);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
               ma = this.makeMaintenanceActivity(rs); 
            }
            return ma;
        } catch (SQLException ex) {
           throw new MaintenanceActivityException("Maintenance Activity retriving error");
        }  catch (SiteException ex) {
           throw new MaintenanceActivityException(ex.getMessage());
        }      
    }
    
    
   /**
    * This method build and retrieve the correct MaintenanceActivity object according to ResultSet
    * @param rs ResultSet from which to build maintenance activity object
    * @return {@code MaintenaceActivity} MaintenanceActivity according to the content of resultSet,
    * null if Result set is empty or there is an error
    * @throws SQLException
    * @throws exception.SiteException if there is a problem in retrieving site of the activity
    * @throws exception.MaintenanceActivityException if there are problem in conversion of unplanned typology
    */
    /*Method developed by Rosario Gaeta*/
    private MaintenanceActivity makeMaintenanceActivity(ResultSet rs) throws SQLException, SiteException, MaintenanceActivityException{
        try {
            String typologyOfActivity = rs.getString("typologyOfActivity").toUpperCase();
            String typologyOfUnplanned = rs.getString("typologyOfUnplannedActivity");
            typologyOfUnplanned = typologyOfUnplanned == null ? null : typologyOfUnplanned.toUpperCase();
            // Selection of the type of the object to create 
            MaintenanceActivityFactory.Typology type = typologyOfActivity.compareTo("PLANNED")==0 ? 
                    MaintenanceActivityFactory.Typology.PLANNED : 
                    MaintenanceActivityFactory.Typology.valueOf(typologyOfUnplanned);
            String branchOffice = rs.getString("branchOffice");
            String area = rs.getString("area");
            Site site = siteDao.retrieveSiteDao(branchOffice, area);
            MaintenanceProcedure procedure = null;
            String smp = rs.getString("smp");
            if (smp != null)
                procedure = new MaintenanceProcedure(smp);
            if (site != null){
                return MaintenanceActivityFactory.make(type, rs.getInt("activityId"), site,  rs.getString("typologyName"),
                        rs.getString("activityDescription"), rs.getInt("estimatedInterventionTime"),
                        LocalDate.parse(rs.getString("dateActivity")),procedure, null, rs.getBoolean("interruptibleActivity"));
            }
            throw new SiteException(); 
        } catch (SQLException ex) {
            throw ex;
        }catch(IllegalArgumentException ex){
            throw new MaintenanceActivityException("Typology of activity not valid");
        }
    }
    
    
    /**
     * This method allows to modify an existent Maintenance
     * activity into databse acccording to actvityId parameter
     * @param newActivity intance of Mintenance activity 
     * that contains the new fields to set
     * @return {@code true} if the the change
     * is successful, {@code false} otherwise
     * @throws exception.MaintenanceActivityException
     * if there are problems in modifyng activity
     * @throws exception.NotValidParameterException if 
     * required dao (siteDao) is not correctly initialized.
     */
    /*Developed by Antonio Gorrasi*/
    @Override
    public boolean modifyMaintenaceActivity(MaintenanceActivity newActivity) 
            throws MaintenanceActivityException, NotValidParameterException{
        checkDao(siteDao,"Error in deleting activity");
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(UPDATE_ACTIVITY);
            setPreparedStatement(pstm, newActivity);
            return pstm.executeUpdate()!=0;
        } catch (SQLException ex) {
            throw new MaintenanceActivityException("Modifying activity failed");
        }
    }
    
    
    /**
     * This method retrieve a list of MaintenanceActivity according to startDate and stopDate
     * SiteDAO required
     * @param startDate start date of the range
     * @param stopDate stop date of the range
     * @return {@code List<MaintenanceActivity>} listOfMainteananceActivity
     * @throws MaintenanceActivityException if there is a problem in retrieving maintenance activity
     * @throws exception.NotValidParameterException if required sitedao is not correctly initialized
     */
    @Override
    public List<MaintenanceActivity> retrieveMaintenanceActivityFromRange(LocalDate startDate, LocalDate stopDate)
            throws MaintenanceActivityException,NotValidParameterException{
        checkDao(siteDao,"Error in deleting activity");
        if (startDate==null || stopDate==null || startDate.isAfter(stopDate))
            throw new MaintenanceActivityException("Error on date");
        try {
            List<MaintenanceActivity> activityList = new ArrayList<>();
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_ACTIVITY_DATE_RANGE);
            pstm.setDate(1, Date.valueOf(startDate));
            pstm.setDate(2, Date.valueOf(stopDate));
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
               activityList.add(this.makeMaintenanceActivity(rs)); 
            }
            return activityList;
        } catch (SQLException ex) {
           throw new MaintenanceActivityException("Maintenance Activity retriving error");
        } catch (SiteException ex) {
            throw new MaintenanceActivityException(ex.getMessage());
        }
    }
    
    
    /**
     * This method prepare a statement
     * @param preparedStatement statement to prepare
     * @param activity Maintenace activity 
     * @throws SQLException if there is a problem preparing the statement
     */
    private void setPreparedStatement(PreparedStatement preparedStatement, MaintenanceActivity activity) throws SQLException{
        preparedStatement.setString(1, activity.getActivityDescription());
        preparedStatement.setInt(2, activity.getEstimatedInterventionTime());
        preparedStatement.setObject(3, activity.getDate());
        preparedStatement.setBoolean(4, activity.isInterruptibleActivity());
        if (PlannedMaintenanceActivity.class.isInstance(activity)){
            preparedStatement.setString(5, "Planned");
            preparedStatement.setNull(6, java.sql.Types.VARCHAR);
        } else if(Ewo.class.isInstance(activity)){
            preparedStatement.setString(5, "Unplanned");
            preparedStatement.setString(6, "EWO");
        } else {
            preparedStatement.setString(5, "Unplanned");
            preparedStatement.setString(6, "Extra");
        }
        preparedStatement.setString(7, activity.getTypology());
        preparedStatement.setString(8, activity.getSite().getBranchOffice());
        preparedStatement.setString(9, activity.getSite().getArea());
        preparedStatement.setInt(10, activity.getActivityId());
    }
    
    
    /**
     * This method checks that the siteDao parameter 
     * is valid, otherwise it throws an exception.
     * @param siteDao data access object for site
     * @param message Error message
     * @throws NotValidParameterException if siteDao is {@code null}
     */
    private void checkDao(SiteDao siteDao, String message) throws NotValidParameterException{
        if(siteDao == null)
            throw new NotValidParameterException(message);
    }


}
