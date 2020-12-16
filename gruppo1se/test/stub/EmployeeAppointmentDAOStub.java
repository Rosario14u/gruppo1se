/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stub;

import business.maintenanceactivity.Appointment;
import exception.AppointmentException;
import exception.DateException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import persistence.maintenanceactivity.EmployeeAppointmentDAO;

/**
 *
 * @author gorra
 */
public class EmployeeAppointmentDAOStub implements EmployeeAppointmentDAO {

    /**
     * Simulates the behaviour of getEmployeeAvailability
     * of EmployeeAppointmentDAO.
     * @param username username of the Maintainer
     * @param startDate first day of the week
     * @param endDate last day of the week
     * @return a list of {@code Appointment}
     * @throws AppointmentException if username is 
     * {@code null} or empty string
     * @throws DateException if startDate is after endDate
     */
    //Developed by Antonio Gorrasi
    @Override
    public List<Appointment> getEmployeeAvailability(String username, LocalDate startDate, LocalDate endDate)
            throws AppointmentException, DateException {
        if(startDate.isAfter(endDate))
            throw new DateException();
        else if(username == null || username.equals(""))
            throw new AppointmentException();
        else if(username.equals("username1")){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return new ArrayList<>(){{
                add(new Appointment(1, LocalDateTime.parse("2020-01-05 00:00:00", formatter), 20));
                add(new Appointment(2, LocalDateTime.parse("2020-01-05 00:00:00", formatter), 40));
            }};
        }else{
            return new ArrayList<>();
        }
    }
    
    
    /**
     * Simulates the behaviour of addEmployeeAvailability of EmployeeAppointmentDAO.
     * @param username
     * @param listAppointment
     * @return {@code booelan}
     * @throws AppointmentException if username == null, empty string, listAppointment == null
     * or username = "username2"
     */
    @Override
    public boolean addEmployeeAvailability(String username, List<Appointment> listAppointment) throws AppointmentException {
        if(username == null || username.trim().replaceAll("  +", " ").equals("") || listAppointment == null ||
                (username.equals("username2") && !listAppointment.isEmpty())){
            /* This if simulate a series of cases in which the method throws an appointment exception. The case in which 
            username.equals("username2") && !listAppointment.isEmpty() simulates the case of an username that already exists
            and so this method throws an exception.*/
            throw new AppointmentException();
        }else if(listAppointment.isEmpty()){ /*Simulate the case in which the passed list is empty*/
            return false;
        }else{
            return true; /*In the other cases the stub returns true*/
        }
    }
    
   
    /**
     * Simulates the behaviour of getDurationOfAssignedActivity of EmployeeAppointmentDAO.
     * @param activityId
     * @return
     * @throws AppointmentException 
     */
    @Override
    public int getDurationOfAssignedActivity(int activityId) throws AppointmentException {
        switch (activityId) {
            case 1:
                return 100;
            case 2:
                throw new AppointmentException();
            default:
                return 0;
        }
    }
        
}
