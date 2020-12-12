/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.maintenanceactivity;

import business.maintenanceactivity.Appointment;
import exception.AppointmentException;
import exception.DateException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rosar
 */
public interface EmployeeAppointmentDAO {
    public List<Appointment> getEmployeeAvailability(String username, LocalDate startDate, LocalDate endDate)
            throws AppointmentException,DateException;
    public boolean addEmployeeAvailability(String username, List<Appointment> listAppointment)
            throws AppointmentException;
    public int getDurationOfAssignedActivity(int activityId) throws AppointmentException;
}
