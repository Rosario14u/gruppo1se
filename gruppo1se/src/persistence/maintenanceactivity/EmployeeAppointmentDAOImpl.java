/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import exception.AppointmentException;
import exception.DateException;
import exception.NotValidParameterException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;
import persistence.database.ConnectionDB;

/**
 *
 * @author rosar
 */
public class EmployeeAppointmentDAOImpl implements EmployeeAppointmentDAO {
    private static final String SELECT_EMPLOYEE_AVAILABILITY = "SELECT * FROM EmployeeAppointment where"
            + " username = ? and startDateTime between ? and ?";
    private static final String INSERT_EMPLOYEE_AVAILABILITY = "INSERT INTO EmployeeAppointment"
            + "(activityId,username,startDateTime,duration) VALUES(?,?,?,?)";
    private static final String SELECT_EMPLOYEE = "SELECT sum(duration) as sumduration FROM EmployeeAppointment where"
            + " activityId = ?";
    
    /**
     * This method returns the list of appointments that the maintainer has in a certain<br>
     * range of date. Returns an Empty list if there aren't appointments in that range of date
     * @param username username of the maintainer
     * @param startDate start date of the range
     * @param endDate end date of the range
     * @return {@code List<Appointment>} appointments of the maintainer
     * @throws AppointmentException if there are problems in the retrieving of the appointments<br>
     * or username is not valid
     * @throws DateException if startdate is after end date 
     */
    /*Developed by Rosario Gaeta*/
    @Override
    public List<Appointment> getEmployeeAvailability(String username, LocalDate startDate, LocalDate endDate)
            throws AppointmentException,DateException, NotValidParameterException {
        if (startDate.isAfter(endDate))
            throw new DateException("startDate and endDate not valid");
        if(username == null || username.trim().replaceAll("  +", " ").equals(""))
            throw new AppointmentException("Error in retrieving appointment");
        try {
            List<Appointment> appointmentList = new ArrayList<>();
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_EMPLOYEE_AVAILABILITY);
            pstm.setString(1, username);
            pstm.setDate(2,Date.valueOf(startDate));
            pstm.setTimestamp(3, Timestamp.valueOf(endDate.atTime(23,59,59)));
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                appointmentList.add(new Appointment(rs.getInt("activityId"),
                        rs.getTimestamp("startDateTime").toLocalDateTime(),rs.getInt("duration")));
            }
            return appointmentList;
        } catch (SQLException ex) {
            throw new AppointmentException("Error in retrieving appointment");
            
        }        
    }
    
    /**
     * This method allows to associate a list of appointments to a maintainer with the specified username 
     * @param username Username of the maintainer
     * @param listAppointment list of appointments to be added
     * @return {@code boolean} returns true if the operation is successful
     * @throws AppointmentException if the listAppointment is empty or if there is a problem in saving data<br>
     * in the system
     */
    /*Developed by Rosario Gaeta*/
    @Override
    public boolean addEmployeeAvailability(String username, List<Appointment> listAppointment) throws AppointmentException {
        if(username == null || username.trim().replaceAll("  +", "").equals("") || listAppointment == null)
            throw new AppointmentException();
        if(listAppointment.isEmpty())
            return false;
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            for(Appointment appointment: listAppointment){
                PreparedStatement pstm = conn.prepareStatement(INSERT_EMPLOYEE_AVAILABILITY);
                pstm.setInt(1, appointment.getActivityId());
                pstm.setString(2,username);
                pstm.setTimestamp(3,Timestamp.valueOf(appointment.getStartDateAndTime()));
                pstm.setInt(4, appointment.getDuration());
                pstm.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            throw new AppointmentException("Error in inserting appointment");
        }  
    }
    /**
     * This method get sum of the durations maintenance appointments assigned to an activity
     * @param activityId activity id of the maintenance activity 
     * @return {@code int} total duration of maintenance appointments of the activity
     * @throws AppointmentException if activity id is not valid
     */
    /*Developed by Rosario Gaeta*/
    @Override
    public int getDurationOfAssignedActivity(int activityId) throws AppointmentException {
        if(activityId <= 0)
           throw new AppointmentException("Error in retrieving duration of activity assigned");
        int sum = 0;
        try {
            Connection conn = ConnectionDB.getInstanceConnection().getConnection();
            PreparedStatement pstm = conn.prepareStatement(SELECT_EMPLOYEE);
            pstm.setInt(1, activityId);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                sum = rs.getInt("sumduration");
            }
            return sum;
        } catch (SQLException ex) {
            throw new AppointmentException("Error in retrieving duration of activity assigned");
        }
    }
    
    
    
}
