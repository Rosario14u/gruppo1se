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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Override
    public List<Appointment> getEmployeeAvailability(String username, LocalDate startDate, LocalDate endDate)
            throws AppointmentException,DateException {
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
    
    
    
}
